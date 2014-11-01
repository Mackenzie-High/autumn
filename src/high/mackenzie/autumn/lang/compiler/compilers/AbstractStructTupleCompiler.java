package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ClassFile;
import autumn.lang.compiler.ast.commons.IRecord;
import autumn.lang.internals.Helpers;
import com.google.common.collect.Lists;
import high.mackenzie.autumn.lang.compiler.typesystem.CustomConstructor;
import high.mackenzie.autumn.lang.compiler.typesystem.CustomFormalParameter;
import high.mackenzie.autumn.lang.compiler.typesystem.CustomMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IConstructor;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IFormalParameter;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IInterfaceType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;
import high.mackenzie.autumn.lang.compiler.utils.BridgeMethod;
import high.mackenzie.autumn.lang.compiler.utils.GetterMethod;
import high.mackenzie.autumn.lang.compiler.utils.RecordElement;
import high.mackenzie.autumn.lang.compiler.utils.SetterMethod;
import high.mackenzie.autumn.lang.compiler.utils.TypeSystemUtils;
import high.mackenzie.autumn.lang.compiler.utils.Utils;
import java.util.LinkedList;
import java.util.List;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TableSwitchInsnNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

/**
 * This class generalizes the compilation of struct-definitions and tuple-definitions.
 *
 * <p>
 * Note: Design-definitions are technically records, but they are compiled very differently.
 * </p>
 *
 * @author Mackenzie High
 */
public class AbstractStructTupleCompiler
        extends AbstractRecordCompiler
{
    /**
     * This is the type of the tuple's only constructor.
     */
    private CustomConstructor ctor;

    /**
     * This flag is true, iff the record is a struct.
     */
    private final boolean struct;

    /**
     * Sole Constructor.
     *
     * @param module is the module that contains the tuple being compiled.
     * @param node is the AST node that represents the tuple being compiled.
     */
    public AbstractStructTupleCompiler(final ModuleCompiler module,
                                       final IRecord node,
                                       final boolean is_tuple)
    {
        super(module, node);

        assert module != null;
        assert node != null;
        assert module.tuples.contains(node);

        this.struct = !is_tuple;
    }

    /**
     * This method gets the name of a field that stores an element.
     *
     * <p>
     * This method is needed in order to prevent name collisions with special fields.
     * </p>
     *
     * @param element is the name of the element.
     * @return the name of the field that stores the value of the element.
     */
    private String nameOfField(final String element)
    {
        assert element != null;

        return "element$" + element;
    }

    /**
     * This method generates the compiled class-file.
     *
     * @return the compiled class-file.
     */
    public ClassFile build()
    {
        final String internal_name = Utils.internalName(type);

        final String source_name = Utils.sourceName(type);

        /**
         * Create the bytecode representations of the tuple's fields.
         */
        final List<FieldNode> fields = Lists.newLinkedList();

        // Create the field that indicates whether the tuple is mutable.
        // Tuples are immutable, by default.
        fields.add(new FieldNode(Opcodes.ACC_PRIVATE,
                                 "MUTABLE",
                                 "Z",
                                 null,
                                 false));

        // Create the field that stores the bindings of special-methods.
        fields.add(new FieldNode(Opcodes.ACC_PRIVATE,
                                 "BINDINGS",
                                 program.typesystem.utils.SPECIAL_METHODS.getDescriptor(),
                                 null,
                                 false));

        // Create the field that stores the names of the elements.
        fields.add(new FieldNode(Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC + Opcodes.ACC_FINAL,
                                 "KEYS",
                                 "Ljava/util/List;",
                                 null,
                                 null));

        // Create the field that stores the types of the elements.
        fields.add(new FieldNode(Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC + Opcodes.ACC_FINAL,
                                 "TYPES",
                                 "Ljava/util/List;",
                                 null,
                                 null));

        // Create the field that stores the instance() tuple.
        fields.add(new FieldNode(Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC + Opcodes.ACC_FINAL,
                                 "INSTANCE",
                                 type.getDescriptor(),
                                 null,
                                 null));

        // Create the fields that store the values of the elements.
        fields.addAll(this.generateElementFields());

        /**
         * Create the bytecode representation of the tuple itself.
         */
        final ClassNode clazz = new ClassNode();
        {
            clazz.version = Opcodes.V1_6;
            clazz.visibleAnnotations = Lists.newLinkedList();
            clazz.access = type.getModifiers();
            clazz.name = internal_name;
            clazz.superName = Utils.internalName(type.getSuperclass());
            clazz.fields = fields;
            clazz.methods = Lists.newLinkedList();
            clazz.sourceFile = String.valueOf(node.getLocation().getFile());

            /**
             * The record may implement zero or more designs, as specified by the user.
             */
            for (IInterfaceType superinterface : type.getSuperinterfaces())
            {
                clazz.interfaces.add(Utils.internalName(superinterface));
            }

            /**
             * Add some special methods to the tuple.
             */
            clazz.methods.add(this.generateStaticCtor());

            clazz.methods.add(this.generateCtor());

            clazz.methods.add(this.generateMethodInstance());

            clazz.methods.add(this.generateMethodIsStruct());

            clazz.methods.add(this.generateMethodIsTuple());

            clazz.methods.add(this.generateMethodKeys());

            clazz.methods.add(this.generateMethodTypes());

            clazz.methods.add(this.generateMethodValues());

            clazz.methods.add(this.generateMethodIsMutable());

            clazz.methods.add(this.generateMethodMutable());

            clazz.methods.add(this.generateMethodImmutable());

            clazz.methods.add(this.generateMethodGet());

            clazz.methods.add(this.generateMethodSet());

            clazz.methods.add(this.generateMethodBind());

            clazz.methods.add(this.generateMethodBindings());

            clazz.methods.addAll(this.generateBridgeMethods());

            /**
             * Generate the setter and getter methods.
             */
            for (RecordElement element : analyzer.elements.values())
            {
                /**
                 * Generate the primary setter and primary getter methods.
                 * In other words, generate the methods that can actually set or get an element.
                 */
                clazz.methods.add(this.generateSetter(element.setter()));
                clazz.methods.add(this.generateGetter(element.getter()));
            }
        }

        /**
         * Assemble the bytecode into an array of bytes.
         */
        final ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        clazz.accept(writer);
        final byte[] bytecode = writer.toByteArray();

        /**
         * Create the class-file object that will store the emitted bytecode.
         */
        final ClassFile file = new ClassFile(source_name, bytecode);

        return file;
    }

    /**
     * This method creates the type-system representation of the only constructor in the tuple.
     *
     * @return the aforedescribed constructor.
     */
    @Override
    protected IConstructor typeofCtor()
    {
        final List<IFormalParameter> formals = Lists.newLinkedList();

        /**
         * Create a formal parameter for each element in the tuple.
         */
        for (String element : keys)
        {
            final CustomFormalParameter formal = new CustomFormalParameter();

            formal.setType(typeOfElement(element));

            formals.add(formal);
        }

        ctor = new CustomConstructor(program.typesystem.typefactory());

        ctor.setAnnotations(new LinkedList());
        ctor.setModifiers(Opcodes.ACC_PUBLIC);
        ctor.setOwner(type);
        ctor.setParameters(formals);
        ctor.setReturnType(program.typesystem.utils.VOID);
        ctor.setThrowsClause(new LinkedList());

        return ctor;
    }

    /**
     * This method creates the type-system representation of the instance() method.
     *
     * @return the aforedescribed method.
     */
    @Override
    protected IMethod typeofInstance()
    {
        final CustomMethod method = new CustomMethod(program.typesystem.typefactory(), false);

        method.setAnnotations(new LinkedList());
        method.setModifiers(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC);
        method.setName("instance");
        method.setOwner(type);
        method.setParameters(new LinkedList());
        method.setReturnType(type);
        method.setThrowsClause(new LinkedList());

        return method;
    }

    /**
     * This method generates the bytecode representations of the fields that store the elements.
     *
     * @return the generated bytecode.
     */
    private List<FieldNode> generateElementFields()
    {
        final List<FieldNode> fields = Lists.newLinkedList();

        /**
         * Generate one field for each element.
         */
        for (String element : keys)
        {
            // The field is private, because element's are accessed via getters and setters.
            final int access = Opcodes.ACC_PRIVATE;

            // The name of the field will be given a prefix.
            // This prevents name collisions with special fields.
            final String name = nameOfField(element);

            // The descriptor describes the static-type of the element.
            final String desc = typeOfElement(element).getDescriptor();

            // Create the bytecode representation of the field.
            final FieldNode field = new FieldNode(access, name, desc, null, null);

            fields.add(field);
        }

        return fields;
    }

    /**
     * This method generates the bytecode representation of the tuple's only constructor.
     *
     * <p>
     * The constructor's actual parameters are the values of the tuple's elements.
     * Parameter[i] of the constructor provides the value of element[i] of the tuple.
     * </p>
     *
     * @return the aforedescribed bytecode.
     */
    private MethodNode generateCtor()
    {
        // The generated constructor must do the following:
        // . invoke super()
        // . for each parameter [p]:
        // . . transfer [p] into the field that will store [p].
        // . set BINDINGS = SpecialMethods.EMPTY
        // . return
        //
        //////////////////////////////////////////////////////////

        final MethodNode method = Utils.bytecodeOf(module, ctor);

        /**
         * Generate the bytecode that invokes super().
         */
        method.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0)); // Load 'this'
        method.instructions.add(new MethodInsnNode(Opcodes.INVOKESPECIAL,
                                                   Utils.internalName(program.typesystem.utils.ABSTRACT_RECORD),
                                                   "<init>",
                                                   "()V"));


        /**
         * Skip the first memory address (i.e. address zero),
         * because that is where 'this' is automatically stored.
         */
        int address = 1;

        /**
         * Transfer each actual parameter into the field that stores the related element.
         */
        for (String element : keys)
        {
            final IVariableType element_type = typeOfElement(element);

            final String owner = Utils.internalName(type);
            final String name = nameOfField(element);
            final String desc = element_type.getDescriptor();

            method.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0)); // Load 'this'
            method.instructions.add(Utils.selectLoadVarInsn(element_type, address));
            method.instructions.add(new FieldInsnNode(Opcodes.PUTFIELD, owner, name, desc));

            address += Utils.sizeof(element_type);
        }
        /**
         * Set the field that stores the special-method bindings to its default value.
         *
         * 1. Load the 'this' onto the operand-stack.
         * 2. Get the EMPTY SpecialMethods object.
         * 3. Set the BINDINGS field.
         */
        method.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0)); // Load 'this'

        method.instructions.add(new FieldInsnNode(Opcodes.GETSTATIC,
                                                  Utils.internalName(program.typesystem.utils.SPECIAL_METHODS),
                                                  "EMPTY",
                                                  program.typesystem.utils.SPECIAL_METHODS.getDescriptor()));

        method.instructions.add(new FieldInsnNode(Opcodes.PUTFIELD,
                                                  Utils.internalName(type),
                                                  "BINDINGS",
                                                  program.typesystem.utils.SPECIAL_METHODS.getDescriptor()));

        /**
         * Return from the constructor.
         */
        method.instructions.add(new InsnNode(Opcodes.RETURN));

        return method;
    }

    /**
     * This method generates the tuple's static constructor.
     *
     * <p>
     * The static-constructor is needed in order to initialize a few static fields.
     * </p>
     *
     * @return the generated method.
     */
    private MethodNode generateStaticCtor()
    {
        final MethodNode clinit = new MethodNode(Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC,
                                                 "<clinit>",
                                                 "()V",
                                                 null,
                                                 new String[0]);

        /**
         * Initialize the KEYS field.
         */
        initListOfKeys(clinit.instructions);

        /**
         * Initialize the TYPES field.
         */
        initListOfTypes(clinit.instructions);

        /**
         * Initialize the INSTANCE field.
         */
        initInstance(clinit.instructions);

        /**
         * Exit the static constructor.
         */
        clinit.instructions.add(new InsnNode(Opcodes.RETURN));

        return clinit;
    }

    /**
     * This method generates bytecode that initializes the field that stores the list of keys.
     *
     * @param code is the bytecode being generated.
     */
    private void initListOfKeys(final InsnList code)
    {
        final CollectionCompiler<String> cmp = new CollectionCompiler<String>()
        {
            @Override
            public void compile(final String element)
            {
                code().add(new LdcInsnNode(element));
            }

            @Override
            public InsnList code()
            {
                return code;
            }
        };

        /**
         * Generate the bytecode that creates a mutable list containing the keys.
         */
        cmp.compile(keys);

        /**
         * Make the list immutable.
         */
        Utils.makeListImmutable(code);

        /**
         * Assign the immutable list to the field.
         */
        code.add(new FieldInsnNode(Opcodes.PUTSTATIC,
                                   Utils.internalName(type),
                                   "KEYS",
                                   "Ljava/util/List;"));
    }

    /**
     * This method generates bytecode that initializes the field that stores the map of types.
     *
     * @param code is the bytecode being generated.
     */
    private void initListOfTypes(final InsnList code)
    {
        final CollectionCompiler<String> cmp = new CollectionCompiler<String>()
        {
            @Override
            public void compile(final String element)
            {
                code().add(Utils.ldcClass(typeOfElement(element)));
            }

            @Override
            public InsnList code()
            {
                return code;
            }
        };

        /**
         * Generate the bytecode that creates a mutable list containing the keys.
         */
        cmp.compile(keys);

        /**
         * Make the list immutable.
         */
        Utils.makeListImmutable(code);

        /**
         * Assign the immutable list to the field.
         */
        code.add(new FieldInsnNode(Opcodes.PUTSTATIC,
                                   Utils.internalName(type),
                                   "TYPES",
                                   "Ljava/util/List;"));
    }

    /**
     * This method generates bytecode that initializes the field containing instance() tuple.
     *
     * @param code is the bytecode being generated.
     */
    private void initInstance(final InsnList code)
    {
        /**
         * Create a new uninitialized instance of the tuple.
         */
        code.add(new TypeInsnNode(Opcodes.NEW, Utils.internalName(type)));

        /**
         * Duplicate the object-reference.
         */
        code.add(new InsnNode(Opcodes.DUP));

        /**
         * Load the value of each element onto the operand-stack.
         */
        for (String element : keys)
        {
            /**
             * Load the default value of the element onto the operand-stack.
             */
            code.add(Utils.ldcDefault(typeOfElement(element)));
        }

        /**
         * Invoke the constructor in order to create a copy of this object.
         */
        code.add(new MethodInsnNode(Opcodes.INVOKESPECIAL,
                                    Utils.internalName(type),
                                    "<init>",
                                    ctor.getDescriptor()));

        /**
         * Assign the immutable list to the field.
         */
        code.add(new FieldInsnNode(Opcodes.PUTSTATIC,
                                   Utils.internalName(type),
                                   "INSTANCE",
                                   type.getDescriptor()));
    }

    /**
     * This method generates the bytecode representations of the bridge methods.
     *
     * @return the generated methods.
     */
    private List<MethodNode> generateBridgeMethods()
    {
        final List<MethodNode> result = Lists.newLinkedList();

        for (BridgeMethod bridge : bridges)
        {
            result.add(bridge.compile(module));
        }

        return result;
    }

    /**
     * This method generates the bytecode representation of the instance() method.
     *
     * @return the generated method.
     */
    private MethodNode generateMethodInstance()
    {
        final MethodNode method = Utils.bytecodeOf(module,
                                                   TypeSystemUtils.find(type.getMethods(),
                                                                        "instance",
                                                                        "()" + type.getDescriptor()));

        // Remove the abstract modifier.
        method.access = method.access & (~Opcodes.ACC_ABSTRACT);

        /**
         * The method simply retrieves the value in a static field and returns it.
         */
        method.instructions.add(new FieldInsnNode(Opcodes.GETSTATIC,
                                                  Utils.internalName(type),
                                                  "INSTANCE",
                                                  type.getDescriptor()));

        method.instructions.add(new InsnNode(Opcodes.ARETURN));

        return method;
    }

    /**
     * This method generates the bytecode representation of the isStruct() method.
     *
     * @return the generated method.
     */
    private MethodNode generateMethodIsStruct()
    {
        final MethodNode method = Utils.bytecodeOf(module,
                                                   TypeSystemUtils.find(type.getAllVisibleMethods(),
                                                                        "isStruct",
                                                                        "()Z"));

        // Remove the abstract modifier.
        method.access = method.access & (~Opcodes.ACC_ABSTRACT);

        // Return a constant boolean value.
        method.instructions.add(new LdcInsnNode(struct));
        method.instructions.add(new InsnNode(Opcodes.IRETURN));

        return method;
    }

    /**
     * This method generates the bytecode representation of the isTuple() method.
     *
     * @return the generated method.
     */
    private MethodNode generateMethodIsTuple()
    {
        final MethodNode method = Utils.bytecodeOf(module,
                                                   TypeSystemUtils.find(type.getAllVisibleMethods(),
                                                                        "isTuple",
                                                                        "()Z"));

        // Remove the abstract modifier.
        method.access = method.access & (~Opcodes.ACC_ABSTRACT);

        // Return a constant boolean value.
        method.instructions.add(new LdcInsnNode(!struct));
        method.instructions.add(new InsnNode(Opcodes.IRETURN));

        return method;
    }

    /**
     * This method generates the bytecode representation of the set(int, Object) method.
     *
     * @return the generated method.
     */
    private MethodNode generateMethodSet()
    {
        final MethodNode method = Utils.bytecodeOf(module,
                                                   TypeSystemUtils.find(type.getAllVisibleMethods(),
                                                                        "set",
                                                                        "(ILjava/lang/Object;)" + program.typesystem.utils.RECORD.getDescriptor()));

        // Remove the abstract modifier.
        method.access = method.access & (~Opcodes.ACC_ABSTRACT);

        final LabelNode default_case = new LabelNode();

        /**
         * Essentially, this will generate a switch-statement.
         * The switch-case branches based on the index that is given as an parameter to the method.
         * The default-case will be generated at the bottom of this method,.
         * because no jump-table is needed, if the tuple is empty.
         */
        if (!keys.isEmpty())
        {
            /**
             * Generate the jump-table itself.
             */
            final int min = 0;
            final int max = keys.isEmpty() ? 0 : keys.size() - 1;
            final TableSwitchInsnNode table = new TableSwitchInsnNode(min, max, default_case);

            // Load the actual argument onto the operand-stack and then branch.
            method.instructions.add(new VarInsnNode(Opcodes.ILOAD, 1));
            method.instructions.add(table);

            /**
             * Generate each case in the switch-case, except the default-case.
             */
            for (String element : keys)
            {
                /**
                 * Get the static-type of the element.
                 */
                final IVariableType element_type = typeOfElement(element);

                /**
                 * Mark the entry-point of the switch-case.
                 */
                final LabelNode label = new LabelNode();
                table.labels.add(label);
                method.instructions.add(label);

                /**
                 * Obtain a modifiable version of the tuple.
                 * If the tuple is modifiable, then this will be the tuple itself.
                 * Otherwise, this will be a copy of the tuple.
                 */
                loadModifiableVariant(method.instructions);

                /**
                 * Duplicate the object-reference, because we will need an extra one later.
                 */
                method.instructions.add(new InsnNode(Opcodes.DUP));

                /**
                 * Push the value onto an argument-stack and then pop it right back off.
                 * This will convert the value to the appropriate type.
                 *
                 * The argument is an object and is the second user-defined parameter.
                 * The first parameter is the index, which is an int.
                 */
                // Load two references to the the argument-stack.
                Utils.loadArgumentStack(method.instructions);
                method.instructions.add(new InsnNode(Opcodes.DUP));
                // Push
                method.instructions.add(new VarInsnNode(Opcodes.ALOAD, 2));
                Utils.pushArgument(program, method.instructions, program.typesystem.utils.OBJECT);
                // Pop - Peek Value
                Utils.peekArgument(program, method.instructions, element_type);
                // Pop - Clear the Argument Stack
                Utils.loadArgumentStack(method.instructions);
                method.instructions.add(new InsnNode(Opcodes.POP));

                /**
                 * Assign the value to the element.
                 */
                method.instructions.add(new FieldInsnNode(Opcodes.PUTFIELD,
                                                          Utils.internalName(type),
                                                          nameOfField(element),
                                                          element_type.getDescriptor()));

                /**
                 * Return the modified object.
                 * This is why the object-reference was duplicated previously.
                 */
                method.instructions.add(new InsnNode(Opcodes.ARETURN));
            }

            /**
             * The default-case executes the same code that executes when the tuple is empty.
             */
            method.instructions.add(default_case);
        }

        /**
         * Throw an exception, if the element does not exist.
         */
        method.instructions.add(new VarInsnNode(Opcodes.ILOAD, 1));
        method.instructions.add(new LdcInsnNode(keys.size()));
        Utils.invoke(method.instructions,
                     Opcodes.INVOKESTATIC,
                     Helpers.class,
                     void.class,
                     "throwIndexOutOfBoundsException",
                     int.class,
                     int.class);

        /**
         * Add a return instruction, even though it will never actually execute.
         * This is needed, due to bytecode verification.
         */
        method.instructions.add(new InsnNode(Opcodes.ACONST_NULL));
        method.instructions.add(new InsnNode(Opcodes.ARETURN));

        return method;
    }

    /**
     * This method generates the bytecode representation of the get(int) method.
     *
     * @return the generated method.
     */
    private MethodNode generateMethodGet()
    {
        final MethodNode method = Utils.bytecodeOf(module,
                                                   TypeSystemUtils.find(type.getAllVisibleMethods(),
                                                                        "get",
                                                                        "(I)Ljava/lang/Object;"));

        // Remove the abstract modifier.
        method.access = method.access & (~Opcodes.ACC_ABSTRACT);

        final LabelNode default_case = new LabelNode();

        /**
         * Essentially, this will generate a switch-statement.
         * The switch-case branches based on the index that is given as an parameter to the method.
         * If a case is executed, it will do the following things.
         * First, the value of the element is loaded onto the operand-stack.
         * Second, the value will be auto-boxed, if the element is a primitive-type.
         * Third, the value will be returned from the method.
         * The default-case will be generated at the bottom of this method,.
         * because no jump-table is needed, if the tuple is empty.
         */
        if (!keys.isEmpty())
        {
            /**
             * Generate the jump-table itself.
             */
            final int min = 0;
            final int max = keys.isEmpty() ? 0 : keys.size() - 1;
            final TableSwitchInsnNode table = new TableSwitchInsnNode(min, max, default_case);

            // Load the actual argument onto the operand-stack and then branch.
            method.instructions.add(new VarInsnNode(Opcodes.ILOAD, 1));
            method.instructions.add(table);

            /**
             * Generate each case in the switch-case, except the default-case.
             */
            for (String element : keys)
            {
                /**
                 * Get the static-type of the element.
                 */
                final IVariableType element_type = typeOfElement(element);


                /**
                 * Mark the entry-point of the switch-case.
                 */
                final LabelNode label = new LabelNode();
                table.labels.add(label);
                method.instructions.add(label);

                /**
                 * Load the value of the element onto the operand-stack.
                 * This requires retrieving the value from the field it is stored in.
                 */
                method.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0)); // Load 'this'
                method.instructions.add(new FieldInsnNode(Opcodes.GETFIELD,
                                                          Utils.internalName(type),
                                                          nameOfField(element),
                                                          element_type.getDescriptor()));

                /**
                 * Box the value, if needed, so it can be added to the list.
                 */
                program.typesystem.utils.autoboxToObject(method.instructions, element_type);

                /**
                 * Return the result.
                 */
                method.instructions.add(new InsnNode(Opcodes.ARETURN));
            }

            /**
             * The default-case executes the same code that executes when the tuple is empty.
             */
            method.instructions.add(default_case);
        }

        /**
         * Throw an exception, if the element does not exist.
         */
        method.instructions.add(new VarInsnNode(Opcodes.ILOAD, 1));
        method.instructions.add(new LdcInsnNode(keys.size()));
        Utils.invoke(method.instructions,
                     Opcodes.INVOKESTATIC,
                     Helpers.class,
                     void.class,
                     "throwIndexOutOfBoundsException",
                     int.class,
                     int.class);

        /**
         * Add a return instruction, even though it will never actually execute.
         * This is needed, due to bytecode verification.
         */
        method.instructions.add(new InsnNode(Opcodes.ACONST_NULL));
        method.instructions.add(new InsnNode(Opcodes.ARETURN));

        return method;
    }

    /**
     * This method generates the bytecode representation of the isMutable() method.
     *
     * @return the generated method.
     */
    private MethodNode generateMethodIsMutable()
    {
        final MethodNode method = Utils.bytecodeOf(module,
                                                   TypeSystemUtils.find(type.getAllVisibleMethods(),
                                                                        "isMutable",
                                                                        "()Z"));

        // Remove the abstract modifier.
        method.access = method.access & (~Opcodes.ACC_ABSTRACT);

        /**
         * Get the field that indicates whether the tuple is mutable.
         */
        method.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0)); // Load 'this'
        method.instructions.add(new FieldInsnNode(Opcodes.GETFIELD,
                                                  Utils.internalName(type),
                                                  "MUTABLE",
                                                  "Z"));

        /**
         * Return the result.
         */
        method.instructions.add(new InsnNode(Opcodes.IRETURN));

        return method;
    }

    /**
     * This method generates the bytecode representation of the immutable() method.
     *
     * @return the generated method.
     */
    private MethodNode generateMethodImmutable()
    {
        // Generated Bytecode:
        //
        // NEW *type*              - Create a new uninitialized object of this record-type.
        // DUP                     - Duplicate the object-reference to the uninitialized object.
        //
        // ALOAD this              - Load 'this' onto the operand-stack.
        // GETFIELD this.field[0]  - Load value in field #0 onto the operand-stack.
        //
        // ALOAD this              - Load 'this' onto the operand-stack.
        // GETFIELD this.field[1]  - Load value in field #1 onto the operand-stack.
        //
        // ALOAD this              - Load 'this' onto the operand-stack.
        // GETFIELD this.field[2]  - Load value in field #2 onto the operand-stack.
        //
        // ...
        //
        // ALOAD this              - Load 'this' onto the operand-stack.
        // GETFIELD this.field[n]  - Load value in field #n onto the operand-stack.
        //
        // INVOKESPECIAL <init>    - Invoke the only ctor that the uninitialized object has.
        //                         - The constructor takes the previous field values as arguments.
        //                         - Essentially, we are simply copying the 'this' object.
        //                         - However, the ctor does *not* copy the special method bindings.
        //                         - So, we still need to do that.
        //
        // NOTE                    - The uninitialized object is now initialized.
        //                         - A reference to that object is on the top of the operand-stack.
        //
        // NOTE                    - Now, we need to manually copy the special-method bindings.
        //                         - The bindings are stored in a special field named BINDINGS.
        //
        // DUP                     - Duplicate the aforesaid object-reference.
        // ALOAD this              - Load the 'this' reference onto the operand-stack.
        // GETFIELD this.BINDINGS  - Get the value in the 'this' object in the BINDINGS field.
        // PUTFIELD copy.BINDINGS  - Set the value of the BINDINGS field in the 'copy' object.
        //
        // ARETURN copy            - Return the copy object.
        //
        ////////////////////////////////////////////////////////////////////////////////////////////


        final MethodNode method = Utils.bytecodeOf(module,
                                                   TypeSystemUtils.find(type.getAllVisibleMethods(),
                                                                        "immutable",
                                                                        "()" + program.typesystem.utils.RECORD.getDescriptor()));

        // Remove the abstract modifier.
        method.access = method.access & (~Opcodes.ACC_ABSTRACT);

        /**
         * Create a new uninitialized instance of the tuple.
         */
        method.instructions.add(new TypeInsnNode(Opcodes.NEW, Utils.internalName(type)));

        /**
         * Duplicate the object-reference.
         */
        method.instructions.add(new InsnNode(Opcodes.DUP));

        /**
         * Load the value of each element onto the operand-stack.
         */
        for (String element : keys)
        {
            /**
             * Get the static-type of the element.
             */
            final IVariableType element_type = typeOfElement(element);

            /**
             * Load the value of the element onto the operand-stack.
             * This requires retrieving the value from the field it is stored in.
             */
            method.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0)); // Load 'this'
            method.instructions.add(new FieldInsnNode(Opcodes.GETFIELD,
                                                      Utils.internalName(type),
                                                      nameOfField(element),
                                                      element_type.getDescriptor()));
        }

        /**
         * Invoke the constructor in order to create a copy of this object.
         */
        method.instructions.add(new MethodInsnNode(Opcodes.INVOKESPECIAL,
                                                   Utils.internalName(type),
                                                   "<init>",
                                                   ctor.getDescriptor()));

        /**
         * Copy the special-methods field manually.
         *
         * Be sure to leave a reference to the copy of the record on the operand-stack.
         */
        method.instructions.add(new InsnNode(Opcodes.DUP));
        method.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0)); // Load 'this'
        method.instructions.add(new FieldInsnNode(Opcodes.GETFIELD,
                                                  Utils.internalName(type),
                                                  "BINDINGS",
                                                  program.typesystem.utils.SPECIAL_METHODS.getDescriptor()));
        method.instructions.add(new FieldInsnNode(Opcodes.PUTFIELD,
                                                  Utils.internalName(type),
                                                  "BINDINGS",
                                                  program.typesystem.utils.SPECIAL_METHODS.getDescriptor()));

        /**
         * Return the copy.
         */
        method.instructions.add(new InsnNode(Opcodes.ARETURN));


        return method;
    }

    /**
     * This method generates the bytecode representation of the mutable() method.
     *
     * @return the generated method.
     */
    private MethodNode generateMethodMutable()
    {
        final MethodNode method = Utils.bytecodeOf(module,
                                                   TypeSystemUtils.find(type.getAllVisibleMethods(),
                                                                        "mutable",
                                                                        "()" + program.typesystem.utils.RECORD.getDescriptor()));

        // Remove the abstract modifier.
        method.access = method.access & (~Opcodes.ACC_ABSTRACT);

        /**
         * Create an immutable copy of the tuple.
         */
        method.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0)); // Load 'this'
        method.instructions.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL,
                                                   Utils.internalName(type),
                                                   "immutable",
                                                   "()" + type.getDescriptor()));

        /**
         * Duplicate the object reference.
         */
        method.instructions.add(new InsnNode(Opcodes.DUP));

        /**
         * Set the field that indicates the tuple is mutable to indicate
         * that it is in fact mutable.
         */
        method.instructions.add(new LdcInsnNode(true));
        method.instructions.add(new FieldInsnNode(Opcodes.PUTFIELD,
                                                  Utils.internalName(type),
                                                  "MUTABLE",
                                                  "Z"));

        /**
         * Return the copy.
         */
        method.instructions.add(new InsnNode(Opcodes.ARETURN));


        return method;
    }

    /**
     * This method generates the bytecode representation of the bindings() method.
     *
     * @return the generated method.
     */
    public MethodNode generateMethodBindings()
    {
        /**
         * Get partial bytecode representation of the bindings() method.
         */
        final MethodNode method = Utils.bytecodeOf(module,
                                                   TypeSystemUtils.find(type.getAllVisibleMethods(),
                                                                        "bindings",
                                                                        "()" + program.typesystem.utils.SPECIAL_METHODS));

        /**
         * Remove the abstract modifier.
         */
        method.access = method.access & (~Opcodes.ACC_ABSTRACT);

        /**
         * Load the 'this' instance onto the operand-stack.
         */
        method.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0));

        /**
         * Load the object that stores the method bindings onto the operand-stack.
         *
         * The bindings are stored in a special field.
         */
        method.instructions.add(new FieldInsnNode(Opcodes.GETFIELD,
                                                  Utils.internalName(type),
                                                  "BINDINGS",
                                                  program.typesystem.utils.SPECIAL_METHODS.getDescriptor()));

        /**
         * Return the object that stores the method bindings.
         */
        method.instructions.add(new InsnNode(Opcodes.ARETURN));

        return method;
    }

    /**
     * This method generates the bytecode representation of the bind(SpecialMethods) method.
     *
     * @return the generated method.
     */
    public MethodNode generateMethodBind()
    {
        final IInterfaceType RECORD = program.typesystem.utils.RECORD;

        /**
         * Get partial bytecode representation of the bindings() method.
         */
        final MethodNode method = Utils.bytecodeOf(module,
                                                   TypeSystemUtils.find(type.getAllVisibleMethods(),
                                                                        "bind",
                                                                        "(" + program.typesystem.utils.SPECIAL_METHODS + ")" + RECORD.getDescriptor()));

        /**
         * Remove the abstract modifier.
         */
        method.access = method.access & (~Opcodes.ACC_ABSTRACT);

        /**
         * Load the a modifiable instance of the record onto the operand-stack.
         * If the record object is mutable, then it will be pushed onto the operand-stack.
         * If the record object is immutable, then a copy will be pushed onto the operand-stack.
         */
        loadModifiableVariant(method.instructions);

        /**
         * Duplicate the reference to the modifiable instance.
         * One instance will be needed in an upcoming operation.
         * Another instance will be returned by the method.
         */
        method.instructions.add(new InsnNode(Opcodes.DUP));

        /**
         * Load the new SpecialMethods object onto the operand-stack.
         */
        method.instructions.add(new VarInsnNode(Opcodes.ALOAD, 1));

        /**
         * The reference cannot be null.
         */
        method.instructions.add(new InsnNode(Opcodes.DUP));
        method.instructions.add(new MethodInsnNode(Opcodes.INVOKESTATIC,
                                                   Utils.internalName(program.typesystem.utils.HELPERS),
                                                   "requireNonNull",
                                                   "(Ljava/lang/Object;)V"));

        /**
         * Set the field that stores the special-method bindings.
         *
         * This will pop one of the references to the record object off of the operand-stack.
         */
        method.instructions.add(new FieldInsnNode(Opcodes.PUTFIELD,
                                                  Utils.internalName(type),
                                                  "BINDINGS",
                                                  program.typesystem.utils.SPECIAL_METHODS.getDescriptor()));

        /**
         * Return the record object itself.
         */
        method.instructions.add(new InsnNode(Opcodes.ARETURN));

        return method;
    }

    /**
     * This method generates the bytecode representation of the keys() method.
     *
     * @return the generated method.
     */
    private MethodNode generateMethodKeys()
    {
        final MethodNode method = Utils.bytecodeOf(module,
                                                   TypeSystemUtils.find(type.getAllVisibleMethods(),
                                                                        "keys",
                                                                        "()Ljava/util/List;"));

        // Remove the abstract modifier.
        method.access = method.access & (~Opcodes.ACC_ABSTRACT);

        /**
         * The method simply retrieves the value in a static field and returns it.
         */
        method.instructions.add(new FieldInsnNode(Opcodes.GETSTATIC,
                                                  Utils.internalName(type),
                                                  "KEYS",
                                                  "Ljava/util/List;"));

        method.instructions.add(new InsnNode(Opcodes.ARETURN));

        return method;
    }

    /**
     * This method generates the bytecode representation of the types() method.
     *
     * @return the generated method.
     */
    private MethodNode generateMethodTypes()
    {
        final MethodNode method = Utils.bytecodeOf(module,
                                                   TypeSystemUtils.find(type.getAllVisibleMethods(),
                                                                        "types",
                                                                        "()Ljava/util/List;"));

        // Remove the abstract modifier.
        method.access = method.access & (~Opcodes.ACC_ABSTRACT);

        /**
         * The method simply retrieves the value in a static field and returns it.
         */
        method.instructions.add(new FieldInsnNode(Opcodes.GETSTATIC,
                                                  Utils.internalName(type),
                                                  "TYPES",
                                                  "Ljava/util/List;"));

        method.instructions.add(new InsnNode(Opcodes.ARETURN));

        return method;
    }

    /**
     * This method generates the bytecode representation of the values() method.
     *
     * @return the generated method.
     */
    private MethodNode generateMethodValues()
    {
        final MethodNode method = Utils.bytecodeOf(module,
                                                   TypeSystemUtils.find(type.getAllVisibleMethods(),
                                                                        "values",
                                                                        "()Ljava/util/List;"));

        // Remove the abstract modifier.
        method.access = method.access & (~Opcodes.ACC_ABSTRACT);

        /**
         * This object will generate bytecode that a list containing the values in the record.
         */
        final CollectionCompiler<String> cmp = new CollectionCompiler<String>()
        {
            @Override
            public void compile(final String element)
            {
                /**
                 * Get the static-type of the element.
                 */
                final IVariableType element_type = typeOfElement(element);


                // Load 'this' onto the operand-stack.
                code().add(new VarInsnNode(Opcodes.ALOAD, 0));

                // Get the value stored in the element's field and push it onto the operand stack.
                // This pops the 'this' instance previously pushed.
                code().add(new FieldInsnNode(Opcodes.GETFIELD,
                                             Utils.internalName(type),
                                             nameOfField(element),
                                             element_type.getDescriptor()));

                // Autobox the value, if the value is primitive.
                program.typesystem.utils.autoboxToObject(code(), element_type);
            }

            @Override
            public InsnList code()
            {
                return method.instructions;
            }
        };

        /**
         * Create a list of the values in the record and push it onto the operand-stack.
         */
        cmp.compile(keys);

        /**
         * Make the list immutable.
         */
        Utils.makeListImmutable(method.instructions);

        /**
         * Return the list of values.
         */
        method.instructions.add(new InsnNode(Opcodes.ARETURN));

        return method;
    }

    /**
     * This method generates the bytecode representation of a setter method.
     *
     * <p>
     * A setter method must obtain a modifiable variant of the tuple.
     * In other words, the setter must copy the tuple, if it is immutable.
     * Then the setter must set the field in the tuple to the new value.
     * Finally, the setter must return the modified tuple.
     * Take note, the returned tuple may not be the original tuple.
     * </p>
     *
     * @param element is an object that describes the element.
     * @return the generated method.
     */
    private MethodNode generateSetter(final SetterMethod element)
    {
        /**
         * Get the static-type of the element.
         */
        final IVariableType element_type = typeOfElement(element.name);


        final MethodNode method = Utils.bytecodeOf(module,
                                                   TypeSystemUtils.find(type.getMethods(),
                                                                        element.name,
                                                                        "(" + element_type.getDescriptor() + ")" + type.getDescriptor()));

        // Remove the abstract modifier.
        method.access = method.access & (~Opcodes.ACC_ABSTRACT);

        /**
         * Get a variant of the tuple which can be modified.
         * If the original tuple is mutable, then this will simply be the original tuple.
         * If the original tuple is immutable, then this will be a copy of the original.
         */
        loadModifiableVariant(method.instructions);

        /**
         * Duplicate the object-reference that refers to the tuple.
         */
        method.instructions.add(new InsnNode(Opcodes.DUP));


        /**
         * Load the value, which will be assigned to the field, onto the operand-stack.
         */
        method.instructions.add(Utils.selectLoadVarInsn(element_type, 1));

        /**
         * Set the field that stores the value of the element.
         */
        method.instructions.add(new FieldInsnNode(Opcodes.PUTFIELD,
                                                  Utils.internalName(type),
                                                  nameOfField(element.name),
                                                  element_type.getDescriptor()));

        /**
         * Return the result.
         */
        method.instructions.add(new InsnNode(Opcodes.ARETURN));

        return method;
    }

    /**
     * This method generates bytecode that creates a modifiable variant of the tuple.
     *
     * <p>
     * This method expects that the tuple object is the topmost element of the operand-stack.
     * </p>
     *
     * <p>
     * If the tuple is immutable, then it will be copied.
     * If the tuple is mutable, then the tuple will be unchanged.
     * </p>
     *
     * @param code is the bytecode being generated.
     */
    private void loadModifiableVariant(final InsnList code)
    {
        // Generated Bytecode:
        //
        // ALOAD 0
        // GETFIELD mutable
        // IF_FALSE @ELSE
        //
        // ALOAD 0
        //
        // GOTO @END
        // @ELSE
        //
        // ALOAD 0
        // INVOKEVIRTUAL this.immutable()
        //
        // @END
        //
        ///////////////////////////////////////////

        final LabelNode ELSE = new LabelNode();

        final LabelNode END = new LabelNode();

        code.add(new VarInsnNode(Opcodes.ALOAD, 0)); // Load 'this'
        code.add(new FieldInsnNode(Opcodes.GETFIELD,
                                   Utils.internalName(type),
                                   "MUTABLE",
                                   "Z"));

        code.add(new JumpInsnNode(Utils.IF_FALSE, ELSE));

        code.add(new VarInsnNode(Opcodes.ALOAD, 0)); // Load 'this'

        code.add(new JumpInsnNode(Opcodes.GOTO, END));
        code.add(ELSE);

        code.add(new VarInsnNode(Opcodes.ALOAD, 0)); // Load 'this'
        code.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL,
                                    Utils.internalName(type),
                                    "immutable",
                                    "()" + type.getDescriptor()));

        code.add(END);
    }

    /**
     * This method generates the bytecode representation of a getter method.
     *
     * <p>
     * A getter method simply read the field that stores the element and then returns the result.
     * </p>
     *
     * @param element is an object that describes the element.
     * @return the generated method.
     */
    private MethodNode generateGetter(final GetterMethod element)
    {
        /**
         * Get the static-type of the element.
         */
        final IVariableType element_type = typeOfElement(element.name);


        final MethodNode method = Utils.bytecodeOf(module,
                                                   TypeSystemUtils.find(type.getMethods(),
                                                                        element.name,
                                                                        "()" + element_type.getDescriptor()));

        // Remove the abstract modifier.
        method.access = method.access & (~Opcodes.ACC_ABSTRACT);

        /**
         * The method simply retrieves the value in a field and returns it.
         */
        method.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0)); // Load 'this'
        method.instructions.add(new FieldInsnNode(Opcodes.GETFIELD,
                                                  Utils.internalName(type),
                                                  nameOfField(element.name),
                                                  element_type.getDescriptor()));

        /**
         * Return the result.
         */
        method.instructions.add(Utils.selectReturnInsn(element_type));

        return method;
    }

    /**
     * This method retrieves the static-type of an element given the element's name.
     *
     * @param key is the name of the element.
     * @return the most-specific static-type of the element.
     */
    private IVariableType typeOfElement(final String key)
    {
        final IVariableType result = analyzer.elements.get(key).type();

        return result;
    }
}

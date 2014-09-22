package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ClassFile;
import autumn.lang.compiler.ast.nodes.FormalParameter;
import autumn.lang.compiler.ast.nodes.TupleDefinition;
import autumn.lang.internals.Helpers;
import com.google.common.collect.Lists;
import high.mackenzie.autumn.lang.compiler.typesystem.CustomConstructor;
import high.mackenzie.autumn.lang.compiler.typesystem.CustomDeclaredType;
import high.mackenzie.autumn.lang.compiler.typesystem.CustomFormalParameter;
import high.mackenzie.autumn.lang.compiler.typesystem.CustomMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IConstructor;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IFormalParameter;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IInterfaceType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;
import high.mackenzie.autumn.lang.compiler.utils.BridgeMethod;
import high.mackenzie.autumn.lang.compiler.utils.TypeSystemUtils;
import high.mackenzie.autumn.lang.compiler.utils.Utils;
import java.util.Collections;
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
 * An instance of this class controls the compilation of a tuple-definition.
 *
 * @author Mackenzie High
 */
public final class TupleCompiler
        implements ICompiler
{
    /**
     * An instance of this class represents an element in the tuple.
     */
    private static class Element
    {
        /**
         * This is the index of the element within the tuple's definition.
         */
        public final int index;

        /**
         * This is the name of the element.
         */
        public final String name;

        /**
         * This is the static-type of the element as declared in the tuple's definition.
         */
        public final IVariableType type;

        /**
         * Sole Constructor.
         *
         * @param index is the index of the element in the tuple.
         * @param name is the name of the element.
         * @param type is the type of value stored in the element.
         */
        public Element(final int index,
                       final String name,
                       final IVariableType type)
        {
            assert index >= 0;
            assert name != null;
            assert type != null;

            this.index = index;
            this.name = name;
            this.type = type;
        }
    }

    /**
     * Essentially, this is the program that is being compiled.
     */
    public final ProgramCompiler program;

    /**
     * Essentially, this is the enclosing module that is also being compiled.
     */
    public final ModuleCompiler module;

    /**
     * This is the Abstract-Syntax-Tree representation of the tuple's definition.
     */
    public final TupleDefinition node;

    /**
     * This will be the type-system representation of the tuple's definition.
     *
     * This field will be initialized during the type-declaration compiler pass.
     */
    public CustomDeclaredType type;

    /**
     * This is the type of the tuple's only constructor.
     */
    private CustomConstructor ctor;

    /**
     * These objects represent the elements of the tuple.
     */
    private final List<Element> elements = Lists.newLinkedList();

    /**
     * These objects will simplify the creation of bridge methods.
     *
     * The elements of this list will be created during the type-initialization compiler pass.
     *
     * These objects will be used to generate the bytecode representations of the bridge methods.
     */
    private final List<BridgeMethod> bridges = Lists.newLinkedList();

    /**
     * Sole Constructor.
     *
     * @param module is the module that contains the tuple being compiled.
     * @param node is the AST node that represents the tuple being compiled.
     */
    public TupleCompiler(final ModuleCompiler module,
                         final TupleDefinition node)
    {
        assert module != null;
        assert node != null;
        assert module.tuples.contains(node);

        this.program = module.program;
        this.module = module;
        this.node = node;
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
        final String tuple_internal_name = Utils.internalName(type);

        final String tuple_source_name = Utils.sourceName(type);

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

        // Create the field that stores the names of the elements.
        fields.add(new FieldNode(Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC + Opcodes.ACC_FINAL,
                                 "KEYS",
                                 "Ljava/util/List;",
                                 null,
                                 null));

        // Create the field that stores the types of the elements.
        fields.add(new FieldNode(Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC + Opcodes.ACC_FINAL,
                                 "TYPES",
                                 "Ljava/util/Map;",
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
            clazz.name = tuple_internal_name;
            clazz.superName = Utils.internalName(type.getSuperclass());
            clazz.interfaces = Lists.newLinkedList();
            clazz.fields = fields;
            clazz.methods = Lists.newLinkedList();
            clazz.sourceFile = String.valueOf(node.getLocation().getFile());

            // Add some special methods to the tuple.

            clazz.methods.add(this.generateStaticCtor());

            clazz.methods.add(this.generateCtor());

            clazz.methods.add(this.generateMethodInstance());

            clazz.methods.add(this.generateMethodKeys());

            clazz.methods.add(this.generateMethodTypes());

            clazz.methods.add(this.generateMethodIsMutable());

            clazz.methods.add(this.generateMethodMutable());

            clazz.methods.add(this.generateMethodImmutable());

            clazz.methods.add(this.generateMethodGet());

            clazz.methods.add(this.generateMethodSet());

            clazz.methods.addAll(this.generateBridgeMethods());


            for (Element element : elements)
            {
                clazz.methods.add(this.generateSetter(element));
                clazz.methods.add(this.generateGetter(element));
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
        final ClassFile file = new ClassFile(tuple_source_name, bytecode);

        return file;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeDeclaration()
    {
        /**
         * Determine the descriptor of the tuple.
         */
        final String namespace = module.type.getNamespace().replace('.', '/');
        final String name = node.getName().getName();
        final String descriptor = "L" + namespace + '/' + name + ';';

        /**
         * Ensure that the type was not already declared elsewhere.
         */
        program.checker.requireNonDuplicateType(node.getName(), descriptor);

        /**
         * Declare the tuple.
         */
        this.type = program.typesystem.typefactory().newClassType(descriptor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeInitialization()
    {
        /**
         * Set the type's modifier flags to public and final.
         */
        type.setModifiers(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL);

        /**
         * Set the superclass of the tuple's type.
         */
        type.setSuperclass(program.typesystem.utils.ABSTRACT_TUPLE);

        /**
         * Add the type of the constructor defined in the tuple to the tuple's type.
         */
        type.setConstructors(Lists.newArrayList(typeofCtor()));

        /**
         * Add the types of the methods defined in the tuple to the tuple's type.
         */
        final List<IMethod> methods = Lists.newLinkedList();

        /**
         * Add the type of the static instance() method.
         */
        methods.add(this.typeofInstance());

        /**
         * Create the getter and setter methods for each element in the tuple.
         */
        for (FormalParameter formal : node.getElements().getParameters())
        {
            final String element_name = formal.getVariable().getName();

            final IVariableType element_type = module.imports.resolveVariableType(formal.getType());

            final Element element = new Element(elements.size(), element_name, element_type);

            // Hold onto the type of the element for later use in other methods.
            elements.add(element);

            /**
             * Each element has an associated getter method and a setter method.
             */
            methods.add(typeofSetter(element));
            methods.add(typeofGetter(element));
        }

        /**
         * Now we have generated the types of the methods in the tuple.
         * However, we still need to add the generated method types to the tuple's type.
         */
        type.setMethods(methods);

        /**
         * Now we need to generate bridge methods.
         */
        typeofBridgeMethods();
    }

    /**
     * This method creates the type-system representation of the only constructor in the tuple.
     *
     * @return the aforedescribed constructor.
     */
    private IConstructor typeofCtor()
    {
        ctor = new CustomConstructor(program.typesystem.typefactory());

        ctor.setAnnotations(new LinkedList());
        ctor.setModifiers(Opcodes.ACC_PUBLIC);
        ctor.setOwner(type);
        ctor.setParameters(module.formals(node.getElements().getParameters()));
        ctor.setReturnType(program.typesystem.utils.VOID);
        ctor.setThrowsClause(new LinkedList());

        return ctor;
    }

    /**
     * This method creates the type-system representation of the method that sets a tuple element.
     *
     * @param element is the element that the setter sets.
     * @return the aforedescribed method.
     */
    private IMethod typeofSetter(final Element element)
    {
        final CustomMethod method = new CustomMethod(program.typesystem.typefactory(), false);

        final CustomFormalParameter param = new CustomFormalParameter();
        param.setType(element.type);

        method.setAnnotations(new LinkedList());
        method.setModifiers(Opcodes.ACC_PUBLIC);
        method.setName(element.name);
        method.setOwner(type);
        method.setParameters(Collections.<IFormalParameter>singletonList(param));
        method.setReturnType(type);
        method.setThrowsClause(new LinkedList());

        return method;
    }

    /**
     * This method creates the type-system representation of the method that gets a tuple element.
     *
     * @param element is the element that the getter gets.
     * @return the aforedescribed method.
     */
    private IMethod typeofGetter(final Element element)
    {
        final CustomMethod method = new CustomMethod(program.typesystem.typefactory(), false);

        method.setAnnotations(new LinkedList());
        method.setModifiers(Opcodes.ACC_PUBLIC);
        method.setName(element.name);
        method.setOwner(type);
        method.setParameters(new LinkedList());
        method.setReturnType(element.type);
        method.setThrowsClause(new LinkedList());

        return method;
    }

    /**
     * This method creates the type-system representation of the instance() method.
     *
     * @return the aforedescribed method.
     */
    private IMethod typeofInstance()
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
     * This method creates the type-system representations of the bridge methods.
     */
    private void typeofBridgeMethods()
    {
        final IInterfaceType RECORD = program.typesystem.utils.RECORD;

        final IInterfaceType TUPLE = program.typesystem.utils.TUPLE;

        /**
         * Method: bind(SpecialMethods)
         */
        bridges.add(new BridgeMethod(type,
                                     type,
                                     TypeSystemUtils.find(RECORD.getAllVisibleMethods(),
                                                          "bind")));

        /**
         * Method: set(String, Object)
         */
        bridges.add(new BridgeMethod(type,
                                     type,
                                     TypeSystemUtils.find(type.getAllVisibleMethods(),
                                                          "set",
                                                          "(Ljava/lang/String;Ljava/lang/Object;)" + RECORD.getDescriptor())));

        /**
         * Method: set(int, Object)
         */
        bridges.add(new BridgeMethod(type,
                                     type,
                                     TypeSystemUtils.find(type.getAllVisibleMethods(),
                                                          "set",
                                                          "(ILjava/lang/Object;)" + TUPLE.getDescriptor())));

        /**
         * Method: keys()
         */
        bridges.add(new BridgeMethod(type,
                                     program.typesystem.utils.LIST,
                                     TypeSystemUtils.find(type.getAllVisibleMethods(),
                                                          "keys",
                                                          "()Ljava/util/Collection;")));

        /**
         * Method: mutable()
         */
        bridges.add(new BridgeMethod(type,
                                     type,
                                     TypeSystemUtils.find(type.getAllVisibleMethods(),
                                                          "mutable",
                                                          "()" + RECORD.getDescriptor())));

        /**
         * Method: immutable()
         */
        bridges.add(new BridgeMethod(type,
                                     type,
                                     TypeSystemUtils.find(RECORD.getAllVisibleMethods(),
                                                          "immutable",
                                                          "()" + RECORD.getDescriptor())));

        /**
         * Method: copy()
         */
        bridges.add(new BridgeMethod(type,
                                     type,
                                     TypeSystemUtils.find(RECORD.getAllVisibleMethods(),
                                                          "copy",
                                                          "()" + RECORD.getDescriptor())));

        /**
         * Method: clear()
         */
        bridges.add(new BridgeMethod(type,
                                     type,
                                     TypeSystemUtils.find(TUPLE.getMethods(),
                                                          "clear",
                                                          "()" + TUPLE.getDescriptor())));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeStructureChecking()
    {
        // TODO:
        // 1. No duplicate elements.
        // 2. Element types must be non-void and non-null.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeUsageChecking()
    {
        // Pass
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
        for (FormalParameter element : node.getElements().getParameters())
        {
            // The field is private, because element's are accessed via getters and setters.
            final int access = Opcodes.ACC_PRIVATE;

            // The name of the field will be given a prefix.
            // This prevents name collisions with special fields.
            final String name = nameOfField(element.getVariable().getName());

            // The descriptor describes the static-type of the element.
            final String desc = module.imports.resolveVariableType(element.getType()).getDescriptor();

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
        // . return
        //
        //////////////////////////////////////////////////////////

        final MethodNode method = Utils.bytecodeOf(module, ctor);

        /**
         * Generate the bytecode that invokes super().
         */
        method.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0)); // Load 'this'
        method.instructions.add(new MethodInsnNode(Opcodes.INVOKESPECIAL,
                                                   Utils.internalName(program.typesystem.utils.ABSTRACT_TUPLE),
                                                   "<init>",
                                                   "()V"));


        // Skip the first memory address (i.e. address zero),
        // because that is where 'this' is automatically stored.
        int address = 1;

        /**
         * Transfer each actual parameter into the field that stores the related element.
         */
        for (Element element : elements)
        {
            final String owner = Utils.internalName(type);
            final String name = nameOfField(element.name);
            final String desc = element.type.getDescriptor();

            method.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0)); // Load 'this'
            method.instructions.add(Utils.selectLoadVarInsn(element.type, address));
            method.instructions.add(new FieldInsnNode(Opcodes.PUTFIELD, owner, name, desc));

            address += Utils.sizeof(element.type);
        }

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
         * Initialize the TYPES field.
         */
        initMapOfTypes(clinit.instructions);

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
     * This method generates bytecode that initializes the field that stores the map of types.
     *
     * @param code is the bytecode being generated.
     */
    private void initMapOfTypes(final InsnList code)
    {
        /**
         * Create a list object containing the name of each element (i.e. keys).
         */
        initListOfKeys(code);

        /**
         * Push the list of keys onto the operand-stack.
         */
        code.add(new FieldInsnNode(Opcodes.GETSTATIC,
                                   Utils.internalName(type),
                                   "KEYS",
                                   "Ljava/util/List;"));

        /**
         * Create a list object containing the type of each element (i.e. values ).
         */
        createListOfTypes(code);

        /**
         * Create a map object from the two lists.
         */
        Utils.createImmutableMap(code);

        /**
         * Place the map object into the field that will store it for later use.
         */
        code.add(new FieldInsnNode(Opcodes.PUTSTATIC,
                                   Utils.internalName(type),
                                   "TYPES",
                                   "Ljava/util/Map;"));
    }

    /**
     * This method generates bytecode that initializes the field that stores the list of keys.
     *
     * @param code is the bytecode being generated.
     */
    private void initListOfKeys(final InsnList code)
    {
        final CollectionCompiler<Element> cmp = new CollectionCompiler<Element>()
        {
            @Override
            public void compile(final Element element)
            {
                code().add(new LdcInsnNode(element.name));
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
        cmp.compile(elements);

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
     * This method generates bytecode that creates a list containing the types.
     *
     * @param code is the bytecode being generated.
     */
    private void createListOfTypes(final InsnList code)
    {
        final CollectionCompiler<Element> cmp = new CollectionCompiler<Element>()
        {
            @Override
            public void compile(final Element element)
            {
                code().add(Utils.ldcClass(element.type));
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
        cmp.compile(elements);
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
        for (Element element : elements)
        {
            /**
             * Load the default value of the element onto the operand-stack.
             */
            code.add(Utils.ldcDefault(element.type));
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
     * This method generates the bytecode representation of the set(int, Object) method.
     *
     * @return the generated method.
     */
    private MethodNode generateMethodSet()
    {
        final MethodNode method = Utils.bytecodeOf(module,
                                                   TypeSystemUtils.find(type.getAllVisibleMethods(),
                                                                        "set",
                                                                        "(ILjava/lang/Object;)" + program.typesystem.utils.TUPLE.getDescriptor()));

        // Remove the abstract modifier.
        method.access = method.access & (~Opcodes.ACC_ABSTRACT);

        final LabelNode default_case = new LabelNode();

        /**
         * Essentially, this will generate a switch-statement.
         * The switch-case branches based on the index that is given as an parameter to the method.
         * The default-case will be generated at the bottom of this method,.
         * because no jump-table is needed, if the tuple is empty.
         */
        if (!elements.isEmpty())
        {
            /**
             * Generate the jump-table itself.
             */
            final int min = 0;
            final int max = elements.isEmpty() ? 0 : elements.size() - 1;
            final TableSwitchInsnNode table = new TableSwitchInsnNode(min, max, default_case);

            // Load the actual argument onto the operand-stack and then branch.
            method.instructions.add(new VarInsnNode(Opcodes.ILOAD, 1));
            method.instructions.add(table);

            /**
             * Generate each case in the switch-case, except the default-case.
             */
            for (Element element : elements)
            {
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
                Utils.peekArgument(program, method.instructions, element.type);
                // Pop - Clear the Argument Stack
                Utils.loadArgumentStack(method.instructions);
                method.instructions.add(new InsnNode(Opcodes.POP));

                /**
                 * Assign the value to the element.
                 */
                method.instructions.add(new FieldInsnNode(Opcodes.PUTFIELD,
                                                          Utils.internalName(type),
                                                          nameOfField(element.name),
                                                          element.type.getDescriptor()));

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
        method.instructions.add(new LdcInsnNode(elements.size()));
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
        if (!elements.isEmpty())
        {
            /**
             * Generate the jump-table itself.
             */
            final int min = 0;
            final int max = elements.isEmpty() ? 0 : elements.size() - 1;
            final TableSwitchInsnNode table = new TableSwitchInsnNode(min, max, default_case);

            // Load the actual argument onto the operand-stack and then branch.
            method.instructions.add(new VarInsnNode(Opcodes.ILOAD, 1));
            method.instructions.add(table);

            /**
             * Generate each case in the switch-case, except the default-case.
             */
            for (Element element : elements)
            {
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
                                                          nameOfField(element.name),
                                                          element.type.getDescriptor()));

                /**
                 * Box the value, if needed, so it can be added to the list.
                 */
                program.typesystem.utils.autoboxToObject(method.instructions, element.type);

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
        method.instructions.add(new LdcInsnNode(elements.size()));
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
        for (Element element : elements)
        {
            /**
             * Load the value of the element onto the operand-stack.
             * This requires retrieving the value from the field it is stored in.
             */
            method.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0)); // Load 'this'
            method.instructions.add(new FieldInsnNode(Opcodes.GETFIELD,
                                                      Utils.internalName(type),
                                                      nameOfField(element.name),
                                                      element.type.getDescriptor()));
        }

        /**
         * Invoke the constructor in order to create a copy of this object.
         */
        method.instructions.add(new MethodInsnNode(Opcodes.INVOKESPECIAL,
                                                   Utils.internalName(type),
                                                   "<init>",
                                                   ctor.getDescriptor()));

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
     * This method generates the bytecode representation of the types() method.
     *
     * @return the generated method.
     */
    private MethodNode generateMethodTypes()
    {
        final MethodNode method = Utils.bytecodeOf(module,
                                                   TypeSystemUtils.find(type.getAllVisibleMethods(),
                                                                        "types",
                                                                        "()Ljava/util/Map;"));

        // Remove the abstract modifier.
        method.access = method.access & (~Opcodes.ACC_ABSTRACT);

        /**
         * The method simply retrieves the value in a static field and returns it.
         */
        method.instructions.add(new FieldInsnNode(Opcodes.GETSTATIC,
                                                  Utils.internalName(type),
                                                  "TYPES",
                                                  "Ljava/util/Map;"));

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
                                                                        "()Ljava/util/Collection;"));

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
     * @return the generated method.
     */
    private MethodNode generateSetter(final Element element)
    {
        final MethodNode method = Utils.bytecodeOf(module,
                                                   TypeSystemUtils.find(type.getMethods(),
                                                                        element.name,
                                                                        "(" + element.type.getDescriptor() + ")" + type.getDescriptor()));

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
        method.instructions.add(Utils.selectLoadVarInsn(element.type, 1));

        /**
         * Set the field that stores the value of the element.
         */
        method.instructions.add(new FieldInsnNode(Opcodes.PUTFIELD,
                                                  Utils.internalName(type),
                                                  nameOfField(element.name),
                                                  element.type.getDescriptor()));

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
     * @return the generated method.
     */
    private MethodNode generateGetter(final Element element)
    {
        final MethodNode method = Utils.bytecodeOf(module,
                                                   TypeSystemUtils.find(type.getMethods(),
                                                                        element.name,
                                                                        "()" + element.type.getDescriptor()));

        // Remove the abstract modifier.
        method.access = method.access & (~Opcodes.ACC_ABSTRACT);

        /**
         * The method simply retrieves the value in a field and returns it.
         */
        method.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0)); // Load 'this'
        method.instructions.add(new FieldInsnNode(Opcodes.GETFIELD,
                                                  Utils.internalName(type),
                                                  nameOfField(element.name),
                                                  element.type.getDescriptor()));

        /**
         * Return the result.
         */
        method.instructions.add(Utils.selectReturnInsn(element.type));

        return method;
    }
}

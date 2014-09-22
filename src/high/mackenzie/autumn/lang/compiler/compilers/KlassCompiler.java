package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ClassFile;
import autumn.lang.compiler.ast.nodes.SourceLocation;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IFormalParameter;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IInterfaceType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IReferenceType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IReturnType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;
import high.mackenzie.autumn.lang.compiler.utils.Design;
import high.mackenzie.autumn.lang.compiler.utils.GetterList;
import high.mackenzie.autumn.lang.compiler.utils.MethodList;
import high.mackenzie.autumn.lang.compiler.utils.SetterList;
import high.mackenzie.autumn.lang.compiler.utils.Utils;
import java.util.List;
import java.util.Map.Entry;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

/**
 * An instance of this class generates the bytecode representation of a class
 * that provides a prototypical implementation of an design-type.
 *
 * @author Mackenzie High
 */
public final class KlassCompiler
{
    public final ProgramCompiler program;

    public final ModuleCompiler module;

    public final IInterfaceType type;

    public final SourceLocation location;

    public final Design info;

    public final ClassNode clazz = new ClassNode();

    public KlassCompiler(final ModuleCompiler module,
                         final IInterfaceType type,
                         final SourceLocation location)
    {
        this.program = module.program;
        this.module = module;
        this.type = type;
        this.location = location;

        this.info = new Design(type);
    }

    public String name()
    {
        return Utils.internalName(type) + "_prototype";
    }

    /**
     * This method generates bytecode that loads an instance of the class onto the operand-stack.
     *
     * @param code is the list of instructions being generated.
     */
    public void load(final InsnList code)
    {
        code.add(new TypeInsnNode(Opcodes.NEW, name()));
        code.add(new InsnNode(Opcodes.DUP));
        code.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, name(), "<init>", "()V"));
    }

    public ClassFile build()
    {
        /**
         * Generate the field to put into the class.
         */
        final List<FieldNode> fields = Lists.newLinkedList();

        fields.add(this.generateFieldKlass());

        /**
         * Generate the methods and constructors to put into the class.
         */
        final List<MethodNode> methods = Lists.newLinkedList();

        methods.add(this.generateStaticCtor());
        methods.add(this.generateCtorNullary());
        methods.add(this.generateCtorCopy());

        // Generate the copy() methods.
        //   copy() : T, where T is the the type of the design.
        //   copy() : Prototype, a bridge method, which simply invokes copy() : T.
        //   copy() : AbstractPrototype, a bridge method, simply invokes copy() : T.
        methods.add(this.generateMethodCopy());
        methods.add(this.generateMethodCopyReturnsPrototype());
        methods.add(this.generateMethodCopyReturnsAbstractPrototype());

        /**
         * Create the bytecode representation of the design itself.
         */
        clazz.version = Opcodes.V1_6;
        clazz.visibleAnnotations = Lists.newLinkedList();
        clazz.access = Opcodes.ACC_FINAL + Opcodes.ACC_SYNTHETIC;
        clazz.name = name();
//        clazz.superName = Utils.internalName(program.typesystem.utils.ABSTRACT_PROTOTYPE);
        clazz.interfaces = Lists.newArrayList(Utils.internalName(type));
        clazz.fields.addAll(fields);
        clazz.methods.addAll(methods);
        clazz.sourceFile = String.valueOf(location.getFile());

        /**
         * Assemble the bytecode into an array of bytes.
         */
        final ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        clazz.accept(writer);
        final byte[] bytecode = writer.toByteArray();

        /**
         * Create the class-file object that will store the emitted bytecode.
         */
        final String source_name = Utils.sourceName(type) + "_prototype";
        final ClassFile file = new ClassFile(source_name, bytecode);

        return file;
    }

    private FieldNode generateFieldKlass()
    {
        final int access = Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC + Opcodes.ACC_FINAL;

        final String descriptor = "";
        final FieldNode field = new FieldNode(access, "klass", descriptor, null, null);

        return field;
    }

    /**
     * This method generates the static-constructor of the interface.
     *
     * @return the bytecode representation of the static constructor.
     */
    private MethodNode generateStaticCtor()
    {
        final MethodNode ctor = new MethodNode();

        ctor.access = Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC;
        ctor.name = "<clinit>";
        ctor.desc = "()V";
        ctor.exceptions = ImmutableList.of();

        /**
         * Create the MetaPrototype object.
         */
//        ctor.instructions.add(new TypeInsnNode(Opcodes.NEW, Utils.internalName(program.typesystem.utils.META_PROTOTYPE)));
        ctor.instructions.add(new InsnNode(Opcodes.DUP));
        Utils.invoke(ctor.instructions,
                     Opcodes.INVOKESPECIAL,
                     Class.class,
                     void.class,
                     "<init>");

        /**
         * Initialize the MetaPrototype object.
         */
        int index = 0;

        for (Entry<String, GetterList> entry : info.getters().entrySet())
        {
            final GetterList getters = entry.getValue();
            final SetterList setters = info.setters().get(entry.getKey());

            Preconditions.checkArgument(getters != null);
            Preconditions.checkArgument(setters != null);

            defineProperty(ctor.instructions, getters);

            generateGetter(index, getters);
            generateSetter(index, setters);

            ++index;
        }

        for (Entry<String, MethodList> entry : info.methods().entrySet())
        {
            if (defineMethod(ctor.instructions, entry.getValue()))
            {
                generateMethod(index, entry.getValue());

                ++index;
            }
        }

        /**
         * Put the MetaPrototype object into the 'klass' field.
         */
//        ctor.instructions.add(new FieldInsnNode(Opcodes.PUTSTATIC,
//                                                name(),
//                                                "klass",
//                                                program.typesystem.utils.META_PROTOTYPE.getDescriptor()));
        ctor.instructions.add(new InsnNode(Opcodes.RETURN));

        return ctor;
    }

    /**
     * This method generates bytecode that adds a property to a meta-prototype.
     *
     * @param code is the code being generated.
     * @param getters partially describes the property to add.
     */
    private void defineProperty(final InsnList code,
                                final GetterList getters)
    {
        /**
         * Get the most specifically typed getter associated with the property.
         */
        final IMethod most_specific_getter = getters.list().get(0);

        /**
         * Get the name of the property.
         */
        final String name = most_specific_getter.getName();

        /**
         * Get the static-type of the value stored in the property.
         */
        final IReturnType returns = most_specific_getter.getReturnType();

        /**
         * Assume the operand-stack contains a reference to the meta-prototype.
         *
         * We need to duplicate the reference, so we do not remove it.
         *
         * Then, we need to load the name and the type onto the operand-stack.
         *
         * Finally, we need to invoke a method on the meta-prototype in order
         * to define the property.
         */
        code.add(new InsnNode(Opcodes.DUP));
        code.add(new LdcInsnNode(name));
        code.add(Utils.ldcClass(returns));
        Utils.invoke(code,
                     Opcodes.INVOKEVIRTUAL,
                     Class.class,
                     void.class,
                     "newProperty",
                     String.class,
                     Class.class);
    }

    /**
     * This method generates bytecode that adds a method to a meta-prototype.
     *
     * @param code is the code being generated.
     * @param getters partially describes the method to add.
     * @return true, iff a method was actually defined.
     */
    private boolean defineMethod(final InsnList code,
                                 final MethodList methods)
    {

        /**
         * Get the most specifically typed override associated with the method.
         */
        final IMethod most_specific_override = methods.list().get(0);

        /**
         * Get the name of the method.
         */
        final String name = most_specific_override.getName();

        /**
         * Get the static return-type of the method.
         */
        final IReturnType returns = most_specific_override.getReturnType();

        if (most_specific_override.getOwner().isInterfaceType() == false)
        {
            return false;
        }

        if (most_specific_override.getOwner().equals(program.typesystem.utils.STRUCT))
        {
            return false;
        }

        /**
         * Assume the operand-stack contains a reference to the meta-prototype.
         *
         * We need to duplicate the reference, so we do not remove it.
         *
         * Next, we need to load the name onto the operand-stack.
         *
         * Then, we need to create a list of Class objects,
         * which contains the types of the method's formal-parameters,
         * on the operand-stack.
         *
         * Next, we need to load the method's return-type onto the operand-stack.
         *
         * Finally, we need to invoke a method on the meta-prototype in order
         * to define the method.
         */
        code.add(new InsnNode(Opcodes.DUP));
        code.add(new LdcInsnNode(name));
        loadParameterTypes(code, most_specific_override);
        code.add(Utils.ldcClass(returns));
        Utils.invoke(code,
                     Opcodes.INVOKEVIRTUAL,
                     Class.class,
                     void.class,
                     "newMethod",
                     String.class,
                     Iterable.class,
                     Class.class);

        return true;
    }

    /**
     * This method generates bytecode that creates a list of class objects,
     * which are the types of a method's formal-parameters.
     *
     * @param code is the code being generated.
     * @param method is the method whose formal-parameter types will be in the list.
     */
    private void loadParameterTypes(final InsnList code,
                                    final IMethod method)
    {
        final CollectionCompiler<IFormalParameter> cmp = new CollectionCompiler<IFormalParameter>()
        {
            @Override
            public void compile(final IFormalParameter element)
            {
                code().add(Utils.ldcClass(element.getType()));
            }

            @Override
            public InsnList code()
            {
                return code;
            }
        };

        cmp.compile(method.getParameters());
    }

    /**
     * This method generates the bytecode representation of the nullary constructor.
     *
     * <p>
     * The nullary constructor simply invokes super(klass).
     * Obviously, klass is the value stored in the klass field, which stores the meta-prototype.
     * </p>
     *
     * @return the generated method.
     */
    private MethodNode generateCtorNullary()
    {
        final MethodNode ctor = new MethodNode();

        ctor.access = Opcodes.ACC_SYNTHETIC;
        ctor.name = "<init>";
        ctor.desc = "()V";
        ctor.exceptions = ImmutableList.of();

        // Generated Bytecode:
        //
        // ALOAD this                            - Load the uninitialized 'this' reference.
        // GETSTATIC *this*.klass                - Load the MetaPrototype object that describes design.
        // INVOKESPECIAL super(MetaPrototype)    - Invoke the super constructor.
        // RETURN                                - Exit the constructor.

        ctor.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0));

//        ctor.instructions.add(new FieldInsnNode(Opcodes.GETSTATIC, name(),
//                                                "klass",
//                                                program.typesystem.utils.META_PROTOTYPE.getDescriptor()));

        Utils.invoke(ctor.instructions,
                     Opcodes.INVOKESPECIAL,
                     Class.class,
                     void.class,
                     "<init>",
                     Class.class);

        ctor.instructions.add(new InsnNode(Opcodes.RETURN));

        return ctor;
    }

    /**
     * This method generates the bytecode representation of the copy constructor.
     *
     * @return the bytecode representation of the ctor(AbstractPrototype).
     */
    private MethodNode generateCtorCopy()
    {
        final MethodNode ctor = new MethodNode();

        ctor.access = Opcodes.ACC_PRIVATE + Opcodes.ACC_SYNTHETIC;
        ctor.name = "<init>";
//        ctor.desc = "(" + program.typesystem.utils.ABSTRACT_PROTOTYPE.getDescriptor() + ")V";
        ctor.exceptions = ImmutableList.of();

        // Generated Bytecode:
        //
        // ALOAD this                                - Load the uninitialized 'this' reference.
        // ALOAD original                            - Load the original prototype object.
        // INVOKESPECIAL super(AbstractPrototype)    - Invoke the super copy constructor.
        // RETURN                                    - Exit the constructor.

        ctor.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0));

        ctor.instructions.add(new VarInsnNode(Opcodes.ALOAD, 1));

        Utils.invoke(ctor.instructions,
                     Opcodes.INVOKESPECIAL,
                     Class.class,
                     void.class,
                     "<init>",
                     Class.class);

        ctor.instructions.add(new InsnNode(Opcodes.RETURN));

        return ctor;
    }

    /**
     * This method generates the bytecode representation of the copy() : T method,
     * where T is the type of the design.
     *
     * @return the bytecode representation of the method.
     */
    private MethodNode generateMethodCopy()
    {
        // There are two bridge methods that will invoke copy() : T.
        // Namely: copy() : Prototype
        //         copy() : AbstactPrototype

        final MethodNode method = new MethodNode();

        method.access = Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL;
        method.name = "copy";
        method.desc = "()" + type.getDescriptor();
        method.exceptions = ImmutableList.of();

        // Generated Bytecode:
        //
        // NEW *this-type*                         - Create a new instance of the type that implements the design.
        // DUP                                     - Duplicate the reference to the uninitialized instance.
        // ALOAD this                              - Load the prototype object.
        // INVOKESPECIAL <init>(AbstractPrototype) - Invoke the copy constructor in order to initialize the instance.
        // ARETURN                                 - Return the initialized instance, which is the copy.

        method.instructions.add(new TypeInsnNode(Opcodes.NEW, name()));

        method.instructions.add(new InsnNode(Opcodes.DUP));

        method.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0));

//        method.instructions.add(new MethodInsnNode(Opcodes.INVOKESPECIAL,
//                                                   name(),
//                                                   "<init>",
////                                                   "(" + program.typesystem.utils.ABSTRACT_PROTOTYPE.getDescriptor() + ")V"));

//        method.instructions.add(new InsnNode(Opcodes.ARETURN));

        return null;
    }

    /**
     * This method generates the bytecode representation of the copy() : AbstractPrototype method.
     *
     * @return the bytecode representation of the method.
     */
    private MethodNode generateMethodCopyReturnsAbstractPrototype()
    {
        final MethodNode method = new MethodNode();

        method.access = Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_BRIDGE;
        method.name = "copy";
//        method.desc = "()" + program.typesystem.utils.ABSTRACT_PROTOTYPE.getDescriptor();
        method.exceptions = ImmutableList.of();

        // Generated Bytecode:
        //
        // ALOAD this                            - Load the prototype object.
        // INVOKEVIRTUAL copy() : *design-type*  - Invoke the true copy() method.
        // CHECKCAST *this-type*                 - Downcast the copy to type that implements the design.
        // ARETURN                               - Return the copy.

        method.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0));
        method.instructions.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL,
                                                   name(),
                                                   "copy",
                                                   "()" + type.getDescriptor()));

        method.instructions.add(new TypeInsnNode(Opcodes.CHECKCAST, name()));

        method.instructions.add(new InsnNode(Opcodes.ARETURN));

        return method;
    }

    /**
     * This method generates the bytecode representation of the copy() : Prototype method.
     *
     * @return the bytecode representation of the method.
     */
    private MethodNode generateMethodCopyReturnsPrototype()
    {
        final MethodNode method = new MethodNode();

        method.access = Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_BRIDGE;
        method.name = "copy";
        method.desc = "()" + program.typesystem.utils.STRUCT.getDescriptor();
        method.exceptions = ImmutableList.of();

        // Generated Bytecode:
        //
        // ALOAD this                            - Load the prototype object.
        // INVOKEVIRTUAL copy() : *design-type*  - Invoke the true copy() method.
        // ARETURN                               - Return the copy.

        method.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0));
        method.instructions.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL,
                                                   name(),
                                                   "copy",
                                                   "()" + type.getDescriptor()));

        method.instructions.add(new InsnNode(Opcodes.ARETURN));

        return method;
    }

    private void generateSetter(final int index,
                                final SetterList setter_list)
    {
        generateSetter(index, setter_list.mostSpecific());

        for (Entry<IMethod, IMethod> entry : setter_list.bridges().entrySet())
        {
            generateBridgeMethod(entry.getKey(), entry.getValue());
        }
    }

    private void generateGetter(final int index,
                                final GetterList getter_list)
    {
        generateGetter(index, getter_list.mostSpecific());

        for (Entry<IMethod, IMethod> entry : getter_list.bridges().entrySet())
        {
            generateBridgeMethod(entry.getKey(), entry.getValue());
        }
    }

    private void generateMethod(final int index,
                                final MethodList method_list)
    {
        generateMethod(index, method_list.mostSpecific());

        for (Entry<IMethod, IMethod> entry : method_list.bridges().entrySet())
        {
            generateBridgeMethod(entry.getKey(), entry.getValue());
        }
    }

    /**
     * This method generates a non-bridge setter method.
     *
     * @param index is the index of the related property within the prototype.
     * @param setter_type is the type of the setter method to generate.
     */
    private void generateSetter(final int index,
                                final IMethod setter_type)
    {
        final MethodNode method = Utils.bytecodeOf(module, setter_type);
        clazz.methods.add(method);

        final IVariableType argument = setter_type.getParameters().get(0).getType();

        final String parameter_desc = argument.isReferenceType()
                ? "Ljava/lang/Object;"
                : argument.getDescriptor();

//        final String desc = "(" + program.typesystem.utils.ABSTRACT_PROTOTYPE.getDescriptor() + "I" + parameter_desc + ")" + program.typesystem.utils.ABSTRACT_PROTOTYPE.getDescriptor();

        // Remove the abstract modifier.
        method.access = Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL;

        // Generated Bytecode:
        //
        // ALOAD this        - Load the prototype object.
        // LDC index         - Load the index of the property.
        // LOAD value        - Load the value to assign to the property.
        // INVOKESTATIC set  - One of the set methods in the AbstractPrototype.Actions class.
        // ARETURN           - Return the modified version of the prototype.
        //
        //////////////////////////////////////////////////////////////////////////////////////

        method.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0));

        method.instructions.add(new LdcInsnNode(index));

        method.instructions.add(Utils.selectLoadVarInsn(argument, 1));

//        method.instructions.add(new MethodInsnNode(Opcodes.INVOKESTATIC,
//                                                   Utils.internalName(program.typesystem.utils.ACTIONS),
//                                                   "set",
//                                                   desc));

        method.instructions.add(new InsnNode(Opcodes.ARETURN));
    }

    /**
     * This method generates a non-bridge getter method.
     *
     * @param index is the index of the related property within the prototype.
     * @param getter_type is the type of the getter method to generate.
     */
    private void generateGetter(final int index,
                                final IMethod getter_type)
    {
        final MethodNode method = Utils.bytecodeOf(module, getter_type);
        clazz.methods.add(method);

        final String name = getter_type.getReturnType().isReferenceType()
                ? "getO"
                : "get" + getter_type.getReturnType().getDescriptor();

        final String return_desc = getter_type.getReturnType().isReferenceType()
                ? "Ljava/lang/Object;"
                : getter_type.getReturnType().getDescriptor();

        final String desc = "I)" + return_desc;

        // Remove the abstract modifier.
        method.access = Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL;

        // Generated Bytecode, if the getter returns a primitive-type:
        //
        // ALOAD this              - Load the prototype object.
        // LDC index               - Load the index of the property.
        // INVOKESTATIC get*       - One of the get methods in the AbstractPrototype.Actions class.
        // *RETURN                 - Return the value returned by the get* method.
        //
        //
        // Generated Bytecode, if the getter returns a reference-type:
        //
        // ALOAD this              - Load the prototype object.
        // LDC index               - Load the index of the property.
        // INVOKESTATIC getO       - One of the get methods in the AbstractPrototype.Actions class.
        // CHECKCAST *return-type* - Downcast the value returned by the getO method to the return-type of the getter.
        // ARETURN                 - Return the value returned by the getO method.
        //
        //////////////////////////////////////////////////////////////////////////////////////

        method.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0));

        method.instructions.add(new LdcInsnNode(index));

//        method.instructions.add(new MethodInsnNode(Opcodes.INVOKESTATIC,
//                                                   Utils.internalName(program.typesystem.utils.ACTIONS),
//                                                   name,
//                                                   desc));

        if (getter_type.getReturnType().isReferenceType())
        {
            final String cast_type = Utils.internalName((IReferenceType) getter_type.getReturnType());

            method.instructions.add(new TypeInsnNode(Opcodes.CHECKCAST,
                                                     cast_type));
        }

        method.instructions.add(Utils.selectReturnInsn(getter_type.getReturnType()));
    }

    private void generateMethod(final int index,
                                final IMethod method_type)
    {
        // TODO: exception handling

        final MethodNode method = Utils.bytecodeOf(module, method_type);
        clazz.methods.add(method);

        // Remove the abstract modifier.
        method.access = Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL;

        // Generated Bytecode:
        //
        // ALOAD this                     - Load the prototype object.
        // LDC index                      - Load the index of the method in the prototype.
        // INVOKESTATIC getThreadStack    - Get the argument-stack of the current thread.
        // DUP                            - Duplicate the reference to the argument-stack.
        // ALOAD this                     - Load the prototype object.
        // PUSH-ARG                       - Push the prototype object onto the argument-stack.
        // DUP                            - Duplicate the reference to the argument-stack.
        // LOAD argument[0]               - Load argument[0] to pass to the method.
        // PUSH-ARG                       - Push the argument onto the argument-stack.
        // DUP                            - Duplicate the reference to the argument-stack.
        // LOAD argument[1]               - Load argument[1] to pass to the method.
        // PUSH-ARG                       - Push the argument onto the argument-stack.
        // DUP                            - Duplicate the reference to the argument-stack.
        // LOAD argument[2]               - Load argument[2] to pass to the method.
        // PUSH-ARG                       - Push the argument onto the argument-stack.
        // DUP                            - Duplicate the reference to the argument-stack.
        // LOAD argument[.]               - Load argument[.] to pass to the method.
        // PUSH-ARG                       - Push the argument onto the argument-stack.
        // DUP                            - Duplicate the reference to the argument-stack.
        // LOAD argument[n]               - Load argument[n] to pass to the method.
        // PUSH-ARG                       - Push the argument onto the argument-stack.
        // DUP_X2                         - Duplicate the reference to the argument-stack and place it below the (prototype, index, and argument-stack).
        // INVOKESTATIC invoke            - Invoke the Actions.invoke(AbstractPrototype, int, ArgumentStack) method.
        //
        //
        // The following bytecode will be generated, if the return-type is a primitive-type.
        //
        // PEEK-ARG                       - Transfer the method's return-value from the argument-stack to the operand-stack.
        // INVOKESTATIC getThreadStack    - Get the argument-stack of the current thread in order to automatically clear it.
        // POP                            - Pop the argument-stack off of the operand-stack.
        // X-RETURN                       - Return the return-value.
        //
        //
        // The following bytecode will be generated, if the return-type is a reference-type.
        //
        // PEEK-ARG                       - Transfer the method's return-value from the argument-stack to the operand-stack.
        // INVOKESTATIC getThreadStack    - Get the argument-stack of the current thread in order to automatically clear it.
        // POP                            - Pop the argument-stack off of the operand-stack.
        // CHECKCAST *return-type*        - Cast the return-value to the return-type.
        // ARETURN                        - Return the return-value.
        //
        //
        // The following bytecode will be generated, if the return-type is the void-type.
        //
        // INVOKESTATIC getThreadStack    - Get the argument-stack of the current thread in order to automatically clear it.
        // RETURN                         - Return the return-value.
        ////////////////////////////////////////////////////////////////////////////////////////////
        //
        //
        ////////////////////////////////////////////////////////////////////////////////////////////

        method.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0)); // Load 'this'

        method.instructions.add(new LdcInsnNode(index));

        Utils.loadArgumentStack(method.instructions);

        method.instructions.add(new InsnNode(Opcodes.DUP));

        method.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0)); // Load 'this'

        Utils.pushArgument(program, method.instructions, type);

        int address = 1; // offset due to 'this'

        for (IFormalParameter formal : method_type.getParameters())
        {
            method.instructions.add(new InsnNode(Opcodes.DUP));

            method.instructions.add(Utils.selectLoadVarInsn(formal.getType(), address));

            Utils.pushArgument(program, method.instructions, formal.getType());

            address += Utils.sizeof(formal.getType());
        }

        method.instructions.add(new InsnNode(Opcodes.DUP_X2));

//        method.instructions.add(new MethodInsnNode(Opcodes.INVOKESTATIC,
//                                                   Utils.internalName(program.typesystem.utils.ACTIONS),
//                                                   "invoke",
//                                                   "(" + program.typesystem.utils.ABSTRACT_PROTOTYPE.getDescriptor() + "I" + program.typesystem.utils.ARGUMENT_STACK.getDescriptor() + ")V"));

        if (method_type.getReturnType().isPrimitiveType())
        {
            Utils.peekArgument(program, method.instructions, method_type.getReturnType());

            Utils.loadArgumentStack(method.instructions);

            method.instructions.add(new InsnNode(Opcodes.POP));

            method.instructions.add(Utils.selectReturnInsn(method_type.getReturnType()));
        }
        else if (method_type.getReturnType().isReferenceType())
        {
            Utils.peekArgument(program, method.instructions, method_type.getReturnType());

            Utils.loadArgumentStack(method.instructions);

            method.instructions.add(new InsnNode(Opcodes.POP));

            method.instructions.add(new TypeInsnNode(Opcodes.CHECKCAST,
                                                     Utils.internalName((IReferenceType) method_type.getReturnType())));

            method.instructions.add(new InsnNode(Opcodes.ARETURN));
        }
        else // void return-type
        {
            Utils.loadArgumentStack(method.instructions);

            method.instructions.add(new InsnNode(Opcodes.RETURN));
        }
    }

    /**
     * This method generates a bridge method that simply invokes a more specific method.
     *
     * <p>
     * Example:<br>
     * Assume there are two methods moo() : A and moo() : B.
     * Further, assume that B is a subtype of A.
     * Then the first method is a bridge method.
     * That is to say that moo() : A simply invokes moo() : B and then returns the result.
     * </p>
     *
     * @param bridge is the method that will invoke the target method.
     * @param target is the method that will be invoked by the bridge method.
     */
    private void generateBridgeMethod(final IMethod bridge,
                                      final IMethod target)
    {
        final MethodNode method = Utils.bytecodeOf(module, bridge);
        clazz.methods.add(method);

        // Remove the abstract modifier.
        method.access = Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL;

        // Generated Bytecode:
        //
        // ALOAD this                - Load the prototype object.
        // LOAD argument[0]          - Load argument[0] to pass to the target method.
        // LOAD argument[1]          - Load argument[1] to pass to the target method.
        // LOAD argument[2]          - Load argument[2] to pass to the target method.
        // LOAD argument[.]          - Load argument[.] to pass to the target method.
        // LOAD argument[n]          - Load argument[n] to pass to the target method.
        // INVOKEVIRTUAL target      - Invoke the target method.
        // CHECKCAST *return-type*   - Cast the target's return-value to the return-type (see note).
        // X-RETURN                  - Return the return-value.
        ////////////////////////////////////////////////////////////////////////////////////////////
        //
        // Note: The CHECKCAST instruction is emitted, only if the return-type is a reference-type.
        //
        ////////////////////////////////////////////////////////////////////////////////////////////

        method.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0)); // Load 'this'

        int address = 1; // offset due to 'this'

        for (IFormalParameter formal : bridge.getParameters())
        {
            method.instructions.add(Utils.selectLoadVarInsn(formal.getType(), address));

            address += Utils.sizeof(formal.getType());
        }

        method.instructions.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL,
                                                   name(),
                                                   target.getName(),
                                                   target.getDescriptor()));

        if (bridge.getReturnType().isReferenceType())
        {
            method.instructions.add(new TypeInsnNode(Opcodes.CHECKCAST,
                                                     Utils.internalName((IReferenceType) bridge.getReturnType())));
        }

        method.instructions.add(Utils.selectReturnInsn(bridge.getReturnType()));
    }
}

package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.Functor;
import autumn.lang.compiler.ClassFile;
import autumn.lang.compiler.ast.nodes.FormalParameter;
import autumn.lang.compiler.ast.nodes.FunctorDefinition;
import autumn.lang.internals.ArgumentStack;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import high.mackenzie.autumn.lang.compiler.typesystem.CustomConstructor;
import high.mackenzie.autumn.lang.compiler.typesystem.CustomDeclaredType;
import high.mackenzie.autumn.lang.compiler.typesystem.CustomMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IConstructor;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IFormalParameter;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IInterfaceType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;
import high.mackenzie.autumn.lang.compiler.utils.Utils;
import java.util.LinkedList;
import java.util.List;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

/**
 * An instance of this class controls the compilation of a functor-definition.
 *
 * @author Mackenzie High
 */
public final class FunctorCompiler
        implements ICompiler
{
    private final ProgramCompiler program;

    private final ModuleCompiler module;

    private final FunctorDefinition node;

    private CustomDeclaredType type;

    /**
     * This will be the type-system representation of the sole constructor.
     */
    private CustomConstructor ctor;

    /**
     * This will be the type-system representation of the invoke(*) method.
     */
    private CustomMethod invoke;

    private final List<IVariableType> parameters = Lists.newLinkedList();

    /**
     * Sole Constructor.
     *
     * @param module is essentially the module that is being compiled.
     * @param node is the AST node that represents the functor-definition.
     */
    public FunctorCompiler(final ModuleCompiler module,
                           final FunctorDefinition node)
    {
        this.module = module;
        this.node = node;

        this.program = module.program;
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
         * Create the bytecode representation of the enum itself.
         */
        final ClassNode clazz = new ClassNode();
        {
            clazz.version = Opcodes.V1_6;
            clazz.visibleAnnotations = Lists.newArrayList();
            clazz.access = type.getModifiers();
            clazz.name = internal_name;
            clazz.superName = Utils.internalName(type.getSuperclass());
            clazz.interfaces = program.typesystem.utils.superinterfaces(type.getSuperinterfaces());
            clazz.fields = ImmutableList.of();
            clazz.methods = Lists.newLinkedList();
            clazz.sourceFile = String.valueOf(node.getLocation().getFile());

            clazz.methods.add(this.generateConstructor());
            clazz.methods.add(this.generateMethodInvoke());
            clazz.methods.add(this.generateMethodParameterTypes());
            clazz.methods.add(this.generateMethodReturnType());
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
     * {@inheritDoc}
     */
    @Override
    public void performTypeDeclaration()
    {
        /**
         * Determine the descriptor of the functor.
         */
        final String namespace = module.type.getNamespace().replace('.', '/');
        final String name = node.getName().getName();
        final String descriptor = "L" + namespace + '/' + name + ';';

        /**
         * Ensure that this functor is not a duplicate type-declaration.
         */
        if (program.typesystem.typefactory().findType(descriptor) != null)
        {
            // TODO: error
            System.out.println("Duplicate Type: " + descriptor);
        }

        /**
         * Declare the functor.
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
         * Set the type's modifier flags.
         */
        type.setModifiers(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL);

        /**
         * Set the superclass of the functor's type.
         */
        type.setSuperclass(program.typesystem.utils.ABSTRACT_STATIC_FUNCTOR);

        /**
         * Add the constructor to the type.
         */
        type.setConstructors(Lists.newArrayList(typeOfConstructor()));

        /**
         * Add the invoke(*) method to the type.
         */
        type.setMethods(Lists.newArrayList(typeOfMethodInvoke()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeStructureChecking()
    {
        // Pass, because nostructure needs checked.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeUsageChecking()
    {
        // Pass, because no usages need checked.
    }

    /**
     * This method creates the type-system representation of constructor.
     *
     * @return the type of the constructor.
     */
    public IConstructor typeOfConstructor()
    {
        ctor = new CustomConstructor(type.getTypeFactory());

        final IInterfaceType FUNCTOR = program.typesystem.utils.FUNCTOR;

        final IFormalParameter formal = program.typesystem.utils.formal(FUNCTOR);

        ctor.setOwner(type);
        ctor.setAnnotations(new LinkedList());
        ctor.setModifiers(Opcodes.ACC_PUBLIC);
        ctor.setName("<init>");
        ctor.setParameters(Lists.newArrayList(formal));
        ctor.setReturnType(program.typesystem.utils.VOID);
        ctor.setThrowsClause(new LinkedList());

        return ctor;
    }

    /**
     * This method creates the type-system representation of the invoke(*) method.
     *
     * @return the type of the aforesaid method.
     */
    public IMethod typeOfMethodInvoke()
    {
        invoke = new CustomMethod(type.getTypeFactory(), false);

        /**
         * Create the list of formal parameters.
         */
        final List<IFormalParameter> formals = Lists.newLinkedList();

        for (FormalParameter param : node.getParameters().getParameters())
        {
            final IVariableType param_type = module.imports.resolveVariableType(param.getType());

            formals.add(program.typesystem.utils.formal(param_type));

            parameters.add(param_type);
        }

        /**
         * Initialize the type-system representation of the method.
         */
        invoke.setOwner(type);
        invoke.setAnnotations(new LinkedList());
        invoke.setModifiers(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL);
        invoke.setName("invoke");
        invoke.setParameters(formals);
        invoke.setReturnType(module.imports.resolveType(node.getReturnType()));
        invoke.setThrowsClause(Lists.newArrayList(program.typesystem.utils.THROWABLE));

        return invoke;
    }

    /**
     * This method generates the bytecode representation of the constructor.
     *
     * @return the constructor's bytecode.
     */
    private MethodNode generateConstructor()
    {
        final MethodNode method = Utils.bytecodeOf(module, ctor);

        /**
         * Invoke the super constructor.
         */
        method.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0)); // Load 'this'
        method.instructions.add(new VarInsnNode(Opcodes.ALOAD, 1)); // Load param[0]
        method.instructions.add(new MethodInsnNode(Opcodes.INVOKESPECIAL,
                                                   Utils.internalName(program.typesystem.utils.ABSTRACT_STATIC_FUNCTOR),
                                                   "<init>",
                                                   "(Lautumn/lang/Functor;)V"));

        /**
         * Return from the constructor.
         */
        method.instructions.add(new InsnNode(Opcodes.RETURN));

        return method;
    }

    /**
     * This method generates the bytecode representation of the parameterTypes().
     *
     * @return the method's bytecode.
     */
    private MethodNode generateMethodParameterTypes()
    {
        final MethodNode method = new MethodNode();

        method.access = Opcodes.ACC_PUBLIC;
        method.name = "parameterTypes";
        method.desc = "()Ljava/util/List;";
        method.exceptions = ImmutableList.of();

        final CollectionCompiler<IVariableType> cmp = new CollectionCompiler<IVariableType>()
        {
            @Override
            public void compile(final IVariableType element)
            {
                code().add(Utils.ldcClass(element));
            }

            @Override
            public InsnList code()
            {
                return method.instructions;
            }
        };

        /**
         * Generate the bytecode that creates the mutable list of class objects.
         */
        cmp.compile(parameters);

        /**
         * Convert the mutableList to an immutable list.
         */
        /**
         * Return from the method.
         */
        method.instructions.add(new InsnNode(Opcodes.ARETURN));

        return method;
    }

    /**
     * This method generates the bytecode representation of the returnType().
     *
     * @return the method's bytecode.
     */
    private MethodNode generateMethodReturnType()
    {
        final MethodNode method = new MethodNode();

        method.access = Opcodes.ACC_PUBLIC;
        method.name = "returnType";
        method.desc = "()Ljava/lang/Class;";
        method.exceptions = ImmutableList.of();

        /**
         * Generate the method's body
         */
        method.instructions.add(Utils.ldcClass(invoke.getReturnType()));
        method.instructions.add(new InsnNode(Opcodes.ARETURN));

        return method;
    }

    /**
     * This method generates the bytecode representation of the invoke(*) method.
     *
     * @return the method's bytecode.
     */
    private MethodNode generateMethodInvoke()
    {
        final MethodNode method = Utils.bytecodeOf(module, invoke);

        /**
         * Get the argument-stack associated with the current thread.
         */
        Utils.loadArgumentStack(method.instructions);

        /**
         * Load each of the formal-parameters onto the argument-stack.
         */
        int address = 1;
        for (IVariableType parameter : parameters)
        {
            method.instructions.add(new InsnNode(Opcodes.DUP));

            method.instructions.add(Utils.selectLoadVarInsn(parameter, address));

            Utils.pushArgument(program, method.instructions, parameter);

            address += Utils.sizeof(parameter);
        }

        /**
         * At this point, there is an ArgumentStack reference on the operand-stack.
         * We will need another later, so duplicate the object-reference.
         */
        method.instructions.add(new InsnNode(Opcodes.DUP));

        /**
         * Invoke the apply(ArgumentStack) method.
         */
        method.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0));
        method.instructions.add(new InsnNode(Opcodes.SWAP));
        Utils.invoke(method.instructions,
                     Opcodes.INVOKEINTERFACE,
                     Functor.class,
                     void.class,
                     "apply",
                     ArgumentStack.class);

        /**
         * Generate the code that will exit the invoke(*) method.
         * If the method returns void, a simply return is sufficient.
         * If a value must be returned, we must pop it off the argument-stack and then return it.
         */
        if (invoke.getReturnType().isVoidType())
        {
            method.instructions.add(new InsnNode(Opcodes.RETURN));
        }
        else
        {
            // Transfer the value to return from the argument-stack to the operand-stack.
            Utils.peekArgument(program, method.instructions, invoke.getReturnType());

            // Clear the argument-stack.
            // Simply retrieving the argument-stack causes it to be cleared.
            Utils.loadArgumentStack(method.instructions);
            method.instructions.add(new InsnNode(Opcodes.POP));

            // Return from the invoke method.
            method.instructions.add(Utils.selectReturnInsn(invoke.getReturnType()));
        }

        return method;
    }
}

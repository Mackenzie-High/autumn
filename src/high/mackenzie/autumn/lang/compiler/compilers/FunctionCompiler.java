package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.annotations.Setup;
import autumn.lang.compiler.ast.nodes.FormalParameter;
import autumn.lang.compiler.ast.nodes.FunctionDefinition;
import autumn.lang.compiler.ast.nodes.Variable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import high.mackenzie.autumn.lang.compiler.typesystem.CustomFormalParameter;
import high.mackenzie.autumn.lang.compiler.typesystem.CustomMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IAnnotation;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IAnnotationType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IFormalParameter;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;
import high.mackenzie.autumn.lang.compiler.utils.TypeSystemUtils;
import high.mackenzie.autumn.lang.compiler.utils.Utils;
import java.util.List;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;

/**
 * An instance of this class controls the compilation of a function-definition.
 *
 * @author Mackenzie High
 */
final class FunctionCompiler
        extends AbstractFunctionCompiler
        implements ICompiler
{
    /**
     * This is the Abstract-Syntax-Tree representation of the function.
     */
    public final FunctionDefinition node;

    /**
     * This is the type-system representation of the function.
     */
    public final CustomMethod type;

    /**
     * This label marks the location that recur-statements redirect execution to.
     */
    public final LabelNode recur_label = new LabelNode();

    /**
     * These are the formal-parameter variables.
     */
    private final List<Variable> param_vars = Lists.newArrayList();

    /**
     * These are the formal-parameter types.
     */
    private final List<IVariableType> param_types = Lists.newArrayList();

    /**
     * Sole Constructor.
     *
     * @param module is the compiler of the module that this definition is part of.
     * @param node is the AST node that represents this definition.
     */
    public FunctionCompiler(final ModuleCompiler module,
                            final FunctionDefinition node)
    {
        super(module, new VariableAllocator(0));

        this.node = node;

        this.type = new CustomMethod(module.program.typesystem.typefactory(), false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeDeclaration()
    {
        // Do not do anything during this pass.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeInitialization()
    {
        // Add this function's type to the collection of functions in the module's type.
        final List<IMethod> functions = Lists.newLinkedList(module.type.getMethods());
        functions.add(type);
        module.type.setMethods(functions);

        final int modifiers = Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC + Opcodes.ACC_FINAL;

        final List<IFormalParameter> params = Lists.newArrayList();
        {
            for (FormalParameter p : node.getParameters().getParameters())
            {
                final IVariableType param_type = module.imports.resolveVariableType(p.getType());

                final CustomFormalParameter param = new CustomFormalParameter();

                param.setAnnotations(ImmutableList.<IAnnotation>of());

                param.setType(param_type);

                params.add(param);

                param_vars.add(p.getVariable());
                param_types.add(param_type);
            }
        }

        type.setOwner(module.type);
        type.setAnnotations(module.anno_utils.typesOf(node.getAnnotations()));
        type.setModifiers(modifiers);
        type.setName(node.getName().getName());
        type.setParameters(params);
        type.setReturnType(module.imports.resolveReturnType(node.getReturnType()));

        /**
         * Add a special annotation.
         */
        module.anno_utils.add(type, autumn.lang.internals.annotations.FunctionDefinition.class);

        /**
         * Check the list of annotations.
         */
        program.checker.checkAnnotations(node.getAnnotations(), type.getAnnotations());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeStructureChecking()
    {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeUsageChecking()
    {
        /**
         * The function defines a scope for local variables.
         */
        allocator.enterScope();
        {
            /**
             * Allocate the formal-parameter local variables.
             */
            for (int i = 0; i < type.getParameters().size(); i++)
            {
                allocator.declareParameter(param_vars.get(i).getName(), param_types.get(i));
            }

            /**
             * Visit and type-check the body of the function.
             */
            try
            {
                final StatementTypeChecker checker = new StatementTypeChecker(this);

                node.getBody().accept(checker);

                /**
                 * Type-checking of labels was deferred in order to avoid
                 * the need for another compiler pass.
                 */
                labels.check();
            }
            catch (TypeCheckFailed ex)
            {
                // Pass, because the error was already reported via the error-reporter.
            }
        }
        allocator.exitScope();

        /**
         * A function defines the widest available scope for local variables.
         * Therefore, no variable should be in-scope after the function's scope is exited.
         */
        allocator.checkExitStatus();
    }

    public MethodNode build()
    {
        // Generated Bytecode:
        //
        // LABEL @RECUR
        //
        // <init-variables>
        // <body>
        // <default-termination>
        //
        //
        ///////////////////////////////////////////////////////////////////////////////////////////

        final MethodNode method = Utils.bytecodeOf(module, type);

        method.instructions.clear();
        {
            final StatementCodeGenerator codegen = new StatementCodeGenerator(this);

            method.instructions.add(recur_label);

            vars.initScope();

            node.getBody().accept(codegen);

            for (AbstractInsnNode x : instructions.toArray())
            {
                method.instructions.add(x);
            }
        }
//        method.instructions.add(new FieldInsnNode(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"));
//        method.instructions.add(new LdcInsnNode("Hello World!"));
//        method.instructions.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V"));
//        method.instructions.add(new InsnNode(Opcodes.RETURN));

        addDefaultMethodTermination(method);

        method.tryCatchBlocks = ImmutableList.copyOf(trycatches);

        return method;
    }

    /**
     * This method generates the default method termination bytecode.
     *
     * <p>
     * If a function does not contain a return statement, then execution may reach the end
     * of the function during an invocation. The JVM requires that this special case be handled.
     * Thus, it is necessary to generate bytecode to handle this special situation.
     * </p>
     *
     * <p>
     * Per the specification, a function will simply return, if the return-type is void.
     * On the other hand, the function will raise an exception, if the return-type is non-void.
     * </p>
     *
     * @param is the bytecode representation of the function.
     */
    private void addDefaultMethodTermination(final MethodNode method)
    {
        if (isReturnTypeVoid())
        {
            method.instructions.add(new InsnNode(Opcodes.RETURN));
        }
        else
        {
            method.instructions.add(new TypeInsnNode(Opcodes.NEW, "autumn/lang/exceptions/UnexpectedTerminationException"));
            method.instructions.add(new InsnNode(Opcodes.DUP));
            method.instructions.add(new MethodInsnNode(Opcodes.INVOKESPECIAL,
                                                       "autumn/lang/exceptions/UnexpectedTerminationException",
                                                       "<init>",
                                                       "()V"));
            method.instructions.add(new InsnNode(Opcodes.ATHROW));
        }
    }

    /**
     * This convenience function determines whether the function's return-type is void.
     *
     * @return true, iff the function returns void.
     */
    public boolean isReturnTypeVoid()
    {
        return "void".equals(node.getReturnType().getName().getName())
               && node.getReturnType().getDimensions() == null
               && node.getReturnType().getNamespace() == null;
    }

    /**
     * This method determines whether the function is a setup function.
     *
     * <p>
     * A setup function is a function that has the Setup annotation applied to it.
     * </p>
     *
     * @return true, if the function is a setup function.
     */
    public boolean isSetup()
    {
        assert type != null;

        return TypeSystemUtils.isAnnotationPresent(type, Setup.class);
    }

    /**
     * This method determines whether a particular annotation is applied to this method.
     *
     * @param anno is the type of the annotation.
     * @return true, iff an annotation of the specified type is applied to this function.
     */
    public boolean isAnnotationPresent(final IAnnotationType anno)
    {
        assert anno != null;

        for (IAnnotation x : type.getAnnotations())
        {
            if (x.getAnnotationType().equals(anno))
            {
                return true;
            }
        }

        return false;
    }
}

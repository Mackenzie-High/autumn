package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ast.nodes.FormalParameter;
import autumn.lang.compiler.ast.nodes.FunctionDefinition;
import autumn.lang.compiler.ast.nodes.Variable;
import autumn.lang.compiler.errors.ErrorCode;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import high.mackenzie.autumn.lang.compiler.typesystem.CustomFormalParameter;
import high.mackenzie.autumn.lang.compiler.typesystem.CustomMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IAnnotation;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IAnnotationType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IFormalParameter;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;
import high.mackenzie.autumn.lang.compiler.utils.Utils;
import high.mackenzie.autumn.resources.Finished;
import java.lang.reflect.Modifier;
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
@Finished("2015/01/18")
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
        // Pass
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeInitialization()
    {
        /**
         * Add this function's type to the collection of functions in the module's type.
         */
        final List<IMethod> functions = Lists.newLinkedList(module.type.getMethods());
        functions.add(type);
        module.type.setMethods(functions);

        /**
         * Convert the Abstract-Syntax-Tree representations of the formal-parameters
         * to their type-system representations.
         */
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

        /**
         * Create the type-system representation the function.
         */
        type.setOwner(module.type);
        type.setAnnotations(module.anno_utils.typesOf(node.getAnnotations()));
        type.setModifiers(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC | Opcodes.ACC_FINAL | (isAnnotationPresent(program.typesystem.utils.SYNC) ? Modifier.SYNCHRONIZED : 0));
        type.setName(node.getName().getName());
        type.setParameters(params);
        type.setReturnType(module.imports.resolveReturnType(node.getReturnType()));

        /**
         * Add a special annotation.
         */
        module.anno_utils.add(type, autumn.lang.internals.annotations.FunctionDefinition.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeStructureChecking()
    {
        /**
         * Check the list of annotations.
         */
        program.checker.checkAnnotations(node.getAnnotations(), type.getAnnotations());

        /**
         * Some names cannot be used.
         */
        program.checker.checkName(node.getName(), "instance");

        /**
         * Check the signature of a infer-function.
         */
        if (isAnnotationPresent(program.typesystem.utils.INFER))
        {
            boolean error = false;

            if (param_types.isEmpty())
            {
                error = true;
            }
            else if (param_types.get(0).isReferenceType() == false)
            {
                error = true;
            }
            else if (type.getReturnType().equals(param_types.get(0)) == false)
            {
                error = true;
            }

            if (error)
            {
                program.checker.reportWrongSignatureForAnnotation(this, ErrorCode.WRONG_SIGNATURE_FOR_INFER, "(T, ...) : T, where T is some reference-type");
            }
        }

        /**
         * Check the signature of a start-function.
         */
        if (isAnnotationPresent(program.typesystem.utils.START))
        {
            if (type.getNamePlusDescriptor().equals("main([Ljava/lang/String;)V") == false)
            {
                program.checker.reportWrongSignatureForAnnotation(this, ErrorCode.WRONG_SIGNATURE_FOR_START, "main (String[]) : void");
            }
        }

        /**
         * Check the signature of a setup-function.
         */
        if (isAnnotationPresent(program.typesystem.utils.SETUP))
        {
            if (type.getDescriptor().equals("()V") == false)
            {
                program.checker.reportWrongSignatureForAnnotation(this, ErrorCode.WRONG_SIGNATURE_FOR_SETUP, "() : void");
            }
        }

        /**
         * Check the signature of a test-function.
         */
        if (isAnnotationPresent(program.typesystem.utils.TEST))
        {
            if (type.getDescriptor().equals("(Lautumn/util/test/TestCase;)V") == false)
            {
                program.checker.reportWrongSignatureForAnnotation(this, ErrorCode.WRONG_SIGNATURE_FOR_TEST, "(TestCase) : void");
            }
        }
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

    /**
     * This method generates the bytecode representation of the function.
     *
     * @return the aforedescribed bytecode.
     */
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

        /**
         * Generate the bytecode, excluding the body.
         */
        final MethodNode method = Utils.bytecodeOf(module, type);

        /**
         * The body of the function is simply a statement.
         * So, we will use a statement-compiler to compile the body.
         */
        final StatementCodeGenerator codegen = new StatementCodeGenerator(this);

        /**
         * At runtime, execution will goto the recur-label,
         * whenever a recur-statement is executed inside the function.
         */
        method.instructions.add(recur_label);

        /**
         * Generate bytecode that assigns default-values to the local-variables.
         */
        vars.initScope();

        /**
         * Generate the bytecode of the body.
         */
        node.getBody().accept(codegen);

        /**
         * Transfer the generated instructions into the generated method.
         */
        for (AbstractInsnNode insn : instructions.toArray())
        {
            method.instructions.add(insn);
        }

        /**
         * Generate bytecode that throws an exception,
         * if the function unexpectedly terminates.
         */
        addDefaultMethodTermination(method);

        /**
         * If any bytecode level try-catch blocks were created,
         * then add them to the generated method.
         */
        method.tryCatchBlocks = ImmutableList.copyOf(trycatches);

        /**
         * Return the generated bytecode representation of the function.
         */
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
    private boolean isReturnTypeVoid()
    {
        return "void".equals(node.getReturnType().getName().getName())
               && node.getReturnType().getDimensions() == null
               && node.getReturnType().getNamespace() == null;
    }

    /**
     * This method determines whether a particular annotation is applied to this method.
     *
     * @param anno is the type of the annotation.
     * @return true, iff an annotation of the specified type is applied to this function.
     */
    public final boolean isAnnotationPresent(final IAnnotationType anno)
    {
        Preconditions.checkNotNull(anno);

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

///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package high.mackenzie.autumn.lang.compiler.compilers;
//
//import autumn.lang.compiler.ast.nodes.FormalParameter;
//import autumn.lang.compiler.ast.nodes.LambdaExpression;
//import autumn.lang.compiler.ast.nodes.Variable;
//import com.google.common.collect.ImmutableList;
//import com.google.common.collect.Lists;
//import high.mackenzie.autumn.lang.compiler.typesystem.design.IClassType;
//import high.mackenzie.autumn.lang.compiler.typesystem.design.IExpressionType;
//import high.mackenzie.autumn.lang.compiler.typesystem.design.IReturnType;
//import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;
//import high.mackenzie.autumn.lang.compiler.utils.Utils;
//import java.util.List;
//import org.objectweb.asm.Opcodes;
//import org.objectweb.asm.tree.AbstractInsnNode;
//import org.objectweb.asm.tree.InsnNode;
//import org.objectweb.asm.tree.MethodInsnNode;
//import org.objectweb.asm.tree.MethodNode;
//import org.objectweb.asm.tree.TypeInsnNode;
//
///**
// * An instance of this class controls the compilation of a lambda-function.
// *
// * @author Mackenzie High
// */
//public class LambdaCompiler
//        extends AbstractTypedFunctorCompiler
//{
//    /**
//     * This is the module that encloses the lambda.
//     */
//    public final ModuleCompiler module;
//
//    /**
//     * This is the scope of the lambda itself.
//     * This scope closes over the scope in which the lambda is created.
//     */
//    public final VariableScope scope;
//
//    /**
//     * This object simplifies the manipulation of variables.
//     */
//    public final VariableManipulator vars;
//
//    /**
//     * This is the lambda being compiled.
//     */
//    public final LambdaExpression lambda;
//
//    /**
//     * This is the code within the lambda.
//     * In other words, this is the code that is executed, when the lambda is invoked.
//     */
//    private final List<AbstractInsnNode> code = Lists.newLinkedList();
//
//    /**
//     * Sole Constructor.
//     *
//     * @param module is the enclosing module.
//     * @param outer is the scope that is closed-over.
//     * @param lambda is the lambda itself.
//     */
//    public LambdaCompiler(final ModuleCompiler module,
//                          final VariableScope outer,
//                          final LambdaExpression lambda)
//    {
//        super(module);
//
//        this.module = module;
//        this.scope = new VariableScope(outer, 1); // The initial-address equals 1, due to 'this'.
//        this.vars = new VariableManipulator(scope, code);
//        this.lambda = lambda;
//    }
//
//    /**
//     * This method causes the lambda to undergo static type-checking.
//     */
//    public void performTypeAnalysis()
//    {
//        for (FormalParameter param : lambda.getParameters().getParameters())
//        {
//            final Variable variable = param.getVariable();
//
//            final IExpressionType type = module.resolveType(param.getType());
//
//            scope.declareParameter(variable, type);
//        }
//
//        final ExpressionTypeChecker checker = new ExpressionTypeChecker(module, scope);
//
//        lambda.getBody().accept(checker);
//    }
//
//    /**
//     * This method causes the lambda's body to undergo code generation.
//     */
//    public void performCodeGeneration()
//    {
//        final ExpressionCodeGenerator codegen = new ExpressionCodeGenerator(module, vars, code);
//
//        lambda.getBody().accept(codegen);
//
//        code.add(Utils.selectReturnInsn(returnType()));
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    protected MethodNode buildMethodInvoke2()
//    {
//        final MethodNode method = new MethodNode();
//        method.access = Opcodes.ACC_PRIVATE + Opcodes.ACC_SYNTHETIC;
//        method.name = "invoke2";
//        method.desc = descriptorOfInvoke2();
//        method.exceptions = ImmutableList.of("java/lang/Throwable");
//
//        for (AbstractInsnNode node : code)
//        {
//            method.instructions.add(node);
//        }
//
//        return method;
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    protected IClassType superType()
//    {
//        return module.program.typesystem.utils.ABSTRACT_LAMBDA;
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    protected List<IVariableType> parameterTypes()
//    {
//        final List<IVariableType> result = Lists.newLinkedList();
//
//        for (FormalParameter param : lambda.getParameters().getParameters())
//        {
//            final IVariableType type = (IVariableType) module.resolveType(param.getType());
//
//            result.add(type);
//        }
//
//        return result;
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    protected IReturnType returnType()
//    {
//        return (IReturnType) module.program.symbols.expressions.get(lambda.getBody());
//    }
//
//    /**
//     * This method generates bytecode that creates the lambda and loads it onto the operand-stack.
//     *
//     * @param code is the bytecode where the lambda will be created.
//     */
//    public void load(final List<AbstractInsnNode> code)
//    {
//        // TODO: This is a very temporary implementation.
//        //       The lambda should be cached, if it does not capture any variables.
//
//        code.add(new TypeInsnNode(Opcodes.NEW, name()));
//        code.add(new InsnNode(Opcodes.DUP));
//        code.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, name(), "<init>", "()V"));
//    }
//}


//package high.mackenzie.autumn.lang.compiler.compilers;
//
//import autumn.lang.compiler.ast.commons.IExpression;
//import org.objectweb.asm.tree.ClassNode;
//import org.objectweb.asm.tree.InsnList;
//
///**
// * An instance of this class controls the bytecode generation of a lambda-statement.
// *
// * @author Mackenzie High
// */
//public final class LambdaCompiler
//{
//    private final ExpressionCodeGenerator codegen;
//
//    private final InsnList code;
//
//    private final VariableManipulator vars;
//
//    public LambdaCompiler(final ModuleCompiler module,
//                          final IExpression body)
//    {
//        this.vars = new VariableManipulator();
//
//        this.codegen = new ExpressionCodeGenerator(module, vars, code);
//    }
//
//    public ClassNode build()
//    {
//        return null;
//    }
//}


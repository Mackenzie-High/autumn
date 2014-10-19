package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ast.nodes.*;
import high.mackenzie.autumn.resources.Finished;

/**
 * This class provides a partial implementation of the IAstVisitor interface.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public class AbstractAstVisitor
        implements IAstVisitor
{
    @Override
    public void visit(Module object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(ModuleDirective object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(ImportDirective object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(Annotation object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(AnnotationList object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(AnnotationDefinition object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(ExceptionDefinition object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(DesignDefinition object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(TupleDefinition object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(StructDefinition object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(FunctorDefinition object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(EnumDefinition object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(FunctionDefinition object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(IfStatement object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(WhenStatement object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(GotoStatement object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(MarkerStatement object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(ForeverStatement object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(WhileStatement object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(UntilStatement object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(DoWhileStatement object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(DoUntilStatement object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(ForStatement object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(ForeachStatement object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(BreakStatement object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(ContinueStatement object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(RedoStatement object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(VarStatement object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(ValStatement object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(LetStatement object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(LambdaStatement object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(DelegateStatement object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(SetterStatement object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(GetterStatement object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(MethodStatement object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(SequenceStatement object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(ExpressionStatement object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(NopStatement object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(DebugStatement object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(TryCatchStatement object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(ExceptionHandler object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(ThrowStatement object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(AssertStatement object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(AssumeStatement object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(ReturnVoidStatement object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(ReturnValueStatement object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(RecurStatement object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(YieldVoidStatement object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(YieldValueStatement object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(BooleanDatum object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(CharDatum object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(ByteDatum object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(ShortDatum object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(IntDatum object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(LongDatum object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(FloatDatum object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(DoubleDatum object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(StringDatum object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(NullDatum object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(VariableDatum object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(ClassDatum object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(PrognExpression object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(ListExpression object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(DispatchExpression object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(CallStaticMethodExpression object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(SetStaticFieldExpression object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(GetStaticFieldExpression object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(NewExpression object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(CallMethodExpression object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(SetFieldExpression object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(GetFieldExpression object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(InstanceOfExpression object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(TernaryConditionalExpression object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(LocalsExpression object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(AsOperation object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(IsOperation object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(NegateOperation object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(NotOperation object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(DivideOperation object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(ModuloOperation object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(MultiplyOperation object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(AddOperation object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(SubtractOperation object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(LessThanOperation object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(LessThanOrEqualsOperation object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(GreaterThanOperation object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(GreaterThanOrEqualsOperation object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(EqualsOperation object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(NotEqualsOperation object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(IdentityEqualsOperation object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(IdentityNotEqualsOperation object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(AndOperation object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(OrOperation object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(XorOperation object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(ImpliesOperation object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(NullCoalescingOperation object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(ConcatOperation object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(Variable object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(DocComment object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(DocCommentLine object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(Name object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(TypeSpecifier object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(Namespace object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(ElementList object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(Element object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(FormalParameterList object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(FormalParameter object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(ConditionalCase object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(Label object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(SourceLocation object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ast.commons.IConstruct;
import autumn.lang.compiler.ast.commons.IExpression;
import autumn.lang.compiler.ast.commons.IStatement;
import autumn.lang.compiler.ast.nodes.BreakStatement;
import autumn.lang.compiler.ast.nodes.ContinueStatement;
import autumn.lang.compiler.ast.nodes.ExceptionHandler;
import autumn.lang.compiler.ast.nodes.Label;
import autumn.lang.compiler.ast.nodes.RedoStatement;
import autumn.lang.compiler.ast.nodes.TypeSpecifier;
import autumn.lang.compiler.ast.nodes.Variable;
import autumn.lang.compiler.errors.ErrorCode;
import autumn.lang.compiler.errors.ErrorReport;
import autumn.lang.compiler.errors.IErrorReporter;
import high.mackenzie.autumn.lang.compiler.exceptions.TypeUsageCheckFailed;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IExpressionType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IReturnType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IType;
import high.mackenzie.autumn.lang.compiler.utils.Utils;
import java.util.List;

/**
 * An instance of this class provides methods which check for an report static type errors.
 *
 * @author Mackenzie High
 */
public final class StaticChecker
{
    /**
     * This is the program that is being compiled.
     */
    private final ProgramCompiler program;

    /**
     * This is the error-reporter that will be used to report any errors that are found.
     */
    private final IErrorReporter reporter;

    /**
     * Sole Constructor.
     *
     * @param program is the program that is being compiled.
     */
    public StaticChecker(final ProgramCompiler program)
    {
        assert program != null;

        this.program = program;
        this.reporter = program.reporter;
    }

    private void report(final ErrorReport report)
    {
        reporter.reportFailedCheck(report);
        throw new TypeUsageCheckFailed();
    }

    /**
     * This method ensures that a condition expression returns either primitive or boxed boolean.
     *
     * @param condition is the condition expression itself.
     */
    public void checkCondition(final IExpression condition)
    {
        assert condition != null;

        final IExpressionType type = (IExpressionType) program.symbols.expressions.get(condition);

        final boolean primitive_boolean = "Z".equals(type.getDescriptor());
        final boolean boxed_boolean = "Ljava/lang/Boolean;".equals(type.getDescriptor());

        if (primitive_boolean || boxed_boolean)
        {
            return;
        }

        /**
         * Create the error-report.
         */
        final ErrorCode code = ErrorCode.EXPECTED_CONDITION;
        final String message = "A boolean condition was expected.";
        final ErrorReport report = new ErrorReport(condition, code, message);
        report.addDetail("Expected", "boolean OR java.lang.Boolean");
        report.addDetail("Actual", Utils.sourceName(type));

        /**
         * Issue the error-report to the user.
         */
        report(report);
    }

    /**
     * This method ensures that a variable was already declared.
     *
     * @param scope is the scope that should contain the variable.
     * @param variable is the variable itself.
     */
    public void checkDeclared(final VariableScope scope,
                              final Variable variable)
    {
        final boolean declared = scope.isDeclared(variable.getName());

        if (declared)
        {
            return;
        }

        final ErrorReport report = new ErrorReport(variable,
                                                   ErrorCode.NO_SUCH_VARIABLE,
                                                   "A variable was used before it was declared.");
        {
            report.addDetail("Variable", variable.getName());
        }

        /**
         * Issue the error-report to the user.
         */
        report(report);
    }

    /**
     * This method ensures that an expression's static-type is a subtype of the expected type.
     *
     * @param expression is the expression to check.
     * @param expected is the widest static-type that the expression can have.
     */
    public void expectSubtype(final IExpression expression,
                              final IExpressionType expected)
    {
        final IExpressionType actual = program.symbols.expressions.get(expression);

        if (actual.isSubtypeOf(expected))
        {
            return;
        }

        final String MESSAGE = "The static-type of an expression was unexpected.";

        final ErrorReport report = new ErrorReport(expression, ErrorCode.WRONG_TYPE, MESSAGE);
        {
            report.addDetail("Expected", Utils.sourceName(expected));
            report.addDetail("Actual", Utils.sourceName(actual));
        }

        /**
         * Issue the error-report to the user.
         */
        report(report);
    }

    public void expectVoidFunction(final IStatement statement,
                                   final IMethod function)
    {
//        if (function.getReturnType().isVoidType() == false)
//        {
//            return;
//        }
//
//        final ErrorCode ERROR_CODE = ErrorCode.EXPECTED_VOID_FUNCTION;
//
//        final String MESSAGE = "Void was found were it is not allowed.";
//
//        final ErrorReport report = new ErrorReport(statement, ERROR_CODE, MESSAGE);
//
//        reporter.reportFailedCheck(report);
//
//        throw new TypeUsageCheckFailed();
    }

    /**
     * This method ensures that an expression does not have a static-type of void.
     *
     * @param expression is the expression that cannot be void.
     */
    public void requireNonVoid(final IExpression expression)
    {
        final IType actual = program.symbols.expressions.get(expression);

        if ("V".equals(actual.getDescriptor()) == false)
        {
            return;
        }

        final ErrorCode ERROR_CODE = ErrorCode.EXPECTED_NON_VOID_EXPRESSION;

        final String MESSAGE = "Void was found were it is not allowed.";

        final ErrorReport report = new ErrorReport(expression, ERROR_CODE, MESSAGE);

        /**
         * Issue the error-report to the user.
         */
        report(report);
    }

    /**
     * This method ensures that an expression does not have the null-type as its static-type.
     *
     * @param expression is the expression that cannot be of the null-type.
     */
    public void requireNonNull(final IExpression expression)
    {
    }

    public void requireString(final IExpression expression)
    {
        final IType actual = program.symbols.expressions.get(expression);

        if ("Ljava/lang/String;".equals(actual.getDescriptor()))
        {
            return;
        }

        final ErrorCode ERROR_CODE = ErrorCode.EXPECTED_STRING;

        final String MESSAGE = "A java.lang.String was expected.";

        final ErrorReport report = new ErrorReport(expression, ERROR_CODE, MESSAGE);

        /**
         * Issue the error-report to the user.
         */
        report(report);
    }

    public void requireIterable(final IExpression expression)
    {
        final IType actual = program.symbols.expressions.get(expression);

        if (actual.isSubtypeOf(program.typesystem.utils.ITERABLE))
        {
            return;
        }

        final ErrorCode ERROR_CODE = ErrorCode.EXPECTED_ITERABLE;

        final String MESSAGE = "A java.lang.Iterable was expected.";

        final ErrorReport report = new ErrorReport(expression, ERROR_CODE, MESSAGE);

        /**
         * Issue the error-report to the user.
         */
        report(report);
    }

    public void requireThrowable(final IExpression expression)
    {
        final IType actual = program.symbols.expressions.get(expression);

        if (actual.isSubtypeOf(program.typesystem.utils.THROWABLE))
        {
            return;
        }

        final ErrorCode ERROR_CODE = ErrorCode.EXPECTED_THROWABLE;

        final String MESSAGE = "A java.lang.Throwable was expected.";

        final ErrorReport report = new ErrorReport(expression, ERROR_CODE, MESSAGE);

        /**
         * Issue the error-report to the user.
         */
        report(report);
    }

    public void requireThrowable(final IConstruct construct,
                                 final IType type)
    {
        if (type.isSubtypeOf(program.typesystem.utils.THROWABLE))
        {
            return;
        }

        final ErrorCode ERROR_CODE = ErrorCode.EXPECTED_THROWABLE;

        final String MESSAGE = "A java.lang.Throwable was expected.";

        final ErrorReport report = new ErrorReport(construct, ERROR_CODE, MESSAGE);

        /**
         * Issue the error-report to the user.
         */
        report(report);
    }

    public void reportNoSuchAsConversion(final IExpression value,
                                         final IReturnType type)
    {
    }

    public void reportNoSuchIsConversion(final IExpression value,
                                         final IReturnType type)
    {
    }

    public void reportNoSuchField(final IReturnType owner,
                                  final String name)
    {
    }

    public void reportNoSuchStaticField(final IReturnType owner,
                                        final String name)
    {
    }

    public void reportNoSuchConstructor(final IReturnType owner,
                                        final List<IExpressionType> arguments)
    {
    }

    public void reportNoSuchMethod(final IReturnType owner,
                                   final List<IExpressionType> arguments)
    {
    }

    public void reportNoSuchStaticMethod(final IReturnType owner,
                                         final List<IExpressionType> arguments)
    {
    }

    public void reportNoSuchType(final TypeSpecifier specifier,
                                 final IType type)
    {
        if (type != null)
        {
            return;
        }

        final ErrorCode ERROR_CODE = ErrorCode.NO_SUCH_TYPE;

        final String MESSAGE = "The specified type does not exist.";

        final ErrorReport report = new ErrorReport(specifier, ERROR_CODE, MESSAGE);

        report.details().put("Type", program.typesystem.utils.extractTypeName(specifier));

        /**
         * Issue the error-report to the user.
         */
        report(report);
    }

    public void requireArguments(final Iterable<IExpression> arguments)
    {
        for (IExpression x : arguments)
        {
            requireNonVoid(x);
        }
    }

    public void reportDuplicateVariable(final Variable variable,
                                        boolean condition)
    {
        if (!condition)
        {
            return;
        }

        final ErrorCode ERROR_CODE = ErrorCode.DUPLICATE_VARIABLE;

        final String MESSAGE = "A variable was declared more than once in the same scope.";

        final ErrorReport report = new ErrorReport(variable, ERROR_CODE, MESSAGE);

        report.details().put("Variable", variable.getName());

        /**
         * Issue the error-report to the user.
         */
        report(report);
    }

    public void reportDuplicateExceptionHandler(final ExceptionHandler handler,
                                                final IExpressionType type)
    {
        final ErrorCode ERROR_CODE = ErrorCode.DUPLICATE_EXCEPTION_HANDLER;

        final String MESSAGE = "Exception handlers in a try-catch block share a type.";

        final ErrorReport report = new ErrorReport(handler, ERROR_CODE, MESSAGE);

        report.details().put("Type", Utils.sourceName(type));

        /**
         * Issue the error-report to the user.
         */
        report(report);
    }

    public void reportBreakOutsideOfLoop(final BreakStatement statement)
    {
        final ErrorCode ERROR_CODE = ErrorCode.BREAK_OUTSIDE_OF_LOOP;

        final String MESSAGE = "'break' statements can only be used inside of loop constructs.";

        final ErrorReport report = new ErrorReport(statement, ERROR_CODE, MESSAGE);

        /**
         * Issue the error-report to the user.
         */
        report(report);
    }

    public void reportContinueOutsideOfLoop(final ContinueStatement statement)
    {
        final ErrorCode ERROR_CODE = ErrorCode.BREAK_OUTSIDE_OF_LOOP;

        final String MESSAGE = "'continue' statements can only be used inside of loop constructs.";

        final ErrorReport report = new ErrorReport(statement, ERROR_CODE, MESSAGE);

        /**
         * Issue the error-report to the user.
         */
        report(report);
    }

    public void reportRedoOutsideOfLoop(final RedoStatement statement)
    {
        final ErrorCode ERROR_CODE = ErrorCode.BREAK_OUTSIDE_OF_LOOP;

        final String MESSAGE = "'redo' statements can only be used inside of loop constructs.";

        final ErrorReport report = new ErrorReport(statement, ERROR_CODE, MESSAGE);

        /**
         * Issue the error-report to the user.
         */
        report(report);
    }

    public void requireTypedFunctor(final IExpressionType type)
    {
    }

    public void requireAutumnObject(final IExpressionType type)
    {
    }

    public void requireEnum(final IExpressionType type)
    {
    }

    public void reportDuplicateLabel(final Label label)
    {
        final ErrorCode ERROR_CODE = ErrorCode.DUPLICATE_LABEL;

        final String MESSAGE = "A label was declared more than once in the same scope.";

        final ErrorReport report = new ErrorReport(label, ERROR_CODE, MESSAGE);

        report.addDetail("Label", label.getName());

        /**
         * Issue the error-report to the user.
         */
        report(report);
    }

    public void reportUndeclaredLabel(final Label label)
    {
        final ErrorCode ERROR_CODE = ErrorCode.NO_SUCH_LABEL;

        final String MESSAGE = "A label was used, but not declared.";

        final ErrorReport report = new ErrorReport(label, ERROR_CODE, MESSAGE);

        report.addDetail("Label", label.getName());

        /**
         * Issue the error-report to the user.
         */
        report(report);
    }
}

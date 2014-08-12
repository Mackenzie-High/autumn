package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ast.commons.IBinaryOperation;
import autumn.lang.compiler.ast.commons.IConstruct;
import autumn.lang.compiler.ast.commons.IExpression;
import autumn.lang.compiler.ast.commons.IStatement;
import autumn.lang.compiler.ast.commons.IUnaryOperation;
import autumn.lang.compiler.ast.nodes.AsOperation;
import autumn.lang.compiler.ast.nodes.BreakStatement;
import autumn.lang.compiler.ast.nodes.ContinueStatement;
import autumn.lang.compiler.ast.nodes.ExceptionHandler;
import autumn.lang.compiler.ast.nodes.IsOperation;
import autumn.lang.compiler.ast.nodes.Label;
import autumn.lang.compiler.ast.nodes.Name;
import autumn.lang.compiler.ast.nodes.RedoStatement;
import autumn.lang.compiler.ast.nodes.TypeSpecifier;
import autumn.lang.compiler.ast.nodes.Variable;
import autumn.lang.compiler.errors.ErrorCode;
import autumn.lang.compiler.errors.ErrorReport;
import autumn.lang.compiler.errors.IErrorReporter;
import high.mackenzie.autumn.lang.compiler.exceptions.TypeCheckFailed;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IEnumType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IExpressionType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IReturnType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;
import high.mackenzie.autumn.lang.compiler.utils.Utils;
import java.util.List;

/**
 * An instance of this class provides methods which check for and report static type errors.
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

    /**
     * This method is invoked in order to actually issue an error report,
     * regarding a type-usage.
     *
     * @param report is the error report to issue.
     * @throws TypeCheckFailed always.
     */
    private void report(final ErrorReport report)
    {
        reporter.reportFailedCheck(report);
        throw new TypeCheckFailed();
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

        final ErrorCode ERROR_CODE = ErrorCode.VALUE_REQUIRED;

        final String MESSAGE = "The type of an expression is void when it is forbidden to be.";

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

    /**
     * This method ensures that the static-type of an expression is a reference-type.
     *
     * @param expression is the expression that must be a reference-type.
     */
    public void requireReferenceType(final IExpression expression)
    {
        final IExpressionType actual = program.symbols.expressions.get(expression);

        if (actual.isReferenceType())
        {
            return;
        }

        final ErrorCode ERROR_CODE = ErrorCode.EXPECTED_REFERENCE_TYPE;

        final String MESSAGE = "The type of a particular expression must be a reference-type.";

        final ErrorReport report = new ErrorReport(expression, ERROR_CODE, MESSAGE);

        /**
         * Issue the error-report to the user.
         */
        report(report);
    }

    /**
     * This method ensures that a type is a variable-type.
     *
     * @param construct is the construct that is performing the type-check.
     * @param expression is the expression that must be a variable-type.
     */
    public void requireVariableType(final IConstruct construct,
                                    final IExpressionType type)
    {
        if (type instanceof IVariableType)
        {
            return;
        }

        final ErrorCode ERROR_CODE = ErrorCode.EXPECTED_VARIABLE_TYPE;

        final String MESSAGE = "A variable-type was expected.";

        final ErrorReport report = new ErrorReport(construct, ERROR_CODE, MESSAGE);

        /**
         * Issue the error-report to the user.
         */
        report(report);
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

    /**
     * This method ensures that a type-specifier specifies a Throwable type.
     *
     * @param specifier must specify a Throwable type.
     * @param type must be a subtype of Throwable.
     */
    public void requireThrowable(final TypeSpecifier specifier,
                                 final IType type)
    {
        if (type.isSubtypeOf(program.typesystem.utils.THROWABLE))
        {
            return;
        }

        final ErrorCode ERROR_CODE = ErrorCode.EXPECTED_THROWABLE;

        final String MESSAGE = "A java.lang.Throwable was expected.";

        final ErrorReport report = new ErrorReport(specifier, ERROR_CODE, MESSAGE);

        /**
         * Issue the error-report to the user.
         */
        report(report);
    }

    /**
     * This method ensures that a name is not a keyword.
     *
     * @param name is the name to check.
     */
    public void requireLegalName(final Name name)
    {
        if (Utils.isKeyword(name.getName()) == false)
        {
            return;
        }

        final ErrorCode ERROR_CODE = ErrorCode.ILLEGAL_NAME;

        final String MESSAGE = "A name cannot be a JVM keyword.";

        final ErrorReport report = new ErrorReport(name, ERROR_CODE, MESSAGE);

        report.addDetail("Name", name.getName());

        /**
         * Issue the error-report to the user.
         */
        report(report);
    }

    /**
     * This method ensures that a type-declaration is not a duplicate.
     *
     * @param type is the name of the type being declared.
     * @param descriptor is the type-descriptor of the type being declared.
     */
    public void requireNonDuplicateType(final Name type,
                                        final String descriptor)
    {
        if (program.typesystem.typefactory().findType(descriptor) == null)
        {
            return;
        }

        final ErrorCode ERROR_CODE = ErrorCode.DUPLICATE_TYPE;

        final String MESSAGE = "The fully-qualified name of a type must be unique.";

        final ErrorReport report = new ErrorReport(type, ERROR_CODE, MESSAGE);

        report.addDetail("Type", type.getName());

        /**
         * Issue the error-report to the user.
         */
        report(report);
    }

    /**
     * This method reports that a type is not accessible where it is used.
     *
     * @param specifier specifies the inaccessible type.
     * @param type is the type that is inaccessible.
     */
    public void reportInaccessibleType(final TypeSpecifier specifier,
                                       final IReturnType type)
    {
        final ErrorCode ERROR_CODE = ErrorCode.INACCESSIBLE_TYPE;

        final String MESSAGE = "The specified type is not accessible from where it is used.";

        final ErrorReport report = new ErrorReport(specifier, ERROR_CODE, MESSAGE);

        report.addDetail("Type", Utils.sourceName(type));

        /**
         * Issue the error-report to the user.
         */
        report(report);
    }

    /**
     * This method reports that a type inherits from itself.
     *
     * @param declaration is the declaration of the erroneous type.
     * @param type is the type-system representation of the erroneous type.
     */
    public void reportCircularInheritance(final IConstruct declaration,
                                          final IReturnType type)
    {
        final ErrorCode ERROR_CODE = ErrorCode.CIRCULAR_INHERITANCE;

        final String MESSAGE = "A type cannot be a subtype of itself.";

        final ErrorReport report = new ErrorReport(declaration, ERROR_CODE, MESSAGE);

        report.addDetail("Type", Utils.sourceName(type));

        /**
         * Issue the error-report to the user.
         */
        report(report);
    }

    /**
     * This method reports a duplicate enum-constant.
     *
     * @param enumeration is the type-system representation of the enum.
     * @param constant is the name of the duplicated enum-constant.
     */
    public void reportDuplicateEnumConstant(final IEnumType enumeration,
                                            final Name constant)
    {
        final ErrorCode ERROR_CODE = ErrorCode.DUPLICATE_CONSTANT;

        final String MESSAGE = "No two enum-constants, in the same enum, can share their name.";

        final ErrorReport report = new ErrorReport(constant, ERROR_CODE, MESSAGE);

        report.addDetail("Enum Type", Utils.sourceName(enumeration));

        report.addDetail("Constant", constant.getName());

        /**
         * Issue the error-report to the user.
         */
        report(report);
    }

    public void reportNoSuchAsConversion(final AsOperation conversion,
                                         final IReturnType type)
    {
        final IExpressionType actual = program.symbols.expressions.get(conversion.getValue());

        final ErrorCode ERROR_CODE = ErrorCode.IMPOSSIBLE_CONVERSION;

        final String MESSAGE = "An as-conversion is not possible.";

        final ErrorReport report = new ErrorReport(conversion, ERROR_CODE, MESSAGE);

        /**
         * Issue the error-report to the user.
         */
        report(report);
    }

    public void reportNoSuchIsConversion(final IsOperation conversion,
                                         final IReturnType type)
    {
        final IExpressionType actual = program.symbols.expressions.get(conversion.getValue());

        final ErrorCode ERROR_CODE = ErrorCode.IMPOSSIBLE_CONVERSION;

        final String MESSAGE = "An is-conversion is not possible.";

        final ErrorReport report = new ErrorReport(conversion, ERROR_CODE, MESSAGE);

        /**
         * Issue the error-report to the user.
         */
        report(report);
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

    public void reportNoSuchMethod(final IConstruct construct,
                                   final IReturnType owner,
                                   final String name,
                                   final List<IExpressionType> arguments)
    {
        final ErrorCode ERROR_CODE = ErrorCode.NO_SUCH_METHOD;

        final String MESSAGE = "No accessible method could be found that matches the given name and arguments.";

        final ErrorReport report = new ErrorReport(construct, ERROR_CODE, MESSAGE);

        report.addDetail("Method.Name", name);

        int i = 0;

        for (IExpressionType arg : arguments)
        {
            report.addDetail("Method.Argument[" + i++ + "]", Utils.sourceName(arg));
        }

        /**
         * Issue the error-report to the user.
         */
        report(report);
    }

    public void requireType(final TypeSpecifier specifier,
                            final IType type)
    {
        if (type != null)
        {
            return;
        }

        final ErrorCode ERROR_CODE = ErrorCode.NO_SUCH_TYPE;

        final String MESSAGE = "The specified type does not exist.";

        final ErrorReport report = new ErrorReport(specifier, ERROR_CODE, MESSAGE);

        report.addDetail("Type", program.typesystem.utils.extractTypeName(specifier));

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

    /**
     * This method reports that a unary operator cannot be applied to its operand.
     *
     * @param operation is the unary operation itself.
     */
    public void reportNoSuchUnaryOperator(final IUnaryOperation operation,
                                          final IExpressionType operand)
    {
        final ErrorCode ERROR_CODE = ErrorCode.NO_SUCH_UNARY_OPERATOR;

        final String MESSAGE = "A unary operator cannot be applied to the given operand type.";

        final ErrorReport report = new ErrorReport(operation, ERROR_CODE, MESSAGE);

        report.addDetail("operand.type", Utils.sourceName(operand));

        /**
         * Issue the error-report to the user.
         */
        report(report);
    }

    /**
     * This method reports that a binary operator cannot be applied to its operands.
     *
     * @param operation is the binary operation itself.
     */
    public void reportNoSuchBinaryOperator(final IBinaryOperation operation,
                                           final IExpressionType left,
                                           final IExpressionType right)
    {
        final ErrorCode ERROR_CODE = ErrorCode.NO_SUCH_BINARY_OPERATOR;

        final String MESSAGE = "A binary operator cannot be applied to the given operand types.";

        final ErrorReport report = new ErrorReport(operation, ERROR_CODE, MESSAGE);

        report.addDetail("left.type  ", Utils.sourceName(left));
        report.addDetail("right.type ", Utils.sourceName(right));

        /**
         * Issue the error-report to the user.
         */
        report(report);
    }
}

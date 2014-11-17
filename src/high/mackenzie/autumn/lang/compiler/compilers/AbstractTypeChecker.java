package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.annotations.InferReturnType;
import autumn.lang.compiler.ast.commons.IBinaryOperation;
import autumn.lang.compiler.ast.commons.IExpression;
import autumn.lang.compiler.ast.commons.IUnaryOperation;
import autumn.lang.compiler.ast.nodes.Name;
import autumn.lang.compiler.ast.nodes.TypeSpecifier;
import autumn.lang.compiler.ast.nodes.Variable;
import autumn.lang.internals.Operators;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IClassType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IDeclaredType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IExpressionType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IField;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IReturnType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IType;
import high.mackenzie.autumn.lang.compiler.utils.TypeSystemUtils;
import java.util.Collections;
import java.util.List;

/**
 * This class provides methods needed by all type-checkers.
 *
 * @author Mackenzie High
 */
abstract class AbstractTypeChecker
        extends AbstractAstVisitor
{
    /**
     * Essentially, this method retrieves the enclosing function.
     *
     * @return the enclosing function compiler.
     */
    public abstract AbstractFunctionCompiler function();

    /**
     * This is the compiler that is responsible for compiling the enclosing program.
     */
    protected final ProgramCompiler program;

    /**
     * This is the compiler that is responsible for compiling the enclosing module.
     */
    protected final ModuleCompiler module;

    /**
     * Sole Constructor.
     *
     * @param function is the compiler that is responsible for compiling the enclosing function.
     */
    protected AbstractTypeChecker(final AbstractFunctionCompiler function)
    {
        Preconditions.checkNotNull(function);

        this.program = function.module.program;
        this.module = function.module;
    }

    /**
     * This method binds a type to an abstract-syntax-tree node.
     *
     * @param node is the AST node that is being typed.
     * @param type is the type of the construct represented by the AST node.
     */
    protected void infer(final IExpression node,
                         final IExpressionType type)
    {
        Preconditions.checkNotNull(node);
        Preconditions.checkNotNull(type);

        program.symbols.expressions.put(node, type);
    }

    /**
     * This method type-checks a condition expression.
     *
     * <p>
     * If the type-check fails, an error-report will be issued by the compiler.
     * </p>
     *
     * @param expression is the expression that is a condition.
     */
    protected void condition(final IExpression expression)
    {
        Preconditions.checkNotNull(expression);

        expression.accept(this);

        program.checker.checkCondition(expression);
    }

    /**
     * This method requires that the type of an expression is a subtype of a given type.
     *
     * <p>
     * If the type-check fails, an error-report will be issued by the compiler.
     * </p>
     *
     * @param expected is the expected type of the expression.
     * @param expression is the expression to type-check.
     */
    protected void assertType(final IReturnType expected,
                              final IExpression expression)
    {
        expression.accept(this);

        program.checker.expectSubtype(expression, expected);
    }

    /**
     * This method generalizes the resolution and type-checking of a unary operator.
     *
     * <p>
     * If the type-checking fails, an error-report will be issued by the compiler.
     * </p>
     *
     * @param operation is the unary operation itself.
     * @param function is the name of the static utility method that implements the operation.
     * @param operand is the only operand of the operation.
     */
    protected void unaryOperation(final IUnaryOperation operation,
                                  final String function,
                                  final IExpression operand)
    {
        Preconditions.checkNotNull(operation);
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(operand);

        // Perform type-checking on the left-operand.
        operand.accept(this);
        final IExpressionType operand_type = program.symbols.expressions.get(operand);

        // This is the type of the Operators class, which provides the operation implementations.
        final IDeclaredType owner = (IDeclaredType) program.typesystem
                .typefactory()
                .fromClass(Operators.class);

        // Resolve the applicable overloads of the operator method.
        final List<IMethod> overloads = program.typesystem.utils.resolveStaticMethods(module.type,
                                                                                      owner,
                                                                                      function,
                                                                                      Collections.<IType>singletonList(operand_type));

        // If no applicable overload was found,
        // then issue an error-report,
        // because a bad operand was provided by the user.
        if (overloads.isEmpty())
        {
            // This metohd will throw an exception.
            program.checker.reportNoSuchUnaryOperator(operation, operand_type);
        }

        // The first method is the most specific.
        final IMethod overload = overloads.get(0);

        // Remember the overload, because the code generator will need it later.
        program.symbols.calls.put(operation, overload);

        // The return-type of the selected overload is the return-type of the expression.
        infer(operation, overload.getReturnType());
    }

    /**
     * This method generalizes the resolution and type-checking of a binary operator.
     *
     * <p>
     * If the type-checking fails, an error-report will be issued by the compiler.
     * </p>
     *
     * @param operation is the binary operation itself.
     * @param function is the name of the static utility method that implements the operation.
     * @param operand is the only operand of the operation.
     */
    protected void binaryOperation(final IBinaryOperation operation,
                                   final String function,
                                   final IExpression left,
                                   final IExpression right)
    {
        Preconditions.checkNotNull(operation);
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(left);
        Preconditions.checkNotNull(right);

        // These are the types of the operands.
        final List<IType> operands = Lists.newLinkedList();

        // Perform type-checking on the left-operand.
        left.accept(this);
        final IExpressionType left_type = program.symbols.expressions.get(left);
        operands.add(left_type);

        // Perform type-checking on the right-operand.
        right.accept(this);
        final IExpressionType right_type = program.symbols.expressions.get(right);
        operands.add(right_type);

        // This is the type of the Operators class, which provides the operation implementations.
        final IDeclaredType owner = (IDeclaredType) program.typesystem
                .typefactory()
                .fromClass(Operators.class);

        // Resolve the applicable overloads of the operator method.
        final List<IMethod> overloads = program.typesystem.utils.resolveStaticMethods(module.type,
                                                                                      owner,
                                                                                      function,
                                                                                      operands);

        // If no applicable overload was found,
        // then issue an error-report,
        // because bad operands were provided by the user.
        if (overloads.isEmpty())
        {
            // This metohd will throw an exception.
            program.checker.reportNoSuchBinaryOperator(operation, left_type, right_type);
        }

        // The first method is the most specific.
        final IMethod overload = overloads.get(0);

        // Remember the overload, because the code generator will need it later.
        program.symbols.calls.put(operation, overload);

        // The return-type of the selected overload is the return-type of the expression.
        infer(operation, overload.getReturnType());
    }

    /**
     * This method generalizes the resolution and type-checking of a static method invocation.
     *
     * @param operation is the invocation expression himself.
     * @param owner specifies the type that declares the invoked method.
     * @param name is the name of the invoked method.
     * @param arguments are the expressions that produce the invoked method's arguments.
     */
    protected void callStaticMethod(final IExpression operation,
                                    final TypeSpecifier owner,
                                    final Name name,
                                    final Iterable<IExpression> arguments)
    {
        /**
         * Type-check the owner.
         */
        final IDeclaredType owner_type = module.imports.resolveDeclaredType(owner);

        /**
         * Visit and type-check the arguments.
         */
        final List<IExpressionType> args = Lists.newLinkedList();

        for (IExpression arg : arguments)
        {
            arg.accept(this);
            args.add(program.symbols.expressions.get(arg));
        }

        /**
         * Resolve the method overload.
         */
        final List<IMethod> methods = program.typesystem.utils.resolveStaticMethods(module.type,
                                                                                    owner_type,
                                                                                    name.getName(),
                                                                                    args);

        /**
         * If no method overload could be found, issue an error.
         */
        if (methods.isEmpty())
        {
            program.checker.reportNoSuchMethod(operation, true, owner_type, name.getName(), args);
        }

        /**
         * Remember the resolved method overload, because the code-generator will need it.
         */
        final IMethod method = (IMethod) methods.get(0);
        program.symbols.calls.put(operation, method);

        /**
         * The return-type of a method-invocation is usually the return-type of the invoked method.
         * However, sometimes the return-type of the method-invocation is inferred.
         *
         * Note: We cannot infer the return-type when there are no arguments, even if requested.
         */
        if (method.getParameters().isEmpty())
        {
            infer(operation, method.getReturnType());
        }
        else if (TypeSystemUtils.isAnnotationPresent(method, InferReturnType.class))
        {
            infer(operation, args.get(0));
        }
        else
        {
            infer(operation, method.getReturnType());
        }
    }

    /**
     * This method performs the resolution of a static-field.
     *
     * @param site is the site where the field is being used.
     * @param type is the type that contains the field.
     * @param name is the name of the field.
     * @return the field that was found, or null, if no field was found.
     */
    protected IField findStaticField(final IExpression site,
                                     final TypeSpecifier type,
                                     final String name)
    {
        /**
         * Get the type of the enclosing module in order to perform access checking.
         */
        final IClassType user = module.type;

        /**
         * Get the type that owns the field.
         *
         * This will throw an exception, if the type does not exist
         * or type is not a declared-type.
         */
        final IDeclaredType owner = module.imports.resolveDeclaredType(type);

        /**
         * Resolve the field.
         */
        final IField field = program.typesystem.utils.resolveStaticField(user,
                                                                         owner,
                                                                         name);

        /**
         * If no field was found, then issue an error.
         */
        if (field == null)
        {
            // This will throw an exception.
            program.checker.reportNoSuchField(site, true, owner, name);
        }

        /**
         * Remember the field that is being set, so that the code-generator can emit bytecode.
         */
        program.symbols.fields.put(site, field);

        return field;
    }

    /**
     * This method performs the resolution of an instance-field.
     *
     * @param site is the site where the field is being used.
     * @param object is the expression that produces the owner of the field.
     * @param name is the name of the field.
     * @return the field that was found, or null, if no field was found.
     */
    protected IField findField(final IExpression site,
                               final IExpression object,
                               final String name)
    {
        /**
         * Get the type of the enclosing module in order to perform access checking.
         */
        final IClassType user = module.type;

        /**
         * Get the type that owns the field.
         */
        final IExpressionType owner_expr_type = program.symbols.expressions.get(object);

        // This will throw an exception, if the owner-type is not a declared-type.
        program.checker.requireDeclaredType(object, owner_expr_type);

        // This never fails.
        final IDeclaredType owner = (IDeclaredType) owner_expr_type;

        /**
         * Resolve the field.
         */
        final IField field = program.typesystem.utils.resolveField(user,
                                                                   owner,
                                                                   name);

        /**
         * If no field was found, then issue an error.
         */
        if (field == null)
        {
            // This will throw an exception.
            program.checker.reportNoSuchField(site, false, owner, name);
        }

        /**
         * Remember the field that is being set, so that the code-generator can emit bytecode.
         */
        program.symbols.fields.put(site, field);

        return field;
    }

    /**
     * This method declares a local-variable and type-checks the declaration.
     *
     * @param variable is the variable to declare.
     * @param type is the type of the new variable.
     * @param mutable is false, iff the variable is readonly.
     */
    protected void declareVar(final Variable variable,
                              final IExpressionType type,
                              final boolean mutable)
    {
        /**
         * The variable cannot be declared, if another variable was already created
         * with the same name.
         */
        final boolean alread_declared = function().allocator.isDeclared(variable.getName());
        program.checker.reportDuplicateVariable(variable, alread_declared);

        /**
         * The type of a variable must be a variable-type.
         */
        program.checker.requireVariableType(variable, type);

        /**
         * Perform the actual variable declaration.
         */
        if (mutable)
        {
            function().allocator.declareVar(variable.getName(), type);
        }
        else
        {
            function().allocator.declareVal(variable.getName(), type);
        }
    }
}

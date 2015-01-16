package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ast.commons.IExpression;
import autumn.lang.compiler.ast.commons.IStatement;
import autumn.lang.compiler.ast.nodes.*;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import high.mackenzie.autumn.lang.compiler.exceptions.TypeCheckFailed;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IClassType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IExpressionType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IReferenceType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;
import high.mackenzie.autumn.lang.compiler.utils.DelegateToHandler;
import high.mackenzie.autumn.lang.compiler.utils.TopoSorter;
import java.util.List;
import java.util.Set;

/**
 * An instance of this class performs type usage checking on a statement.
 *
 * @author Mackenzie High
 */
public final class StatementTypeChecker
        extends ExpressionTypeChecker
{
    private int loop_nesting_level = 0;

    private final FunctionCompiler function;

    public StatementTypeChecker(final FunctionCompiler function)
    {
        super(function, function.allocator);

        this.function = function;
    }

    @Override
    public void visit(final IfStatement object)
    {
        // Visit the conditional case that is always present.
        object.getMainCase().accept(this);

        // Visit the elif-cases, if there are any elif-clauses present.
        if (object.getElifCases() != null)
        {
            for (ConditionalCase x : object.getElifCases())
            {
                x.accept(this);
            }
        }

        // Visit the else-clause, if there is an else-clause present.
        if (object.getElseCase() != null)
        {
            object.getElseCase().accept(this);
        }
    }

    @Override
    public void visit(final ConditionalCase object)
    {
        // Visit and type-check the condition.
        condition(object.getCondition());

        // Visit the body.
        object.getBody().accept(this);
    }

    @Override
    public void visit(final WhenStatement object)
    {
        // Visit and type-check the condition.
        condition(object.getCondition());

        // Visit the body.
        object.getBody().accept(this);
    }

    @Override
    public void visit(final GotoStatement object)
    {
        /**
         * The checking of the labels will be done later.
         * This prevents the need for another compiler-pass.
         */
        function.labels.defer(object);
    }

    @Override
    public void visit(final MarkerStatement object)
    {
        /**
         * The checking of the labels will be done later.
         * This prevents the need for another compiler-pass.
         */
        function.labels.defer(object);
    }

    @Override
    public void visit(final BranchStatement object)
    {
        /**
         * The checking of the labels will be done later.
         * This prevents the need for another compiler-pass.
         */
        function.labels.defer(object);

        /**
         * However, we can check the index now.
         */
        object.getIndex().accept(this);
        program.checker.requireInteger(object.getIndex());
    }

    @Override
    public void visit(final ForeverStatement object)
    {
        /**
         * Visit the body.
         */
        ++loop_nesting_level;
        {
            object.getBody().accept(this);
        }
        --loop_nesting_level;
    }

    @Override
    public void visit(final WhileStatement object)
    {
        /**
         * Visit and type-check the condition.
         */
        condition(object.getCondition());

        /**
         * Visit the body.
         */
        ++loop_nesting_level;
        {
            object.getBody().accept(this);
        }
        --loop_nesting_level;
    }

    @Override
    public void visit(final UntilStatement object)
    {
        /**
         * Visit and type-check the condition.
         */
        condition(object.getCondition());

        /**
         * Visit the body.
         */
        ++loop_nesting_level;
        {
            object.getBody().accept(this);
        }
        --loop_nesting_level;
    }

    @Override
    public void visit(final DoWhileStatement object)
    {
        /**
         * Visit and type-check the condition.
         */
        condition(object.getCondition());

        /**
         * Visit the body.
         */
        ++loop_nesting_level;
        {
            object.getBody().accept(this);
        }
        --loop_nesting_level;
    }

    @Override
    public void visit(final DoUntilStatement object)
    {
        /**
         * Visit and type-check the condition.
         */
        condition(object.getCondition());

        /**
         * Visit the body.
         */
        ++loop_nesting_level;
        {
            object.getBody().accept(this);
        }
        --loop_nesting_level;
    }

    @Override
    public void visit(final ForStatement object)
    {
        /**
         * Visit and type-check the initializer.
         *
         * Warning: Do *not* move this code into the following try-catch.
         */
        object.getInitializer().accept(this);
        program.checker.requireInteger(object.getInitializer());

        try
        {
            /**
             * A for-loop defines a nested scope that covers the condition, modifier, and body.
             */
            allocator.enterScope();

            /**
             * Declare the control variable.
             */
            super.declareVar(object.getVariable(), program.typesystem.utils.PRIMITIVE_INT, false);

            /**
             * Visit and type-check the condition.
             */
            condition(object.getCondition());

            /**
             * Visit and type-check the modifier.
             */
            object.getNext().accept(this);
            program.checker.requireInteger(object.getNext());

            /**
             * Visit the body.
             */
            ++loop_nesting_level;
            {
                object.getBody().accept(this);
            }
            --loop_nesting_level;
        }
        catch (TypeCheckFailed ex)
        {
            throw ex;
        }
        finally
        {
            /**
             * This must always be done; otherwise, the scope management could get messed up.
             */
            allocator.exitScope();
        }
    }

    @Override
    public void visit(final ForeachStatement object)
    {
        /**
         * Visit and type-check the iterable.
         *
         * Warning: Do *not* move this code into the following try-catch.
         */
        object.getIterable().accept(this);
        program.checker.requireIterable(object.getIterable());

        try
        {
            /**
             * A foreach-loop defines a nested scope that covers body.
             */
            allocator.enterScope();

            /**
             * Declare the variable and type-check the type.
             */
            final Variable variable = object.getVariable();
            final IReferenceType type = function.module.imports.resolveReferenceType(object.getType());
            super.declareVar(variable, type, false);

            /**
             * Visit the body.
             */
            ++loop_nesting_level;
            {
                object.getBody().accept(this);
            }
            --loop_nesting_level;
        }
        catch (TypeCheckFailed ex)
        {
            throw ex;
        }
        finally
        {
            /**
             * This must always be done; otherwise, the scope management could get messed up.
             */
            allocator.exitScope();
        }
    }

    @Override
    public void visit(final BreakStatement object)
    {
        /**
         * Ensure that the break-statement is actually inside of a loop.
         */
        if (loop_nesting_level == 0)
        {
            program.checker.reportBreakOutsideOfLoop(object);
        }
    }

    @Override
    public void visit(final ContinueStatement object)
    {
        /**
         * Ensure that the continue-statement is actually inside of a loop.
         */
        if (loop_nesting_level == 0)
        {
            program.checker.reportContinueOutsideOfLoop(object);
        }
    }

    @Override
    public void visit(final RedoStatement object)
    {
        /**
         * Ensure that the redo-statement is actually inside of a loop.
         */
        if (loop_nesting_level == 0)
        {
            program.checker.reportRedoOutsideOfLoop(object);
        }
    }

    @Override
    public void visit(final VarStatement object)
    {
        /**
         * Visit and type-check the expression.
         */
        object.getValue().accept(this);

        /**
         * Declare the assignee.
         */
        final Variable assignee = object.getVariable();

        final IExpression value = object.getValue();

        final IExpressionType type = program.symbols.expressions.get(value);

        super.declareVar(assignee, type, true);
    }

    @Override
    public void visit(final ValStatement object)
    {
        /**
         * Visit and type-check the expression.
         */
        object.getValue().accept(this);

        /**
         * Declare the assignee.
         */
        final Variable assignee = object.getVariable();

        final IExpression value = object.getValue();

        final IExpressionType type = program.symbols.expressions.get(value);

        super.declareVar(assignee, type, false);
    }

    @Override
    public void visit(final LetStatement object)
    {
        /**
         * Generate the bytecode that produces the value.
         */
        object.getValue().accept(this);

        /**
         * The variable must have been declared somewhere.
         */
        program.checker.checkDeclared(function.allocator, object.getVariable());

        /**
         * The variable cannot be readonly.
         */
        final boolean readonly = !function.vars.allocator().isReadOnly(object.getVariable().getName());

        program.checker.requireMutableVariable(object.getVariable(), readonly);

        /**
         * The assignment must actually be possible type wise.
         */
        final IVariableType expected = function.allocator.typeOf(object.getVariable().getName());

        program.checker.checkAssign(object, expected, object.getValue());
    }

    @Override
    public void visit(final LambdaStatement object)
    {
        /**
         * Get the type of the lambda.
         */
        final IClassType functor_type = module.imports.resolveClassType(object.getType());

        /**
         * Declare the lambda variable.
         */
        super.declareVar(object.getVariable(), functor_type, false);

        try
        {
            /**
             * A lambda-statement defines a nested scope that covers its parameters and body.
             */
            allocator.enterScope();

            /**
             * Create the lambda itself.
             */
            final LambdaCompiler lambda = new LambdaCompiler(function, object);

            /**
             * Visit and type-check the lambda's body.
             */
            lambda.performTypeUsageChecking();

            /**
             * Save the lambda-compiler, because it will generate the lambda's bytecode later.
             */
            program.symbols.lambdas.put(object, lambda);
        }
        catch (TypeCheckFailed ex)
        {
            throw ex;
        }
        finally
        {
            /**
             * This must always be done; otherwise, the scope management could get messed up.
             */
            allocator.exitScope();
        }
    }

    @Override
    public void visit(final DelegateStatement object)
    {
        /**
         * Get the assignee variable.
         */
        final Variable assignee = object.getVariable();

        /**
         * Get the name of the function that will be delegated to.
         */
        final String name = object.getMethod().getName();

        /**
         * Get the type of the delegate itself.
         */
        final IClassType functor_type = module.imports.resolveDefinedFunctorType(object.getType());

        /**
         * The functor-type must also be a class-type.
         */
        program.checker.requireClassType(object, functor_type);

        /**
         * Get the type of the module that contains the function that will be delegated to.
         */
        final IClassType owner_type = module.imports.resolveModuleType(object.getOwner());

        /**
         * Resolve the method that will be delegated to.
         */
        final DelegateToHandler mapping = DelegateToHandler.find(functor_type, owner_type, name);

        if (mapping.error == DelegateToHandler.Errors.NO_SUCH_METHOD)
        {
            // This will throw an exception.
            program.checker.reportNoSuchMethod(object, owner_type, name);
        }
        else if (mapping.error == DelegateToHandler.Errors.OVERLOADED_METHOD)
        {
            // This will throw an exception.
            program.checker.reportOverloadedMethod(object, owner_type, name);
        }
        else if (mapping.error == DelegateToHandler.Errors.INCOMPATIBLE_METHOD)
        {
            // This will throw an exception.
            program.checker.reportIncompatibleDelegate(object, mapping.delegate, mapping.handler);
        }

        /**
         * Declare the assignee variable as a readonly local variable.
         */
        super.declareVar(assignee, functor_type, false);

        /**
         * Remember the delegate-method, because it will be needed during code generation.
         */
        program.symbols.delegates.put(object, mapping.handler);
    }

    @Override
    public void visit(final SequenceStatement object)
    {
        allocator.enterScope();

        for (IStatement s : object.getElements())
        {
            /**
             * The following code must be in a try-catch.
             * Otherwise, an exception would mess up the scope management.
             */
            try
            {
                s.accept(this);
            }
            catch (TypeCheckFailed ex)
            {
                // Pass in order to report as many errors as possible.
            }
        }

        allocator.exitScope();
    }

    @Override
    public void visit(final ExpressionStatement object)
    {
        /**
         * Visit and type-check the expression itself.
         */
        object.getExpression().accept(this);
    }

    @Override
    public void visit(final NopStatement object)
    {
        // Pass, because there is nothing to check.
    }

    @Override
    public void visit(final TryCatchStatement object)
    {
        /**
         * Visit and type-check the body.
         */
        object.getBody().accept(this);

        /**
         * Visit and type-check all of the handlers.
         */
        object.getHandlers().accept(this);

        /**
         * Sort the handlers based on the type of exception they catch.
         * For example, a handler that catches Exception should come before
         * a handler that catches Throwable, because Exception is more specific
         * than Throwable.
         */
        final TopoSorter<ExceptionHandler> sorter = new TopoSorter<ExceptionHandler>()
        {
            @Override
            public boolean isLess(final ExceptionHandler left,
                                  final ExceptionHandler right)
            {
                final IType left_type = function.module.imports.resolveReturnType(left.getType());
                final IType right_type = function.module.imports.resolveReturnType(right.getType());

                return left_type.isSubtypeOf(right_type);
            }
        };

        sorter.addAll(object.getHandlers());

        final List<ExceptionHandler> sorted = ImmutableList.copyOf(sorter.elements());

        /**
         * Remember the sorted handlers, so the code-generator can generate them in sorted order.
         */
        program.symbols.handlers.put(object, sorted);

        /**
         * Detect duplicate exception handlers.
         */
        final Set<IExpressionType> types = Sets.newHashSet();

        for (ExceptionHandler handler : sorted)
        {
            final IExpressionType type = function.module.imports.resolveReturnType(handler.getType());

            if (types.contains(type))
            {
                program.checker.reportDuplicateExceptionHandler(handler, type);
            }

            types.add(type);
        }
    }

    @Override
    public void visit(final ExceptionHandler object)
    {
        /**
         * Get the type of exception that the handler will handle.
         */
        final IExpressionType type = function.module.imports.resolveReturnType(object.getType());

        try
        {
            /**
             * The handler defines a scope that covers the body of the handler.
             */
            allocator.enterScope();

            /**
             * The handler can only be used to catch exception-types.
             */
            program.checker.requireType(object.getType(), type);
            program.checker.requireThrowable(object.getType(), type);

            /**
             * Declare the exception variable.
             */
            super.declareVar(object.getVariable(), type, false);

            /**
             * Visit and type-check the body of the handler.
             */
            object.getBody().accept(this);
        }
        catch (TypeCheckFailed ex)
        {
            throw ex;
        }
        finally
        {
            /**
             * This must always be done; otherwise, the scope management could get messed up.
             */
            allocator.exitScope();
        }
    }

    @Override
    public void visit(final ThrowStatement object)
    {
        object.getValue().accept(this);

        // A throw-statement can only throw throwable objects.
        program.checker.requireThrowable(object.getValue());
    }

    @Override
    public void visit(final AssertStatement object)
    {
        object.getCondition().accept(this);

        // The condition must produce a some sort of boolean.
        condition(object.getCondition());

        // The message, if present, must produce a string.
        if (object.getMessage() != null)
        {
            object.getMessage().accept(this);
            program.checker.requireString(object.getMessage());
        }
    }

    @Override
    public void visit(final AssumeStatement object)
    {
        object.getCondition().accept(this);

        // The condition must produce a some sort of boolean.
        condition(object.getCondition());

        // The message, if present, must produce a string.
        if (object.getMessage() != null)
        {
            object.getMessage().accept(this);
            program.checker.requireString(object.getMessage());
        }
    }

    @Override
    public void visit(final ReturnVoidStatement object)
    {
        /**
         * Only a function whose return-type is void can contain a return-void statement.
         */
        program.checker.requireVoid(object, function.type.getReturnType());
    }

    @Override
    public void visit(final ReturnValueStatement object)
    {
        /**
         * Visit and type-check the expression that produces the value to return.
         */
        object.getValue().accept(this);

        /**
         * The expression must return a non void value.
         */
        program.checker.requireNonVoid(object.getValue());

        /**
         * The type of the expression must be assignable to the return-type of the function.
         */
        program.checker.checkReturn(object, function.type.getReturnType(), object.getValue());
    }

    @Override
    public void visit(final RecurStatement object)
    {
        /**
         * The number of arguments must match the number of parameters.
         */
        if (function.type.getParameters().size() != object.getArguments().size())
        {
            // This will throw an exception.
            program.checker.reportBadArgumentCount(object, function.type.getParameters().size());
        }

        /**
         * Each argument must be assignable to the related parameter.
         */
        for (int i = 0; i < object.getArguments().size(); i++)
        {
            /**
             * Get the argument expression.
             */
            final IExpression argument = object.getArguments().get(i);

            /**
             * Get the type of the related parameter.
             */
            final IVariableType parameter = function.type.getParameters().get(i).getType();

            /**
             * Visit and type-check the argument.
             */
            argument.accept(this);

            /**
             * The argument must be assignable to the parameter.
             */
            program.checker.checkAssign(object, parameter, argument);
        }
    }
}

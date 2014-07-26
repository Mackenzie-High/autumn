/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ast.commons.IExpression;
import autumn.lang.compiler.ast.commons.IStatement;
import autumn.lang.compiler.ast.nodes.*;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IExpressionType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IReturnType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;
import high.mackenzie.autumn.lang.compiler.utils.TopoSorter;
import java.util.List;
import java.util.Set;
import org.objectweb.asm.tree.LabelNode;

/**
 * An instance of this class performs type usage checking on a statement.
 *
 * @author Mackenzie High
 */
public final class StatementTypeChecker
        extends ExpressionTypeChecker
{
    private int loop_nesting_level = 0;

    public StatementTypeChecker(final FunctionCompiler function)
    {
        super(function, function.scope);
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
        // The type-checking of goto-statements will be done later.
        // This prevents the need for another compiler-pass.
        function.labels.defer(object);
    }

    @Override
    public void visit(final MarkerStatement object)
    {
        // The type-checking of marker-statements will be done later.
        // This prevents the need for another compiler-pass.
        function.labels.defer(object);
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
    public void visit(final ForeverStatement object)
    {
        ++loop_nesting_level;
        {
            object.getBody().accept(this);
        }
        --loop_nesting_level;
    }

    @Override
    public void visit(final WhileStatement object)
    {
        condition(object.getCondition());

        ++loop_nesting_level;
        {
            object.getBody().accept(this);
        }
        --loop_nesting_level;
    }

    @Override
    public void visit(final UntilStatement object)
    {
        condition(object.getCondition());

        ++loop_nesting_level;
        {
            object.getBody().accept(this);
        }
        --loop_nesting_level;
    }

    @Override
    public void visit(final DoWhileStatement object)
    {
        condition(object.getCondition());

        ++loop_nesting_level;
        {
            object.getBody().accept(this);
        }
        --loop_nesting_level;
    }

    @Override
    public void visit(final DoUntilStatement object)
    {
        condition(object.getCondition());

        ++loop_nesting_level;
        {
            object.getBody().accept(this);
        }
        --loop_nesting_level;
    }

    @Override
    public void visit(final ForStatement object)
    {
        function.scope.declareVar(object.getVariable(), program.typesystem.utils.PRIMITIVE_INT);

        object.getInitializer().accept(this);
        object.getCondition().accept(this);
        object.getNext().accept(this);
        object.getBody().accept(this);

        program.checker.checkCondition(object.getCondition());

        // TODO: Type Checking
    }

    @Override
    public void visit(final ForeachStatement object)
    {
        final Variable variable = object.getVariable();
        final IExpressionType type = function.module.resolveType(object.getType());

        function.scope.declareVal(variable, type);

        object.getIterable().accept(this);
        object.getBody().accept(this);

        program.checker.requireIterable(object.getIterable());
    }

    @Override
    public void visit(final BreakStatement object)
    {
        if (loop_nesting_level == 0)
        {
            program.checker.reportBreakOutsideOfLoop(object);
        }
    }

    @Override
    public void visit(final ContinueStatement object)
    {
        if (loop_nesting_level == 0)
        {
            program.checker.reportContinueOutsideOfLoop(object);
        }
    }

    @Override
    public void visit(final RedoStatement object)
    {
        if (loop_nesting_level == 0)
        {
            program.checker.reportRedoOutsideOfLoop(object);
        }
    }

    @Override
    public void visit(final VarStatement object)
    {
        object.getValue().accept(this);

        // TODO: Require that the type of the initializer is a variable-type.
        //       Expressions can be of the void-type or the null-type.
        //       Neither of those two types() can be the type of a variable.

        final Variable variable = object.getVariable();

        final IExpression value = object.getValue();

        final IVariableType type = (IVariableType) program.symbols.expressions.get(value);

        final boolean alread_declared = function.scope.isDeclared(variable.getName());
        program.checker.reportDuplicateVariable(variable, alread_declared);

        function.scope.declareVar(variable, type);
    }

    @Override
    public void visit(final ValStatement object)
    {
        object.getValue().accept(this);

        // TODO: Require that the type of the initializer is a variable-type.
        //       Expressions can be of the void-type or the null-type.
        //       Neither of those two types() can be the type of a variable.

        final Variable variable = object.getVariable();

        final IExpression value = object.getValue();

        final IVariableType type = (IVariableType) program.symbols.expressions.get(value);

        final boolean alread_declared = function.scope.isDeclared(variable.getName());
        program.checker.reportDuplicateVariable(variable, alread_declared);

        function.scope.declareVal(variable, type);
    }

    @Override
    public void visit(final LetStatement object)
    {
        object.getValue().accept(this);

        program.checker.checkDeclared(function.scope, object.getVariable());

        final IVariableType expected = function.scope.typeOf(object.getVariable().getName());

        checkAssign(expected, object.getValue());
    }

    @Override
    public void visit(final LambdaStatement object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(final DelegateStatement object)
    {
        throw new UnsupportedOperationException("This should never happen.");
    }

    @Override
    public void visit(final SetterStatement object)
    {
        object.getOwner().accept(this);
    }

    @Override
    public void visit(final GetterStatement object)
    {
        object.getOwner().accept(this);
    }

    @Override
    public void visit(final MethodStatement object)
    {
        object.getOwner().accept(this);
    }

    @Override
    public void visit(final SequenceStatement object)
    {
        for (IStatement s : object.getElements())
        {
            try
            {
                s.accept(this);
            }
            catch (Exception ex)
            {
                // Pass.
            }
        }
    }

    @Override
    public void visit(final ExpressionStatement object)
    {
        object.getExpression().accept(this);
    }

    @Override
    public void visit(final NopStatement object)
    {
        // Pass
    }

    @Override
    public void visit(final DebugStatement object)
    {
        // Pass
    }

    @Override
    public void visit(final TryCatchStatement object)
    {
        object.getBody().accept(this);
        object.getHandlers().accept(this);

        final TopoSorter<ExceptionHandler> sorter = new TopoSorter<ExceptionHandler>()
        {
            @Override
            public boolean isLess(final ExceptionHandler left,
                                  final ExceptionHandler right)
            {
                final IType left_type = function.module.resolveType(left.getType());
                final IType right_type = function.module.resolveType(right.getType());

                return left_type.isSubtypeOf(right_type);
            }
        };

        sorter.addAll(object.getHandlers());

        final List<ExceptionHandler> sorted = ImmutableList.copyOf(sorter.elements());

        program.symbols.handlers.put(object, sorted);

        /**
         * Detect duplicate exception handlers.
         */
        final Set<IExpressionType> types = Sets.newHashSet();

        for (ExceptionHandler handler : sorted)
        {
            final IExpressionType type = function.module.resolveType(handler.getType());

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
        final IExpressionType type = function.module.resolveType(object.getType());

        final boolean already_declared = function.scope.isDeclared(object.getVariable().getName());

        program.checker.reportNoSuchType(object.getType(), type);
        program.checker.reportDuplicateVariable(object.getVariable(), already_declared);
        program.checker.requireThrowable(object, type);

        function.scope.declareVal(object.getVariable(), type);

        object.getHandler().accept(this);
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
        if (function.isReturnTypeVoid() == false)
        {
            // Error
        }
    }

    @Override
    public void visit(final ReturnValueStatement object)
    {
        object.getValue().accept(this);

        final IReturnType value = (IReturnType) program.symbols.expressions.get(object.getValue());

        if (value.isSubtypeOf(function.type.getReturnType()) == false)
        {
            // Error
        }
    }

    @Override
    public void visit(final RecurStatement object)
    {
        final List<IType> args = Lists.newLinkedList();

        for (IExpression arg : object.getArguments())
        {
            arg.accept(this);
            args.add(program.symbols.expressions.get(arg));
        }

        program.typesystem.utils.checkArgs(ImmutableList.of(function.type), args);
    }

    @Override
    public void visit(final YieldVoidStatement object)
    {
        if (function.isReturnTypeVoid() == false)
        {
            // Error
        }

        final LabelNode reentry = new LabelNode();

        function.yields.add(reentry);
        function.yieldField();
        program.symbols.yields.put(object, reentry);
    }

    @Override
    public void visit(final YieldValueStatement object)
    {
        object.getValue().accept(this);

        final IReturnType value = (IReturnType) program.symbols.expressions.get(object.getValue());

        if (value.isSubtypeOf(function.type.getReturnType()) == false)
        {
            // Error
        }

        final LabelNode reentry = new LabelNode();

        function.yields.add(reentry);
        function.yieldField();
        program.symbols.yields.put(object, reentry);
    }
}

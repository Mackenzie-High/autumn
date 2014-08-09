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
import high.mackenzie.autumn.lang.compiler.exceptions.BadGetterAssignment;
import high.mackenzie.autumn.lang.compiler.exceptions.BadMethodAssignment;
import high.mackenzie.autumn.lang.compiler.exceptions.BadSetterAssignment;
import high.mackenzie.autumn.lang.compiler.exceptions.NoSuchHandlerFunction;
import high.mackenzie.autumn.lang.compiler.exceptions.NoSuchProperty;
import high.mackenzie.autumn.lang.compiler.exceptions.PrototypeExpected;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IClassType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IExpressionType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IReturnType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;
import high.mackenzie.autumn.lang.compiler.utils.MemberToHandler;
import high.mackenzie.autumn.lang.compiler.utils.TopoSorter;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        final IExpressionType type = function.module.imports.resolveType(object.getType());

        super.declareVar(variable, type, false);

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

        final Variable variable = object.getVariable();

        final IExpression value = object.getValue();

        final IExpressionType type = program.symbols.expressions.get(value);

        super.declareVar(variable, type, true);
    }

    @Override
    public void visit(final ValStatement object)
    {
        object.getValue().accept(this);

        final Variable variable = object.getVariable();

        final IExpression value = object.getValue();

        final IExpressionType type = program.symbols.expressions.get(value);

        super.declareVar(variable, type, false);
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
        final String name = object.getMethod().getName();

        final IClassType functor_type = module.imports.resolveFunctorType(object.getType());

        final IClassType owner_type = module.imports.resolveModuleType(object.getOwner());

        final IMethod handler = MemberToHandler.findHandler(owner_type, name);

        super.declareVar(object.getVariable(), functor_type, false);

        program.symbols.delegates.put(object, handler);
    }

    @Override
    public void visit(final SetterStatement object)
    {
        /**
         * Get the type of the prototype object.
         */
        final IVariableType owner = function.scope.typeOf(object.getOwner().getName());

        /**
         * Get the type of the module that contains the handler function.
         */
        final IClassType mule = module.imports.resolveModuleType(object.getModule());

        /**
         * Create the mapping between the setter and the handler function.
         */
        try
        {
            final MemberToHandler mapping = MemberToHandler.forSetter(owner,
                                                                      object.getName().getName(),
                                                                      mule,
                                                                      object.getMethod().getName());

            /**
             * Record the mapping, because the code-generator will need it.
             */
            program.symbols.setters.put(object, mapping);
        }
        catch (PrototypeExpected ex)
        {
            // TODO
            Logger.getLogger(StatementTypeChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NoSuchProperty ex)
        {
            Logger.getLogger(StatementTypeChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NoSuchHandlerFunction ex)
        {
            Logger.getLogger(StatementTypeChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (BadSetterAssignment ex)
        {
            Logger.getLogger(StatementTypeChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void visit(final GetterStatement object)
    {
        /**
         * Get the type of the prototype object.
         */
        final IVariableType owner = function.scope.typeOf(object.getOwner().getName());

        /**
         * Get the type of the module that contains the handler function.
         */
        final IClassType mule = module.imports.resolveModuleType(object.getModule());

        /**
         * Create the mapping between the setter and the handler function.
         */
        try
        {
            final MemberToHandler mapping = MemberToHandler.forGetter(owner,
                                                                      object.getName().getName(),
                                                                      mule,
                                                                      object.getMethod().getName());

            /**
             * Record the mapping, because the code-generator will need it.
             */
            program.symbols.getters.put(object, mapping);
        }
        catch (PrototypeExpected ex)
        {
            // TODO
            Logger.getLogger(StatementTypeChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NoSuchProperty ex)
        {
            Logger.getLogger(StatementTypeChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NoSuchHandlerFunction ex)
        {
            Logger.getLogger(StatementTypeChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (BadGetterAssignment ex)
        {
            Logger.getLogger(StatementTypeChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void visit(final MethodStatement object)
    {
        /**
         * Get the type of the prototype object.
         */
        final IVariableType owner = function.scope.typeOf(object.getOwner().getName());

        /**
         * Get the type of the module that contains the handler function.
         */
        final IClassType mule = module.imports.resolveModuleType(object.getModule());

        /**
         * Create the mapping between the setter and the handler function.
         */
        try
        {
            final MemberToHandler mapping = MemberToHandler.forMethod(owner,
                                                                      object.getName().getName(),
                                                                      mule,
                                                                      object.getMethod().getName());

            /**
             * Record the mapping, because the code-generator will need it.
             */
            program.symbols.methods.put(object, mapping);
        }
        catch (PrototypeExpected ex)
        {
            // TODO
            Logger.getLogger(StatementTypeChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NoSuchProperty ex)
        {
            Logger.getLogger(StatementTypeChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NoSuchHandlerFunction ex)
        {
            Logger.getLogger(StatementTypeChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (BadMethodAssignment ex)
        {
            Logger.getLogger(StatementTypeChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                final IType left_type = function.module.imports.resolveType(left.getType());
                final IType right_type = function.module.imports.resolveType(right.getType());

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
            final IExpressionType type = function.module.imports.resolveType(handler.getType());

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
        final IExpressionType type = function.module.imports.resolveType(object.getType());

        program.checker.requireType(object.getType(), type);
        program.checker.requireThrowable(object, type);

        super.declareVar(object.getVariable(), type, false);

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

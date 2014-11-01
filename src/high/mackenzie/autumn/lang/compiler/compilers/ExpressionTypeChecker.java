package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ast.commons.IExpression;
import autumn.lang.compiler.ast.nodes.*;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import high.mackenzie.autumn.lang.compiler.exceptions.TypeCheckFailed;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IClassType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IConstructor;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IDeclaredType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IExpressionType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IField;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IReferenceType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IReturnType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;
import high.mackenzie.autumn.lang.compiler.utils.Conversion;
import high.mackenzie.autumn.lang.compiler.utils.TypeSystemUtils;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * An instance of this class performs type usage checking on an expression.
 *
 * @author Mackenzie High
 */
public class ExpressionTypeChecker
        extends AbstractTypeChecker
{
    /**
     * This field refers to the standard library method that implements identity-equality.
     */
    private final IMethod IDENTITY_EQUALITY;

    /**
     * This field refers to the standard library method that implements identity-inequality.
     */
    private final IMethod IDENTITY_INEQUALITY;

    /**
     * This object manages the allocation of local variables.
     */
    protected final VariableAllocator allocator;

    /**
     * Sole Constructor.
     *
     * @param function is the function being compiled.
     * @param allocator manages the allocation of local variables.
     */
    public ExpressionTypeChecker(final FunctionCompiler function,
                                 final VariableAllocator allocator)
    {
        super(function);

        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(allocator);

        this.allocator = allocator;

        // The Operators class in the standard library proivdes the operator implementations.
        final IDeclaredType OPERATORS = program.typesystem.utils.OPERATORS;

        // Find the method that implements identity equality.
        this.IDENTITY_EQUALITY = TypeSystemUtils.find(OPERATORS.getMethods(),
                                                      "identityEquals",
                                                      "(Ljava/lang/Object;Ljava/lang/Object;)Z");

        // Find the method that implements identity inequality.
        this.IDENTITY_INEQUALITY = TypeSystemUtils.find(OPERATORS.getMethods(),
                                                        "identityNotEquals",
                                                        "(Ljava/lang/Object;Ljava/lang/Object;)Z");
    }

    @Override
    public void visit(final BooleanDatum object)
    {
        infer(object, program.typesystem.utils.PRIMITIVE_BOOLEAN);
    }

    @Override
    public void visit(final CharDatum object)
    {
        infer(object, program.typesystem.utils.PRIMITIVE_CHAR);
    }

    @Override
    public void visit(final ByteDatum object)
    {
        infer(object, program.typesystem.utils.PRIMITIVE_BYTE);
    }

    @Override
    public void visit(final ShortDatum object)
    {
        infer(object, program.typesystem.utils.PRIMITIVE_SHORT);
    }

    @Override
    public void visit(final IntDatum object)
    {
        infer(object, program.typesystem.utils.PRIMITIVE_INT);
    }

    @Override
    public void visit(final LongDatum object)
    {
        infer(object, program.typesystem.utils.PRIMITIVE_LONG);
    }

    @Override
    public void visit(final FloatDatum object)
    {
        infer(object, program.typesystem.utils.PRIMITIVE_FLOAT);
    }

    @Override
    public void visit(final DoubleDatum object)
    {
        infer(object, program.typesystem.utils.PRIMITIVE_DOUBLE);
    }

    @Override
    public void visit(final StringDatum object)
    {
        program.checker.requireWellFormedStringLiteral(object);

        infer(object, program.typesystem.utils.STRING);
    }

    @Override
    public void visit(final NullDatum object)
    {
        infer(object, program.typesystem.utils.NULL);
    }

    @Override
    public void visit(final VariableDatum object)
    {
        // The variable must have already been declared.
        program.checker.checkDeclared(allocator, object.getVariable());

        // The variable must be in-scope.
        program.checker.checkScope(allocator, object.getVariable());

        // This is the type of the variable based on its declaration.
        final IVariableType type = allocator.typeOf(object.getVariable().getName());

        // A variable datum returns the value stored in the variable.
        // Thus, the datum's type is the type of the variable.
        infer(object, type);
    }

    @Override
    public void visit(final ClassDatum object)
    {
        // Perform type-checking.
        // This call will throw an exception, if the type does not exist, etc.
        module.imports.resolveReturnType(object.getType());

        infer(object, program.typesystem.utils.CLASS);
    }

    @Override
    public void visit(final PrognExpression object)
    {
        IExpression last = null;

        for (IExpression x : object.getElements())
        {
            x.accept(this);

            last = x;
        }

        assert last != null;

        // The last element must return a value.
        program.checker.requireNonVoid(last);

        final IExpressionType type = program.symbols.expressions.get(last);

        infer(object, type);
    }

    @Override
    public void visit(final ListExpression object)
    {
        // Visit the elements.
        object.getElements().accept(this);

        // A list-expression returns type java.util.List.
        infer(object, program.typesystem.utils.LIST);

        // Type-check the elements.
        program.checker.requireArguments(object.getElements());
    }

    @Override
    public void visit(final ListComprehensionExpression object)
    {
        /**
         * Type-check the iterable.
         */
        object.getIterable().accept(this);
        program.checker.requireIterable(object.getIterable());

        try
        {
            /**
             * A list-comprehension defines a nested scope that covers modifier and condition.
             */
            allocator.enterScope();

            /**
             * Declare the variable and type-check the type.
             */
            final Variable variable = object.getVariable();
            final IReferenceType type = function.module.imports.resolveReferenceType(object.getType());
            super.declareVar(variable, type, false);

            /**
             * Type-check the modifier.
             */
            object.getModifier().accept(this);
            program.checker.requireNonVoid(object.getModifier());

            /**
             * Type-check the condition.
             */
            if (object.getCondition() != null)
            {
                condition(object.getCondition());
            }
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
    public void visit(final DispatchExpression object)
    {
        final List<IType> args = Lists.newLinkedList();

        for (IExpression arg : object.getArguments())
        {
            arg.accept(this);
            args.add(program.symbols.expressions.get(arg));
        }

        final List<IMethod> methods = program.typesystem.utils.resolveMethods(module.type,
                                                                              module.type,
                                                                              object.getName().getName(),
                                                                              args);

        if (methods.isEmpty())
        {
            // TODO: Compile Error
            throw new RuntimeException("No Such Method: " + object.getName().getName() + args.toString());
        }

        program.symbols.dispatches.put(object, methods);
        infer(object, program.typesystem.utils.OBJECT);
    }

    @Override
    public void visit(final CallStaticMethodExpression object)
    {
        /**
         * The compilation of static methods is abstracted,
         * because that are used for other things also,
         * such as operators.
         */
        callStaticMethod(object,
                         object.getOwner(),
                         object.getName(),
                         object.getArguments());
    }

    @Override
    public void visit(final SetStaticFieldExpression object)
    {
        /**
         * Resolve the field.
         */
        final IField field = findStaticField(object, object.getOwner(), object.getName().getName());

        /**
         * Visit and type-check the value.
         */
        object.getValue().accept(this);

        /**
         * Ensure that the field is not readonly.
         */
        program.checker.requireNonFinalFieldAssignment(object, field);

        /**
         * Ensure that the value can actually be assigned to the field.
         */
        program.checker.checkAssign(object, field.getType(), object.getValue());

        /**
         * The return-type of the expression is void.
         */
        infer(object, program.typesystem.utils.VOID);
    }

    @Override
    public void visit(final GetStaticFieldExpression object)
    {
        /**
         * Resolve the field.
         */
        final IField field = findStaticField(object, object.getOwner(), object.getName().getName());

        /**
         * The return-type of the expression is the type of the field.
         */
        infer(object, field.getType());
    }

    @Override
    public void visit(final NewExpression object)
    {
        /**
         * Get the type that is being instantiated.
         * This will throw an exception, if the type does not exist or is inaccessible.
         */
        final IClassType type = module.imports.resolveClassType(object.getType());

        /**
         * Visit and type-check the arguments.
         */
        final List<IExpressionType> args = Lists.newLinkedList();

        for (IExpression arg : object.getArguments())
        {
            arg.accept(this);
            args.add(program.symbols.expressions.get(arg));
        }

        /**
         * Resolve the constructor that will be invoked.
         */
        final List<IConstructor> ctors = program.typesystem.utils.resolveCtors(module.type,
                                                                               type,
                                                                               args);

        /**
         * If no applicable constructor exists, issue a warning.
         */
        if (ctors.isEmpty())
        {
            // This will throw an exception.
            program.checker.reportNoSuchConstructor(object, type, args);
        }

        /**
         * Remember the resolved constructor, because the code-generator will need it.
         */
        final IConstructor ctor = (IConstructor) ctors.get(0);
        program.symbols.calls.put(object, ctor);

        infer(object, (IExpressionType) type);
    }

    @Override
    public void visit(final CallMethodExpression object)
    {
        /**
         * Visit and type-check the owner expression.
         */
        object.getOwner().accept(this);

        final IExpressionType type = program.symbols.expressions.get(object.getOwner());

        // This will throw an exception, if the type is not a declared-type.
        program.checker.requireDeclaredType(object, type);

        // This never fails.
        final IDeclaredType owner_type = (IDeclaredType) type;

        /**
         * Visit and type-check the argument expressions.
         * At the same time, build a list containing the types of the arguments.
         */
        final List<IExpressionType> args = Lists.newLinkedList();

        for (IExpression arg : object.getArguments())
        {
            arg.accept(this);
            args.add(program.symbols.expressions.get(arg));
        }


        /**
         * Resolve the method.
         */
        final String name = object.getName().getName();

        final List<IMethod> methods = program.typesystem.utils.resolveMethods(module.type,
                                                                              owner_type,
                                                                              name,
                                                                              args);

        /**
         * If no acceptable method overload was found, issue an error.
         */
        if (methods.isEmpty())
        {
            // This will throw an exception.
            program.checker.reportNoSuchMethod(object,
                                               false,
                                               owner_type,
                                               object.getName().getName(),
                                               args);
        }

        /**
         * Remember the selected method overload, because the code-generator will need it.
         */
        final IMethod method = (IMethod) methods.get(0);
        program.symbols.calls.put(object, method);

        /**
         * The return-type of a call-expression is the return-type
         * of the selected method overload.
         */
        infer(object, method.getReturnType());
    }

    @Override
    public void visit(final SetFieldExpression object)
    {
        /**
         * Visit the owner expression.
         */
        object.getOwner().accept(this);

        /**
         * Visit the value expression.
         */
        object.getValue().accept(this);

        /**
         * Resolve the field.
         */
        final IField field = findField(object, object.getOwner(), object.getName().getName());

        /**
         * Ensure that the field is not readonly.
         */
        program.checker.requireNonFinalFieldAssignment(object, field);

        /**
         * Ensure that the value can actually be assigned to the field.
         */
        program.checker.checkAssign(object, field.getType(), object.getValue());

        /**
         * The return-type of the expression is void.
         */
        infer(object, program.typesystem.utils.VOID);
    }

    @Override
    public void visit(final GetFieldExpression object)
    {
        /**
         * Visit the owner expression.
         */
        object.getOwner().accept(this);

        /**
         * Resolve the field.
         */
        final IField field = findField(object, object.getOwner(), object.getName().getName());

        /**
         * The return-type of the expression is the type of the field.
         */
        infer(object, field.getType());
    }

    @Override
    public void visit(final InstanceOfExpression object)
    {
        /**
         * Resolve the type.
         */
        final IDeclaredType type = module.imports.resolveDeclaredType(object.getType());

        /**
         * Visit the value expression.
         */
        object.getValue().accept(this);

        /**
         * Get the type of the value expression.
         */
        final IExpressionType value = program.symbols.expressions.get(object.getValue());

        /**
         * The type of the value expression must be a declared-type.
         */
        program.checker.requireDeclaredType(object, value);

        /**
         * The operation must be viable.
         */
        final boolean is_null = value.isNullType();
        final boolean case1 = value.isSubtypeOf(type);
        final boolean case2 = type.isSubtypeOf(value);

        if (is_null || !(case1 || case2))
        {
            // This will throw an exception.
            program.checker.reportNonViableInstanceOf(object, value, type);
        }

        /**
         * The return-type of an instance-of expression is boolean.
         */
        infer(object, program.typesystem.utils.PRIMITIVE_BOOLEAN);
    }

    @Override
    public void visit(final TernaryConditionalExpression object)
    {
        /**
         * Visit ant type-check the condition expression.
         */
        object.getCondition().accept(this);

        program.checker.checkCondition(object.getCondition());

        /**
         * Visit and type-check the true-case expression.
         */
        object.getCaseTrue().accept(this);

        // The true-case expression must produce a value.
        program.checker.requireArguments(Collections.singleton(object.getCaseTrue()));

        /**
         * Visit and type-check the false-case expression.
         */
        object.getCaseFalse().accept(this);

        // The false-case expression must produce a value.
        program.checker.requireArguments(Collections.singleton(object.getCaseFalse()));

        /**
         * The operands must be compatible.
         */
        final IExpressionType left = program.symbols.expressions.get(object.getCaseTrue());
        final IExpressionType right = program.symbols.expressions.get(object.getCaseFalse());

        // The type of one of the operands should be wider than the other.
        // For example:
        //     (if Boolean then String else Object) ==> Object is Wider
        //     (if Boolean then Object else String) ==> Object is Wider
        // Error Case Example:
        //     (if Boolean then Integer else Long) ==> Neither operand is wider than the other.
        // In the error case, this variable will be assigned null.
        final IExpressionType widest = program.typesystem.utils.widestType(left, right);

        /**
         * If neither operand is wider, then issue an error.
         */
        if (widest == null)
        {
            // This will throw an exception.
            program.checker.reportIncompatibleOperands(object, left, right);
        }

        /**
         * The return-type is the ternary-conditional-expression is the widest operand type.
         */
        infer(object, widest);
    }

    @Override
    public void visit(final LocalsExpression object)
    {
        /**
         * Get the names of the variables that will be captured.
         */
        final Set<String> visible = Sets.newTreeSet();

        for (String variable : allocator.getVariables())
        {
            // The variable must be in-scope.
            final boolean usable = allocator.isUsable(variable);

            // The variable cannot be a temporary.
            final boolean non_temp = !allocator.isTemporary(variable);

            if (usable && non_temp)
            {
                visible.add(variable);
            }
        }

        /**
         * The code-generator will needs the names of the variables to capture.
         */
        program.symbols.locals.put(object, visible);

        /**
         * The return-type of a locals-expression is always LocalsMap.
         */
        infer(object, program.typesystem.utils.LOCALS_MAP);
    }

    @Override
    public void visit(final AsOperation object)
    {
        // Visit the operand.
        object.getValue().accept(this);

        // Get the type of the operand.
        final IExpressionType input = program.symbols.expressions.get(object.getValue());

        // Get the output type.
        final IReturnType output = module.imports.resolveReturnType(object.getType());

        // Obtain a description of the conversion being performed.
        // This will be null, if the conversion is impossible.
        final Conversion conversion = Conversion.findConversion(program.typesystem, input, output);

        /**
         * If the conversion is impossible, issue an error.
         */
        if (conversion == null)
        {
            // This call will throw an exception.
            program.checker.reportNoSuchAsConversion(object, output);
        }

        // Remember the conversion, because the code-generator will need it.
        program.symbols.conversions.put(object, conversion);

        // A conversion operation returns the return-type of the operation.
        infer(object, output);
    }

    @Override
    public void visit(final IsOperation object)
    {
        // Visit the operand.
        object.getValue().accept(this);

        // Get the type of the operand.
        final IExpressionType input = program.symbols.expressions.get(object.getValue());

        // Get the output type.
        final IReturnType output = module.imports.resolveReturnType(object.getType());

        // Obtain a description of the conversion being performed.
        // This will be null, if the conversion is impossible.
        final Conversion conversion = Conversion.findConversion(program.typesystem, input, output);

        /**
         * If the conversion is impossible, issue an error.
         */
        if (conversion == null)
        {
            // This call will throw an exception.
            program.checker.reportNoSuchIsConversion(object, output);
        }

        // Remember the conversion, because the code-generator will need it.
        program.symbols.conversions.put(object, conversion);

        // A conversion operation returns the return-type of the operation.
        infer(object, output);
    }

    @Override
    public void visit(final NegateOperation object)
    {
        // Visit the operand and perform type-checking.
        unaryOperation(object,
                       "negate",
                       object.getOperand());
    }

    @Override
    public void visit(final NotOperation object)
    {
        // Visit the operand and perform type-checking.
        unaryOperation(object,
                       "not",
                       object.getOperand());
    }

    @Override
    public void visit(final DivideOperation object)
    {
        // Visit the operands and perform type-checking.
        binaryOperation(object,
                        "divide",
                        object.getLeftOperand(),
                        object.getRightOperand());
    }

    @Override
    public void visit(final ModuloOperation object)
    {
        // Visit the operands and perform type-checking.
        binaryOperation(object,
                        "modulo",
                        object.getLeftOperand(),
                        object.getRightOperand());
    }

    @Override
    public void visit(final MultiplyOperation object)
    {
        // Visit the operands and perform type-checking.
        binaryOperation(object,
                        "multiply",
                        object.getLeftOperand(),
                        object.getRightOperand());
    }

    @Override
    public void visit(final AddOperation object)
    {
        // Visit the operands and perform type-checking.
        binaryOperation(object,
                        "add",
                        object.getLeftOperand(),
                        object.getRightOperand());
    }

    @Override
    public void visit(final SubtractOperation object)
    {
        // Visit the operands and perform type-checking.
        binaryOperation(object,
                        "subtract",
                        object.getLeftOperand(),
                        object.getRightOperand());
    }

    @Override
    public void visit(final ConcatOperation object)
    {
        // Visit the operands and perform type-checking.
        binaryOperation(object,
                        "concat",
                        object.getLeftOperand(),
                        object.getRightOperand());
    }

    @Override
    public void visit(final LessThanOperation object)
    {
        // Visit the operands and perform type-checking.
        binaryOperation(object,
                        "lessThan",
                        object.getLeftOperand(),
                        object.getRightOperand());
    }

    @Override
    public void visit(final LessThanOrEqualsOperation object)
    {
        // Visit the operands and perform type-checking.
        binaryOperation(object,
                        "lessThanOrEquals",
                        object.getLeftOperand(),
                        object.getRightOperand());
    }

    @Override
    public void visit(final GreaterThanOperation object)
    {
        // Visit the operands and perform type-checking.
        binaryOperation(object,
                        "greaterThan",
                        object.getLeftOperand(),
                        object.getRightOperand());
    }

    @Override
    public void visit(final GreaterThanOrEqualsOperation object)
    {
        // Visit the operands and perform type-checking.
        binaryOperation(object,
                        "greaterThanOrEquals",
                        object.getLeftOperand(),
                        object.getRightOperand());
    }

    @Override
    public void visit(final EqualsOperation object)
    {
        // Visit the operands and perform type-checking.
        binaryOperation(object,
                        "equals",
                        object.getLeftOperand(),
                        object.getRightOperand());
    }

    @Override
    public void visit(final NotEqualsOperation object)
    {
        // Visit the operands and perform type-checking.
        binaryOperation(object,
                        "notEquals",
                        object.getLeftOperand(),
                        object.getRightOperand());
    }

    @Override
    public void visit(final IdentityEqualsOperation object)
    {
        // Visit the operands.
        object.getLeftOperand().accept(this);
        object.getRightOperand().accept(this);

        // Perform type-checking.
        program.checker.requireReferenceType(object.getLeftOperand());
        program.checker.requireReferenceType(object.getRightOperand());

        // This type of operator always returns boolean.
        infer(object, program.typesystem.utils.PRIMITIVE_BOOLEAN);

        // Remember the method that is needed to implement this operator.
        program.symbols.calls.put(object, IDENTITY_EQUALITY);
    }

    @Override
    public void visit(final IdentityNotEqualsOperation object)
    {
        // Visit the operands.
        object.getLeftOperand().accept(this);
        object.getRightOperand().accept(this);

        // Perform type-checking.
        program.checker.requireReferenceType(object.getLeftOperand());
        program.checker.requireReferenceType(object.getRightOperand());

        // This type of operator always returns boolean.
        infer(object, program.typesystem.utils.PRIMITIVE_BOOLEAN);

        // Remember the method that is needed to implement this operator.
        program.symbols.calls.put(object, IDENTITY_INEQUALITY);
    }

    @Override
    public void visit(final AndOperation object)
    {
        // Visit and type-check the operands.
        condition(object.getLeftOperand());
        condition(object.getRightOperand());

        // This type of operator always returns a boolean value.
        infer(object, program.typesystem.utils.PRIMITIVE_BOOLEAN);
    }

    @Override
    public void visit(final OrOperation object)
    {
        // Visit and type-check the operands.
        condition(object.getLeftOperand());
        condition(object.getRightOperand());

        // This type of operator always returns a boolean value.
        infer(object, program.typesystem.utils.PRIMITIVE_BOOLEAN);
    }

    @Override
    public void visit(final XorOperation object)
    {
        // Visit and type-check the operands.
        condition(object.getLeftOperand());
        condition(object.getRightOperand());

        // This type of operator always returns a boolean value.
        infer(object, program.typesystem.utils.PRIMITIVE_BOOLEAN);
    }

    @Override
    public void visit(final ImpliesOperation object)
    {
        // Visit and type-check the operands.
        condition(object.getLeftOperand());
        condition(object.getRightOperand());

        // This type of operator always returns a boolean value.
        infer(object, program.typesystem.utils.PRIMITIVE_BOOLEAN);
    }

    @Override
    public void visit(final NullCoalescingOperation object)
    {
        // Visit the operands.
        object.getLeftOperand().accept(this);
        object.getRightOperand().accept(this);

        // Retrieve the types of the operands.
        final IExpressionType left = program.symbols.expressions.get(object.getLeftOperand());
        final IExpressionType right = program.symbols.expressions.get(object.getRightOperand());

        // Noth operands must be references.
        program.checker.requireReferenceType(object.getLeftOperand());
        program.checker.requireReferenceType(object.getRightOperand());

        // The type of one of the operands should be wider than the other.
        // For example:
        //     (String ?? Object) ==> Object is Wider
        //     (Object ?? String) ==> Object is Wider
        // Error Case Example:
        //     (Integer ?? Long) ==> Neither operand is wider than the other.
        // In the error case, this variable will be assigned null.
        final IExpressionType widest = program.typesystem.utils.widestType(left, right);

        // If neither operand is wider, then issue an error.
        if (widest == null)
        {
            program.checker.reportIncompatibleOperands(object, left, right);
        }

        // A null-coalescing operator returns the widest of its operand types.
        infer(object, left);
    }
}

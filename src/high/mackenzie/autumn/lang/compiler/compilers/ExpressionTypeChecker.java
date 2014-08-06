package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ast.commons.IExpression;
import autumn.lang.compiler.ast.nodes.*;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IConstructor;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IDeclaredType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IExpressionType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IField;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IInterfaceType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IReferenceType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IReturnType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;
import high.mackenzie.autumn.lang.compiler.utils.Conversion;
import high.mackenzie.autumn.lang.compiler.utils.TypeSystemUtils;
import java.util.List;

/**
 * An instance of this class can perform type usage checking on an expression.
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
     * This is the scope of variables in the enclosing function.
     */
    protected final VariableScope scope;

    /**
     * Sole Constructor.
     *
     * @param function is the function being compiled.
     * @param scope is the scope of the function being compiled.
     */
    public ExpressionTypeChecker(final FunctionCompiler function,
                                 final VariableScope scope)
    {
        super(function);

        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(scope);

        this.scope = scope;

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
        program.checker.checkDeclared(scope, object.getVariable());

        // This is the type of the variable based on its declaration.
        final IVariableType type = scope.typeOf(object.getVariable().getName());

        // A variable datum returns the value stored in the variable.
        // Thus, the datum's type is the type of the variable.
        infer(object, type);
    }

    @Override
    public void visit(final ClassDatum object)
    {
        // Perform type-checking.
        // This call will throw an exception, if the type does not exist, etc.
        module.imports.resolveType(object.getType());

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
        callStaticMethod(object,
                         object.getOwner(),
                         object.getName(),
                         object.getArguments());
    }

    @Override
    public void visit(final SetStaticFieldExpression object)
    {
        object.getValue().accept(this);

        infer(object, program.typesystem.utils.VOID);

        findStaticField(object, object.getOwner(), object.getName().getName());
    }

    @Override
    public void visit(final GetStaticFieldExpression object)
    {
        final IField field = findStaticField(object, object.getOwner(), object.getName().getName());

        if (field == null)
        {
            // TODO: error
            throw new IllegalStateException();
        }

        infer(object, field.getType());
    }

    @Override
    public void visit(final NewExpression object)
    {
        final List<IType> args = Lists.newLinkedList();

        for (IExpression arg : object.getArguments())
        {
            arg.accept(this);
            args.add(program.symbols.expressions.get(arg));
        }

        final IType type = module.imports.resolveType(object.getType());

        // TODO: Check that the owner is a declared type.

        final IDeclaredType declared_type = (IDeclaredType) type;

        final List<IConstructor> ctors = program.typesystem.utils.resolveCtors(declared_type, // TODO: fix
                                                                               declared_type,
                                                                               args);

        if (ctors.isEmpty())
        {
            // TODO: Compile Error
            throw new RuntimeException("No Ctor: " + type);
        }

        final IConstructor ctor = (IConstructor) ctors.get(0);

        program.symbols.calls.put(object, ctor);
        infer(object, (IExpressionType) type);
    }

    @Override
    public void visit(final CreateExpression object)
    {
        final IExpressionType type = module.imports.resolveType(object.getType());

        if (type.isReferenceType() == false)
        {
            // TODO: error
        }

        if (((IReferenceType) type).isDeclaredType() == false)
        {
            // TODO: error
        }

        if (((IDeclaredType) type).isInterfaceType() == false)
        {
            // TODO: error
        }

        final IInterfaceType face = (IInterfaceType) type;

        if (face.isSubtypeOf(program.typesystem.utils.ABSTRACT_PROTOTYPE) == false)
        {
            // TODO: error
        }

        // final ClassCompiler creator = new ClassCompiler(module, face, object.getLocation());
        // program.symbols.creators.put(object, creator);

        infer(object, face);
    }

    @Override
    public void visit(final CallMethodExpression object)
    {
        object.getOwner().accept(this);

        final List<IType> args = Lists.newLinkedList();

        for (IExpression arg : object.getArguments())
        {
            arg.accept(this);
            args.add(program.symbols.expressions.get(arg));
        }

        final IType type = program.symbols.expressions.get(object.getOwner());

        // TODO: Check that the owner is a declared type.

        final IDeclaredType declared_type = (IDeclaredType) type;

        final List<IMethod> methods = program.typesystem.utils.resolveMethods(module.type,
                                                                              declared_type,
                                                                              object.getName().getName(),
                                                                              args);

        if (methods.isEmpty())
        {
            // TODO: Compile Error
            throw new RuntimeException("No Such Method: " + object.getName().getName() + args.toString());
        }

        final IMethod method = (IMethod) methods.get(0);

        program.symbols.calls.put(object, method);
        infer(object, method.getReturnType());
    }

    @Override
    public void visit(final SetFieldExpression object)
    {
        object.getOwner().accept(this);
        object.getValue().accept(this);

        infer(object, program.typesystem.utils.VOID);

        findField(object, object.getOwner(), object.getName().getName());
    }

    @Override
    public void visit(final GetFieldExpression object)
    {
        object.getOwner().accept(this);

        final IField field = findField(object, object.getOwner(), object.getName().getName());

        if (field == null)
        {
            // TODO: error
            throw new IllegalStateException();
        }

        infer(object, field.getType());
    }

    @Override
    public void visit(final InstanceOfExpression object)
    {
        object.getValue().accept(this);

        final IType type = module.imports.resolveType(object.getType());

        if ((type instanceof IReferenceType && ((IReferenceType) type).isDeclaredType()) == false)
        {
            // TODO: error
        }

        // TODO: Finish
        infer(object, program.typesystem.utils.PRIMITIVE_BOOLEAN);
    }

    @Override
    public void visit(final TernaryConditionalExpression object)
    {
        object.getCondition().accept(this);
        object.getCaseTrue().accept(this);
        object.getCaseFalse().accept(this);

        final IExpressionType left = program.symbols.expressions.get(object.getCaseTrue());
        final IExpressionType right = program.symbols.expressions.get(object.getCaseFalse());

        // TODO: finish and remove
        Preconditions.checkArgument(left.equals(right));

        infer(object, left);
    }

    @Override
    public void visit(final LocalsExpression object)
    {
        final IExpressionType returns = program.typesystem.utils.LOCALS_MAP;

        infer(object, returns);
    }

    @Override
    public void visit(final AsOperation object)
    {
        // Visit the operand.
        object.getValue().accept(this);

        // Get the type of the operand.
        final IExpressionType input = program.symbols.expressions.get(object.getValue());

        // Get the output type.
        final IReturnType output = module.imports.resolveType(object.getType());

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
        final IReturnType output = module.imports.resolveType(object.getType());

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
        // Visit the operands and perform type-checking.
        binaryOperation(object, "and",
                        object.getLeftOperand(),
                        object.getRightOperand());
    }

    @Override
    public void visit(final OrOperation object)
    {
        // Visit the operands and perform type-checking.
        binaryOperation(object,
                        "or",
                        object.getLeftOperand(),
                        object.getRightOperand());
    }

    @Override
    public void visit(final XorOperation object)
    {
        // Visit the operands and perform type-checking.
        binaryOperation(object,
                        "xor",
                        object.getLeftOperand(),
                        object.getRightOperand());
    }

    @Override
    public void visit(final ImpliesOperation object)
    {
        // Visit the operands and perform type-checking.
        binaryOperation(object,
                        "implies",
                        object.getLeftOperand(),
                        object.getRightOperand());
    }

    @Override
    public void visit(final ShortCircuitAndOperation object)
    {
        // Visit and type-check the operands.
        condition(object.getLeftOperand());
        condition(object.getRightOperand());

        // This type of operator always returns a boolean value.
        infer(object, program.typesystem.utils.PRIMITIVE_BOOLEAN);
    }

    @Override
    public void visit(final ShortCircuitOrOperation object)
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
            program.checker.reportNoSuchBinaryOperator(object, left, right);
        }

        // A null-coalescing operator returns the widest of its operand types.
        infer(object, left);
    }
}

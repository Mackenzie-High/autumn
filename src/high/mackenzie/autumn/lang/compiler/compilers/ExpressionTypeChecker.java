/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.util.List;

/**
 * An instance of this class can perform type usage checking on an expression.
 *
 * @author Mackenzie High
 */
public class ExpressionTypeChecker
        extends AbstractTypeChecker
{
    protected final VariableScope scope;

    public ExpressionTypeChecker(final FunctionCompiler function,
                                 final VariableScope scope)
    {
        super(function);

        this.scope = scope;
    }

    @Override
    public void visit(BooleanDatum object)
    {
        infer(object, program.typesystem.utils.PRIMITIVE_BOOLEAN);
    }

    @Override
    public void visit(CharDatum object)
    {
        infer(object, program.typesystem.utils.PRIMITIVE_CHAR);
    }

    @Override
    public void visit(ByteDatum object)
    {
        infer(object, program.typesystem.utils.PRIMITIVE_BYTE);
    }

    @Override
    public void visit(ShortDatum object)
    {
        infer(object, program.typesystem.utils.PRIMITIVE_SHORT);
    }

    @Override
    public void visit(IntDatum object)
    {
        infer(object, program.typesystem.utils.PRIMITIVE_INT);
    }

    @Override
    public void visit(LongDatum object)
    {
        infer(object, program.typesystem.utils.PRIMITIVE_LONG);
    }

    @Override
    public void visit(FloatDatum object)
    {
        infer(object, program.typesystem.utils.PRIMITIVE_FLOAT);
    }

    @Override
    public void visit(DoubleDatum object)
    {
        infer(object, program.typesystem.utils.PRIMITIVE_DOUBLE);
    }

    @Override
    public void visit(StringDatum object)
    {
        infer(object, program.typesystem.utils.STRING);
    }

    @Override
    public void visit(NullDatum object)
    {
        infer(object, program.typesystem.utils.NULL);
    }

    @Override
    public void visit(VariableDatum object)
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
    public void visit(ClassDatum object)
    {
        final IReturnType type = module.resolveType(object.getType());

        if (type == null)
        {
            // TODO: No such type
        }

        infer(object, program.typesystem.utils.CLASS);
    }

    @Override
    public void visit(PrognExpression object)
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
    public void visit(ListExpression object)
    {
        object.getElements().accept(this);

        infer(object, program.typesystem.utils.LIST);

        program.checker.requireArguments(object.getElements());
    }

    @Override
    public void visit(DispatchExpression object)
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
    public void visit(CallStaticMethodExpression object)
    {
        callStaticMethod(object,
                         object.getOwner(),
                         object.getName(),
                         object.getArguments());
    }

    @Override
    public void visit(SetStaticFieldExpression object)
    {
        object.getValue().accept(this);

        infer(object, program.typesystem.utils.VOID);

        findStaticField(object, object.getOwner(), object.getName().getName());
    }

    @Override
    public void visit(GetStaticFieldExpression object)
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
    public void visit(NewExpression object)
    {
        final List<IType> args = Lists.newLinkedList();

        for (IExpression arg : object.getArguments())
        {
            arg.accept(this);
            args.add(program.symbols.expressions.get(arg));
        }

        final IType type = module.resolveType(object.getType());

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
    public void visit(CreateExpression object)
    {
        final IExpressionType type = module.resolveType(object.getType());

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

        final ClassCompiler creator = new ClassCompiler(module, face, object.getLocation());

        program.symbols.creators.put(object, creator);

        infer(object, face);
    }

    @Override
    public void visit(CallMethodExpression object)
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
    public void visit(SetFieldExpression object)
    {
        object.getOwner().accept(this);
        object.getValue().accept(this);

        infer(object, program.typesystem.utils.VOID);

        findField(object, object.getOwner(), object.getName().getName());
    }

    @Override
    public void visit(GetFieldExpression object)
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
    public void visit(InstanceOfExpression object)
    {
        object.getValue().accept(this);

        final IType type = module.resolveType(object.getType());

        if ((type instanceof IReferenceType && ((IReferenceType) type).isDeclaredType()) == false)
        {
            // TODO: error
        }

        // TODO: Finish
        infer(object, program.typesystem.utils.PRIMITIVE_BOOLEAN);
    }

    @Override
    public void visit(FuncallExpression object)
    {
        object.getFunctor().accept(this);
        object.getArguments().accept(this);

        infer(object, program.typesystem.utils.OBJECT);

        program.checker.expectSubtype(object.getFunctor(), program.typesystem.utils.FUNCTOR);
        program.checker.requireArguments(object.getArguments());
    }

    @Override
    public void visit(TernaryConditionalExpression object)
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
    public void visit(DelegateExpression object)
    {
        final DelegateCompiler delegate = new DelegateCompiler(module, object);

        program.symbols.delegates.put(object, delegate);

        infer(object, program.typesystem.utils.DELEGATE);
    }

    @Override
    public void visit(LocalsExpression object)
    {
        final IExpressionType returns = program.typesystem.utils.LOCALS_MAP;

        infer(object, returns);
    }

    @Override
    public void visit(AsOperation object)
    {
        object.getValue().accept(this);

        final IExpressionType input = program.symbols.expressions.get(object.getValue());

        final IReturnType output = module.resolveType(object.getType());

        final Conversion conversion = Conversion.findConversion(program.typesystem, input, output);

        program.symbols.conversions.put(object, conversion);

        infer(object, output);
    }

    @Override
    public void visit(IsOperation object)
    {
        object.getValue().accept(this);

        final IExpressionType input = program.symbols.expressions.get(object.getValue());

        final IReturnType output = module.resolveType(object.getType());

        final Conversion conversion = Conversion.findConversion(program.typesystem, input, output);

        program.symbols.conversions.put(object, conversion);

        infer(object, output);
    }

    @Override
    public void visit(NegateOperation object)
    {
        unaryOperation(object, "negate", object.getOperand());
    }

    @Override
    public void visit(NotOperation object)
    {
        unaryOperation(object, "not", object.getOperand());
    }

    @Override
    public void visit(DivideOperation object)
    {
        binaryOperation(object, "divide", object.getLeftOperand(), object.getRightOperand());
    }

    @Override
    public void visit(ModuloOperation object)
    {
        binaryOperation(object, "modulo", object.getLeftOperand(), object.getRightOperand());
    }

    @Override
    public void visit(MultiplyOperation object)
    {
        binaryOperation(object, "multiply", object.getLeftOperand(), object.getRightOperand());
    }

    @Override
    public void visit(AddOperation object)
    {
        binaryOperation(object, "add", object.getLeftOperand(), object.getRightOperand());
    }

    @Override
    public void visit(SubtractOperation object)
    {
        binaryOperation(object, "subtract", object.getLeftOperand(), object.getRightOperand());
    }

    @Override
    public void visit(ConcatOperation object)
    {
        binaryOperation(object, "concat", object.getLeftOperand(), object.getRightOperand());
    }

    @Override
    public void visit(LessThanOperation object)
    {
        binaryOperation(object, "lessThan", object.getLeftOperand(), object.getRightOperand());
    }

    @Override
    public void visit(LessThanOrEqualsOperation object)
    {
        binaryOperation(object, "lessThanOrEquals", object.getLeftOperand(), object.getRightOperand());
    }

    @Override
    public void visit(GreaterThanOperation object)
    {
        binaryOperation(object, "greaterThan", object.getLeftOperand(), object.getRightOperand());
    }

    @Override
    public void visit(GreaterThanOrEqualsOperation object)
    {
        binaryOperation(object, "greaterThanOrEquals", object.getLeftOperand(), object.getRightOperand());
    }

    @Override
    public void visit(EqualsOperation object)
    {
        binaryOperation(object, "equals", object.getLeftOperand(), object.getRightOperand());
    }

    @Override
    public void visit(NotEqualsOperation object)
    {
        binaryOperation(object, "notEquals", object.getLeftOperand(), object.getRightOperand());
    }

    @Override
    public void visit(IdentityEqualsOperation object)
    {
        binaryOperation(object, "identityEquals", object.getLeftOperand(), object.getRightOperand());
    }

    @Override
    public void visit(IdentityNotEqualsOperation object)
    {
        binaryOperation(object, "identityNotEquals", object.getLeftOperand(), object.getRightOperand());
    }

    @Override
    public void visit(AndOperation object)
    {
        binaryOperation(object, "and", object.getLeftOperand(), object.getRightOperand());
    }

    @Override
    public void visit(OrOperation object)
    {
        binaryOperation(object, "or", object.getLeftOperand(), object.getRightOperand());
    }

    @Override
    public void visit(XorOperation object)
    {
        binaryOperation(object, "xor", object.getLeftOperand(), object.getRightOperand());
    }

    @Override
    public void visit(ShortCircuitAndOperation object)
    {
        condition(object.getLeftOperand());
        condition(object.getRightOperand());
        infer(object, program.typesystem.utils.PRIMITIVE_BOOLEAN);
    }

    @Override
    public void visit(ShortCircuitOrOperation object)
    {
        condition(object.getLeftOperand());
        condition(object.getRightOperand());
        infer(object, program.typesystem.utils.PRIMITIVE_BOOLEAN);
    }

    @Override
    public void visit(NullCoalescingOperation object)
    {
        object.getLeftOperand().accept(this);
        object.getRightOperand().accept(this);

        final IExpressionType left = program.symbols.expressions.get(object.getLeftOperand());

        // The right-operand must be a subtype of the left-operand.
        program.checker.expectSubtype(object.getRightOperand(), left);

        infer(object, left);
    }
}

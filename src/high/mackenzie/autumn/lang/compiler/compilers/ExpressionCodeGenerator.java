/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ast.commons.ConstructList;
import autumn.lang.compiler.ast.commons.IBinaryOperation;
import autumn.lang.compiler.ast.commons.IExpression;
import autumn.lang.compiler.ast.commons.IUnaryOperation;
import autumn.lang.compiler.ast.nodes.*;
import com.google.common.collect.Lists;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IDeclaredType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IExpressionType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IField;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IInvokableMember;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IReferenceType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IReturnType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;
import high.mackenzie.autumn.lang.compiler.utils.Conversion;
import high.mackenzie.autumn.lang.compiler.utils.Utils;
import java.util.Iterator;
import java.util.List;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.TypeInsnNode;

/**
 * An instance of this class performs the bytecode generation related to an expression.
 *
 * @author Mackenzie High
 */
public class ExpressionCodeGenerator
        extends AbstractAstVisitor
{
    protected final ProgramCompiler program;

    protected final ModuleCompiler module;

    protected final TypeSystem types;

    protected final VariableScope scope;

    protected final VariableManipulator vars;

    protected final List<AbstractInsnNode> code;

    public ExpressionCodeGenerator(final ModuleCompiler module,
                                   final VariableManipulator vars,
                                   final List<AbstractInsnNode> code)
    {
        this.program = module.program;
        this.module = module;
        this.types = program.typesystem;
        this.vars = vars;
        this.scope = vars.scope();
        this.code = code;
    }

    @Override
    public void visit(BooleanDatum object)
    {
        code.add(new LdcInsnNode(object.getValue()));
    }

    @Override
    public void visit(CharDatum object)
    {
        code.add(new LdcInsnNode(object.getValue().value()));
    }

    @Override
    public void visit(ByteDatum object)
    {
        code.add(new LdcInsnNode(object.getValue().value()));
    }

    @Override
    public void visit(ShortDatum object)
    {
        code.add(new LdcInsnNode(object.getValue().value()));
    }

    @Override
    public void visit(IntDatum object)
    {
        code.add(new LdcInsnNode(object.getValue().value()));
    }

    @Override
    public void visit(LongDatum object)
    {
        code.add(new LdcInsnNode(object.getValue().value()));
    }

    @Override
    public void visit(FloatDatum object)
    {
        code.add(new LdcInsnNode(object.getValue().value()));
    }

    @Override
    public void visit(DoubleDatum object)
    {
        code.add(new LdcInsnNode(object.getValue().value()));
    }

    @Override
    public void visit(StringDatum object)
    {
        code.add(new LdcInsnNode(object.getValue()));
    }

    @Override
    public void visit(NullDatum object)
    {
        code.add(new InsnNode(Opcodes.ACONST_NULL));
    }

    @Override
    public void visit(VariableDatum object)
    {
        vars.load(object.getVariable().getName());
    }

    @Override
    public void visit(ClassDatum object)
    {
        final IReturnType type = module.resolveType(object.getType());

        code.add(Utils.ldcClass(type));
    }

    @Override
    public void visit(PrognExpression object)
    {
        final Iterator<IExpression> iter = object.getElements().iterator();

        // For each expression in the progn-expression.
        while (iter.hasNext())
        {
            // Get the expression and its type.
            final IExpression element = iter.next();
            final IType type = program.symbols.expressions.get(element);

            // Generate the expression's bytecode.
            element.accept(this);

            // If the expression is not the last element in the progn-expression,
            // then pop its value off of the operand-stack.
            if (iter.hasNext())
            {
                code.add(Utils.selectPop(type));
            }
        }
    }

    @Override
    public void visit(ListExpression object)
    {
        // First, generate a java.util.LinkedList.

        final ExpressionCodeGenerator THIS = this;

        final CollectionCompiler<IExpression> cmp = new CollectionCompiler<IExpression>()
        {
            @Override
            public void compile(final IExpression element)
            {
                final IType element_type = program.symbols.expressions.get(element);

                element.accept(THIS);

                autoboxToObject(element_type);
            }

            @Override
            public List code()
            {
                return THIS.code;
            }
        };

        // Create list.
        cmp.compile(object.getElements());

        // Second, convert the java.util.LinkedList into an immutable-list.
        code.add(new MethodInsnNode(Opcodes.INVOKESTATIC,
                                    Utils.internalName(program.typesystem.utils.HELPERS),
                                    "newImmutableList",
                                    "(Ljava/lang/Iterable;)Ljava/util/List;"));
    }

    @Override
    public void visit(DispatchExpression object)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void generateDispatchHandler(final List<IType> arguments,
                                         final IMethod method,
                                         final LabelNode end)
    {
    }

    @Override
    public void visit(CallStaticMethodExpression object)
    {
        final IMethod method = (IMethod) module.program.symbols.calls.get(object);

        compileStaticMethodCall(method, object.getArguments());
    }

    @Override
    public void visit(SetStaticFieldExpression object)
    {
        final IField field = program.symbols.fields.get(object);

        final String owner = Utils.internalName(field.getOwner());

        final String name = field.getName();

        final String desc = field.getType().getDescriptor();

        // Evaluate the expression that produces the value to put into the field.
        object.getValue().accept(this);

        // Get the value from the field.
        code.add(new FieldInsnNode(Opcodes.PUTSTATIC, owner, name, desc));
    }

    @Override
    public void visit(GetStaticFieldExpression object)
    {
        final IField field = program.symbols.fields.get(object);

        final String owner = Utils.internalName(field.getOwner());

        final String name = field.getName();

        final String desc = field.getType().getDescriptor();

        // Get the value from the field.
        code.add(new FieldInsnNode(Opcodes.GETSTATIC, owner, name, desc));
    }

    @Override
    public void visit(NewExpression object)
    {
        final IInvokableMember method = program.symbols.calls.get(object);

        final String type = method.getOwner().getDescriptor().substring(1).replace(";", "");

        final List<IExpression> arguments = object.getArguments().asMutableList();

        code.add(new TypeInsnNode(Opcodes.NEW, type));

        code.add(new InsnNode(Opcodes.DUP));

        for (int i = 0; i < arguments.size(); i++)
        {
            final IType parameter = method.getFormalParameters().get(i).getType();
            final IType argument = program.symbols.expressions.get(arguments.get(i));

            // Generate the argument's bytecode.
            arguments.get(i).accept(this);

            // Generate code to box/unbox the argument, if needed.
            code.addAll(types.utils.assign(argument, parameter));
        }

        code.add(new MethodInsnNode(Opcodes.INVOKESPECIAL,
                                    type,
                                    method.getName(),
                                    method.getDescriptor()));
    }

    @Override
    public void visit(CreateExpression object)
    {
        final ClassCompiler creator = program.symbols.creators.get(object);

        creator.load(code);
    }

    @Override
    public void visit(CallMethodExpression object)
    {
        final IMethod method = (IMethod) program.symbols.calls.get(object);

        final List<IExpression> arguments = object.getArguments().asMutableList();

        object.getOwner().accept(this);

        for (int i = 0; i < arguments.size(); i++)
        {
            final IType parameter = method.getFormalParameters().get(i).getType();
            final IType argument = program.symbols.expressions.get(arguments.get(i));

            // Generate the argument's bytecode.
            arguments.get(i).accept(this);

            // Generate code to box/unbox the argument, if needed.
            code.addAll(types.utils.assign(argument, parameter));
        }

        final int opcode = method.getOwner().isInterfaceType()
                ? Opcodes.INVOKEINTERFACE
                : Opcodes.INVOKEVIRTUAL;

        code.add(new MethodInsnNode(opcode,
                                    method.getOwner().getDescriptor().substring(1).replace(";", ""),
                                    method.getName(),
                                    method.getDescriptor()));
    }

    @Override
    public void visit(SetFieldExpression object)
    {
        final IField field = program.symbols.fields.get(object);

        final String owner = Utils.internalName(field.getOwner());

        final String name = field.getName();

        final String desc = field.getType().getDescriptor();

        // Evaluate the expression that produces the owner of the field.
        object.getOwner().accept(this);

        // Evaluate the expression that produces the value to put into the field.
        object.getValue().accept(this);

        // Get the value from the field.
        code.add(new FieldInsnNode(Opcodes.PUTFIELD, owner, name, desc));
    }

    @Override
    public void visit(GetFieldExpression object)
    {
        final IField field = program.symbols.fields.get(object);

        final String owner = Utils.internalName(field.getOwner());

        final String name = field.getName();

        final String desc = field.getType().getDescriptor();

        // Evaluate the expression that produces the owner of the field.
        object.getOwner().accept(this);

        // Get the value from the field.
        code.add(new FieldInsnNode(Opcodes.GETFIELD, owner, name, desc));
    }

    @Override
    public void visit(InstanceOfExpression object)
    {
        final IType type = module.resolveType(object.getType());

        // Evaluate the expression.
        object.getValue().accept(this);

        // Perform the instance-of check.
        code.add(new TypeInsnNode(Opcodes.INSTANCEOF, Utils.internalName((IDeclaredType) type)));
    }

    @Override
    public void visit(FuncallExpression object)
    {
        // Evaluate the functor.
        object.getFunctor().accept(this);

        // Retrieve the argument-stack.
        code.add(new MethodInsnNode(Opcodes.INVOKESTATIC,
                                    "autumn/lang/internals/ArgumentStack",
                                    "getThreadStack",
                                    "()Lautumn/lang/internals/ArgumentStack;"));

        // Duplicate the reference to the argument-stack.
        code.add(new InsnNode(Opcodes.DUP));

        // Ensure that the argument-stack is clear.
        code.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL,
                                    "autumn/lang/internals/ArgumentStack",
                                    "clear",
                                    "()V"));

        // Push the arguments onto the argument-stack.
        for (IExpression arg : object.getArguments())
        {
            // Duplicate the reference to the argument-stack.
            code.add(new InsnNode(Opcodes.DUP));

            final IExpressionType type = program.symbols.expressions.get(arg);

            // Evaluate the argument.
            arg.accept(this);

            // Push the actual-argument onto the argument-stack.
            Utils.pushArgument(program, code, type);
        }

        // Duplicate the reference to the argument-stack and insert it under the functor.
        code.add(new InsnNode(Opcodes.DUP_X1));

        // Perform the functor invocation.
        code.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE,
                                    "autumn/lang/Functor",
                                    "invoke",
                                    "(Lautumn/lang/internals/ArgumentStack;)V"));

        // Retrieve the result of invoking the functor, as an object.
        code.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL,
                                    "autumn/lang/internals/ArgumentStack",
                                    "popO",
                                    "()Ljava/lang/Object;"));
    }

    @Override
    public void visit(TernaryConditionalExpression object)
    {
        final LabelNode ELSE = new LabelNode();
        final LabelNode END = new LabelNode();

        compileCondition(object.getCondition());
        code.add(new JumpInsnNode(Utils.IF_FALSE, ELSE));
        {
            object.getCaseTrue().accept(this);
        }
        code.add(new JumpInsnNode(Opcodes.GOTO, END));
        code.add(ELSE);
        {
            object.getCaseFalse().accept(this);
        }
        code.add(END);
    }

    @Override
    public void visit(DelegateExpression object)
    {
//        final DelegateCompiler delegate = program.symbols.delegates.get(object);
//
//        delegate.load(code);
    }

    @Override
    public void visit(LocalsExpression object)
    {
        this.loadLocalsMap(object.getLocation());
    }

    protected void loadLocalsMap(final SourceLocation location)
    {
        final String STRING = "Ljava/lang/String;";
        final String ITERABLE = "Ljava/lang/Iterable;";

        final String owner = Utils.internalName(program.typesystem.utils.LOCALS_MAP);
        final String name = "<init>";
        final String desc = '(' + STRING + 'I' + 'I' + ITERABLE + ')' + 'V';

        // Create the uninitialized LocalsMap object.
        code.add(new TypeInsnNode(Opcodes.NEW, owner));

        // Duplicate the reference to the map.
        code.add(new InsnNode(Opcodes.DUP));

        // Load the arguments to pass to the constructor.
        code.add(new LdcInsnNode(location.getFile().toString()));
        code.add(new LdcInsnNode(location.getLine()));
        code.add(new LdcInsnNode(location.getColumn()));
        loadLocals();

        // Invoke the constructor.
        code.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, owner, name, desc));

        // The initialized LocalsMap is now the topmost value on the operand-stack.
    }

    private void loadLocals()
    {
        final String CLASS = "Ljava/lang/Class;";
        final String STRING = "Ljava/lang/String;";
        final String OBJECT = "Ljava/lang/Object;";

        final String owner = Utils.internalName(program.typesystem.utils.LOCAL);
        final String init = "<init>";
        final String desc = '(' + STRING + CLASS + OBJECT + ')' + 'V';

        /**
         * This object will be used to create a list of Local objects.
         * Each Local object represents a local variable in the enclosing scope.
         * There will be one object in the list for each local variable.
         */
        final CollectionCompiler cmp = new CollectionCompiler()
        {
            @Override
            public void compile(Object element)
            {
                final String name = (String) element;

                final IVariableType type = scope.typeOf(name);

                final int address = scope.addressOf(name);

                // Create the uninitialized Local object.
                code().add(new TypeInsnNode(Opcodes.NEW, owner));

                // Duplicate the reference to the Local object.
                code().add(new InsnNode(Opcodes.DUP));

                // Puch the arguments to pass to the constructor.
                code().add(new LdcInsnNode(name));
                code().add(Utils.ldcClass(type));
                code().add(Utils.selectLoadVarInsn(type, address));
                autoboxToObject(type);

                // Invoke the constructor.
                code().add(new MethodInsnNode(Opcodes.INVOKESPECIAL, owner, init, desc));

                // The initialized Local object is now the topmost value on the operand-stack.
            }

            @Override
            public List code()
            {
                return code;
            }
        };

        // Generate the list.
        cmp.compile(scope.getAllVariables());
    }

    @Override
    public void visit(AsOperation object)
    {
        final Conversion conversion = program.symbols.conversions.get(object);

        if (conversion.cast)
        {
            final LabelNode ELSE = new LabelNode();
            final LabelNode END = new LabelNode();

            // Evaluate the expression that produces the value to convert.
            object.getValue().accept(this);

            // Duplicate the object reference.
            code.add(new InsnNode(Opcodes.DUP));

            // Perform the instanceof check.
            // This will pop one object reference off of the operand-stack.
            code.add(new TypeInsnNode(Opcodes.INSTANCEOF, Utils.internalName((IReferenceType) conversion.type)));

            // If the check failed, replace the value with null.
            // Otherwise, perform a cast that will definitely succeed at runtime.
            code.add(new JumpInsnNode(Utils.IF_TRUE, ELSE));
            {
                code.add(new InsnNode(Opcodes.POP));

                code.add(new InsnNode(Opcodes.ACONST_NULL));
            }
            code.add(new JumpInsnNode(Opcodes.GOTO, END));
            code.add(ELSE);
            {
                code.add(new TypeInsnNode(Opcodes.CHECKCAST, Utils.internalName((IReferenceType) conversion.type)));
            }
            code.add(END);
        }
        else
        {
            // Evaluate the expression that produces the value to convert.
            object.getValue().accept(this);

            // Invoke the conversion method.
            final String owner = Utils.internalName(program.typesystem.utils.CONVERSIONS);
            final String name = conversion.name;
            final String desc = "(" + conversion.value.getDescriptor() + ")" + conversion.type.getDescriptor();
            code.add(new MethodInsnNode(Opcodes.INVOKESTATIC, owner, name, desc));
        }
    }

    @Override
    public void visit(IsOperation object)
    {
        final Conversion conversion = program.symbols.conversions.get(object);

        if (conversion.cast)
        {
            // Evaluate the expression that produces the value to convert.
            object.getValue().accept(this);

            // Perform the checked cast.
            code.add(new TypeInsnNode(Opcodes.CHECKCAST, Utils.internalName((IDeclaredType) conversion.type)));
        }
        else
        {
            // Evaluate the expression that produces the value to convert.
            object.getValue().accept(this);

            // Invoke the conversion method.
            final String owner = Utils.internalName(program.typesystem.utils.CONVERSIONS);
            final String name = conversion.name;
            final String desc = "(" + conversion.value.getDescriptor() + ")" + conversion.type.getDescriptor();
            code.add(new MethodInsnNode(Opcodes.INVOKESTATIC, owner, name, desc));
        }
    }

    @Override
    public void visit(NegateOperation object)
    {
        compileUnaryOperator(object);
    }

    @Override
    public void visit(NotOperation object)
    {
        compileUnaryOperator(object);
    }

    @Override
    public void visit(DivideOperation object)
    {
        compileBinaryOperator(object);
    }

    @Override
    public void visit(ModuloOperation object)
    {
        compileBinaryOperator(object);
    }

    @Override
    public void visit(MultiplyOperation object)
    {
        compileBinaryOperator(object);
    }

    @Override
    public void visit(AddOperation object)
    {
        compileBinaryOperator(object);
    }

    @Override
    public void visit(ConcatOperation object)
    {
        compileBinaryOperator(object);
    }

    @Override
    public void visit(SubtractOperation object)
    {
        compileBinaryOperator(object);
    }

    @Override
    public void visit(LessThanOperation object)
    {
        compileBinaryOperator(object);
    }

    @Override
    public void visit(LessThanOrEqualsOperation object)
    {
        compileBinaryOperator(object);
    }

    @Override
    public void visit(GreaterThanOperation object)
    {
        compileBinaryOperator(object);
    }

    @Override
    public void visit(GreaterThanOrEqualsOperation object)
    {
        compileBinaryOperator(object);
    }

    @Override
    public void visit(EqualsOperation object)
    {
        compileBinaryOperator(object);
    }

    @Override
    public void visit(NotEqualsOperation object)
    {
        compileBinaryOperator(object);
    }

    @Override
    public void visit(IdentityEqualsOperation object)
    {
        compileBinaryOperator(object);
    }

    @Override
    public void visit(IdentityNotEqualsOperation object)
    {
        compileBinaryOperator(object);
    }

    @Override
    public void visit(AndOperation object)
    {
        compileBinaryOperator(object);
    }

    @Override
    public void visit(OrOperation object)
    {
        compileBinaryOperator(object);
    }

    @Override
    public void visit(XorOperation object)
    {
        compileBinaryOperator(object);
    }

    @Override
    public void visit(ShortCircuitAndOperation object)
    {
        final LabelNode ELSE = new LabelNode();
        final LabelNode END = new LabelNode();

        compileCondition(object.getLeftOperand());

        code.add(new JumpInsnNode(Utils.IF_FALSE, ELSE));
        {
            compileCondition(object.getRightOperand());
        }
        code.add(new JumpInsnNode(Opcodes.GOTO, END));
        code.add(ELSE);
        {
            code.add(new LdcInsnNode(false));
        }
        code.add(END);
    }

    @Override
    public void visit(ShortCircuitOrOperation object)
    {
        final LabelNode ELSE = new LabelNode();
        final LabelNode END = new LabelNode();

        compileCondition(object.getLeftOperand());

        code.add(new JumpInsnNode(Utils.IF_TRUE, ELSE));
        {
            compileCondition(object.getRightOperand());
        }
        code.add(new JumpInsnNode(Opcodes.GOTO, END));
        code.add(ELSE);
        {
            code.add(new LdcInsnNode(true));
        }
        code.add(END);
    }

    @Override
    public void visit(NullCoalescingOperation object)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected void compileUnaryOperator(final IUnaryOperation operation)
    {
        final IMethod method = (IMethod) program.symbols.calls.get(operation);

        ConstructList<IExpression> operands = new ConstructList<IExpression>();
        operands = operands.add(operation.getOperand());

        compileStaticMethodCall(method, operands);
    }

    protected void compileBinaryOperator(final IBinaryOperation operation)
    {
        final IMethod method = (IMethod) program.symbols.calls.get(operation);

        final IExpression left = operation.getLeftOperand();

        final IExpression right = operation.getRightOperand();

        ConstructList<IExpression> operands = new ConstructList<IExpression>();
        operands = operands.add(left);
        operands = operands.add(right);

        compileStaticMethodCall(method, operands);
    }

    protected void compileStaticMethodCall(final IMethod method,
                                           final ConstructList<IExpression> arguments)
    {
        final List<IExpression> args = arguments.asMutableList();

        for (int i = 0; i < arguments.size(); i++)
        {
            final IType parameter = method.getFormalParameters().get(i).getType();
            final IType argument = program.symbols.expressions.get(args.get(i));

            // Generate the argument's bytecode.
            args.get(i).accept(this);

            // Generate code to box/unbox the argument, if needed.
            code.addAll(types.utils.assign(argument, parameter));
        }

        code.add(new MethodInsnNode(Opcodes.INVOKESTATIC,
                                    method.getOwner().getDescriptor().substring(1).replace(";", ""),
                                    method.getName(),
                                    method.getDescriptor()));
    }

    protected final void autoboxToObject(final IType type)
    {
        List<AbstractInsnNode> boxing = types.utils.box(type, types.utils.OBJECT);

        boxing = boxing == null ? Lists.<AbstractInsnNode>newArrayList() : boxing;

        code.addAll(boxing);
    }

    protected final void compileCondition(final IExpression condition)
    {
        final IType type = program.symbols.expressions.get(condition);

        // Compile the expression itself.
        condition.accept(this);

        // If the expression evaluates to a boxed-boolean rather than a primitive-boolean,
        // then it is necessary to unbox the value.
        if (type.equals(program.typesystem.utils.BOXED_BOOLEAN))
        {
            final IType input = program.typesystem.utils.BOXED_BOOLEAN;

            final IType output = program.typesystem.utils.PRIMITIVE_BOOLEAN;

            code.addAll(program.typesystem.utils.unbox(input, output));
        }
    }

    protected final void convert(final IType type,
                                 final IExpression expression)
    {
        final IType etype = program.symbols.expressions.get(expression);

        code.addAll(program.typesystem.utils.assign(etype, type));
    }
}

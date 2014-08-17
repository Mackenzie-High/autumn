package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ast.commons.ConstructList;
import autumn.lang.compiler.ast.commons.IBinaryOperation;
import autumn.lang.compiler.ast.commons.IExpression;
import autumn.lang.compiler.ast.commons.IUnaryOperation;
import autumn.lang.compiler.ast.nodes.*;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IDeclaredType;
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
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
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
    /**
     * Basically, this is the program that is being compiled.
     */
    protected final ProgramCompiler program;

    /**
     * Basically, this is the module that contains the expressions compiled by this object.
     */
    protected final ModuleCompiler module;

    /**
     * This is the type-system in use by the compiler.
     */
    protected final TypeSystem types;

    /**
     * This is the scope any variables that occur in expressions compiled by this object.
     */
    protected final VariableScope scope;

    /**
     * This object simplifies the manipulation of variables.
     */
    protected final VariableManipulator vars;

    /**
     * This is the list of instructions being generated.
     * The whole purpose of this ExpressionCodeGenerator is to add instructions to this list.
     */
    protected final InsnList code;

    /**
     * Sole Constructor.
     *
     * @param module is the module being compiled.
     * @param vars is the enclosing scope.
     * @param code is the list of bytecode instructions being generated.
     */
    public ExpressionCodeGenerator(final ModuleCompiler module,
                                   final VariableManipulator vars,
                                   final InsnList code)
    {
        Preconditions.checkNotNull(module);
        Preconditions.checkNotNull(vars);
        Preconditions.checkNotNull(code);

        this.program = module.program;
        this.module = module;
        this.types = program.typesystem;
        this.vars = vars;
        this.scope = vars.scope();
        this.code = code;
    }

    /**
     * This method generalizes the compilation of a unary operator.
     *
     * @param operation is the operation to compile.
     */
    protected void compileUnaryOperator(final IUnaryOperation operation)
    {
        Preconditions.checkNotNull(operation);

        final IMethod method = (IMethod) program.symbols.calls.get(operation);

        ConstructList<IExpression> operands = new ConstructList<IExpression>();
        operands = operands.add(operation.getOperand());

        compileStaticMethodCall(method, operands);
    }

    /**
     * This method generalizes the compilation of a binary operator.
     *
     * @param operation is the operation to compile.
     */
    protected void compileBinaryOperator(final IBinaryOperation operation)
    {
        Preconditions.checkNotNull(operation);

        // During type-checking a method was selected that implements the operation.
        // The method is a static utility method in the standard-library Operators class.
        final IMethod method = (IMethod) program.symbols.calls.get(operation);

        // Get the types of the operands.
        final IExpression left = operation.getLeftOperand();
        final IExpression right = operation.getRightOperand();

        // Generate an invocation of the static utility method.
        // The optimizer may optimize out the invocation later.
        compileStaticMethodCall(method, Lists.newArrayList(left, right));
    }

    /**
     * This method generalizes the compilation of a static method invocation.
     *
     * @param method is the method being invoked.
     * @param arguments are the expressions that produce the arguments.
     */
    protected void compileStaticMethodCall(final IMethod method,
                                           final Iterable<IExpression> arguments)
    {
        Preconditions.checkNotNull(method);
        Preconditions.checkNotNull(arguments);

        // Convert the iterable to a list.
        final List<IExpression> args = Lists.newArrayList(arguments);

        /**
         * Generate the bytecode for each argument.
         */
        for (int i = 0; i < args.size(); i++)
        {
            final IType parameter = method.getFormalParameters().get(i).getType();
            final IType argument = program.symbols.expressions.get(args.get(i));

            // Generate the argument's bytecode.
            args.get(i).accept(this);

            // Generate code to box/unbox the argument, if needed.
            code.add(types.utils.assign(argument, parameter));
        }

        /**
         * Generate the method invocation itself.
         */
        code.add(new MethodInsnNode(Opcodes.INVOKESTATIC,
                                    Utils.internalName(method.getOwner()),
                                    method.getName(),
                                    method.getDescriptor()));
    }

    /**
     * This method generalizes the compilation of a condition expression.
     *
     * <p>
     * The condition will be evaluated, and automatically boxed or unboxed when necessary.
     * </p>
     *
     * @param condition is the expression that is really a condition.
     */
    protected final void compileCondition(final IExpression condition)
    {
        Preconditions.checkNotNull(condition);

        // Retrieve the type of the expression.
        final IType type = program.symbols.expressions.get(condition);

        // Compile the expression itself.
        condition.accept(this);

        // If the expression evaluates to a boxed-boolean rather than a primitive-boolean,
        // then it is necessary to unbox the value.
        if (type.equals(program.typesystem.utils.BOXED_BOOLEAN))
        {
            final IType input = program.typesystem.utils.BOXED_BOOLEAN;

            final IType output = program.typesystem.utils.PRIMITIVE_BOOLEAN;

            code.add(program.typesystem.utils.unbox(input, output));
        }
    }

    /**
     * This method generates bytecode that converts an expression's value to another type.
     *
     * <p>
     * The generated bytecode expects that the expression's value is the topmost value
     * on the operand-stack.
     * </p>
     *
     * @param type is the type to which the value will be converted.
     * @param expression is the expression whose value is being converted.
     */
    protected final void convert(final IType type,
                                 final IExpression expression)
    {
        Preconditions.checkNotNull(type);
        Preconditions.checkNotNull(expression);

        final IType etype = program.symbols.expressions.get(expression);

        code.add(program.typesystem.utils.assign(etype, type));
    }

    @Override
    public void visit(final BooleanDatum object)
    {
        // Generated Bytecode;
        //
        // LDC value
        //
        /////////////////////////////////////////////////

        code.add(new LdcInsnNode(object.getValue()));
    }

    @Override
    public void visit(final CharDatum object)
    {
        // Generated Bytecode;
        //
        // LDC value
        //
        /////////////////////////////////////////////////

        code.add(new LdcInsnNode(object.getValue().value()));
    }

    @Override
    public void visit(final ByteDatum object)
    {
        // Generated Bytecode;
        //
        // LDC value
        //
        /////////////////////////////////////////////////

        code.add(new LdcInsnNode(object.getValue().value()));
    }

    @Override
    public void visit(final ShortDatum object)
    {
        // Generated Bytecode;
        //
        // LDC value
        //
        /////////////////////////////////////////////////

        code.add(new LdcInsnNode(object.getValue().value()));
    }

    @Override
    public void visit(final IntDatum object)
    {
        // Generated Bytecode;
        //
        // LDC value
        //
        /////////////////////////////////////////////////

        code.add(new LdcInsnNode(object.getValue().value()));
    }

    @Override
    public void visit(final LongDatum object)
    {
        // Generated Bytecode;
        //
        // LDC value
        //
        /////////////////////////////////////////////////

        code.add(new LdcInsnNode(object.getValue().value()));
    }

    @Override
    public void visit(final FloatDatum object)
    {
        // Generated Bytecode;
        //
        // LDC value
        //
        /////////////////////////////////////////////////

        code.add(new LdcInsnNode(object.getValue().value()));
    }

    @Override
    public void visit(final DoubleDatum object)
    {
        // Generated Bytecode;
        //
        // LDC value
        //
        /////////////////////////////////////////////////

        code.add(new LdcInsnNode(object.getValue().value()));
    }

    @Override
    public void visit(final StringDatum object)
    {
        // Generated Bytecode;
        //
        // LDC value
        //
        /////////////////////////////////////////////////

        code.add(new LdcInsnNode(object.getValue()));
    }

    @Override
    public void visit(final NullDatum object)
    {
        // Generated Bytecode;
        //
        // ACONST_NULL
        //
        /////////////////////////////////////////////////

        code.add(new InsnNode(Opcodes.ACONST_NULL));
    }

    @Override
    public void visit(final VariableDatum object)
    {
        vars.load(object.getVariable().getName());
    }

    @Override
    public void visit(final ClassDatum object)
    {
        final IReturnType type = module.imports.resolveReturnType(object.getType());

        code.add(Utils.ldcClass(type));
    }

    @Override
    public void visit(final PrognExpression object)
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
    public void visit(final ListExpression object)
    {
        final ExpressionCodeGenerator THIS = this;

        final CollectionCompiler<IExpression> cmp = new CollectionCompiler<IExpression>()
        {
            @Override
            public void compile(final IExpression element)
            {
                final IType element_type = program.symbols.expressions.get(element);

                element.accept(THIS);

                program.typesystem.utils.autoboxToObject(code(), element_type);
            }

            @Override
            public InsnList code()
            {
                return THIS.code;
            }
        };

        // Create list.
        cmp.compile(object.getElements());
    }

    @Override
    public void visit(final DispatchExpression object)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void generateDispatchHandler(final List<IType> arguments,
                                         final IMethod method,
                                         final LabelNode end)
    {
    }

    @Override
    public void visit(final CallStaticMethodExpression object)
    {
        final IMethod method = (IMethod) module.program.symbols.calls.get(object);

        compileStaticMethodCall(method, object.getArguments());
    }

    @Override
    public void visit(final SetStaticFieldExpression object)
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
    public void visit(final GetStaticFieldExpression object)
    {
        final IField field = program.symbols.fields.get(object);

        final String owner = Utils.internalName(field.getOwner());

        final String name = field.getName();

        final String desc = field.getType().getDescriptor();

        // Get the value from the field.
        code.add(new FieldInsnNode(Opcodes.GETSTATIC, owner, name, desc));
    }

    @Override
    public void visit(final NewExpression object)
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
            code.add(types.utils.assign(argument, parameter));
        }

        code.add(new MethodInsnNode(Opcodes.INVOKESPECIAL,
                                    type,
                                    method.getName(),
                                    method.getDescriptor()));
    }

    @Override
    public void visit(final CreateExpression object)
    {
        // Generated Bytecode:
        //
        // GETSTATIC type::INSTANCE
        //
        ///////////////////////////////////////////

        /**
         * Get the type that is being created, which is an interface-type.
         */
        final IReferenceType type = (IReferenceType) module.imports.resolveReturnType(object.getType());


        /**
         * The interface contains a public static final field that stores
         * a prototypical instance of the interface.
         */
        code.add(new FieldInsnNode(Opcodes.GETSTATIC,
                                   Utils.internalName(type),
                                   "INSTANCE",
                                   type.getDescriptor()));
    }

    @Override
    public void visit(final CallMethodExpression object)
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
            code.add(types.utils.assign(argument, parameter));
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
    public void visit(final SetFieldExpression object)
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
    public void visit(final GetFieldExpression object)
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
    public void visit(final InstanceOfExpression object)
    {
        final IType type = module.imports.resolveReturnType(object.getType());

        // Evaluate the expression.
        object.getValue().accept(this);

        // Perform the instance-of check.
        code.add(new TypeInsnNode(Opcodes.INSTANCEOF, Utils.internalName((IDeclaredType) type)));
    }

    @Override
    public void visit(final TernaryConditionalExpression object)
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
    public void visit(final LocalsExpression object)
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
                program.typesystem.utils.autoboxToObject(code(), type);

                // Invoke the constructor.
                code().add(new MethodInsnNode(Opcodes.INVOKESPECIAL, owner, init, desc));

                // The initialized Local object is now the topmost value on the operand-stack.
            }

            @Override
            public InsnList code()
            {
                return code;
            }
        };

        // Generate the list.
        cmp.compile(scope.getAllVisibleVariables());
    }

    @Override
    public void visit(final AsOperation object)
    {
        // The type-checker created an object that describes the conversion to perform.
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
    public void visit(final IsOperation object)
    {
        // The type-checker created an object that describes the conversion to perform.
        final Conversion conversion = program.symbols.conversions.get(object);

        if (conversion.cast)
        {
            // Evaluate the expression that produces the value to convert.
            object.getValue().accept(this);

            // Perform the checked cast.
            code.add(new TypeInsnNode(Opcodes.CHECKCAST, Utils.internalName((IReferenceType) conversion.type)));
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
    public void visit(final NegateOperation object)
    {
        compileUnaryOperator(object);
    }

    @Override
    public void visit(final NotOperation object)
    {
        compileUnaryOperator(object);
    }

    @Override
    public void visit(final DivideOperation object)
    {
        compileBinaryOperator(object);
    }

    @Override
    public void visit(final ModuloOperation object)
    {
        compileBinaryOperator(object);
    }

    @Override
    public void visit(final MultiplyOperation object)
    {
        compileBinaryOperator(object);
    }

    @Override
    public void visit(final AddOperation object)
    {
        compileBinaryOperator(object);
    }

    @Override
    public void visit(final ConcatOperation object)
    {
        compileBinaryOperator(object);
    }

    @Override
    public void visit(final SubtractOperation object)
    {
        compileBinaryOperator(object);
    }

    @Override
    public void visit(final LessThanOperation object)
    {
        compileBinaryOperator(object);
    }

    @Override
    public void visit(final LessThanOrEqualsOperation object)
    {
        compileBinaryOperator(object);
    }

    @Override
    public void visit(final GreaterThanOperation object)
    {
        compileBinaryOperator(object);
    }

    @Override
    public void visit(final GreaterThanOrEqualsOperation object)
    {
        compileBinaryOperator(object);
    }

    @Override
    public void visit(final EqualsOperation object)
    {
        compileBinaryOperator(object);
    }

    @Override
    public void visit(final NotEqualsOperation object)
    {
        compileBinaryOperator(object);
    }

    @Override
    public void visit(final IdentityEqualsOperation object)
    {
        compileBinaryOperator(object);
    }

    @Override
    public void visit(final IdentityNotEqualsOperation object)
    {
        compileBinaryOperator(object);
    }

    @Override
    public void visit(final AndOperation object)
    {
        compileBinaryOperator(object);
    }

    @Override
    public void visit(final OrOperation object)
    {
        compileBinaryOperator(object);
    }

    @Override
    public void visit(final XorOperation object)
    {
        compileBinaryOperator(object);
    }

    @Override
    public void visit(final ImpliesOperation object)
    {
        compileBinaryOperator(object);
    }

    @Override
    public void visit(final ShortCircuitAndOperation object)
    {
        // Generated Bytecode:
        //
        // <left-operand>
        // IF_FALSE @ELSE
        // <right-operand>
        // GOTO @END
        // @ELSE
        // LDC false
        // @END
        ///////////////////////////////

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
    public void visit(final ShortCircuitOrOperation object)
    {
        // Generated Bytecode:
        //
        // <left-operand>
        // IF_TRUE @ELSE
        // <right-operand>
        // GOTO @END
        // @ELSE
        // LDC true
        // @END
        ///////////////////////////////

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
    public void visit(final NullCoalescingOperation object)
    {
        // Generated Bytecode:
        //
        // <left-operand>
        // DUP
        // IF_NON_NULL @END
        // POP
        // <right-operand>
        // @END
        ///////////////////////////////

        final LabelNode END = new LabelNode();

        object.getLeftOperand().accept(this);

        code.add(new InsnNode(Opcodes.DUP));

        code.add(new JumpInsnNode(Opcodes.IFNONNULL, END));
        {
            code.add(new InsnNode(Opcodes.POP));

            object.getRightOperand().accept(this);
        }
        code.add(END);
    }
}

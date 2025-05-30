package com.mackenziehigh.autumn.lang.compiler.compilers;

import autumn.lang.annotations.Infer;
import autumn.lang.compiler.ast.commons.ConstructList;
import autumn.lang.compiler.ast.commons.IBinaryOperation;
import autumn.lang.compiler.ast.commons.IExpression;
import autumn.lang.compiler.ast.commons.IUnaryOperation;
import autumn.lang.compiler.ast.nodes.*;
import autumn.util.F;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.mackenziehigh.autumn.lang.compiler.compilers.ModuleCompiler.HiddenField;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IDeclaredType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IExpressionType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IField;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IInvokableMember;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IMethod;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IReferenceType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IReturnType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IVariableType;
import com.mackenziehigh.autumn.lang.compiler.utils.Conversion;
import com.mackenziehigh.autumn.lang.compiler.utils.TypeSystemUtils;
import com.mackenziehigh.autumn.lang.compiler.utils.Utils;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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
class ExpressionCodeGenerator
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
     * This object manages the allocation of local variables.
     */
    protected final VariableAllocator allocator;

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
        this.allocator = vars.allocator();
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
            final IType parameter = method.getParameters().get(i).getType();
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

        /**
         * Downcast the return-value, if the return-type is inferred.
         */
        if (TypeSystemUtils.isAnnotationPresent(method, Infer.class))
        {
            if (method.getParameters().isEmpty())
            {
                // Pass, there should not be a @InferReturnType annotation on the method.
            }
            else if (program.symbols.expressions.get(args.get(0)).isReferenceType() == false)
            {
                // Pass, there should not be a @InferReturnType annotation on the method.
            }
            else
            {
                final IReferenceType type = (IReferenceType) program.symbols.expressions.get(args.get(0));

                code.add(new TypeInsnNode(Opcodes.CHECKCAST, Utils.internalName(type)));
            }
        }
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

        final long value = object.getValue().value();

        code.add(new LdcInsnNode(value));
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
        /////////////////////////////////////////////////////////////////////////

        final double value = object.getValue().value();

        code.add(new LdcInsnNode(value));
    }

    @Override
    public void visit(final BigIntegerDatum object)
    {
        // Generated Bytecode;
        //
        // LDC source
        // INVOKESTATIC Helpers.createBigInteger(String) : BigInteger
        //
        /////////////////////////////////////////////////////////////////////////

        final String source = object.getValue().value().toString();

        code.add(new LdcInsnNode(source));

        code.add(new MethodInsnNode(Opcodes.INVOKESTATIC,
                                    Utils.internalName(program.typesystem.utils.HELPERS),
                                    "createBigInteger",
                                    "(Ljava/lang/String;)Ljava/math/BigInteger;"));
    }

    @Override
    public void visit(final BigDecimalDatum object)
    {
        // Generated Bytecode;
        //
        // LDC source
        // INVOKESTATIC Helpers.createBigDecimal(String) : BigDecimal
        //
        /////////////////////////////////////////////////////////////////////////

        final String source = object.getValue().value().toString();

        code.add(new LdcInsnNode(source));

        code.add(new MethodInsnNode(Opcodes.INVOKESTATIC,
                                    Utils.internalName(program.typesystem.utils.HELPERS),
                                    "createBigDecimal",
                                    "(Ljava/lang/String;)Ljava/math/BigDecimal;"));
    }

    @Override
    public void visit(final StringDatum object)
    {
        // Generated Bytecode;
        //
        // LDC value
        //
        /////////////////////////////////////////////////

        final String value = object.getValue();

        final String string = object.getVerbatim() ? value : F.escape(value);

        code.add(new LdcInsnNode(string));
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
        // Generated Bytecode;
        //
        // LDC class
        //
        /////////////////////////////////////////////////

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
    public void visit(final ListComprehensionExpression object)
    {
        // Generated Bytecode:
        //
        // NEW LinkedList                       - Create a new uninitialized linked-list.
        // DUP                                  - Duplicate the reference to the list.
        // INVOKESPECIAL <init>()               - Initialize the linked-list.
        // ASTORE list                          - Store the list in a temporary variable.
        //                                      - This list will be the result of the comprehension.
        //
        // <iterable>                           - Evaluate the iterable.
        // INVOKEINTERFACE iterable.iterator()  - Get an Iterator from the Iterable.
        // ASTORE iterator                      - Save the iterator for later.
        //
        // @CONTINUE                            - This is where each iteration starts.
        // ALOAD iterator                       - Load the iterator object.
        // INVOKEINTERFACE iterator.hasNext()   - Ask whether the iterator has more elements.
        // IF_FALSE @BREAK                      - If no, we can now exit the loop.
        //
        // ALOAD iterator                       - Load the iterator object.
        // INVOKEINTERFACE iterator.next()      - Get the next element in the sequence.
        // CHECKCAST type                       - Cast the element to the required type.
        // ASTORE variable                      - Put the element into the variable.
        //
        // @REDO                                - This is where redo-statements jump to.
        //
        //
        //                                      - The following code is not always generated.
        // <condition>                          - Evaluate the condition.
        // <unbox>                              - Unbox the condition, if needed.
        // IF_FALSE @CONTINUE                   - If the condition is false skip the element.
        //                                      - The code above is not always generated.
        //
        // <modifier>                           - Evaluate the modifier expression.
        // <box>                                - Box the result, if needed.
        // ALOAD list                           - Get the list that is being created.
        // INVOKEINTERFACE List.add(Object)     - Add the result to the list.
        // POP                                  - Pop the return-value of the add(Object) method.
        //
        // GOTO CONTINUE                        - Goto the start of the next iteration.
        //
        // @BREAK                               - This is the exit-point of the loop.
        //
        // ACONST_NULL                          - Load null.
        // ASTORE iterator                      - Clear the iterator temporary variable.
        //
        // ALOAD list                           - Return the list.
        //
        // ACONST_NULL                          - Load null.
        // ASTORE list                          - Clear the list temporary variable.
        //
        //////////////////////////////////////////////////////////////////////////

        final IDeclaredType type = (IDeclaredType) module.imports.resolveVariableType(object.getType());

        final LabelNode BREAK = new LabelNode();
        final LabelNode CONTINUE = new LabelNode();
        final LabelNode REDO = new LabelNode();

        /**
         * Declare the temporary variable that will store the iterator.
         */
        final String iterator = "autumn$temp$" + F.unique();
        allocator.declareTemp(iterator, program.typesystem.utils.OBJECT);

        /**
         * Declare the temporary variable that will store the list.
         */
        final String list = "autumn$temp$" + F.unique();
        allocator.declareTemp(list, program.typesystem.utils.LIST);

        // Create the list and put it into the variable that will store it.
        code.add(new TypeInsnNode(Opcodes.NEW, "java/util/LinkedList"));
        code.add(new InsnNode(Opcodes.DUP));
        code.add(new MethodInsnNode(Opcodes.INVOKESPECIAL,
                                    "java/util/LinkedList",
                                    "<init>",
                                    "()V"));
        vars.store(list);

        // Evaluate the iterable.
        object.getIterable().accept(this);

        // Get an iterator over the iterable.
        code.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE,
                                    "java/lang/Iterable",
                                    "iterator",
                                    "()Ljava/util/Iterator;"));

        // Save the iterator for later.
        vars.store(iterator);

        code.add(CONTINUE);

        // If the iterator is consumed, then break out of the loop.
        vars.load(iterator);
        code.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE,
                                    "java/util/Iterator",
                                    "hasNext",
                                    "()Z"));
        code.add(new JumpInsnNode(Utils.IF_FALSE, BREAK));

        // Get the next element from the iterator.
        vars.load(iterator);
        code.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE,
                                    "java/util/Iterator",
                                    "next",
                                    "()Ljava/lang/Object;"));

        // Cast the value to the expected type.
        code.add(new TypeInsnNode(Opcodes.CHECKCAST, Utils.internalName(type)));

        // Assign the value to the variable.
        vars.store(object.getVariable().getName());

        // Execute the body of the loop.
        code.add(REDO);
        {
            // Evaluate the condition, if it exists.
            // If the condition evaluates to false, jump to @CONTINUE.
            if (object.getCondition() != null)
            {
                compileCondition(object.getCondition());
                code.add(new JumpInsnNode(Utils.IF_FALSE, CONTINUE));
            }

            // Evaluate the expression that produces the value to add to the list.
            object.getModifier().accept(this);

            // Autobox the value, if needed.
            program.typesystem.utils.autoboxToObject(code, program.symbols.expressions.get(object.getModifier()));

            // Add the value to the list.
            vars.load(list);
            code.add(new InsnNode(Opcodes.SWAP));
            code.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE,
                                        "java/util/List",
                                        "add",
                                        "(Ljava/lang/Object;)Z"));
            code.add(new InsnNode(Opcodes.POP)); // Pop the boolean off the operand-stack.
        }
        code.add(new JumpInsnNode(Opcodes.GOTO, CONTINUE));
        code.add(BREAK);

        // Clear the iterator temporary variable.
        code.add(new InsnNode(Opcodes.ACONST_NULL));
        vars.store(iterator);

        // Load the generated list onto the operand-stack.
        vars.load(list);

        // Clear the list temporary variable.
        code.add(new InsnNode(Opcodes.ACONST_NULL));
        vars.store(list);
    }

    @Override
    public void visit(final DispatchExpression object)
    {
        /**
         * Dispatch-expressions are compiled using a specialized object,
         * because of the intricacy of such expressions.
         */
        final DispatchCompiler cmp = program.symbols.dispatches.get(object);

        /**
         * Generate the bytecode.
         */
        cmp.compile(this);
    }

    @Override
    public void visit(final CallStaticMethodExpression object)
    {
        /**
         * Get the method overload that was selected during type-checking.
         */
        final IMethod method = (IMethod) module.program.symbols.calls.get(object);

        /**
         * Generate the bytecode that evaluates the arguments invokes the overload.
         */
        compileStaticMethodCall(method, object.getArguments());
    }

    @Override
    public void visit(final SetStaticFieldExpression object)
    {
        // Generated Bytecode:
        //
        // <value>                   - Evaluate the value expression.
        // <convert>                 - Box, Unbox, or Coerce the value, if needed.
        // PUTSTATIC field = value   - Put the value into the field.
        //
        ////////////////////////////////////////////////////////////////////

        /**
         * Get the type of the value.
         */
        final IExpressionType value = program.symbols.expressions.get(object.getValue());

        /**
         * Get the field that was selected during type-checking.
         */
        final IField field = program.symbols.fields.get(object);

        final String owner = Utils.internalName(field.getOwner());
        final String name = field.getName();
        final String desc = field.getType().getDescriptor();

        // Evaluate the expression that produces the value to put into the field.
        object.getValue().accept(this);

        // Convert the value to the type stored in the field.
        code.add(program.typesystem.utils.assign(value, field.getType()));

        // Set the field.
        code.add(new FieldInsnNode(Opcodes.PUTSTATIC, owner, name, desc));
    }

    @Override
    public void visit(final GetStaticFieldExpression object)
    {
        // Generated Bytecode:
        //
        // GETSTATIC field = value   - Get the value from the field.
        //
        ////////////////////////////////////////////////////////////////////

        /**
         * Get the field that was selected during type-checking.
         */
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
        // Generated Bytecode:
        //
        // NEW type              - Create a new uninitialized instance of the type.
        // DUP                   - Duplicate the reference to the instance.
        // <argument [0]>        - Evaluate argument[0]
        // <argument [1]>        - Evaluate argument[1]
        // <argument [2]>        - Evaluate argument[2]
        // <argument [.]>        - Evaluate argument[.]
        // <argument [N]>        - Evaluate argument[N]
        // INVOKESPECIAL <init>  - Invoke the selected constructor.
        //
        /////////////////////////////////////////////////////////

        /**
         * Get the constructor overload that was selected during type-checking.
         */
        final IInvokableMember method = program.symbols.calls.get(object);

        /**
         * Compute the internal name of the type that will be instantiated.
         */
        final String type = Utils.internalName(method.getOwner());

        /**
         * Generate the bytecode.
         */
        code.add(new TypeInsnNode(Opcodes.NEW, type));

        code.add(new InsnNode(Opcodes.DUP));

        final List<IExpression> arguments = object.getArguments().asMutableList();

        for (int i = 0; i < arguments.size(); i++)
        {
            final IType parameter = method.getParameters().get(i).getType();
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
    public void visit(final CallMethodExpression object)
    {
        // Generated Bytecode:
        //
        // <owner>               - Evaluate the owner expression.
        // <argument [0]>        - Evaluate argument[0]
        // <argument [1]>        - Evaluate argument[1]
        // <argument [2]>        - Evaluate argument[2]
        // <argument [.]>        - Evaluate argument[.]
        // <argument [N]>        - Evaluate argument[N]
        // INVOKE-X method       - Invoke the selected method overload.
        //
        ///////////////////////////////////////////////////////////////////////

        /**
         * Get the method overload that was selected during type-checking.
         */
        final IMethod method = (IMethod) program.symbols.calls.get(object);

        /**
         * Generate the bytecode of the owner expression.
         */
        object.getOwner().accept(this);

        /**
         * Generate the bytecode of the arguments.
         */
        final List<IExpression> arguments = object.getArguments().asMutableList();

        for (int i = 0; i < arguments.size(); i++)
        {
            final IType parameter = method.getParameters().get(i).getType();
            final IType argument = program.symbols.expressions.get(arguments.get(i));

            // Generate the argument's bytecode.
            arguments.get(i).accept(this);

            // Generate code to box/unbox the argument, if needed.
            code.add(types.utils.assign(argument, parameter));
        }

        /**
         * Generate the actual invocation.
         */
        final int opcode = method.getOwner().isInterfaceType()
                ? Opcodes.INVOKEINTERFACE
                : Opcodes.INVOKEVIRTUAL;

        code.add(new MethodInsnNode(opcode,
                                    Utils.internalName(method.getOwner()),
                                    method.getName(),
                                    method.getDescriptor()));
    }

    @Override
    public void visit(final SetFieldExpression object)
    {
        // Generated Bytecode:
        //
        // <owner>                        - Evaluate the owner expression.
        // <value>                        - Evaluate the value expression.
        // <convert>                      - Box, Unbox, or Coerce the value, if needed.
        // PUTFIELD owner.field = value   - Put the value into the field.
        //
        /////////////////////////////////////////////////////////////////////////////////////

        /**
         * Get the type of the value.
         */
        final IExpressionType value = program.symbols.expressions.get(object.getValue());

        /**
         * Get the field that was chosen during type-checking.
         */
        final IField field = program.symbols.fields.get(object);

        final String owner = Utils.internalName(field.getOwner());
        final String name = field.getName();
        final String desc = field.getType().getDescriptor();

        // Evaluate the expression that produces the owner of the field.
        object.getOwner().accept(this);

        // Evaluate the expression that produces the value to put into the field.
        object.getValue().accept(this);

        // Convert the value to the type stored in the field.
        code.add(program.typesystem.utils.assign(value, field.getType()));

        // Set the field.
        code.add(new FieldInsnNode(Opcodes.PUTFIELD, owner, name, desc));
    }

    @Override
    public void visit(final GetFieldExpression object)
    {
        // Generated Bytecode:
        //
        // <owner>                - Evaluate the owner expression.
        // GETFIELD owner.field   - Get the value stored in the field.
        //
        //////////////////////////////////////////////////////////////////

        // Get the field that was chosen during type-checking.
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
        // Generated Bytecode:
        //
        // <value>                   - Evaluate the value expression.
        // INSTANCEOF value : type   - Perform the instance-of operation.
        //
        ////////////////////////////////////////////////////////////////////

        final IType type = module.imports.resolveDeclaredType(object.getType());

        // Evaluate the expression.
        object.getValue().accept(this);

        // Perform the instance-of check.
        code.add(new TypeInsnNode(Opcodes.INSTANCEOF, Utils.internalName((IDeclaredType) type)));
    }

    @Override
    public void visit(final TernaryConditionalExpression object)
    {
        // Generated Bytecode:
        //
        // <condition>       - Evaluate the condition expression.
        // IF_FALSE @ELSE    - Conditionally jump to the expression to evaluate.
        // <true-case>       - Evaluate the true-case expression.
        // GOTO @END         - Exit the expression.
        // @ELSE
        // <false-case>      - Evaluate the false-case expression.
        // @END
        //
        ////////////////////////////////////////////////////////////////////////////////////

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
    public void visit(final OrOperation object)
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
    public void visit(final XorOperation object)
    {
        // Generated Bytecode:
        //
        // <left-operand>
        // <right-operand>
        // IXOR
        //
        ///////////////////////////////

        compileCondition(object.getLeftOperand());
        compileCondition(object.getRightOperand());

        code.add(new InsnNode(Opcodes.IXOR));
    }

    @Override
    public void visit(final ImpliesOperation object)
    {
        // Generated Bytecode:
        //
        // <left-operand>
        // IF_FALSE @ELSE
        // <right-operand>
        // GOTO @END
        // @ELSE
        // LDC true
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

    @Override
    public void visit(final OnceExpression object)
    {
        // Generated Bytecode;
        //
        // GETSTATIC hidden.status  - Get the status flag, which indicates whether a memoized value is available.
        // IF_FALSE @ELSE           - If no memoized value is available, this is the first evaluation of the expression.
        //
        // GETSTATIC hidden.value   - Get the previously memoized value.
        // GOTO @END                - We will simply return the previously memoized value.
        //
        //
        // @ELSE
        //
        // LDC true
        // PUTSTATIC hidden.status  - Set the status flag to indicate that a memoized value is now available.
        // <value>                  - Evaluate the expression that produces the value to store.
        // DUP                      - One copy will be memoized and the other one will be returned.
        // PUTSTATIC hidden.value   - Place the value into the hidden field.
        //
        // @END
        //
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        final LabelNode ELSE = new LabelNode();
        final LabelNode END = new LabelNode();

        final HiddenField status = new HiddenField(program.typesystem.utils.PRIMITIVE_BOOLEAN, false);
        final HiddenField value = new HiddenField((IVariableType) program.symbols.expressions.get(object), false);

        module.hidden.add(status);
        module.hidden.add(value);

        code.add(new FieldInsnNode(Opcodes.GETSTATIC,
                                   Utils.internalName(module.type),
                                   status.name,
                                   status.type.getDescriptor()));

        code.add(new JumpInsnNode(Utils.IF_FALSE, ELSE));

        code.add(new FieldInsnNode(Opcodes.GETSTATIC,
                                   Utils.internalName(module.type),
                                   value.name,
                                   value.type.getDescriptor()));

        code.add(new JumpInsnNode(Opcodes.GOTO, END));

        code.add(ELSE);

        code.add(new LdcInsnNode(true));

        code.add(new FieldInsnNode(Opcodes.PUTSTATIC,
                                   Utils.internalName(module.type),
                                   status.name,
                                   status.type.getDescriptor()));

        object.getValue().accept(this);

        code.add(new InsnNode(Opcodes.DUP));

        code.add(new FieldInsnNode(Opcodes.PUTSTATIC,
                                   Utils.internalName(module.type),
                                   value.name,
                                   value.type.getDescriptor()));

        code.add(END);
    }

    @Override
    public void visit(final LocalsExpression object)
    {
        /**
         * Get the names of the variables that will be captured.
         */
        final Set<String> visible = program.symbols.locals.get(object);

        /**
         * Generate the bytecode that creates the LocalsMap.
         */
        loadLocalsMap(object.getLocation(), visible);
    }

    /**
     * This method generates bytecode that creates a LocalsMap object.
     *
     * @param location is the source-location to embed in the LocalsMap.
     * @param variables are the names of the local variables captured in the LocalsMap.
     */
    protected void loadLocalsMap(final SourceLocation location,
                                 final Set<String> variables)
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
        loadLocals(variables);

        // Invoke the constructor.
        code.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, owner, name, desc));

        // The initialized LocalsMap is now the topmost value on the operand-stack.
    }

    /**
     * This method generates bytecode that creates a list of Local objects.
     *
     * @param variables are the names of the local variables described by the elements of the list.
     */
    private void loadLocals(final Set<String> variables)
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

                final IVariableType type = allocator.typeOf(name);

                final int address = allocator.addressOf(name);

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
        cmp.compile(variables);
    }
}

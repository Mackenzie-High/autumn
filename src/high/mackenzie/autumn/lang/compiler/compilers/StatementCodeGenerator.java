package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ast.commons.*;
import autumn.lang.compiler.ast.nodes.*;
import com.google.common.collect.Lists;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IDeclaredType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IType;
import high.mackenzie.autumn.lang.compiler.utils.Utils;
import java.util.List;
import java.util.Stack;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.TryCatchBlockNode;
import org.objectweb.asm.tree.TypeInsnNode;

/**
 * An instance of this class can generate the bytecode that implements a statement.
 *
 * @author Mackenzie High
 */
public final class StatementCodeGenerator
        extends ExpressionCodeGenerator
{
    private final FunctionCompiler function;

    private final Stack<LabelNode> break_labels = new Stack<LabelNode>();

    private final Stack<LabelNode> continue_labels = new Stack<LabelNode>();

    private final Stack<LabelNode> redo_labels = new Stack<LabelNode>();

    public StatementCodeGenerator(final FunctionCompiler function)
    {
        super(function.module, function.vars, function.instructions);

        this.function = function;
    }

    @Override
    public void visit(IfStatement object)
    {
        final LabelNode END = new LabelNode();

        final List<ConditionalCase> cases = Lists.newLinkedList();
        cases.add(object.getMainCase());
        cases.addAll(object.getElifCases().asMutableList());

        for (ConditionalCase cc : cases)
        {
            final LabelNode END_OF_CASE = new LabelNode();

            compileCondition(cc.getCondition());
            code.add(new JumpInsnNode(Utils.IF_FALSE, END_OF_CASE));
            {
                cc.getBody().accept(this);
                code.add(new JumpInsnNode(Opcodes.GOTO, END));
            }
            code.add(END_OF_CASE);
        }

        if (object.getElseCase() != null)
        {
            object.getElseCase().accept(this);
        }

        code.add(END);
    }

    @Override
    public void visit(WhenStatement object)
    {
        final LabelNode END = new LabelNode();

        compileCondition(object.getCondition());
        code.add(new JumpInsnNode(Utils.IF_FALSE, END));
        object.getBody().accept(this);
        code.add(END);
    }

    @Override
    public void visit(GotoStatement object)
    {
        final String name = object.getLabel().getName();

        final LabelNode node = function.labels.nodeOf(name);

        code.add(new JumpInsnNode(Opcodes.GOTO, node));
    }

    @Override
    public void visit(MarkerStatement object)
    {
        final String name = object.getLabel().getName();

        final LabelNode node = function.labels.nodeOf(name);

        code.add(node);
    }

    @Override
    public void visit(WhileStatement object)
    {
        final LabelNode BREAK = new LabelNode();
        final LabelNode CONTINUE = new LabelNode();
        final LabelNode REDO = new LabelNode();

        code.add(CONTINUE);
        compileCondition(object.getCondition());
        code.add(new JumpInsnNode(Utils.IF_FALSE, BREAK));
        code.add(REDO);
        {
            break_labels.push(BREAK);
            continue_labels.push(CONTINUE);
            redo_labels.push(REDO);

            object.getBody().accept(this);

            break_labels.pop();
            continue_labels.pop();
            redo_labels.pop();
        }
        code.add(new JumpInsnNode(Opcodes.GOTO, CONTINUE));
        code.add(BREAK);
    }

    @Override
    public void visit(UntilStatement object)
    {
        final LabelNode BREAK = new LabelNode();
        final LabelNode CONTINUE = new LabelNode();
        final LabelNode REDO = new LabelNode();

        code.add(CONTINUE);
        compileCondition(object.getCondition());
        code.add(new JumpInsnNode(Utils.IF_TRUE, BREAK));
        code.add(REDO);
        {
            break_labels.push(BREAK);
            continue_labels.push(CONTINUE);
            redo_labels.push(REDO);

            object.getBody().accept(this);

            break_labels.pop();
            continue_labels.pop();
            redo_labels.pop();
        }
        code.add(new JumpInsnNode(Opcodes.GOTO, CONTINUE));
        code.add(BREAK);
    }

    @Override
    public void visit(DoWhileStatement object)
    {
        final LabelNode BREAK = new LabelNode();
        final LabelNode CONTINUE = new LabelNode();
        final LabelNode REDO = new LabelNode();
        final LabelNode TOP = new LabelNode();

        code.add(TOP);
        code.add(REDO);
        {
            break_labels.push(BREAK);
            continue_labels.push(CONTINUE);
            redo_labels.push(REDO);

            object.getBody().accept(this);

            break_labels.pop();
            continue_labels.pop();
            redo_labels.pop();
        }
        code.add(CONTINUE);
        compileCondition(object.getCondition());
        code.add(new JumpInsnNode(Utils.IF_TRUE, TOP));
        code.add(BREAK);
    }

    @Override
    public void visit(DoUntilStatement object)
    {
        final LabelNode BREAK = new LabelNode();
        final LabelNode CONTINUE = new LabelNode();
        final LabelNode REDO = new LabelNode();
        final LabelNode TOP = new LabelNode();

        code.add(TOP);
        code.add(REDO);
        {
            break_labels.push(BREAK);
            continue_labels.push(CONTINUE);
            redo_labels.push(REDO);

            object.getBody().accept(this);

            break_labels.pop();
            continue_labels.pop();
            redo_labels.pop();
        }
        code.add(CONTINUE);
        compileCondition(object.getCondition());
        code.add(new JumpInsnNode(Utils.IF_FALSE, TOP));
        code.add(BREAK);
    }

    @Override
    public void visit(ForStatement object)
    {
        // TODO: Finish. Be careful with continue.

        final LabelNode BREAK = new LabelNode();
        final LabelNode CONTINUE = new LabelNode();
        final LabelNode REDO = new LabelNode();
        final LabelNode TOP = new LabelNode();

        object.getInitializer().accept(this);
        vars.store(object.getVariable().getName(), true);

        code.add(TOP);
        compileCondition(object.getCondition());
        code.add(new JumpInsnNode(Utils.IF_FALSE, BREAK));
        code.add(REDO);
        {
            break_labels.push(BREAK);
            continue_labels.push(CONTINUE);
            redo_labels.push(REDO);

            object.getBody().accept(this);

            code.add(CONTINUE);

            object.getNext().accept(this);
            vars.store(object.getVariable().getName(), true);

            break_labels.pop();
            continue_labels.pop();
            redo_labels.pop();
        }
        code.add(new JumpInsnNode(Opcodes.GOTO, TOP));
        code.add(BREAK);
    }

    @Override
    public void visit(ForeachStatement object)
    {
        final IDeclaredType type = (IDeclaredType) function.module.resolveType(object.getType());

        final LabelNode BREAK = new LabelNode();
        final LabelNode CONTINUE = new LabelNode();
        final LabelNode REDO = new LabelNode();

        // Evaluate the iterable.
        object.getIterable().accept(this);

        // Get an iterator over the iterable.
        code.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE,
                                    "java/lang/Iterable",
                                    "iterator",
                                    "()Ljava/util/Iterator;"));

        code.add(CONTINUE);

        // If the iterator is consumed, then break out of the loop.
        code.add(new InsnNode(Opcodes.DUP));
        code.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE,
                                    "java/util/Iterator",
                                    "hasNext",
                                    "()Z"));
        code.add(new JumpInsnNode(Utils.IF_FALSE, BREAK));

        // Get the next element from the iterator.
        code.add(new InsnNode(Opcodes.DUP));
        code.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE,
                                    "java/util/Iterator",
                                    "next",
                                    "()Ljava/lang/Object;"));

        // Cast/convert the value.
        code.add(new TypeInsnNode(Opcodes.CHECKCAST, Utils.internalName(type)));

        // Assign the value to the variable.
        function.vars.store(object.getVariable().getName(), true);

        // Execute the body of the loop.
        code.add(REDO);
        {
            break_labels.push(BREAK);
            continue_labels.push(CONTINUE);
            redo_labels.push(REDO);

            object.getBody().accept(this);

            break_labels.pop();
            continue_labels.pop();
            redo_labels.pop();
        }
        code.add(new JumpInsnNode(Opcodes.GOTO, CONTINUE));
        code.add(BREAK);

        // Remove the iterable from the operand-stack.
        code.add(new InsnNode(Opcodes.POP));
    }

    @Override
    public void visit(BreakStatement object)
    {
        final LabelNode BREAK = break_labels.peek();

        code.add(new JumpInsnNode(Opcodes.GOTO, BREAK));
    }

    @Override
    public void visit(ContinueStatement object)
    {
        final LabelNode CONTINUE = continue_labels.peek();

        code.add(new JumpInsnNode(Opcodes.GOTO, CONTINUE));
    }

    @Override
    public void visit(RedoStatement object)
    {
        final LabelNode REDO = continue_labels.peek();

        code.add(new JumpInsnNode(Opcodes.GOTO, REDO));
    }

    @Override
    public void visit(VarStatement object)
    {
        final String name = object.getVariable().getName();

        object.getValue().accept(this);

        vars.store(name, true);
    }

    @Override
    public void visit(ValStatement object)
    {
        final String name = object.getVariable().getName();

        object.getValue().accept(this);

        vars.store(name, true);
    }

    @Override
    public void visit(LetStatement object)
    {
        final String name_of_variable = object.getVariable().getName();

        final IType type_of_variable = function.scope.typeOf(name_of_variable);

        // Evaluate the expression that produces the value to assign to the variable.
        object.getValue().accept(this);

        // Perform auto-boxing or auto-unboxing, if needed.
        convert(type_of_variable, object.getValue());

        // Assign the value to the variable.
        vars.store(object.getVariable().getName(), false);
    }

    @Override
    public void visit(SetterStatement object)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visit(GetterStatement object)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visit(MethodStatement object)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visit(SequenceStatement object)
    {
        for (IStatement s : object.getElements())
        {
            s.accept(this);
        }
    }

    @Override
    public void visit(ExpressionStatement object)
    {
        object.getExpression().accept(this);

        final IType type = program.symbols.expressions.get(object.getExpression());

        code.add(Utils.selectPop(type));
    }

    @Override
    public void visit(NopStatement object)
    {
        code.add(new InsnNode(Opcodes.NOP));
    }

    @Override
    public void visit(DebugStatement object)
    {
        String owner;
        String name;
        String desc;

        code.add(new LdcInsnNode(object.getLocation().getFile().toString()));
        code.add(new LdcInsnNode(object.getLocation().getLine()));
        code.add(new LdcInsnNode(object.getLocation().getColumn()));
        super.loadLocalsMap(object.getLocation());

        owner = Utils.internalName(program.typesystem.utils.HELPERS);
        name = "debug";
        desc = "(Ljava/lang/String;IILautumn/lang/LocalsMap;)V";
        code.add(new MethodInsnNode(Opcodes.INVOKESTATIC, owner, name, desc));
    }

    @Override
    public void visit(TryCatchStatement object)
    {
        final List<ExceptionHandler> handlers = program.symbols.handlers.get(object);

        /**
         * This label marks the start of the protected region.
         */
        final LabelNode START = new LabelNode();

        /**
         * This label marks the end of the protected region.
         */
        final LabelNode END = new LabelNode();

        /**
         * This label node marks the end of the entire try-catch construct.
         */
        final LabelNode EXIT = new LabelNode();

        // Enter the protected region.
        code.add(START);

        // Execute the protected region.
        // If an exception occurs, jump directly to the appropriate handler.
        object.getBody().accept(this);

        // Exit the protected region.
        code.add(END);

        // Since no exception occurred, skip the exception handlers.
        code.add(new JumpInsnNode(Opcodes.GOTO, EXIT));

        // Compile the exception handlers.
        for (ExceptionHandler handler : handlers)
        {
            // Compile a single exception handler.
            generateExceptionHandler(START, END, handler);

            // If the handler was executed, then immediately exit the try-catch construct.
            code.add(new JumpInsnNode(Opcodes.GOTO, EXIT));
        }

        // Exit the try-catch construct.
        code.add(EXIT);
    }

    private void generateExceptionHandler(final LabelNode start,
                                          final LabelNode end,
                                          final ExceptionHandler handler)
    {
        /**
         * This is the name of the variable that will contain the exception object.
         */
        final String variable = handler.getVariable().getName();

        /**
         * This is the name of the type that this exception-handler handles.
         */
        final String type = Utils.internalName((IDeclaredType) module.resolveType(handler.getType()));

        /**
         * This label marks the entry-point of this try-catch handler.
         */
        final LabelNode CATCHER = new LabelNode();

        /**
         * This object describes the simplified try-catch block created by this exception-handler.
         */
        final TryCatchBlockNode block = new TryCatchBlockNode(start, end, CATCHER, type);

        // Add the exception-handler to the function.
        function.trycatches.add(block);

        // Enter the exception handler.
        code.add(CATCHER);

        // Store the exception object in the appropriate variable.
        vars.store(variable, true);

        // Execute the exception handling code.
        handler.getHandler().accept(this);
    }

    @Override
    public void visit(ThrowStatement object)
    {
        object.getValue().accept(this);

        code.add(new InsnNode(Opcodes.ATHROW));
    }

    @Override
    public void visit(AssertStatement object)
    {
        final String descriptor;

        final LabelNode END = new LabelNode();

        compileCondition(object.getCondition());

        code.add(new JumpInsnNode(Utils.IF_TRUE, END));

        code.add(new TypeInsnNode(Opcodes.NEW, "autumn/lang/exceptions/AssertionFailedException"));

        code.add(new InsnNode(Opcodes.DUP));

        code.add(new LdcInsnNode(object.getLocation().getFile().toString()));

        code.add(new LdcInsnNode(object.getLocation().getLine()));

        if (object.getMessage() == null)
        {
            descriptor = "(Ljava/lang/String;I)V";
        }
        else
        {
            descriptor = "(Ljava/lang/String;ILjava/lang/String;)V";

            object.getMessage().accept(this);
        }

        code.add(new MethodInsnNode(Opcodes.INVOKESPECIAL,
                                    "autumn/lang/exceptions/AssertionFailedException",
                                    "<init>",
                                    descriptor));

        code.add(new InsnNode(Opcodes.ATHROW));

        code.add(END);
    }

    @Override
    public void visit(ReturnVoidStatement object)
    {
        // Conditionally save the state of the function's local-variables.
        if (function.isGenerator())
        {
            saveState();
        }

        code.add(new InsnNode(Opcodes.RETURN));
    }

    @Override
    public void visit(ReturnValueStatement object)
    {
        // Conditionally save the state of the function's local-variables.
        if (function.isGenerator())
        {
            saveState();
        }

        // Evaluate the expression that produces the value to return.
        object.getValue().accept(this);

        // Perform auto-boxing or auto-unboxing, if needed.
        convert(function.type.getReturnType(), object.getValue());

        // Return.
        code.add(Utils.selectReturnInsn(function.type.getReturnType()));
    }

    @Override
    public void visit(RecurStatement object)
    {
        int i = 0;

        // Evaluate the arguments and store them in the parameter variables.
        for (IExpression arg : object.getArguments())
        {
            final String parameter = function.node // Get the function's AST node.
                    .getParameters() // Get formal-parameter-list.
                    .getParameters() // From the formal-parameter-list, get the actual list.
                    .asMutableList() // Convert the list, because we need the get(int) method.
                    .get(i++) // Get the ith formal-parameter in the list.
                    .getVariable() // We only need the variable, not the type.
                    .getName(); // Get the name of the variable as a string.

            // Generate the argument's bytecode.
            arg.accept(this);

            // TODO: autoboxing?

            // Store the value of the argument in the appropriate local-variable.
            function.vars.store(parameter, true);
        }

        // Goto the start of the function.
        code.add(new JumpInsnNode(Opcodes.GOTO, function.recur_label));
    }

    @Override
    public void visit(YieldVoidStatement object)
    {
        /**
         * This label marks the location where code will be reentered.
         */
        final LabelNode label = program.symbols.yields.get(object);

        // Save the function's reentry-point.
        saveReentryIndex(object);

        // Save the state of the function's local variables.
        saveState();

        // Yield.
        code.add(new InsnNode(Opcodes.RETURN));

        // Reentry Point.
        code.add(label);
    }

    @Override
    public void visit(YieldValueStatement object)
    {
        /**
         * This label marks the location where code will be reentered.
         */
        final LabelNode label = program.symbols.yields.get(object);

        // Save the function's reentry-point.
        saveReentryIndex(object);

        // Save the state of the function's local variables.
        saveState();

        // Evaluate the expression to yield.
        object.getValue().accept(this);

        // Perform auto-boxing or auto-unboxing, if needed.
        convert(function.type.getReturnType(), object.getValue());

        // Yield.
        code.add(Utils.selectReturnInsn(function.type.getReturnType()));

        // Reentry Point.
        code.add(label);
    }

    /**
     * This method generates bytecode that causes a generator function to save the index of the
     * yield statement where the function will be reentered, if the function is reentered.
     *
     * @param yield is the yield statement that is being compiled.
     */
    private void saveReentryIndex(final IStatement yield)
    {
        assert function.isGenerator();

        String owner;
        String name;
        String desc;

        /**
         * This is the field that stores the state of the method, between invocations.
         */
        final FieldNode field = function.yieldField();

        /**
         * This label marks the location where code will be reentered.
         */
        final LabelNode label = program.symbols.yields.get(yield);

        // Load the yield-state object onto the operand-stack.
        owner = Utils.internalName(function.module.type);
        name = field.name;
        desc = field.desc;
        code.add(new FieldInsnNode(Opcodes.GETSTATIC, owner, name, desc));

        // Duplicate the reference to the yield-state object.
        code.add(new InsnNode(Opcodes.DUP));

        // Load the reentry index onto the operand-stack, if necessary.
        final int index = function.yields.indexOf(label);
        code.add(new LdcInsnNode(index));

        // Set the reentry index.
        owner = Utils.internalName(program.typesystem.utils.YIELD_STATE);
        name = "setIndex";
        desc = "(I)V";
        code.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, owner, name, desc));
    }

    /**
     * This function generates the bytecode that causes a generator function to save the state
     * of the function's local variables.
     */
    private void saveState()
    {
        assert function.isGenerator();

        String owner;
        String name;
        String desc;

        /**
         * This is the field that stores the state of the method, between invocations.
         */
        final FieldNode field = function.yieldField();

        // Load the yield-state object onto the operand-stack.
        owner = Utils.internalName(function.module.type);
        name = field.name;
        desc = field.desc;
        code.add(new FieldInsnNode(Opcodes.GETSTATIC, owner, name, desc));

        // Load the storage-stack onto the operand-stack.
        owner = "autumn/lang/internals/YieldState";
        name = "stack";
        desc = "()Lautumn/lang/internals/ArgumentStack;";
        code.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, owner, name, desc));

        // Store each variable.
        for (String variable : function.scope.getAllVariables())
        {
            // Skip parameters, because they are unique to each invocation.
            if (function.scope.isParameter(variable))
            {
                continue;
            }

            // Duplicate the reference to the storage-stack.
            code.add(new InsnNode(Opcodes.DUP));

            // Load the variable's value onto the operand-stack.
            function.vars.load(variable);

            // Push the variable's value onto the storage-stack.
            Utils.pushArgument(program, code, function.scope.typeOf(variable));
        }

        // Pop the storage-stack off of the operand-stack.
        code.add(new InsnNode(Opcodes.POP));
    }
}

package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ast.commons.IExpression;
import autumn.lang.compiler.ast.nodes.DispatchExpression;
import autumn.util.F;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IExpressionType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IFormalParameter;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IReferenceType;
import high.mackenzie.autumn.lang.compiler.utils.Utils;
import high.mackenzie.autumn.resources.Finished;
import java.util.List;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.TypeInsnNode;

/**
 * An instance of this class performs the compilation of a dispatch-expression.
 *
 * @author Mackenzie High
 */
@Finished("2015/01/16")
final class DispatchCompiler
{
    /**
     * This is the Abstract Syntax Tree representation of the dispatch-expression itself.
     */
    private final DispatchExpression node;

    /**
     * Essentially, this is the module that encloses the dispatch-expression.
     */
    private final ModuleCompiler module;

    /**
     * This object controls the allocation of local variables in the enclosing function.
     */
    private final VariableAllocator allocator;

    /**
     * This object simplifies the manipulation of local-variables.
     */
    private final VariableManipulator vars;

    /**
     * This is the name of the function whose overloads will handle the invocation.
     */
    private final String name;

    /**
     * These are the expressions that produce the arguments of the invocation.
     */
    private final List<IExpression> arguments;

    /**
     * These are the types of the static-types of the arguments.
     */
    private final List<IExpressionType> argument_types = Lists.newArrayList();

    /**
     * These are the overloads of the function that may handle the invocation.
     */
    private final List<IMethod> applicable = Lists.newArrayList();

    /**
     * These are the names of the temporary local variables used to store the arguments.
     */
    private final List<String> temporaries = Lists.newArrayList();

    /**
     * This is used to ensure that this class is used correctly.
     */
    private int state = 1;

    /**
     * Sole Constructor.
     *
     * @param node is the dispatch-expression itself.
     * @param module is essentially the enclosing module.
     * @param allocator is used to allocate variables.
     * @param vars is used to generate bytecode that manipulates variables.
     * @param name is the name of the function being dispatched.
     * @param arguments are the arguments being passed to the function.
     */
    public DispatchCompiler(final DispatchExpression node,
                            final ModuleCompiler module,
                            final VariableAllocator allocator,
                            final VariableManipulator vars,
                            final String name,
                            final List<IExpression> arguments)
    {
        Preconditions.checkNotNull(node);
        Preconditions.checkNotNull(module);
        Preconditions.checkNotNull(allocator);
        Preconditions.checkNotNull(vars);
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(arguments);

        this.node = node;
        this.allocator = allocator;
        this.arguments = arguments;
        this.module = module;
        this.name = name;
        this.vars = vars;

        /**
         * Retrieve the types of the arguments.
         */
        for (IExpression arg : arguments)
        {
            argument_types.add(module.program.symbols.expressions.get(arg));
        }
    }

    /**
     * This method searches for the applicable function overloads.
     */
    public void resolve()
    {
        /**
         * This is the 1st step in the compilation of a dispatch-expression.
         */
        assert state++ == 1;

        /**
         * Select the overloads that are applicable under the terms of a regular invocation.
         */
        final List<IMethod> selected = module.program.typesystem.utils.select(module.type,
                                                                              module.type.getAllVisibleMethods(),
                                                                              true,
                                                                              name);

        /**
         * This list will hold the unsorted applicable overloads.
         */
        final List<IMethod> unsorted = Lists.newArrayList();

        /**
         * Transfer the truly applicable overloads to the unsorted list.
         */
        for (IMethod method : selected)
        {
            if (isApplicable(method))
            {
                unsorted.add(method);
            }
        }

        /**
         * Sort the overloads and store them for later.
         */
        applicable.addAll(module.program.typesystem.utils.sort(unsorted));
    }

    /**
     * This method determines whether a function is potentially applicable to the arguments.
     *
     * @param function is the function overload that may be able to accept the arguments.
     * @return true, iff the function is potentially applicable to the arguments.
     */
    private boolean isApplicable(final IMethod function)
    {
        /**
         * Dispatch-expressions can only invoke functions that return reference-types or the void-type.
         */
        if (function.getReturnType().isReferenceType())
        {
            // OK
        }
        else if (function.getReturnType().isVoidType())
        {
            // OK
        }
        else
        {
            return false;
        }

        /**
         * Dispatch-expressions can only invoke functions whose parameters are reference-types.
         */
        for (IFormalParameter parameter : function.getParameters())
        {
            if (parameter.getType().isReferenceType() == false)
            {
                return false;
            }
        }

        /**
         * The function is potentially applicable to the arguments.
         */
        return true;
    }

    /**
     * This method performs the static-checking of the dispatch-expression.
     */
    public void check()
    {
        /**
         * This is the 2nd step in the compilation of a dispatch-expression.
         */
        assert state++ == 2;

        /**
         * There must be applicable function overloads available.
         */
        if (applicable.isEmpty())
        {
            module.program.checker.reportNoSuchMethod(node, true, module.type, name, argument_types);
        }

        /**
         * The arguments must produce a value.
         */
        for (IExpression argument : arguments)
        {
            module.program.checker.requireNonVoid(argument);
        }
    }

    /**
     * This function performs the actual bytecode generation.
     *
     * @param expr is used to compile the argument expressions.
     */
    public void compile(final ExpressionCodeGenerator expr)
    {
        /**
         * This is the 3rd step in the compilation of a dispatch-expression.
         */
        assert state++ == 3;

        /**
         * This label marks the location that execution jumps to after a successful dispatch.
         */
        final LabelNode done = new LabelNode();

        /**
         * Evaluate the arguments and store them in temporaries.
         */
        loadTemps(expr);

        /**
         * Dispatch Table
         */
        for (IMethod function : applicable)
        {
            generateDispatchTableEntry(expr.code, function, done);
        }

        /**
         * Default Termination
         */
        generateDefaultDispatch(expr.code);
        expr.code.add(done);
    }

    /**
     * This method generates the bytecode that evaluates the argument expressions
     * and then places the resulting arguments into the temporary local variables.
     *
     * @param expr will be used to compile the argument expressions.
     */
    private void loadTemps(final ExpressionCodeGenerator expr)
    {
        for (IExpression argument : arguments)
        {
            /**
             * Get the static-type of the argument.
             */
            final IExpressionType type = module.program.symbols.expressions.get(argument);

            /**
             * Declare a new temporary local variable.
             */
            final String local = "autumn$dispatch$" + F.unique();
            allocator.declareTemp(local, module.program.typesystem.utils.OBJECT);
            temporaries.add(local);

            /**
             * Compile the argument expression.
             */
            argument.accept(expr);

            /**
             * Generate bytecode that automatically boxes the argument, if necessary.
             */
            module.program.typesystem.utils.autoboxToObject(expr.code, type);

            /**
             * Generate bytecode that places the argument into the temporary.
             */
            vars.store(local);
        }
    }

    /**
     * This method generates the bytecode of a single entry in the dispatch-table entry.
     *
     * <p>
     * A bytecode generated by a dispatch-table entry does several things.
     * The entry decides whether the related overload is applicable to the arguments.
     * If so, the entry invokes the related overload passing in the arguments.
     * Then, the entry forces execution to exit the dispatch-expression.
     * If not, the entry simply allows execution to fall through.
     * </p>
     *
     * @param code is the bytecode that is being generated.
     * @param target represents the function overload associated with the entry.
     * @param done is a bytecode label that marks the end of the dispatch-expression.
     */
    private void generateDispatchTableEntry(final InsnList code,
                                            final IMethod target,
                                            final LabelNode done)
    {
        assert arguments.size() == target.getParameters().size();

        /**
         * This label marks the end of the this dispatch-table entry only.
         */
        final LabelNode skip = new LabelNode();

        /**
         * Generate bytecode that determines whether the arguments match the parameters.
         *
         * If any of the arguments fail to match, skip this entry in the dispatch-table.
         */
        for (int i = 0; i < arguments.size(); i++)
        {
            generateArgumentTest(code, target, skip, i);
        }

        /**
         * Since all the arguments matched, invoke the target method.
         */
        generateInvocation(code, target);

        /**
         * Exit the dispatch expression.
         */
        code.add(new JumpInsnNode(Opcodes.GOTO, done));
        code.add(skip);
    }

    /**
     * This method generates bytecode that determines whether an argument matches a parameter.
     *
     * @param code is the bytecode being generated.
     * @param target is the function overload that contains the parameter.
     * @param skip is a bytecode label that marks the endpoint of the enclosing dispatch-table entry.
     * @param index identifies the argument and parameter.
     */
    private void generateArgumentTest(final InsnList code,
                                      final IMethod target,
                                      final LabelNode skip,
                                      final int index)
    {
        assert arguments.size() == target.getParameters().size();

        /**
         * Get the static-type of the parameter.
         */
        final IReferenceType parameter = (IReferenceType) target.getParameters().get(index).getType();

        /**
         * Get the name of the temporary local variable that stores the argument.
         */
        final String argument = temporaries.get(index);

        /**
         * If the argument matches the parameter, execution will redirect to the location of this label.
         */
        final LabelNode ok = new LabelNode();

        /**
         * Generated Bytecode:
         *
         * LOAD argument - Load the argument onto the operand-stack.
         * IFNULL ok - If the argument is null, then the argument matches.
         * LOAD argument - Load the argument onto the operand-stack.
         * INSTANCEOF argument : parameter - If (argument instanceof parameter-type), then the argument matches.
         * IF_FALSE skip - Otherwise, the argument does not match.
         * LABEL ok -
         */
        vars.load(argument);
        code.add(new JumpInsnNode(Opcodes.IFNULL, ok));
        vars.load(argument);
        code.add(new TypeInsnNode(Opcodes.INSTANCEOF, Utils.internalName(parameter)));
        code.add(new JumpInsnNode(Utils.IF_FALSE, skip));
        code.add(ok);
    }

    /**
     * This method generates the bytecode of a function invocation.
     *
     * @param code is the bytecode that is being generated.
     * @param target is the function overload
     */
    private void generateInvocation(final InsnList code,
                                    final IMethod function)
    {
        /**
         * Load the arguments onto the operand-stack.
         */
        for (int i = 0; i < temporaries.size(); i++)
        {
            /**
             * Get the name of the temporary local variable that stores the argument.
             */
            final String argument = temporaries.get(i);

            /**
             * Get the type of the parameter related to the argument.
             */
            final IReferenceType type = (IReferenceType) function.getParameters().get(i).getType();

            /**
             * Generate the bytecode that loads the argument onto the operand-stack.
             */
            vars.load(argument);

            /**
             * Generate a checked-cast that casts the argument to the type of the parameter.
             */
            code.add(new TypeInsnNode(Opcodes.CHECKCAST, Utils.internalName(type)));
        }

        /**
         * Generate the bytecode that actually performs the invocation of the function.
         */
        code.add(new MethodInsnNode(Opcodes.INVOKESTATIC,
                                    Utils.internalName(function.getOwner()),
                                    function.getName(),
                                    function.getDescriptor()));

        /**
         * If the function returns void, make it appear to return null instead.
         */
        if (function.getReturnType().isVoidType())
        {
            code.add(new InsnNode(Opcodes.ACONST_NULL));
        }
    }

    /**
     * This method generates the bytecode that executes when dispatch fails.
     *
     * <p>
     * The generated bytecode creates an exception object and then throws it.
     * </p>
     *
     * @param code is the bytecode being generated.
     */
    private void generateDefaultDispatch(final InsnList code)
    {
        code.add(new TypeInsnNode(Opcodes.NEW, "autumn/lang/exceptions/DispatchException"));
        code.add(new InsnNode(Opcodes.DUP));
        code.add(new MethodInsnNode(Opcodes.INVOKESPECIAL,
                                    "autumn/lang/exceptions/DispatchException",
                                    "<init>",
                                    "()V"));
        code.add(new InsnNode(Opcodes.ATHROW));
    }
}

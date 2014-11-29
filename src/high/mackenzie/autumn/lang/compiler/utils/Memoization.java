package high.mackenzie.autumn.lang.compiler.utils;

import autumn.lang.compiler.ast.nodes.FormalParameter;
import high.mackenzie.autumn.lang.compiler.compilers.CollectionCompiler;
import high.mackenzie.autumn.lang.compiler.compilers.FunctionCompiler;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IExpressionType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;

/**
 * This method generates the bytecode necessary to implement memoization of functions.
 *
 * @author Mackenzie High
 */
public final class Memoization
{
    private final FunctionCompiler function;

    public Memoization(final FunctionCompiler function)
    {
        this.function = function;
    }

    /**
     * This method generates the bytecode that short-circuits a function invocation,
     * if the result was already computed in a previous invocation.
     *
     * @param code is the bytecode being generated.
     */
    public void shortcircuit(final InsnList code)
    {
        final LabelNode NO_MEMOIZED_VALUE = new LabelNode();

        /**
         * Get the memoizer object that is associated with the function.
         */
        loadMemoizer(code);

        /**
         * Create the list of arguments.
         */
        loadArgumentList(code);

        /**
         * Duplicate the references to the memoizer and the list of arguments.
         */
        code.add(new InsnNode(Opcodes.DUP2));

        /**
         * Check whether a memoized value exists.
         */
        code.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL,
                                    "autumn/lang/Memoizer",
                                    "check",
                                    "(Ljava/util/List;)Z"));

        /**
         * If no value was previously memoized for the given argument-list,
         * then nothing else can be done regarding short-circuiting.
         */
        code.add(new JumpInsnNode(Utils.IF_FALSE, NO_MEMOIZED_VALUE));

        /**
         * Get the memoized value, since it exists.
         */
        code.add(getter());

        /**
         * Convert the value from type Object to the return-type of the function.
         */
//        function.program.typesystem.utils.forceAssign(code,
//                                                      function.type.getReturnType(),
//                                                      function.program.typesystem.utils.OBJECT);
        /**
         * Return the memoized value from the function.
         */
        code.add(Utils.selectReturnInsn(function.type.getReturnType()));

        /**
         * This is where execution jumps to when nothing else can be done.
         * This point is never reached, if a memoized value was found.
         */
        code.add(NO_MEMOIZED_VALUE);
    }

    /**
     * This method generates the bytecode needed to memoize a value being returned by a function.
     *
     * <p>
     * This method expects that the value being memoized is already on the operand-stack.
     * </p>
     *
     * <p>
     * The value will still be on the operand-stack after the code generate herein executes.
     * </p>
     *
     * @param code is the bytecode being generated.
     * @param function is the type of the enclosing function.
     * @param type is the type of the value to memoize.
     */
    public void intern(final InsnList code,
                       final IExpressionType type)
    {
        assert function.isMemoized();

        /**
         * Duplicate the value, because we want to leave a copy of it on the operand-stack.
         */
        if (type.getDescriptor().equals("J") || type.getDescriptor().equals("D"))
        {
            code.add(new InsnNode(Opcodes.DUP2));
        }
        else
        {
            code.add(new InsnNode(Opcodes.DUP));
        }

        /**
         * Convert the value to an object.
         */
        function.module.program.typesystem.utils.autoboxToObject(code, type);

        /**
         * Get the memoizer object that is associated with the function.
         */
        loadMemoizer(code);

        /**
         * Swap the value with the memoizer, so that the memoizer is below the value.
         */
        code.add(new InsnNode(Opcodes.SWAP));

        /**
         * Create the list of arguments.
         */
        loadArgumentList(code);

        /**
         * Memoize the value.
         */
        code.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL,
                                    "autumn/lang/Memoizer",
                                    "intern",
                                    "(Ljava/lang/Object;Ljava/util/List;)V"));
    }

    /**
     * This method generates bytecode that creates a list containing the function's arguments.
     *
     * <p>
     * The list will be placed onto the operand-stack.
     * </p>
     *
     * @param code is the bytecode being generated.
     */
    private void loadArgumentList(final InsnList code)
    {
        final CollectionCompiler<FormalParameter> cmp = new CollectionCompiler<FormalParameter>()
        {
            @Override
            public void compile(final FormalParameter element)
            {
                /**
                 * Get the name of the parameter.
                 */
                final String name = element.getVariable().getName();

                /**
                 * Get the type of the parameter.
                 */
                final IVariableType type = function.allocator.typeOf(name);

                /**
                 * Load the argument onto the operand-stack.
                 */
                function.vars.load(name);

                /**
                 * Autobox the argument, if necessary.
                 */
                function.program.typesystem.utils.autoboxToObject(code, type);
            }

            @Override
            public InsnList code()
            {
                return code;
            }
        };

        /**
         * Create a list containing the values of the arguments passed to the function.
         */
        cmp.compile(function.node.getParameters().getParameters());
    }

    /**
     * This method generates bytecode that retrieves the memoizer object associated with the function.
     *
     * <p>
     * The memoizer object will be placed onto the operand-stack.
     * </p>
     *
     * @param code is the bytecode being generated.
     */
    private void loadMemoizer(final InsnList code)
    {
        /**
         * Get the singleton instance of the module by invoking the Module.instance() static method.
         */
        code.add(new MethodInsnNode(Opcodes.INVOKESTATIC,
                                    Utils.internalName(function.type.getOwner()),
                                    "instance",
                                    "()" + function.type.getOwner().getDescriptor()));

        /**
         * Get the ModuleInfo object by invoke the module.moduleInfo() instance method.
         */
        code.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE,
                                    "autumn/lang/Module",
                                    "moduleInfo",
                                    "()Lautumn/lang/ModuleInfo;"));
        /**
         * Get the memoizer associated with the function.
         */
        code.add(new LdcInsnNode(function.type.getName()));
        code.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE,
                                    "autumn/lang/ModuleInfo",
                                    "memoizerOf",
                                    "(Ljava/lang/String;)Lautumn/lang/Memoizer;"));
    }

    private MethodInsnNode getter()
    {
        if (function.type.getReturnType().getDescriptor().equals("Z"))
        {
            return new MethodInsnNode(Opcodes.INVOKEVIRTUAL,
                                      "autumn/lang/Memoizer",
                                      "valueAsBoolean",
                                      "(Ljava/util/List;)Z");
        }
        else if (function.type.getReturnType().getDescriptor().equals("C"))
        {
            return new MethodInsnNode(Opcodes.INVOKEVIRTUAL,
                                      "autumn/lang/Memoizer",
                                      "valueAsChar",
                                      "(Ljava/util/List;)C");
        }
        else if (function.type.getReturnType().getDescriptor().equals("B"))
        {
            return new MethodInsnNode(Opcodes.INVOKEVIRTUAL,
                                      "autumn/lang/Memoizer",
                                      "valueAsByte",
                                      "(Ljava/util/List;)B");
        }
        else if (function.type.getReturnType().getDescriptor().equals("S"))
        {
            return new MethodInsnNode(Opcodes.INVOKEVIRTUAL,
                                      "autumn/lang/Memoizer",
                                      "valueAsShort",
                                      "(Ljava/util/List;)S");
        }
        else if (function.type.getReturnType().getDescriptor().equals("I"))
        {
            return new MethodInsnNode(Opcodes.INVOKEVIRTUAL,
                                      "autumn/lang/Memoizer",
                                      "valueAsInt",
                                      "(Ljava/util/List;)I");
        }
        else if (function.type.getReturnType().getDescriptor().equals("J"))
        {
            return new MethodInsnNode(Opcodes.INVOKEVIRTUAL,
                                      "autumn/lang/Memoizer",
                                      "valueAsLong",
                                      "(Ljava/util/List;)J");
        }
        else if (function.type.getReturnType().getDescriptor().equals("F"))
        {
            return new MethodInsnNode(Opcodes.INVOKEVIRTUAL,
                                      "autumn/lang/Memoizer",
                                      "valueAsFloat",
                                      "(Ljava/util/List;)F");
        }
        else if (function.type.getReturnType().getDescriptor().equals("D"))
        {
            return new MethodInsnNode(Opcodes.INVOKEVIRTUAL,
                                      "autumn/lang/Memoizer",
                                      "valueAsDouble",
                                      "(Ljava/util/List;)D");
        }
        else
        {
            return new MethodInsnNode(Opcodes.INVOKEVIRTUAL,
                                      "autumn/lang/Memoizer",
                                      "valueAsObject",
                                      "(Ljava/util/List;)Ljava/lang/Object;");
        }
    }
}

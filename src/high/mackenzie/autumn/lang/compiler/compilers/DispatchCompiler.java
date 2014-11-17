package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ast.commons.IExpression;
import autumn.util.F;
import com.google.common.collect.Lists;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IExpressionType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IReferenceType;
import high.mackenzie.autumn.lang.compiler.utils.Utils;
import java.util.List;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.TypeInsnNode;

/**
 * An instance of this class performs the compilation of a dispatch-expression.
 *
 * @author Mackenzie High
 */
final class DispatchCompiler
{
    private final ModuleCompiler module;

    private final VariableAllocator allocator;

    private final VariableManipulator vars;

    private final String name;

    private final List<IExpression> arguments;

    private final List<IExpressionType> argument_types = Lists.newArrayList();

    private final List<IMethod> applicable = Lists.newArrayList();

    private final List<String> temporaries = Lists.newArrayList();

    private int state = 0;

    /**
     * Sole Constructor.
     *
     * @param module is essentially the enclosing module.
     * @param allocator is used to allocate variables.
     * @param vars is used to generate bytecode that manipulates variables.
     * @param name is the name of the function being dispatched.
     * @param arguments are the arguments being passed to the function.
     */
    public DispatchCompiler(final ModuleCompiler module,
                            final VariableAllocator allocator,
                            final VariableManipulator vars,
                            final String name,
                            final List<IExpression> arguments)
    {
        this.allocator = allocator;
        this.arguments = arguments;
        this.module = module;
        this.name = name;
        this.vars = vars;

        for (IExpression arg : arguments)
        {
            argument_types.add(module.program.symbols.expressions.get(arg));
        }
    }

    public void resolve()
    {
        assert state == 0;
        ++state;

        final List<IMethod> selected = module.program.typesystem.utils.select(module.type,
                                                                              module.type.getAllVisibleMethods(),
                                                                              true,
                                                                              name);

        final List<IMethod> methods = Lists.newArrayList();

        for (IMethod method : selected)
        {
            if (isApplicable(method))
            {
                methods.add(method);
            }
        }

        applicable.addAll(module.program.typesystem.utils.sort(methods));
    }

    private boolean isApplicable(final IMethod function)
    {
        return true;
    }

    public void check()
    {
        assert state == 1;
        ++state;
    }

    public void compile(final ExpressionCodeGenerator expr)
    {
        assert state == 2;
        ++state;

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

    private void loadTemps(final ExpressionCodeGenerator expr)
    {
        for (IExpression arg : arguments)
        {
            final String local = "autumn$dispatch$" + F.unique();

            final IExpressionType type = module.program.symbols.expressions.get(arg);

            allocator.declareTemp(local, type);

            temporaries.add(local);

            arg.accept(expr);

            vars.store(local);
        }
    }

    private void generateDispatchTableEntry(final InsnList code,
                                            final IMethod target,
                                            final LabelNode done)
    {
        assert arguments.size() == target.getParameters().size();

        final LabelNode skip = new LabelNode();

        /**
         * If any of the arguments fail to match, skip this entry in the dispatch table.
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

    private void generateArgumentTest(final InsnList code,
                                      final IMethod target,
                                      final LabelNode skip,
                                      final int index)
    {
        final IReferenceType parameter_type = (IReferenceType) target.getParameters().get(index).getType();

        final String local = temporaries.get(index);

        final LabelNode ok = new LabelNode();

        vars.load(local);
        code.add(new JumpInsnNode(Opcodes.IFNULL, ok));
        vars.load(local);
        code.add(new TypeInsnNode(Opcodes.INSTANCEOF, Utils.internalName(parameter_type)));
        code.add(new JumpInsnNode(Utils.IF_FALSE, skip));
        code.add(ok);
    }

    private void generateInvocation(final InsnList code,
                                    final IMethod target)
    {
        /**
         * Load the arguments onto the operand-stack.
         */
        for (int i = 0; i < temporaries.size(); i++)
        {
            final String argument = temporaries.get(i);

            final IReferenceType type = (IReferenceType) target.getParameters().get(i).getType();

            vars.load(argument);

            code.add(new TypeInsnNode(Opcodes.CHECKCAST, Utils.internalName(type)));
        }

        /**
         * Invoke the target function.
         */
        code.add(new MethodInsnNode(Opcodes.INVOKESTATIC,
                                    Utils.internalName(target.getOwner()),
                                    target.getName(),
                                    target.getDescriptor()));
    }

    private void generateDefaultDispatch(final InsnList code)
    {
        code.add(new TypeInsnNode(Opcodes.NEW, "java/lang/RuntimeException")); // TODO: change
        code.add(new InsnNode(Opcodes.DUP));
        code.add(new LdcInsnNode("Dispatch Failed: " + name));                 // TODO: improve
        code.add(new MethodInsnNode(Opcodes.INVOKESPECIAL,
                                    "java/lang/RuntimeException",
                                    "<init>",
                                    "(Ljava/lang/String;)V"));
        code.add(new InsnNode(Opcodes.ATHROW));
    }
}

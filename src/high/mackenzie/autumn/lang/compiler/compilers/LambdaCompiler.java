package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ClassFile;
import autumn.lang.compiler.ast.nodes.LambdaStatement;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import high.mackenzie.autumn.lang.compiler.typesystem.CustomDeclaredType;
import high.mackenzie.autumn.lang.compiler.utils.TypeSystemUtils;
import high.mackenzie.autumn.lang.compiler.utils.Utils;
import java.util.List;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

/**
 * An instance of this this class controls the compilation of a lambda function.
 *
 * @author Mackenzie High
 */
public final class LambdaCompiler
        implements ICompiler
{
    /**
     * Essentially, this is the enclosing program that is being compiled.
     */
    private final ProgramCompiler program;

    /**
     * Essentially, this is the enclosing function that is being compiled.
     */
    private final FunctionCompiler function;

    /**
     * This is the Abstract-Syntax-Tree representation of the lambda function.
     */
    private final LambdaStatement node;

    /**
     * This is the type-system representation of the lambda function.
     */
    private CustomDeclaredType type;

    /**
     * Sole Constructor.
     *
     * @param function is
     */
    public LambdaCompiler(final FunctionCompiler function,
                          final LambdaStatement node)
    {
        this.function = function;
        this.node = node;
        this.program = function.module.program;

        this.performTypeDeclaration();
        this.performTypeInitialization();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeDeclaration()
    {
        /**
         * Determine the descriptor of the tuple.
         */
        final String namespace = function.module.type.getNamespace().replace('.', '/');
        final String name = "autumn$lambda$1"; // TODO
        final String descriptor = "L" + namespace + '/' + name + ';';

        /**
         * Declare the type.
         */
        this.type = program.typesystem.typefactory().newClassType(descriptor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeInitialization()
    {
        type.setSuperclass(program.typesystem.utils.ABSTRACT_LAMBDA);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeStructureChecking()
    {
        // Pass
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeUsageChecking()
    {
        // Pass
    }

    /**
     * {@inheritDoc}
     */
    public ClassFile build()
    {
        final List<MethodNode> methods = Lists.newLinkedList();

        methods.add(this.generateConstructor());
//        methods.add(this.generateMethodApply());
        methods.add(this.generateMethodParameterTypes());
        methods.add(this.generateMethodReturnType());

        final String lambda_internal_name = Utils.internalName(type);

        final String lambda_source_name = Utils.sourceName(type);

        final ClassNode clazz = new ClassNode();
        {
            clazz.version = Opcodes.V1_6;
            clazz.visibleAnnotations = ImmutableList.of();
            clazz.access = Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL;
            clazz.name = lambda_internal_name;
            clazz.superName = Utils.internalName(program.typesystem.utils.ABSTRACT_LAMBDA);
            clazz.interfaces = Lists.newLinkedList();
            clazz.fields = Lists.newLinkedList();
            clazz.methods = ImmutableList.copyOf(methods);
            clazz.sourceFile = String.valueOf(node.getLocation().getFile());

            assert clazz.superName.equals("autumn/lang/internals/AbstractLambda");
            assert clazz.access == Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL;
        }

        final ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        clazz.accept(writer);

        final byte[] bytecode = writer.toByteArray();

        final ClassFile file = new ClassFile(lambda_source_name, bytecode);

        return file;
    }

    private MethodNode generateConstructor()
    {
        final MethodNode ctor = new MethodNode();
        ctor.access = Opcodes.ACC_PUBLIC;
        ctor.name = "<init>";
        ctor.desc = "()V";
        ctor.exceptions = ImmutableList.of();


        ctor.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0)); // Load 'this'
        ctor.instructions.add(new MethodInsnNode(Opcodes.INVOKESPECIAL,
                                                 Utils.internalName(program.typesystem.utils.ABSTRACT_LAMBDA),
                                                 "<init>",
                                                 "()V"));

        ctor.instructions.add(new InsnNode(Opcodes.RETURN));

        return ctor;
    }

    private MethodNode generateMethodParameterTypes()
    {
        final MethodNode method = Utils.bytecodeOf(function.module,
                                                   TypeSystemUtils.find(program.typesystem.utils.ABSTRACT_LAMBDA.getAllVisibleMethods(),
                                                                        "parameterTypes"));

        // Remove the abstract modifier.
        method.access = method.access & (~Opcodes.ACC_ABSTRACT);

        method.instructions.add(new InsnNode(Opcodes.ACONST_NULL));
        method.instructions.add(new InsnNode(Opcodes.ARETURN));

        return method;
    }

    private MethodNode generateMethodReturnType()
    {
        final MethodNode method = Utils.bytecodeOf(function.module,
                                                   TypeSystemUtils.find(program.typesystem.utils.ABSTRACT_LAMBDA.getAllVisibleMethods(),
                                                                        "returnType"));

        // Remove the abstract modifier.
        method.access = method.access & (~Opcodes.ACC_ABSTRACT);

        method.instructions.add(new InsnNode(Opcodes.ACONST_NULL));
        method.instructions.add(new InsnNode(Opcodes.ARETURN));

        return method;
    }

    private MethodNode generateMethodApply()
    {
        final MethodNode method = Utils.bytecodeOf(function.module,
                                                   TypeSystemUtils.find(program.typesystem.utils.ABSTRACT_LAMBDA.getAllVisibleMethods(),
                                                                        "apply"));

        // Remove the abstract modifier.
        method.access = method.access & (~Opcodes.ACC_ABSTRACT);

        method.instructions.add(new InsnNode(Opcodes.ACONST_NULL));
        method.instructions.add(new InsnNode(Opcodes.ARETURN));

        return method;
    }

    /**
     * This method generates the bytecode needed to instantiate the lambda
     * and load it onto the operand-stack.
     *
     * @param code is the bytecode being generated.
     */
    public void load(final InsnList code)
    {
        code.add(new TypeInsnNode(Opcodes.NEW, Utils.internalName(type)));

        code.add(new InsnNode(Opcodes.DUP));

        code.add(new MethodInsnNode(Opcodes.INVOKESPECIAL,
                                    Utils.internalName(type),
                                    "<init>",
                                    "()V"));
    }
}

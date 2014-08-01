package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ClassFile;
import autumn.lang.compiler.ast.nodes.ExceptionDefinition;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import high.mackenzie.autumn.lang.compiler.typesystem.CustomConstructor;
import high.mackenzie.autumn.lang.compiler.typesystem.CustomDeclaredType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IAnnotation;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IClassType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IConstructor;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IField;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IFormalParameter;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IInterfaceType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IType;
import high.mackenzie.autumn.lang.compiler.utils.Utils;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Set;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

/**
 * An instance of this class controls the compilation of an exception-definition.
 *
 * @author Mackenzie High
 */
public final class ExceptionCompiler
        implements ICompiler
{
    public final ProgramCompiler program;

    public final ModuleCompiler module;

    public final ExceptionDefinition node;

    public CustomDeclaredType type;

    /**
     * This field indicates whether any constructors were inferred from the superclass.
     * If inferred is negate, then inference has not been attempted yet.
     * If inferred is zero, then no constructors were inferred (report compile-time error).
     * If inferred is non-zero and positive, then constructors were inferred.
     */
    private int inferred = -100;

    public ExceptionCompiler(final ModuleCompiler module,
                             final ExceptionDefinition node)
    {
        this.program = module.program;
        this.module = module;
        this.node = node;
    }

    /**
     * This method generates the compiled class-file.
     *
     * @return the compiled class-file.
     */
    public ClassFile build()
    {
        final String exception_internal_name = Utils.internalName(type);

        final String exception_source_name = Utils.sourceName(type);

        /**
         * Create the bytecode representation of the exception itself.
         */
        final ClassNode clazz = new ClassNode();
        {
            clazz.version = Opcodes.V1_6;
            clazz.visibleAnnotations = Lists.newLinkedList();
            clazz.access = type.getModifiers();
            clazz.name = exception_internal_name;
            clazz.superName = Utils.internalName(type.getSuperclass());
            clazz.interfaces = ImmutableList.of();
            clazz.fields = ImmutableList.of();
            clazz.methods = Lists.newLinkedList();
            clazz.sourceFile = String.valueOf(node.getLocation().getFile());

            if (inferred == 0)
            {
                // TODO: Error
                assert false;
            }
            else
            {
                addInferredCtors(clazz);
            }
        }

        /**
         * Assemble the bytecode into an array of bytes.
         */
        final ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        clazz.accept(writer);
        final byte[] bytecode = writer.toByteArray();

        /**
         * Create the class-file object that will store the emitted bytecode.
         */
        final ClassFile file = new ClassFile(exception_source_name, bytecode);

        return file;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeDeclaration()
    {
        /**
         * Determine the descriptor of the exception.
         */
        final String namespace = module.type.getNamespace().replace('.', '/');
        final String name = node.getName().getName();
        final String descriptor = "L" + namespace + '/' + name + ';';

        /**
         * Ensure that this exception is not a duplicate type-declaration.
         */
        if (program.typesystem.typefactory().findType(descriptor) != null)
        {
            // TODO: error
            System.out.println("Duplicate Type: " + descriptor);
        }

        /**
         * Declare the exception.
         */
        this.type = program.typesystem.typefactory().newClassType(descriptor);

        /**
         * Publicize this exception, so that it will be possible to infer constructors.
         */
        program.symbols.exceptions.put(type, this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeInitialization()
    {
        final IType supertype = module.resolveType(node.getSuperclass());

        if (supertype instanceof IClassType == false)
        {
            // TODO: error
            assert false;
        }

        this.type.setAnnotations(ImmutableList.<IAnnotation>of());
        this.type.setModifiers(Opcodes.ACC_PUBLIC);
        this.type.setSuperclass((IClassType) supertype);
        this.type.setSuperinterfaces(ImmutableList.<IInterfaceType>of());
        this.type.setFields(ImmutableList.<IField>of());
        this.type.setConstructors(ImmutableList.<IConstructor>of());
        this.type.setMethods(ImmutableList.<IMethod>of());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeStructureChecking()
    {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeUsageChecking()
    {
    }

    private void addInferredCtors(final ClassNode clazz)
    {
        for (IConstructor ctor : type.getConstructors())
        {
            final MethodNode m = new MethodNode();
            m.access = ctor.getModifiers();
            m.name = "<init>";
            m.desc = ctor.getDescriptor();
            m.exceptions = Lists.newLinkedList(); // TODO: infer exceptions
            clazz.methods.add(m);

            // Load 'this'
            m.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0));

            // Load each actual-parameter onto the operand-stack.
            int offset = 1; // skip 'this'
            for (IFormalParameter param : ctor.getFormalParameters())
            {
                m.instructions.add(Utils.selectLoadVarInsn(param.getType(), offset));
                offset = offset + Utils.sizeof(param.getType());
            }

            // Invoke the super constructor.
            final String owner = Utils.internalName(type.getSuperclass());
            final String init = "<init>";
            final String desc = ctor.getDescriptor();
            m.instructions.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, owner, init, desc));

            // Return.
            m.instructions.add(new InsnNode(Opcodes.RETURN));
        }
    }

    /**
     * This method determines whether this class or any of its parents inherit from themselves.
     *
     * @return true, iff circular inheritance is present.
     */
    public boolean detectCircularInheritance()
    {
        final Set<IType> set = Sets.newHashSet();

        set.add(type);

        IClassType p = type.getSuperclass();

        while ("Ljava/lang/Object;".equals(p.getDescriptor()) == false)
        {
            if (set.contains(p))
            {
                return true;
            }
            else
            {
                set.add(p);
                p = p.getSuperclass();
            }
        }

        return false;
    }

    /**
     * This method causes the exception's type to inherit constructors from its superclass.
     */
    public void inferConstructors()
    {
        inferred = 0;

        // If circular inheritance exists, then bad things will happen.
        if (detectCircularInheritance())
        {
            // Prevent bad things from happening.
            return;
        }

        // The superclass may have to infer its constructors first.
        if (program.symbols.exceptions.containsKey(type.getSuperclass()))
        {
            program.symbols.exceptions.get(type.getSuperclass()).inferConstructors();
        }

        final List<IConstructor> ctors = Lists.newLinkedList();

        for (IConstructor ctor : type.getSuperclass().getConstructors())
        {
            final boolean public_access = Modifier.isPublic(ctor.getModifiers());
            final boolean protected_access = Modifier.isProtected(ctor.getModifiers());

            // The constructor must either be public or protected.
            if (!public_access && !protected_access)
            {
                continue;
            }

            // Copy the constructor from the supertype.
            final CustomConstructor custom = new CustomConstructor(ctor.getOwner().getTypeFactory());
            custom.setOwner(type);
            custom.setAnnotations(ImmutableList.<IAnnotation>of());
            custom.setModifiers(ctor.getModifiers());
            custom.setName(ctor.getName());
            custom.setParameters(ctor.getFormalParameters());
            custom.setReturnType(ctor.getReturnType());
            custom.setThrowsClause(ctor.getThrowsClause());

            ctors.add(custom);
            ++inferred;
        }

        // Add all the constructors that were copied from the supertype to the type.
        type.setConstructors(ctors);
    }
}

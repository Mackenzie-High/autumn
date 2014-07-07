package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ClassFile;
import autumn.lang.compiler.ast.nodes.DesignDefinition;
import autumn.lang.compiler.ast.nodes.DesignMethod;
import autumn.lang.compiler.ast.nodes.DesignProperty;
import autumn.lang.compiler.ast.nodes.FormalParameter;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import high.mackenzie.autumn.lang.compiler.typesystem.CustomDeclaredType;
import high.mackenzie.autumn.lang.compiler.typesystem.CustomFormalParameter;
import high.mackenzie.autumn.lang.compiler.typesystem.CustomMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IAnnotation;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IClassType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IField;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IFormalParameter;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IInterfaceType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;
import high.mackenzie.autumn.lang.compiler.utils.Utils;
import java.util.Collection;
import java.util.List;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

/**
 * An instance of this class controls the compilation of a design-definition.
 *
 * @author Mackenzie High
 */
public class DesignCompiler
        implements ICompiler
{
    private final ProgramCompiler program;

    private final ModuleCompiler module;

    private final DesignDefinition node;

    public CustomDeclaredType type;

    private final ClassNode clazz = new ClassNode();

    /**
     * Sole Constructor.
     *
     * @param module is the module being compiled.
     * @param node is the AST representation of the design.
     */
    public DesignCompiler(final ModuleCompiler module,
                          final DesignDefinition node)
    {
        Preconditions.checkNotNull(module);
        Preconditions.checkNotNull(node);

        this.module = module;
        this.node = node;
        this.program = module.program;
    }

    /**
     * This method generates the compiled class-file.
     *
     * @return the compiled class-file.
     */
    public ClassFile build()
    {
        /**
         * Assemble the bytecode into an array of bytes.
         */
        final ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        clazz.accept(writer);
        final byte[] bytecode = writer.toByteArray();

        /**
         * Create the class-file object that will store the emitted bytecode.
         */
        final ClassFile file = new ClassFile(Utils.sourceName(type), bytecode);

        return file;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeDeclaration()
    {
        /**
         * Determine the descriptor of the design.
         */
        final String namespace = module.type.getNamespace().replace('.', '/');
        final String name = node.getName().getName();
        final String descriptor = "L" + namespace + '/' + name + ';';

        /**
         * Ensure that this design is not a duplicate type-declaration.
         */
        if (program.typesystem.getTypeFactory().findType(descriptor) != null)
        {
            // TODO: error
            System.out.println("Duplicate Type: " + descriptor);
        }

        /**
         * Declare the design.
         */
        this.type = program.typesystem.getTypeFactory().newInterfaceType(descriptor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeInitialization()
    {
        final List<IMethod> methods = Lists.newLinkedList();

        for (DesignProperty property : node.getProperties())
        {
            initProperty(methods, property);
        }

        for (DesignMethod method : node.getMethods())
        {
            initMethod(methods, method);
        }

        /**
         * Initialize the type that represents the design being compiled.
         */
        this.type.setModifiers(Opcodes.ACC_PUBLIC + Opcodes.ACC_INTERFACE);
        this.type.setSuperclass(program.typesystem.utils.OBJECT);
        this.type.setSuperinterfaces(ImmutableList.<IInterfaceType>of());
        this.type.setFields(ImmutableList.<IField>of());
        this.type.setMethods(ImmutableList.copyOf(methods));
    }

    /**
     * This method performs type-initialization of a property.
     *
     * @param methods is the mutable collection of methods in the design's type.
     * @param property is the property whose type-system representation will be created.
     */
    private void initProperty(final Collection<IMethod> methods,
                              final DesignProperty property)
    {
        final IVariableType ptype = module.resolveVariableType(property.getType());

        initGetter(methods, property, ptype);
        initSetter(methods, property, ptype);
    }

    /**
     * This method performs type-initialization of a property's getter.
     *
     * @param methods is the mutable collection of methods in the design's type.
     * @param property is the property whose getter's type-system representation will be created.
     */
    private void initGetter(final Collection<IMethod> methods,
                            final DesignProperty property,
                            final IVariableType ptype)
    {
        final CustomMethod cm = new CustomMethod(type.getTypeFactory(), false);
        cm.setOwner(type);
        cm.setAnnotations(Lists.<IAnnotation>newLinkedList());
        cm.setModifiers(Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT);
        cm.setName(property.getName().getName());
        cm.setParameters(Lists.<IFormalParameter>newLinkedList());
        cm.setReturnType(ptype);
        cm.setThrowsClause(Lists.<IClassType>newLinkedList());

        methods.add(cm);
    }

    /**
     * This method performs type-initialization of a property's setter.
     *
     * @param methods is the mutable collection of methods in the design's type.
     * @param property is the property whose setter's type-system representation will be created.
     */
    private void initSetter(final Collection<IMethod> methods,
                            final DesignProperty property,
                            final IVariableType ptype)
    {
        final CustomFormalParameter param = new CustomFormalParameter();
        param.setAnnotations(ImmutableList.<IAnnotation>of());
        param.setType(ptype);

        final CustomMethod cm = new CustomMethod(type.getTypeFactory(), false);
        cm.setOwner(type);
        cm.setAnnotations(Lists.<IAnnotation>newLinkedList());
        cm.setModifiers(Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT);
        cm.setName(property.getName().getName());
        cm.setParameters(ImmutableList.<IFormalParameter>of(param));
        cm.setReturnType(type);
        cm.setThrowsClause(Lists.<IClassType>newLinkedList());

        methods.add(cm);
    }

    /**
     * This method performs type-initialization of a method.
     *
     * @param methods is the mutable collection of methods in the design's type.
     * @param method is the method whose type-system representation will be created.
     */
    private void initMethod(final Collection<IMethod> methods,
                            final DesignMethod method)
    {
        final List<IFormalParameter> params = Lists.newLinkedList();

        for (FormalParameter formal : method.getParameters().getParameters())
        {
            final IVariableType ftype = module.resolveVariableType(formal.getType());

            final CustomFormalParameter param = new CustomFormalParameter();
            param.setAnnotations(ImmutableList.<IAnnotation>of());
            param.setType(ftype);

            params.add(param);
        }

        final CustomMethod cm = new CustomMethod(type.getTypeFactory(), false);
        cm.setOwner(type);
        cm.setAnnotations(Lists.<IAnnotation>newLinkedList());
        cm.setModifiers(Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT);
        cm.setName(method.getName().getName());
        cm.setParameters(ImmutableList.<IFormalParameter>copyOf(params));
        cm.setReturnType(module.resolveType(method.getReturnType()));
        cm.setThrowsClause(Lists.<IClassType>newLinkedList());

        methods.add(cm);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void performCodeGeneration()
    {
        final List<MethodNode> methods = Lists.newLinkedList();

        for (IMethod method : this.type.getMethods())
        {
            methods.add(generateMethod(method));
        }

        /**
         * Create the bytecode representation of the design itself.
         */
        clazz.version = Opcodes.V1_6;
        clazz.visibleAnnotations = Lists.newLinkedList();
        clazz.access = type.getModifiers();
        clazz.name = Utils.internalName(type);
        clazz.superName = Utils.internalName(type.getSuperclass());
        clazz.interfaces = Lists.newLinkedList();
        clazz.fields = ImmutableList.of();
        clazz.methods = methods;
        clazz.sourceFile = String.valueOf(node.getLocation().getFile());
    }

    /**
     * This method generates the bytecode representation of a setter, getter, or regular method.
     *
     * @param method is the type-system representation of the method.
     * @return the bytecode representation of the method.
     */
    private MethodNode generateMethod(final IMethod method)
    {
        final MethodNode m = new MethodNode();

        m.access = method.getModifiers();
        m.name = method.getName();
        m.desc = method.getDescriptor();
        m.exceptions = ImmutableList.of();

        return m;
    }
}

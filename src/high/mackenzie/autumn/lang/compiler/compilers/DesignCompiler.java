package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ClassFile;
import autumn.lang.compiler.ast.nodes.DesignDefinition;
import autumn.lang.compiler.ast.nodes.DesignMethod;
import autumn.lang.compiler.ast.nodes.DesignProperty;
import autumn.lang.compiler.ast.nodes.FormalParameter;
import autumn.lang.compiler.ast.nodes.TypeSpecifier;
import autumn.lang.internals.annotations.Getter;
import autumn.lang.internals.annotations.Setter;
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
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnNode;
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

    public KlassCompiler klass;

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
        final List<MethodNode> methods = Lists.newLinkedList();

        /**
         * Generate the field that stores the prototypical instance.
         */
        clazz.fields.add(generateProtoField());

        /**
         * Generate the static constructor, which creates the prototypical instance.
         */
        methods.add(generateStaticCtor());

        /**
         * Generate each interface method.
         */
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
        clazz.interfaces = program.typesystem.utils.internalNamesOf(type.getSuperinterfaces());
        clazz.methods = methods;
        clazz.sourceFile = String.valueOf(node.getLocation().getFile());

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
         * Ensure that the type was not already declared elsewhere.
         */
        program.checker.requireNonDuplicateType(node.getName(), descriptor);

        /**
         * Declare the design.
         */
        this.type = program.typesystem.typefactory().newInterfaceType(descriptor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeInitialization()
    {
        final List<IInterfaceType> superinterfaces = Lists.newLinkedList();

        superinterfaces.add(program.typesystem.utils.PROTOTYPE);

        for (TypeSpecifier face : node.getSuperinterfaces())
        {
            superinterfaces.add(module.imports.resolveInterfaceType(face));
        }

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
        this.type.setModifiers(Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT + Opcodes.ACC_INTERFACE);
        this.type.setSuperclass(program.typesystem.utils.OBJECT);
        this.type.setSuperinterfaces(superinterfaces);
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
        final IVariableType ptype = module.imports.resolveVariableType(property.getType());

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
        /**
         * Create the type-system representations of the getter's annotations.
         */
        final List<IAnnotation> annotations = Lists.newLinkedList();
        annotations.add(module.anno_utils.typeOf(Getter.class));
        annotations.addAll(module.anno_utils.typesOf(property.getAnnotations()));

        /**
         * Create the type-system representation of the getter itself.
         */
        final CustomMethod cm = new CustomMethod(type.getTypeFactory(), false);
        cm.setOwner(type);
        cm.setAnnotations(annotations);
        cm.setModifiers(Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT);
        cm.setName(property.getName().getName());
        cm.setParameters(Lists.<IFormalParameter>newLinkedList());
        cm.setReturnType(ptype);
        cm.setThrowsClause(Lists.<IClassType>newArrayList(program.typesystem.utils.THROWABLE));

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
        /**
         * Create the type-system representations of the setter's annotations.
         */
        final List<IAnnotation> annotations = Lists.newLinkedList();
        annotations.add(module.anno_utils.typeOf(Setter.class));
        annotations.addAll(module.anno_utils.typesOf(property.getAnnotations()));

        /**
         * Create the type-system representation of the setter's only formal-parameter.
         */
        final CustomFormalParameter param = new CustomFormalParameter();
        param.setAnnotations(ImmutableList.<IAnnotation>of());
        param.setType(ptype);

        /**
         * Create the type-system representation of the setter itself.
         */
        final CustomMethod cm = new CustomMethod(type.getTypeFactory(), false);
        cm.setOwner(type);
        cm.setAnnotations(annotations);
        cm.setModifiers(Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT);
        cm.setName(property.getName().getName());
        cm.setParameters(ImmutableList.<IFormalParameter>of(param));
        cm.setReturnType(type);
        cm.setThrowsClause(Lists.<IClassType>newArrayList(program.typesystem.utils.THROWABLE));

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
        /**
         * Create the type-system representations of the method's annotations.
         */
        final List<IAnnotation> annotations = Lists.newLinkedList();
        annotations.addAll(module.anno_utils.typesOf(method.getAnnotations()));

        /**
         * Create the type-system representation of the method's formal-parameters.
         */
        final List<IFormalParameter> params = Lists.newLinkedList();

        for (FormalParameter formal : method.getParameters().getParameters())
        {
            final IVariableType ftype = module.imports.resolveVariableType(formal.getType());

            final CustomFormalParameter param = new CustomFormalParameter();
            param.setAnnotations(ImmutableList.<IAnnotation>of());
            param.setType(ftype);

            params.add(param);
        }

        /**
         * Create the type-system representation of the method itself.
         */
        final CustomMethod cm = new CustomMethod(type.getTypeFactory(), false);
        cm.setOwner(type);
        cm.setAnnotations(annotations);
        cm.setModifiers(Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT);
        cm.setName(method.getName().getName());
        cm.setParameters(ImmutableList.<IFormalParameter>copyOf(params));
        cm.setReturnType(module.imports.resolveReturnType(method.getReturnType()));
        cm.setThrowsClause(Lists.<IClassType>newArrayList(program.typesystem.utils.THROWABLE));

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
     * This method generates the bytecode representation of a setter, getter, or regular method.
     *
     * @param method is the type-system representation of the method.
     * @return the bytecode representation of the method.
     */
    private MethodNode generateMethod(final IMethod method)
    {
        final MethodNode m = new MethodNode();

        m.visibleAnnotations = module.anno_utils.compileAnnotationList(method.getAnnotations());
        m.access = method.getModifiers();
        m.name = method.getName();
        m.desc = method.getDescriptor();
        m.exceptions = Lists.newArrayList(Utils.internalName(program.typesystem.utils.THROWABLE));

        return m;
    }

    /**
     * This method generates the public static final field that stores the prototypical instance.
     *
     * @return the generated field.
     */
    private FieldNode generateProtoField()
    {
        final int access = Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC + Opcodes.ACC_FINAL;

        final FieldNode field = new FieldNode(access,
                                              "INSTANCE",
                                              type.getDescriptor(),
                                              null,
                                              null);

        return field;
    }

    /**
     * This method generates the static-constructor of the interface.
     *
     * @return the bytecode representation of the static constructor.
     */
    private MethodNode generateStaticCtor()
    {
        klass = new KlassCompiler(module, type, node.getLocation());

        final MethodNode ctor = new MethodNode();

        ctor.access = Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC;
        ctor.name = "<clinit>";
        ctor.desc = "()V";
        ctor.exceptions = ImmutableList.of();

        klass.load(ctor.instructions);

        ctor.instructions.add(new FieldInsnNode(Opcodes.PUTSTATIC,
                                                Utils.internalName(type),
                                                "INSTANCE",
                                                type.getDescriptor()));

        ctor.instructions.add(new InsnNode(Opcodes.RETURN));

        return ctor;
    }
}

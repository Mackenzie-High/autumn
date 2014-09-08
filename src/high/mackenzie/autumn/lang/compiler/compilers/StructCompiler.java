package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ClassFile;
import autumn.lang.compiler.ast.nodes.FormalParameter;
import autumn.lang.compiler.ast.nodes.StructDefinition;
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
 * An instance of this class controls the compilation of a struct-definition.
 *
 * @author Mackenzie High
 */
public class StructCompiler
        implements ICompiler
{
    private final ProgramCompiler program;

    private final ModuleCompiler module;

    private final StructDefinition node;

    public CustomDeclaredType type;

    private final ClassNode clazz = new ClassNode();

    public KlassCompiler klass;

    /**
     * Sole Constructor.
     *
     * @param module is the module being compiled.
     * @param node is the AST representation of the design.
     */
    public StructCompiler(final ModuleCompiler module,
                          final StructDefinition node)
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
        final List<IInterfaceType> supers = Lists.newLinkedList();

        supers.add(program.typesystem.utils.STRUCT);

        for (TypeSpecifier face : node.getSupers())
        {
            supers.add(module.imports.resolveInterfaceType(face));
        }

        final List<IMethod> methods = Lists.newLinkedList();

        for (FormalParameter element : node.getElements().getParameters())
        {
            initElement(methods, element);
        }

        /**
         * Initialize the type that represents the struct being compiled.
         */
        this.type.setModifiers(Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT + Opcodes.ACC_INTERFACE);
        this.type.setSuperclass(program.typesystem.utils.OBJECT);
        this.type.setSuperinterfaces(supers);
        this.type.setFields(ImmutableList.<IField>of());
        this.type.setMethods(ImmutableList.copyOf(methods));
    }

    /**
     * This method performs type-initialization of a element.
     *
     * @param methods is the mutable collection of methods in the struct's type.
     * @param element is the element whose type-system representation will be created.
     */
    private void initElement(final Collection<IMethod> methods,
                             final FormalParameter element)
    {
        final IVariableType ptype = module.imports.resolveVariableType(element.getType());

        initGetter(methods, element, ptype);
        initSetter(methods, element, ptype);
    }

    /**
     * This method performs type-initialization of a element's getter.
     *
     * @param methods is the mutable collection of methods in the struct's type.
     * @param element is the element whose getter's type-system representation will be created.
     * @param ptype is the type of the value stored in the element.
     */
    private void initGetter(final Collection<IMethod> methods,
                            final FormalParameter element,
                            final IVariableType ptype)
    {
        /**
         * Create the type-system representations of the getter's annotations.
         */
        final List<IAnnotation> annotations = Lists.newLinkedList();
        annotations.add(module.anno_utils.typeOf(Getter.class));

        /**
         * Create the type-system representation of the getter itself.
         */
        final CustomMethod cm = new CustomMethod(type.getTypeFactory(), false);
        cm.setOwner(type);
        cm.setAnnotations(annotations);
        cm.setModifiers(Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT);
        cm.setName(element.getVariable().getName());
        cm.setParameters(Lists.<IFormalParameter>newLinkedList());
        cm.setReturnType(ptype);
        cm.setThrowsClause(ImmutableList.<IClassType>of());

        methods.add(cm);
    }

    /**
     * This method performs type-initialization of a element's setter.
     *
     * @param methods is the mutable collection of methods in the struct's type.
     * @param element is the element whose setter's type-system representation will be created.
     * @param ptype is the type of the value stored in the element.
     */
    private void initSetter(final Collection<IMethod> methods,
                            final FormalParameter property,
                            final IVariableType ptype)
    {
        /**
         * Create the type-system representations of the setter's annotations.
         */
        final List<IAnnotation> annotations = Lists.newLinkedList();
        annotations.add(module.anno_utils.typeOf(Setter.class));

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
        cm.setName(property.getVariable().getName());
        cm.setParameters(ImmutableList.<IFormalParameter>of(param));
        cm.setReturnType(type);
        cm.setThrowsClause(ImmutableList.<IClassType>of());

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

package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ClassFile;
import autumn.lang.compiler.ast.nodes.AnnotationDefinition;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import high.mackenzie.autumn.lang.compiler.typesystem.CustomDeclaredType;
import high.mackenzie.autumn.lang.compiler.utils.Utils;
import high.mackenzie.autumn.resources.Finished;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;

/**
 * An instance of this class is a compiler that compiles an annotation-definition.
 *
 * @author Mackenzie High
 */
@Finished("2014/08/11")
final class AnnotationCompiler
        implements ICompiler
{
    /**
     * Essentially, this is the program that is being compiled.
     */
    public final ProgramCompiler program;

    /**
     * Essentially, this is the module that contains the annotation-definition.
     */
    public final ModuleCompiler module;

    /**
     * This is the Abstract-Syntax-Tree representation of the annotation-definition.
     */
    public final AnnotationDefinition node;

    /**
     * This will be the type-system representation of the annotation-definition.
     *
     * This field is set during the type-declaration compiler pass.
     */
    public CustomDeclaredType type;

    /**
     * Sole Constructor.
     *
     * @param module is the module that contains the enum being compiled.
     * @param node is the AST node that represents the enum being compiled.
     */
    public AnnotationCompiler(final ModuleCompiler module,
                              final AnnotationDefinition node)
    {
        Preconditions.checkNotNull(module);
        Preconditions.checkNotNull(node);

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
        final String internal_name = Utils.internalName(type);

        final String source_name = Utils.sourceName(type);

        /**
         * Create the bytecode representation of the annotation itself.
         */
        final ClassNode clazz = new ClassNode();
        {
            clazz.version = Opcodes.V1_6;
            clazz.visibleAnnotations = Lists.newLinkedList();
            clazz.visibleAnnotations.addAll(module.anno_utils.compileAnnotationList(type.getAnnotations()));
            clazz.visibleAnnotations.add(createRuntimeAnnotation());
            clazz.access = type.getModifiers();
            clazz.name = internal_name;
            clazz.superName = Utils.internalName(type.getSuperclass());
            clazz.interfaces = program.typesystem.utils.internalNamesOf(type.getSuperinterfaces());
            clazz.fields = ImmutableList.of();
            clazz.methods = ImmutableList.of();
            clazz.sourceFile = String.valueOf(node.getLocation().getFile());
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
        final ClassFile file = new ClassFile(source_name, bytecode);

        return file;
    }

    /**
     * This method creates the annotation that causes the annotation
     * being compiled to have runtime retention.
     *
     * @return the bytecode representation of the aforesaid annotation.
     */
    private AnnotationNode createRuntimeAnnotation()
    {
        final AnnotationNode anno = new AnnotationNode("Ljava/lang/annotation/Retention;");

        final String[] constant = new String[2];
        constant[0] = "Ljava/lang/annotation/RetentionPolicy;";
        constant[1] = "RUNTIME";

        anno.values = Lists.newLinkedList();
        anno.values.add("value");
        anno.values.add(constant);

        return anno;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeDeclaration()
    {
        /**
         * Determine the descriptor of the annotation.
         */
        final String namespace = module.type.getNamespace().replace('.', '/');
        final String name = node.getName().getName();
        final String descriptor = "L" + namespace + '/' + name + ';';

        /**
         * Ensure that the name is not forbidden.
         */
        program.checker.requireLegalName(node.getName());

        /**
         * Ensure that the type was not already declared elsewhere.
         */
        program.checker.requireNonDuplicateType(node.getName(), descriptor);

        /**
         * Declare the annotation.
         */
        this.type = program.typesystem.typefactory().newAnnotationType(descriptor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeInitialization()
    {
        /**
         * Set the annotation's applied to the annotation-type.
         */
        type.setAnnotations(module.anno_utils.typesOf(node.getAnnotations()));

        /**
         * Set the type's modifier flags.
         */
        type.setModifiers(Opcodes.ACC_PUBLIC
                          + Opcodes.ACC_ABSTRACT
                          + Opcodes.ACC_INTERFACE
                          + Opcodes.ACC_ANNOTATION);

        /**
         * Set the superclass of the annotation-type.
         */
        type.setSuperclass(program.typesystem.utils.OBJECT);

        /**
         * An annotation has a single superinterface, namely java.lang.annotation.Annotation.
         */
        type.setSuperinterfaces(Lists.newArrayList(program.typesystem.utils.ANNOTATION));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeStructureChecking()
    {
        // Pass, because the type does not have any structure to check.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeUsageChecking()
    {
        // Pass, because the type does not have any usages to check.
    }
}

package high.mackenzie.autumn.lang.compiler.utils;

import autumn.lang.compiler.ast.nodes.Annotation;
import autumn.lang.compiler.ast.nodes.AnnotationList;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import high.mackenzie.autumn.lang.compiler.compilers.ModuleCompiler;
import high.mackenzie.autumn.lang.compiler.typesystem.CustomAnnotation;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IAnnotation;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IAnnotationType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IType;
import java.util.List;
import org.objectweb.asm.tree.AnnotationNode;

/**
 *
 * @author mackenzie
 */
public final class AnnotationUtils
{
    private final ModuleCompiler module;

    public AnnotationUtils(final ModuleCompiler module)
    {
        this.module = module;
    }

    /**
     * This method compiles a single annotation to its bytecode representation.
     *
     * <p>
     * Note: This method does not perform any type-checking.
     * </p>
     *
     * @param annotation is the annotation to compile.
     * @return the bytecode representation of the given annotation.
     */
    public AnnotationNode compileAnnotation(final Annotation annotation)
    {
        final IAnnotation type = this.typeOf(annotation);

        final String descriptor = type.getAnnotationType().getDescriptor();

        final AnnotationNode node = new AnnotationNode(descriptor);

        node.values = ImmutableList.of();

        return node;
    }

    /**
     * This method compiles a list of annotations to their bytecode representations.
     *
     * <p>
     * Note: This method does not perform any type-checking.
     * </p>
     *
     * @param annotations are the annotations to compile.
     * @return an immutable list containing the compiled list of annotations.
     */
    public List<AnnotationNode> compileAnnotationList(final AnnotationList annotations)
    {
        final List<AnnotationNode> result = Lists.newLinkedList();

        for (Annotation anno : annotations.getAnnotations())
        {
            result.add(this.compileAnnotation(anno));
        }

        return ImmutableList.copyOf(result);
    }

    /**
     * This method gets the type representations of the annotations in an annotation-list.
     *
     * <p>
     * Note: This method does not perform any type-checking.
     * </p>
     *
     * @param annotations is the annotation-list itself.
     * @return an immutable list containing the types of the annotations in the annotation-list.
     */
    public List<IAnnotation> typesOf(final AnnotationList annotations)
    {
        final List<IAnnotation> result = Lists.newLinkedList();

        for (Annotation anno : annotations.getAnnotations())
        {
            final IAnnotation type = this.typeOf(anno);

            result.add(type);
        }

        return ImmutableList.copyOf(result);
    }

    /**
     * This method creates the type-system representation of an annotation from its AST node.
     *
     * @param annotation is the AST node that represents the annotation.
     * @return the annotation's type-system representation,
     * or null, if no such representation can be created.
     */
    public IAnnotation typeOf(final Annotation annotation)
    {
        final IType type = module.resolveType(annotation.getType());

        if (type == null || type instanceof IAnnotationType == false)
        {
            return null;
        }

        final IAnnotation result = new CustomAnnotation(null, (IAnnotationType) type);

        return result;
    }
}

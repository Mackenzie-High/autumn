package com.mackenziehigh.autumn.lang.compiler.utils;

import autumn.lang.compiler.ast.nodes.Annotation;
import autumn.lang.compiler.ast.nodes.AnnotationList;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mackenziehigh.autumn.lang.compiler.compilers.ModuleCompiler;
import com.mackenziehigh.autumn.lang.compiler.typesystem.CustomAnnotation;
import com.mackenziehigh.autumn.lang.compiler.typesystem.ICustomAnnotatable;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IAnnotation;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IAnnotationType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IType;
import java.util.LinkedList;
import java.util.List;
import org.objectweb.asm.tree.AnnotationNode;

/**
 * An instance of this class provides generalized methods for processing annotations.
 *
 * @author Mackenzie High
 */
public final class AnnotationUtils
{
    /**
     * Essentially, this is the enclosing module that is bing compiled.
     */
    private final ModuleCompiler module;

    /**
     * Sole Constructor.
     *
     * @param module is essentially the module that is being compiled.
     */
    public AnnotationUtils(final ModuleCompiler module)
    {
        Preconditions.checkNotNull(module);

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
    public AnnotationNode compileAnnotation(final IAnnotation annotation)
    {
        Preconditions.checkNotNull(annotation);

        final String descriptor = annotation.getAnnotationType().getDescriptor();

        final AnnotationNode node = new AnnotationNode(descriptor);

        final List<String> values = annotation.getAnnotationValues();

        if (values == null)
        {
            node.values = null;
        }
        else
        {
            node.values = Lists.newArrayList("value", values);
        }

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
    public List<AnnotationNode> compileAnnotationList(final Iterable<IAnnotation> annotations)
    {
        Preconditions.checkNotNull(annotations);

        final List<AnnotationNode> result = Lists.newLinkedList();

        for (IAnnotation anno : annotations)
        {
            result.add(this.compileAnnotation(anno));
        }

        return ImmutableList.copyOf(result);
    }

    /**
     * This method gets the type representations of the annotations in an annotation-list.
     *
     * <p>
     * This method performs type-checking on the annotation-list.
     * </p>
     *
     * @param annotations is the annotation-list itself.
     * @return an immutable list containing the types of the annotations in the annotation-list.
     */
    public List<IAnnotation> typesOf(final AnnotationList annotations)
    {
        Preconditions.checkNotNull(annotations);

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
     * <p>
     * The method will issue an error-message, if the annotation-type does not exist.
     * </p>
     *
     * @param annotation is the AST node that represents the annotation.
     * @return the annotation's type-system representation,
     * or null, if no such representation can be created.
     */
    public IAnnotation typeOf(final Annotation annotation)
    {
        Preconditions.checkNotNull(annotation);

        final IType type = module.imports.resolveReturnType(annotation.getType());

        if (type == null || type instanceof IAnnotationType == false)
        {
            return null;
        }

        final List<String> values = annotation.getValues();

        final IAnnotation result = new CustomAnnotation(null, values, (IAnnotationType) type);

        return result;
    }

    /**
     * This method creates the type-system representation of an annotation a Class object.
     *
     * @param type is the class-object that represents the annotation-type.
     * @return the annotation's type-system representation,
     * or null, if no such representation can be created.
     */
    public IAnnotation typeOf(final Class type)
    {
        Preconditions.checkNotNull(type);

        final IAnnotationType annotation_type = (IAnnotationType) module.program.typesystem
                .typefactory()
                .fromClass(type);

        final IAnnotation result = new CustomAnnotation(null, null, annotation_type);

        return result;
    }

    /**
     * This method adds a marker annotation to an annotatable custom entity.
     *
     * @param annotated is the annotatable custom entity.
     * @param type is the type of the annotation.
     */
    public void add(final ICustomAnnotatable annotated,
                    final Class type)
    {
        Preconditions.checkNotNull(annotated);
        Preconditions.checkNotNull(type);

        /**
         * Get the type-system representation of the marker annotation.
         */
        final IAnnotation annotation = typeOf(type);

        /**
         * Get the annotations that are already applied to the entity.
         */
        final List<IAnnotation> immutable = annotated.getAnnotations();

        /**
         * We need an immutable list in order to add the new annotation.
         */
        final List<IAnnotation> mutable = new LinkedList<IAnnotation>(immutable);

        /**
         * Add the new marker annotation to the entity.
         */
        mutable.add(annotation);
        annotated.setAnnotations(mutable);
    }
}

package com.mackenziehigh.autumn.lang.compiler.typesystem;

import autumn.util.F;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IAnnotation;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IAnnotationType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.ITypeFactory;
import com.mackenziehigh.autumn.resources.Finished;
import java.lang.annotation.Annotation;
import java.util.List;

/**
 * An instance of this class represents an annotation instantiation.
 *
 * <p>
 * <b>Example:</b>
 * <code>@Person</code> <br>
 * "Person" is the name of the annotation type (i.e. the annotation-definition).
 * </p>
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public final class CustomAnnotation
        implements IAnnotation
{
    private final Annotation annotation;

    private final List<String> values;

    private final IAnnotationType definition;

    /**
     * Constructor.
     *
     * @param annotation is the annotation that the new object will represent,
     * or null, if the new object is not backed by a real annotation.
     * @param values are the values stored in the annotation, or null, if no such values are present.
     * @param definition is the type of the annotation's definition.
     */
    public CustomAnnotation(final Annotation annotation,
                            final List<String> values,
                            final IAnnotationType definition)
    {
        Preconditions.checkNotNull(definition);

        this.annotation = annotation;
        this.definition = definition;
        this.values = values == null ? null : ImmutableList.copyOf(values);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IAnnotationType getAnnotationType()
    {
        return definition;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getAnnotationValues()
    {
        return values;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Annotation toAnnotation()
    {
        return annotation;
    }

    /**
     * This method creates a new IAnnotation from an actual annotation.
     *
     * @param factory type-factory that is used to access types.
     * @param annotation is the actual annotation.
     * @return the type-system representation of the annotation.
     */
    public static IAnnotation fromAnnotation(final ITypeFactory factory,
                                             final Annotation annotation)
    {
        Preconditions.checkNotNull(factory);
        Preconditions.checkNotNull(annotation);

        final Class clazz = annotation.annotationType();

        final IAnnotationType type = (IAnnotationType) factory.fromClass(clazz);

        final List<String> values = F.iter(annotation);

        final IAnnotation result = new CustomAnnotation(annotation, values, type);

        return result;
    }
}

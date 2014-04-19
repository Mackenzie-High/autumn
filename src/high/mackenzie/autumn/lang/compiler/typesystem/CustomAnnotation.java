package high.mackenzie.autumn.lang.compiler.typesystem;

import high.mackenzie.autumn.lang.compiler.typesystem.design.IAnnotation;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IAnnotationType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.ITypeFactory;
import java.lang.annotation.Annotation;

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
public final class CustomAnnotation
        implements IAnnotation
{
    private final Annotation annotation;

    private final IAnnotationType definition;

    /**
     * Constructor.
     *
     * @param annotation is the annotation that the new object will represent,
     * or null, if the new object is not backed by a real annotation.
     * @param definition is the type of the annotation's definition.
     */
    public CustomAnnotation(final Annotation annotation,
                            final IAnnotationType definition)
    {
        this.annotation = annotation;

        this.definition = definition;
    }

    @Override
    public IAnnotationType getAnnotationType()
    {
        return definition;
    }

    @Override
    public Annotation toAnnotation()
    {
        return annotation;
    }

    /**
     *
     *
     * @param annotation
     * @return
     */
    public static final IAnnotation fromAnnotation(final ITypeFactory factory,
                                                   final Annotation annotation)
    {
        final Class clazz = annotation.annotationType();

        final IAnnotationType type = (IAnnotationType) factory.fromClass(clazz);

        final IAnnotation result = new CustomAnnotation(annotation, type);

        return result;
    }
}

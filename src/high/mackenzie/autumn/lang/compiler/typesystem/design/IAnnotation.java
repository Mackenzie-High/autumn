package high.mackenzie.autumn.lang.compiler.typesystem.design;

import high.mackenzie.autumn.resources.Finished;
import java.lang.annotation.Annotation;

/**
 * An instance of this interface is an instantiation of an annotation.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public interface IAnnotation
{
    /**
     * This method tries to return the annotation related to this type.
     *
     * @return the annotation representation of this type, if an annotation backs this
     * object; otherwise, return null.
     */
    public Annotation toAnnotation();

    /**
     * This method returns the type of the annotation-definition.
     *
     * <p>
     * This method may return null, under abnormal circumstances. <br>
     * For example, this method will return null, if the name of the annotation is
     * misspelled.
     * </p>
     *
     * @return the annotation-definition of the underlying annotation, or null, if no such
     * object can be found.
     */
    public IAnnotationType getAnnotationType();
}

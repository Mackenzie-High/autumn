package high.mackenzie.autumn.lang.compiler.typesystem.design;

import high.mackenzie.autumn.resources.Finished;

/**
 * An instance of this interface is the type of a method in an annotation-definition.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public interface IAnnotationMethod
        extends IMethod
{
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAnnotationMethod();
}

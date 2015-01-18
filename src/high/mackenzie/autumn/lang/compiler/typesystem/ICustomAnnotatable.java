package high.mackenzie.autumn.lang.compiler.typesystem;

import high.mackenzie.autumn.lang.compiler.typesystem.design.IAnnotatable;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IAnnotation;
import high.mackenzie.autumn.resources.Finished;
import java.util.Collection;

/**
 * An instance of this interface represents a customized annotatable entity.
 *
 * @author Mackenzie High
 */
@Finished("2015/01/17")
public interface ICustomAnnotatable
        extends IAnnotatable
{
    /**
     * Setter.
     *
     * @param annotations are the annotations applied directly to this entity.
     */
    public void setAnnotations(Collection<IAnnotation> annotations);
}

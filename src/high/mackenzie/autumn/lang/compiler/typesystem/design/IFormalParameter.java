package high.mackenzie.autumn.lang.compiler.typesystem.design;

import high.mackenzie.autumn.resources.Finished;
import java.util.List;

/**
 * An instance of this interface is the type of a formal parameter.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public interface IFormalParameter
        extends IAnnotatable
{
    /**
     * {@inheritDoc}
     */
    @Override
    public List<IAnnotation> getAnnotations();

    /**
     * This method returns the type of value that this formal-parameter can accept.
     *
     * @return the formal type of this formal-parameter.
     */
    public IVariableType getType();
}

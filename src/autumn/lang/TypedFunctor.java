package autumn.lang;

import high.mackenzie.autumn.resources.Finished;
import java.util.List;

/**
 * An instance of this interface is a functor that has typed formal-parameters and a return-type.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public interface TypedFunctor
        extends Functor
{
    /**
     * This method retrieves the types of the functor's formal-parameters.
     *
     * @return an immutable list of the formal-parameter's types.
     */
    public List<Class> parameterTypes();

    /**
     * This method retrieves the type of the value that is returned by this functor.
     *
     * @return the return-type of this functor.
     */
    public Class returnType();
}

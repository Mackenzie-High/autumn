package autumn.lang;

import high.mackenzie.autumn.resources.Finished;
import java.lang.reflect.Method;
import java.util.List;

/**
 * An instance of this interface is a delegate.
 *
 * <p>
 * A delegate is a functor that simply invokes a function.
 * </p>
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public interface Delegate
        extends TypedFunctor
{
    /**
     * This method retrieves the module that contains the delegated function.
     *
     * @return the module that contains the function that this delegate refers to.
     */
    public Module module();

    /**
     * This method retrieves the type that owns the method that is invoked by this delegate.
     *
     * @return the owner of the underlying method.
     */
    public Class owner();

    /**
     * This method retrieves the name of the method that is invoked by this delegate.
     *
     * @return the name of the underlying method.
     */
    public String name();

    /**
     * This method retrieves the return-type of the the method that is invoked by this delegate.
     *
     * @return the return-type of the underlying method.
     */
    @Override
    public Class returnType();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Class> parameterTypes();

    /**
     * This method retrieves the reflection of the method that is invoked by this delegate.
     *
     * <p>
     * Remember: functions are really just static methods in a class.
     * </p>
     *
     * @return the reflective view of the underlying method.
     */
    public Method method();
}

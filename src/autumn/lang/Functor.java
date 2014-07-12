package autumn.lang;

import autumn.lang.internals.ArgumentStack;
import high.mackenzie.autumn.resources.Finished;
import java.util.List;

/**
 * An instance of this interface is a function object.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public interface Functor
{
    /**
     * This method performs an invocation of this function object.
     *
     * <p>
     * The argument stack will be cleared and then the result will be pushed onto it.
     * If the functor does not return a value, then the stack will remain empty.
     * </p>
     *
     * @param stack is a stack containing the arguments being passed to this functor.
     */
    public void invoke(final ArgumentStack stack)
            throws Throwable;

    /**
     * This method applies this functor to an array of arguments.
     *
     * <p>
     * This method is intended to simplify the invocation of functors in Java code.
     * </p>
     *
     * @param arguments are the arguments being passed to the functor.
     * @return the result of executing this functor.
     */
    public Object apply(final Object... arguments)
            throws Throwable;

    /**
     * This method applies this functor to a list of arguments.
     *
     * @param arguments are the arguments being passed to the functor.
     * @return the result of executing this functor.
     */
    public Object apply(final List arguments)
            throws Throwable;
}

package autumn.util;

import autumn.lang.Functor;

/**
 * This class provides static utility methods for working with function objects.
 *
 * @author Mackenzie High
 */
public final class Functors
{
    /**
     * This method applies a functor to a list of arguments without risking a checked exception.
     *
     * @param functor is the function object.
     * @param arguments are the arguments to pass to the functor.
     * @return the value returned by the functor.
     */
    public static Object quietlyApply(final Functor functor,
                                      final Iterable<?> arguments)
    {
        try
        {
            return F.apply(functor, arguments);
        }
        catch (Throwable ex)
        {
            F.rethrow(ex);
        }

        throw new RuntimeException("This should never happen.");
    }
}

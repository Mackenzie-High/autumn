package autumn.util;

import autumn.lang.Functor;
import autumn.lang.TypedFunctor;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.util.List;

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

    /**
     * This method creates a list describing the signature of a functor.
     *
     * @param functor is the functor itself.
     * @return a new immutable list: [return-type, parameter-type-1, ... , parameter-type-N]
     */
    public static List<Class> signatureOf(final TypedFunctor functor)
    {
        final List<Class> sig = Lists.newLinkedList();

        sig.add(functor.returnType());

        sig.addAll(functor.parameterTypes());

        return ImmutableList.copyOf(sig);
    }
}

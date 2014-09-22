package autumn.util;

import autumn.lang.TypedFunctor;
import autumn.lang.exceptions.CheckedException;
import com.google.common.base.Preconditions;
import java.util.Collections;

/**
 * This class provides static utility methods for working with threads.
 *
 * @author Mackenzie High
 */
public final class Threads
{
    /**
     * This method creates a new Thread object that can run a functor.
     *
     * <p>
     * This method does not start the thread.
     * </p>
     *
     * @param functor is the functor that is run by the new thread.
     * @return the newly created thread.
     * @throws IllegalArgumentException if the functor is non-nullary.
     */
    public static Thread newThread(final TypedFunctor functor)
    {
        Preconditions.checkNotNull(functor);
        Preconditions.checkArgument(functor.parameterTypes().isEmpty(),
                                    "The functor cannot take any parameters.");

        final Runnable runnable = new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    F.apply(functor, Collections.EMPTY_LIST);
                }
                catch (Throwable ex)
                {
                    throw new CheckedException(ex);
                }
            }
        };

        final Thread thread = new Thread(runnable);

        return thread;
    }

    /**
     * This method synchronizes the invocation of a functor.
     *
     * @param locked is the object to obtain a lock on.
     * @param body is the functor to invoke.
     * @throws Throwable if an exception is thrown by the functor.
     * @throws IllegalArgumentException if the functor is non-nullary.
     */
    public static void sync(final Object locked,
                            final TypedFunctor body)
            throws Throwable
    {
        Preconditions.checkNotNull(body);
        Preconditions.checkArgument(body.parameterTypes().isEmpty(),
                                    "The functor cannot take any parameters.");

        synchronized (locked)
        {
            F.apply(body, Collections.EMPTY_LIST);
        }
    }
}

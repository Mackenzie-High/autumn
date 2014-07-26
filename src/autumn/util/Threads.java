package autumn.util;

import autumn.lang.Functor;
import java.util.Collections;

/**
 * This class provides static utility methods for working with threads.
 *
 * @author Mackenzie High
 */
public final class Threads
{
    public static Thread newThread(final Functor functor)
    {
        return null;
    }

    public static void sync(final Object locked,
                            final Functor body)
            throws Throwable
    {
        synchronized (locked)
        {
            F.apply(body, Collections.EMPTY_LIST);
        }
    }
}

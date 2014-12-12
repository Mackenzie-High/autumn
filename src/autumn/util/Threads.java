package autumn.util;

import autumn.util.functors.Action;
import com.google.common.base.Preconditions;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

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
    public static Thread newThread(final Action functor)
    {
        Preconditions.checkNotNull(functor);
        Preconditions.checkArgument(functor.parameterTypes().isEmpty(),
                                    "The functor cannot take any parameters.");

        final Runnable runnable = newRunnable(functor);

        final Thread thread = new Thread(runnable);

        return thread;
    }

    /**
     * This method creates a new TimerTask that wraps an Action.
     *
     * @param action is the wrapped action.
     * @return the new task.
     */
    public static TimerTask newTimerTask(final Action action)
    {
        Preconditions.checkNotNull(action);

        return new TimerTask()
        {
            @Override
            public void run()
            {
                Functors.quietlyApply(action, Collections.EMPTY_LIST);
            }
        };
    }

    /**
     * This method creates a new Runnable object that wraps an Action.
     *
     * @param action is the wrapped action.
     * @return the new runnable object.
     */
    public static Runnable newRunnable(final Action action)
    {
        Preconditions.checkNotNull(action);

        return new Runnable()
        {
            @Override
            public void run()
            {
                Functors.quietlyApply(action, Collections.EMPTY_LIST);
            }
        };
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
                            final Action body)
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

    /**
     * This method creates a new thread and uses it to immediately run a given action.
     *
     * <p>
     * Equivalent:
     * <code>
     * Threads.newThread(action).start()
     * </code>
     * </p>
     *
     * @param action is the action that will run on the newly created thread.
     */
    public static void run(final Action action)
    {
        Threads.newThread(action).start();
    }

    /**
     * This method invokes a given action based on a clock.
     *
     * <p>
     * You may want to use a Timer object instead, which is what this method uses internally.
     * </p>
     *
     * @param action is the action to invoke.
     * @param interval is the approximate number of milliseconds between each invocation.
     * @param count is the maximum number of times to invoke the action.
     */
    public static void ticker(final Action action,
                              final long interval,
                              final int count)
    {
        Preconditions.checkNotNull(action);
        Preconditions.checkNotNull(interval >= 0);
        Preconditions.checkNotNull(count >= 0);

        /**
         * This object will be used to count the number of times that the action has executed.
         */
        final AtomicInteger counter = new AtomicInteger();

        /**
         * This is essentially the clock that will fire the event.
         */
        final Timer timer = new Timer();

        /**
         * This is the event that will be fired by the clock.
         */
        final TimerTask task = new TimerTask()
        {
            @Override
            public void run()
            {
                if (counter.get() >= count)
                {
                    timer.cancel();
                    return;
                }
                else
                {
                    counter.incrementAndGet();
                    Functors.quietlyApply(action, Collections.EMPTY_LIST);
                }
            }
        };

        /**
         * Start the clock.
         */
        timer.scheduleAtFixedRate(task, 0, interval);
    }
}

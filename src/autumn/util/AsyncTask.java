package autumn.util;

import autumn.util.functors.Action;

/**
 * An instance of this interface encapsulates a task to perform asynchronously.
 *
 * @author Mackenzie High
 */
public interface AsyncTask
{
    /**
     * This method creates an exact copy of this object.
     *
     * @return a copy of this object.
     */
    public AsyncTask copy();

    /**
     * TODO: wrong functor type
     *
     * This method retrieves the action that will be performed by this task.
     *
     * @return the underlying action that will be performed asynchronously.
     */
    public Action action();

    /**
     * This method makes this task runnable and then immediately returns.
     *
     * <p>
     * This task may not be executed immediately.
     * Instead, this task will be executed when a thread becomes available for it.
     * Thus, the return of this method does not imply that the task has completed.
     * </p>
     *
     * @throws IllegalStateException if this method was already invoked.
     */
    public void run();

    /**
     * This method tries to cancel this task.
     *
     * @return true, iff this task was successfully canceled due to this invocation.
     */
    public boolean cancel();

    /**
     * This method determines whether this task was canceled.
     *
     * @return true, iff this task was successfully canceled.
     */
    public boolean isCanceled();

    /**
     * This method determines whether this task is currently running.
     *
     * @return true, iff this task is currently running.
     */
    public boolean isRunning();

    /**
     * This method determines whether the task has finished running yet.
     *
     * @return true, iff the task has finished running.
     */
    public boolean isFinished();

    /**
     * This method attempts to retrieve the result of this task.
     *
     * @param block is true, iff this method must block until the task completes.
     * @return the result of successfully completing the task; otherwise, return null.
     */
    public Object result(final boolean block);

    /**
     * This method binds an action hereto to perform after completion of the task.
     *
     * @param functor is an action to perform after the task finishes.
     * @throws NullPointerException if functor is null.
     */
    public void after(final Action functor);

    /**
     * This method binds an action hereto to perform after an error.
     *
     * @param functor is an action to perform, if the task cannot finish due to an exception.
     * @throws NullPointerException if functor is null.
     */
    public void error(final Action functor);
}

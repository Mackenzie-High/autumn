package autumn.lang.exceptions;

import high.mackenzie.autumn.resources.Finished;

/**
 * An instance of this class prevents the need for throws clauses by wrapping a checked-exception.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public final class CheckedException
        extends RuntimeException
{
    /**
     * Sole Constructor.
     *
     * @param ex is the checked exception.
     */
    public CheckedException(final Throwable ex)
    {
    }
}

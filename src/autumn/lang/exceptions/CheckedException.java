package autumn.lang.exceptions;

/**
 * An instance of this class prevents the need for throws clauses by wrapping a checked-exception.
 *
 * @author Mackenzie High
 */
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

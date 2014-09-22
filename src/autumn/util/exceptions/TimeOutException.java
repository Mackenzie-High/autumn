package autumn.util.exceptions;

/**
 * This type of exception indicates that an operation timed-out.
 *
 * @author Mackenzie High
 */
public final class TimeOutException
        extends RuntimeException
{
    /**
     * Constructor.
     */
    public TimeOutException()
    {
        // Pass
    }

    /**
     * Constructor.
     *
     * @param message is a custom error-message.
     */
    public TimeOutException(String message)
    {
        super(message);
    }
}

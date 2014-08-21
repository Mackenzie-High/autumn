package autumn.util.test;

/**
 * This type of exception indicates that there is a problem with a test function.
 *
 * <p>
 * This type of exception will be thrown, if a test function does
 * not take TestCase parameter or the test function does not return void.
 * </p>
 *
 * @author Mackenzie High
 */
public final class MalformedTestException
        extends RuntimeException
{
    /**
     * Sole Constructor.
     *
     * @param message is a message describing the problem.
     */
    public MalformedTestException(final String message)
    {
        super(message);
    }
}

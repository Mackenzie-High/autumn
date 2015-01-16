package autumn.lang.exceptions;

import high.mackenzie.autumn.resources.Finished;

/**
 * This type of exception is thrown by a dispatch-expression,
 * when none of the statically applicable function overloads
 * will accept the arguments at runtime.
 *
 * @author Mackenzie High
 */
@Finished("2015/01/15")
public final class DispatchException
        extends RuntimeException
{
    /**
     * Sole Constructor.
     */
    public DispatchException()
    {
        // Pass
    }
}

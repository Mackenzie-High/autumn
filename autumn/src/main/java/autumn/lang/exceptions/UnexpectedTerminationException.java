package autumn.lang.exceptions;

import com.mackenziehigh.autumn.resources.Finished;

/**
 * This type of exception indicates that execution reached the end a function without
 * encountering a return-statement, etc.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public final class UnexpectedTerminationException
        extends RuntimeException
{
    // Pass
}

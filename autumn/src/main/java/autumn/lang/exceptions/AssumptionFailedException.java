package autumn.lang.exceptions;

import com.google.common.base.Preconditions;
import high.mackenzie.autumn.resources.Finished;

/**
 * This type of exception is thrown by assume-statements, when a required condition does not hold.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public class AssumptionFailedException
        extends RuntimeException
{
    /**
     * This is the path to the source-code file that contains the assumption.
     */
    private final String file;

    /**
     * This is the index of the line that contains the assumption.
     */
    private final int line;

    /**
     * Constructor.
     *
     * @param file is the file that contains the assumption.
     * @param line is the index of the line that contains the assumption.
     * @param message is a user-defined error-message.
     */
    public AssumptionFailedException(final String file,
                                     final int line,
                                     final String message)
    {
        super(message);

        Preconditions.checkNotNull(file);
        Preconditions.checkNotNull(message);

        this.file = file;
        this.line = line;
    }

    /**
     * Constructor.
     *
     * @param file is the file that contains the assumption.
     * @param line is the index of the line that contains the assumption.
     */
    public AssumptionFailedException(final String file,
                                     final int line)
    {
        super();

        Preconditions.checkNotNull(file);

        this.file = file;
        this.line = line;
    }

    /**
     * This method retrieves the path to the file that contains the assumption.
     *
     * @return the path to the file that contains the assumption.
     */
    public String file()
    {
        return file;
    }

    /**
     * This method retrieves the index of the line that contains the assumption.
     *
     * @return the index of the line that contains the assumption.
     */
    public int line()
    {
        return line;
    }
}

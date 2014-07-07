package autumn.lang.exceptions;

import com.google.common.base.Preconditions;
import java.io.File;

/**
 * This type of exception is thrown by assume-statements, when a required condition does not hold.
 *
 * @author Mackenzie High
 */
public class AssumptionFailedException
        extends RuntimeException
{
    /**
     * This is the path to the source-code file that contains the assertion.
     */
    private final File file;

    /**
     * This is the index of the line that contains the assertion.
     */
    private final int line;

    /**
     * Constructor.
     *
     * @param file is the file that contains the assertion.
     * @param line is the index of the line that contains the assertion.
     * @param message is a user-defined error-message.
     */
    public AssumptionFailedException(final String file,
                                     final int line,
                                     final String message)
    {
        super(message);

        Preconditions.checkNotNull(file);
        Preconditions.checkNotNull(message);

        this.file = new File(file);
        this.line = line;
    }

    /**
     * Constructor.
     *
     * @param file is the file that contains the assertion.
     * @param line is the index of the line that contains the assertion.
     */
    public AssumptionFailedException(final String file,
                                     final int line)
    {
        super();

        Preconditions.checkNotNull(file);

        this.file = new File(file);
        this.line = line;
    }

    /**
     * This method retrieves the path to the file that contains the assertion.
     *
     * @return the path to the file that contains the assertion.
     */
    public File file()
    {
        return file;
    }

    /**
     * This method retrieves the index of the line that contains the assertion.
     *
     * @return the index of the line that contains the assertion.
     */
    public int line()
    {
        return line;
    }
}

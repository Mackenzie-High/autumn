package autumn.lang.compiler.errors;

import java.io.File;

/**
 * An instance of this interface is used to report compile-time errors to the user/programmer.
 *
 * <p>
 * <b>Specification Note: </b>
 * Error codes are assigned randomly using a random number generator.
 * This avoids the appearance of relationships between similarly numbered errors.
 * </p>
 *
 * @author Mackenzie High
 */
public interface IErrorReporter
{
    /**
     * This method reports that the parsing of a module failed.
     *
     * @param file is the path to the module's file, as supplied to the compiler.
     * @param line is an estimate of where parsing failed within the input.
     * @param column is an estimate of where parsing failed within the input.
     */
    public void reportSyntaxError(final File file,
                                  final int line,
                                  final int column);

    /**
     * This method reports that a static-check failed.
     *
     * @param report is a an error-report that describes what went wrong.
     */
    public void reportFailedCheck(final ErrorReport report);

    /**
     * This method returns the number of errors that were thus far reported by this reporter.
     *
     * @return the number of reported errors.
     */
    public int errorCount();
}

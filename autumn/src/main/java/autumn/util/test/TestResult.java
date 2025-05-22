package autumn.util.test;

import autumn.lang.Delegate;
import com.mackenziehigh.autumn.resources.Finished;
import java.io.PrintStream;

/**
 * An instance of this interface is the result of a single unit-test.
 *
 * <p>
 * Caution: This interface may have additional methods added in the future.
 * </p>
 *
 * @author Mackenzie High
 */
@Finished("2014/08/21")
public interface TestResult
{
    /**
     * This method retrieves the delegate that refers to the test function.
     *
     * @return the test function in functor form.
     */
    public Delegate function();

    /**
     * This method retrieves the name of the test-case.
     *
     * @return the name of the test-case.
     */
    public String name();

    /**
     * This method retrieves the description of the test-case.
     *
     * @return the description of the test-case.
     */
    public String description();

    /**
     * This method retrieves the class-object that represents the expected type of exception.
     *
     * <p>
     * For example, when the test-case should throw an IllegalArgumentException,
     * this method will return the class-object that represents IllegalArgumentException.
     * </p>
     *
     * @return the expected exception-type.
     */
    public Class expected();

    /**
     * This method retrieves the exception that was thrown by the test-case.
     *
     * @return the thrown exception; or null, if no exception was thrown.
     */
    public Throwable thrown();

    /**
     * This method determines whether the test-case passed.
     *
     * @return true, iff the test-case passed.
     */
    public boolean passed();

    /**
     * This method determines whether the test-case failed.
     *
     * @return true, iff the test-case failed.
     */
    public boolean failed();

    /**
     * This method retrieves the amount of time that the test-case took to execute.
     *
     * @return the number of milliseconds the test-case took executing.
     */
    public long executionTime();

    /**
     * This method prints a hopefully human readable representation of this test-result.
     *
     * @param out is the output-stream to print to.
     */
    public void print(final PrintStream out);
}

package autumn.util.test;

import high.mackenzie.autumn.resources.Finished;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;

/**
 * An instance of this interface contains the results of zero-or-more unit-tests.
 *
 * <p>
 * Caution: This interface may have additional methods added in the future.
 * </p>
 *
 * @author Mackenzie High
 */
@Finished("2014/08/21")
public interface TestResults
        extends Iterable<TestResult>
{
    /**
     * This method determines whether all of the test-cases passed.
     *
     * @return true, iff all of the test-cases passed.
     */
    public boolean passed();

    /**
     * This method determines whether any of the test-cases failed.
     *
     * @return true, iff any of the test-cases failed.
     */
    public boolean failed();

    /**
     * This method retrieves the total amount of time that the test-cases spent executing.
     *
     * @return the total number of milliseconds that the tests spent executing.
     */
    public long time();

    /**
     * This method prints a hopefully human readable description of these test results.
     *
     * @param out is the output-stream to print to.
     */
    public void print(PrintStream out);

    /**
     * This method converts this object to a list.
     *
     * @return an immutable list containing the individual test results.
     */
    public List<TestResult> results();

    /**
     * This method creates an iterator that iterates over the results herein.
     *
     * @return an iterator over the results().
     */
    @Override
    public Iterator<TestResult> iterator();
}

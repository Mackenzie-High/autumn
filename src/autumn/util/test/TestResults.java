package autumn.util.test;

import java.util.Iterator;

/**
 * An instance of this class contains the results of zero-or-more unit-tests.
 *
 * @author Mackenzie High
 */
public final class TestResults
        implements Iterable<TestResult>
{
    TestResults(final Iterable<TestResult> results)
    {
    }

    @Override
    public Iterator<TestResult> iterator()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

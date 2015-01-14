package high.mackenzie.autumn.resources;

import org.junit.Test;

/**
 * This class causes the tests in the tests/execution package to run.
 *
 * @author Mackenzie
 */
public final class ExecutionTest
{
    /**
     * Run the tests.
     */
    @Test
    public void main()
    {
        execution.Runner real_tester = new execution.Runner();

        real_tester.main();
    }
}

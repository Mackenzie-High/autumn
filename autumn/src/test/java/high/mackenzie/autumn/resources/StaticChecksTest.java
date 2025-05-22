package high.mackenzie.autumn.resources;

import org.junit.Test;

/**
 * This class causes the tests in the tests/typechecks package to run.
 *
 * @author Mackenzie
 */
public final class StaticChecksTest
{
    /**
     * Run the tests.
     */
    @Test
    public void main()
    {
        typechecks.Runner real_tester = new typechecks.Runner();

        real_tester.main();
    }
}

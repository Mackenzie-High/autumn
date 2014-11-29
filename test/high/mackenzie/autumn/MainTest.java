package high.mackenzie.autumn;

import java.io.IOException;
import org.junit.Test;

public final class MainTest
{
    /**
     * This method is used to invoke tests that are written in autumn.
     */
    @Test
    public void autumnBasedTests()
            throws IOException
    {
        execution.Runner.main(new String[0]);

        typechecks.Runner.main(new String[0]);
    }
}

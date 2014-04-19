package typechecks;

import autumn.lang.compiler.AutumnCompiler;
import autumn.lang.compiler.AutumnParser;
import autumn.lang.compiler.ast.nodes.Module;
import autumn.lang.compiler.errors.BasicErrorReporter;
import autumn.lang.compiler.errors.ErrorCode;
import static autumn.lang.compiler.errors.ErrorCode.*;
import com.google.common.collect.ImmutableSet;
import com.google.common.io.NullOutputStream;
import com.google.common.io.Resources;
import java.io.File;
import java.io.PrintStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Set;

public final class Runner
{
    private static final boolean FAILED = false;

    private static final boolean PASSED = true;

    private static int failed_count;

    /**
     * This method runs tests to determine whether the language's is type-checks work.
     * The results of the tests will be printed to STDOUT.
     *
     * @param args are ignored.
     */
    public static void main(final String[] args)
    {
        failed_count = 0;

        test("T0001", EXPECTED_CONDITION);
        test("T0002", EXPECTED_CONDITION);
        test("T0003", EXPECTED_CONDITION);
        test("T0004", EXPECTED_CONDITION);
        test("T0005", EXPECTED_CONDITION);
        test("T0006", EXPECTED_CONDITION);
        test("T0007", EXPECTED_CONDITION);
        test("T0008", EXPECTED_CONDITION);
        test("T0009", EXPECTED_STRING);
        test("T0010", EXPECTED_THROWABLE);
        test("T0011", DUPLICATE_DECLARATION_OF_VARIABLE);
        test("T0012", EXPECTED_THROWABLE);
        test("T0013", DUPLICATE_EXCEPTION_HANDLER);
        test("T0014", NO_SUCH_TYPE);
        test("T0015", DUPLICATE_DECLARATION_OF_VARIABLE);
        test("T0016", DUPLICATE_DECLARATION_OF_VARIABLE);
        test("T0017", NO_SUCH_VARIABLE);
        test("T0019", EXPECTED_CONDITION);
        test("T0020", DUPLICATE_DECLARATION_OF_LABEL);
        test("T0021", NO_SUCH_LABEL);

        System.out.println("Number of Failed Tests: " + failed_count);
    }

    /**
     * This method runs a specified test multiple times and then reports the results.
     *
     * @param test is the test to run.
     * @param errors are the expected error codes.
     */
    private static void test(final String test,
                             final ErrorCode... errors)
    {
        for (int i = 0; i < 10; i++)
        {
            if (singleTest(test, errors) == FAILED)
            {
                System.out.println("Test Failed: " + test);
                ++failed_count;
                return;
            }
        }

        System.out.println("Test Passed: " + test);
    }

    /**
     * This method runs a specified test one time and then returns a value indicating the result.
     *
     * @param test is the test to run.
     * @param errors are the expected error codes.
     * @return true, iff the test was successful.
     */
    private static boolean singleTest(final String test,
                                      final ErrorCode[] errors)
    {
        try
        {
            final String path = "/typechecks/" + test;

            final URL url = Resources.getResource(Runner.class, path);

            final String code = Resources.toString(url, Charset.defaultCharset());

            final NullOutputStream devnull = new NullOutputStream();

            final PrintStream stream = new PrintStream(devnull);

            final BasicErrorReporter reporter = new BasicErrorReporter(stream);

            final AutumnParser parser = new AutumnParser(reporter);

            final Module module = parser.parse(code, new File(path));

            if (module == null)
            {
                return FAILED;
            }

            final AutumnCompiler cmp = new AutumnCompiler(reporter);

            cmp.compile(module);

            final Set<ErrorCode> expected = ImmutableSet.copyOf(Arrays.asList(errors));

            final Set<ErrorCode> actual = ImmutableSet.copyOf(reporter.codes());

            return expected.equals(actual) ? PASSED : FAILED;
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            ex.printStackTrace(System.out);
            return FAILED;
        }
    }
}

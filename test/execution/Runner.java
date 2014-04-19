package execution;

import autumn.lang.compiler.AutumnCompiler;
import autumn.lang.compiler.AutumnParser;
import autumn.lang.compiler.CompiledProgram;
import autumn.lang.compiler.ast.nodes.Module;
import autumn.lang.compiler.errors.BasicErrorReporter;
import autumn.lang.compiler.errors.IErrorReporter;
import autumn.lang.exceptions.AssertionFailedException;
import com.google.common.io.Resources;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.charset.Charset;

public final class Runner
{
    private static final boolean FAILED = false;

    private static final boolean PASSED = true;

    private static int failed_count;

    /**
     * This method runs tests to determine whether the language is properly implemented.
     * The results of the tests will be printed to STDOUT.
     *
     * @param args are ignored.
     */
    public static void main(final String[] args)
    {
        failed_count = 0;

        test("T0077");
        test("T0076");
        test("T0075");
        test("T0074");
        test("T0073");
        test("T0072");
        test("T0071");
        test("T0070");
        test("T0069");
        test("T0068");
        test("T0067");
        test("T0066");
        test("T0065");
        test("T0064");
        test("T0063");
        test("T0062");
        test("T0061");
        test("T0060");
        test("T0058");
        test("T0057");
        test("T0056");
        test("T0055");
        test("T0054");
        test("T0053");
        test("T0052");
        test("T0051");
        test("T0050");
        test("T0049");
        test("T0046");
        test("T0044");
        test("T0041");
        test("T0039");
        test("T0038");
        test("T0037");
        test("T0036");
        test("T0035");
        test("T0034");
//        test("T0024");
//        test("T0023");
//        test("T0022");
        test("T0018");
        test("T0017");
        test("T0016");
        test("T0013");
        test("T0012");
        test("T0011");
        test("T0010");
        test("T0007");
        test("T0002");
        test("T0001");

        System.out.println("Number of Failed Tests: " + failed_count);
    }

    /**
     * This method runs a specified test multiple times and then reports the results.
     *
     * @param test is the test to run.
     * @param args are the command-line arguments to pass to the test's main function.
     */
    private static void test(final String test,
                             final String... args)
    {
        for (int i = 0; i < 10; i++)
        {
            if (singleTest(test, args) == FAILED)
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
     * @param args are the arguments to pass to the test's main function.
     * @return true, iff the test was successful.
     */
    private static boolean singleTest(final String test,
                                      final String[] args)
    {
        try
        {
            final String path = "/execution/" + test;

            final URL url = Resources.getResource(Runner.class, path);

            final String code = Resources.toString(url, Charset.defaultCharset());

            final IErrorReporter reporter = new BasicErrorReporter();

            final AutumnParser parser = new AutumnParser(reporter);

            final Module module = parser.parse(code, new File(path));

            if (module == null)
            {
                return FAILED;
            }

            final AutumnCompiler cmp = new AutumnCompiler();

            final CompiledProgram program = cmp.compile(module);

            if (program == null)
            {
                return FAILED;
            }

            program.load().invokeMain(args);
        }
        catch (InvocationTargetException ite)
        {
            final Throwable ex = ite.getCause();

            if (ex instanceof AssertionFailedException)
            {
                final AssertionFailedException afe = (AssertionFailedException) ex;

                System.out.println("Assertion Failed!");
                System.out.println("  File: " + afe.file());
                System.out.println("  Line #" + afe.line());
                afe.printStackTrace(System.out);
            }
            else
            {
                System.out.println(ex);
                ex.printStackTrace(System.out);
            }

            return FAILED;
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            ex.printStackTrace(System.out);
            return FAILED;
        }

        return PASSED;
    }
}

package stdlib;

import autumn.lang.compiler.Autumn;
import autumn.lang.compiler.CompiledProgram;
import autumn.lang.exceptions.AssertionFailedException;
import com.google.common.io.Resources;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

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



        test("T0007");
        test("T0006");
        test("T0005");
//        test("T0004");
//        test("T0003");
        test("T0002");
//        test("T0001");

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
            final String path = "/stdlib/" + test + ".leaf";

            final URL url = Resources.getResource(Runner.class, path);

//            final String code = Resources.toString(url, Charset.defaultCharset());
//
//            final IErrorReporter reporter = new BasicErrorReporter();
//
//            final AutumnParser parser = new AutumnParser(reporter);
//
//            final Module module = parser.parse(code, new File(path));
//
//            if (module == null)
//            {
//                return FAILED;
//            }

            final Autumn cmp = new Autumn();

            cmp.srcURL(url);

            final CompiledProgram program = cmp.compile();

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
                System.out.println("  Line: #" + afe.line());
                System.out.println("  Message: " + afe.getMessage());
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

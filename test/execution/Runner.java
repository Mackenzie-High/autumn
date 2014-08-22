package execution;

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




        test("T0219");
        test("T0218");
        test("T0217");
        test("T0216");
        test("T0215");
        test("T0214");
        test("T0213");
        test("T0212");
        test("T0211");
        test("T0210");
        test("T0209");
        test("T0208");
        test("T0207");
        test("T0206");
        test("T0205");
        test("T0204");
        test("T0203");
        test("T0202");
        test("T0201");
        test("T0200");
        test("T0199");
        test("T0198");
        test("T0197");
        test("T0196");
        test("T0195");
        test("T0194");
        test("T0193");
        test("T0192");
        test("T0191");
        test("T0190");
        test("T0189");
        test("T0188");
        test("T0187");
        test("T0186");
        test("T0185");
        test("T0184");
        test("T0183");
        test("T0182");
        test("T0181");
        test("T0180");
        test("T0179");
        test("T0178");
        test("T0177");
        test("T0176");
        test("T0175");
        test("T0174");
        test("T0173");
        test("T0172");
        test("T0171");
        test("T0170");
        test("T0169");
        test("T0168");
        test("T0167");
        test("T0166");
        test("T0165");
        test("T0164");
        test("T0163");
        test("T0162");
        test("T0161");
        test("T0160");
        test("T0159");
        test("T0158");
        test("T0157");
        test("T0156");
        test("T0155");
        test("T0154");
        test("T0153");
        test("T0152");
        test("T0151");
        test("T0150");
        test("T0149");
        test("T0148");
        test("T0147");
        test("T0146");
        test("T0145");
        test("T0144");
        test("T0143");
        test("T0142");
        test("T0141");
        test("T0140");
        test("T0139");
        test("T0138");
        test("T0137");
        test("T0136");
        test("T0135");
        test("T0134");
        test("T0133");
        test("T0132");
        test("T0131");
        test("T0130");
        test("T0129");
        test("T0128");
        test("T0127");
        test("T0126");
        test("T0125");
        test("T0124");
        test("T0123");
        test("T0122");
        test("T0121");
        test("T0120");
        test("T0119");
        test("T0118");
        test("T0117");
        test("T0116");
        test("T0115");
        test("T0114");
        test("T0113");
        test("T0112");
        test("T0111");
        test("T0110");
        test("T0109");
        test("T0108");
        test("T0107");
        test("T0106");
        test("T0105");
        test("T0104");
        test("T0103");
        test("T0102");
        test("T0101");
        test("T0100");
        test("T0099");
        test("T0098");
        test("T0097");
        test("T0096");
        test("T0095");
        test("T0094");
        test("T0093");
        test("T0092");
        test("T0091");
        test("T0090");
        test("T0089");
        test("T0088");
        test("T0087");
        test("T0086");
        test("T0085");
        test("T0084");
        test("T0083");
        test("T0082");
        test("T0081");
        test("T0080");
        test("T0079");
        test("T0078");
        test("T0077");
        test("T0076");
        test("T0075");
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
        test("T0051");
        test("T0050");
        test("T0049");
        test("T0046");
        test("T0044");
        test("T0043");
        test("T0041");
        test("T0040");
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
        test("T0014");
        test("T0013");
        test("T0012");
        test("T0011");
        test("T0010");
        test("T0008");
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

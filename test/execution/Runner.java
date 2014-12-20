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



        test("T0000");




//        test("T0284");



        test("T0389");
        test("T0388");
        test("T0387");
        test("T0386");
        test("T0385");
        test("T0384");
        test("T0383");
        test("T0382");
        test("T0381");
        test("T0380");
        test("T0379");
        test("T0378");
        test("T0377");
        test("T0376");
        test("T0375");
        test("T0374");
        test("T0373");
        test("T0372");
        test("T0371");
        test("T0370");
        test("T0369");
        test("T0368");
        test("T0367");
        test("T0366");
        test("T0365");
        test("T0364");
        test("T0363");
        test("T0362");
        test("T0361");
        test("T0360");
        test("T0359");
        test("T0358");
        test("T0357");
        test("T0356");
        test("T0355");
        test("T0354");
        test("T0353");
        test("T0352");
        test("T0351");
        test("T0350");
        test("T0349");
        test("T0348");
        test("T0347");
        test("T0346");
        test("T0345");
        test("T0344");
        test("T0343");
        test("T0342");
        test("T0341");
        test("T0340");
        test("T0339");
        test("T0338");
        test("T0337");
        test("T0336");
        test("T0335");
        test("T0334");
        // test("T0333");
        test("T0332");
        test("T0331");
        test("T0330");
        test("T0329");
        test("T0328");
        test("T0327");
        test("T0326");
        test("T0325");
        test("T0324");
        test("T0323");
        test("T0322");
        test("T0321");
        test("T0320");
        test("T0319");
        test("T0318");
        test("T0317");
        test("T0316");
        test("T0315");
        test("T0314");
        test("T0313");
        test("T0312");
        test("T0311");
        test("T0310");
        test("T0309");
        test("T0308");
        test("T0307");
        test("T0306");
        test("T0305");
        test("T0304");
        test("T0303");
        test("T0302");
        test("T0301");
        test("T0300");
        test("T0299");
        test("T0298");
        test("T0297");
        test("T0296");
        test("T0295");
        test("T0294");
        test("T0293");
        test("T0292");
        test("T0291");
        test("T0290");
        test("T0287");
        test("T0286");
        test("T0285");
        test("T0284");
        test("T0283");
        test("T0282");
        test("T0280");
        test("T0279");
        test("T0278");
        test("T0277");
        test("T0276");
        test("T0275");
        test("T0274");
        test("T0273");
        test("T0272");
        test("T0271");
        test("T0270");
        test("T0269");
        test("T0268");
        test("T0267");
        test("T0266");
        test("T0265");
        test("T0264");
        test("T0263");
        test("T0262");
        test("T0261");
        test("T0260");
        test("T0259");
        test("T0258");
        test("T0257");
        test("T0256");
        test("T0255");
        test("T0254");
        test("T0253");
        test("T0252");
        test("T0251");
        test("T0250");
        test("T0249");
        test("T0248");
        test("T0247");
        test("T0246");
        test("T0245");
        test("T0244");
        test("T0243");
        test("T0242");
        test("T0241");
        test("T0240");
        test("T0239");
        test("T0238");
        test("T0237");
        test("T0236");
        test("T0235");
        test("T0234");
        test("T0233");
        test("T0232");
        test("T0231");
        test("T0230");
        test("T0229");
        test("T0228");
        test("T0227");
        test("T0226");
        test("T0225");
        test("T0224");
        test("T0223");
        test("T0222");
        test("T0221");
        test("T0220");
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
        test("T0156");
        test("T0155");
        test("T0154");
        test("T0153");
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
        test("T0134");
        test("T0133");
        test("T0132");
        test("T0130");
        test("T0129");
        test("T0128");
        test("T0127");
        test("T0126");
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
        test("T0078");
        test("T0077");
        test("T0076");
        test("T0075");
        test("T0073");
        test("T0072");
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

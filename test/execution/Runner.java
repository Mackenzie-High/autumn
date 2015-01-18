package execution;

import autumn.lang.compiler.Autumn;
import autumn.lang.compiler.CompiledProgram;
import autumn.lang.exceptions.AssertionFailedException;
import com.google.common.io.Resources;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import org.junit.Assert;
import org.junit.Test;

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



//        test("T0000");




//        test("T0284");



//        test("T0255"); // covariant elements



        test("T0724");
        test("T0723");
        test("T0722");
        test("T0721");
        test("T0720");
        test("T0719");
        test("T0718");
        test("T0717");
        test("T0716");
        test("T0715");
        test("T0714");
        test("T0713");
        test("T0712");
        test("T0711");
        test("T0710");
        test("T0709");
        test("T0708");
        test("T0707");
        test("T0706");
        test("T0705");
        test("T0704");
        test("T0703");
        test("T0702");
        test("T0701");
        test("T0700");
        test("T0699");
        test("T0698");
        test("T0697");
        test("T0696");
        test("T0695");
        test("T0693");
        test("T0692");
        test("T0691");
        test("T0690");
        test("T0689");
        test("T0688");
        test("T0687");
        test("T0686");
        test("T0685");
        test("T0684");
        test("T0683");
        test("T0682");
        test("T0681");
        test("T0680");
        test("T0679");
        test("T0678");
        test("T0677");
        test("T0676");
        test("T0675");
        test("T0674");
        test("T0673");
        test("T0672");
        test("T0670");
        test("T0669");
        test("T0668");
        test("T0667");
        test("T0666");
        test("T0665");
        test("T0664");
        test("T0663");
        test("T0662");
        test("T0656");
        test("T0655");
        test("T0654");
        test("T0653");
        test("T0652");
        test("T0651");
        test("T0650");
        test("T0649");
        test("T0648");
        test("T0647");
        test("T0646");
        test("T0645");
        test("T0644");
        test("T0643");
        test("T0642");
        test("T0641");
        test("T0640");
        test("T0639");
        test("T0638");
        test("T0637");
        test("T0636");
        test("T0635");
        test("T0634");
        test("T0633");
        test("T0632");
        test("T0631");
        test("T0630");
        test("T0629");
        test("T0628");
        test("T0627");
        test("T0626");
        test("T0625");
        test("T0624");
        test("T0623");
        test("T0622");
        test("T0621");
        test("T0620");
        test("T0619");
        test("T0612");
        test("T0611");
        test("T0610");
        test("T0609");
        test("T0608");
        test("T0607");
        test("T0606");
        test("T0605");
        test("T0604");
        test("T0603");
        test("T0602");
        test("T0601");
        test("T0600");
        test("T0599");
        test("T0598");
        test("T0597");
        test("T0596");
        test("T0595");
        test("T0594");
        test("T0593");
        test("T0592");
        test("T0591");
        test("T0590");
        test("T0589");
        test("T0588");
        test("T0587");
        test("T0586");
        test("T0585");
        test("T0584");
        test("T0583");
        test("T0582");
        test("T0581");
        test("T0580");
        test("T0579");
        test("T0578");
        test("T0577");
        test("T0576");
        test("T0575");
        test("T0574");
        test("T0573");
        test("T0572");
        test("T0571");
        test("T0570");
        test("T0569");
        test("T0568");
        test("T0567");
        test("T0566");
        test("T0565");
        test("T0564");
        test("T0563");
        test("T0562");
        test("T0561");
        test("T0560");
        test("T0559");
        test("T0558");
        test("T0557");
        test("T0556");
        test("T0555");
        test("T0554");
        test("T0553");
        test("T0552");
        test("T0551");
        test("T0550");
        test("T0549");
        test("T0548");
        test("T0547");
        test("T0546");
        test("T0545");
        test("T0544");
        test("T0543");
        test("T0542");
        test("T0541");
        test("T0540");
        test("T0539");
        test("T0538");
        test("T0537");
        test("T0536");
        test("T0535");
        test("T0534");
        test("T0533");
        test("T0532");
        test("T0531");
        test("T0530");
        test("T0529");
        test("T0528");
        test("T0527");
        test("T0526");
        test("T0523");
        test("T0522");
        test("T0521");
        test("T0520");
        test("T0519");
        test("T0518");
        test("T0517");
        test("T0516");
        test("T0513");
        test("T0512");
        test("T0511");
        test("T0510");
        test("T0509");
        test("T0508");
        test("T0507");
        test("T0506");
        test("T0505");
        test("T0504");
        test("T0503");
        test("T0502");
        test("T0501");
        test("T0500");
        test("T0499");
        test("T0498");
        test("T0497");
        test("T0496");
        test("T0495");
        test("T0494");
        test("T0493");
        test("T0492");
        test("T0491");
        test("T0490");
        test("T0489");
        test("T0488");
        test("T0487");
        test("T0486");
        test("T0485");
        test("T0484");
        test("T0483");
        test("T0482");
        test("T0481");
        test("T0480");
        test("T0479");
        test("T0478");
        test("T0477");
        test("T0476");
        test("T0475");
        test("T0474");
        test("T0473");
        test("T0472");
        test("T0471");
        test("T0470");
        test("T0469");
        test("T0468");
        test("T0467");
        test("T0466");
        test("T0465");
        test("T0464");
        test("T0463");
        test("T0462");
        test("T0460");
        test("T0459");
        test("T0458");
        test("T0457");
        test("T0456");
        test("T0455");
        test("T0454");
        test("T0453");
        test("T0452");
        test("T0450");
        test("T0449");
        test("T0448");
        test("T0447");
        test("T0446");
        test("T0445");
        test("T0444");
        test("T0443");
        test("T0442");
        test("T0441");
        test("T0440");
        test("T0439");
        test("T0438");
        test("T0437");
        test("T0436");
        test("T0435");
        test("T0434");
        test("T0433");
        test("T0430");
        test("T0429");
        test("T0428");
        test("T0427");
        test("T0426");
        test("T0425");
        test("T0424");
        test("T0423");
        test("T0422");
        test("T0421");
        test("T0419");
        test("T0418");
        test("T0417");
        test("T0416");
        test("T0415");
        test("T0414");
        test("T0413");
        test("T0412");
        test("T0411");
        test("T0410");
        test("T0409");
        test("T0408");
        test("T0407");
        test("T0406");
        test("T0405");
        test("T0404");
        test("T0403");
        test("T0402");
        test("T0401");
        test("T0400");
        test("T0399");
        test("T0398");
        test("T0397");
        test("T0396");
        test("T0395");
        test("T0394");
        test("T0393");
        test("T0392");
        test("T0391");
        test("T0390");
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
        test("T0331");
        test("T0330");
        test("T0329");
        test("T0328");
        test("T0327");
        test("T0326");
        test("T0325");
        test("T0324");
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
        test("T0261");
        test("T0260");
        test("T0259");
        test("T0258");
        test("T0257");
        test("T0256");
        test("T0255");
        test("T0254");
        test("T0253");
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
        test("T0134");
        test("T0132");
        test("T0128");
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

    /**
     * This method allows NetBeans to automatically run the tests herein.
     */
    @Test
    public void main()
    {
        /**
         * Perform the tests.
         */
        main(null);

        /**
         * All of the tests should have passed.
         */
        Assert.assertEquals(0, failed_count);
    }
}

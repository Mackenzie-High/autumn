package typechecks;

import autumn.lang.compiler.Autumn;
import autumn.lang.compiler.AutumnParser;
import autumn.lang.compiler.ast.nodes.Module;
import autumn.lang.compiler.errors.BasicErrorReporter;
import autumn.lang.compiler.errors.ErrorCode;
import static autumn.lang.compiler.errors.ErrorCode.*;
import autumn.util.Files;
import com.google.common.collect.ImmutableSet;
import com.google.common.io.Resources;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Set;

public final class Runner
{
    private static final String STDOUT = "/media/disk/Code/EclipseProjects/AutumnSpecification/autumn/out/typechecks/";

    private static final boolean FAILED = false;

    private static final boolean PASSED = true;

    private static int failed_count;

    private static Set<ErrorCode> actual;

    /**
     * This method runs tests to determine whether the language's is type-checks work.
     * The results of the tests will be printed to STDOUT.
     *
     * @param args are ignored.
     */
    public static void main(final String[] args)
            throws IOException
    {
        failed_count = 0;





        test("T0228", INACCURATE_NUMERIC_LITERAL);
        test("T0227", INACCURATE_NUMERIC_LITERAL);
        test("T0226", INACCURATE_NUMERIC_LITERAL);
        test("T0225", INACCURATE_NUMERIC_LITERAL);
        test("T0224", INACCURATE_NUMERIC_LITERAL);
        test("T0223", INACCURATE_NUMERIC_LITERAL);
        test("T0222", INACCURATE_NUMERIC_LITERAL);
        test("T0221", INACCURATE_NUMERIC_LITERAL);
        //test("T0220", NO_SUCH_METHOD);
        test("T0219", EXPECTED_MODULE_TYPE);
        test("T0218", INACCESSIBLE_TYPE);
        test("T0217", NO_SUCH_TYPE);
        test("T0216", EXPECTED_DEFINED_FUNCTOR_TYPE);
        test("T0215", INACCESSIBLE_TYPE);
        test("T0214", NO_SUCH_TYPE);
        test("T0213", DUPLICATE_VARIABLE);
        //test("T0212", DUPLICATE_VARIABLE);
        test("T0211", WRONG_TYPE);
        test("T0210", WRONG_TYPE);
        test("T0209", EXPECTED_DEFINED_FUNCTOR_TYPE);
        test("T0208", INACCESSIBLE_TYPE);
        test("T0207", NO_SUCH_TYPE);
        test("T0206", DUPLICATE_VARIABLE);
        test("T0205", COVARIANCE_VIOLATION);
        test("T0204", EXPECTED_VARIABLE_TYPE);
        test("T0203", INACCESSIBLE_TYPE);
        test("T0202", NO_SUCH_TYPE);
        test("T0201", DUPLICATE_ELEMENT);
        test("T0200", DUPLICATE_SUPERTYPE);
        test("T0199", EXPECTED_DESIGN_TYPE);
        test("T0198", EXPECTED_INTERFACE_TYPE);
        test("T0197", INACCESSIBLE_TYPE);
        test("T0196", NO_SUCH_TYPE);
        test("T0195", EXPECTED_VARIABLE_TYPE);
        test("T0194", INACCESSIBLE_TYPE);
        test("T0193", NO_SUCH_TYPE);
        test("T0192", EXPECTED_DESIGN_TYPE);
        test("T0191", EXPECTED_INTERFACE_TYPE);
        test("T0189", WRONG_TYPE);
        test("T0187", EXPECTED_VOID);
        test("T0186", INACCESSIBLE_TYPE);
        test("T0185", NO_SUCH_TYPE);
        test("T0184", EXPECTED_VARIABLE_TYPE);
        test("T0183", INACCESSIBLE_TYPE);
        test("T0182", NO_SUCH_TYPE);
        test("T0181", EXPECTED_INTERFACE_TYPE);
        test("T0180", EXPECTED_DESIGN_TYPE);
        test("T0179", COVARIANCE_VIOLATION);
        test("T0178", DUPLICATE_SUPERTYPE);
        test("T0177", EXPECTED_VARIABLE_TYPE);
        test("T0176", INACCESSIBLE_TYPE);
        test("T0175", NO_SUCH_TYPE);
        test("T0174", DUPLICATE_ELEMENT);
        test("T0173", EXPECTED_INTERFACE_TYPE);
        test("T0172", EXPECTED_INTERFACE_TYPE);
        test("T0171", INACCESSIBLE_TYPE);
        test("T0170", NO_SUCH_TYPE);
        test("T0169", DUPLICATE_TYPE);
        test("T0168", DUPLICATE_TYPE);
        test("T0163", CIRCULAR_INHERITANCE);
        test("T0162", CIRCULAR_INHERITANCE);
        test("T0161", INACCESSIBLE_TYPE);
        test("T0160", NO_SUCH_TYPE);
        test("T0159", INACCESSIBLE_TYPE);
        test("T0158", NO_SUCH_TYPE);
        test("T0157", DUPLICATE_ANNOTATION);
        test("T0156", DUPLICATE_ANNOTATION);
        test("T0155", DUPLICATE_ANNOTATION);
        test("T0154", DUPLICATE_ANNOTATION);
        test("T0153", DUPLICATE_ANNOTATION);
        test("T0152", DUPLICATE_ANNOTATION);
        test("T0151", DUPLICATE_ANNOTATION);
        test("T0150", DUPLICATE_ANNOTATION);
        test("T0149", CIRCULAR_INHERITANCE);
        test("T0148", INACCESSIBLE_TYPE);
        test("T0147", NO_SUCH_TYPE);
        test("T0146", EXPECTED_VARIABLE_TYPE);
        test("T0145", INACCESSIBLE_TYPE);
        test("T0144", NO_SUCH_TYPE);
        test("T0143", EXPECTED_DEFINED_FUNCTOR_TYPE);
        test("T0142", EXPECTED_CLASS_TYPE);
        test("T0141", INACCESSIBLE_TYPE);
        test("T0140", NO_SUCH_TYPE);
        test("T0139", DUPLICATE_TYPE);
        test("T0138", EXPECTED_CLASS_TYPE);
        test("T0137", EXPECTED_CONDITION);
        test("T0136", VARIABLE_OUTSIDE_OF_SCOPE);
        test("T0135", NO_SUCH_VARIABLE);
        test("T0134", EXPECTED_ITERABLE);
        test("T0133", EXPECTED_REFERENCE_TYPE);
        test("T0132", INACCESSIBLE_TYPE);
        test("T0131", NO_SUCH_TYPE);
        test("T0130", DUPLICATE_VARIABLE);
        test("T0129", VALUE_REQUIRED);
        test("T0128", NO_SUCH_LABEL);
        test("T0127", NO_SUCH_LABEL);
        test("T0126", EXPECTED_INTEGER);
        test("T0125", DUPLICATE_ELEMENT);
        test("T0124", DUPLICATE_SUPERTYPE);
        test("T0123", INACCESSIBLE_TYPE);
        test("T0122", NO_SUCH_TYPE);
        test("T0121", DUPLICATE_TYPE);
        test("T0120", EXPECTED_CONDITION);
        test("T0119", EXPECTED_CONDITION);
        test("T0118", EXPECTED_CONDITION);
        test("T0117", EXPECTED_CONDITION);
        test("T0116", VALUE_REQUIRED);
        test("T0115", VALUE_REQUIRED);
        test("T0114", INCOMPATIBLE_OPERANDS);
        test("T0113", EXPECTED_REFERENCE_TYPE);
        test("T0112", EXPECTED_CONDITION);
        test("T0108", NON_VIABLE_INSTANCEOF);
        test("T0107", NO_SUCH_TYPE);
        test("T0106", INACCESSIBLE_TYPE);
        test("T0105", EXPECTED_DECLARED_TYPE);
        test("T0104", EXPECTED_DECLARED_TYPE);
        test("T0103", NO_SUCH_FIELD);
        test("T0102", EXPECTED_DECLARED_TYPE);
        test("T0101", IMPOSSIBLE_ASSIGNMENT);
        test("T0100", ASSIGNMENT_TO_READONLY);
        test("T0099", NO_SUCH_FIELD);
        test("T0098", EXPECTED_DECLARED_TYPE);
        test("T0097", ASSIGNMENT_TO_READONLY);
        test("T0096", NO_SUCH_FIELD);
        test("T0095", EXPECTED_DECLARED_TYPE);
        test("T0094", INACCESSIBLE_TYPE);
        test("T0093", NO_SUCH_TYPE);
        test("T0092", IMPOSSIBLE_ASSIGNMENT);
        test("T0091", NO_SUCH_FIELD);
        test("T0090", EXPECTED_DECLARED_TYPE);
        test("T0089", INACCESSIBLE_TYPE);
        test("T0088", NO_SUCH_TYPE);
        test("T0087", NO_SUCH_METHOD);
        test("T0086", EXPECTED_DECLARED_TYPE);
        test("T0085", NO_SUCH_METHOD);
        test("T0084", EXPECTED_DECLARED_TYPE);
        test("T0083", INACCESSIBLE_TYPE);
        test("T0082", NO_SUCH_TYPE);
        test("T0081", VARIABLE_OUTSIDE_OF_SCOPE);
        test("T0080", NO_SUCH_VARIABLE);
        test("T0079", INACCESSIBLE_TYPE);
        test("T0078", NO_SUCH_TYPE);
        test("T0077", MALFORMED_STRING_LITERAL);
        test("T0076", VARIABLE_OUTSIDE_OF_SCOPE);
        test("T0075", VARIABLE_OUTSIDE_OF_SCOPE);
        test("T0074", REDO_OUTSIDE_OF_LOOP);
        test("T0073", CONTINUE_OUTSIDE_OF_LOOP);
        test("T0072", INACCESSIBLE_TYPE);
        test("T0071", VARIABLE_OUTSIDE_OF_SCOPE);
        test("T0070", VARIABLE_OUTSIDE_OF_SCOPE);
        test("T0069", NO_SUCH_VARIABLE);
        test("T0068", VARIABLE_OUTSIDE_OF_SCOPE);
        test("T0067", NO_SUCH_VARIABLE);
        test("T0066", NO_SUCH_CONSTRUCTOR);
        test("T0065", EXPECTED_CLASS_TYPE);
        test("T0064", INACCESSIBLE_TYPE);
        test("T0063", NO_SUCH_TYPE);
        test("T0062", REDO_OUTSIDE_OF_LOOP);
        test("T0061", CONTINUE_OUTSIDE_OF_LOOP);
        test("T0060", BREAK_OUTSIDE_OF_LOOP);
        test("T0059", EXPECTED_CONDITION);
        test("T0058", EXPECTED_INTEGER);
        test("T0057", EXPECTED_INTEGER);
        test("T0056", DUPLICATE_VARIABLE);
        test("T0055", EXPECTED_ITERABLE);
        test("T0054", EXPECTED_REFERENCE_TYPE);
        test("T0053", INACCESSIBLE_TYPE);
        test("T0052", NO_SUCH_TYPE);
        test("T0051", DUPLICATE_VARIABLE);
        test("T0050", DUPLICATE_CONSTANT);
        test("T0049", DUPLICATE_TYPE);
        test("T0046", CIRCULAR_INHERITANCE);
        test("T0045", CIRCULAR_INHERITANCE);
        test("T0044", INACCESSIBLE_TYPE);
        test("T0043", NO_SUCH_TYPE);
        test("T0042", EXPECTED_THROWABLE);
        test("T0041", DUPLICATE_TYPE);
        test("T0039", DUPLICATE_TYPE);

        //
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
        test("T0011", DUPLICATE_VARIABLE);
        test("T0012", EXPECTED_THROWABLE);
        test("T0013", DUPLICATE_EXCEPTION_HANDLER);
        test("T0014", NO_SUCH_TYPE);
        test("T0015", DUPLICATE_VARIABLE);
        test("T0016", DUPLICATE_VARIABLE);
        test("T0017", NO_SUCH_VARIABLE);
        test("T0019", EXPECTED_CONDITION);
        test("T0020", DUPLICATE_LABEL);
        test("T0021", NO_SUCH_LABEL);
        test("T0022", NO_SUCH_BINARY_OPERATOR);
        test("T0023", NO_SUCH_UNARY_OPERATOR);
        test("T0024", EXPECTED_REFERENCE_TYPE);
        test("T0025", EXPECTED_REFERENCE_TYPE);
        test("T0026", EXPECTED_REFERENCE_TYPE);
        test("T0027", INCOMPATIBLE_OPERANDS);
        test("T0028", EXPECTED_CONDITION);
        test("T0029", EXPECTED_CONDITION);
        test("T0030", EXPECTED_CONDITION);
        test("T0031", EXPECTED_CONDITION);
        test("T0032", EXPECTED_REFERENCE_TYPE);
        test("T0033", EXPECTED_REFERENCE_TYPE);
        test("T0034", IMPOSSIBLE_CONVERSION);
        test("T0035", IMPOSSIBLE_CONVERSION);
        test("T0036", VALUE_REQUIRED);
        test("T0037", EXPECTED_CONDITION, NO_SUCH_VARIABLE);

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
            throws IOException
    {
        for (int i = 0; i < 10; i++)
        {
            if (singleTest(test, errors) == FAILED)
            {
                System.out.println("Test Failed: " + test + "!!!!! - " + actual);
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
            throws IOException
    {
        final String path = "/typechecks/" + test;

        final File stdout = new File(STDOUT + test + ".stdout");

        final FileOutputStream fos = new FileOutputStream(stdout);

        final BufferedOutputStream bos = new BufferedOutputStream(fos);

        try
        {

            final URL url = Resources.getResource(Runner.class, path);

            final String code = Resources.toString(url, Charset.defaultCharset());

            Files.writeText(new File(STDOUT + test + ".code"), code);

            final PrintStream stream = new PrintStream(bos);

            final BasicErrorReporter reporter = new BasicErrorReporter(stream);

            final AutumnParser parser = new AutumnParser(new BasicErrorReporter());

            final Module module = parser.parse(code, new File(path));

            if (module == null)
            {
                return FAILED;
            }

            final Autumn cmp = new Autumn();

            cmp.setErrorReporter(reporter);

            cmp.src(module);

            cmp.compile();

            final Set<ErrorCode> expected = ImmutableSet.copyOf(Arrays.asList(errors));

            actual = ImmutableSet.copyOf(reporter.codes());

            return expected.equals(actual) ? PASSED : FAILED;
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            ex.printStackTrace(System.out);
            return FAILED;
        }
        finally
        {
            bos.close();
        }
    }
}

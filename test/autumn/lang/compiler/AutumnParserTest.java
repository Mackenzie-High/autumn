package autumn.lang.compiler;

import autumn.lang.compiler.ast.nodes.Module;
import autumn.lang.compiler.errors.BasicErrorReporter;
import autumn.lang.compiler.errors.IErrorReporter;
import com.google.common.io.Resources;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.nio.charset.Charset;
import static org.junit.Assert.*;
import org.junit.Test;

public final class AutumnParserTest
{
    /**
     * Test: 20130820015010053094
     *
     * <p>
     * Method:
     * <code>parse(String)</code>
     * </p>
     *
     * <p>
     * Case: Success
     * </p>
     */
    @Test
    public void test20130820015010053094()
            throws IOException
    {
        System.out.println("Test: 20130820015010053094");

        final URL input_path = Resources.getResource("autumn/lang/compiler/input.txt");

        final String input = Resources.toString(input_path, Charset.defaultCharset());

        final URL output_path = Resources.getResource("autumn/lang/compiler/output.txt");

        final String output = Resources.toString(output_path, Charset.defaultCharset()).trim();

        final IErrorReporter reporter = new BasicErrorReporter();

        final AutumnParser parser = new AutumnParser(reporter);

        final Module module = parser.parse(input, new File(getClass().toString()));

        final StringBuilder pretty = new StringBuilder();

        final PrintStream stream = AutumnPrettyPrinter.createStreamASCII(pretty);

        final AutumnPrettyPrinter printer = new AutumnPrettyPrinter(stream);

        assertTrue(printer.stream() == stream);

        printer.print(module);

        final String results = pretty.toString().trim();

        assertEquals(output, results);

        assertTrue(AutumnPrettyPrinter.nodesOf(module).size() > 250);

        assertTrue(parser.parse(results, new File(getClass().toString())) != null);
    }
}

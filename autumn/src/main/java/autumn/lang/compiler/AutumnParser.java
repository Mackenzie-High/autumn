package autumn.lang.compiler;

import autumn.lang.compiler.ast.nodes.Module;
import autumn.lang.compiler.errors.IErrorReporter;
import com.google.common.base.Preconditions;
import high.mackenzie.autumn.lang.compiler.parser.AstBuilder;
import high.mackenzie.autumn.lang.compiler.parser.Parser;
import high.mackenzie.autumn.lang.compiler.parser.Utils;
import high.mackenzie.autumn.resources.Finished;
import com.mackenziehigh.snowflake.LinesAndColumns;
import com.mackenziehigh.snowflake.NewlineStyles;
import com.mackenziehigh.snowflake.ParserOutput;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * An instance of this class is a parser that can parse an Autumn module.
 *
 * @author Mackenzie High
 */
@Finished("2014/08/19")
public final class AutumnParser
{
    /**
     * This object is used to report syntax-errors.
     */
    private final IErrorReporter reporter;

    /**
     * Constructor.
     *
     * @param reporter is the error-reporter to use in order to report any syntax errors.
     * @throws NullPointerException if reporter is null.
     */
    public AutumnParser(final IErrorReporter reporter)
    {
        Preconditions.checkNotNull(reporter);

        this.reporter = reporter;
    }

    /**
     * This method parses a string of Autumn source code that represents a single module.
     *
     * <p>
     * If a syntax-error is found, then the error-reporter will be used to report the error.
     * </p>
     *
     * @param code is the source-code itself.
     * @param file denotes the location of the module, if error reporting occurs.
     * @throws NullPointerException if code is null.
     * @throws NullPointerException if file is null.
     * @return a module created via parsing, or null, if parsing fails.
     */
    public Module parse(final String code,
                        final URL file)
    {
        Preconditions.checkNotNull(code);
        Preconditions.checkNotNull(file);

        // Callers should not be using multiple threads to invoke this parser.
        // However, this will provide some extra safety, just in case.
        // Some of the code that is used to create an AST is not thread-safe.
        synchronized (AutumnParser.class)
        {
            // The source file will be attached to each AST node.
            Utils.source_file = file;

            // Create the real parser.
            final Parser parser = new Parser();

            // Parse the source-code.
            final ParserOutput output = parser.parse(code);

            // If a syntax-error was detected, then report it.
            if (!output.success())
            {
                // Detect the style of newline used within the code.
                // This varies based on the platform (Windows, Linux, etc) on which the code was created.
                // Code may have been created on different platforms.
                // So, we will guess the newline style based on the souce-code.
                // This method is more reliable than simply using the default newline style.
                final NewlineStyles newline = NewlineStyles.fromGuess(code, NewlineStyles.fromSystem());

                // Compute the line-numbers and column-numbers for each character in the input.
                final LinesAndColumns finder = new LinesAndColumns(code.toCharArray(), newline);

                // Get the line-number of where the syntax-error is located.
                final int line = finder.lineNumbers()[output.lengthOfConsumption()];

                // Get the column-number of where the syntax-error is located.
                final int column = finder.columnNumbers()[output.lengthOfConsumption()];

                // Issue the syntax-error.
                reporter.reportSyntaxError(file, line, column);

                // No Abstract-Syntax-Tree can be returned, because none could be created.
                return null;
            }

            // Convert the parse-tree to an abstract-syntax-tree.
            final Module module = AstBuilder.build(output.parseTree());

            return module;
        }
    }

    /**
     * This method parses a string of Autumn source code that represents a single module.
     *
     * <p>
     * If a syntax-error is found, then the error-reporter will be used to report the error.
     * </p>
     *
     * @param code is the source-code itself.
     * @param file denotes the location of the module, if error reporting occurs.
     * @return a module created via parsing, or null, if parsing fails.
     * @throws NullPointerException if code is null.
     * @throws NullPointerException if file is null.
     * @throws IllegalArgumentException if the file cannot be converted to a URL.
     */
    public Module parse(final String code,
                        final File file)
    {
        Preconditions.checkNotNull(code);
        Preconditions.checkNotNull(file);

        try
        {
            final URL url = file.toURI().toURL();

            final Module module = parse(code, url);

            return module;
        }
        catch (MalformedURLException ex)
        {
            throw new IllegalArgumentException("Bad URL", ex);
        }
    }
}

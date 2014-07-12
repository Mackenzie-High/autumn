package autumn.lang.compiler;

import autumn.lang.compiler.ast.nodes.Module;
import autumn.lang.compiler.errors.IErrorReporter;
import com.google.common.base.Preconditions;
import high.mackenzie.autumn.lang.compiler.parser.AstBuilder;
import high.mackenzie.autumn.lang.compiler.parser.Parser;
import high.mackenzie.autumn.lang.compiler.parser.Utils;
import high.mackenzie.snowflake.LinesAndColumns;
import high.mackenzie.snowflake.NewlineStyles;
import high.mackenzie.snowflake.ParserOutput;
import java.io.File;

/**
 * An instance of this class is a parser that can parse an Autumn module.
 *
 * @author Mackenzie High
 */
public final class AutumnParser
{
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
     * This may be null; however, that is usually not recommended.
     * @return a module created via parsing, or null, if parsing fails.
     */
    public Module parse(final String code,
                        final File file)
    {
        Preconditions.checkNotNull(code);

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
                final NewlineStyles newline = NewlineStyles.fromSystem();

                final LinesAndColumns finder = new LinesAndColumns(code.toCharArray(), newline);

                final int line = finder.lineNumbers()[output.lengthOfConsumption()];

                final int column = finder.columnNumbers()[output.lengthOfConsumption()];

                reporter.reportSyntaxError(file, line, column);

                return null;
            }

            // Convert the parse-tree to an abstract-syntax-tree.
            final Module module = AstBuilder.build(output.parseTree());

            return module;
        }
    }
}

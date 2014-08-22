package high.mackenzie.autumn;

import high.mackenzie.autumn.lang.compiler.args.Parser;
import high.mackenzie.autumn.lang.compiler.args.Visitor;
import high.mackenzie.autumn.resources.Finished;
import high.mackenzie.snowflake.ITreeNode;
import high.mackenzie.snowflake.ParserOutput;

/**
 * This is the entry-point of the compiler program.
 *
 * @author Mackenzie High
 */
@Finished("2014/08/22")
public final class Main
{
    /**
     * Program Entry Point.
     *
     * @param args is the command-line broken into pieces.
     * @throws Exception in order to propagate exceptions thrown within Autumn scripts. .
     */
    public static void main(final String[] args)
            throws Exception
    {
        /**
         * Reassemble the command-line into a single string,
         * for the command-line parser.
         */
        final String input = concatArgs(args);

        /**
         * Parse the command-line.
         */
        final Parser parser = new Parser();
        final ParserOutput output = parser.parse(input);
        final ITreeNode root = output.parseTree();

        /**
         * Execute the commands that were extracted from the command-line.
         */
        final Visitor visitor = new Visitor();

        if (root != null)
        {
            visitor.visit(root);
        }
        else
        {
            /**
             * The command-line was malformed, so all we can really do
             * is print the default help message.
             */
            visitor.printHelp();
        }
    }

    /**
     * This method concatenates a series of argument strings together.
     *
     * @param args are the strings to concatenate.
     * @return the args as a single space separated string.
     */
    private static String concatArgs(String[] args)
    {
        final StringBuilder result = new StringBuilder();

        for (String arg : args)
        {
            result.append(arg);

            result.append(' ');
        }

        return result.toString().trim();
    }
}

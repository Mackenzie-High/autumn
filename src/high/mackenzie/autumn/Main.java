/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn;

import high.mackenzie.autumn.lang.compiler.args.Parser;
import high.mackenzie.autumn.lang.compiler.args.Visitor;
import high.mackenzie.snowflake.ITreeNode;
import high.mackenzie.snowflake.ParserOutput;

/**
 * This is the entry-point of the compiler program.
 *
 * @author Mackenzie High
 */
public final class Main
{
    public static void main(final String[] args)
            throws Exception
    {
        final String input = concatArgs(args);

        final Parser parser = new Parser();

        final ParserOutput output = parser.parse(input);

        final ITreeNode root = output.parseTree();

        final Visitor visitor = new Visitor();

        visitor.visit(root);
    }

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

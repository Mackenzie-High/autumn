package high.mackenzie.autumn.lang.debugger;

import autumn.lang.LocalsMap;
import autumn.lang.debugger.IDebugger;
import high.mackenzie.snowflake.ITreeNode;
import high.mackenzie.snowflake.ParserOutput;
import java.util.Scanner;

/**
 *
 * @author mackenzie
 */
public final class DefaultDebugger
        implements IDebugger
{
    private final Visitor interpreter = new Visitor();

    @Override
    public void debug(final String file,
                      final int line,
                      final int column,
                      final LocalsMap locals)
    {
        interpreter.done = false;

        final Scanner stdin = new Scanner(System.in);

        while (!interpreter.done)
        {
            System.out.print(">>> ");

            final String command = stdin.nextLine();

            final Parser parser = new Parser();

            final ParserOutput output = parser.parse(command);

            if (output.success() == false)
            {
                System.out.println("Error.");
                continue;
            }

            final ITreeNode tree = output.parseTree();

            interpreter.done = false;
            interpreter.locals = locals;

            interpreter.visit(tree);
        }
    }
}

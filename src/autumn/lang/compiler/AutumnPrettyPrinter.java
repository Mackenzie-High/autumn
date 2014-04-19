/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autumn.lang.compiler;

import autumn.lang.compiler.ast.commons.IConstruct;
import autumn.lang.compiler.exceptions.IncompleteNodeException;
import autumn.lang.compiler.exceptions.RepeatedNodeException;
import autumn.lang.compiler.exceptions.UnprintableNodeException;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import high.mackenzie.autumn.lang.compiler.pretty.PrintingVisitor;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

/**
 * An instance of this class can pretty-print an abstract-syntax-tree.
 *
 * <p>
 * Note: The output of this pretty-printer is version-specific.
 * </p>
 *
 * @author Mackenzie High
 */
public final class AutumnPrettyPrinter
{
    private final PrintStream out;

    /**
     * Constructor.
     *
     * <p>
     * Equivalent:
     * <code>AutumnPrettyPrinter(System.out)</code>
     * </p>
     */
    public AutumnPrettyPrinter()
    {
        this(System.out);
    }

    /**
     * Constructor.
     *
     * @param out is the stream to print to.
     */
    public AutumnPrettyPrinter(final PrintStream out)
    {
        Preconditions.checkNotNull(out);

        this.out = out;
    }

    /**
     * This method retrieves the stream that is being printed to.
     *
     * @return the aforedescribed stream.
     */
    public PrintStream stream()
    {
        return out;
    }

    /**
     * This method prints an abstract-syntax-tree node to the underlying stream.
     *
     * @param node is the AST node to print, which may be the root of an entire tree.
     * @throws UnprintableNodeException if the AST contains a node that is unprintable.
     * @throws IncompleteNodeException if the AST is incomplete.
     * @throws RepeatedNodeException if the AST contains the same node more than once.
     */
    public void print(final IConstruct node)
            throws UnprintableNodeException,
                   IncompleteNodeException,
                   RepeatedNodeException
    {
        Preconditions.checkNotNull(node);

        final PrintingVisitor printer = new PrintingVisitor();

        node.accept(printer);

        final String pretty = printer.buildString();

        out.print(pretty);
    }

    /**
     * This method creates a PrintStream that prints to a StringBuilder using ASCII.
     *
     * <p>
     * Note: The returned stream throws an IOException, when non-ASCII encoded text is encountered.
     * </p>
     *
     * <p>
     * Note: The returned stream cannot be used to print null characters (i.e. ASCII(0)).
     * </p>
     *
     * @param builder is the builder to print ASCII encoded characters to.
     * @return the aforedescribed stream.
     */
    public static PrintStream createStreamASCII(final StringBuilder builder)
    {
        final OutputStream stream = new OutputStream()
        {
            @Override
            public void write(final int character)
                    throws IOException
            {
                if (character > 0 && character <= 127)
                {
                    builder.append((char) character);
                }
                else if (character == 0)
                {
                    throw new IOException("The character cannot be the null-terminator.");
                }
                else
                {
                    throw new IOException("The character must be encodable using ASCII.");
                }
            }
        };

        return new PrintStream(stream);
    }

    /**
     * This method linearizes the nodes in a given abstract-syntax-tree.
     *
     * @param node is the root of the AST.
     * @return the aforedescribed immutable list.
     */
    public static List<IConstruct> nodesOf(final IConstruct node)
    {
        Preconditions.checkNotNull(node);

        final PrintingVisitor printer = new PrintingVisitor();

        node.accept(printer);

        final List<IConstruct> list = ImmutableList.copyOf(printer.visited());

        return list;
    }
}

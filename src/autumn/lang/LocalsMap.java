package autumn.lang;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.PrintStream;
import java.util.Collection;
import java.util.List;

/**
 * An instance of this class represents the state of the variables within a scope.
 *
 * <p>
 * An instance of this class is returned by a locals-expression.
 * </p>
 *
 * @author Mackenzie High
 */
public final class LocalsMap
{
    /**
     * This is the path to the source-code file that contains the locals-expression.
     */
    private final String file;

    /**
     * This is the index of the line that contains the locals-expression.
     */
    private final int line;

    /**
     * This is the index of the column that contains the locals-expression.
     */
    private final int column;

    /**
     * These objects represent the local variables themselves.
     */
    private final List<Local> locals;

    /**
     * Sole Constructor.
     *
     * @param file is the path to the file that contains the caller local-expression.
     * @param line is the line-number of the caller locals-expression.
     * @param column is the column-number of the caller locals-expression.
     * @param locals are objects that represent the local variables.
     */
    public LocalsMap(final String file,
                     final int line,
                     final int column,
                     final Iterable<Local> locals)
    {
        Preconditions.checkNotNull(file);
        Preconditions.checkNotNull(locals);

        this.file = file;
        this.line = line;
        this.column = column;
        this.locals = ImmutableList.copyOf(locals);
    }

    public String file()
    {
        return file;
    }

    public int line()
    {
        return line;
    }

    public int column()
    {
        return column;
    }

    public Collection<Local> locals()
    {
        return locals;
    }

    public Local get(final String name)
    {
        Preconditions.checkNotNull(name);

        for (Local local : locals)
        {
            if (name.equals(local.name()))
            {
                return local;
            }
        }

        return null;
    }

    public void print()
    {
        print(System.out);
    }

    public void print(final PrintStream out)
    {
        out.println("Locals");

        out.println("  File: " + file());
        out.println("  Line: " + line());
        out.println("  Column: " + column());

        for (Local local : locals)
        {
            out.println("  Variable: " + local.toString());
        }
    }

    public Class typeOf(final String variable)
    {
        final Local local = get(variable);

        return local == null ? null : local.type();
    }
}

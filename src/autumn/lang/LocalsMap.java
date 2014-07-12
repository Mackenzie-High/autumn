package autumn.lang;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import high.mackenzie.autumn.resources.Finished;
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
@Finished("2014/07/12")
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

    /**
     * This method retrieves the path to the file that contains the caller local-expression.
     *
     * @return the path to the source code file.
     */
    public String file()
    {
        return file;
    }

    /**
     * This method retrieves the line-number of the caller locals-expression.
     *
     * @return the aforedescribed line-number.
     */
    public int line()
    {
        return line;
    }

    /**
     * This method retrieves the column-number of the caller locals-expression.
     *
     * @return the aforedescribed column-number.
     */
    public int column()
    {
        return column;
    }

    /**
     * This method retrieves the objects that represent the local variables..
     *
     * @return the descriptions of the local variables.
     */
    public Collection<Local> locals()
    {
        return locals;
    }

    /**
     * This method retrieves the object representation of a named local variable.
     *
     * @param name is the name of the local variable.
     * @return an object representation of the variable; or null, if the variable does not exist.
     */
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

    /**
     * This method prints this object to standard-out.
     */
    public void print()
    {
        print(System.out);
    }

    /**
     * This method prints this object to a given print-stream.
     *
     * @param out is the given print-stream.
     */
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

    /**
     * This method retrieves the static-type of a variable that is described herein.
     *
     * @param variable is the name of the variable.
     * @return the static-type of the variable; or null, if the variable does not exist.
     */
    public Class typeOf(final String variable)
    {
        final Local local = get(variable);

        return local == null ? null : local.type();
    }
}

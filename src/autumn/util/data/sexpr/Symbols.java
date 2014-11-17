package autumn.util.data.sexpr;

/**
 * This class provides static utility methods related to symbolic-expressions.
 *
 * @author Mackenzie High
 */
public final class Symbols
{
    /**
     * This method creates a new symbolic-atom.
     *
     * @param value is the textual representation of the atom.
     * @return the new symbolic-atom.
     * @throws NullPointerException if value is null.
     */
    public static SymbolicAtom atom(final String value)
    {
        return null;
    }

    /**
     * This method creates a new symbolic-string.
     *
     * @param value is the content of the string.
     * @return the new symbolic-string.
     * @throws NullPointerException if value is null.
     */
    public static SymbolicString string(final String value)
    {
        return null;
    }

    /**
     * This method creates a new symbolic-list.
     *
     * @param elements provides the elements for the new list.
     * @return the new symbolic-list.
     * @throws NullPointerException if elements is null.
     */
    public static SymbolicList list(final Iterable<Symbol> elements)
    {
        return null;
    }

    /**
     * This method converts the textual representation of a symbolic-expression to an object tree.
     *
     * @param input is the textual representation of the symbolic-expression.
     * @return the equivalent representation using symbol objects.
     */
    public static Symbol parse(final String input)
    {
        return null;
    }
}

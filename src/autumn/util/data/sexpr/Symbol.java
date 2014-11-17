package autumn.util.data.sexpr;

/**
 * An instance of this interface is a part of a symbolic-expression.
 *
 * @author Mackenzie High
 */
public interface Symbol
{
    /**
     * This method determines whether this symbol is an atom.
     *
     * @return true, iff this symbol is an atom.
     */
    public boolean isAtom();

    /**
     * This method determines whether this symbol is a list.
     *
     * @return true, iff this symbol is a list.
     */
    public boolean isList();

    /**
     * This method determines whether this symbol is a string.
     *
     * @return true, iff this symbol is a string.
     */
    public boolean isString();

    /**
     * This method converts this symbol to its textual representation.
     *
     * @return
     */
    @Override
    public String toString();
}

package autumn.util.data.sexpr;

import java.util.List;

/**
 * An instance of this interface is a list of symbolic-expressions.
 *
 * @author Mackenzie High
 */
public interface SymbolicList
        extends Symbol,
                List<Symbol>
{
    /**
     * This method determines whether this list is bounded by parentheses.
     *
     * @return true, iff this is a parentheses based list.
     */
    public boolean isParenList();

    /**
     * This method determines whether this list is bounded by curly-brackets.
     *
     * @return true, iff this is a curly-bracket based list.
     */
    public boolean isCurlyList();

    /**
     * This method determines whether this list is bounded by square-brackets.
     *
     * @return true, iff this is a square-bracket based list.
     */
    public boolean isSquareList();

    /**
     * This method retrieves the first element in this list.
     *
     * @return the first element herein.
     * @throws NoSuchElementException if this list is empty.
     */
    public Symbol first();

    /**
     * This method retrieves the last element in this list.
     *
     * @return the last element herein.
     * @throws NoSuchElementException if this list is empty.
     */
    public Symbol last();
}

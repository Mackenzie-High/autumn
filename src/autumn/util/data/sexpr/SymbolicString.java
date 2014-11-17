package autumn.util.data.sexpr;

/**
 * An instance of this interface is a symbol that represents a string.
 *
 * @author Mackenzie High
 */
public interface SymbolicString
        extends Symbol,
                CharSequence
{
    /**
     * This method determines whether this string matches a given regular expression.
     *
     * @param regex is the regular expression.
     * @return true, iff this string matches the regular expression.
     */
    public boolean matches(final String regex);
}

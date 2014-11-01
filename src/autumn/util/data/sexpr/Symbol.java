package autumn.util.data.sexpr;

/**
 * An instance of this interface is a part of a symbolic-expression.
 *
 * @author Mackenzie High
 */
public interface Symbol
{
    public boolean isAtom();

    public boolean isList();

    public boolean isString();
}

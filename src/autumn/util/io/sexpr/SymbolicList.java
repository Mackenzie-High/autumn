package autumn.util.io.sexpr;

import com.google.common.collect.ImmutableList;
import java.util.List;

/**
 * An instance of this class is a list of symbolic-expressions.
 *
 * @author mackenzie
 */
public final class SymbolicList
        implements Symbol
{
    private static enum Types
    {
        PAREN,
        BRACE,
        BRACKET,
    }

    private final Types type;

    private final List<Symbol> elements;

    /**
     * Sole Constructor.
     *
     * @param type is the type of the opening and closing characters.
     * @param elements are the elements in the list.
     */
    private SymbolicList(final Types type,
                         final Iterable<Symbol> elements)
    {
        this.type = type;
        this.elements = ImmutableList.copyOf(elements);
    }

    public SymbolicList asParenList()
    {
        return null;
    }

    public SymbolicList asBracketList()
    {
        return null;
    }

    public SymbolicList asBraceList()
    {
        return null;
    }

    public boolean isParenList()
    {
        return false;
    }

    public boolean isBracketList()
    {
        return false;
    }

    public boolean isBraceList()
    {
        return false;
    }

    public static SymbolicList create(final Iterable<Symbol> elements)
    {
        return new SymbolicList(Types.PAREN, elements);
    }
}

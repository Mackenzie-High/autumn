package autumn.util.io.sexpr;

/**
 *
 * @author mackenzie
 */
public final class SymbolicAtom
        implements Symbol
{
    public SymbolicAtom(final String value)
    {
    }

    public boolean isNumber()
    {
        return false;
    }

    public boolean isWord()
    {
        return false;
    }
}

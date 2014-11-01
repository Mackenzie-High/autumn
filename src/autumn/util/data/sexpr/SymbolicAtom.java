package autumn.util.data.sexpr;

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

    @Override
    public boolean isAtom()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isList()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isString()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

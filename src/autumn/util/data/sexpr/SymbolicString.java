package autumn.util.data.sexpr;

/**
 *
 * @author mackenzie
 */
public class SymbolicString
        implements Symbol
{
    public SymbolicString set(final String value)
    {
        return null;
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

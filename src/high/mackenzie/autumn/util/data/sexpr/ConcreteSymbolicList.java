package high.mackenzie.autumn.util.data.sexpr;

import autumn.util.data.sexpr.Symbol;
import autumn.util.data.sexpr.SymbolicList;
import com.google.common.collect.Lists;
import java.util.AbstractList;
import java.util.Collections;
import java.util.List;

/**
 * This class provides a concrete implementation of the SymbolicList interface.
 *
 * @author Mackenzie High
 */
public class ConcreteSymbolicList
        extends AbstractList<Symbol>
        implements SymbolicList
{
    private final List<Symbol> elements;

    public ConcreteSymbolicList(Iterable<Symbol> elements)
    {
        this.elements = Collections.unmodifiableList(Lists.newArrayList(elements));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAtom()
    {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isList()
    {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isString()
    {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isParenList()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCurlyList()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSquareList()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Symbol first()
    {
        return get(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Symbol last()
    {
        return get(size() - 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Symbol get(final int index)
    {
        return elements.get(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size()
    {
        return elements.size();
    }
}

package high.mackenzie.autumn.util.ds;

import autumn.util.ds.FunctionalList;
import autumn.util.ds.ImmutableList;
import autumn.util.ds.MutableList;
import java.util.AbstractList;

/**
 * This class provides a concrete implementation of the ImmutableList interface.
 *
 * @author Mackenzie High
 */
public final class ConcreteImmutableList<E>
        extends AbstractList<E>
        implements ImmutableList<E>
{
    /**
     * This is the persistent list that this immutable list wraps.
     */
    private FunctionalList<E> list;

    /**
     * Sole Constructor.
     *
     * @param list is the backing list.
     */
    ConcreteImmutableList(final FunctionalList list)
    {
        assert list != null;

        this.list = list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MutableList<E> mutable()
    {
        return list.mutable();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FunctionalList<E> functional()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E get(int index)
    {
        return list.get(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size()
    {
        return list.size();
    }
}

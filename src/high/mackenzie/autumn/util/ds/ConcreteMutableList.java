package high.mackenzie.autumn.util.ds;

import autumn.util.ds.FunctionalList;
import autumn.util.ds.ImmutableList;
import autumn.util.ds.MutableList;
import java.util.AbstractList;

/**
 * An instance of this class is a mutable list based on a persistent list.
 *
 * @author Mackenzie High
 */
public final class ConcreteMutableList<E>
        extends AbstractList<E>
        implements MutableList<E>
{
    /**
     * This is the persistent list that this mutable list wraps.
     */
    private FunctionalList<E> list;

    /**
     * Sole Constructor.
     *
     * @param list is the initial backing list.
     */
    ConcreteMutableList(final FunctionalList list)
    {
        assert list != null;

        this.list = list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableList<E> immutable()
    {
        return list.immutable();
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
    public void add(final int index,
                    final E value)
    {
        list = list.add(index, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E remove(final int index)
    {
        final E result = get(index);

        list = list.remove(index);

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E set(final int index,
                 final E value)
    {
        final E result = get(index);

        list = list.set(index, value);

        return result;
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

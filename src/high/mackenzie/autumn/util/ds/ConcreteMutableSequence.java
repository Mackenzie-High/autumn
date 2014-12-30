package high.mackenzie.autumn.util.ds;

import autumn.util.ds.ImmutableSequence;
import autumn.util.ds.MutableSequence;
import com.google.common.base.Preconditions;

/**
 * An instance of this class is a mutable list based on a persistent list.
 *
 * @author Mackenzie High
 */
public final class ConcreteMutableSequence<E>
        extends AbstractSequence<E>
        implements MutableSequence<E>
{
    /**
     * This is the persistent list that this mutable list wraps.
     */
    private FunctionalList<E> list;

    /**
     * Constructor.
     */
    public ConcreteMutableSequence()
    {
        this.list = new FunctionalList<E>();
    }

    /**
     * Constructor.
     *
     * @param list is the initial backing list.
     * @throws NullPointerException if list is null.
     */
    ConcreteMutableSequence(final FunctionalList<E> list)
    {
        Preconditions.checkNotNull(list);

        this.list = list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableSequence<E> immutable()
    {
        return list.immutable();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(final int index,
                    final E value)
    {
        list = list.insert(index, value);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean offerFirst(final E e)
    {
        addFirst(e);

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean offerLast(final E e)
    {
        addLast(e);

        return true;
    }
}

package high.mackenzie.autumn.util.ds;

import autumn.util.ds.ImmutableSequence;
import autumn.util.ds.MutableSequence;
import com.google.common.base.Preconditions;

/**
 * This class provides a concrete implementation of the ImmutableList interface.
 *
 * @author Mackenzie High
 */
public final class ConcreteImmutableSequence<E>
        extends AbstractSequence<E>
        implements ImmutableSequence<E>
{
    /**
     * This is the persistent list that this immutable list wraps.
     */
    private FunctionalList<E> list;

    /**
     * Constructor.
     */
    public ConcreteImmutableSequence()
    {
        this(new FunctionalList());
    }

    /**
     * Sole Constructor.
     *
     * @param list is the backing list.
     * @throws NullPointerException if list is null.
     */
    ConcreteImmutableSequence(final FunctionalList list)
    {
        Preconditions.checkNotNull(list);

        this.list = list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MutableSequence<E> mutable()
    {
        return list.mutable();
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
        /**
         * The value cannot be added to this sequence,
         * because the sequence is immutable.
         */
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean offerLast(E e)
    {
        /**
         * The value cannot be added to this sequence,
         * because the sequence is immutable.
         */
        return false;
    }
}

package high.mackenzie.autumn.util.ds;

import com.google.common.base.Objects;
import java.util.AbstractList;
import java.util.Deque;
import java.util.Iterator;

/**
 * This class provides a partial implementation for the ImmutableSequence and MutableSequence interfaces.
 *
 * @author Mackenzie High
 */
abstract class AbstractSequence<E>
        extends AbstractList<E>
        implements Deque<E>
{
    /**
     * {@inheritDoc}
     */
    @Override
    public final void addFirst(final E e)
    {
        add(0, e);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void addLast(final E e)
    {
        add(size(), e);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final E removeFirst()
    {
        return remove(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final E removeLast()
    {
        return remove(size() - 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final E pollFirst()
    {
        if (isEmpty())
        {
            return null;
        }
        else
        {
            return removeFirst();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final E pollLast()
    {
        if (isEmpty())
        {
            return null;
        }
        else
        {
            return removeLast();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final E getFirst()
    {
        return get(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final E getLast()
    {
        return get(size() - 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final E peekFirst()
    {
        if (isEmpty())
        {
            return null;
        }
        else
        {
            return getFirst();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final E peekLast()
    {
        if (isEmpty())
        {
            return null;
        }
        else
        {
            return getLast();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean removeFirstOccurrence(final Object o)
    {
        /**
         * Search the sequence, in ascending-order, for the value.
         * If it is found, remove it and return true;
         * otherwise, return simply false.
         */
        final Iterator<E> iter = iterator();

        while (iter.hasNext())
        {
            if (Objects.equal(o, iter.next()))
            {
                iter.remove();

                return true;
            }
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean removeLastOccurrence(final Object o)
    {
        /**
         * Search the sequence, in descending-order, for the value.
         * If it is found, remove it and return true;
         * otherwise, return simply false.
         */
        final Iterator<E> iter = descendingIterator();

        while (iter.hasNext())
        {
            if (Objects.equal(o, iter.next()))
            {
                iter.remove();

                return true;
            }
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean offer(final E e)
    {
        return offer(e);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final E remove()
    {
        return removeFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final E poll()
    {
        return pollFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final E element()
    {
        return getFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final E peek()
    {
        return peekFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void push(final E e)
    {
        addFirst(e);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final E pop()
    {
        return removeFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Iterator<E> descendingIterator()
    {
        // TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

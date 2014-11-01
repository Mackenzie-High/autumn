package high.mackenzie.autumn.util.ds;

import autumn.lang.internals.Operators;
import autumn.util.ds.FunctionalCollection;
import com.google.common.collect.Sets;
import java.util.Set;

/**
 * This class provides a partial implementation of the FunctionalCollection interface.
 *
 * @author Mackenzie High
 */
public abstract class AbstractFunctionalCollection<E>
        implements FunctionalCollection<E>
{
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(final E value)
    {
        for (Object element : this)
        {
            if (Operators.equals(element, value))
            {
                return true;
            }
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean containsAll(final Iterable<? extends E> values)
    {
        final Set<E> set = Sets.newHashSet(values);

        for (Object element : this)
        {
            if (set.contains(element))
            {
                set.remove(element);
            }
        }

        return set.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean containsAny(Iterable<? extends E> values)
    {
        final Set<E> set = Sets.newHashSet(values);

        for (Object element : this)
        {
            if (set.contains(element))
            {
                return true;
            }
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean isEmpty()
    {
        return size() == 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean equals(final Object other)
    {
        return immutable().equals(other);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int hashCode()
    {
        return immutable().hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String toString()
    {
        return immutable().toString();
    }
}

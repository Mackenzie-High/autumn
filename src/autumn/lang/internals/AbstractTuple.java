package autumn.lang.internals;

import autumn.lang.Tuple;
import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.SortedMap;

/**
 * This class provides a partial implementation of the tuple interface.
 *
 * @author Mackenzie High
 */
public abstract class AbstractTuple<T extends AbstractTuple>
        implements Tuple<T>
{
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
    public final int indexOf(Object value)
    {
        return indexOf(value, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int indexOf(Object value,
                             int skip)
    {
        final int size = size();

        int skipped = 0;

        for (int i = 0; i < size; i++)
        {
            if (Objects.equal(value, get(i)))
            {
                if (skipped == skip)
                {
                    return i;
                }
                else
                {
                    ++skipped;
                }
            }
        }

        return -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int lastIndexOf(Object value)
    {
        return lastIndexOf(value, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int lastIndexOf(Object value,
                                 int skip)
    {
        final int size = size();

        int skipped = 0;

        for (int i = size - 1; i >= 0; i--)
        {
            if (Objects.equal(value, get(i)))
            {
                if (skipped == skip)
                {
                    return i;
                }
                else
                {
                    ++skipped;
                }
            }
        }

        return -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean contains(Object value)
    {
        return indexOf(value) >= 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final SortedMap<String, Object> toMap()
    {
        final SortedMap<String, Object> map = Maps.newTreeMap();

        final List<String> keys = keys();

        int i = 0;

        for (String key : keys)
        {
            map.put(key, get(i++));
        }

        return Collections.unmodifiableSortedMap(map);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Tuple<T> copy()
    {
        return isMutable() ? mutableCopy() : readonlyCopy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Iterator<Object> iterator()
    {
        return values().iterator(); // TODO write an iterator class for better performance
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final ListIterator<Object> listIterator()
    {
        throw new UnsupportedOperationException("Not supported yet."); // TODO
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int compareTo(Tuple other)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean equals(Object other)
    {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int hashCode()
    {
        return toMap().hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String toString()
    {
        final StringBuilder tuple = new StringBuilder();

        int i = 0;

        tuple.append('(');

        for (Object value : values())
        {
            tuple.append(value);

            tuple.append(i < size() - 1 ? ", " : "");
        }

        tuple.append(')');

        return tuple.toString();
    }
}

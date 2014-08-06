package autumn.lang.internals;

import autumn.lang.Tuple;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.SortedMap;

/**
 * This class provides a partial implementation of the tuple interface.
 *
 * <p>
 * Subclasses must contain the following methods:
 * <ul>
 * <li>a constructor that takes the elements as its parameters,</li>
 * <li>implementations of the abstract methods defined herein,</li>
 * <li>the instance() static method, which returns an empty tuple,</li>
 * <li>setters and getters for each element,</li>
 * <li>bridge methods for each method herein that returns a Tuple,</li>
 * </ul>
 * </p>
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
    public abstract List<Class> types();

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract List<String> keys();

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract Tuple<T> set(final int index,
                                 final Object value);

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract Object get(final int index);

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract boolean isMutable();

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract Tuple<T> immutableCopy();

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract Tuple<T> mutableCopy();

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<Object> values()
    {
        final int size = size();

        final ArrayList<Object> list = new ArrayList<Object>(size);

        for (int i = 0; i < size; i++)
        {
            list.add(get(i));
        }

        list.trimToSize();

        return Collections.unmodifiableList(list);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Tuple<T> assign(final Tuple<?> other)
    {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Tuple<T> clear()
    {
        return this.isMutable() ? clearThis() : clearCopy();
    }

    /**
     * This method clears the elements of this mutable tuple.
     *
     * @return this object.
     */
    private Tuple<T> clearThis()
    {
        final List<Class> types = types();

        final int size = size();

        for (int i = 0; i < size; i++)
        {
            final Class type = types.get(i);

            if (boolean.class.equals(type))
            {
                set(i, false);
            }
            else if (char.class.equals(type))
            {
                set(i, (char) 0);
            }
            else if (byte.class.equals(type))
            {
                set(i, (byte) 0);
            }
            else if (short.class.equals(type))
            {
                set(i, (short) 0);
            }
            else if (int.class.equals(type))
            {
                set(i, (int) 0);
            }
            else if (long.class.equals(type))
            {
                set(i, (long) 0);
            }
            else if (float.class.equals(type))
            {
                set(i, (float) 0);
            }
            else if (double.class.equals(type))
            {
                set(i, (double) 0);
            }
            else
            {
                set(i, null);
            }
        }

        return this;
    }

    /**
     * This method creates an immutable tuple that is a cleared copy of this tuple.
     *
     * @return a copy of this tuple.
     */
    private Tuple<T> clearCopy()
    {
        // This object is an immutable tuple.
        // Create a mutable copy of this tuple.
        // Clear the mutable copy.
        // Return an immutable copy of the cleared mutable tuple.
        return this.mutableCopy().clear().immutableCopy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Tuple<T> set(final String key,
                              final Object value)
    {
        Preconditions.checkNotNull(key);

        final int index = keys().indexOf(key);

        if (index < 0)
        {
            throw new NoSuchElementException("Key: " + key);
        }
        else
        {
            return set(index, value);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Object get(final String key)
    {
        Preconditions.checkNotNull(key);

        final int index = keys().indexOf(key);

        if (index < 0)
        {
            throw new NoSuchElementException("Key: " + key);
        }
        else
        {
            return get(index);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isImmutable()
    {
        return !isMutable();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size()
    {
        return keys().size();
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
    public final SortedMap<String, Object> toMap()
    {
        final SortedMap<String, Object> map = Maps.newTreeMap();

        final List<String> keys = keys();

        final List<Object> values = Lists.newArrayList(values());

        int i = 0;

        for (String key : keys)
        {
            map.put(key, values.get(i++));
        }

        return Collections.unmodifiableSortedMap(map);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Tuple<T> copy()
    {
        return isMutable() ? mutableCopy() : immutableCopy();
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
    public final int compareTo(final Tuple other)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean equals(final Object other)
    {
        // TODO
        if (other instanceof Tuple)
        {
            return toMap().equals(((Tuple) other).toMap());
        }
        else
        {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int hashCode()
    {
        return toMap().hashCode(); // TODO
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

            tuple.append(i++ < size() - 1 ? ", " : "");
        }

        tuple.append(')');

        return tuple.toString();
    }
}

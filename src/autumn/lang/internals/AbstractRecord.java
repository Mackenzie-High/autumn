package autumn.lang.internals;

import autumn.lang.Record;
import autumn.lang.SpecialMethods;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

/**
 * This class provides a partial implementation of the Record.
 *
 * @author Mackenzie High
 */
public abstract class AbstractRecord
        implements Record
{
    /**
     * {@inheritDoc}
     */
    @Override
    public abstract Collection<String> keys();

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract Collection<Object> values();

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract Map<String, Class> types();

    /**
     * {@inheritDoc}
     */
    @Override
    public Record bind(SpecialMethods methods)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Record clear()
    {
        return this.isMutable() ? clearThis() : clearCopy();
    }

    /**
     * This method clears the elements of this mutable tuple.
     *
     * @return this object.
     */
    private Record clearThis()
    {
        final Map<String, Class> types = types();

        for (String key : keys())
        {
            final Class type = types.get(key);

            if (boolean.class.equals(type))
            {
                set(key, false);
            }
            else if (char.class.equals(type))
            {
                set(key, (char) 0);
            }
            else if (byte.class.equals(type))
            {
                set(key, (byte) 0);
            }
            else if (short.class.equals(type))
            {
                set(key, (short) 0);
            }
            else if (int.class.equals(type))
            {
                set(key, (int) 0);
            }
            else if (long.class.equals(type))
            {
                set(key, (long) 0);
            }
            else if (float.class.equals(type))
            {
                set(key, (float) 0);
            }
            else if (double.class.equals(type))
            {
                set(key, (double) 0);
            }
            else
            {
                set(key, null);
            }
        }

        return this;
    }

    /**
     * This method creates an immutable tuple that is a cleared copy of this tuple.
     *
     * @return a copy of this tuple.
     */
    private Record clearCopy()
    {
        // This object is an immutable tuple.
        // Create a mutable copy of this tuple.
        // Clear the mutable copy.
        // Return an immutable copy of the cleared mutable tuple.
        return this.mutable().clear().immutable();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean isImmutable()
    {
        return !isMutable();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int size()
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
    public final Map<String, Object> toMap()
    {
        final SortedMap<String, Object> map = Maps.newTreeMap();

        final Collection<String> keys = keys();

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
    public Record copy()
    {
        return isMutable() ? mutable() : immutable();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int compareTo(final Record other)
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
        if (other instanceof Record)
        {
            return toMap().equals(((Record) other).toMap());
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
}

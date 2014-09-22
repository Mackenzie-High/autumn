package autumn.lang.internals;

import autumn.lang.SpecialMethods;
import autumn.lang.Tuple;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

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
public abstract class AbstractTuple
        extends AbstractRecord
        implements Tuple
{
    /**
     * {@inheritDoc}
     */
    @Override
    public Tuple bind(SpecialMethods methods)
    {
        return (Tuple) super.bind(methods);
    }

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
    public Tuple clear()
    {
        return (Tuple) super.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tuple copy()
    {
        return (Tuple) super.copy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tuple set(final String key,
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

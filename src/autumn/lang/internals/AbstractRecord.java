package autumn.lang.internals;

import autumn.lang.Record;
import autumn.util.F;
import autumn.util.T;
import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * This class provides a partial implementation of the Record type.
 *
 * <p>
 * A subclass must provide a single constructor.
 * The constructor takes one parameter for each element in the record.
 * The constructor simply places its parameters into the appropriate elements.
 * The new record must be immutable.
 * </p>
 *
 * <p>
 * A subclass needs to implement the following methods:
 * <ul>
 * <li>static method instance() - Returns an immutable empty instance.</li>
 * <li>isStruct()</li>
 * <li>isTuple()</li>
 * <li>keys()</li>
 * <li>types()</li>
 * <li>values()</li>
 * <li>set(int, Object)</li>
 * <li>get(int)</li>
 * </ul>
 * </p>
 *
 * <p>
 * A subclass must provide bridge methods for every method herein that returns a Record.
 * The return-type of the bridge method must be the subclass itself.
 * For example in a subclass T, method copy() : Record should have a bridge method copy() : T.
 * <br>
 * <ul>
 * <li>set(int, Object)</li>
 * <li>copy()</li>
 * </ul>
 * </p>
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
    public abstract List<String> keys();

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract List<Class> types();

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract boolean isStruct();

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract boolean isTuple();

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract Record set(int index,
                               Object value);

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract Object get(int index);

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<Object> values()
    {
        final int size = size();

        final List<Object> values = Lists.newLinkedList();

        for (int i = 0; i < size; i++)
        {
            values.add(get(i));
        }

        final List<Object> result = Collections.unmodifiableList(values);

        return result;
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
    public final int compareTo(final Record other)
    {
        if (other == null)
        {
            return 1; // Null is always less than an object.
        }
        else if (keys().equals(other.keys()) == false)
        {
            throw new UnsupportedOperationException();
        }
        else
        {
            for (int i = 0; i < size(); i++)
            {
                try
                {
                    final Comparable operand1 = (Comparable) this.get(i);
                    final Comparable operand2 = (Comparable) other.get(i);

                    final int relationship = T.compare(operand1, operand2);

                    if (relationship < 0)
                    {
                        return -1;
                    }
                    else if (relationship > 0)
                    {
                        return 1;
                    }
                }
                catch (ClassCastException ex)
                {
                    throw new UnsupportedOperationException(ex);
                }
            }
        }

        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean equals(final Object other)
    {
        if (other == null)
        {
            return false;
        }

        if (this == other)
        {
            return true;
        }

        if (other instanceof Record == false)
        {
            return false;
        }

        final Record record = (Record) other;

        final boolean answer = keys().equals(record.keys()) && values().equals(record.values());

        return answer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int hashCode()
    {
        return keys().hashCode() ^ values().hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Iterator<Object> iterator()
    {
        return values().iterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String toString()
    {
        final String result = F.str(values(), "(", ", ", ")");

        return result;
    }
}

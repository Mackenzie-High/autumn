package autumn.lang.internals;

import autumn.lang.Record;
import autumn.lang.SpecialMethods;
import autumn.lang.TypedFunctor;
import autumn.util.F;
import autumn.util.Functors;
import autumn.util.Strings;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;

/**
 * This class provides a partial implementation of the Record.
 *
 * <p>
 * A subclass must provide a single constructor.
 * The constructor takes one parameter for each element in the record.
 * The constructor simply copies its parameters into the appropriate elements.
 * The new record must be immutable.
 * </p>
 *
 * <p>
 * A subclass needs to implement the following methods:
 * <ul>
 * <li>static method instance() - Returns an immutable empty instance.</li>
 * <li>bind(SpecialMethods)</li>
 * <li>bindings()</li>
 * <li>isStruct()</li>
 * <li>isTuple()</li>
 * <li>isMutable()</li>
 * <li>mutable()</li>
 * <li>immutable()</li>
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
 * <li>bind(SpecialMethods)</li>
 * <li>set(int, Object)</li>
 * <li>copy()</li>
 * <li>immutable()</li>
 * <li>mutable()</li>
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
    public abstract Record bind(final SpecialMethods methods);

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract SpecialMethods bindings();

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
    public abstract List<Object> values();

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract Record immutable();

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract Record mutable();

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
    public abstract boolean isMutable();

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
        if (bindings().getCompareTo() != null)
        {
            return (Integer) Functors.quietlyApply(bindings().getCompareTo(),
                                                   Lists.newArrayList(this, other));
        }
        else if (other == null)
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

                    final int relationship = F.compare(operand1, operand2);

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
        final TypedFunctor method = bindings().getEquals();

        if (method != null)
        {
            final Object self = this;

            return (Boolean) Functors.quietlyApply(method, Lists.newArrayList(self, other));
        }

        if (this == null)
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
        final TypedFunctor method = bindings().getHashCode();

        if (method == null)
        {
            return keys().hashCode() ^ values().hashCode();
        }
        else
        {
            final Object self = this;

            return (Integer) Functors.quietlyApply(method, Lists.newArrayList(self));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Iterator<Object> iterator()
    {
        final TypedFunctor method = bindings().getIterator();

        if (method != null)
        {
            final Object self = this;

            return (Iterator) Functors.quietlyApply(method, Lists.newArrayList(self));
        }
        else
        {
            return values().iterator();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String toString()
    {
        final TypedFunctor method = bindings().getToString();

        if (method != null)
        {
            final Object self = this;

            final List<Object> arguments = Lists.newArrayList(self);

            return "" + Functors.quietlyApply(method, arguments);
        }
        else
        {
            final String result = Strings.str(values(), "(", ", ", ")");

            return result;
        }
    }
}

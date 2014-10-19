package autumn.lang.internals;

import autumn.lang.Record;
import autumn.lang.SpecialMethods;
import autumn.lang.TypedFunctor;
import autumn.util.Functors;
import autumn.util.Records;
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
 * <li>clear()</li>
 * <li>set(int, Object)</li>
 * <li>set(String, Object)</li>
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
        if (bindings().getCompareTo() == null)
        {
            throw new UnsupportedOperationException("compareTo(Record)");
        }
        else
        {
            return (Integer) Functors.quietlyApply(bindings().getCompareTo(),
                                                   Lists.newArrayList(this, other));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean equals(final Object other)
    {
        if (bindings().getEquals() != null)
        {
            return (Boolean) Functors.quietlyApply(bindings().getEquals(),
                                                   Lists.newArrayList(this, other));
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

        final boolean answer = Records.entryMap(this).equals(Records.entryMap(record));

        return answer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int hashCode()
    {
        if (bindings().getHashCode() == null)
        {
            return Records.entryMap(this).hashCode();
        }
        else
        {
            return (Integer) Functors.quietlyApply(bindings().getHashCode(),
                                                   Lists.newArrayList(this));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Iterator<Object> iterator()
    {
        final TypedFunctor method = bindings().getToString();

        if (method != null)
        {
            return (Iterator) Functors.quietlyApply(method, Lists.newArrayList(this));
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
            return Functors.quietlyApply(method, Lists.newArrayList(this)) + "";
        }
        else
        {
            return Strings.str(values(), "(", ", ", ")");
        }
    }
}

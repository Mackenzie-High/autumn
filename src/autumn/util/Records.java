package autumn.util;

import autumn.lang.Record;
import autumn.lang.RecordEntry;
import autumn.lang.SpecialMethods;
import autumn.lang.TypedFunctor;
import autumn.lang.annotations.Infer;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.SortedMap;

/**
 * This class provides static utility methods for working with tuples.
 *
 * @author Mackenzie High
 */
public final class Records
{
    /**
     * This method sets the value of each element to its default value.
     *
     * <p>
     * This method does not affect the special-method bindings.
     * </p>
     *
     * @return this object, if this record is mutable; otherwise, return a modified copy thereof.
     */
    @Infer
    public static <T extends Record> T clear(final T self)
    {
        return self.isMutable() ? clearThis(self) : clearCopy(self);
    }

    /**
     * This method clears the elements of this mutable tuple.
     *
     * @return this object.
     */
    private static <T extends Record> T clearThis(final T self)
    {
        final List<Class> types = self.types();

        for (int i = 0; i < self.size(); i++)
        {
            self.set(i, F.defaultValue(types.get(i)));
        }

        return self;
    }

    /**
     * This method creates an immutable tuple that is a cleared copy of this tuple.
     *
     * @return a copy of this tuple.
     */
    private static <T extends Record> T clearCopy(final T self)
    {
        // This object is an immutable tuple.
        // Create a mutable copy of this tuple.
        // Clear the mutable copy.
        // Return an immutable copy of the cleared mutable tuple.
        return (T) clear(self.mutable()).immutable();
    }

    /**
     * This method creates an object that represents an entry in this record.
     *
     * @param index is the index of the element to assign the value to.
     * @return an object that describes the specified entry.
     * @throws IndexOutOfBoundsException if the index is out-of-bounds.
     */
    public static RecordEntry find(final Record self,
                                   final int index)
    {
        return new RecordEntry()
        {
            private String key = null;

            @Override
            public Record record()
            {
                return self;
            }

            @Override
            public String key()
            {
                if (key == null)
                {
                    key = self.keys().get(index);
                }

                return key;
            }

            @Override
            public Class type()
            {
                return self.types().get(index);
            }

            @Override
            public Object value()
            {
                return self.get(index);
            }

            @Override
            public Record clear()
            {
                return self.set(index, F.defaultValue(type()));
            }

            @Override
            public Record set(Object value)
            {
                return self.set(index, value);
            }

            @Override
            public boolean isMutable()
            {
                return self.isMutable();
            }

            @Override
            public boolean isImmutable()
            {
                return self.isImmutable();
            }

            @Override
            public String toString()
            {
                return "" + value();
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    public static RecordEntry find(final Record self,
                                   final String key)
    {
        Preconditions.checkNotNull(key);

        final int index = self.keys().indexOf(key);

        final RecordEntry result = find(self, index);

        return result;
    }

    /**
     * This method assigns a new value to a specific element.
     *
     * <p>
     * Auto-unboxing will be performed, if necessary.
     * However, the unboxed value will not be auto-widened.
     * </p>
     *
     * <p>
     * This is a linear-time operation in the worst case.
     * </p>
     *
     * @param key is the name of the element to assign the value to.
     * @param value is the new value.
     * @return this object, if this record is mutable; otherwise, return a modified copy thereof.
     * @throws NullPointerException if the key is null.
     * @throws NoSuchElementException if the key does not refer to an actual element.
     * @throws ClassCastException if the object is not of an acceptable type.
     */
    @Infer
    public static <T extends Record> T set(final T self,
                                           final String key,
                                           final Object value)
    {
        Preconditions.checkNotNull(key);

        final int index = self.keys().indexOf(key);

        if (index < 0)
        {
            throw new NoSuchElementException("Key: " + key);
        }

        final Record result = self.set(index, value);

        return (T) result;
    }

    /**
     * This method retrieves the value of a specific element.
     *
     * <p>
     * This is a linear-time operation in the worst case.
     * </p>
     *
     * @param key is the name of the element to retrieve.
     * @return the value of the element.
     * @throws NullPointerException if the key is null.
     * @throws NoSuchElementException if no element is identified by the given key.
     */
    public static Object get(final Record self,
                             final String key)
    {
        Preconditions.checkNotNull(key);

        final int index = self.keys().indexOf(key);

        if (index < 0)
        {
            throw new NoSuchElementException("Key: " + key);
        }

        final Object result = self.get(index);

        return result;
    }

    /**
     * This method redefines the iterator() method.
     *
     * <p>
     * If the handler is null, the method will revert to its default behavior.
     * </p>
     *
     * @param owner is the object whose method will be redefined.
     * @param handler is the new implementation of the method.
     * @return the modified object, if it is mutable; otherwise, return a modified copy thereof.
     * @throws NullPointerException if owner is null.
     */
    @Infer
    public static <T extends Record> T bindIter(final T owner,
                                                final TypedFunctor handler)
    {
        Preconditions.checkNotNull(owner);

        final SpecialMethods methods = owner.bindings().setIterator(handler);

        final T result = (T) owner.bind(methods);

        return result;
    }

    /**
     * This method redefines the compareTo(Object) method.
     *
     * <p>
     * If the handler is null, the method will revert to its default behavior.
     * </p>
     *
     * @param owner is the object whose method will be redefined.
     * @param handler is the new implementation of the method.
     * @return the modified object, if it is mutable; otherwise, return a modified copy thereof.
     * @throws NullPointerException if owner is null.
     */
    @Infer
    public static <T extends Record> T bindCompare(final T owner,
                                                   final TypedFunctor handler)
    {
        Preconditions.checkNotNull(owner);

        final SpecialMethods methods = owner.bindings().setCompareTo(handler);

        final T result = (T) owner.bind(methods);

        return result;
    }

    /**
     * This method redefines the equals(Object) method.
     *
     * <p>
     * If the handler is null, the method will revert to its default behavior.
     * </p>
     *
     * @param owner is the object whose method will be redefined.
     * @param handler is the new implementation of the method.
     * @return the modified object, if it is mutable; otherwise, return a modified copy thereof.
     * @throws NullPointerException if owner is null.
     */
    @Infer
    public static <T extends Record> T bindEquals(final T owner,
                                                  final TypedFunctor handler)
    {
        final SpecialMethods methods = owner.bindings().setEquals(handler);

        final T result = (T) owner.bind(methods);

        return result;
    }

    /**
     * This method redefines the hashCode() method.
     *
     * <p>
     * If the handler is null, the method will revert to its default behavior.
     * </p>
     *
     * @param owner is the object whose method will be redefined.
     * @param handler is the new implementation of the method.
     * @return the modified object, if it is mutable; otherwise, return a modified copy thereof.
     */
    @Infer
    public static <T extends Record> T bindHash(final T owner,
                                                final TypedFunctor handler)
    {
        final SpecialMethods methods = owner.bindings().setHashCode(handler);

        final T result = (T) owner.bind(methods);

        return result;
    }

    /**
     * This method redefines the toString() method.
     *
     * <p>
     * If the handler is null, the method will revert to its default behavior.
     * </p>
     *
     * @param owner is the object whose method will be redefined.
     * @param handler is the new implementation of the method.
     * @return the modified object, if it is mutable; otherwise, return a modified copy thereof.
     */
    @Infer
    public static <T extends Record> T bindStr(final T owner,
                                               final TypedFunctor handler)
    {
        final SpecialMethods methods = owner.bindings().setToString(handler);

        final T result = (T) owner.bind(methods);

        return result;
    }

    /**
     * This method determines whether the set of entries in one record
     * is a subset of the records in another record.
     *
     * @param subtype is the possible subset record.
     * @param supertype is the possible superset record.
     * @return true, if subtype.keys() is a subset of supertype.keys().
     */
    public static boolean isSubset(final Record subtype,
                                   final Record supertype)
    {
        Preconditions.checkNotNull(subtype);
        Preconditions.checkNotNull(supertype);

        final boolean answer = subtype.keys().containsAll(supertype.keys());

        return answer;
    }

    /**
     * This method assigns the writes the entries in a map into another record.
     *
     * @param assignee is the record that will be written into.
     * @param value is the map that contains the entries to write into the assignee.
     * @return the assignee, if it is modifiable; otherwise, return a modified copy thereof.
     * @param NullPointerException if the assignee is null.
     * @param NullPointerException if the value is null.
     */
    @Infer
    public static <T extends Record> T assign(final T assignee,
                                              final Map<String, Object> value)
    {
        Preconditions.checkNotNull(assignee, "The assignee cannot be null.");
        Preconditions.checkNotNull(value, "The value cannot be null.");

        T record = assignee;

        for (String key : value.keySet())
        {
            record = (T) Records.set(record, key, value.get(key));
        }

        return record;
    }

    /**
     * This method assigns the writes the entries in one record into another record.
     *
     * @param assignee is the record that will be written into.
     * @param value is the record that contains the entries to write into the assignee.
     * @return the assignee, if it is modifiable; otherwise, return a modified copy thereof.
     * @param NullPointerException if the assignee is null.
     * @param NullPointerException if the value is null.
     */
    @Infer
    public static <T extends Record> T assign(final T assignee,
                                              final Record value)
    {
        Preconditions.checkNotNull(assignee, "The assignee cannot be null.");
        Preconditions.checkNotNull(value, "The value cannot be null.");

        return assign(assignee, entryMap(value));
    }

    /**
     * This method determines whether the type of the entries in a record are all the same.
     *
     * @param record is the record itself.
     * @return true, if the static type of each entry is the same.
     */
    public static boolean isHomogeneous(final Record record)
    {
        Preconditions.checkNotNull(record);

        // This algorithm could be replaced in the future.
        final boolean answer = Sets.newHashSet(record.types()).size() <= 1;

        return answer;
    }

    /**
     * This method creates a map that maps the entry-names to entry-values.
     *
     * @param record is the record that will be converted to a map.
     * @return a map representation of the record.
     */
    public static Map<String, Object> entryMap(final Record record)
    {
        Preconditions.checkNotNull(record);

        final SortedMap<String, Object> map = Maps.newTreeMap();

        final Collection<String> keys = record.keys();

        final List<Object> values = record.values();

        int i = 0;

        for (String key : keys)
        {
            map.put(key, values.get(i++));
        }

        return Collections.unmodifiableSortedMap(map);
    }
}

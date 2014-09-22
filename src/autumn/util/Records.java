package autumn.util;

import autumn.lang.Functor;
import autumn.lang.Record;
import autumn.lang.SpecialMethods;
import autumn.lang.Struct;
import autumn.lang.Tuple;
import autumn.lang.TypedFunctor;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class provides static utility methods for working with tuples.
 *
 * @author Mackenzie High
 */
public final class Records
{
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
    public static <T extends Record> T bindCompare(final T owner,
                                                   final TypedFunctor handler)
    {
        Preconditions.checkNotNull(owner);

        final SpecialMethods methods = new SpecialMethods().setCompareTo(handler);

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
    public static <T extends Record> T bindEquals(final T owner,
                                                  final Functor handler)
    {
        final SpecialMethods methods = new SpecialMethods().setEquals(handler);

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
    public static <T extends Record> T bindHash(final T owner,
                                                final Functor handler)
    {
        final SpecialMethods methods = new SpecialMethods().setHashCode(handler);

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
    public static <T extends Record> T bindStr(final T owner,
                                               final Functor handler)
    {
        final SpecialMethods methods = new SpecialMethods().setToString(handler);

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
    public static <T extends Record> T assign(final T assignee,
                                              final Map<String, Object> value)
    {
        Preconditions.checkNotNull(assignee, "The assignee cannot be null.");
        Preconditions.checkNotNull(value, "The value cannot be null.");

        T record = assignee;

        for (String key : value.keySet())
        {
            record = (T) record.set(key, value.get(key));
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
    public static <T extends Record> T assign(final T assignee,
                                              final Record value)
    {
        Preconditions.checkNotNull(assignee, "The assignee cannot be null.");
        Preconditions.checkNotNull(value, "The value cannot be null.");

        return assign(assignee, value.toMap());
    }

    public static List<String> linearize(final Struct struct)
    {
        Preconditions.checkNotNull(struct);

        return null;
    }

    public static Set<Object> enumerate(final Tuple tuple)
    {
        return null;
    }

    public static void visit(final Struct root)
    {
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
        final boolean answer = Sets.newHashSet(record.types().values()).size() <= 1;

        return answer;
    }

    /**
     * This method creates a list of the types of the elements in a tuple.
     *
     * <p>
     * Element[i] of the returned list is the static-type of the element[i] of the tuple.
     * </p>
     *
     * @param tuple is the tuple itself.
     * @return an immutable list containing the types of the elements in the tuple.
     */
    public static List<Class> types(final Tuple tuple)
    {
        Preconditions.checkNotNull(tuple);

        final List<Class> result = Lists.newLinkedList();

        for (String key : tuple.keys())
        {
            result.add(tuple.types().get(key));
        }

        return Collections.unmodifiableList(result);
    }
}

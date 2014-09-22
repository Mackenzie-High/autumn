// TODO: Finish this interface.
// I think it is mostly done, but double check it.
// For example, should compare throw USOException or ISException?
package autumn.lang;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * An instance of this interface is a tuple data-structure.
 *
 * <p>
 * Unlike some languages, tuples in Autumn are so-called "named tuples".
 * In other words, tuples also map keys to values, rather than being simply linear.
 * </p>
 *
 * <p>
 * An implementation of this interface must provide a single public constructor.
 * The constructor should have one formal-parameter for each element.
 * As a result, a user must be able to create an instance by simply calling the constructor.
 * <br>
 * <br>
 * Example tuple(String, Integer, String): <br>
 * <code>
 * Tuple greeting = new Tuple("Hello", 2, "World");
 * </code>
 * </p>
 *
 * <p>
 * An implementation of this interface must also provide a static method instance().
 * This method should return an instance of the implementation, when invoked.
 * Thus, the return type of the method would be the type of the enclosing implementation.
 * The returned instance must be immutable.
 * Each element in the returned instance should be set to the default value for its type.
 * Remember, the size of a tuple is a constant.
 * Even though the elements are set to their defaults, the size() is still the element count.
 * </p>
 *
 * <p>
 * <b>Warning:</p>
 * Tuples should not be used to create recursive data, because this could cause unbounded recursion
 * in the compareTo(Record), equals(Object), hashCode(), or toString() method.
 * However, recursive data may be acceptable, if there methods are redefined by the user.
 * </p>
 *
 * @author Mackenzie High
 */
public interface Tuple
        extends Record,
                Iterable
{
    /**
     * {@inheritDoc}
     */
    @Override
    public Tuple bind(SpecialMethods methods);

    /**
     * {@inheritDoc}
     */
    @Override
    public Tuple set(String key,
                     Object value);

    /**
     * {@inheritDoc}
     */
    @Override
    public Tuple clear();

    /**
     * {@inheritDoc}
     */
    @Override
    public Tuple copy();

    /**
     * {@inheritDoc}
     */
    @Override
    public Tuple immutable();

    /**
     * {@inheritDoc}
     */
    @Override
    public Tuple mutable();

    /**
     * This method creates a list containing the key part of each element.
     *
     * <p>
     * Element [i] of the returned list is the key of the element [i] in this tuple.
     * </p>
     *
     * @return the aforedescribed immutable list.
     */
    @Override
    public List<String> keys();

    /**
     * This method creates a list containing the value of each element in this tuple.
     *
     * <p>
     * Element [i] of the returned list is the value of the element [i] in this tuple.
     * </p>
     *
     * @return the aforedescribed immutable list.
     */
    @Override
    public List<Object> values();

    /**
     * This method assigns a new value to the element at a given index.
     *
     * <p>
     * Auto-unboxing will be performed, if necessary.
     * However, the unboxed value will not be auto-widened.
     * </p>
     *
     * @param index is the index of the element to assign.
     * @param value is the new value.
     * @return this tuple, if this tuple is mutable; otherwise, returned a modified copy thereof.
     * @throws IndexOutOfBoundsException if the index does not refer to an actual element.
     * @throws ClassCastException if the object is not of an acceptable type.
     */
    public Tuple set(int index,
                     Object value);

    /**
     * This method retrieves the value of an element at a given index.
     *
     * @param index is the index of the element to retrieve.
     * @return the value of the element.
     * @throws IndexOutOfBoundsException if the index does not refer to an actual element.
     */
    public Object get(int index);

    /**
     * This method creates an iterator over this tuple.
     *
     * <p>
     * The iterator does not support the optional remove() operation.
     * </p>
     *
     * @return <code>values().iterator()</code>
     */
    @Override
    public Iterator<Object> iterator();

    /**
     * This method creates a list-iterator over this tuple.
     *
     * <p>
     * The iterator does not support the optional add and remove operations.
     * </p>
     *
     * <p>
     * The iterator supports the set(Object) operation, iff this tuple is mutable.
     * </p>
     *
     * @return the aforedescribed iterator.
     */
    public ListIterator<Object> listIterator();

    /**
     * This method creates a string representation of this tuple.
     *
     * <p>
     * The string representation of a tuple is a parentheses enclosed list of the values therein.
     * <br>
     * Examples:
     * <code>
     * ()
     * (1, 2)
     * (Hello, Alien, World)
     * </code>
     * </p>
     *
     * <p>
     * This method's default behavior can be replaced by binding a method handler to the object.
     * </p>
     *
     * @return the string representation of this tuple.
     */
    @Override
    public String toString();
}

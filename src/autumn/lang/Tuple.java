// TODO: Finish this interface.
// I think it is mostly done, but double check it.
// For example, should compare throw USOException or ISException?
package autumn.lang;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.SortedMap;

/**
 * An instance of this interface is a tuple.
 *
 * <p>
 * Unlike some languages, tuples in Autumn are so-called "named tuples".
 * In other words, tuples also map keys to values, rather than being simply linear.
 * </p>
 *
 * <p>
 * An implementation of this interface should provide a single public constructor.
 * The constructor should have one formal-parameter for each element.
 * As a result, a user should be able to create an instance by simply calling that constructor.
 * <br>
 * <br>
 * Example tuple(String, Integer, String): <br>
 * <code>
 * Tuple greeting = new Tuple("Hello", 2, "World");
 * </code>
 * </p>
 *
 * <p>
 * An implementation of this interface should also provide a static method proto().
 * This method should return an instance of the implementation, when invoked.
 * Thus, the return type of the method would be the type of the enclosing implementation.
 * The returned instance must be immutable.
 * Each element in the returned instance should be set to the default value for its type.
 * Remember, the size of a tuple is a constant.
 * Even though the elements are set to their defaults, the size() is still the element count.
 * </p>
 *
 * <p>
 * <b>Warning:</p> Tuples should not be used to create recursive data, because this could
 * cause unbounded recursion in the equals(Object), hashCode(), or toString() method.
 * </p>
 *
 * @author Mackenzie High
 */
public interface Tuple<T extends Tuple>
        extends Iterable,
                Comparable<Tuple>,
                Serializable
{
    /**
     * This method creates a list of the static-types of the elements.
     *
     * @return the aforesaid immutable list.
     */
    public List<Class> types();

    /**
     * This method creates a list containing the key part of each element.
     *
     * <p>
     * Element [i] of the returned list is the key of the element [i] in this tuple.
     * </p>
     *
     * @return the aforedescribed immutable list.
     */
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
    public List<Object> values();

    /**
     * This method determines whether this tuple is empty.
     *
     * @return true, iff the size() of this tuple is zero.
     */
    public boolean isEmpty();

    /**
     * This method counts the number of elements in this tuple.
     *
     * @return the size of this tuple.
     */
    public int size();

    /**
     * This method searches for a value in this tuple.
     *
     * <p>
     * The search is conducted in moving away from index zero.
     * </p>
     *
     * @param value is the value to search for,
     * @return the index of the found value, or negative one, if the value was not found.
     */
    public int indexOf(Object value);

    /**
     * This method searches for a value in this tuple, but skips given number of occurrences.
     *
     * <p>
     * The search is conducted in moving away from index zero.
     * </p>
     *
     * @param value is the value to search for,
     * @param skip is the number of occurrences to skip.
     * @return the index of the found value, or negative one, if the value was not found.
     */
    public int indexOf(Object value,
                       int skip);

    /**
     * This method searches for a value in this tuple.
     *
     * <p>
     * The search is conducted in moving towards index zero.
     * </p>
     *
     * @param value is the value to search for,
     * @return the index of the found value, or negative one, if the value was not found.
     */
    public int lastIndexOf(Object value);

    /**
     * This method searches for a value in this tuple, but skips given number of occurrences.
     *
     * <p>
     * The search is conducted in moving towards index zero.
     * </p>
     *
     * @param value is the value to search for,
     * @param skip is the number of occurrences to skip.
     * @return the index of the found value, or negative one, if the value was not found.
     */
    public int lastIndexOf(Object value,
                           int skip);

    /**
     * This method sets the value of each element to its default value.
     *
     * <p>
     * This method does not affect the size of the tuple.
     * </p>
     *
     * @return this object, if this tuple is mutable; otherwise, return a modified copy thereof.
     */
    public Tuple<T> clear();

    /**
     * This method determines whether this tuple contains a given value.
     *
     * @param value is the value this tuple may contain.
     * @return true, iff this tuple contains the value.
     */
    public boolean contains(Object value);

    /**
     * This method assigns a new value to the element at a given index.
     *
     * @param index is the index of the element to assign.
     * @param value is the new value.
     * @return this tuple, if this tuple is mutable; otherwise, returned a modified copy thereof.
     * @throws IndexOutOfBoundsException if the index does not refer to an actual element.
     * @throws ClassCastException if the object is not of an acceptable type.
     */
    public Tuple<T> set(int index,
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
     * This method assigns a new value to the element at a given key.
     *
     * @param key is the key of the element to assign.
     * @param value is the new value.
     * @return this tuple, if this tuple is mutable; otherwise, returned a modified copy thereof.
     * @throws NullPointerException if the key is null.
     * @throws NoSuchElementException if no element is identified by the given key.
     * @throws ClassCastException if the object is not of an acceptable type.
     */
    public Tuple<T> set(String key,
                        Object value);

    /**
     * This method retrieves the value of an element at a given key.
     *
     * @param key is the key of the element to retrieve.
     * @return the value of the element.
     * @throws NullPointerException if the key is null.
     * @throws NoSuchElementException if no element is identified by the given key.
     */
    public Object get(String key);

    /**
     * This method creates a Map representation of this tuple.
     *
     * @return an immutable map containing the key-value mappings of this tuple.
     */
    public SortedMap<String, Object> toMap();

    /**
     * This method makes the elements in this tuple be those of another tuple.
     *
     * <p>
     * In short, this method copies each of the elements of the other tuple into this tuple.
     * Each element is cast to the appropriate type in order to ensure type-safety.
     * </p>
     *
     * @param other is the tuple that will supply the new elements for this tuple.
     * @return this tuple, if this tuple is mutable; otherwise, returned a modified copy thereof.
     * @throws NullPointerException if other is null.
     * @throws IllegalArgumentException if the other.size() != this.size().
     * @throws IllegalStateException if this tuple is not mutable.
     * @throws ClassCastException if any of the elements cannot be assigned due to its type.
     */
    public Tuple<T> assign(Tuple<?> other);

    /**
     * This method determines whether this tuple is mutable.
     *
     * @return ! isReadonly().
     */
    public boolean isMutable();

    /**
     * This method determines whether this tuple is immutable.
     *
     * @return ! isMutable().
     */
    public boolean isImmutable();

    /**
     * This method creates a shallow copy of this tuple.
     *
     * <p>
     * If this tuple is mutable, then the copy will be mutable.
     * If this tuple is immutable, then the copy will be immutable.
     * </p>
     *
     * @return a copy of this tuple.
     */
    public Tuple<T> copy();

    /**
     * This method creates a immutable copy of this tuple.
     *
     * @return a immutable copy of this tuple.
     */
    public Tuple<T> immutableCopy();

    /**
     * This method creates a mutable copy of this tuple.
     *
     * @return a mutable copy of this tuple.
     */
    public Tuple<T> mutableCopy();

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
     * This method compares this tuple to another given tuple.
     *
     * <p>
     * Tuples are compared based on the following information.
     * Firstly, tuples are compared based on their length.
     * Shorter tuples are less-than longer tuples.
     * Secondly, tuples are compared based on their keys.
     * Since each key is a string, it can be compared to the equally index key in other.
     * This enforces a sort of lexicographic order.
     * Thirdly, the tuples are compared based on their comparable values at equal indexes.
     * If none of the values are comparable, then an IllegalStateException is thrown.
     * The exception should include an informative error message.
     * </p>
     *
     * @param other is the other tuple itself.
     * @return an integer indicating the relationship of this tuple to the other tuple.
     * @throws IllegalStateException if this method is not supported by this instance.
     */
    @Override
    public int compareTo(Tuple other);

    /**
     * This method determines whether this tuple equals another given tuple.
     *
     * <p>
     * Equality Rules:
     * <ol>
     * <li>If this is the same object as other, then return true.</li>
     * <li>If other is not a Tuple, return false.</i>
     * <li>If this.size() != other.size(), return false.</li>
     * <li>Return this.keys().equals(other.keys()) && this.values().equals(other.values()).</li>
     * </ol>
     * </p>
     *
     * @param other is the other tuple itself.
     * @return true, iff this tuple equals the other tuple.
     */
    @Override
    public boolean equals(Object other);

    /**
     * This method computes a hash-code for this tuple.
     *
     * @return <code>this.toMap().hashCode()</code>
     */
    @Override
    public int hashCode();

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
     * ("Hello", "Alien", "World")
     * </code>
     * </p>
     *
     * @return the string representation of this tuple.
     */
    @Override
    public String toString();
}

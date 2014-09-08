package autumn.lang;

import java.io.Serializable;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;

/**
 * An instance of this interface is a struct.
 *
 * @author Mackenzie High
 */
public interface Struct<T extends Struct>
        extends Comparable<Tuple>,
                Serializable
{
    /**
     * This method creates a map that maps the name of an element to its static-type.
     *
     * @return the aforesaid immutable map.
     */
    public Map<String, Class> types();

    /**
     * This method creates set that contains the names of the elements in this struct.
     *
     * @return the aforedescribed immutable set.
     */
    public SortedSet<String> keys();

    /**
     * This method sets the value of each element to its default value.
     *
     * @return this object, if this struct is mutable; otherwise, return a modified copy thereof.
     */
    public Struct<T> clear();

    /**
     * This method assigns a new value to the element at a given index.
     *
     * <p>
     * Auto-unboxing will be performed, if necessary.
     * However, the unboxed value will not be auto-widened.
     * </p>
     *
     * @param key is the name of the element to assign the value to.
     * @param value is the new value.
     * @return this object, if this struct is mutable; otherwise, returned a modified copy thereof.
     * @throws NoSuchElementException if the key does not refer to an actual element.
     * @throws ClassCastException if the object is not of an acceptable type.
     */
    public Struct<T> set(String index,
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
     * This method creates a Map representation of this struct.
     *
     * @return an immutable map containing the key-value mappings of this struct.
     */
    public SortedMap<String, Object> toMap();

    /**
     * This method determines whether this struct is mutable.
     *
     * @return ! isReadonly().
     */
    public boolean isMutable();

    /**
     * This method determines whether this struct is immutable.
     *
     * @return ! isMutable().
     */
    public boolean isImmutable();

    /**
     * This method creates a shallow copy of this tuple.
     *
     * <p>
     * If this struct is mutable, then the copy will be mutable.
     * If this struct is immutable, then the copy will be immutable.
     * </p>
     *
     * @return a copy of this struct.
     */
    public Struct<T> copy();

    /**
     * This method creates a immutable copy of this struct.
     *
     * @return a immutable copy of this struct.
     */
    public Struct<T> immutableCopy();

    /**
     * This method creates a mutable copy of this struct.
     *
     * @return a mutable copy of this struct.
     */
    public Struct<T> mutableCopy();

    /**
     * TODO
     *
     * This method compares this struct to another given struct.
     *
     * @param other is the other struct itself.
     * @return an integer indicating the relationship of this struct to the other struct.
     * @throws IllegalStateException if this method is not supported by this instance.
     */
    @Override
    public int compareTo(Tuple other);

    /**
     * This method determines whether this tuple equals another object.
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

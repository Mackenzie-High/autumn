package autumn.lang;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * An instance of this interface is a user-defined key-value mapping data-structure.
 *
 * <p>
 * Subtypes of this interface should provide covariant overrides of the following methods:
 * <ul>
 * <li> bind(SpecialMethods) </li>
 * <li> set(String, Object) </li>
 * <li> clear() </li>
 * <li> copy() </li>
 * <li> immutable() </li>
 * <li> mutable() </li>
 * </ul>
 * </p>
 *
 * @author Mackenzie High
 */
public interface Record
        extends Copyable,
                Mutable,
                Immutable,
                Comparable<Record>,
                Serializable
{
    /**
     * This method sets the handlers of the special methods herein.
     *
     * @param methods provides handlers for special methods.
     * @return this object, if this record is mutable; otherwise, returned a modified copy thereof.
     */
    public Record bind(SpecialMethods methods);

    /**
     * This method creates a collection that contains the names of the elements in this record.
     *
     * @return the aforedescribed immutable collection.
     */
    public Collection<String> keys();

    /**
     * This method creates a collection containing the value of each element in this record.
     *
     * @return the aforedescribed immutable collection.
     */
    public Collection<Object> values();

    /**
     * This method creates a map that maps the name of an element to its static-type.
     *
     * @return the aforesaid immutable map.
     */
    public Map<String, Class> types();

    /**
     * This method sets the value of each element to its default value.
     *
     * <p>
     * This method does not affect the special-method bindings.
     * </p>
     *
     * @return this object, if this record is mutable; otherwise, return a modified copy thereof.
     */
    public Record clear();

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
     * @return this object, if this record is mutable; otherwise, returned a modified copy thereof.
     * @throws NoSuchElementException if the key does not refer to an actual element.
     * @throws ClassCastException if the object is not of an acceptable type.
     */
    public Record set(String index,
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
     * This method creates a Map representation of this record.
     *
     * @return an immutable map containing the key-value mappings of this record.
     */
    public Map<String, Object> toMap();

    /**
     * This method counts the elements in this record.
     *
     * @return <code> key().size() </code>
     */
    public int size();

    /**
     * This method determines whether this tuple is empty.
     *
     * @return true, iff the size() of this tuple is zero.
     */
    public boolean isEmpty();

    /**
     * This method determines whether this record is mutable.
     *
     * @return <code> ! isReadonly() </code>
     */
    public boolean isMutable();

    /**
     * This method determines whether this record is immutable.
     *
     * @return <code> ! isMutable() </code>
     */
    public boolean isImmutable();

    /**
     * This method creates a shallow copy of this record.
     *
     * <p>
     * If this record is mutable, then the copy will be mutable.
     * If this record is immutable, then the copy will be immutable.
     * </p>
     *
     * @return a copy of this record.
     */
    @Override
    public Record copy();

    /**
     * This method creates a immutable copy of this record.
     *
     * @return a immutable copy of this record.
     */
    @Override
    public Record immutable();

    /**
     * This method creates a mutable copy of this record.
     *
     * @return a mutable copy of this record.
     */
    @Override
    public Record mutable();

    /**
     * TODO
     *
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
     * <p>
     * This method's default behavior can be replaced by binding a method handler to the object.
     * </p>
     *
     * @param other is the other tuple itself.
     * @return an integer indicating the relationship of this tuple to the other tuple.
     * @throws IllegalStateException if this method is not supported by this instance.
     */
    @Override
    public int compareTo(Record other);

    /**
     * This method determines whether this record equals another object.
     *
     * <p>
     * Equality Rules:
     * <ol>
     * <li>If this is the same object as other, then return true.</li>
     * <li>If other is not a Record, return false.</i>
     * <li>If this.size() != other.size(), return false.</li>
     * <li>Return this.keys().equals(other.keys()) && this.values().equals(other.values()).</li>
     * </ol>
     * </p>
     *
     * <p>
     * This method's default behavior can be replaced by binding a method handler to the object.
     * </p>
     *
     * @param other is the other tuple itself.
     * @return true, iff this record equals the other object.
     */
    @Override
    public boolean equals(Object other);

    /**
     * This method computes a hash-code for this record.
     *
     * <p>
     * This method's default behavior can be replaced by binding a method handler to the object.
     * </p>
     *
     * @return <code> this.toMap().hashCode() </code>
     */
    @Override
    public int hashCode();
}

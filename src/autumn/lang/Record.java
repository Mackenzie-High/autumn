package autumn.lang;

import high.mackenzie.autumn.resources.Finished;
import java.util.Iterator;
import java.util.List;

/**
 * An instance of this interface is a user-defined key-value mapping data-structure.
 *
 * <p>
 * This interface only defines the minimum required functionality.
 * Convenience functions are provided by class autumn.util.Records and class autumn.util.F.
 * </p>
 *
 * <p>
 * Subtypes of this interface should provide covariant overrides of the set(String, Object) method.
 * For example, a type name T should provide a set(String, Object) : T method.
 * </p>
 *
 * @author Mackenzie High
 */
@Finished("2014/10/18")
public interface Record
        extends Iterable,
                Comparable<Record>
{
    /**
     * This method determines whether this object was created from a struct-definition.
     *
     * @return true, if this object is an instance of a struct.
     */
    public boolean isStruct();

    /**
     * This method determines whether this object was created from a tuple-definition.
     *
     * @return true, if this object is an instance of a tuple.
     */
    public boolean isTuple();

    /**
     * This method retrieves a list that contains the names of the elements in this record.
     *
     * <p>
     * This is a constant-time operation.
     * </p>
     *
     * @return the aforedescribed immutable list.
     */
    public List<String> keys();

    /**
     * This method creates a new list containing the type of each element in this record.
     *
     * <p>
     * This is a constant-time operation.
     * </p>
     *
     * @return the aforedescribed immutable list.
     */
    public List<Class> types();

    /**
     * This method creates a new list containing the value of each element in this record.
     *
     * <p>
     * This is a linear-time operation.
     * </p>
     *
     * <p>
     * The new collection is <b>not</b> backed by this object.
     * </p>
     *
     * @return the aforedescribed immutable list.
     */
    public List<Object> values();

    /**
     * This method assigns a new value to a specific element.
     *
     * <p>
     * Auto-unboxing will be performed, if necessary.
     * However, the unboxed value will not be automatically widened.
     * </p>
     *
     * @param index is the index of the element to assign the value to.
     * @param value is the new value.
     * @return this object, if this record is mutable; otherwise, return a modified copy thereof.
     * @throws IndexOutOfBoundsException if the index is out-of-bounds.
     * @throws ClassCastException if the object is not of an acceptable type.
     */
    public Record set(int index,
                      Object value);

    /**
     * This method retrieves the value of a specific element.
     *
     * @param index is the index of the element to assign the value to.
     * @return the value of the element.
     * @throws IndexOutOfBoundsException if the index is out-of-bounds.
     */
    public Object get(int index);

    /**
     * This method counts the elements in this record.
     *
     * <p>
     * This is a constant-time operation.
     * </p>
     *
     * @return <code> key().size() </code>
     */
    public int size();

    /**
     * This method determines whether this record is empty.
     *
     * <p>
     * This is a constant-time operation.
     * </p>
     *
     * @return true, iff the size() of this tuple is zero.
     */
    public boolean isEmpty();

    /**
     * This method creates an iterator over the values in this record.
     *
     * @return values().iterator().
     */
    @Override
    public Iterator iterator();

    /**
     * This method compares this record to another given record.
     *
     * <p>
     * Records are compared based on the natural ordering of their values().
     * If the other object is null, then this object is object is greater.
     * If the other object is non-null, then the two records must have exactly the same keys().
     * </ol>
     *
     * </p>
     *
     * @param other is the other record itself.
     * @return an integer indicating the relationship of this record to the other record.
     * @throws UnsupportedOperationException if the comparison operation could not be completed.
     */
    @Override
    public int compareTo(Record other);

    /**
     * This method determines whether this record equals another object.
     *
     * <p>
     * Equality Rules:
     * <ol>
     * <li>If other is null, then return false.</li>
     * <li>If this is the same object as other, then return true.</li>
     * <li>If other is not a Record, return false.</i>
     * <li>If this.keys() is not equal to other.keys(), then return false.</li>
     * <li>If this.values() is not equal to other.values(), then return false.</li>
     * <li>Return true.</li>
     * </ol>
     * </p>
     *
     * @param other is a value that may be equal to this object.
     * @return true, iff this record equals the given value.
     */
    @Override
    public boolean equals(Object other);

    /**
     * This method computes a hash-code for this record.
     *
     * @return <code> keys().hashCode() xor values().hashCode() </code>
     */
    @Override
    public int hashCode();

    /**
     * This method creates a string representation of this object.
     *
     * @return value().toString().
     */
    @Override
    public String toString();
}

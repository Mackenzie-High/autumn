package autumn.util.ds;

import java.util.List;
import java.util.Map;

/**
 * An instance of this interface is a sparse-array data-structure.
 *
 * @author Mackenzie High
 */
public interface SparseArray<T>
        extends Iterable<T>
{
    /**
     * This method retrieves the default-value of elements in this sparse-array.
     *
     * @return the default-value used herein.
     */
    public T defaultValue();

    /**
     * This method retrieves the lowest index allowed in this sparse-array.
     *
     * @return the lowest index that is allowed.
     */
    public int lowerBound();

    /**
     * This method retrieves the highest index allowed in this sparse-array.
     *
     * @return the highest index that is allowed.
     */
    public int upperBound();

    /**
     * This method retrieves the lowest index present in this sparse-array.
     *
     * @return the lowest index that is present.
     */
    public int minimum();

    /**
     * This method retrieves the highest index present in this sparse-array.
     *
     * @return the highest index that is present.
     */
    public int maximum();

    /**
     * This method sets an element in this sparse-array.
     *
     * <p>
     * If this sparse-array is immutable, then this method will
     * return a modified copy of this object.
     * </p>
     *
     * <p>
     * Using this method to set an element to the default value causes the element to be removed.
     * </p>
     *
     * @param index is the index of the element to set.
     * @param value is the value to assign to the element.
     * @return a modified version of this object.
     * @throws IndexOutOfBoundsException if index is out-of-bounds.
     */
    public SparseArray<T> set(int index,
                              T value);

    /**
     * This method gets the value of an element in this sparse-array.
     *
     * <p>
     * If the element is not present, then the default value will be returned.
     * </p>
     *
     * @param index is the the index of the element to get.
     * @return the value of the element.
     * @throws IndexOutOfBoundsException if index is out-of-bounds.
     */
    public T get(int index);

    /**
     * This method computes the size of this sparse-array.
     *
     * @return upperBound() - lowerBound().
     */
    public int size();

    /**
     * This method counts the number of elements actually present in this sparse-array.
     *
     * @return the number of elements herein.
     */
    public int count();

    /**
     * This method creates a list of the indexes that ares actually present in this sparse-array.
     *
     * @return the indexes that are present herein.
     */
    public List<Integer> indexes();

    /**
     * This method creates a list of the values that ares actually present in this sparse-array.
     *
     * @return the values that are present herein.
     */
    public List<T> values();

    /**
     * This method converts this sparse-array to a map.
     *
     * @return a map that maps indexes to values.
     */
    public Map<Integer, T> toMap();

    /**
     * This method determines whether another object equals this sparse-array.
     *
     * <p>
     * Equality Requirement: other is not null <br>
     * Equality Requirement: other is a SparseArray <br>
     * Equality Requirement: other.indexes() equals this.indexes() <br>
     * Equality Requirement: other.values() equals this.values() <br>
     * </p>
     *
     * @param other is an object that may equal this object.
     * @return true, iff the other object equals this object.
     */
    @Override
    public boolean equals(Object other);

    /**
     * This method computes a hash-value for this sparse-array.
     *
     * @return (sum of indexes) ^ (sum of hash-codes of values)
     */
    @Override
    public int hashCode();
}

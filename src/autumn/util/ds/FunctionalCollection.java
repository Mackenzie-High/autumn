package autumn.util.ds;

import java.util.Iterator;

/**
 * An instance of this interface is a purely functional collection of values.
 *
 * @author Mackenzie High
 */
public interface FunctionalCollection<E>
        extends Iterable<E>
{
    /**
     * This method creates an object that can be used to create a derivative of this data-structure.
     *
     * @return a mutable data-structure based on this functional data-structure.
     */
    public MutableCollection<E> mutable();

    /**
     * This method creates an immutable copy of this object.
     *
     * @return an immutable copy of this object.
     */
    public ImmutableCollection<E> immutable();

    /**
     * This method determines whether this data-structure contains a given value.
     *
     * @param value is the value that this object may contain.
     * @return true, iff this object contains the given value.
     */
    public boolean contains(E value);

    /**
     * This method determines whether this data-structure contains all of a group of values.
     *
     * @param values are the values that this object may contain.
     * @return true, iff this object contains all of the given values.
     */
    public boolean containsAll(Iterable<? extends E> values);

    /**
     * This method determines whether this data-structure contains any of a group of values.
     *
     * @param values are the values that this object may contain.
     * @return true, iff this object contains any of the given values.
     */
    public boolean containsAny(Iterable<? extends E> values);

    /**
     * This method computes the size of this data-structure.
     *
     * @return the number of elements in this data-structure.
     */
    public int size();

    /**
     * This method determines whether this data-structure is empty.
     *
     * @return true, iff size() equals zero.
     */
    public boolean isEmpty();

    /**
     * This method creates an iterator over the values in this data-structure.
     *
     * <p>
     * If this data-structure is ordered,
     * then the iterator will return elements in the order they appear herein.
     * </p>
     *
     * <p>
     * The iterator does not support the remove() operation.
     * </p>
     *
     * @return an iterator over the elements in this collection.
     */
    @Override
    public Iterator<E> iterator();

    /**
     * This method determines whether this object equals a given value.
     *
     * <p>
     * This method obeys the equals(Object) contract set forth in
     * <code>java.util.Collection</code>.
     * </p>
     *
     * @param other is the value that may be equal to this object.
     * @return true, iff this object equals other.
     */
    @Override
    public boolean equals(Object other);

    /**
     * This method computes the hash-code of this data-structure.
     *
     * <p>
     * This method obeys the hashCode() contract set forth in
     * <code>java.util.Collection</code>.
     * </p>
     *
     * @return the hash-code of this object.
     */
    @Override
    public int hashCode();

    /**
     * This method creates a string representation of this data-structure.
     *
     * <p>
     * This method obeys the toString() contract set forth in
     * <code>java.util.Collection</code>.
     * </p>
     *
     * @return a string representation of this data-structure.
     */
    @Override
    public String toString();
}

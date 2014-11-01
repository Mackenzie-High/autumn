package autumn.util.ds;

/**
 * An instance of this interface is persistent list data-structure.
 *
 * @author Mackenzie High
 */
public interface FunctionalList<E>
        extends FunctionalCollection<E>
{
    /**
     * {@inheritDoc}
     */
    @Override
    public MutableList<E> mutable();

    /**
     * This method creates an immutable copy of this object.
     *
     * @return an immutable copy of this object.
     */
    @Override
    public ImmutableList<E> immutable();

    /**
     * This method non-destructively adds a value to the end of this list.
     *
     * <p>
     * Equivalent: addLast(Object)
     * </p>
     *
     * @param value is the value to add to this list.
     * @return a modified copy of this list.
     */
    public FunctionalList<E> add(E value);

    /**
     * This method non-destructively inserts a value into this list.
     *
     * @param index is the position where to insert the value.
     * @param value is the value to insert.
     * @return a modified copy of this list.
     */
    public FunctionalList<E> add(int index,
                                 E value);

    /**
     * This method non-destructively add multiple value to the end of this list.
     *
     * @param values are the values to add to this list.
     * @return a modified copy of this list.
     */
    public FunctionalList<E> addAll(Iterable<? extends E> values);

    /**
     * This method non-destructively inserts multiple values into this list.
     *
     * @param index is the position where to insert the values.
     * @param value are the values to insert.
     * @return a modified copy of this list.
     */
    public FunctionalList<E> addAll(int index,
                                    Iterable<? extends E> values);

    /**
     * This method non-destructively adds a value to the front of this list.
     *
     * @param value is the value to add to this list.
     * @return a modified copy of this list.
     */
    public FunctionalList<E> addFirst(E value);

    /**
     * This method non-destructively adds a value to the end of this list.
     *
     * @param value is the value to add to this list.
     * @return a modified copy of this list.
     */
    public FunctionalList<E> addLast(E value);

    /**
     * This method non-destructively removes an element from this list.
     *
     * @param index is the index of the element to remove.
     * @return a modified copy of this list.
     */
    public FunctionalList<E> remove(int index);

    /**
     * This method non-destructively removes the first element from this list.
     *
     * @return a modified copy of this list.
     */
    public FunctionalList<E> removeFirst();

    /**
     * This method non-destructively removes the last element from this list.
     *
     * @return a modified copy of this list.
     */
    public FunctionalList<E> removeLast();

    /**
     * This method non-destructively removes all the elements from this list.
     *
     * @return a modified copy of this list.
     */
    public FunctionalList<E> clear();

    /**
     * This method non-destructively sets an element in this list.
     *
     * @param index is the index of the element to set.
     * @param value is the value to place into the element.
     * @return a modified copy of this list.
     */
    public FunctionalList<E> set(final int index,
                                 final E value);

    /**
     * This method retrieves the value of an element in this list.
     *
     * @param index is the index of the element.
     * @return the value stored in the element.
     */
    public E get(int index);

    /**
     * This method retrieves the value of the first element in this list.
     *
     * @return the value stored in the element.
     */
    public E first();

    /**
     * This method retrieves the value of the last element in this list.
     *
     * @return the value stored in the element.
     */
    public E last();

    /**
     * {@inheritDoc}
     */
    @Override
    public int size();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other);

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode();

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString();
}

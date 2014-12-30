package high.mackenzie.autumn.util.ds;

import autumn.util.Strings;
import autumn.util.ds.ImmutableSequence;
import autumn.util.ds.MutableSequence;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.util.Collections;
import java.util.Iterator;
import java.util.Stack;

/**
 * An instance of this class is a functional-style list data-structure.
 *
 * @author Mackenzie High
 */
final class FunctionalList<E>
        implements Iterable<E>
{
    private final int first;

    private final int last;

    /**
     * This tree maps pseudo-indexes to values.
     */
    private final ITree tree;

    /**
     * Constructor.
     */
    public FunctionalList()
    {
        this(new SlowTree(), 0, 0);
    }

    /**
     * Constructor.
     *
     * @param tree
     * @param first
     * @param last
     */
    private FunctionalList(final ITree tree,
                           final int first,
                           final int last)
    {
        this.tree = tree;
        this.first = first;
        this.last = last;
    }

    /**
     * This method creates a mutable list that is backed by this immutable list.
     *
     * @return the new mutable list.
     */
    public MutableSequence<E> mutable()
    {
        return new ConcreteMutableSequence<E>(this);
    }

    /**
     * This method creates an immutable list that is backed by this immutable list.
     *
     * <p>
     * Unlike this list, the new list implements the List interface.
     * </p>
     *
     * @return the new immutable list.
     */
    public ImmutableSequence<E> immutable()
    {
        return new ConcreteImmutableSequence<E>(this);
    }

    /**
     * This method computes the number of elements in this list.
     *
     * @return the number of elements herein.
     */
    public int size()
    {
        return tree.size();
    }

    /**
     * This method determines whether this list is empty.
     *
     * @return true, iff this list is empty.
     */
    public boolean isEmpty()
    {
        return size() == 0;
    }

    /**
     * This method inserts a value into this list.
     *
     * @param index is where the element will be inserted.
     * @param value is the value to insert.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public FunctionalList<E> insert(final int index,
                                    final E value)
    {
        return insertAll(index, Collections.singleton(value));
    }

    /**
     * This method inserts multiple values into this list simultaneously.
     *
     * @param index is where the element will be inserted.
     * @param value is the value to insert.
     * @throws NullPointerException if values is null.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public FunctionalList<E> insertAll(final int index,
                                       final Iterable<? extends E> values)
    {
        Preconditions.checkNotNull(values);

        FunctionalList<E> p = this;

        final int size = size();

        /**
         * Make sure that we can actually insert the values.
         */
        if (index < 0 || index > size)
        {
            throw new IndexOutOfBoundsException("index: " + index);
        }

        /**
         * If the values are to be inserted at the very start, insert them.
         */
        if (index == 0)
        {
            for (E value : values)
            {
                p = p.insertFirst(value);
            }

            return p;
        }

        /**
         * If the values are to be inserted at the very end, insert them.
         */
        if (index == size())
        {
            for (E value : values)
            {
                p = p.insertLast(value);
            }

            return p;
        }

        /**
         * If the values are to be inserted in the middle somewhere, insert them.
         */
        if (index < size / 2)
        {
            return insertFront(index, values);
        }
        else
        {
            return insertBack(index, values);
        }
    }

    /**
     * This method inserts elements into this list.
     *
     * <p>
     * This method is best at inserting elements into the first half of the list.
     * </p>
     *
     * @param index is the index where to insert the elements.
     * @param values are the values to insert.
     * @return a modified copy of this list.
     */
    private FunctionalList<E> insertFront(final int index,
                                          final Iterable<? extends E> values)
    {
        FunctionalList<E> p = this;

        final Stack<E> front = new Stack<E>();

        /**
         * Pop the elements off the front of the list that occur before the insertion point.
         */
        while (front.size() < index)
        {
            front.push(p.get(0));

            p = p.removeFirst();
        }

        /**
         * Add the values that are being inserted to the mutable list.
         */
        for (E value : values)
        {
            front.push(value);
        }

        /**
         * Reinsert the elements that where popped off of the list.
         */
        while (front.isEmpty() == false)
        {
            p = p.insertFirst(front.pop());
        }

        return p;
    }

    /**
     * This method inserts elements into this list.
     *
     * <p>
     * This method is best at inserting elements into the second half of the list.
     * </p>
     *
     * @param index is the index where to insert the elements.
     * @param values are the values to insert.
     * @return a modified copy of this list.
     */
    private FunctionalList<E> insertBack(final int index,
                                         final Iterable<? extends E> values)
    {
        FunctionalList<E> p = this;

        final Stack<E> rear = new Stack<E>();

        /**
         * Pop the elements off the rear of the list that occur after the insertion point.
         */
        while (rear.size() < index)
        {
            rear.push(p.get(p.last));

            p = p.removeLast();
        }

        /**
         * Add the values that are being inserted to the mutable list.
         */
        for (E value : values)
        {
            rear.push(value);
        }

        /**
         * Reinsert the elements that where popped off of the list.
         */
        while (rear.isEmpty() == false)
        {
            p = p.insertLast(rear.pop());
        }

        return p;
    }

    /**
     * This method adds a value to the front of this list.
     *
     * @param value is the value to insert.
     */
    public FunctionalList<E> insertFirst(final E value)
    {
        if (isEmpty())
        {
            return new FunctionalList<E>(tree.insert(0, value), -1, 1);
        }
        else
        {
            return new FunctionalList<E>(tree.insert(first, value), first - 1, last);
        }
    }

    /**
     * This method adds a value to the rear of this list.
     *
     * @param value is the value to insert.
     */
    public FunctionalList<E> insertLast(final E value)
    {
        if (isEmpty())
        {
            return new FunctionalList<E>(tree.insert(0, value), -1, 1);
        }
        else
        {
            return new FunctionalList<E>(tree.insert(last, value), first, last + 1);
        }
    }

    /**
     * This method removes an element from this list.
     *
     * @param index is the index of the element to remove.
     * @return a modified copy of the list.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public FunctionalList<E> remove(final int index)
    {
        final int size = size();

        /**
         * The element being removed must actually be present in the list.
         */
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException("index: " + index);
        }

        /**
         * Use a faster algorithm, if the first element is being removed.
         */
        if (index == 0)
        {
            return removeFirst();
        }

        /**
         * Use a faster algorithm, if the last element is being removed.
         */
        if (index == size)
        {
            return removeLast();
        }

        /**
         * If the element is somewhere in the middle of the list, remove it.
         */
        if (index < size / 2)
        {
            return removeFront(index);
        }
        else
        {
            return removeBack(index);
        }
    }

    /**
     * This method removes a value from somewhere in the middle of this list.
     *
     * <p>
     * This method is best at removing an element from the first half of the list.
     * </p>
     *
     * @param index is the index where to insert the elements.
     * @return a modified copy of this list.
     */
    private FunctionalList<E> removeFront(final int index)
    {
        FunctionalList<E> p = this;

        final Stack<E> front = new Stack<E>();

        /**
         * Pop the elements off the front of the list that occur before the insertion point.
         */
        while (front.size() < index)
        {
            front.push(p.get(0));

            p = p.removeFirst();
        }

        /**
         * Remove the element.
         */
        p = p.removeFirst();

        /**
         * Reinsert the elements that where popped off of the list.
         */
        while (front.isEmpty() == false)
        {
            p = p.insertFirst(front.pop());
        }

        return p;
    }

    /**
     * This method removes a value from somewhere in the middle of this list.
     *
     * <p>
     * This method is best at removing an element from the second half of the list.
     * </p>
     *
     * @param index is the index where to insert the elements.
     * @return a modified copy of this list.
     */
    private FunctionalList<E> removeBack(final int index)
    {
        FunctionalList<E> p = this;

        final Stack<E> rear = new Stack<E>();

        /**
         * Pop the elements off the front of the list that occur before the insertion point.
         */
        for (int i = p.size() - 1; i > index; i--)
        {
            rear.push(p.get(p.last - 1));

            p = p.removeLast();
        }

        /**
         * Remove the element.
         */
        p = p.removeLast();

        /**
         * Reinsert the elements that where popped off of the list.
         */
        while (rear.isEmpty() == false)
        {
            p = p.insertLast(rear.pop());
        }

        return p;
    }

    /**
     * This method removes the first element from this list.
     *
     * @return a modified copy of this list.
     */
    public FunctionalList<E> removeFirst()
    {
        FunctionalList r = new FunctionalList<E>(tree.delete(first + 1), first + 1, last);

        return r;
    }

    /**
     * This method removes the last element from this list.
     *
     * @return a modified copy of this list.
     */
    public FunctionalList<E> removeLast()
    {
        return new FunctionalList<E>(tree.delete(last - 1), first, last - 1);
    }

    /**
     * This method removes all the elements from this list.
     *
     * @return an empty list.
     */
    public FunctionalList<E> clear()
    {
        return new FunctionalList<E>();
    }

    /**
     * This method sets the value of an element in this list.
     *
     * @param index is the location of the element in this list.
     * @param value is the new value of the element.
     * @return a modified copy of this list.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public FunctionalList<E> set(final int index,
                                 final E value)
    {
        return new FunctionalList<E>(tree.insert(first + index + 1, value), first, last);
    }

    /**
     * This method gets the value of an element in this list.
     *
     * @param index is the location of the element in this list.
     * @return the value of the element.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public E get(final int index)
    {
        if (index < 0 || index >= size())
        {
            throw new IndexOutOfBoundsException("index: " + index);
        }

        return (E) tree.find(first + index + 1).value;
    }

    /**
     * This method searches for a value in this list.
     *
     * @param value is the value to search for.
     * @return the index where the value is located, or minus one, if the value is not found.
     */
    public int indexOf(final Object value)
    {
        int index = 0;

        for (Object element : this)
        {
            if (Objects.equal(value, element))
            {
                return index;
            }
            else
            {
                ++index;
            }
        }

        return -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<E> iterator()
    {
        return tree.values();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        return Strings.str(this, "[", ", ", "]");
    }

    public static void main(String[] args)
    {
        FunctionalList p = new FunctionalList();

        p = p.insert(0, "A");
        p = p.insert(1, "B");
        p = p.insert(2, "C");

        System.out.println(p);

        p = p.set(2, "M");

        System.out.println(p);
    }
}

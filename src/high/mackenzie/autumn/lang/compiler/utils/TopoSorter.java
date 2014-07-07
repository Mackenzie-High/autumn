package high.mackenzie.autumn.lang.compiler.utils;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * An instance of this class can sort a list of elements topologically.
 *
 * @author Mackenzie High
 */
public abstract class TopoSorter<T>
{
    /**
     * Given two elements, this function determines whether the left one is less than the right one.
     *
     * @param left is the elements that may be less than the right element.
     * @param right is the right element itself.
     * @return true, iff left is less-than right.
     */
    public abstract boolean isLess(T left,
                                   T right);

    /**
     * These are the sorted elements.
     */
    private final LinkedList<T> elements = Lists.newLinkedList();

    /**
     * This method adds more elements to this topologically sorted list.
     *
     * @param elements are the elements to add.
     */
    public void addAll(final Iterable<? extends T> elements)
    {
        Preconditions.checkNotNull(elements);

        for (T element : elements)
        {
            Preconditions.checkNotNull(element);

            add(element);
        }
    }

    /**
     * This method adds a single element to this topologically sorted list.
     *
     * @param element is the element to add.
     */
    public void add(final T element)
    {
        // Note: This algorithm is currently an O(N^2) in the worst case.
        // The inputs are usually small enough to make this acceptable.
        // However, this algorithm *could* be rewritten at a later date.

        final ListIterator<T> iter = elements.listIterator();

        while (iter.hasNext())
        {
            final T next = iter.next();

            final boolean condition = isLess(element, next);

            if (condition)
            {
                iter.previous();
                iter.add(element);
                return;
            }
        }

        elements.add(element);
    }

    /**
     * This method converts this topologically sorted list to an immutable list.
     *
     * @return this list as an immutable list.
     */
    public List<T> elements()
    {
        return ImmutableList.copyOf(elements);
    }
}

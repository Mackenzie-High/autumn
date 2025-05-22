package autumn.lang.compiler.ast.commons;

import autumn.lang.compiler.ast.nodes.IAstVisitor;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import high.mackenzie.autumn.resources.Finished;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * An instance of this class is an immutable list of constructs.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public final class ConstructList<T extends IConstruct>
        implements Iterable<T>
{
    private ImmutableList<T> elements;

    /**
     * Constructor.
     */
    public ConstructList()
    {
        this.elements = ImmutableList.of();
    }

    /**
     * Constructor.
     *
     * @param elements are the elements in the new list of constructs.
     */
    public ConstructList(final Iterable<T> elements)
    {
        Preconditions.checkNotNull(elements);

        this.elements = ImmutableList.copyOf(elements);
    }

    /**
     * This method non-destructively adds elements to this list.
     *
     * @param elements are the elements to add to this list.
     * @return a modified variant of this object.
     */
    public ConstructList<T> addAll(final Iterable<T> elements)
    {
        final List<T> xoo = this.asMutableList();

        for (T x : elements)
        {
            xoo.add(x);
        }

        return new ConstructList(xoo);
    }

    /**
     * This method non-destructively adds an element to this list.
     *
     * @param value is the element to add.
     * @return a modified variant of this object.
     */
    public ConstructList<T> add(final T value)
    {
        final List<T> xoo = this.asMutableList();

        xoo.add(value);

        return new ConstructList(xoo);
    }

    /**
     * This method retrieves the element at a given index.
     *
     * @param index is the index of the element to retrieve.
     * @return the element at the given index.
     * @throws IndexOutOfBoundsException if the index is out-of-bounds.
     */
    public T get(final int index)
    {
        return this.elements.get(index);
    }

    /**
     * This method computes the number of elements in this list.
     *
     * @return the size of this list.
     */
    public int size()
    {
        return elements.size();
    }

    /**
     * This method determines whether the is list is empty.
     *
     * @return true, iff this list is empty.
     */
    public boolean isEmpty()
    {
        return elements.isEmpty();
    }

    /**
     * This method creates and returns an iterator over this list.
     *
     * <p>
     * Note: The iterator cannot be used to modify this list.
     * </p>
     *
     * @return an iterator over this list.
     */
    @Override
    public ListIterator<T> iterator()
    {
        return elements.listIterator();
    }

    /**
     * This method creates a mutable copy of this list.
     *
     * @return a mutable copy of this list.
     */
    public List<T> asMutableList()
    {
        return new LinkedList<T>(elements);
    }

    /**
     * This method causes each element in this list to accept a given visitor.
     *
     * @param visitor is the visitor that will visit the elements.
     */
    public void accept(final IAstVisitor visitor)
    {
        Preconditions.checkNotNull(visitor);

        for (T element : elements)
        {
            element.accept(visitor);
        }
    }
}

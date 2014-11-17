package high.mackenzie.autumn.util.ds;

import autumn.util.ds.ImmutableList;
import autumn.util.ds.MutableList;
import com.google.common.base.Preconditions;
import java.util.Iterator;

/**
 * An instance of this class is a concrete implementation of the FunctionalList interface.
 *
 * @author Mackenzie High
 */
public final class FunctionalList<E>
{
    private final ImmutableList<E> immutable;

    private final int first;

    private final int last;

    private final AvlTree tree;

    public FunctionalList()
    {
        this(new AvlTree(), 0, 0);
    }

    private FunctionalList(final AvlTree tree,
                           final int first,
                           final int last)
    {
        this.tree = tree;
        this.first = first;
        this.last = last;
        this.immutable = new ConcreteImmutableList<E>(this);
    }

    /**
     * {@inheritDoc}
     */
    public MutableList<E> mutable()
    {
        return new ConcreteMutableList<E>(this);
    }

    /**
     * {@inheritDoc}
     */
    public ImmutableList<E> immutable()
    {
        return immutable;
    }

    /**
     * {@inheritDoc}
     */
    public int size()
    {
        return tree.size();
    }

    /**
     * {@inheritDoc}
     */
    public FunctionalList<E> add(final E value)
    {
        return addLast(value);
    }

    /**
     * {@inheritDoc}
     */
    public FunctionalList<E> add(final int index,
                                 final E value)
    {
        if (index == 0)
        {
            return addFirst(value);
        }

        if (index == size() - 1)
        {
            return addLast(value);
        }

        FunctionalList p = this;

        assert false;
        return null; // TODO
    }

    /**
     * {@inheritDoc}
     */
    public FunctionalList<E> addAll(final Iterable<? extends E> values)
    {
        Preconditions.checkNotNull(values);

        FunctionalList<E> p = this;

        for (E value : values)
        {
            p = p.add(value);
        }

        return p;
    }

    /**
     * {@inheritDoc}
     */
    public FunctionalList<E> addAll(int index,
                                    Iterable<? extends E> values)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    public FunctionalList<E> addFirst(E value)
    {
        return new FunctionalList<E>(tree.insert(first, value), first - 1, last);
    }

    /**
     * {@inheritDoc}
     */
    public FunctionalList<E> addLast(E value)
    {
        return new FunctionalList<E>(tree.insert(last, value), first, last + 1);
    }

    /**
     * {@inheritDoc}
     */
    public FunctionalList<E> remove(int index)
    {
        if (index == 0)
        {
            return removeFirst();
        }

        if (index == size() - 1)
        {
            return removeLast();
        }

        assert false;
        return null; // TODO
    }

    /**
     * {@inheritDoc}
     */
    public FunctionalList<E> removeFirst()
    {
        return new FunctionalList<E>(tree.delete(last), first, last - 1);
    }

    /**
     * {@inheritDoc}
     */
    public FunctionalList<E> removeLast()
    {
        return new FunctionalList<E>(tree.delete(last), first, last - 1);
    }

    /**
     * {@inheritDoc}
     */
    public FunctionalList<E> clear()
    {
        return new FunctionalList<E>();
    }

    /**
     * {@inheritDoc}
     */
    public FunctionalList<E> set(final int index,
                                 final E value)
    {
        return new FunctionalList<E>(tree.insert(first + index, value), first, last);
    }

    /**
     * {@inheritDoc}
     */
    public E get(final int index)
    {
        return (E) tree.find(first + index).value;
    }

    /**
     * {@inheritDoc}
     */
    public E first()
    {
        return (E) AvlTree.findMin(tree.root).value;
    }

    /**
     * {@inheritDoc}
     */
    public E last()
    {
        return (E) AvlTree.findMax(tree.root).value;
    }

    /**
     * {@inheritDoc}
     */
    public Iterator<E> iterator()
    {
        return tree.values();
    }
}

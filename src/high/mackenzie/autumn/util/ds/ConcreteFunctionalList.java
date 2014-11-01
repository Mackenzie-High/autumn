package high.mackenzie.autumn.util.ds;

import autumn.util.ds.FunctionalList;
import autumn.util.ds.ImmutableList;
import autumn.util.ds.MutableList;
import com.google.common.base.Preconditions;
import java.util.Iterator;

/**
 * An instance of this class is a concrete implementation of the FunctionalList interface.
 *
 * @author Mackenzie High
 */
public final class ConcreteFunctionalList<E>
        extends AbstractFunctionalCollection<E>
        implements FunctionalList<E>
{
    private final ImmutableList<E> immutable;

    private final int first;

    private final int last;

    private final AvlTree tree;

    public ConcreteFunctionalList()
    {
        this(new AvlTree(), 0, 0);
    }

    private ConcreteFunctionalList(final AvlTree tree,
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
    @Override
    public MutableList<E> mutable()
    {
        return new ConcreteMutableList<E>(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableList<E> immutable()
    {
        return immutable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size()
    {
        return tree.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FunctionalList<E> add(final E value)
    {
        return addLast(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
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
    @Override
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
    @Override
    public FunctionalList<E> addAll(int index,
                                    Iterable<? extends E> values)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FunctionalList<E> addFirst(E value)
    {
        return new ConcreteFunctionalList<E>(tree.insert(first, value), first - 1, last);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FunctionalList<E> addLast(E value)
    {
        return new ConcreteFunctionalList<E>(tree.insert(last, value), first, last + 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
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
    @Override
    public FunctionalList<E> removeFirst()
    {
        return new ConcreteFunctionalList<E>(tree.delete(last), first, last - 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FunctionalList<E> removeLast()
    {
        return new ConcreteFunctionalList<E>(tree.delete(last), first, last - 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FunctionalList<E> clear()
    {
        return new ConcreteFunctionalList<E>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FunctionalList<E> set(final int index,
                                 final E value)
    {
        return new ConcreteFunctionalList<E>(tree.insert(first + index, value), first, last);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E get(final int index)
    {
        return (E) tree.find(first + index).value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E first()
    {
        return (E) AvlTree.findMin(tree.root).value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E last()
    {
        return (E) AvlTree.findMax(tree.root).value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<E> iterator()
    {
        return tree.values();
    }
}

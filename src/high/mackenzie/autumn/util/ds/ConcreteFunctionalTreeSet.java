package high.mackenzie.autumn.util.ds;

import autumn.util.ds.FunctionalMap;
import autumn.util.ds.FunctionalSet;
import autumn.util.ds.FunctionalSortedSet;
import autumn.util.ds.ImmutableSortedSet;
import autumn.util.ds.MutableSortedSet;
import com.google.common.base.Preconditions;
import java.util.Iterator;

/**
 * An instance of this class is a persistent tree-based set data-structure.
 *
 * @author Mackenzie High
 */
public final class ConcreteFunctionalTreeSet<E>
        extends AbstractFunctionalCollection<E>
        implements FunctionalSortedSet<E>
{
    /**
     * By basing this set on a map, less new code is needed.
     */
    private final FunctionalMap<E, Object> map;

    /**
     * Sole Constructor.
     *
     * @param backer is the map that is used to implement this set.
     */
    public ConcreteFunctionalTreeSet(final FunctionalMap<E, Object> backer)
    {
        assert backer != null;

        this.map = backer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MutableSortedSet<E> mutable()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableSortedSet<E> immutable()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FunctionalSet<E> add(final E value)
    {
        return new ConcreteFunctionalTreeSet<E>(map.put(value, null));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FunctionalSet<E> addAll(Iterable<? extends E> values)
    {
        Preconditions.checkNotNull(values);

        FunctionalSet<E> p = this;

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
    public FunctionalSet<E> remove(E value)
    {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FunctionalSet<E> removeAll(Iterable<? extends E> values)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FunctionalSet<E> clear()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(final E value)
    {
        return map.containsKey(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size()
    {
        return map.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<E> iterator()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

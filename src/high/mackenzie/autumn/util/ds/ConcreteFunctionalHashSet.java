package high.mackenzie.autumn.util.ds;

import autumn.util.ds.FunctionalMap;
import autumn.util.ds.FunctionalSet;
import autumn.util.ds.ImmutableSet;
import autumn.util.ds.MutableSet;
import com.google.common.base.Preconditions;
import java.util.Iterator;

/**
 *
 * @author mackenzie
 */
public final class ConcreteFunctionalHashSet<E>
        extends AbstractFunctionalCollection<E>
        implements FunctionalSet<E>
{
    private final FunctionalMap map;

    public ConcreteFunctionalHashSet()
    {
        this(new ConcreteFunctionalHashMap());
    }

    private ConcreteFunctionalHashSet(final FunctionalMap map)
    {
        assert map != null;

        this.map = map;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<E> iterator()
    {
        return map.immutable().keySet().iterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MutableSet<E> mutable()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableSet<E> immutable()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FunctionalSet<E> add(final E value)
    {
        return new ConcreteFunctionalHashSet<E>(map.put(value, null));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FunctionalSet<E> addAll(final Iterable<? extends E> values)
    {
        Preconditions.checkNotNull(values);

        FunctionalSet p = this;

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
    public FunctionalSet<E> remove(final E value)
    {
        if (map.containsKey(value))
        {
            return new ConcreteFunctionalHashSet(map.remove(value));
        }
        else
        {
            return this;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FunctionalSet<E> removeAll(final Iterable<? extends E> values)
    {
        Preconditions.checkNotNull(values);

        FunctionalSet<E> p = this;

        for (E value : values)
        {
            p = p.remove(value);
        }

        return p;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FunctionalSet<E> clear()
    {
        return new ConcreteFunctionalHashSet<E>();
    }
}

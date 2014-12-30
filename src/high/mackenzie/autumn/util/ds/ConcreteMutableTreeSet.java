package high.mackenzie.autumn.util.ds;

import autumn.util.ds.ImmutableTreeSet;
import autumn.util.ds.MutableTreeMap;
import autumn.util.ds.MutableTreeSet;
import java.util.AbstractSet;
import java.util.Iterator;

/**
 * This class provides a concrete tree-based implementation of the MutableSet interface.
 *
 * @author Mackenzie High
 */
public final class ConcreteMutableTreeSet<E>
        extends AbstractSet<E>
        implements MutableTreeSet<E>
{
    /**
     * This Set object is really just a wrapper around this Map object.
     */
    private MutableTreeMap<E, ?> map;

    /**
     * Constructor.
     */
    public ConcreteMutableTreeSet()
    {
        this.map = new FunctionalTreeMap().mutable();
    }

    /**
     * Constructor.
     *
     * @param map is a map whose keys are the initial elements of this object.
     * @throws NullPointerException if map is null.
     */
    ConcreteMutableTreeSet(MutableTreeMap<E, ?> map)
    {
        this.map = map.immutable().mutable();

        assert this.map != map;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(final E value)
    {
        map.put(value, null);

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<E> iterator()
    {
        return map.keySet().iterator();
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
    public ImmutableTreeSet<E> immutable()
    {
        return new ConcreteImmutableTreeSet(map);
    }
}

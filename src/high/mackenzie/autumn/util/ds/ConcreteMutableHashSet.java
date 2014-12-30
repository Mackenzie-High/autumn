package high.mackenzie.autumn.util.ds;

import autumn.util.ds.ImmutableHashSet;
import autumn.util.ds.MutableHashMap;
import autumn.util.ds.MutableHashSet;
import com.google.common.base.Preconditions;
import java.util.AbstractSet;
import java.util.Iterator;

/**
 * This class provides a concrete hashing-based implementation of the MutableSet interface.
 *
 * @author Mackenzie High
 */
public final class ConcreteMutableHashSet<E>
        extends AbstractSet<E>
        implements MutableHashSet<E>
{
    /**
     * This Set object is really just a wrapper around this Map object.
     */
    private MutableHashMap<E, ?> map;

    /**
     * Constructor.
     */
    public ConcreteMutableHashSet()
    {
        this.map = new ConcreteMutableHashMap<E, Object>();
    }

    /**
     * Constructor.
     *
     * @param map is a map whose keys will be the initial elements in this object.
     * @throws NullPointerException if map is null.
     */
    ConcreteMutableHashSet(final MutableHashMap<E, ?> map)
    {
        Preconditions.checkNotNull(map);

        this.map = map.immutable().mutable();

        assert this.map != map;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableHashSet<E> immutable()
    {
        return new ConcreteImmutableHashSet<E>(map);
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
    public static void main(String[] args)
    {
        final ConcreteMutableHashSet<String> set = new ConcreteMutableHashSet<String>();

        set.add("A");
        set.add("B");
        set.add("C");
        set.add("A");

        set.remove("B");

        System.out.println(set);
    }
}

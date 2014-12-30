package high.mackenzie.autumn.util.ds;

import autumn.util.ds.ImmutableHashMap;
import autumn.util.ds.ImmutableHashSet;
import autumn.util.ds.MutableHashMap;
import autumn.util.ds.MutableHashSet;
import com.google.common.base.Preconditions;
import java.util.AbstractSet;
import java.util.Iterator;

/**
 * This class provides a concrete hashing-based implementation of the ImmutableHashSet interface.
 *
 * @author Mackenzie High
 */
public final class ConcreteImmutableHashSet<E>
        extends AbstractSet<E>
        implements ImmutableHashSet<E>
{
    /**
     * This Set object is really just a wrapper around this Map object.
     */
    private final ImmutableHashMap<E, ?> map;

    /**
     * Sole Constructor.
     *
     * @param map is a maps whose keys are the values to store in this set.
     * @throws NullPointerException if map is null.
     */
    ConcreteImmutableHashSet(final MutableHashMap<E, ?> map)
    {
        Preconditions.checkNotNull(map);

        this.map = map.immutable();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MutableHashSet<E> mutable()
    {
        return new ConcreteMutableHashSet<E>(map.mutable());
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
}

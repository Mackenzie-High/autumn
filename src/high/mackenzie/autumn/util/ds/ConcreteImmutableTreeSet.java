package high.mackenzie.autumn.util.ds;

import autumn.util.ds.ImmutableTreeMap;
import autumn.util.ds.ImmutableTreeSet;
import autumn.util.ds.MutableTreeMap;
import autumn.util.ds.MutableTreeSet;
import com.google.common.base.Preconditions;
import java.util.AbstractSet;
import java.util.Iterator;

/**
 * This class provides a concrete tree-based implementation of the ImmutableSortedSet interface.
 *
 * @author Mackenzie High
 */
public final class ConcreteImmutableTreeSet<E>
        extends AbstractSet<E>
        implements ImmutableTreeSet<E>
{
    /**
     * This Set object is really just a wrapper around this Map object.
     */
    private final ImmutableTreeMap<E, ?> map;

    /**
     * Sole Constructor.
     *
     * @param map is a maps whose keys are the values to store in this set.
     * @throws NullPointerException if map is null.
     */
    ConcreteImmutableTreeSet(final MutableTreeMap<E, ?> map)
    {
        Preconditions.checkNotNull(map);

        this.map = map.immutable();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MutableTreeSet<E> mutable()
    {
        return new ConcreteMutableTreeSet<E>(map.mutable());
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

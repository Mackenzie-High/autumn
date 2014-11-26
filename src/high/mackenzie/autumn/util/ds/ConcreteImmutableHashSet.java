package high.mackenzie.autumn.util.ds;

import autumn.util.ds.ImmutableMap;
import autumn.util.ds.ImmutableSet;
import autumn.util.ds.MutableMap;
import autumn.util.ds.MutableSet;
import java.util.AbstractSet;
import java.util.Iterator;

/**
 * This class provides a concrete hashing-based implementation of the ImmutableSet interface.
 *
 * @author Mackenzie High
 */
public final class ConcreteImmutableHashSet<E>
        extends AbstractSet<E>
        implements ImmutableSet<E>
{
    private final ImmutableMap<E, ?> map;

    public ConcreteImmutableHashSet(MutableMap<E, ?> map)
    {
        this.map = map.immutable();
    }

    @Override
    public MutableSet<E> mutable()
    {
        return new ConcreteMutableHashSet<E>(map.mutable());
    }

    @Override
    public Iterator<E> iterator()
    {
        return map.keySet().iterator();
    }

    @Override
    public int size()
    {
        return map.size();
    }
}

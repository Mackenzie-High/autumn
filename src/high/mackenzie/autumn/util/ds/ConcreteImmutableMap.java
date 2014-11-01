package high.mackenzie.autumn.util.ds;

import autumn.util.ds.FunctionalMap;
import autumn.util.ds.ImmutableMap;
import autumn.util.ds.MutableMap;
import java.util.AbstractMap;
import java.util.Set;

/**
 * This class provides a concrete implementation of the ImmutableMap interface.
 *
 * @author Mackenzie High
 */
public final class ConcreteImmutableMap<K, V>
        extends AbstractMap<K, V>
        implements ImmutableMap<K, V>
{
    private final FunctionalMap<K, V> map;

    public ConcreteImmutableMap(final FunctionalMap<K, V> map)
    {
        this.map = map;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MutableMap<K, V> mutable()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FunctionalMap<K, V> functional()
    {
        return map;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Entry<K, V>> entrySet()
    {
        return null; // TODO
    }
}

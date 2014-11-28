package autumn.util.ds.specific;

import autumn.util.ds.MutableMap;

/**
 * An instance of this interface is a mutable hash-based map data-structure.
 *
 * @author Mackenzie High
 */
public interface MutableHashMap<K, V>
        extends MutableMap<K, V>
{
    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableHashMap<K, V> immutable();
}

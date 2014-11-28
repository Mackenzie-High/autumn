package autumn.util.ds.specific;

import autumn.util.ds.ImmutableMap;

/**
 * An instance of this interface is an immutable hash-based map data-structure.
 *
 * @author Mackenzie High
 */
public interface ImmutableHashMap<K, V>
        extends ImmutableMap<K, V>
{
    /**
     * {@inheritDoc}
     */
    @Override
    public MutableHashMap<K, V> mutable();
}

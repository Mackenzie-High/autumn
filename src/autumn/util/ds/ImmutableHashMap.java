package autumn.util.ds;

import java.util.Map;

/**
 * An instance of this interface is an immutable hash-based map data-structure.
 *
 * @author Mackenzie High
 */
public interface ImmutableHashMap<K, V>
        extends Map<K, V>
{
    /**
     * {@inheritDoc}
     */
    public MutableHashMap<K, V> mutable();
}

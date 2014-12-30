package autumn.util.ds;

import java.util.Map;

/**
 * An instance of this interface is a mutable hash-based map data-structure.
 *
 * @author Mackenzie High
 */
public interface MutableHashMap<K, V>
        extends Map<K, V>
{
    /**
     * {@inheritDoc}
     */
    public ImmutableHashMap<K, V> immutable();
}

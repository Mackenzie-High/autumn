package autumn.util.ds;

import java.util.Map;

/**
 * An instance of this interface is a mutable tree-based map data-structure.
 *
 * @author Mackenzie High
 */
public interface MutableTreeMap<K, V>
        extends Map<K, V>
{
    /**
     * {@inheritDoc}
     */
    public ImmutableTreeMap<K, V> immutable();
}

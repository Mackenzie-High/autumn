package autumn.util.ds;

import java.util.Map;

/**
 * An instance of this interface is an immutable tree-based map data-structure.
 *
 * @author Mackenzie High
 */
public interface ImmutableTreeMap<K, V>
        extends Map<K, V>
{
    /**
     * {@inheritDoc}
     */
    public MutableTreeMap<K, V> mutable();
}

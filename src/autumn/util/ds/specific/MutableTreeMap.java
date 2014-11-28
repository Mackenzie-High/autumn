package autumn.util.ds.specific;

import autumn.util.ds.MutableNavigableMap;

/**
 * An instance of this interface is a mutable tree-based map data-structure.
 *
 * @author Mackenzie High
 */
public interface MutableTreeMap<K, V>
        extends MutableNavigableMap<K, V>
{
    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableTreeMap<K, V> immutable();
}

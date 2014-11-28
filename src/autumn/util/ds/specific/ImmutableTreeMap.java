package autumn.util.ds.specific;

import autumn.util.ds.ImmutableNavigableMap;

/**
 * An instance of this interface is an immutable tree-based map data-structure.
 *
 * @author Mackenzie High
 */
public interface ImmutableTreeMap<K, V>
        extends ImmutableNavigableMap<K, V>
{
    /**
     * {@inheritDoc}
     */
    @Override
    public MutableTreeMap<K, V> mutable();
}

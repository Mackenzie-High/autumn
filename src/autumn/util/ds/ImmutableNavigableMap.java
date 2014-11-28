package autumn.util.ds;

import java.util.NavigableMap;

/**
 * An instance of this class is a navigable immutable map data-structure.
 *
 * @author Mackenzie High
 */
public interface ImmutableNavigableMap<K, V>
        extends ImmutableSortedMap<K, V>,
                NavigableMap<K, V>
{
    /**
     * This method creates a mutable copy of this object.
     *
     * @return a mutable copy of this object.
     */
    @Override
    public MutableNavigableMap<K, V> mutable();
}

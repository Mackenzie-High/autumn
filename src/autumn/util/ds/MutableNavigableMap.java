package autumn.util.ds;

import java.util.NavigableMap;

/**
 * An instance of this class is a navigable mutable map data-structure.
 *
 * @author Mackenzie High
 */
public interface MutableNavigableMap<K, V>
        extends MutableSortedMap<K, V>,
                NavigableMap<K, V>
{
    /**
     * This method creates an immutable copy of this object.
     *
     * @return an immutable copy of this object.
     */
    @Override
    public ImmutableNavigableMap<K, V> immutable();
}

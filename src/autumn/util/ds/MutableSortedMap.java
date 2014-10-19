package autumn.util.ds;

import java.util.NavigableMap;

/**
 * An instance of this interface is a mutable sorted map data-structure.
 *
 * @author Mackenzie High
 */
public interface MutableSortedMap<K, V>
        extends MutableMap<K, V>,
                NavigableMap<K, V>
{
    /**
     * This method creates an immutable copy of this object.
     *
     * @return an immutable copy of this object.
     */
    @Override
    public ImmutableSortedMap<K, V> immutable();
}
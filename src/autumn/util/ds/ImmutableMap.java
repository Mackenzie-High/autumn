package autumn.util.ds;

import java.util.Map;

/**
 * An instance of this method is an immutable map data-structure.
 *
 * @author Mackenzie High
 */
public interface ImmutableMap<K, V>
        extends Map<K, V>
{
    /**
     * This method creates a new mutator that can be used to create a modified copy of this object.
     *
     * @return a new mutator object.
     */
    public MutableMap<K, V> mutable();
}

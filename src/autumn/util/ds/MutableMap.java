package autumn.util.ds;

import java.util.Map;

/**
 * An instance of this interface is a mutable map data-structure.
 *
 * @author Mackenzie High
 */
public interface MutableMap<K, V>
        extends Map<K, V>
{
    /**
     * This method creates an immutable copy of this object.
     *
     * @return an immutable copy of this object.
     */
    public ImmutableMap<K, V> immutable();

    /**
     * This method creates an functional copy of this object.
     *
     * @return a functional copy of this object.
     */
    public FunctionalMap<K, V> functional();
}

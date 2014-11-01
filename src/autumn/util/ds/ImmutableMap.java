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
     * This method creates an mutable copy of this object.
     *
     * @return a mutable copy of this object.
     */
    public MutableMap<K, V> mutable();

    /**
     * This method creates an functional copy of this object.
     *
     * @return a functional copy of this object.
     */
    public FunctionalMap<K, V> functional();
}

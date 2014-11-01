package autumn.util.ds;

/**
 * An instance of this interface is a sorted persistent map data-structure.
 *
 * @author Mackenzie High
 */
public interface FunctionalSortedMap<K, V>
        extends FunctionalMap<K, V>
{
    /**
     * This method creates a mutable copy of this object.
     *
     * @return a mutable copy of this object.
     */
    @Override
    public MutableSortedMap<K, V> mutable();

    /**
     * This method creates an immutable copy of this object.
     *
     * @return an immutable copy of this object.
     */
    @Override
    public ImmutableMap<K, V> immutable();
}

package autumn.util.ds;

/**
 * An instance of this class is a sorted immutable map data-structure.
 *
 * @author Mackenzie High
 */
public interface ImmutableSortedMap<K, V>
        extends ImmutableMap<K, V>
{
    /**
     * This method creates an mutable copy of this object.
     *
     * @return a mutable copy of this object.
     */
    @Override
    public MutableSortedMap<K, V> mutable();

    /**
     * This method creates an functional copy of this object.
     *
     * @return a functional copy of this object.
     */
    @Override
    public FunctionalSortedMap<K, V> functional();
}

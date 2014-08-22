package autumn.util.ds;

/**
 * An instance of this class is a sorted immutable map data-structure.
 *
 * @author Mackenzie High
 */
public interface ImmutableNavigableMap<K, V>
        extends ImmutableMap<K, V>
{
    /**
     * This method creates a new mutator that can be used to create a modified copy of this object.
     *
     * @return a new mutator object.
     */
    @Override
    public MutableNavigableMap<K, V> mutable();
}

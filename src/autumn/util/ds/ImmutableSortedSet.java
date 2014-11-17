package autumn.util.ds;

/**
 * An instance of this interface is a sorted immutable set data-structure.
 *
 * @author Mackenzie High
 */
public interface ImmutableSortedSet<E>
        extends ImmutableSet<E>
{
    /**
     * This method creates an mutable copy of this object.
     *
     * @return a mutable copy of this object.
     */
    @Override
    public MutableSortedSet<E> mutable();
}

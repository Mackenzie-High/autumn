package autumn.util.ds;

/**
 * An instance of this interface is a sorted immutable set data-structure.
 *
 * @author Mackenzie High
 */
public interface ImmutableNavigableSet<E>
        extends ImmutableSet<E>
{
    /**
     * This method creates a new mutator that can be used to create a modified copy of this object.
     *
     * @return a new mutator object.
     */
    @Override
    public MutableNavigableSet<E> mutable();
}

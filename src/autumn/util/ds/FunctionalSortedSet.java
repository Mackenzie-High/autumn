package autumn.util.ds;

/**
 * An instance of this interface is a sorted persistent set data-structure.
 *
 * @author Mackenzie High
 */
public interface FunctionalSortedSet<E>
        extends FunctionalSet<E>
{
    /**
     * This method creates a mutable copy of this object.
     *
     * @return a mutable copy of this object.
     */
    @Override
    public MutableSortedSet<E> mutable();

    /**
     * This method creates an immutable copy of this object.
     *
     * @return an immutable copy of this object.
     */
    @Override
    public ImmutableSortedSet<E> immutable();
}

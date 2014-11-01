package autumn.util.ds;

import java.util.Set;

/**
 * An instance of this interface is an immutable set data-structure.
 *
 * @author Mackenzie High
 */
public interface ImmutableSet<E>
        extends ImmutableCollection<E>,
                Set<E>
{
    /**
     * This method creates an mutable copy of this object.
     *
     * @return a mutable copy of this object.
     */
    @Override
    public MutableSet<E> mutable();

    /**
     * This method creates an functional copy of this object.
     *
     * @return a functional copy of this object.
     */
    @Override
    public FunctionalSet<E> functional();
}

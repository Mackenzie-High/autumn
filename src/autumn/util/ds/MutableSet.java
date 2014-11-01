package autumn.util.ds;

import java.util.Set;

/**
 * An instance of this interface is a mutable set data-structure.
 *
 * @author Mackenzie High
 */
public interface MutableSet<E>
        extends MutableCollection<E>,
                Set<E>
{
    /**
     * This method creates an immutable copy of this object.
     *
     * @return an immutable copy of this object.
     */
    @Override
    public ImmutableSet<E> immutable();

    /**
     * This method creates an functional copy of this object.
     *
     * @return a functional copy of this object.
     */
    @Override
    public FunctionalSet<E> functional();
}

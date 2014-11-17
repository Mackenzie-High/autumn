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
}

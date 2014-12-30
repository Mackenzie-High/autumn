package autumn.util.ds;

import java.util.Set;

/**
 * An instance of this interface is a mutable hash-based set data-structure.
 *
 * @author Mackenzie High
 */
public interface MutableHashSet<E>
        extends Set<E>
{
    /**
     * {@inheritDoc}
     */
    public ImmutableHashSet<E> immutable();
}

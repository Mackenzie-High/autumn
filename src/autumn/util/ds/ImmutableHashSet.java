package autumn.util.ds;

import java.util.Set;

/**
 * An instance of this interface is an immutable hash-based set data-structure.
 *
 * @author Mackenzie High
 */
public interface ImmutableHashSet<E>
        extends Set<E>
{
    /**
     * {@inheritDoc}
     */
    public MutableHashSet<E> mutable();
}

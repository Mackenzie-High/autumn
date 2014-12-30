package autumn.util.ds;

import java.util.Set;

/**
 * An instance of this interface is a mutable tree-based set data-structure.
 *
 * @author Mackenzie High
 */
public interface MutableTreeSet<E>
        extends Set<E>
{
    /**
     * {@inheritDoc}
     */
    public ImmutableTreeSet<E> immutable();
}

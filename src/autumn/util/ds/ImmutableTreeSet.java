package autumn.util.ds;

import java.util.Set;

/**
 * An instance of this interface is an immutable tree-based set data-structure.
 *
 * @author Mackenzie High
 */
public interface ImmutableTreeSet<E>
        extends Set<E>
{
    /**
     * {@inheritDoc}
     */
    public MutableTreeSet<E> mutable();
}

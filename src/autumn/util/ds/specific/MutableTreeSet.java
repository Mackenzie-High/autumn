package autumn.util.ds.specific;

import autumn.util.ds.MutableNavigableSet;

/**
 * An instance of this interface is a mutable tree-based set data-structure.
 *
 * @author Mackenzie High
 */
public interface MutableTreeSet<E>
        extends MutableNavigableSet<E>
{
    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableTreeSet<E> immutable();
}

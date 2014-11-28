package autumn.util.ds.specific;

import autumn.util.ds.ImmutableNavigableSet;

/**
 * An instance of this interface is an immutable tree-based set data-structure.
 *
 * @author Mackenzie High
 */
public interface ImmutableTreeSet<E>
        extends ImmutableNavigableSet<E>
{
    /**
     * {@inheritDoc}
     */
    @Override
    public MutableTreeSet<E> mutable();
}

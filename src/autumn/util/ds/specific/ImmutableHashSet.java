package autumn.util.ds.specific;

import autumn.util.ds.ImmutableSet;

/**
 * An instance of this interface is an immutable hash-based set data-structure.
 *
 * @author Mackenzie High
 */
public interface ImmutableHashSet<E>
        extends ImmutableSet<E>
{
    /**
     * {@inheritDoc}
     */
    @Override
    public MutableHashSet<E> mutable();
}

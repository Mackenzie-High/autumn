package autumn.util.ds.specific;

import autumn.util.ds.MutableSet;

/**
 * An instance of this interface is a mutable hash-based set data-structure.
 *
 * @author Mackenzie High
 */
public interface MutableHashSet<E>
        extends MutableSet<E>
{
    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableHashSet<E> immutable();
}

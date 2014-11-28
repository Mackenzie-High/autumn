package autumn.util.ds.specific;

import autumn.util.ds.MutableDeque;
import autumn.util.ds.MutableList;

/**
 * An instance of this interface is a mutable list data-structure.
 *
 * @author Mackenzie High
 */
public interface MutableSequence<E>
        extends MutableList<E>,
                MutableDeque<E>
{
    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableSequence<E> immutable();
}

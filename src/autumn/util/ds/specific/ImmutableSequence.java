package autumn.util.ds.specific;

import autumn.util.ds.ImmutableDeque;
import autumn.util.ds.ImmutableList;

/**
 * An instance of this interface is an immutable list data-structure.
 *
 * @author Mackenzie High
 */
public interface ImmutableSequence<E>
        extends ImmutableList<E>,
                ImmutableDeque<E>
{
    /**
     * {@inheritDoc}
     */
    @Override
    public MutableSequence<E> mutable();
}

package autumn.util.ds;

import java.util.Deque;
import java.util.List;

/**
 * An instance of this interface is an immutable list data-structure.
 *
 * @author Mackenzie High
 */
public interface ImmutableSequence<E>
        extends List<E>,
                Deque<E>
{
    /**
     * {@inheritDoc}
     */
    public MutableSequence<E> mutable();
}

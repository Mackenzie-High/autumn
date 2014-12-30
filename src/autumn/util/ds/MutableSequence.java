package autumn.util.ds;

import java.util.Deque;
import java.util.List;

/**
 * An instance of this interface is a mutable list data-structure.
 *
 * @author Mackenzie High
 */
public interface MutableSequence<E>
        extends List<E>,
                Deque<E>
{
    /**
     * {@inheritDoc}
     */
    public ImmutableSequence<E> immutable();
}

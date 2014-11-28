package autumn.util.ds;

import java.util.Deque;

/**
 * An instance of this class is a mutable deque data-structure.
 *
 * @author Mackenzie High
 */
public interface MutableDeque<E>
        extends MutableCollection<E>,
                Deque<E>
{
    /**
     * This method creates an immutable copy of this object.
     *
     * @return an immutable copy of this object.
     */
    @Override
    public ImmutableDeque<E> immutable();
}

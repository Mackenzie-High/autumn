package autumn.util.ds;

import java.util.Deque;

/**
 * An instance of this class is an immutable deque data-structure.
 *
 * @author Mackenzie High
 */
public interface ImmutableDeque<E>
        extends ImmutableCollection<E>,
                Deque<E>
{
    /**
     * This method creates a mutable copy of this object.
     *
     * @return a mutable copy of this object.
     */
    @Override
    public MutableDeque<E> mutable();
}

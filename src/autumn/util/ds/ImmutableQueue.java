package autumn.util.ds;

import java.util.Queue;

/**
 * An instance of this class is an immutable queue data-structure.
 *
 * @author Mackenzie High
 */
public interface ImmutableQueue<E>
        extends ImmutableCollection<E>,
                Queue<E>
{
    /**
     * This method creates a mutable copy of this object.
     *
     * @return a mutable copy of this object.
     */
    @Override
    public MutableQueue<E> mutable();
}

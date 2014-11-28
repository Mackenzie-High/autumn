package autumn.util.ds;

import java.util.Queue;

/**
 * An instance of this class is a mutable queue data-structure.
 *
 * @author Mackenzie High
 */
public interface MutableQueue<E>
        extends MutableCollection<E>,
                Queue<E>
{
    /**
     * This method creates a immutable copy of this object.
     *
     * @return an immutable copy of this object.
     */
    @Override
    public ImmutableQueue<E> immutable();
}

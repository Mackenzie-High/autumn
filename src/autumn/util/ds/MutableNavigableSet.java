package autumn.util.ds;

import java.util.NavigableSet;

/**
 * An instance of this interface is a mutable sorted set data-structure.
 *
 * @author Mackenzie High
 */
public interface MutableNavigableSet<E>
        extends MutableSet<E>,
                NavigableSet<E>
{
    /**
     * This method creates an immutable copy of this object.
     *
     * @return an immutable copy of this object.
     */
    @Override
    public ImmutableNavigableSet<E> immutable();
}

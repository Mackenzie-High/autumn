package autumn.util.ds;

import java.util.NavigableSet;

/**
 * An instance of this class is a navigable mutable set data-structure.
 *
 * @author Mackenzie High
 */
public interface MutableNavigableSet<E>
        extends MutableSortedSet<E>,
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

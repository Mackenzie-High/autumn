package autumn.util.ds;

import java.util.NavigableSet;

/**
 * An instance of this class is a navigable immutable set data-structure.
 *
 * @author Mackenzie High
 */
public interface ImmutableNavigableSet<E>
        extends ImmutableSortedSet<E>,
                NavigableSet<E>
{
    /**
     * This method creates a mutable copy of this object.
     *
     * @return a mutable copy of this object.
     */
    @Override
    public MutableNavigableSet<E> mutable();
}

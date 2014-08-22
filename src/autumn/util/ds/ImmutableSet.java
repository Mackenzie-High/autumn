package autumn.util.ds;

import java.util.Set;

/**
 * An instance of this interface is an immutable set data-structure.
 *
 * @author Mackenzie High
 */
public interface ImmutableSet<E>
        extends ImmutableCollection<E>,
                Set<E>
{
    /**
     * This method creates a new mutator that can be used to create a modified copy of this object.
     *
     * @return a new mutator object.
     */
    @Override
    public MutableSet<E> mutable();
}

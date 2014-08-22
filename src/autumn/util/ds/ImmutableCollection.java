package autumn.util.ds;

import java.util.Collection;

/**
 * An instance of this interface is an immutable collection of values.
 *
 * @author Mackenzie High
 */
public interface ImmutableCollection<E>
        extends Collection<E>
{
    /**
     * This method creates a new mutator that can be used to create a modified copy of this object.
     *
     * @return a new mutator object.
     */
    public MutableCollection<E> mutable();
}

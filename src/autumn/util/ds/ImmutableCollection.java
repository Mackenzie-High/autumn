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
     * This method creates an mutable copy of this object.
     *
     * @return a mutable copy of this object.
     */
    public MutableCollection<E> mutable();
}

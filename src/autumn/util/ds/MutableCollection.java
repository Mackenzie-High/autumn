package autumn.util.ds;

import java.util.Collection;

/**
 * An instance of this class is a mutable collection of values.
 *
 * @author Mackenzie High
 */
public interface MutableCollection<E>
        extends Collection<E>
{
    /**
     * This method creates an immutable copy of this object.
     *
     * @return an immutable copy of this object.
     */
    public ImmutableCollection<E> immutable();
}

package autumn.util.ds;

import java.util.List;

/**
 * An instance of this interface is a mutable list data-structure.
 *
 * @author Mackenzie High
 */
public interface MutableList<E>
        extends MutableCollection<E>,
                List<E>
{
    /**
     * This method creates an immutable copy of this object.
     *
     * @return an immutable copy of this object.
     */
    @Override
    public ImmutableList<E> immutable();

    /**
     * This method creates an functional copy of this object.
     *
     * @return a functional copy of this object.
     */
    @Override
    public FunctionalList<E> functional();
}

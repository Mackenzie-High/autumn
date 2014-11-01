package autumn.util.ds;

import java.util.List;

/**
 * An instance of this class is an immutable list that supports efficient mutation.
 *
 * @author Nackenzie High
 */
public interface ImmutableList<E>
        extends ImmutableCollection<E>,
                List<E>
{
    /**
     * This method creates an mutable copy of this object.
     *
     * @return a mutable copy of this object.
     */
    @Override
    public MutableList<E> mutable();

    /**
     * This method creates an functional copy of this object.
     *
     * @return a functional copy of this object.
     */
    @Override
    public FunctionalList<E> functional();
}

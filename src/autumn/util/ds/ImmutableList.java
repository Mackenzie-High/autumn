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
     * This method creates a new mutator that can be used to create a modified copy of this object.
     *
     * @return a new mutator object.
     */
    @Override
    public MutableList<E> mutable();
}

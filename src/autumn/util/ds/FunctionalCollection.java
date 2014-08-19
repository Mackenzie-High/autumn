package autumn.util.ds;

import java.util.Collection;

/**
 * An instance of this interface is a functional implementation of the Collection interface.
 *
 * @author Mackenzie High
 */
public interface FunctionalCollection<E>
{
    /**
     * An instance of this class is a mutable-list backed by a functional-list.
     */
    public static interface Mutator<E>
            extends Collection<E>
    {
        // Pass
    }

    /**
     * This method creates a new mutator that can be used to create a modified copy of this object.
     *
     * @return a new Mutator object.
     */
    public Mutator mutator();
}

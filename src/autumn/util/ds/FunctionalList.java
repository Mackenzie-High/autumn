package autumn.util.ds;

import java.util.List;

/**
 * An instance of this class is an immutable list that supports efficient mutation.
 *
 * @author Nackenzie High
 */
public interface FunctionalList<E>
        extends List<E>
{
    /**
     * An instance of this class is a mutable-list backed by a functional-list.
     */
    public static interface Mutator<E>
            extends List<E>
    {
        // Pass
    }

    /**
     * This method creates a new mutator that can be used to create a modified copy of this object.
     *
     * @return a new Mutator object.
     */
    public Mutator newMutator();
}

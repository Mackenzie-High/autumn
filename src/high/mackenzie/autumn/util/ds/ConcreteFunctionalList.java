package high.mackenzie.autumn.util.ds;

import autumn.util.ds.FunctionalList;
import java.util.AbstractList;

/**
 * An instance of this class is a concrete implementation of the FunctionalList interface.
 *
 * @author Mackenzie High
 */
public final class ConcreteFunctionalList<E>
        extends AbstractList<E>
        implements FunctionalList<E>
{
    @Override
    public E get(int index)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int size()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Mutator newMutator()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

package autumn.lang.internals;

import autumn.lang.StaticFunctor;
import autumn.lang.TypedFunctor;
import com.google.common.base.Preconditions;

/**
 * This class provides a partial implementation of the StaticFunctor interface.
 *
 * @author Mackenzie High
 */
public abstract class AbstractStaticFunctor
        implements StaticFunctor
{
    private final TypedFunctor inner;

    /**
     * Sole Constructor.
     *
     * @param inner is the inner() functor.
     * @throws NullPointerException.
     */
    public AbstractStaticFunctor(final TypedFunctor inner)
    {
        Preconditions.checkNotNull(inner);

        this.inner = inner;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final TypedFunctor inner()
    {
        return inner;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void apply(ArgumentStack stack)
            throws Throwable
    {
        inner().apply(stack);
    }
}

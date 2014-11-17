package autumn.lang.internals;

import autumn.lang.StaticFunctor;
import autumn.lang.TypedFunctor;
import autumn.util.Strings;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.List;

/**
 * This class provides a partial implementation of the StaticFunctor interface.
 *
 * @author Mackenzie High
 */
public abstract class AbstractStaticFunctor
        extends AbstractTypedFunctor
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

    /**
     * {@inheritDoc}
     */
    @Override
    public final String toString()
    {
        final StringBuilder string = new StringBuilder();

        final List<String> params = Lists.newLinkedList();

        for (Class param : parameterTypes())
        {
            params.add(param.getSimpleName());
        }

        string.append("functor ");
        string.append(Strings.str(params, "(", ", ", ")"));
        string.append(" : ");
        string.append(returnType().getSimpleName());

        return string.toString();
    }
}

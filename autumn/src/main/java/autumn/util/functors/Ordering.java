package autumn.util.functors;

import autumn.lang.TypedFunctor;
import autumn.lang.exceptions.CheckedException;
import autumn.lang.internals.annotations.FunctorDefinition;
import com.google.common.collect.ImmutableList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author mackenzie
 */
@FunctorDefinition
public class Ordering
        extends Function2
        implements Comparator<Object>
{
    /**
     * Sole Constructor.
     *
     * @param inner is the functor that provides the actual functionality.
     */
    public Ordering(final TypedFunctor inner)
    {
        super(inner);
    }

    /**
     * This method invokes the inner functor.
     *
     * @param arg1 is the 1st argument.
     * @param arg2 is the 2nd argument.
     */
    @Override
    public Integer invoke(final Object arg1,
                          final Object arg2)
            throws Throwable
    {
        return (Integer) super.invoke(arg1, arg2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Class> parameterTypes()
    {
        return ImmutableList.<Class>of(Object.class, Object.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class returnType()
    {
        return Integer.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(final Object arg1,
                       final Object arg2)
    {
        try
        {
            return invoke(arg1, arg2);
        }
        catch (Throwable ex)
        {
            throw new CheckedException(ex);
        }
    }
}

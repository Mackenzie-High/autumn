package autumn.util.functors;

import autumn.lang.TypedFunctor;
import autumn.lang.exceptions.CheckedException;
import autumn.lang.internals.AbstractDefinedFunctor;
import autumn.lang.internals.ArgumentStack;
import com.google.common.collect.ImmutableList;
import java.util.List;

/**
 * <code>
 * functor Action () : void
 * </code>
 *
 * @author Mackenzie High
 */
public class Action
        extends AbstractDefinedFunctor
        implements Runnable
{
    /**
     * Sole Constructor.
     *
     * @param inner is the functor that provides the actual functionality.
     */
    public Action(final TypedFunctor functor)
    {
        super(functor);
    }

    /**
     * This method invokes the inner functor.
     */
    public void invoke()
            throws Throwable
    {
        final ArgumentStack stack = ArgumentStack.getThreadStack();

        stack.clear();

        inner().apply(stack);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Class> parameterTypes()
    {
        return ImmutableList.<Class>of();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class returnType()
    {
        return void.class;
    }

    /**
     * This method invokes the inner functor.
     */
    @Override
    public void run()
    {
        try
        {
            invoke();
        }
        catch (Throwable t)
        {
            throw new CheckedException(t);
        }
    }
}

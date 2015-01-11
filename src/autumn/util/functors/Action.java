package autumn.util.functors;

import autumn.lang.TypedFunctor;
import autumn.lang.exceptions.CheckedException;
import autumn.lang.internals.AbstractDefinedFunctor;
import autumn.lang.internals.ArgumentStack;
import com.google.common.collect.ImmutableList;
import java.util.List;

/**
 * functor Action () : void
 *
 * @author Mackenzie High
 */
public class Action
        extends AbstractDefinedFunctor
        implements Runnable
{
    public Action(final TypedFunctor functor)
    {
        super(functor);
    }

    @Override
    public List<Class> parameterTypes()
    {
        return ImmutableList.<Class>of();
    }

    @Override
    public Class returnType()
    {
        return void.class;
    }

    public void invoke()
            throws Throwable
    {
        final ArgumentStack stack = ArgumentStack.getThreadStack();

        stack.clear();

        inner().apply(stack);
    }

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

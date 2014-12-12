package autumn.util.functors;

import autumn.lang.TypedFunctor;
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
}

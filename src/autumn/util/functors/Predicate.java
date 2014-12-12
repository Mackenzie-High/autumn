package autumn.util.functors;

import autumn.lang.TypedFunctor;
import autumn.lang.internals.AbstractDefinedFunctor;
import autumn.lang.internals.ArgumentStack;
import com.google.common.collect.ImmutableList;
import java.util.List;

/**
 * functor Predicate (value : Object) : boolean
 *
 * @author Mackenzie High
 */
public class Predicate
        extends AbstractDefinedFunctor
{
    public Predicate(final TypedFunctor functor)
    {
        super(functor);
    }

    @Override
    public List<Class> parameterTypes()
    {
        return ImmutableList.<Class>of(Object.class);
    }

    @Override
    public Class returnType()
    {
        return boolean.class;
    }

    public boolean invoke(final Object argument)
            throws Throwable
    {
        final ArgumentStack stack = ArgumentStack.getThreadStack();

        stack.push(argument);

        inner().apply(stack);

        final boolean result = stack.peekZ();

        stack.clear();

        return result;
    }
}

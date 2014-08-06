package autumn.util.functors;

import autumn.lang.Functor;
import autumn.lang.internals.AbstractStaticFunctor;
import autumn.lang.internals.ArgumentStack;
import com.google.common.collect.ImmutableList;
import java.util.List;

/**
 *
 * @author mackenzie
 */
public final class Predicate
        extends AbstractStaticFunctor
{
    public Predicate(final Functor functor)
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

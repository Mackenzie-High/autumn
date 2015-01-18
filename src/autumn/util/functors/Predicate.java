package autumn.util.functors;

import autumn.lang.TypedFunctor;
import autumn.lang.internals.AbstractDefinedFunctor;
import autumn.lang.internals.ArgumentStack;
import autumn.lang.internals.annotations.FunctorDefinition;
import com.google.common.collect.ImmutableList;
import java.util.List;

/**
 * functor Predicate (value : Object) : boolean
 *
 * @author Mackenzie High
 */
@FunctorDefinition
public class Predicate
        extends AbstractDefinedFunctor
{
    /**
     * Sole Constructor.
     *
     * @param inner is the functor that provides the actual functionality.
     */
    public Predicate(final TypedFunctor functor)
    {
        super(functor);
    }

    /**
     * This method invokes the inner functor.
     *
     * @param argument is the value to test.
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Class> parameterTypes()
    {
        return ImmutableList.<Class>of(Object.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class returnType()
    {
        return boolean.class;
    }
}

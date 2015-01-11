package autumn.util.functors;

import autumn.lang.TypedFunctor;
import autumn.lang.internals.AbstractDefinedFunctor;
import autumn.lang.internals.ArgumentStack;
import com.google.common.collect.ImmutableList;
import java.util.List;

/**
 *
 * @author mackenzie
 */
public class Function1
        extends AbstractDefinedFunctor
{
    /**
     * Sole Constructor.
     *
     * @param inner is the functor that provides the actual functionality.
     */
    public Function1(final TypedFunctor inner)
    {
        super(inner);
    }

    /**
     * This method invokes the inner functor.
     *
     * @param arg1 is the 1st argument.
     */
    public Object invoke(final Object arg1)
            throws Throwable
    {
        // Get the stack that is associated with this thread.
        final ArgumentStack stack = ArgumentStack.getThreadStack();

        // Push the argument onto the argument-stack.
        stack.push(arg1);

        // Invoke the inner functor.
        inner().apply(stack);

        // Pop the result off of the argument-stack.
        // Also, clear the stack in order to prevent unexpected bugs.
        final Object result = stack.popResult();

        // Return the result of invoking the inner functor.
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
        return Object.class;
    }
}

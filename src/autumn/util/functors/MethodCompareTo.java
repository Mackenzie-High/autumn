package autumn.util.functors;

import autumn.lang.TypedFunctor;
import autumn.lang.internals.AbstractDefinedFunctor;
import autumn.lang.internals.ArgumentStack;
import com.google.common.collect.ImmutableList;
import java.util.List;

/**
 * functor MethodCompareTo (left : Object, right : Object) : int
 *
 * @author Mackenzie High
 */
public class MethodCompareTo
        extends AbstractDefinedFunctor
{
    /**
     * Sole Constructor.
     *
     * @param inner is the functor that provides the actual functionality.
     */
    public MethodCompareTo(final TypedFunctor inner)
    {
        super(inner);
    }

    /**
     * This method invokes the inner functor.
     *
     * @param self is the left-operand.
     * @param other is the right-operand.
     */
    public void invoke(final Object self,
                       final Object other)
            throws Throwable
    {
        // Get the stack that is associated with this thread.
        final ArgumentStack stack = ArgumentStack.getThreadStack();

        // Push the arguments onto the argument-stack.
        stack.push(self);
        stack.push(other);

        // Invoke the inner functor.
        inner().apply(stack);

        // Clear the stack in order to prevent unexpected bugs.
        stack.clear();
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
        return int.class;
    }
}
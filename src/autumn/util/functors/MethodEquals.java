package autumn.util.functors;

import autumn.lang.TypedFunctor;
import autumn.lang.internals.AbstractStaticFunctor;
import autumn.lang.internals.ArgumentStack;
import com.google.common.collect.ImmutableList;
import java.util.List;

/**
 * An instance of this functor-type is a functor that used to implement an equals method.
 *
 * <p>
 * <b>Signature:</b>
 * <code> MethodEquals (self : Object, other : Object) => boolean </code>
 * </p>
 *
 * @author Mackenzie High
 */
public final class MethodEquals
        extends AbstractStaticFunctor
{
    /**
     * Sole Constructor.
     *
     * @param inner is the functor that provides the actual functionality.
     */
    public MethodEquals(final TypedFunctor inner)
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
        return boolean.class;
    }
}

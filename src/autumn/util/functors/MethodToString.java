package autumn.util.functors;

import autumn.lang.Functor;
import autumn.lang.internals.AbstractStaticFunctor;
import autumn.lang.internals.ArgumentStack;
import com.google.common.collect.ImmutableList;
import java.util.List;

/**
 * An instance of this functor-type is a functor that used to implement a to-string method.
 *
 * <p>
 * <b>Signature:</b>
 * <code> MethodStr (self : Object) => String </code>
 * </p>
 *
 * @author Mackenzie High
 */
public final class MethodToString
        extends AbstractStaticFunctor
{
    /**
     * Sole Constructor.
     *
     * @param inner is the functor that provides the actual functionality.
     */
    public MethodToString(final Functor inner)
    {
        super(inner);
    }

    /**
     * This method invokes the inner functor.
     *
     * @param self is the object whose string representation will be created.
     */
    public void invoke(final Object self,
                       final Object other)
            throws Throwable
    {
        // Get the stack that is associated with this thread.
        final ArgumentStack stack = ArgumentStack.getThreadStack();

        // Push the arguments onto the argument-stack.
        stack.push(self);

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
        return ImmutableList.<Class>of(Object.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class returnType()
    {
        return String.class;
    }
}

package autumn.util.functors;

import autumn.lang.TypedFunctor;
import autumn.lang.internals.AbstractStaticFunctor;
import autumn.lang.internals.ArgumentStack;
import com.google.common.collect.ImmutableList;
import java.util.Iterator;
import java.util.List;

/**
 * An instance of this functor-type is a functor that used to implement an iterator method.
 *
 * <p>
 * <b>Signature:</b>
 * <code> MethodIter (self : Object) => Iterator </code>
 * </p>
 *
 * @author Mackenzie High
 */
public final class MethodIterator
        extends AbstractStaticFunctor
{
    /**
     * Sole Constructor.
     *
     * @param inner is the functor that provides the actual functionality.
     */
    public MethodIterator(final TypedFunctor inner)
    {
        super(inner);
    }

    /**
     * This method invokes the inner functor.
     *
     * @param self is the object that will be iterated over.
     */
    public void invoke(final Object self)
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
        return Iterator.class;
    }
}

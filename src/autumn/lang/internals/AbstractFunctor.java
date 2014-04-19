/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autumn.lang.internals;

import autumn.lang.Functor;
import com.google.common.base.Preconditions;
import java.util.List;

/**
 * This class provides a partial implementation of a functor.
 *
 * @author Mackenzie High
 */
public abstract class AbstractFunctor
        implements Functor
{
    /**
     * {@inheritDoc}
     */
    @Override
    public final Object apply(final Object... arguments)
            throws Throwable
    {
        Preconditions.checkNotNull(arguments);

        // Get the stack that is used by this thread.
        final ArgumentStack stack = ArgumentStack.getThreadStack();

        // Reset the stack in case the previous caller did not clear all of its arguments.
        stack.clear();

        // Push all the arguments onto the stack.
        for (Object argument : arguments)
        {
            stack.push(argument);
        }

        // Perform the invocation.
        invoke(stack);

        // Retrieve and pop the result.
        final Object result = stack.peekO();
        stack.pop();

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Object apply(final List arguments)
            throws Throwable
    {
        Preconditions.checkNotNull(arguments);

        // Get the stack that is used by this thread.
        final ArgumentStack stack = ArgumentStack.getThreadStack();

        // Reset the stack in case the previous caller did not clear all of its arguments.
        stack.clear();

        // Push all the arguments onto the stack.
        for (Object argument : arguments)
        {
            stack.push(argument);
        }

        // Perform the invocation.
        invoke(stack);

        // Retrieve and pop the result.
        final Object result = stack.peekO();
        stack.pop();

        return result;
    }
}

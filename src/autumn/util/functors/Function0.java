package autumn.util.functors;

import autumn.lang.TypedFunctor;
import autumn.lang.exceptions.CheckedException;
import autumn.lang.internals.AbstractDefinedFunctor;
import autumn.lang.internals.ArgumentStack;
import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * <code>
 * functor Function0 () : Object
 * </code>
 *
 * @author Mackenzie High
 */
public class Function0
        extends AbstractDefinedFunctor
        implements Callable
{
    /**
     * Sole Constructor.
     *
     * @param inner is the functor that provides the actual functionality.
     */
    public Function0(final TypedFunctor inner)
    {
        super(inner);
    }

    /**
     * This method invokes the inner functor.
     *
     * @return the result produced by the inner functor.
     */
    public Object invoke()
            throws Throwable
    {
        // Get the stack that is associated with this thread.
        final ArgumentStack stack = ArgumentStack.getThreadStack();

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
        return ImmutableList.<Class>of();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class returnType()
    {
        return Object.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object call()
            throws Exception
    {
        try
        {
            return invoke();
        }
        catch (RuntimeException ex)
        {
            throw ex;
        }
        catch (Exception ex)
        {
            throw ex;
        }
        catch (Throwable ex)
        {
            throw new CheckedException(ex);
        }
    }
}

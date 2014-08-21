package autumn.util.functors;

import autumn.lang.Functor;
import autumn.lang.internals.AbstractStaticFunctor;
import autumn.lang.internals.ArgumentStack;
import com.google.common.collect.ImmutableList;
import java.io.File;
import java.util.List;

/**
 * An instance of this functor-type is a functor that accepts a file argument.
 *
 * @author Mackenzie High
 */
public final class FileProcessor
        extends AbstractStaticFunctor
{
    /**
     * Sole Constructor.
     *
     * @param inner is the functor that provides the actual functionality.
     */
    public FileProcessor(final Functor inner)
    {
        super(inner);
    }

    /**
     * This method invokes the inner functor.
     *
     * @param file is the argument to pass to the inner functor.
     */
    public void invoke(final File file)
            throws Throwable
    {
        // Get the stack that is associated with this thread.
        final ArgumentStack stack = ArgumentStack.getThreadStack();

        // Push the argument onto the argument-stack.
        stack.push(file);

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
        return ImmutableList.<Class>of(File.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class returnType()
    {
        return void.class;
    }
}

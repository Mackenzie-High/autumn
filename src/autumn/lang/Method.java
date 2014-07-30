package autumn.lang;

import autumn.lang.internals.ArgumentStack;
import java.util.List;

/**
 * An instance of this method is a method in a prototype based object.
 *
 * @author Mackenzie High
 */
public interface Method
        extends Member,
                TypedFunctor
{
    /**
     * {@inheritDoc}
     */
    @Override
    public int index();

    /**
     * {@inheritDoc}
     */
    @Override
    public Prototype owner();

    /**
     * {@inheritDoc}
     */
    @Override
    public String name();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Class> parameterTypes();

    /**
     * {@inheritDoc}
     */
    @Override
    public Class returnType();

    /**
     * This method sets the handler that handles invocations of the represented method.
     *
     * @param handler is the new method handler (may be null).
     * @return a modified copy of this object.
     */
    public Method setHandler(final Functor handler);

    /**
     * This method gets the handler that handles invocations of the represented method.
     *
     * @return the handler; or null, if no handler is present.
     */
    public Functor getHandler();

    /**
     * {@inheritDoc}
     */
    @Override
    public void apply(final ArgumentStack stack)
            throws Throwable;
}

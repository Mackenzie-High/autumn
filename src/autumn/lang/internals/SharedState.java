package autumn.lang.internals;

import autumn.lang.Functor;
import autumn.lang.reflect.MethodKey;

/**
 * An instance of this class stores the state that is shared between multiple prototype objects.
 * This state is the method, getter, and setter handlers and the static layout.
 *
 * @author Mackenzie High
 */
public final class SharedState
{
    private ObjectLayout layout;

    private Functor[] setters;

    private Functor[] getters;

    private Functor[] methods;

    public SharedState(final ObjectLayout layout)
    {
        this.layout = layout;
    }

    public ObjectLayout layout()
    {
        return layout;
    }

    public int findProperty(final String name)
    {
        return 0;
    }

    public int findMethod(final MethodKey key)
    {
        return 0;
    }

    public Functor getGetter(final int index)
    {
        return null;
    }

    public Functor setGetter(final int index,
                             final Functor handler)
    {
        return null;
    }

    public Functor getSetter(final int index)
    {
        return null;
    }

    public Functor setSetter(final int index,
                             final Functor handler)
    {
        return null;
    }

    public Functor getMethod(final int index)
    {
        return null;
    }

    public Functor setMethod(final int index,
                             final Functor handler)
    {
        return null;
    }
}

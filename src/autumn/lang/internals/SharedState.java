package autumn.lang.internals;

import autumn.lang.Functor;
import java.util.Arrays;

/**
 * An instance of this class stores the state that is shared between multiple prototype objects.
 * This state is the method, getter, and setter handlers and the static layout.
 *
 * @author Mackenzie High
 */
public final class SharedState
{
    private final Functor[] setters;

    private final Functor[] getters;

    private final Functor[] methods;

    public SharedState(final int properties,
                       final int methods)
    {
        this.setters = new Functor[properties];
        this.getters = new Functor[properties];
        this.methods = new Functor[methods];
    }

    private SharedState(final SharedState original)
    {
        this.getters = Arrays.copyOf(original.getters, original.getters.length);
        this.setters = Arrays.copyOf(original.setters, original.setters.length);
        this.methods = Arrays.copyOf(original.methods, original.methods.length);
    }

    public Functor getGetter(final int index)
    {
        return getters[index];
    }

    public SharedState setGetter(final int index,
                                 final Functor handler)
    {
        final SharedState copy = new SharedState(this);
        copy.getters[index] = handler;
        return copy;
    }

    public Functor getSetter(final int index)
    {
        return setters[index];
    }

    public SharedState setSetter(final int index,
                                 final Functor handler)
    {
        final SharedState copy = new SharedState(this);
        copy.setters[index] = handler;
        return copy;
    }

    public Functor getMethod(final int index)
    {
        return methods[index];
    }

    public SharedState setMethod(final int index,
                                 final Functor handler)
    {
        final SharedState copy = new SharedState(this);
        copy.methods[index] = handler;
        return copy;
    }
}

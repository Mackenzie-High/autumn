package autumn.util.events;

import autumn.lang.Module;
import autumn.lang.TypedFunctor;
import com.google.common.base.Preconditions;

/**
 * This class provides the global event-dispatch mechanism.
 *
 * TODO: should events be global?
 * TODO: dataflow programming
 *
 * @author Mackenzie High
 */
public final class Events
{
    /**
     * This method binds an event-listener to a variable.
     *
     * @param name is the name of the global variable.
     * @param handler is the functor that will be invoked whenever the variable is set.
     */
    public void bind(final String name,
                     final TypedFunctor handler)
    {
    }

    /**
     * This method binds the event handler functions in a module to newly created variables.
     *
     * <p>
     * This method iterates over the functions declared in a module.
     * If the module has the <i>Event</i> annotation applied, then it is an event-handler.
     * This method will then declare a new variable,
     * The name of the variable is the name of the function.
     * Finally, this method will bind the event-handler to the variable.
     * </p>
     *
     * @param module is the module that contains the event-handler functions.
     */
    public void bindAll(final Module module)
    {
        Preconditions.checkNotNull(module);
    }

    public void setInterval(final String name,
                            final long millis)
    {
    }

    public long getInterval(final String name)
    {
        return 0;
    }

    public Thread run(final String name)
    {
        return null;
    }

    public void fire(final String name,
                     final Object argument)
    {
    }
}

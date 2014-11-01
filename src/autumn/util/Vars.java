package autumn.util;

import autumn.lang.Module;
import autumn.lang.TypedFunctor;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;

/**
 * This class provides a mechanism for creating globally visible variables.
 *
 * <p>
 * <b>Caution:</b> You should avoid using global variables whenever reasonably possible.
 * </p>
 *
 * @author Mackenzie High
 */
public final class Vars
{
    /**
     * An instance of this class stores vital information related to a global variable.
     */
    private static class Global
    {
        public boolean readonly = false;

        public List<TypedFunctor> events = null;

        public Object value;
    }

    /**
     * These are the currently defined global variables.
     */
    private static final Map<String, Global> vars = Maps.newTreeMap();

    public static void declareVar(final String name,
                                  final Object value)
    {
        Preconditions.checkNotNull(name);
        Preconditions.checkArgument(vars.containsKey(name), "No Such Variable: " + name);

        final Global var = new Global();

        var.readonly = false;
        var.events = null;
        var.value = value;
    }

    public static void declareVal(final String name,
                                  final Object value)
    {
        Preconditions.checkNotNull(name);
        Preconditions.checkArgument(vars.containsKey(name), "No Such Variable: " + name);

        final Global var = new Global();

        var.readonly = true;
        var.events = null;
        var.value = value;
    }

    public static boolean isDeclared(final String name)
    {
        return vars.containsKey(name);
    }

    public static void delete(final String name)
    {
        vars.remove(name);
    }

    /**
     * This method binds an event-listener to a variable.
     *
     * @param name is the name of the global variable.
     * @param handler is the functor that will be invoked whenever the variable is set.
     */
    public static void bind(final String name,
                            final TypedFunctor handler)
    {
        Preconditions.checkNotNull(name);
        Preconditions.checkArgument(vars.containsKey(name), "No Such Variable: " + name);

        // TODO: what happens when an event is bound multiple times?
        // TODO: check event signature

        final Global var = vars.get(name);

        if (var.events == null)
        {
            var.events = Lists.newLinkedList();
        }

        var.events.add(handler);
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
    public static void bindAll(final Module module)
    {
        Preconditions.checkNotNull(module);
    }

    public void set(final String name,
                    final Object value)
    {
        Preconditions.checkNotNull(name);
        Preconditions.checkArgument(vars.containsKey(name), "No Such Variable: " + name);

        final Global var = vars.get(name);

        Preconditions.checkArgument(var.readonly, "Readonly Variable: " + name);

        var.value = value;

        if (var.events != null)
        {
            for (TypedFunctor listener : var.events)
            {
                Functors.quietlyApply(listener, ImmutableList.of(value));
            }
        }

    }

    public Object get(final String name)
    {
        Preconditions.checkNotNull(name);
        Preconditions.checkArgument(vars.containsKey(name), "No Such Variable: " + name);

        final Global var = vars.get(name);

        return var.value;
    }
}

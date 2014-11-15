package autumn.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
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
        var.value = value;
    }

    public static void declareVal(final String name,
                                  final Object value)
    {
        Preconditions.checkNotNull(name);
        Preconditions.checkArgument(vars.containsKey(name), "No Such Variable: " + name);

        final Global var = new Global();

        var.readonly = true;
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

    public void set(final String name,
                    final Object value)
    {
        Preconditions.checkNotNull(name);
        Preconditions.checkArgument(vars.containsKey(name), "No Such Variable: " + name);

        final Global var = vars.get(name);

        Preconditions.checkArgument(var.readonly, "Readonly Variable: " + name);

        var.value = value;
    }

    public Object get(final String name)
    {
        Preconditions.checkNotNull(name);
        Preconditions.checkArgument(vars.containsKey(name), "No Such Variable: " + name);

        final Global var = vars.get(name);

        return var.value;
    }
}

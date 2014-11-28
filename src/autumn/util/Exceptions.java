package autumn.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.util.Map;

/**
 * This class provides static utility methods related to exception handling.
 *
 * @author Mackenzie High
 */
public final class Exceptions
{
    /**
     * TODO: This must be a weak map.
     */
    private static final Map<Object, Map<Object, Object>> details = Maps.newIdentityHashMap();

    public static void put(final Throwable problem,
                           final Object key,
                           final Object value)
    {
        Preconditions.checkNotNull(problem);
        Preconditions.checkNotNull(key);

        if (details.containsKey(problem) == false)
        {
            details.put(problem, Maps.newHashMap());
        }

        final Map<Object, Object> map = details.get(problem);

        map.put(key, value);
    }

    public static Object get(final Throwable problem,
                             final Object key)
    {
        Preconditions.checkNotNull(problem);
        Preconditions.checkNotNull(key);

        if (details.containsKey(problem) == false)
        {
            return null;
        }

        final Map<Object, Object> map = details.get(problem);

        final Object result = map.get(key);

        return result;
    }
}

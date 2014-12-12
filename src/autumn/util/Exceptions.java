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

    /**
     * This method associates a detail with a given exception.
     *
     * @param problem is the exception that the detail is in reference to.
     * @param key identifies the detail.
     * @param value is the content of the detail.
     * @throws NullPointerException if problem is null.
     * @throws NullPointerException if key is null.
     */
    public static void set(final Throwable problem,
                           final String key,
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

    /**
     * This method retrieves a detail that is associated with a given exception.
     *
     * @param problem is the exception that the detail is in reference to.
     * @param key identifies the detail.
     * @return the value of the detail, or null, if the detail does not exist.
     * @throws NullPointerException if problem is null.
     * @throws NullPointerException if key is null.
     */
    public static Object get(final Throwable problem,
                             final String key)
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

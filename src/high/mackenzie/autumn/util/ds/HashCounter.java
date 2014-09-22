package high.mackenzie.autumn.util.ds;

import autumn.util.ds.Counter;
import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.Map;

/**
 * This method provides a counter based on a HashMap.
 *
 * @author Mackenzie High
 */
public final class HashCounter<T>
        implements Counter<T>
{
    private final Map<T, Integer> map = Maps.newHashMap();

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(final T value)
    {
        if (map.containsKey(value) == false)
        {
            map.put(value, 1);
        }
        else
        {
            map.put(value, map.get(value) + 1);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void subtract(T value)
    {
        if (map.containsKey(value) == false)
        {
            map.put(value, -1);
        }
        else
        {
            map.put(value, map.get(value) - 1);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int count(T value)
    {
        if (map.containsKey(value) == false)
        {
            return 0;
        }
        else
        {
            return map.get(value);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<T, Integer> toMap()
    {
        return Collections.unmodifiableMap(map);
    }
}

package autumn.lang;

import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;

/**
 * Instances of this class are used to implement the memoization of function calls.
 *
 * TODO: This is a temporary implementation!!!!!!!!!!
 *
 * @author Mackenzie High
 */
public final class Memoizer
{
    private final Map<List<? extends Object>, Object> cache = Maps.newLinkedHashMap();

    public Memoizer()
    {
        // Pass
    }

    public void clear()
    {
        cache.clear();
    }

    public boolean check(final List<? extends Object> arguments)
    {
        final boolean answer = cache.containsKey(arguments);

        return answer;
    }

    public boolean valueAsBoolean(final List<? extends Object> arguments)
    {
        final Object retval = cache.get(arguments);

        return (Boolean) retval;
    }

    public char valueAsChar(final List<? extends Object> arguments)
    {
        final Object retval = cache.get(arguments);

        return (Character) retval;
    }

    public byte valueAsByte(final List<? extends Object> arguments)
    {
        final Object cached = cache.get(arguments);

        final Number number = (Number) cached;

        return number.byteValue();
    }

    public short valueAsShort(final List<? extends Object> arguments)
    {
        final Object cached = cache.get(arguments);

        final Number number = (Number) cached;

        return number.shortValue();
    }

    public int valueAsInt(final List<? extends Object> arguments)
    {
        final Object cached = cache.get(arguments);

        final Number number = (Number) cached;

        return number.intValue();
    }

    public long valueAsLong(final List<? extends Object> arguments)
    {
        final Object cached = cache.get(arguments);

        final Number number = (Number) cached;

        return number.longValue();
    }

    public float valueAsFloat(final List<? extends Object> arguments)
    {
        final Object cached = cache.get(arguments);

        final Number number = (Number) cached;

        return number.floatValue();
    }

    public double valueAsDouble(final List<? extends Object> arguments)
    {
        final Object cached = cache.get(arguments);

        final Number number = (Number) cached;

        return number.doubleValue();
    }

    public Object valueAsObject(final List<? extends Object> arguments)
    {
        final Object retval = cache.get(arguments);

        return retval;
    }

    public void intern(final Object value,
                       final List<? extends Object> arguments)
    {
        cache.put(arguments, value);
    }
}

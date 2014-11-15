package autumn.lang.internals;

import autumn.lang.Lambda;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.Map;

/**
 *
 * @author mackenzie
 */
public abstract class AbstractLambda
        implements Lambda
{
    private final Map<String, Object> closure = Maps.newTreeMap();

    /**
     * {@inheritDoc}
     */
    @Override
    public final void capture(final String name,
                              final Object value)
    {
        Preconditions.checkNotNull(name);
        Preconditions.checkArgument(closure.containsKey(name) == false);

        closure.put(name, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean valueAsBoolean(final String name)
    {
        Preconditions.checkNotNull(name);
        Preconditions.checkState(closure.containsKey(name));

        return (Boolean) closure.get(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final char valueAsChar(final String name)
    {
        Preconditions.checkNotNull(name);
        Preconditions.checkState(closure.containsKey(name));

        return (Character) closure.get(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final byte valueAsByte(final String name)
    {
        Preconditions.checkNotNull(name);
        Preconditions.checkState(closure.containsKey(name));

        return (Byte) closure.get(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final short valueAsShort(final String name)
    {
        Preconditions.checkNotNull(name);
        Preconditions.checkState(closure.containsKey(name));

        return (Short) closure.get(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int valueAsInt(final String name)
    {
        Preconditions.checkNotNull(name);
        Preconditions.checkState(closure.containsKey(name));

        return (Integer) closure.get(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final long valueAsLong(final String name)
    {
        Preconditions.checkNotNull(name);
        Preconditions.checkState(closure.containsKey(name));

        return (Long) closure.get(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final float valueAsFloat(final String name)
    {
        Preconditions.checkNotNull(name);
        Preconditions.checkState(closure.containsKey(name));

        return (Float) closure.get(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final double valueAsDouble(final String name)
    {
        Preconditions.checkNotNull(name);
        Preconditions.checkState(closure.containsKey(name));

        return (Double) closure.get(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Object valueAsObject(final String name)
    {
        Preconditions.checkNotNull(name);
        Preconditions.checkState(closure.containsKey(name));

        return (Object) closure.get(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Map<String, Object> closure()
    {
        final Map<String, Object> map = Maps.newTreeMap();

        map.putAll(closure);

        return Collections.unmodifiableMap(map);
    }
}

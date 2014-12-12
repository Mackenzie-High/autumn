package autumn.lang;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Instances of this class are used to implement the memoization of function calls.
 *
 * <p>
 * Internally, this object maintains maps argument-lists to interned return-values.
 * </p>
 *
 * @author Mackenzie High
 */
public final class Memoizer
{
    private static final String NOT_CACHED = "The given argument-list does not identify an interned return-value.";

    /**
     * This is the maximum allowed size of the cache.
     */
    private int maximum_size;

    /**
     * This map maps argument-lists to interned return-values.
     */
    private final Map<List<? extends Object>, Object> cache = Maps.newLinkedHashMap();

    /**
     * Sole Constructor.
     */
    public Memoizer()
    {
        // Pass
    }

    /**
     * This method clears the cache of interned values.
     */
    public synchronized void clear()
    {
        cache.clear();
    }

    /**
     * This method sets the maximum size of the cache.
     *
     * @param size is the maximum size of the cache.
     * @throws IllegalArgumentException if size is negative.
     */
    public void setMaximumCacheSize(final int size)
    {
        Preconditions.checkArgument(size >= 0);

        this.maximum_size = size;
    }

    /**
     * This method gets the maximum size of the cache.
     *
     * @return the maximum number of mappings that can be present in the cache at one time.
     */
    public int getMaximumCacheSize()
    {
        return maximum_size;
    }

    /**
     * This method retrieves a readonly view of the cache.
     *
     * <p>
     * The returned map is backed by the actual cache.
     * Therefore, changes made to the cache will be reflected in the returned map.
     * </p>
     *
     * @return a readonly view of the cache.
     */
    public Map<List<?>, ?> cache()
    {
        return Collections.unmodifiableMap(cache);
    }

    /**
     * This method determines whether a given argument-list is already mapped to a return-value.
     *
     * @param arguments is the given argument-list itself.
     * @return true, iff the argument-list is already mapped to a return-value.
     * @throws NullPointerException if arguments is null.
     */
    public synchronized boolean check(final List<? extends Object> arguments)
    {
        Preconditions.checkNotNull(arguments);

        final boolean answer = cache.containsKey(arguments);

        return answer;
    }

    /**
     * This method retrieves an interned return-value given the associated argument-list.
     *
     * @param arguments is the given argument-list.
     * @return the return-value that is identified in the cache by the argument-list.
     * @throws NullPointerException if arguments is null.
     * @throws IllegalStateException if the argument-list does not identify an interned return-value.
     */
    public synchronized boolean valueAsBoolean(final List<? extends Object> arguments)
    {
        Preconditions.checkNotNull(arguments);
        Preconditions.checkState(cache.containsKey(arguments), NOT_CACHED);

        final Object retval = cache.get(arguments);

        return (Boolean) retval;
    }

    /**
     * This method retrieves an interned return-value given the associated argument-list.
     *
     * @param arguments is the given argument-list.
     * @return the return-value that is identified in the cache by the argument-list.
     * @throws NullPointerException if arguments is null.
     * @throws IllegalStateException if the argument-list does not identify an interned return-value.
     */
    public synchronized char valueAsChar(final List<? extends Object> arguments)
    {
        Preconditions.checkNotNull(arguments);
        Preconditions.checkState(cache.containsKey(arguments), NOT_CACHED);

        final Object retval = cache.get(arguments);

        return (Character) retval;
    }

    /**
     * This method retrieves an interned return-value given the associated argument-list.
     *
     * @param arguments is the given argument-list.
     * @return the return-value that is identified in the cache by the argument-list.
     * @throws NullPointerException if arguments is null.
     * @throws IllegalStateException if the argument-list does not identify an interned return-value.
     */
    public synchronized byte valueAsByte(final List<? extends Object> arguments)
    {
        Preconditions.checkNotNull(arguments);
        Preconditions.checkState(cache.containsKey(arguments), NOT_CACHED);

        final Object cached = cache.get(arguments);

        final Number number = (Number) cached;

        return number.byteValue();
    }

    /**
     * This method retrieves an interned return-value given the associated argument-list.
     *
     * @param arguments is the given argument-list.
     * @return the return-value that is identified in the cache by the argument-list.
     * @throws NullPointerException if arguments is null.
     * @throws IllegalStateException if the argument-list does not identify an interned return-value.
     */
    public synchronized short valueAsShort(final List<? extends Object> arguments)
    {
        Preconditions.checkNotNull(arguments);
        Preconditions.checkState(cache.containsKey(arguments), NOT_CACHED);

        final Object cached = cache.get(arguments);

        final Number number = (Number) cached;

        return number.shortValue();
    }

    /**
     * This method retrieves an interned return-value given the associated argument-list.
     *
     * @param arguments is the given argument-list.
     * @return the return-value that is identified in the cache by the argument-list.
     * @throws NullPointerException if arguments is null.
     * @throws IllegalStateException if the argument-list does not identify an interned return-value.
     */
    public synchronized int valueAsInt(final List<? extends Object> arguments)
    {
        Preconditions.checkNotNull(arguments);
        Preconditions.checkState(cache.containsKey(arguments), NOT_CACHED);

        final Object cached = cache.get(arguments);

        final Number number = (Number) cached;

        return number.intValue();
    }

    /**
     * This method retrieves an interned return-value given the associated argument-list.
     *
     * @param arguments is the given argument-list.
     * @return the return-value that is identified in the cache by the argument-list.
     * @throws NullPointerException if arguments is null.
     * @throws IllegalStateException if the argument-list does not identify an interned return-value.
     */
    public synchronized long valueAsLong(final List<? extends Object> arguments)
    {
        Preconditions.checkNotNull(arguments);
        Preconditions.checkState(cache.containsKey(arguments), NOT_CACHED);

        final Object cached = cache.get(arguments);

        final Number number = (Number) cached;

        return number.longValue();
    }

    /**
     * This method retrieves an interned return-value given the associated argument-list.
     *
     * @param arguments is the given argument-list.
     * @return the return-value that is identified in the cache by the argument-list.
     * @throws NullPointerException if arguments is null.
     * @throws IllegalStateException if the argument-list does not identify an interned return-value.
     */
    public synchronized float valueAsFloat(final List<? extends Object> arguments)
    {
        Preconditions.checkNotNull(arguments);
        Preconditions.checkState(cache.containsKey(arguments), NOT_CACHED);

        final Object cached = cache.get(arguments);

        final Number number = (Number) cached;

        return number.floatValue();
    }

    /**
     * This method retrieves an interned return-value given the associated argument-list.
     *
     * @param arguments is the given argument-list.
     * @return the return-value that is identified in the cache by the argument-list.
     * @throws NullPointerException if arguments is null.
     * @throws IllegalStateException if the argument-list does not identify an interned return-value.
     */
    public synchronized double valueAsDouble(final List<? extends Object> arguments)
    {
        Preconditions.checkNotNull(arguments);
        Preconditions.checkState(cache.containsKey(arguments), NOT_CACHED);

        final Object cached = cache.get(arguments);

        final Number number = (Number) cached;

        return number.doubleValue();
    }

    /**
     * This method retrieves an interned return-value given the associated argument-list.
     *
     * @param arguments is the given argument-list.
     * @return the return-value that is identified in the cache by the argument-list.
     * @throws NullPointerException if arguments is null.
     * @throws IllegalStateException if the argument-list does not identify an interned return-value.
     */
    public synchronized Object valueAsObject(final List<? extends Object> arguments)
    {
        Preconditions.checkNotNull(arguments);
        Preconditions.checkState(cache.containsKey(arguments), NOT_CACHED);

        final Object retval = cache.get(arguments);

        return retval;
    }

    /**
     * This method interns a return-value in the cache.
     *
     * <p>
     * An argument-list can only be mapped to one return-value at a time.
     * </p>
     *
     * @param value is the return-value to intern.
     * @param arguments is a list containing the arguments passed to the memoized function.
     * @throws NullPointerException if arguments is null.
     * @throws IllegalStateException if the argument-list is already mapped to a return-value.
     */
    public synchronized void intern(final Object value,
                                    final List<? extends Object> arguments)
    {
        Preconditions.checkNotNull(arguments);
        Preconditions.checkState(cache.containsKey(arguments) == false,
                                 "The given argument-list is already mapped to a return-value.");

        final List key = Collections.unmodifiableList(new ArrayList(arguments));

        cache.put(key, value);
    }
}

package autumn.lang.internals;

import autumn.lang.Delegate;
import autumn.lang.Module;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * This class contains utility methods that allow the compiler to generate simpler bytecode.
 *
 * <p>
 * Note: This class is not intended for use directly by programmers.
 * </p>
 *
 * @author Mackenzie High
 */
public final class Helpers
{
    /**
     * This is the scale of big-decimal literals.
     */
    public static final int BIG_DECIMAL_SCALE = 32;

    /**
     * This method finds a named enum-constant in an array of enum-constants.
     *
     * <p>
     * This method is used in the implementation of the valueOf(String) method in enums.
     * </p>
     *
     * @param constants is the array that may contain the named enum-constant.
     * @param name is the name of the enum-constant to find.
     * @return the named enum-constant.
     * @throws NullPointerException if constants is null.
     * @throws NullPointerException if name is null.
     * @throws IllegalArgumentException if the named enum-constant is not present in the array.
     */
    public static Enum findEnumConstant(final Enum[] constants,
                                        final String name)
    {
        Preconditions.checkNotNull(constants, "An array of enum-constants was expected.");
        Preconditions.checkNotNull(name, "The name of an enum-constant was expected.");

        for (Enum constant : constants)
        {
            if (name.equals(constant.name().trim()))
            {
                return constant;
            }
        }

        throw new IllegalArgumentException("No such enum-constant: " + name);
    }

    /**
     * This method creates a new immutable-list from an iterable data-structure.
     *
     * @param iterable is the data-structure that contains the elements of the new list.
     * @return the new immutable list.
     * @throws NullPointerException if the iterable is null.
     */
    public static <T> List<T> newImmutableList(final Iterable<T> iterable)
    {
        Preconditions.checkNotNull(iterable, "An iterable data-structure was expected.");

        final ArrayList<T> list = Lists.newArrayList(iterable);

        list.trimToSize();

        return Collections.unmodifiableList(list);
    }

    /**
     * This method creates a new immutable-map from an two iterables.
     *
     * <p>
     * The new map will preserve iteration order.
     * </p>
     *
     * @param keys are the keys of the new map in iteration order.
     * @param values are the values of the new map in iteration order.
     * @return the new immutable list.
     * @throws NullPointerException if the keys is null.
     * @throws NullPointerException if the values is null.
     * @throws IllegalArgumentException if keys.size() != values.size().
     */
    public static <K, V> Map<K, V> newImmutableMap(final List<K> keys,
                                                   final List<V> values)
    {
        Preconditions.checkNotNull(keys);
        Preconditions.checkNotNull(values);
        Preconditions.checkArgument(keys.size() == values.size());

        final Map<K, V> map = Maps.newLinkedHashMap();

        for (int i = 0; i < keys.size(); i++)
        {
            map.put(keys.get(i), values.get(i));
        }

        return Collections.unmodifiableMap(map);
    }

    /**
     * This method searches for a particular function's delegate in a module.
     *
     * @param module is the module that provides the delegate.
     * @param method is the name of the delegated method.
     * @return the delegate that refers to the named method.
     * @throws IllegalStateException if the module does not contain a method with the given name.
     */
    public static Delegate delegate(final Module module,
                                    final String method)
    {
        Preconditions.checkNotNull(module);
        Preconditions.checkNotNull(method);

        /**
         * If the delegate refers to the special instance() method,
         * create a special delegate just it.
         */
        if (method.equals("instance"))
        {
            return new AbstractDelegate()
            {
                @Override
                public void apply(ArgumentStack stack)
                        throws Throwable
                {
                    stack.clear();

                    stack.push(module);
                }

                @Override
                public List<Class> parameterTypes()
                {
                    return ImmutableList.of();
                }

                @Override
                public Class returnType()
                {
                    return module.getClass();
                }

                @Override
                public Module module()
                {
                    return module;
                }

                @Override
                public Class owner()
                {
                    return module.getClass();
                }

                @Override
                public String name()
                {
                    return method;
                }
            };
        }

        /**
         * Otherwise, the delegate must refer to a user-defined function.
         */
        for (Delegate d : module.moduleInfo().functions())
        {
            if (method.equals(d.name()))
            {
                return d;
            }
        }

        throw new IllegalStateException(); // TODO: is this right?
    }

    /**
     * This method throws an index-out-of-bounds-exception when an index is out of bounds.
     *
     * @param index is the index that may be out of bounds.
     * @param size is the maximum allowed index plus one.
     */
    public static void throwIndexOutOfBoundsException(final int index,
                                                      final int size)
    {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /**
     * This method ensures that its argument is not null.
     *
     * @param value is the value that may be null.
     * @throws NullPointerException if value is null.
     */
    public static void requireNonNull(final Object value)
    {
        if (value == null)
        {
            throw new NullPointerException();
        }
    }

    /**
     * This method creates a new big-integer.
     *
     * <p>
     * This method is used to implement big-integer literals.
     * </p>
     *
     * @param value is the string representation of the new big-integer.
     * @return the value as a big-integer.
     */
    public static BigInteger createBigInteger(final String value)
    {
        return new BigInteger(value);
    }

    /**
     * This method creates a new big-decimal.
     *
     * <p>
     * This method is used to implement big-decimal literals.
     * </p>
     *
     * @param value is the string representation of the new big-decimal.
     * @return the value as a big-decimal.
     */
    public static BigDecimal createBigDecimal(final String value)
    {
        return createBigDecimal(new BigDecimal(value));
    }

    /**
     * This method creates a new properly scaled big-decimal from an existing big-decimal.
     *
     * <p>
     * This method is used to implement big-decimal operators.
     * </p>
     *
     * @param value is big-decimal itself.
     * @return the value as a properly scaled big-decimal.
     */
    public static BigDecimal createBigDecimal(final BigDecimal value)
    {
        return value.setScale(BIG_DECIMAL_SCALE, RoundingMode.HALF_EVEN);
    }
}

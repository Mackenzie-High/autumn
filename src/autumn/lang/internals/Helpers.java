package autumn.lang.internals;

import autumn.lang.Delegate;
import autumn.lang.LocalsMap;
import autumn.lang.Module;
import autumn.lang.compiler.Autumn;
import autumn.util.F;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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
     * This method is invoked by debug-statements in order to implement breakpoints.
     *
     * @param file is the file that contains the breakpoint.
     * @param line is the line-number where the breakpoint is located.
     * @param column is the column-number where the the breakpoint is located.
     * @param locals describes the local variables in at the time the breakpoint was hit.
     */
    public static void debug(final String file,
                             final int line,
                             final int column,
                             final LocalsMap locals)
    {
        if (Autumn.isDebugOn() == false)
        {
            return;
        }

        System.out.println();
        System.out.printf("Breakpoint Hit: line = %d, column = %d, file = %s\n", line, column, file);
        locals.print();

        F.readln();
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
}

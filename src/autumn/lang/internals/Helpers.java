package autumn.lang.internals;

import autumn.lang.Delegate;
import autumn.util.F;
import autumn.lang.Functor;
import autumn.lang.LocalsMap;
import autumn.lang.Module;
import autumn.lang.Prototype;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        System.out.println();
        System.out.printf("Breakpoint Hit: line = %d, column = %d, file = %s\n", line, column, file);
        locals.print();

        F.readln();
    }

    /**
     * This method is invoked by a setter-statement.
     *
     * @param owner is the object that contains the property.
     * @param key is the name of the property.
     * @param handler is the function object that will be the property's setter.
     */
    public static void setter(final Prototype owner,
                              final String key,
                              final Functor handler)
    {
        // TODO
        System.out.println("setter called!");
    }

    /**
     * This method is invoked by a getter-statement.
     *
     * @param owner is the object that contains the property.
     * @param key is the name of the property.
     * @param handler is the function object that will be the property's getter.
     */
    public static void getter(final Prototype owner,
                              final String key,
                              final Functor handler)
    {
        System.out.println("getter called!");
    }

    /**
     * This method is invoked by a method-statement.
     *
     * @param owner is the object that contains the method.
     * @param key is the name + descriptor of the method.
     * @param handler is the function object that will be the method handler.
     */
    public static void method(final Prototype owner,
                              final String key,
                              final Functor handler)
    {
        System.out.println("method called!");
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
        for (Delegate d : module.moduleFunctions())
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
}

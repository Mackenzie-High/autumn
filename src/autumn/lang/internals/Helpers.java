/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autumn.lang.internals;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
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

        return ImmutableList.copyOf(iterable);
    }
}

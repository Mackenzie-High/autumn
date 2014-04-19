/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autumn.lang.internals;

import com.google.common.base.Objects;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class provides static method in order to implement Autumn's operators.
 *
 * <p> <b>Note:</b> Invocations of these methods should be optimized out by Autumn compilers. </p>
 *
 * @author mackenzie
 */
public class Operators
{
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                 Concat
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static String concat(final String left_operand,
                                final String right_operand)
    {
        return left_operand + right_operand;
    }

    public static List concat(final List left_operand,
                              final List right_operand)
    {
        return null;
    }

    public static Set concat(final Set left_operand,
                             final Set right_operand)
    {
        return null;
    }

    public static Collection concat(final Collection left_operand,
                                    final Collection right_operand)
    {
        return null;
    }

    public static Map concat(final Map left_operand,
                             final Map right_operand)
    {
        return null;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                 Negate
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static byte negate(final byte operand)
    {
        return (byte) -operand;
    }

    public static short negate(final short operand)
    {
        return (short) -operand;
    }

    public static int negate(final int operand)
    {
        return (int) -operand;
    }

    public static long negate(final long operand)
    {
        return (long) -operand;
    }

    public static float negate(final float operand)
    {
        return (float) -operand;
    }

    public static double negate(final double operand)
    {
        return (double) -operand;
    }

    /**
     * This method divides two numbers and returns the result.
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left / right</code>
     */
    public static char divide(final char left,
                              final char right)
    {
        return (char) (left / right);
    }

    /**
     * This method divides two numbers and returns the result.
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left / right</code>
     */
    public static byte divide(final byte left,
                              final byte right)
    {
        return (byte) (left / right);
    }

    /**
     * This method divides two numbers and returns the result.
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left / right</code>
     */
    public static short divide(final short left,
                               final short right)
    {
        return (short) (left / right);
    }

    /**
     * This method divides two numbers and returns the result.
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left / right</code>
     */
    public static int divide(final int left,
                             final int right)
    {
        return left / right;
    }

    /**
     * This method divides two numbers and returns the result.
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left / right</code>
     */
    public static long divide(final long left,
                              final long right)
    {
        return left / right;
    }

    /**
     * This method divides two numbers and returns the result.
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left / right</code>
     */
    public static float divide(final float left,
                               final float right)
    {
        return left / right;
    }

    /**
     * This method divides two numbers and returns the result.
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left / right</code>
     */
    public static double divide(final double left,
                                final double right)
    {
        return left / right;
    }

    /**
     * This method modulos two numbers and returns the result.
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left % right</code>
     */
    public static char modulo(final char left,
                              final char right)
    {
        return (char) (left % right);
    }

    /**
     * This method modulos two numbers and returns the result.
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left % right</code>
     */
    public static byte modulo(final byte left,
                              final byte right)
    {
        return (byte) (left % right);
    }

    /**
     * This method modulos two numbers and returns the result.
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left % right</code>
     */
    public static short modulo(final short left,
                               final short right)
    {
        return (short) (left % right);
    }

    /**
     * This method modulos two numbers and returns the result.
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left % right</code>
     */
    public static int modulo(final int left,
                             final int right)
    {
        return left % right;
    }

    /**
     * This method modulos two numbers and returns the result.
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left % right</code>
     */
    public static long modulo(final long left,
                              final long right)
    {
        return left % right;
    }

    /**
     * This method modulos two numbers and returns the result.
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left % right</code>
     */
    public static float modulo(final float left,
                               final float right)
    {
        return left % right;
    }

    /**
     * This method modulos two numbers and returns the result.
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left % right</code>
     */
    public static double modulo(final double left,
                                final double right)
    {
        return left % right;
    }

    /**
     * This method multiplies two numbers and returns the result.
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left * right</code>
     */
    public static char multiply(final char left,
                                final char right)
    {
        return (char) (left * right);
    }

    /**
     * This method multiplies two numbers and returns the result.
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left * right</code>
     */
    public static byte multiply(final byte left,
                                final byte right)
    {
        return (byte) (left * right);
    }

    /**
     * This method multiplies two numbers and returns the result.
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left * right</code>
     */
    public static short multiply(final short left,
                                 final short right)
    {
        return (short) (left * right);
    }

    /**
     * This method multiplies two numbers and returns the result.
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left * right</code>
     */
    public static int multiply(final int left,
                               final int right)
    {
        return left * right;
    }

    /**
     * This method multiplies two numbers and returns the result.
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left * right</code>
     */
    public static long multiply(final long left,
                                final long right)
    {
        return left * right;
    }

    /**
     * This method multiplies two numbers and returns the result.
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left * right</code>
     */
    public static float multiply(final float left,
                                 final float right)
    {
        return left * right;
    }

    /**
     * This method multiplies two numbers and returns the result.
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left * right</code>
     */
    public static double multiply(final double left,
                                  final double right)
    {
        return left * right;
    }

    /**
     * This method adds two numbers and returns the result.
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left + right</code>
     */
    public static char add(final char left,
                           final char right)
    {
        return (char) (left + right);
    }

    /**
     * This method adds two numbers and returns the result.
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left + right</code>
     */
    public static byte add(final byte left,
                           final byte right)
    {
        return (byte) (left + right);
    }

    /**
     * This method adds two numbers and returns the result.
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left + right</code>
     */
    public static short add(final short left,
                            final short right)
    {
        return (short) (left + right);
    }

    /**
     * This method adds two numbers and returns the result.
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left + right</code>
     */
    public static int add(final int left,
                          final int right)
    {
        return left + right;
    }

    /**
     * This method adds two numbers and returns the result.
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left + right</code>
     */
    public static long add(final long left,
                           final long right)
    {
        return left + right;
    }

    /**
     * This method adds two numbers and returns the result.
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left + right</code>
     */
    public static float add(final float left,
                            final float right)
    {
        return left + right;
    }

    /**
     * This method adds two numbers and returns the result.
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left + right</code>
     */
    public static double add(final double left,
                             final double right)
    {
        return left + right;
    }

    public static Collection<Object> add(final Collection<Object> collection,
                                         final Object value)
    {
        return null;
    }

    /**
     * This method subtracts two numbers and returns the result.
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left - right</code>
     */
    public static char subtract(final char left,
                                final char right)
    {
        return (char) (left - right);
    }

    /**
     * This method subtracts two numbers and returns the result.
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left - right</code>
     */
    public static byte subtract(final byte left,
                                final byte right)
    {
        return (byte) (left - right);
    }

    /**
     * This method subtracts two numbers and returns the result.
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left - right</code>
     */
    public static short subtract(final short left,
                                 final short right)
    {
        return (short) (left - right);
    }

    /**
     * This method subtracts two numbers and returns the result.
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left - right</code>
     */
    public static int subtract(final int left,
                               final int right)
    {
        return left - right;
    }

    /**
     * This method subtracts two numbers and returns the result.
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left - right</code>
     */
    public static long subtract(final long left,
                                final long right)
    {
        return left - right;
    }

    /**
     * This method subtracts two numbers and returns the result.
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left - right</code>
     */
    public static float subtract(final float left,
                                 final float right)
    {
        return left - right;
    }

    /**
     * This method subtracts two numbers and returns the result.
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left - right</code>
     */
    public static double subtract(final double left,
                                  final double right)
    {
        return left - right;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                 Equals
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static boolean equals(final boolean left,
                                 final boolean right)
    {
        return left == right;
    }

    public static boolean equals(final char left,
                                 final char right)
    {
        return left == right;
    }

    public static boolean equals(final byte left,
                                 final byte right)
    {
        return left == right;
    }

    public static boolean equals(final short left,
                                 final short right)
    {
        return left == right;
    }

    public static boolean equals(final int left,
                                 final int right)
    {
        return left == right;
    }

    public static boolean equals(final long left,
                                 final long right)
    {
        return left == right;
    }

    public static boolean equals(final float left,
                                 final float right)
    {
        return left == right;
    }

    public static boolean equals(final double left,
                                 final double right)
    {
        return left == right;
    }

    public static boolean equals(final Object left,
                                 final Object right)
    {
        return Objects.equal(left, right);
    }

    public static boolean notEquals(final boolean left,
                                    final boolean right)
    {
        return left != right;
    }

    public static boolean notEquals(final char left,
                                    final char right)
    {
        return left != right;
    }

    public static boolean notEquals(final byte left,
                                    final byte right)
    {
        return left != right;
    }

    public static boolean notEquals(final short left,
                                    final short right)
    {
        return left != right;
    }

    public static boolean notEquals(final int left,
                                    final int right)
    {
        return left != right;
    }

    public static boolean notEquals(final long left,
                                    final long right)
    {
        return left != right;
    }

    public static boolean notEquals(final float left,
                                    final float right)
    {
        return left != right;
    }

    public static boolean notEquals(final double left,
                                    final double right)
    {
        return left != right;
    }

    public static boolean notEquals(final Object left,
                                    final Object right)
    {
        return !Objects.equal(left, right);
    }

    public static boolean lessThan(final char left,
                                   final char right)
    {
        return left < right;
    }

    public static boolean lessThan(final byte left,
                                   final byte right)
    {
        return left < right;
    }

    public static boolean lessThan(final short left,
                                   final short right)
    {
        return left < right;
    }

    public static boolean lessThan(final int left,
                                   final int right)
    {
        return left < right;
    }

    public static boolean lessThan(final long left,
                                   final long right)
    {
        return left < right;
    }

    public static boolean lessThan(final float left,
                                   final float right)
    {
        return left < right;
    }

    public static boolean lessThan(final double left,
                                   final double right)
    {
        return left < right;
    }

    public static boolean lessThan(final Comparable left,
                                   final Comparable right)
    {
        return left.compareTo(right) < 0;
    }

    public static boolean lessThanOrEquals(final char left,
                                           final char right)
    {
        return left <= right;
    }

    public static boolean lessThanOrEquals(final byte left,
                                           final byte right)
    {
        return left <= right;
    }

    public static boolean lessThanOrEquals(final short left,
                                           final short right)
    {
        return left <= right;
    }

    public static boolean lessThanOrEquals(final int left,
                                           final int right)
    {
        return left <= right;
    }

    public static boolean lessThanOrEquals(final long left,
                                           final long right)
    {
        return left <= right;
    }

    public static boolean lessThanOrEquals(final float left,
                                           final float right)
    {
        return left <= right;
    }

    public static boolean lessThanOrEquals(final double left,
                                           final double right)
    {
        return left <= right;
    }

    public static boolean lessThanOrEquals(final Comparable left,
                                           final Comparable right)
    {
        return left.compareTo(right) <= 0;
    }

    public static boolean greaterThan(final char left,
                                      final char right)
    {
        return left > right;
    }

    public static boolean greaterThan(final byte left,
                                      final byte right)
    {
        return left > right;
    }

    public static boolean greaterThan(final short left,
                                      final short right)
    {
        return left > right;
    }

    public static boolean greaterThan(final int left,
                                      final int right)
    {
        return left > right;
    }

    public static boolean greaterThan(final long left,
                                      final long right)
    {
        return left > right;
    }

    public static boolean greaterThan(final float left,
                                      final float right)
    {
        return left > right;
    }

    public static boolean greaterThan(final double left,
                                      final double right)
    {
        return left > right;
    }

    public static boolean greaterThan(final Comparable left,
                                      final Comparable right)
    {
        return left.compareTo(right) > 0;
    }

    public static boolean greaterThanOrEquals(final char left,
                                              final char right)
    {
        return left >= right;
    }

    public static boolean greaterThanOrEquals(final byte left,
                                              final byte right)
    {
        return left >= right;
    }

    public static boolean greaterThanOrEquals(final short left,
                                              final short right)
    {
        return left >= right;
    }

    public static boolean greaterThanOrEquals(final int left,
                                              final int right)
    {
        return left >= right;
    }

    public static boolean greaterThanOrEquals(final long left,
                                              final long right)
    {
        return left >= right;
    }

    public static boolean greaterThanOrEquals(final float left,
                                              final float right)
    {
        return left >= right;
    }

    public static boolean greaterThanOrEquals(final double left,
                                              final double right)
    {
        return left >= right;
    }

    public static boolean greaterThanOrEquals(final Comparable left,
                                              final Comparable right)
    {
        return left.compareTo(right) >= 0;
    }

    public static boolean identityEquals(final Object left_operand,
                                         final Object right_operand)
    {
        return left_operand == right_operand;
    }

    public static boolean identityNotEquals(final Object left_operand,
                                            final Object right_operand)
    {
        return left_operand != right_operand;
    }

    public static boolean not(final boolean operand)
    {
        return !operand;
    }

    public static boolean and(final boolean left,
                              final boolean right)
    {
        return left && right;
    }

    public static boolean or(final boolean left,
                             final boolean right)
    {
        return left || right;
    }

    public static boolean xor(final boolean left,
                              final boolean right)
    {
        return left ^ right;
    }
}

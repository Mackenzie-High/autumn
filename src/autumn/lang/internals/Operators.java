package autumn.lang.internals;

import com.google.common.base.Objects;

/**
 * This class provides static method in order to implement Autumn's operators.
 *
 * <p> <b>Note:</b> Invocations of these methods should be optimized out by Autumn compilers. </p>
 *
 * @author mackenzie
 */
public class Operators
{
    /**
     * Concatenation Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return the left-operand and the right-operand concatenated together.
     */
    public static String concat(final CharSequence left,
                                final CharSequence right)
    {
        final StringBuilder result = new StringBuilder();
        result.append(left);
        result.append(right);
        return result.toString();
    }

    /**
     * Concatenation Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return the left-operand and the right-operand concatenated together.
     */
    public static String concat(final char left,
                                final CharSequence right)
    {
        final StringBuilder result = new StringBuilder();
        result.append(left);
        result.append(right);
        return result.toString();
    }

    /**
     * Concatenation Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return the left-operand and the right-operand concatenated together.
     */
    public static String concat(final CharSequence left,
                                final char right)
    {
        final StringBuilder result = new StringBuilder();
        result.append(left);
        result.append(right);
        return result.toString();
    }

    /**
     * Negation Operation
     *
     * @param operand is the operand.
     * @return <code>- operand</code>
     */
    public static byte negate(final byte operand)
    {
        return (byte) -operand;
    }

    /**
     * Negation Operation
     *
     * @param operand is the operand.
     * @return <code>- operand</code>
     */
    public static short negate(final short operand)
    {
        return (short) -operand;
    }

    /**
     * Negation Operation
     *
     * @param operand is the operand.
     * @return <code>- operand</code>
     */
    public static int negate(final int operand)
    {
        return (int) -operand;
    }

    /**
     * Negation Operation
     *
     * @param operand is the operand.
     * @return <code>- operand</code>
     */
    public static long negate(final long operand)
    {
        return (long) -operand;
    }

    /**
     * Negation Operation
     *
     * @param operand is the operand.
     * @return <code>- operand</code>
     */
    public static float negate(final float operand)
    {
        return (float) -operand;
    }

    /**
     * Negation Operation
     *
     * @param operand is the operand.
     * @return <code>- operand</code>
     */
    public static double negate(final double operand)
    {
        return (double) -operand;
    }

    /**
     * Division Operation
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
     * Division Operation
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
     * Division Operation
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
     * Division Operation
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
     * Division Operation
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
     * Division Operation
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
     * Division Operation
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
     * Modulo Operation
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
     * Modulo Operation
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
     * Modulo Operation
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
     * Modulo Operation
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
     * Modulo Operation
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
     * Modulo Operation
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
     * Modulo Operation
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
     * Multiply Operation
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
     * Multiply Operation
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
     * Multiply Operation
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
     * Multiply Operation
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
     * Multiply Operation
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
     * Multiply Operation
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
     * Multiply Operation
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
     * Add Operation
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
     * Add Operation
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
     * Add Operation
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
     * Add Operation
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
     * Add Operation
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
     * Add Operation
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
     * Add Operation
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

    /**
     * Subtract Operation
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
     * Subtract Operation
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
     * Subtract Operation
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
     * Subtract Operation
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
     * Subtract Operation
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
     * Subtract Operation
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
     * Subtract Operation
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

    /**
     * Equality Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left == right</code>
     */
    public static boolean equals(final boolean left,
                                 final boolean right)
    {
        return left == right;
    }

    /**
     * Equality Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left == right</code>
     */
    public static boolean equals(final char left,
                                 final char right)
    {
        return left == right;
    }

    /**
     * Equality Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left == right</code>
     */
    public static boolean equals(final byte left,
                                 final byte right)
    {
        return left == right;
    }

    /**
     * Equality Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left == right</code>
     */
    public static boolean equals(final short left,
                                 final short right)
    {
        return left == right;
    }

    /**
     * Equality Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left == right</code>
     */
    public static boolean equals(final int left,
                                 final int right)
    {
        return left == right;
    }

    /**
     * Equality Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left == right</code>
     */
    public static boolean equals(final long left,
                                 final long right)
    {
        return left == right;
    }

    /**
     * Equality Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left == right</code>
     */
    public static boolean equals(final float left,
                                 final float right)
    {
        return left == right;
    }

    /**
     * Equality Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left == right</code>
     */
    public static boolean equals(final double left,
                                 final double right)
    {
        return left == right;
    }

    /**
     * Equality Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left equals right</code>
     */
    public static boolean equals(final Object left,
                                 final Object right)
    {
        return Objects.equal(left, right);
    }

    /**
     * Inequality Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left != right</code>
     */
    public static boolean notEquals(final boolean left,
                                    final boolean right)
    {
        return left != right;
    }

    /**
     * Inequality Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left != right</code>
     */
    public static boolean notEquals(final char left,
                                    final char right)
    {
        return left != right;
    }

    /**
     * Inequality Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left != right</code>
     */
    public static boolean notEquals(final byte left,
                                    final byte right)
    {
        return left != right;
    }

    /**
     * Inequality Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left != right</code>
     */
    public static boolean notEquals(final short left,
                                    final short right)
    {
        return left != right;
    }

    /**
     * Inequality Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left != right</code>
     */
    public static boolean notEquals(final int left,
                                    final int right)
    {
        return left != right;
    }

    /**
     * Inequality Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left != right</code>
     */
    public static boolean notEquals(final long left,
                                    final long right)
    {
        return left != right;
    }

    /**
     * Inequality Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left != right</code>
     */
    public static boolean notEquals(final float left,
                                    final float right)
    {
        return left != right;
    }

    /**
     * Inequality Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left != right</code>
     */
    public static boolean notEquals(final double left,
                                    final double right)
    {
        return left != right;
    }

    /**
     * Inequality Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left != right</code>
     */
    public static boolean notEquals(final Object left,
                                    final Object right)
    {
        return !Objects.equal(left, right);
    }

    /**
     * Less-Than Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left &lt; right</code>
     */
    public static boolean lessThan(final char left,
                                   final char right)
    {
        return left < right;
    }

    /**
     * Less-Than Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left &lt; right</code>
     */
    public static boolean lessThan(final byte left,
                                   final byte right)
    {
        return left < right;
    }

    /**
     * Less-Than Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left &lt; right</code>
     */
    public static boolean lessThan(final short left,
                                   final short right)
    {
        return left < right;
    }

    /**
     * Less-Than Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left &lt; right</code>
     */
    public static boolean lessThan(final int left,
                                   final int right)
    {
        return left < right;
    }

    /**
     * Less-Than Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left &lt; right</code>
     */
    public static boolean lessThan(final long left,
                                   final long right)
    {
        return left < right;
    }

    /**
     * Less-Than Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left &lt; right</code>
     */
    public static boolean lessThan(final float left,
                                   final float right)
    {
        return left < right;
    }

    /**
     * Less-ThanOperation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left &lt; right</code>
     */
    public static boolean lessThan(final double left,
                                   final double right)
    {
        return left < right;
    }

    /**
     * Less-Than Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left &lt; right</code>
     */
    public static boolean lessThan(final Comparable left,
                                   final Comparable right)
    {
        return left.compareTo(right) < 0;
    }

    /**
     * Less-Than-OR-Equals Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left &lt;= right</code>
     */
    public static boolean lessThanOrEquals(final char left,
                                           final char right)
    {
        return left <= right;
    }

    /**
     * Less-Than-OR-Equals Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left &lt;= right</code>
     */
    public static boolean lessThanOrEquals(final byte left,
                                           final byte right)
    {
        return left <= right;
    }

    /**
     * Less-Than-OR-Equals Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left &lt;= right</code>
     */
    public static boolean lessThanOrEquals(final short left,
                                           final short right)
    {
        return left <= right;
    }

    /**
     * Less-Than-OR-Equals Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left &lt;= right</code>
     */
    public static boolean lessThanOrEquals(final int left,
                                           final int right)
    {
        return left <= right;
    }

    /**
     * Less-Than-OR-Equals Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left &lt;= right</code>
     */
    public static boolean lessThanOrEquals(final long left,
                                           final long right)
    {
        return left <= right;
    }

    /**
     * Less-Than-OR-Equals Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left &lt;= right</code>
     */
    public static boolean lessThanOrEquals(final float left,
                                           final float right)
    {
        return left <= right;
    }

    /**
     * Less-Than-OR-Equals Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left &lt;= right</code>
     */
    public static boolean lessThanOrEquals(final double left,
                                           final double right)
    {
        return left <= right;
    }

    /**
     * Less-Than-OR-Equals Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left &lt;= right</code>
     */
    public static boolean lessThanOrEquals(final Comparable left,
                                           final Comparable right)
    {
        return left.compareTo(right) <= 0;
    }

    /**
     * Greater-Than Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left &gt; right</code>
     */
    public static boolean greaterThan(final char left,
                                      final char right)
    {
        return left > right;
    }

    /**
     * Greater-Than Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left &gt; right</code>
     */
    public static boolean greaterThan(final byte left,
                                      final byte right)
    {
        return left > right;
    }

    /**
     * Greater-Than Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left &gt; right</code>
     */
    public static boolean greaterThan(final short left,
                                      final short right)
    {
        return left > right;
    }

    /**
     * Greater-Than Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left &gt; right</code>
     */
    public static boolean greaterThan(final int left,
                                      final int right)
    {
        return left > right;
    }

    /**
     * Greater-Than Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left &gt; right</code>
     */
    public static boolean greaterThan(final long left,
                                      final long right)
    {
        return left > right;
    }

    /**
     * Greater-Than Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left &gt; right</code>
     */
    public static boolean greaterThan(final float left,
                                      final float right)
    {
        return left > right;
    }

    /**
     * Greater-Than Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left &gt; right</code>
     */
    public static boolean greaterThan(final double left,
                                      final double right)
    {
        return left > right;
    }

    /**
     * Greater-Than Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left &gt; right</code>
     */
    public static boolean greaterThan(final Comparable left,
                                      final Comparable right)
    {
        return left.compareTo(right) > 0;
    }

    /**
     * Greater-Than-OR-Equals Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left &gt;= right</code>
     */
    public static boolean greaterThanOrEquals(final char left,
                                              final char right)
    {
        return left >= right;
    }

    /**
     * Greater-Than-OR-Equals Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left &gt;= right</code>
     */
    public static boolean greaterThanOrEquals(final byte left,
                                              final byte right)
    {
        return left >= right;
    }

    /**
     * Greater-Than-OR-Equals Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left &gt;= right</code>
     */
    public static boolean greaterThanOrEquals(final short left,
                                              final short right)
    {
        return left >= right;
    }

    /**
     * Greater-Than-OR-Equals Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left &gt;= right</code>
     */
    public static boolean greaterThanOrEquals(final int left,
                                              final int right)
    {
        return left >= right;
    }

    /**
     * Greater-Than-OR-Equals Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left &gt;= right</code>
     */
    public static boolean greaterThanOrEquals(final long left,
                                              final long right)
    {
        return left >= right;
    }

    /**
     * Greater-Than-OR-Equals Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left &gt;= right</code>
     */
    public static boolean greaterThanOrEquals(final float left,
                                              final float right)
    {
        return left >= right;
    }

    /**
     * Greater-Than-OR-Equals Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left &gt;= right</code>
     */
    public static boolean greaterThanOrEquals(final double left,
                                              final double right)
    {
        return left >= right;
    }

    /**
     * Greater-Than-OR-Equals Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left &gt;= right</code>
     */
    public static boolean greaterThanOrEquals(final Comparable left,
                                              final Comparable right)
    {
        return left.compareTo(right) >= 0;
    }

    /**
     * Identity Equality Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left == right</code>
     */
    public static boolean identityEquals(final Object left_operand,
                                         final Object right_operand)
    {
        return left_operand == right_operand;
    }

    /**
     * Identity Inequality Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left == right</code>
     */
    public static boolean identityNotEquals(final Object left_operand,
                                            final Object right_operand)
    {
        return left_operand != right_operand;
    }

    /**
     * Negation Operation
     *
     * @param operand is the operand.
     * @return <code>! operand</code>
     */
    public static boolean not(final boolean operand)
    {
        return !operand;
    }

    /**
     * Logical-AND Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left && right</code>
     */
    public static boolean and(final boolean left,
                              final boolean right)
    {
        return left && right;
    }

    /**
     * Logical-OR Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left || right</code>
     */
    public static boolean or(final boolean left,
                             final boolean right)
    {
        return left || right;
    }

    /**
     * Logical-XOR Operation
     *
     * @param left is the left-operand.
     * @param right is the right-operand.
     * @return <code>left ^ right</code>
     */
    public static boolean xor(final boolean left,
                              final boolean right)
    {
        return left ^ right;
    }
}

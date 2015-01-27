package autumn.util;

/**
 * This class provides bitwise manipulations, since the language does not have bitwise operators.
 *
 * <p>
 * <b>Warning: This class is still under development.</b>
 * </p>
 *
 * @author Mackenzie High
 */
public final class Bitwise
{
    /**
     * Sole Constructor.
     */
    private Bitwise()
    {
        // Pass, because this is merely a static utility class.
    }

    /**
     * This method performs a bitwise left-shift operation.
     *
     * @param bits contains the bits to shift.
     * @param count is the distance of the shift.
     * @return the bits shifted by the specified distance.
     */
    public static long shiftLeft(final long bits,
                                 final int count)
    {
        return bits << count;
    }

    /**
     * This method performs a bitwise right-shift operation.
     *
     * @param bits contains the bits to shift.
     * @param count is the distance of the shift.
     * @return the bits shifted by the specified distance.
     */
    public static long shiftRight(final long bits,
                                  final int count)
    {
        return bits >> count;
    }

    /**
     * This method performs a bitwise unsigned-right-shift operation.
     *
     * @param bits contains the bits to shift.
     * @param count is the distance of the shift.
     * @return the bits shifted by the specified distance.
     */
    public static long shiftRightUnsigned(final long bits,
                                          final int count)
    {
        return bits >>> count;
    }

    /**
     * This method sets a single bit.
     *
     * @param bits contains the bit to set.
     * @param index is the index of the bit to set.
     * @param value the new value of the bit.
     * @return the modified version of bits.
     */
    public static long setBit(final long bits,
                              final int index,
                              final boolean value)
    {
        return (1 << index) | bits;
    }

    /**
     * This method gets a single bit.
     *
     * @param bits contains the bit to get.
     * @param index is the index of the bit to get.
     * @return the selected bit.
     */
    public static boolean getBit(final long bits,
                                 final int index)
    {
        return ((bits >> index) & 1) == 1;
    }

    /**
     * This method performs a bitwise negation.
     *
     * @param bits are the bits to negate.
     * @return the negated bits.
     */
    public static long not(final long bits)
    {
        return ~bits;
    }

    /**
     * This method performs a bitwise logical-AND.
     *
     * @param left is the left operand.
     * @param left is the right operand.
     * @return the result.
     */
    public static long and(final long left,
                           final long right)
    {
        return left & right;
    }

    /**
     * This method performs a bitwise logical-OR.
     *
     * @param left is the left operand.
     * @param left is the right operand.
     * @return the result.
     */
    public static long or(final long left,
                          final long right)
    {
        return left | right;
    }

    /**
     * This method performs a bitwise logical-XOR.
     *
     * @param left is the left operand.
     * @param left is the right operand.
     * @return the result.
     */
    public static long xor(final long left,
                           final long right)
    {
        return left ^ right;
    }
}

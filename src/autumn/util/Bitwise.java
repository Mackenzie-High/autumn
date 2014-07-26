package autumn.util;

/**
 * This class provides bitwise manipulations, since the language does not have bitwise operators.
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

    public static long shiftLeft(final long value,
                                 final int count)
    {
        return value << count;
    }

    public static long shiftRight(final long value,
                                  final int count)
    {
        return value >> count;
    }

    public static long shiftRightUnsigned(final long value,
                                          final int count)
    {
        return value >>> count;
    }

    public static long setBit(final long bits,
                              final int index,
                              final boolean value)
    {
        return bits; // TODO
    }

    public static long getBit(final long bits,
                              final int index)
    {
        return bits; // TODO
    }

    public static long and(final long bits)
    {
        return ~bits;
    }

    public static long and(final long left,
                           final long right)
    {
        return left & right;
    }

    public static long or(final long left,
                          final long right)
    {
        return left | right;
    }

    public static long xor(final long left,
                           final long right)
    {
        return left ^ right;
    }
}

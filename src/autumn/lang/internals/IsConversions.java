package autumn.lang.internals;

import com.google.common.base.Preconditions;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * This class provides the predefined is-conversions.
 *
 * @author Mackenzie High
 */
public class IsConversions
{
    private static void check(final Boolean value)
    {
        Preconditions.checkNotNull(value);
    }

    private static void check(final Character value)
    {
        Preconditions.checkNotNull(value);
    }

    private static void check(final Byte value)
    {
        Preconditions.checkNotNull(value);
    }

    private static void check(final Short value)
    {
        Preconditions.checkNotNull(value);
    }

    private static void check(final Integer value)
    {
        Preconditions.checkNotNull(value);
    }

    private static void check(final Long value)
    {
        Preconditions.checkNotNull(value);
    }

    private static void check(final Float value)
    {
        Preconditions.checkNotNull(value);
    }

    private static void check(final Double value)
    {
        Preconditions.checkNotNull(value);
    }

    private static void check(final BigInteger value)
    {
        Preconditions.checkNotNull(value);
    }

    private static void check(final BigDecimal value)
    {
        Preconditions.checkNotNull(value);
    }

    private static void check(final String value)
    {
        Preconditions.checkNotNull(value);
    }

    private static void check(final boolean value,
                              final long minimum,
                              final long maximum)
    {
        // Pass
    }

    private static void check(final boolean value,
                              final double minimum,
                              final double maximum)
    {
        // Pass
    }

    private static void check(final long value,
                              final long minimum,
                              final long maximum)
    {
        Preconditions.checkArgument(value >= minimum, "value < minimum");
        Preconditions.checkArgument(value <= maximum, "value > maximum");
    }

    private static void check(final double value,
                              final double minimum,
                              final double maximum)
    {
        Preconditions.checkArgument(value >= minimum, "value < minimum");
        Preconditions.checkArgument(value <= maximum, "value > maximum");
    }

    private static void check(final Number value,
                              final long minimum,
                              final long maximum)
    {
        Preconditions.checkNotNull(value);
        Preconditions.checkArgument(value.longValue() >= minimum, "value < minimum");
        Preconditions.checkArgument(value.longValue() <= maximum, "value > maximum");
    }

    private static void check(final Number value,
                              final double minimum,
                              final double maximum)
    {
        Preconditions.checkNotNull(value);
        Preconditions.checkArgument(value.doubleValue() >= minimum, "value < minimum");
        Preconditions.checkArgument(value.doubleValue() <= maximum, "value > maximum");
    }

    private static void check(final String value,
                              final long minimum,
                              final long maximum)
    {
        Preconditions.checkNotNull(value);

        // This will throw a NumberFormatException, if the value is not properly formated.
        final BigInteger number = new BigInteger(value);

        Preconditions.checkArgument(number.longValue() >= minimum, "value < minimum");
        Preconditions.checkArgument(number.longValue() <= maximum, "value > maximum");
    }

    private static void check(final String value,
                              final double minimum,
                              final double maximum)
    {
        Preconditions.checkNotNull(value);

        // This will throw a NumberFormatException, if the value is not properly formated.
        final BigDecimal number = new BigDecimal(value);

        Preconditions.checkArgument(number.doubleValue() >= minimum, "value < minimum");
        Preconditions.checkArgument(number.doubleValue() <= maximum, "value > maximum");
    }

    /**
     * This method converts a boolean value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToPrimitiveBoolean(final boolean value)
    {
        // No check is needed.
        return AsConversions.convertToPrimitiveBoolean(value);
    }

    /**
     * This method converts a char value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToPrimitiveBoolean(final char value)
    {
        // No check is needed.
        return AsConversions.convertToPrimitiveBoolean(value);
    }

    /**
     * This method converts a byte value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToPrimitiveBoolean(final byte value)
    {
        // No check is needed.
        return AsConversions.convertToPrimitiveBoolean(value);
    }

    /**
     * This method converts a short value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToPrimitiveBoolean(final short value)
    {
        // No check is needed.
        return AsConversions.convertToPrimitiveBoolean(value);
    }

    /**
     * This method converts an int value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToPrimitiveBoolean(final int value)
    {
        // No check is needed.
        return AsConversions.convertToPrimitiveBoolean(value);
    }

    /**
     * This method converts a long value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToPrimitiveBoolean(final long value)
    {
        // No check is needed.
        return AsConversions.convertToPrimitiveBoolean(value);
    }

    /**
     * This method converts a float value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToPrimitiveBoolean(final float value)
    {
        // No check is needed.
        return AsConversions.convertToPrimitiveBoolean(value);
    }

    /**
     * This method converts a double value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToPrimitiveBoolean(final double value)
    {
        // No check is needed.
        return AsConversions.convertToPrimitiveBoolean(value);
    }

    /**
     * This method converts a Boolean value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToPrimitiveBoolean(final Boolean value)
    {
        check(value);
        return AsConversions.convertToPrimitiveBoolean(value);
    }

    /**
     * This method converts a Character value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToPrimitiveBoolean(final Character value)
    {
        check(value);
        return AsConversions.convertToPrimitiveBoolean(value);
    }

    /**
     * This method converts a Byte value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToPrimitiveBoolean(final Byte value)
    {
        check(value);
        return AsConversions.convertToPrimitiveBoolean(value);
    }

    /**
     * This method converts a Short value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToPrimitiveBoolean(final Short value)
    {
        check(value);
        return AsConversions.convertToPrimitiveBoolean(value);
    }

    /**
     * This method converts an Integer value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToPrimitiveBoolean(final Integer value)
    {
        check(value);
        return AsConversions.convertToPrimitiveBoolean(value);
    }

    /**
     * This method converts a Long value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToPrimitiveBoolean(final Long value)
    {
        check(value);
        return AsConversions.convertToPrimitiveBoolean(value);
    }

    /**
     * This method converts a Float value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToPrimitiveBoolean(final Float value)
    {
        check(value);
        return AsConversions.convertToPrimitiveBoolean(value);
    }

    /**
     * This method converts a Double value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToPrimitiveBoolean(final Double value)
    {
        check(value);
        return AsConversions.convertToPrimitiveBoolean(value);
    }

    /**
     * This method converts a BigInteger value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToPrimitiveBoolean(final BigInteger value)
    {
        check(value);
        return AsConversions.convertToPrimitiveBoolean(value);
    }

    /**
     * This method converts a BigDecimal value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToPrimitiveBoolean(final BigDecimal value)
    {
        check(value);
        return AsConversions.convertToPrimitiveBoolean(value);
    }

    /**
     * This method converts a String value to a boolean value.
     *
     * <p>
     * If the value equals "true", when case is ignored, then true is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static boolean convertToPrimitiveBoolean(final String value)
    {
        check(value);
        return AsConversions.convertToPrimitiveBoolean(value);
    }

    /**
     * This method converts a boolean value to a char value.
     *
     * @param value is the value to convert.
     */
    public static char convertToPrimitiveChar(final boolean value)
    {
        check(value, Character.MIN_VALUE, Character.MAX_VALUE);
        return AsConversions.convertToPrimitiveChar(value);
    }

    /**
     * This method converts a char value to a char value.
     *
     * @param value is the value to convert.
     */
    public static char convertToPrimitiveChar(final char value)
    {
        check(value, Character.MIN_VALUE, Character.MAX_VALUE);
        return AsConversions.convertToPrimitiveChar(value);
    }

    /**
     * This method converts a byte value to a char value.
     *
     * @param value is the value to convert.
     */
    public static char convertToPrimitiveChar(final byte value)
    {
        check(value, Character.MIN_VALUE, Character.MAX_VALUE);
        return AsConversions.convertToPrimitiveChar(value);
    }

    /**
     * This method converts a short value to a char value.
     *
     * @param value is the value to convert.
     */
    public static char convertToPrimitiveChar(final short value)
    {
        check(value, Character.MIN_VALUE, Character.MAX_VALUE);
        return AsConversions.convertToPrimitiveChar(value);
    }

    /**
     * This method converts an int value to a char value.
     *
     * @param value is the value to convert.
     */
    public static char convertToPrimitiveChar(final int value)
    {
        check(value, Character.MIN_VALUE, Character.MAX_VALUE);
        return AsConversions.convertToPrimitiveChar(value);
    }

    /**
     * This method converts a long value to a char value.
     *
     * @param value is the value to convert.
     */
    public static char convertToPrimitiveChar(final long value)
    {
        check(value, Character.MIN_VALUE, Character.MAX_VALUE);
        return AsConversions.convertToPrimitiveChar(value);
    }

    /**
     * This method converts a float value to a char value.
     *
     * @param value is the value to convert.
     */
    public static char convertToPrimitiveChar(final float value)
    {
        check(value, Character.MIN_VALUE, Character.MAX_VALUE);
        return AsConversions.convertToPrimitiveChar(value);
    }

    /**
     * This method converts a double value to a char value.
     *
     * @param value is the value to convert.
     */
    public static char convertToPrimitiveChar(final double value)
    {
        check(value, Character.MIN_VALUE, Character.MAX_VALUE);
        return AsConversions.convertToPrimitiveChar(value);
    }

    /**
     * This method converts a Boolean value to a char value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static char convertToPrimitiveChar(final Boolean value)
    {
        check(value, Character.MIN_VALUE, Character.MAX_VALUE);
        return AsConversions.convertToPrimitiveChar(value);
    }

    /**
     * This method converts a Character value to a char value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static char convertToPrimitiveChar(final Character value)
    {
        check(value, Character.MIN_VALUE, Character.MAX_VALUE);
        return AsConversions.convertToPrimitiveChar(value);
    }

    /**
     * This method converts a Byte value to a char value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static char convertToPrimitiveChar(final Byte value)
    {
        check(value, Character.MIN_VALUE, Character.MAX_VALUE);
        return AsConversions.convertToPrimitiveChar(value);
    }

    /**
     * This method converts a Short value to a char value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static char convertToPrimitiveChar(final Short value)
    {
        check(value, Character.MIN_VALUE, Character.MAX_VALUE);
        return AsConversions.convertToPrimitiveChar(value);
    }

    /**
     * This method converts an Integer value to a char value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static char convertToPrimitiveChar(final Integer value)
    {
        check(value, Character.MIN_VALUE, Character.MAX_VALUE);
        return AsConversions.convertToPrimitiveChar(value);
    }

    /**
     * This method converts a Long value to a char value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static char convertToPrimitiveChar(final Long value)
    {
        check(value, Character.MIN_VALUE, Character.MAX_VALUE);
        return AsConversions.convertToPrimitiveChar(value);
    }

    /**
     * This method converts a Float value to a char value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static char convertToPrimitiveChar(final Float value)
    {
        check(value, Character.MIN_VALUE, Character.MAX_VALUE);
        return AsConversions.convertToPrimitiveChar(value);
    }

    /**
     * This method converts a Double value to a char value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static char convertToPrimitiveChar(final Double value)
    {
        check(value, Character.MIN_VALUE, Character.MAX_VALUE);
        return AsConversions.convertToPrimitiveChar(value);
    }

    /**
     * This method converts a BigInteger value to a char value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static char convertToPrimitiveChar(final BigInteger value)
    {
        check(value, Character.MIN_VALUE, Character.MAX_VALUE);
        return AsConversions.convertToPrimitiveChar(value);
    }

    /**
     * This method converts a BigDecimal value to a char value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static char convertToPrimitiveChar(final BigDecimal value)
    {
        check(value, Character.MIN_VALUE, Character.MAX_VALUE);
        return AsConversions.convertToPrimitiveChar(value);
    }

    /**
     * This method converts a String value to a char value.
     *
     * <p>
     * If the value is null, then zero is returned. <br>
     * If the value is an empty string, then zero is returned. <br>
     * Otherwise, the first character of the string is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static char convertToPrimitiveChar(final String value)
    {
        check(value, Character.MIN_VALUE, Character.MAX_VALUE);
        return AsConversions.convertToPrimitiveChar(value);
    }

    /**
     * This method converts an Object value to a char value, if the Object is really a Character.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * <p>
     * This method tries to cast the value to a Character and then unbox the result.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static char convertToPrimitiveChar(final Object value)
    {
        return AsConversions.convertToPrimitiveChar((Character) value);
    }

    /**
     * This method converts a boolean value to a byte value.
     *
     * @param value is the value to convert.
     */
    public static byte convertToPrimitiveByte(final boolean value)
    {
        check(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
        return AsConversions.convertToPrimitiveByte(value);
    }

    /**
     * This method converts a char value to a byte value.
     *
     * @param value is the value to convert.
     */
    public static byte convertToPrimitiveByte(final char value)
    {
        check(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
        return AsConversions.convertToPrimitiveByte(value);
    }

    /**
     * This method converts a byte value to a byte value.
     *
     * @param value is the value to convert.
     */
    public static byte convertToPrimitiveByte(final byte value)
    {
        check(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
        return AsConversions.convertToPrimitiveByte(value);
    }

    /**
     * This method converts a short value to a byte value.
     *
     * @param value is the value to convert.
     */
    public static byte convertToPrimitiveByte(final short value)
    {
        check(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
        return AsConversions.convertToPrimitiveByte(value);
    }

    /**
     * This method converts an int value to a byte value.
     *
     * @param value is the value to convert.
     */
    public static byte convertToPrimitiveByte(final int value)
    {
        check(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
        return AsConversions.convertToPrimitiveByte(value);
    }

    /**
     * This method converts a long value to a byte value.
     *
     * @param value is the value to convert.
     */
    public static byte convertToPrimitiveByte(final long value)
    {
        check(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
        return AsConversions.convertToPrimitiveByte(value);
    }

    /**
     * This method converts a float value to a byte value.
     *
     * @param value is the value to convert.
     */
    public static byte convertToPrimitiveByte(final float value)
    {
        check(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
        return AsConversions.convertToPrimitiveByte(value);
    }

    /**
     * This method converts a double value to a byte value.
     *
     * @param value is the value to convert.
     */
    public static byte convertToPrimitiveByte(final double value)
    {
        check(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
        return AsConversions.convertToPrimitiveByte(value);
    }

    /**
     * This method converts a Boolean value to a byte value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static byte convertToPrimitiveByte(final Boolean value)
    {
        check(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
        return AsConversions.convertToPrimitiveByte(value);
    }

    /**
     * This method converts a Character value to a byte value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static byte convertToPrimitiveByte(final Character value)
    {
        check(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
        return AsConversions.convertToPrimitiveByte(value);
    }

    /**
     * This method converts a Byte value to a byte value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static byte convertToPrimitiveByte(final Byte value)
    {
        check(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
        return AsConversions.convertToPrimitiveByte(value);
    }

    /**
     * This method converts a Short value to a byte value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static byte convertToPrimitiveByte(final Short value)
    {
        check(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
        return AsConversions.convertToPrimitiveByte(value);
    }

    /**
     * This method converts an Integer value to a byte value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static byte convertToPrimitiveByte(final Integer value)
    {
        check(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
        return AsConversions.convertToPrimitiveByte(value);
    }

    /**
     * This method converts a Long value to a byte value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static byte convertToPrimitiveByte(final Long value)
    {
        check(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
        return AsConversions.convertToPrimitiveByte(value);
    }

    /**
     * This method converts a Float value to a byte value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static byte convertToPrimitiveByte(final Float value)
    {
        check(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
        return AsConversions.convertToPrimitiveByte(value);
    }

    /**
     * This method converts a Double value to a byte value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static byte convertToPrimitiveByte(final Double value)
    {
        check(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
        return AsConversions.convertToPrimitiveByte(value);
    }

    /**
     * This method converts a BigInteger value to a byte value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static byte convertToPrimitiveByte(final BigInteger value)
    {
        check(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
        return AsConversions.convertToPrimitiveByte(value);
    }

    /**
     * This method converts a BigDecimal value to a byte value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static byte convertToPrimitiveByte(final BigDecimal value)
    {
        check(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
        return AsConversions.convertToPrimitiveByte(value);
    }

    /**
     * This method converts a String value to a byte value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static byte convertToPrimitiveByte(final String value)
    {
        check(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
        return AsConversions.convertToPrimitiveByte(value);
    }

    /**
     * This method converts an Object value to a byte value, if the Object is really a Byte.
     *
     * <p>
     * If the value is null, then zero is returned. <br>
     * </p>
     *
     * <p>
     * This method tries to cast the value to a Byte and then unbox the result.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static byte convertToPrimitiveByte(final Object value)
    {
        return AsConversions.convertToPrimitiveByte((Byte) value);
    }

    /**
     * This method converts a boolean value to a short value.
     *
     * @param value is the value to convert.
     */
    public static short convertToPrimitiveShort(final boolean value)
    {
        check(value, Short.MIN_VALUE, Short.MAX_VALUE);
        return AsConversions.convertToPrimitiveShort(value);
    }

    /**
     * This method converts a char value to a short value.
     *
     * @param value is the value to convert.
     */
    public static short convertToPrimitiveShort(final char value)
    {
        check(value, Short.MIN_VALUE, Short.MAX_VALUE);
        return AsConversions.convertToPrimitiveShort(value);
    }

    /**
     * This method converts a byte value to a short value.
     *
     * @param value is the value to convert.
     */
    public static short convertToPrimitiveShort(final byte value)
    {
        check(value, Short.MIN_VALUE, Short.MAX_VALUE);
        return AsConversions.convertToPrimitiveShort(value);
    }

    /**
     * This method converts a short value to a short value.
     *
     * @param value is the value to convert.
     */
    public static short convertToPrimitiveShort(final short value)
    {
        check(value, Short.MIN_VALUE, Short.MAX_VALUE);
        return AsConversions.convertToPrimitiveShort(value);
    }

    /**
     * This method converts an int value to a short value.
     *
     * @param value is the value to convert.
     */
    public static short convertToPrimitiveShort(final int value)
    {
        check(value, Short.MIN_VALUE, Short.MAX_VALUE);
        return AsConversions.convertToPrimitiveShort(value);
    }

    /**
     * This method converts a long value to a short value.
     *
     * @param value is the value to convert.
     */
    public static short convertToPrimitiveShort(final long value)
    {
        check(value, Short.MIN_VALUE, Short.MAX_VALUE);
        return AsConversions.convertToPrimitiveShort(value);
    }

    /**
     * This method converts a float value to a short value.
     *
     * @param value is the value to convert.
     */
    public static short convertToPrimitiveShort(final float value)
    {
        check(value, Short.MIN_VALUE, Short.MAX_VALUE);
        return AsConversions.convertToPrimitiveShort(value);
    }

    /**
     * This method converts a double value to a short value.
     *
     * @param value is the value to convert.
     */
    public static short convertToPrimitiveShort(final double value)
    {
        check(value, Short.MIN_VALUE, Short.MAX_VALUE);
        return AsConversions.convertToPrimitiveShort(value);
    }

    /**
     * This method converts a Boolean value to a short value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static short convertToPrimitiveShort(final Boolean value)
    {
        check(value, Short.MIN_VALUE, Short.MAX_VALUE);
        return AsConversions.convertToPrimitiveShort(value);
    }

    /**
     * This method converts a Character value to a short value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static short convertToPrimitiveShort(final Character value)
    {
        check(value, Short.MIN_VALUE, Short.MAX_VALUE);
        return AsConversions.convertToPrimitiveShort(value);
    }

    /**
     * This method converts a Byte value to a short value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static short convertToPrimitiveShort(final Byte value)
    {
        check(value, Short.MIN_VALUE, Short.MAX_VALUE);
        return AsConversions.convertToPrimitiveShort(value);
    }

    /**
     * This method converts a Short value to a short value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static short convertToPrimitiveShort(final Short value)
    {
        check(value, Short.MIN_VALUE, Short.MAX_VALUE);
        return AsConversions.convertToPrimitiveShort(value);
    }

    /**
     * This method converts an Integer value to a short value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static short convertToPrimitiveShort(final Integer value)
    {
        check(value, Short.MIN_VALUE, Short.MAX_VALUE);
        return AsConversions.convertToPrimitiveShort(value);
    }

    /**
     * This method converts a Long value to a short value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static short convertToPrimitiveShort(final Long value)
    {
        check(value, Short.MIN_VALUE, Short.MAX_VALUE);
        return AsConversions.convertToPrimitiveShort(value);
    }

    /**
     * This method converts a Float value to a short value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static short convertToPrimitiveShort(final Float value)
    {
        check(value, Short.MIN_VALUE, Short.MAX_VALUE);
        return AsConversions.convertToPrimitiveShort(value);
    }

    /**
     * This method converts a Double value to a short value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static short convertToPrimitiveShort(final Double value)
    {
        check(value, Short.MIN_VALUE, Short.MAX_VALUE);
        return AsConversions.convertToPrimitiveShort(value);
    }

    /**
     * This method converts a BigInteger value to a short value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static short convertToPrimitiveShort(final BigInteger value)
    {
        check(value, Short.MIN_VALUE, Short.MAX_VALUE);
        return AsConversions.convertToPrimitiveShort(value);
    }

    /**
     * This method converts a BigDecimal value to a short value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static short convertToPrimitiveShort(final BigDecimal value)
    {
        check(value, Short.MIN_VALUE, Short.MAX_VALUE);
        return AsConversions.convertToPrimitiveShort(value);
    }

    /**
     * This method converts a String value to a short value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static short convertToPrimitiveShort(final String value)
    {
        check(value, Short.MIN_VALUE, Short.MAX_VALUE);
        return AsConversions.convertToPrimitiveShort(value);
    }

    /**
     * This method converts an Object value to a short value, if the Object is really a Short.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * <p>
     * This method tries to cast the value to a Short and then unbox the result.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static short convertToPrimitiveShort(final Object value)
    {
        return AsConversions.convertToPrimitiveShort((Short) value);
    }

    /**
     * This method converts a boolean value to a int value.
     *
     * @param value is the value to convert.
     */
    public static int convertToPrimitiveInt(final boolean value)
    {
        check(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return AsConversions.convertToPrimitiveInt(value);
    }

    /**
     * This method converts a char value to a int value.
     *
     * @param value is the value to convert.
     */
    public static int convertToPrimitiveInt(final char value)
    {
        check(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return AsConversions.convertToPrimitiveInt(value);
    }

    /**
     * This method converts a byte value to a int value.
     *
     * @param value is the value to convert.
     */
    public static int convertToPrimitiveInt(final byte value)
    {
        check(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return AsConversions.convertToPrimitiveInt(value);
    }

    /**
     * This method converts a short value to a int value.
     *
     * @param value is the value to convert.
     */
    public static int convertToPrimitiveInt(final short value)
    {
        check(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return AsConversions.convertToPrimitiveInt(value);
    }

    /**
     * This method converts an int value to a int value.
     *
     * @param value is the value to convert.
     */
    public static int convertToPrimitiveInt(final int value)
    {
        check(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return AsConversions.convertToPrimitiveInt(value);
    }

    /**
     * This method converts a long value to a int value.
     *
     * @param value is the value to convert.
     */
    public static int convertToPrimitiveInt(final long value)
    {
        check(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return AsConversions.convertToPrimitiveInt(value);
    }

    /**
     * This method converts a float value to a int value.
     *
     * @param value is the value to convert.
     */
    public static int convertToPrimitiveInt(final float value)
    {
        check(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return AsConversions.convertToPrimitiveInt(value);
    }

    /**
     * This method converts a double value to a int value.
     *
     * @param value is the value to convert.
     */
    public static int convertToPrimitiveInt(final double value)
    {
        check(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return AsConversions.convertToPrimitiveInt(value);
    }

    /**
     * This method converts a Boolean value to a int value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static int convertToPrimitiveInt(final Boolean value)
    {
        check(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return AsConversions.convertToPrimitiveInt(value);
    }

    /**
     * This method converts a Character value to a int value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static int convertToPrimitiveInt(final Character value)
    {
        check(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return AsConversions.convertToPrimitiveInt(value);
    }

    /**
     * This method converts a Byte value to a int value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static int convertToPrimitiveInt(final Byte value)
    {
        check(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return AsConversions.convertToPrimitiveInt(value);
    }

    /**
     * This method converts a Short value to a int value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static int convertToPrimitiveInt(final Short value)
    {
        check(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return AsConversions.convertToPrimitiveInt(value);
    }

    /**
     * This method converts an Integer value to a int value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static int convertToPrimitiveInt(final Integer value)
    {
        check(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return AsConversions.convertToPrimitiveInt(value);
    }

    /**
     * This method converts a Long value to a int value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static int convertToPrimitiveInt(final Long value)
    {
        check(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return AsConversions.convertToPrimitiveInt(value);
    }

    /**
     * This method converts a Float value to a int value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static int convertToPrimitiveInt(final Float value)
    {
        check(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return AsConversions.convertToPrimitiveInt(value);
    }

    /**
     * This method converts a Double value to a int value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static int convertToPrimitiveInt(final Double value)
    {
        check(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return AsConversions.convertToPrimitiveInt(value);
    }

    /**
     * This method converts a BigInteger value to a int value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static int convertToPrimitiveInt(final BigInteger value)
    {
        check(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return AsConversions.convertToPrimitiveInt(value);
    }

    /**
     * This method converts a BigDecimal value to a int value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static int convertToPrimitiveInt(final BigDecimal value)
    {
        check(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return AsConversions.convertToPrimitiveInt(value);
    }

    /**
     * This method converts a String value to a int value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static int convertToPrimitiveInt(final String value)
    {
        check(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return AsConversions.convertToPrimitiveInt(value);
    }

    /**
     * This method converts an Object value to a int value, if the Object is really a Integer.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * <p>
     * This method tries to cast the value to a Integer and then unbox the result.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static int convertToPrimitiveInt(final Object value)
    {
        return AsConversions.convertToPrimitiveInt((Integer) value);
    }

    /**
     * This method converts a boolean value to a long value.
     *
     * @param value is the value to convert.
     */
    public static long convertToPrimitiveLong(final boolean value)
    {
        check(value, Long.MIN_VALUE, Long.MAX_VALUE);
        return AsConversions.convertToPrimitiveLong(value);
    }

    /**
     * This method converts a char value to a long value.
     *
     * @param value is the value to convert.
     */
    public static long convertToPrimitiveLong(final char value)
    {
        check(value, Long.MIN_VALUE, Long.MAX_VALUE);
        return AsConversions.convertToPrimitiveLong(value);
    }

    /**
     * This method converts a byte value to a long value.
     *
     * @param value is the value to convert.
     */
    public static long convertToPrimitiveLong(final byte value)
    {
        check(value, Long.MIN_VALUE, Long.MAX_VALUE);
        return AsConversions.convertToPrimitiveLong(value);
    }

    /**
     * This method converts a short value to a long value.
     *
     * @param value is the value to convert.
     */
    public static long convertToPrimitiveLong(final short value)
    {
        check(value, Long.MIN_VALUE, Long.MAX_VALUE);
        return AsConversions.convertToPrimitiveLong(value);
    }

    /**
     * This method converts an int value to a long value.
     *
     * @param value is the value to convert.
     */
    public static long convertToPrimitiveLong(final int value)
    {
        check(value, Long.MIN_VALUE, Long.MAX_VALUE);
        return AsConversions.convertToPrimitiveLong(value);
    }

    /**
     * This method converts a long value to a long value.
     *
     * @param value is the value to convert.
     */
    public static long convertToPrimitiveLong(final long value)
    {
        check(value, Long.MIN_VALUE, Long.MAX_VALUE);
        return AsConversions.convertToPrimitiveLong(value);
    }

    /**
     * This method converts a float value to a long value.
     *
     * @param value is the value to convert.
     */
    public static long convertToPrimitiveLong(final float value)
    {
        check(value, Long.MIN_VALUE, Long.MAX_VALUE);
        return AsConversions.convertToPrimitiveLong(value);
    }

    /**
     * This method converts a double value to a long value.
     *
     * @param value is the value to convert.
     */
    public static long convertToPrimitiveLong(final double value)
    {
        check(value, Long.MIN_VALUE, Long.MAX_VALUE);
        return AsConversions.convertToPrimitiveLong(value);
    }

    /**
     * This method converts a Boolean value to a long value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static long convertToPrimitiveLong(final Boolean value)
    {
        check(value, Long.MIN_VALUE, Long.MAX_VALUE);
        return AsConversions.convertToPrimitiveLong(value);
    }

    /**
     * This method converts a Character value to a long value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static long convertToPrimitiveLong(final Character value)
    {
        check(value, Long.MIN_VALUE, Long.MAX_VALUE);
        return AsConversions.convertToPrimitiveLong(value);
    }

    /**
     * This method converts a Byte value to a long value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static long convertToPrimitiveLong(final Byte value)
    {
        check(value, Long.MIN_VALUE, Long.MAX_VALUE);
        return AsConversions.convertToPrimitiveLong(value);
    }

    /**
     * This method converts a Short value to a long value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static long convertToPrimitiveLong(final Short value)
    {
        check(value, Long.MIN_VALUE, Long.MAX_VALUE);
        return AsConversions.convertToPrimitiveLong(value);
    }

    /**
     * This method converts an Integer value to a long value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static long convertToPrimitiveLong(final Integer value)
    {
        check(value, Long.MIN_VALUE, Long.MAX_VALUE);
        return AsConversions.convertToPrimitiveLong(value);
    }

    /**
     * This method converts a Long value to a long value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static long convertToPrimitiveLong(final Long value)
    {
        check(value, Long.MIN_VALUE, Long.MAX_VALUE);
        return AsConversions.convertToPrimitiveLong(value);
    }

    /**
     * This method converts a Float value to a long value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static long convertToPrimitiveLong(final Float value)
    {
        check(value, Long.MIN_VALUE, Long.MAX_VALUE);
        return AsConversions.convertToPrimitiveLong(value);
    }

    /**
     * This method converts a Double value to a long value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static long convertToPrimitiveLong(final Double value)
    {
        check(value, Long.MIN_VALUE, Long.MAX_VALUE);
        return AsConversions.convertToPrimitiveLong(value);
    }

    /**
     * This method converts a BigInteger value to a long value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static long convertToPrimitiveLong(final BigInteger value)
    {
        check(value, Long.MIN_VALUE, Long.MAX_VALUE);
        return AsConversions.convertToPrimitiveLong(value);
    }

    /**
     * This method converts a BigDecimal value to a long value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static long convertToPrimitiveLong(final BigDecimal value)
    {
        check(value, Long.MIN_VALUE, Long.MAX_VALUE);
        return AsConversions.convertToPrimitiveLong(value);
    }

    /**
     * This method converts a String value to a long value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static long convertToPrimitiveLong(final String value)
    {
        check(value, Long.MIN_VALUE, Long.MAX_VALUE);
        return AsConversions.convertToPrimitiveLong(value);
    }

    /**
     * This method converts an Object value to a long value, if the Object is really a Long.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * <p>
     * This method tries to cast the value to a Long and then unbox the result.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static long convertToPrimitiveLong(final Object value)
    {
        return AsConversions.convertToPrimitiveLong((Long) value);
    }

    /**
     * This method converts a boolean value to a float value.
     *
     * @param value is the value to convert.
     */
    public static float convertToPrimitiveFloat(final boolean value)
    {
        check(value, Float.MIN_VALUE, Float.MAX_VALUE);
        return AsConversions.convertToPrimitiveFloat(value);
    }

    /**
     * This method converts a char value to a float value.
     *
     * @param value is the value to convert.
     */
    public static float convertToPrimitiveFloat(final char value)
    {
        check(value, Float.MIN_VALUE, Float.MAX_VALUE);
        return AsConversions.convertToPrimitiveFloat(value);
    }

    /**
     * This method converts a byte value to a float value.
     *
     * @param value is the value to convert.
     */
    public static float convertToPrimitiveFloat(final byte value)
    {
        check(value, Float.MIN_VALUE, Float.MAX_VALUE);
        return AsConversions.convertToPrimitiveFloat(value);
    }

    /**
     * This method converts a short value to a float value.
     *
     * @param value is the value to convert.
     */
    public static float convertToPrimitiveFloat(final short value)
    {
        check(value, Float.MIN_VALUE, Float.MAX_VALUE);
        return AsConversions.convertToPrimitiveFloat(value);
    }

    /**
     * This method converts an int value to a float value.
     *
     * @param value is the value to convert.
     */
    public static float convertToPrimitiveFloat(final int value)
    {
        check(value, Float.MIN_VALUE, Float.MAX_VALUE);
        return AsConversions.convertToPrimitiveFloat(value);
    }

    /**
     * This method converts a long value to a float value.
     *
     * @param value is the value to convert.
     */
    public static float convertToPrimitiveFloat(final long value)
    {
        check(value, Float.MIN_VALUE, Float.MAX_VALUE);
        return AsConversions.convertToPrimitiveFloat(value);
    }

    /**
     * This method converts a float value to a float value.
     *
     * @param value is the value to convert.
     */
    public static float convertToPrimitiveFloat(final float value)
    {
        check(value, Float.MIN_VALUE, Float.MAX_VALUE);
        return AsConversions.convertToPrimitiveFloat(value);
    }

    /**
     * This method converts a double value to a float value.
     *
     * @param value is the value to convert.
     */
    public static float convertToPrimitiveFloat(final double value)
    {
        check(value, Float.MIN_VALUE, Float.MAX_VALUE);
        return AsConversions.convertToPrimitiveFloat(value);
    }

    /**
     * This method converts a Boolean value to a float value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static float convertToPrimitiveFloat(final Boolean value)
    {
        check(value, Float.MIN_VALUE, Float.MAX_VALUE);
        return AsConversions.convertToPrimitiveFloat(value);
    }

    /**
     * This method converts a Character value to a float value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static float convertToPrimitiveFloat(final Character value)
    {
        check(value, Float.MIN_VALUE, Float.MAX_VALUE);
        return AsConversions.convertToPrimitiveFloat(value);
    }

    /**
     * This method converts a Byte value to a float value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static float convertToPrimitiveFloat(final Byte value)
    {
        check(value, Float.MIN_VALUE, Float.MAX_VALUE);
        return AsConversions.convertToPrimitiveFloat(value);
    }

    /**
     * This method converts a Short value to a float value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static float convertToPrimitiveFloat(final Short value)
    {
        check(value, Float.MIN_VALUE, Float.MAX_VALUE);
        return AsConversions.convertToPrimitiveFloat(value);
    }

    /**
     * This method converts an Integer value to a float value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static float convertToPrimitiveFloat(final Integer value)
    {
        check(value, Float.MIN_VALUE, Float.MAX_VALUE);
        return AsConversions.convertToPrimitiveFloat(value);
    }

    /**
     * This method converts a Long value to a float value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static float convertToPrimitiveFloat(final Long value)
    {
        check(value, Float.MIN_VALUE, Float.MAX_VALUE);
        return AsConversions.convertToPrimitiveFloat(value);
    }

    /**
     * This method converts a Float value to a float value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static float convertToPrimitiveFloat(final Float value)
    {
        check(value, Float.MIN_VALUE, Float.MAX_VALUE);
        return AsConversions.convertToPrimitiveFloat(value);
    }

    /**
     * This method converts a Double value to a float value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static float convertToPrimitiveFloat(final Double value)
    {
        check(value, Float.MIN_VALUE, Float.MAX_VALUE);
        return AsConversions.convertToPrimitiveFloat(value);
    }

    /**
     * This method converts a BigInteger value to a float value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static float convertToPrimitiveFloat(final BigInteger value)
    {
        check(value, Float.MIN_VALUE, Float.MAX_VALUE);
        return AsConversions.convertToPrimitiveFloat(value);
    }

    /**
     * This method converts a BigDecimal value to a float value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static float convertToPrimitiveFloat(final BigDecimal value)
    {
        check(value, Float.MIN_VALUE, Float.MAX_VALUE);
        return AsConversions.convertToPrimitiveFloat(value);
    }

    /**
     * This method converts a String value to a float value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static float convertToPrimitiveFloat(final String value)
    {
        check(value, Float.MIN_VALUE, Float.MAX_VALUE);
        return AsConversions.convertToPrimitiveFloat(value);
    }

    /**
     * This method converts an Object value to a float value, if the Object is really a Float.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * <p>
     * This method tries to cast the value to a Float and then unbox the result.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static float convertToPrimitiveFloat(final Object value)
    {
        return AsConversions.convertToPrimitiveFloat((Float) value);
    }

    /**
     * This method converts a boolean value to a double value.
     *
     * @param value is the value to convert.
     */
    public static double convertToPrimitiveDouble(final boolean value)
    {
        check(value, Double.MIN_VALUE, Double.MAX_VALUE);
        return AsConversions.convertToPrimitiveDouble(value);
    }

    /**
     * This method converts a char value to a double value.
     *
     * @param value is the value to convert.
     */
    public static double convertToPrimitiveDouble(final char value)
    {
        check(value, Double.MIN_VALUE, Double.MAX_VALUE);
        return AsConversions.convertToPrimitiveDouble(value);
    }

    /**
     * This method converts a byte value to a double value.
     *
     * @param value is the value to convert.
     */
    public static double convertToPrimitiveDouble(final byte value)
    {
        check(value, Double.MIN_VALUE, Double.MAX_VALUE);
        return AsConversions.convertToPrimitiveDouble(value);
    }

    /**
     * This method converts a short value to a double value.
     *
     * @param value is the value to convert.
     */
    public static double convertToPrimitiveDouble(final short value)
    {
        check(value, Double.MIN_VALUE, Double.MAX_VALUE);
        return AsConversions.convertToPrimitiveDouble(value);
    }

    /**
     * This method converts an int value to a double value.
     *
     * @param value is the value to convert.
     */
    public static double convertToPrimitiveDouble(final int value)
    {
        check(value, Double.MIN_VALUE, Double.MAX_VALUE);
        return AsConversions.convertToPrimitiveDouble(value);
    }

    /**
     * This method converts a long value to a double value.
     *
     * @param value is the value to convert.
     */
    public static double convertToPrimitiveDouble(final long value)
    {
        check(value, Double.MIN_VALUE, Double.MAX_VALUE);
        return AsConversions.convertToPrimitiveDouble(value);
    }

    /**
     * This method converts a float value to a double value.
     *
     * @param value is the value to convert.
     */
    public static double convertToPrimitiveDouble(final float value)
    {
        check(value, Double.MIN_VALUE, Double.MAX_VALUE);
        return AsConversions.convertToPrimitiveDouble(value);
    }

    /**
     * This method converts a double value to a double value.
     *
     * @param value is the value to convert.
     */
    public static double convertToPrimitiveDouble(final double value)
    {
        check(value, Double.MIN_VALUE, Double.MAX_VALUE);
        return AsConversions.convertToPrimitiveDouble(value);
    }

    /**
     * This method converts a Boolean value to a double value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static double convertToPrimitiveDouble(final Boolean value)
    {
        check(value, Double.MIN_VALUE, Double.MAX_VALUE);
        return AsConversions.convertToPrimitiveDouble(value);
    }

    /**
     * This method converts a Character value to a double value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static double convertToPrimitiveDouble(final Character value)
    {
        check(value, Double.MIN_VALUE, Double.MAX_VALUE);
        return AsConversions.convertToPrimitiveDouble(value);
    }

    /**
     * This method converts a Byte value to a double value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static double convertToPrimitiveDouble(final Byte value)
    {
        check(value, Double.MIN_VALUE, Double.MAX_VALUE);
        return AsConversions.convertToPrimitiveDouble(value);
    }

    /**
     * This method converts a Short value to a double value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static double convertToPrimitiveDouble(final Short value)
    {
        check(value, Double.MIN_VALUE, Double.MAX_VALUE);
        return AsConversions.convertToPrimitiveDouble(value);
    }

    /**
     * This method converts an Integer value to a double value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static double convertToPrimitiveDouble(final Integer value)
    {
        check(value, Double.MIN_VALUE, Double.MAX_VALUE);
        return AsConversions.convertToPrimitiveDouble(value);
    }

    /**
     * This method converts a Long value to a double value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static double convertToPrimitiveDouble(final Long value)
    {
        check(value, Double.MIN_VALUE, Double.MAX_VALUE);
        return AsConversions.convertToPrimitiveDouble(value);
    }

    /**
     * This method converts a Float value to a double value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static double convertToPrimitiveDouble(final Float value)
    {
        check(value, Double.MIN_VALUE, Double.MAX_VALUE);
        return AsConversions.convertToPrimitiveDouble(value);
    }

    /**
     * This method converts a Double value to a double value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static double convertToPrimitiveDouble(final Double value)
    {
        check(value, Double.MIN_VALUE, Double.MAX_VALUE);
        return AsConversions.convertToPrimitiveDouble(value);
    }

    /**
     * This method converts a BigInteger value to a double value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static double convertToPrimitiveDouble(final BigInteger value)
    {
        check(value, Double.MIN_VALUE, Double.MAX_VALUE);
        return AsConversions.convertToPrimitiveDouble(value);
    }

    /**
     * This method converts a BigDecimal value to a double value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static double convertToPrimitiveDouble(final BigDecimal value)
    {
        check(value, Double.MIN_VALUE, Double.MAX_VALUE);
        return AsConversions.convertToPrimitiveDouble(value);
    }

    /**
     * This method converts a String value to a double value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static double convertToPrimitiveDouble(final String value)
    {
        check(value, Double.MIN_VALUE, Double.MAX_VALUE);
        return AsConversions.convertToPrimitiveDouble(value);
    }

    /**
     * This method converts an Object value to a double value, if the Object is really a Double.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * <p>
     * This method tries to cast the value to a Double and then unbox the result.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static double convertToPrimitiveDouble(final Object value)
    {
        return AsConversions.convertToPrimitiveDouble((Double) value);
    }

    /**
     * This method converts a boolean value to a Boolean value.
     *
     * @param value is the value to convert.
     */
    public static Boolean convertToBoxedBoolean(final boolean value)
    {
        return AsConversions.convertToBoxedBoolean(value);
    }

    /**
     * This method converts a char value to a Boolean value.
     *
     * @param value is the value to convert.
     */
    public static Boolean convertToBoxedBoolean(final char value)
    {
        return AsConversions.convertToBoxedBoolean(value);
    }

    /**
     * This method converts a byte value to a Boolean value.
     *
     * @param value is the value to convert.
     */
    public static Boolean convertToBoxedBoolean(final byte value)
    {
        return AsConversions.convertToBoxedBoolean(value);
    }

    /**
     * This method converts a short value to a Boolean value.
     *
     * @param value is the value to convert.
     */
    public static Boolean convertToBoxedBoolean(final short value)
    {
        return AsConversions.convertToBoxedBoolean(value);
    }

    /**
     * This method converts an int value to a Boolean value.
     *
     * @param value is the value to convert.
     */
    public static Boolean convertToBoxedBoolean(final int value)
    {
        return AsConversions.convertToBoxedBoolean(value);
    }

    /**
     * This method converts a long value to a Boolean value.
     *
     * @param value is the value to convert.
     */
    public static Boolean convertToBoxedBoolean(final long value)
    {
        return AsConversions.convertToBoxedBoolean(value);
    }

    /**
     * This method converts a float value to a Boolean value.
     *
     * @param value is the value to convert.
     */
    public static Boolean convertToBoxedBoolean(final float value)
    {
        return AsConversions.convertToBoxedBoolean(value);
    }

    /**
     * This method converts a double value to a Boolean value.
     *
     * @param value is the value to convert.
     */
    public static Boolean convertToBoxedBoolean(final double value)
    {
        return AsConversions.convertToBoxedBoolean(value);
    }

    /**
     * This method converts a boolean value to a Character value.
     *
     * @param value is the value to convert.
     */
    public static Character convertToBoxedChar(final boolean value)
    {
        check(value, Character.MIN_VALUE, Character.MAX_VALUE);
        return AsConversions.convertToBoxedChar(value);
    }

    /**
     * This method converts a char value to a Character value.
     *
     * @param value is the value to convert.
     */
    public static Character convertToBoxedChar(final char value)
    {
        check(value, Character.MIN_VALUE, Character.MAX_VALUE);
        return AsConversions.convertToBoxedChar(value);
    }

    /**
     * This method converts a byte value to a Character value.
     *
     * @param value is the value to convert.
     */
    public static Character convertToBoxedChar(final byte value)
    {
        check(value, Character.MIN_VALUE, Character.MAX_VALUE);
        return AsConversions.convertToBoxedChar(value);
    }

    /**
     * This method converts a short value to a Character value.
     *
     * @param value is the value to convert.
     */
    public static Character convertToBoxedChar(final short value)
    {
        check(value, Character.MIN_VALUE, Character.MAX_VALUE);
        return AsConversions.convertToBoxedChar(value);
    }

    /**
     * This method converts an int value to a Character value.
     *
     * @param value is the value to convert.
     */
    public static Character convertToBoxedChar(final int value)
    {
        check(value, Character.MIN_VALUE, Character.MAX_VALUE);
        return AsConversions.convertToBoxedChar(value);
    }

    /**
     * This method converts a long value to a Character value.
     *
     * @param value is the value to convert.
     */
    public static Character convertToBoxedChar(final long value)
    {
        check(value, Character.MIN_VALUE, Character.MAX_VALUE);
        return AsConversions.convertToBoxedChar(value);
    }

    /**
     * This method converts a float value to a Character value.
     *
     * @param value is the value to convert.
     */
    public static Character convertToBoxedChar(final float value)
    {
        check(value, Character.MIN_VALUE, Character.MAX_VALUE);
        return AsConversions.convertToBoxedChar(value);
    }

    /**
     * This method converts a double value to a Character value.
     *
     * @param value is the value to convert.
     */
    public static Character convertToBoxedChar(final double value)
    {
        check(value, Character.MIN_VALUE, Character.MAX_VALUE);
        return AsConversions.convertToBoxedChar(value);
    }

    /**
     * This method converts a Boolean value to a Character value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Character convertToBoxedChar(final Boolean value)
    {
        check(value, Character.MIN_VALUE, Character.MAX_VALUE);
        return AsConversions.convertToBoxedChar(value);
    }

    /**
     * This method converts a Character value to a Character value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Character convertToBoxedChar(final Character value)
    {
        check(value, Character.MIN_VALUE, Character.MAX_VALUE);
        return AsConversions.convertToBoxedChar(value);
    }

    /**
     * This method converts a Byte value to a Character value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Character convertToBoxedChar(final Byte value)
    {
        check(value, Character.MIN_VALUE, Character.MAX_VALUE);
        return AsConversions.convertToBoxedChar(value);
    }

    /**
     * This method converts a Short value to a Character value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Character convertToBoxedChar(final Short value)
    {
        check(value, Character.MIN_VALUE, Character.MAX_VALUE);
        return AsConversions.convertToBoxedChar(value);
    }

    /**
     * This method converts an Integer value to a Character value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Character convertToBoxedChar(final Integer value)
    {
        check(value, Character.MIN_VALUE, Character.MAX_VALUE);
        return AsConversions.convertToBoxedChar(value);
    }

    /**
     * This method converts a Long value to a Character value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Character convertToBoxedChar(final Long value)
    {
        check(value, Character.MIN_VALUE, Character.MAX_VALUE);
        return AsConversions.convertToBoxedChar(value);
    }

    /**
     * This method converts a Float value to a Character value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Character convertToBoxedChar(final Float value)
    {
        check(value, Character.MIN_VALUE, Character.MAX_VALUE);
        return AsConversions.convertToBoxedChar(value);
    }

    /**
     * This method converts a Double value to a Character value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Character convertToBoxedChar(final Double value)
    {
        check(value, Character.MIN_VALUE, Character.MAX_VALUE);
        return AsConversions.convertToBoxedChar(value);
    }

    /**
     * This method converts a BigInteger value to a Character value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Character convertToBoxedChar(final BigInteger value)
    {
        check(value, Character.MIN_VALUE, Character.MAX_VALUE);
        return AsConversions.convertToBoxedChar(value);
    }

    /**
     * This method converts a BigDecimal value to a Character value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Character convertToBoxedChar(final BigDecimal value)
    {
        check(value, Character.MIN_VALUE, Character.MAX_VALUE);
        return AsConversions.convertToBoxedChar(value);
    }

    /**
     * This method converts a String value to a Character value.
     *
     * <p>
     * If the value is null, then the null is returned. <br>
     * If the value is an empty string, then null is returned.
     * Otherwise, the first character in the string is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Character convertToBoxedChar(final String value)
    {
        check(value, Character.MIN_VALUE, Character.MAX_VALUE);
        return AsConversions.convertToBoxedChar(value);
    }

    /**
     * This method converts an Object value to a Character value, if the Object is really a
     * Character.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * <p>
     * This method tries to cast the value to a Character and then unbox the result.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Character convertToBoxedChar(final Object value)
    {
        return AsConversions.convertToBoxedChar((Character) value);
    }

    /**
     * This method converts a boolean value to a Byte value.
     *
     * @param value is the value to convert.
     */
    public static Byte convertToBoxedByte(final boolean value)
    {
        check(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
        return AsConversions.convertToBoxedByte(value);
    }

    /**
     * This method converts a char value to a Byte value.
     *
     * @param value is the value to convert.
     */
    public static Byte convertToBoxedByte(final char value)
    {
        check(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
        return AsConversions.convertToBoxedByte(value);
    }

    /**
     * This method converts a byte value to a Byte value.
     *
     * @param value is the value to convert.
     */
    public static Byte convertToBoxedByte(final byte value)
    {
        check(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
        return AsConversions.convertToBoxedByte(value);
    }

    /**
     * This method converts a short value to a Byte value.
     *
     * @param value is the value to convert.
     */
    public static Byte convertToBoxedByte(final short value)
    {
        check(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
        return AsConversions.convertToBoxedByte(value);
    }

    /**
     * This method converts an int value to a Byte value.
     *
     * @param value is the value to convert.
     */
    public static Byte convertToBoxedByte(final int value)
    {
        check(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
        return AsConversions.convertToBoxedByte(value);
    }

    /**
     * This method converts a long value to a Byte value.
     *
     * @param value is the value to convert.
     */
    public static Byte convertToBoxedByte(final long value)
    {
        check(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
        return AsConversions.convertToBoxedByte(value);
    }

    /**
     * This method converts a float value to a Byte value.
     *
     * @param value is the value to convert.
     */
    public static Byte convertToBoxedByte(final float value)
    {
        check(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
        return AsConversions.convertToBoxedByte(value);
    }

    /**
     * This method converts a double value to a Byte value.
     *
     * @param value is the value to convert.
     */
    public static Byte convertToBoxedByte(final double value)
    {
        check(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
        return AsConversions.convertToBoxedByte(value);
    }

    /**
     * This method converts a Boolean value to a Byte value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Byte convertToBoxedByte(final Boolean value)
    {
        check(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
        return AsConversions.convertToBoxedByte(value);
    }

    /**
     * This method converts a Character value to a Byte value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Byte convertToBoxedByte(final Character value)
    {
        check(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
        return AsConversions.convertToBoxedByte(value);
    }

    /**
     * This method converts a Byte value to a Byte value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Byte convertToBoxedByte(final Byte value)
    {
        check(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
        return AsConversions.convertToBoxedByte(value);
    }

    /**
     * This method converts a Short value to a Byte value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Byte convertToBoxedByte(final Short value)
    {
        check(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
        return AsConversions.convertToBoxedByte(value);
    }

    /**
     * This method converts an Integer value to a Byte value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Byte convertToBoxedByte(final Integer value)
    {
        check(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
        return AsConversions.convertToBoxedByte(value);
    }

    /**
     * This method converts a Long value to a Byte value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Byte convertToBoxedByte(final Long value)
    {
        check(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
        return AsConversions.convertToBoxedByte(value);
    }

    /**
     * This method converts a Float value to a Byte value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Byte convertToBoxedByte(final Float value)
    {
        check(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
        return AsConversions.convertToBoxedByte(value);
    }

    /**
     * This method converts a Double value to a Byte value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Byte convertToBoxedByte(final Double value)
    {
        check(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
        return AsConversions.convertToBoxedByte(value);
    }

    /**
     * This method converts a BigInteger value to a Byte value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Byte convertToBoxedByte(final BigInteger value)
    {
        check(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
        return AsConversions.convertToBoxedByte(value);
    }

    /**
     * This method converts a BigDecimal value to a Byte value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Byte convertToBoxedByte(final BigDecimal value)
    {
        check(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
        return AsConversions.convertToBoxedByte(value);
    }

    /**
     * This method converts a String value to a Byte value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Byte convertToBoxedByte(final String value)
    {
        check(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
        return AsConversions.convertToBoxedByte(value);
    }

    /**
     * This method converts an Object value to a Byte value, if the Object is really a Byte.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * <p>
     * This method tries to cast the value to a Byte and then unbox the result.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Byte convertToBoxedByte(final Object value)
    {
        return AsConversions.convertToBoxedByte((Byte) value);
    }

    /**
     * This method converts a boolean value to a Short value.
     *
     * @param value is the value to convert.
     */
    public static Short convertToBoxedShort(final boolean value)
    {
        check(value, Short.MIN_VALUE, Short.MAX_VALUE);
        return AsConversions.convertToBoxedShort(value);
    }

    /**
     * This method converts a char value to a Short value.
     *
     * @param value is the value to convert.
     */
    public static Short convertToBoxedShort(final char value)
    {
        check(value, Short.MIN_VALUE, Short.MAX_VALUE);
        return AsConversions.convertToBoxedShort(value);
    }

    /**
     * This method converts a byte value to a Short value.
     *
     * @param value is the value to convert.
     */
    public static Short convertToBoxedShort(final byte value)
    {
        check(value, Short.MIN_VALUE, Short.MAX_VALUE);
        return AsConversions.convertToBoxedShort(value);
    }

    /**
     * This method converts a short value to a Short value.
     *
     * @param value is the value to convert.
     */
    public static Short convertToBoxedShort(final short value)
    {
        check(value, Short.MIN_VALUE, Short.MAX_VALUE);
        return AsConversions.convertToBoxedShort(value);
    }

    /**
     * This method converts an int value to a Short value.
     *
     * @param value is the value to convert.
     */
    public static Short convertToBoxedShort(final int value)
    {
        check(value, Short.MIN_VALUE, Short.MAX_VALUE);
        return AsConversions.convertToBoxedShort(value);
    }

    /**
     * This method converts a long value to a Short value.
     *
     * @param value is the value to convert.
     */
    public static Short convertToBoxedShort(final long value)
    {
        check(value, Short.MIN_VALUE, Short.MAX_VALUE);
        return AsConversions.convertToBoxedShort(value);
    }

    /**
     * This method converts a float value to a Short value.
     *
     * @param value is the value to convert.
     */
    public static Short convertToBoxedShort(final float value)
    {
        check(value, Short.MIN_VALUE, Short.MAX_VALUE);
        return AsConversions.convertToBoxedShort(value);
    }

    /**
     * This method converts a double value to a Short value.
     *
     * @param value is the value to convert.
     */
    public static Short convertToBoxedShort(final double value)
    {
        check(value, Short.MIN_VALUE, Short.MAX_VALUE);
        return AsConversions.convertToBoxedShort(value);
    }

    /**
     * This method converts a Boolean value to a Short value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Short convertToBoxedShort(final Boolean value)
    {
        check(value, Short.MIN_VALUE, Short.MAX_VALUE);
        return AsConversions.convertToBoxedShort(value);
    }

    /**
     * This method converts a Character value to a Short value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Short convertToBoxedShort(final Character value)
    {
        check(value, Short.MIN_VALUE, Short.MAX_VALUE);
        return AsConversions.convertToBoxedShort(value);
    }

    /**
     * This method converts a Byte value to a Short value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Short convertToBoxedShort(final Byte value)
    {
        check(value, Short.MIN_VALUE, Short.MAX_VALUE);
        return AsConversions.convertToBoxedShort(value);
    }

    /**
     * This method converts a Short value to a Short value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Short convertToBoxedShort(final Short value)
    {
        check(value, Short.MIN_VALUE, Short.MAX_VALUE);
        return AsConversions.convertToBoxedShort(value);
    }

    /**
     * This method converts an Integer value to a Short value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Short convertToBoxedShort(final Integer value)
    {
        check(value, Short.MIN_VALUE, Short.MAX_VALUE);
        return AsConversions.convertToBoxedShort(value);
    }

    /**
     * This method converts a Long value to a Short value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Short convertToBoxedShort(final Long value)
    {
        check(value, Short.MIN_VALUE, Short.MAX_VALUE);
        return AsConversions.convertToBoxedShort(value);
    }

    /**
     * This method converts a Float value to a Short value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Short convertToBoxedShort(final Float value)
    {
        check(value, Short.MIN_VALUE, Short.MAX_VALUE);
        return AsConversions.convertToBoxedShort(value);
    }

    /**
     * This method converts a Double value to a Short value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Short convertToBoxedShort(final Double value)
    {
        check(value, Short.MIN_VALUE, Short.MAX_VALUE);
        return AsConversions.convertToBoxedShort(value);
    }

    /**
     * This method converts a BigInteger value to a Short value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Short convertToBoxedShort(final BigInteger value)
    {
        check(value, Short.MIN_VALUE, Short.MAX_VALUE);
        return AsConversions.convertToBoxedShort(value);
    }

    /**
     * This method converts a BigDecimal value to a Short value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Short convertToBoxedShort(final BigDecimal value)
    {
        check(value, Short.MIN_VALUE, Short.MAX_VALUE);
        return AsConversions.convertToBoxedShort(value);
    }

    /**
     * This method converts a String value to a Short value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Short convertToBoxedShort(final String value)
    {
        check(value, Short.MIN_VALUE, Short.MAX_VALUE);
        return AsConversions.convertToBoxedShort(value);
    }

    /**
     * This method converts an Object value to a Short value, if the Object is really a Short.
     *
     * <p>
     * If the value is null, then zero is returned.
     *
     * <p>
     * This method tries to cast the value to a Short and then unbox the result.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Short convertToBoxedShort(final Object value)
    {
        return AsConversions.convertToBoxedShort((Short) value);
    }

    /**
     * This method converts a boolean value to a Integer value.
     *
     * @param value is the value to convert.
     */
    public static Integer convertToBoxedInt(final boolean value)
    {
        check(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return AsConversions.convertToBoxedInt(value);
    }

    /**
     * This method converts a char value to a Integer value.
     *
     * @param value is the value to convert.
     */
    public static Integer convertToBoxedInt(final char value)
    {
        check(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return AsConversions.convertToBoxedInt(value);
    }

    /**
     * This method converts a byte value to a Integer value.
     *
     * @param value is the value to convert.
     */
    public static Integer convertToBoxedInt(final byte value)
    {
        check(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return AsConversions.convertToBoxedInt(value);
    }

    /**
     * This method converts a short value to a Integer value.
     *
     * @param value is the value to convert.
     */
    public static Integer convertToBoxedInt(final short value)
    {
        check(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return AsConversions.convertToBoxedInt(value);
    }

    /**
     * This method converts an int value to a Integer value.
     *
     * @param value is the value to convert.
     */
    public static Integer convertToBoxedInt(final int value)
    {
        check(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return AsConversions.convertToBoxedInt(value);
    }

    /**
     * This method converts a long value to a Integer value.
     *
     * @param value is the value to convert.
     */
    public static Integer convertToBoxedInt(final long value)
    {
        check(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return AsConversions.convertToBoxedInt(value);
    }

    /**
     * This method converts a float value to a Integer value.
     *
     * @param value is the value to convert.
     */
    public static Integer convertToBoxedInt(final float value)
    {
        check(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return AsConversions.convertToBoxedInt(value);
    }

    /**
     * This method converts a double value to a Integer value.
     *
     * @param value is the value to convert.
     */
    public static Integer convertToBoxedInt(final double value)
    {
        check(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return AsConversions.convertToBoxedInt(value);
    }

    /**
     * This method converts a Boolean value to a Integer value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Integer convertToBoxedInt(final Boolean value)
    {
        check(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return AsConversions.convertToBoxedInt(value);
    }

    /**
     * This method converts a Character value to a Integer value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Integer convertToBoxedInt(final Character value)
    {
        check(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return AsConversions.convertToBoxedInt(value);
    }

    /**
     * This method converts a Byte value to a Integer value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Integer convertToBoxedInt(final Byte value)
    {
        check(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return AsConversions.convertToBoxedInt(value);
    }

    /**
     * This method converts a Short value to a Integer value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Integer convertToBoxedInt(final Short value)
    {
        check(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return AsConversions.convertToBoxedInt(value);
    }

    /**
     * This method converts an Integer value to a Integer value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Integer convertToBoxedInt(final Integer value)
    {
        check(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return AsConversions.convertToBoxedInt(value);
    }

    /**
     * This method converts a Long value to a Integer value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Integer convertToBoxedInt(final Long value)
    {
        check(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return AsConversions.convertToBoxedInt(value);
    }

    /**
     * This method converts a Float value to a Integer value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Integer convertToBoxedInt(final Float value)
    {
        check(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return AsConversions.convertToBoxedInt(value);
    }

    /**
     * This method converts a Double value to a Integer value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Integer convertToBoxedInt(final Double value)
    {
        check(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return AsConversions.convertToBoxedInt(value);
    }

    /**
     * This method converts a BigInteger value to a Integer value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Integer convertToBoxedInt(final BigInteger value)
    {
        check(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return AsConversions.convertToBoxedInt(value);
    }

    /**
     * This method converts a BigDecimal value to a Integer value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Integer convertToBoxedInt(final BigDecimal value)
    {
        check(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return AsConversions.convertToBoxedInt(value);
    }

    /**
     * This method converts a String value to a Integer value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Integer convertToBoxedInt(final String value)
    {
        check(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return AsConversions.convertToBoxedInt(value);
    }

    /**
     * This method converts an Object value to a Integer value, if the Object is really a Integer.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * <p>
     * This method tries to cast the value to a Integer and then unbox the result.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Integer convertToBoxedInt(final Object value)
    {
        return AsConversions.convertToBoxedInt((Integer) value);
    }

    /**
     * This method converts a boolean value to a Long value.
     *
     * @param value is the value to convert.
     */
    public static Long convertToBoxedLong(final boolean value)
    {
        check(value, Long.MIN_VALUE, Long.MAX_VALUE);
        return AsConversions.convertToBoxedLong(value);
    }

    /**
     * This method converts a char value to a Long value.
     *
     * @param value is the value to convert.
     */
    public static Long convertToBoxedLong(final char value)
    {
        check(value, Long.MIN_VALUE, Long.MAX_VALUE);
        return AsConversions.convertToBoxedLong(value);
    }

    /**
     * This method converts a byte value to a Long value.
     *
     * @param value is the value to convert.
     */
    public static Long convertToBoxedLong(final byte value)
    {
        check(value, Long.MIN_VALUE, Long.MAX_VALUE);
        return AsConversions.convertToBoxedLong(value);
    }

    /**
     * This method converts a short value to a Long value.
     *
     * @param value is the value to convert.
     */
    public static Long convertToBoxedLong(final short value)
    {
        check(value, Long.MIN_VALUE, Long.MAX_VALUE);
        return AsConversions.convertToBoxedLong(value);
    }

    /**
     * This method converts an int value to a Long value.
     *
     * @param value is the value to convert.
     */
    public static Long convertToBoxedLong(final int value)
    {
        check(value, Long.MIN_VALUE, Long.MAX_VALUE);
        return AsConversions.convertToBoxedLong(value);
    }

    /**
     * This method converts a long value to a Long value.
     *
     * @param value is the value to convert.
     */
    public static Long convertToBoxedLong(final long value)
    {
        check(value, Long.MIN_VALUE, Long.MAX_VALUE);
        return AsConversions.convertToBoxedLong(value);
    }

    /**
     * This method converts a float value to a Long value.
     *
     * @param value is the value to convert.
     */
    public static Long convertToBoxedLong(final float value)
    {
        check(value, Long.MIN_VALUE, Long.MAX_VALUE);
        return AsConversions.convertToBoxedLong(value);
    }

    /**
     * This method converts a double value to a Long value.
     *
     * @param value is the value to convert.
     */
    public static Long convertToBoxedLong(final double value)
    {
        check(value, Long.MIN_VALUE, Long.MAX_VALUE);
        return AsConversions.convertToBoxedLong(value);
    }

    /**
     * This method converts a Boolean value to a Long value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Long convertToBoxedLong(final Boolean value)
    {
        check(value, Long.MIN_VALUE, Long.MAX_VALUE);
        return AsConversions.convertToBoxedLong(value);
    }

    /**
     * This method converts a Character value to a Long value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Long convertToBoxedLong(final Character value)
    {
        check(value, Long.MIN_VALUE, Long.MAX_VALUE);
        return AsConversions.convertToBoxedLong(value);
    }

    /**
     * This method converts a Byte value to a Long value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Long convertToBoxedLong(final Byte value)
    {
        check(value, Long.MIN_VALUE, Long.MAX_VALUE);
        return AsConversions.convertToBoxedLong(value);
    }

    /**
     * This method converts a Short value to a Long value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Long convertToBoxedLong(final Short value)
    {
        check(value, Long.MIN_VALUE, Long.MAX_VALUE);
        return AsConversions.convertToBoxedLong(value);
    }

    /**
     * This method converts an Integer value to a Long value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Long convertToBoxedLong(final Integer value)
    {
        check(value, Long.MIN_VALUE, Long.MAX_VALUE);
        return AsConversions.convertToBoxedLong(value);
    }

    /**
     * This method converts a Long value to a Long value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Long convertToBoxedLong(final Long value)
    {
        check(value, Long.MIN_VALUE, Long.MAX_VALUE);
        return AsConversions.convertToBoxedLong(value);
    }

    /**
     * This method converts a Float value to a Long value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Long convertToBoxedLong(final Float value)
    {
        check(value, Long.MIN_VALUE, Long.MAX_VALUE);
        return AsConversions.convertToBoxedLong(value);
    }

    /**
     * This method converts a Double value to a Long value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Long convertToBoxedLong(final Double value)
    {
        check(value, Long.MIN_VALUE, Long.MAX_VALUE);
        return AsConversions.convertToBoxedLong(value);
    }

    /**
     * This method converts a BigInteger value to a Long value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Long convertToBoxedLong(final BigInteger value)
    {
        check(value, Long.MIN_VALUE, Long.MAX_VALUE);
        return AsConversions.convertToBoxedLong(value);
    }

    /**
     * This method converts a BigDecimal value to a Long value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Long convertToBoxedLong(final BigDecimal value)
    {
        check(value, Long.MIN_VALUE, Long.MAX_VALUE);
        return AsConversions.convertToBoxedLong(value);
    }

    /**
     * This method converts a String value to a Long value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Long convertToBoxedLong(final String value)
    {
        check(value, Long.MIN_VALUE, Long.MAX_VALUE);
        return AsConversions.convertToBoxedLong(value);
    }

    /**
     * This method converts an Object value to a Long value, if the Object is really a Long.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * <p>
     * This method tries to cast the value to a Long and then unbox the result.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Long convertToBoxedLong(final Object value)
    {
        return AsConversions.convertToBoxedLong((Long) value);
    }

    /**
     * This method converts a boolean value to a Float value.
     *
     * @param value is the value to convert.
     */
    public static Float convertToBoxedFloat(final boolean value)
    {
        check(value, Float.MIN_VALUE, Float.MAX_VALUE);
        return AsConversions.convertToBoxedFloat(value);
    }

    /**
     * This method converts a char value to a Float value.
     *
     * @param value is the value to convert.
     */
    public static Float convertToBoxedFloat(final char value)
    {
        check(value, Float.MIN_VALUE, Float.MAX_VALUE);
        return AsConversions.convertToBoxedFloat(value);
    }

    /**
     * This method converts a byte value to a Float value.
     *
     * @param value is the value to convert.
     */
    public static Float convertToBoxedFloat(final byte value)
    {
        check(value, Float.MIN_VALUE, Float.MAX_VALUE);
        return AsConversions.convertToBoxedFloat(value);
    }

    /**
     * This method converts a short value to a Float value.
     *
     * @param value is the value to convert.
     */
    public static Float convertToBoxedFloat(final short value)
    {
        check(value, Float.MIN_VALUE, Float.MAX_VALUE);
        return AsConversions.convertToBoxedFloat(value);
    }

    /**
     * This method converts an int value to a Float value.
     *
     * @param value is the value to convert.
     */
    public static Float convertToBoxedFloat(final int value)
    {
        check(value, Float.MIN_VALUE, Float.MAX_VALUE);
        return AsConversions.convertToBoxedFloat(value);
    }

    /**
     * This method converts a long value to a Float value.
     *
     * @param value is the value to convert.
     */
    public static Float convertToBoxedFloat(final long value)
    {
        check(value, Float.MIN_VALUE, Float.MAX_VALUE);
        return AsConversions.convertToBoxedFloat(value);
    }

    /**
     * This method converts a float value to a Float value.
     *
     * @param value is the value to convert.
     */
    public static Float convertToBoxedFloat(final float value)
    {
        check(value, Float.MIN_VALUE, Float.MAX_VALUE);
        return AsConversions.convertToBoxedFloat(value);
    }

    /**
     * This method converts a double value to a Float value.
     *
     * @param value is the value to convert.
     */
    public static Float convertToBoxedFloat(final double value)
    {
        check(value, Float.MIN_VALUE, Float.MAX_VALUE);
        return AsConversions.convertToBoxedFloat(value);
    }

    /**
     * This method converts a Boolean value to a Float value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Float convertToBoxedFloat(final Boolean value)
    {
        check(value, Float.MIN_VALUE, Float.MAX_VALUE);
        return AsConversions.convertToBoxedFloat(value);
    }

    /**
     * This method converts a Character value to a Float value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Float convertToBoxedFloat(final Character value)
    {
        check(value, Float.MIN_VALUE, Float.MAX_VALUE);
        return AsConversions.convertToBoxedFloat(value);
    }

    /**
     * This method converts a Byte value to a Float value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Float convertToBoxedFloat(final Byte value)
    {
        check(value, Float.MIN_VALUE, Float.MAX_VALUE);
        return AsConversions.convertToBoxedFloat(value);
    }

    /**
     * This method converts a Short value to a Float value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Float convertToBoxedFloat(final Short value)
    {
        check(value, Float.MIN_VALUE, Float.MAX_VALUE);
        return AsConversions.convertToBoxedFloat(value);
    }

    /**
     * This method converts an Integer value to a Float value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Float convertToBoxedFloat(final Integer value)
    {
        check(value, Float.MIN_VALUE, Float.MAX_VALUE);
        return AsConversions.convertToBoxedFloat(value);
    }

    /**
     * This method converts a Long value to a Float value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Float convertToBoxedFloat(final Long value)
    {
        check(value, Float.MIN_VALUE, Float.MAX_VALUE);
        return AsConversions.convertToBoxedFloat(value);
    }

    /**
     * This method converts a Float value to a Float value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Float convertToBoxedFloat(final Float value)
    {
        check(value, Float.MIN_VALUE, Float.MAX_VALUE);
        return AsConversions.convertToBoxedFloat(value);
    }

    /**
     * This method converts a Double value to a Float value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Float convertToBoxedFloat(final Double value)
    {
        check(value, Float.MIN_VALUE, Float.MAX_VALUE);
        return AsConversions.convertToBoxedFloat(value);
    }

    /**
     * This method converts a BigInteger value to a Float value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Float convertToBoxedFloat(final BigInteger value)
    {
        check(value, Float.MIN_VALUE, Float.MAX_VALUE);
        return AsConversions.convertToBoxedFloat(value);
    }

    /**
     * This method converts a BigDecimal value to a Float value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Float convertToBoxedFloat(final BigDecimal value)
    {
        check(value, Float.MIN_VALUE, Float.MAX_VALUE);
        return AsConversions.convertToBoxedFloat(value);
    }

    /**
     * This method converts a String value to a Float value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Float convertToBoxedFloat(final String value)
    {
        check(value, Float.MIN_VALUE, Float.MAX_VALUE);
        return AsConversions.convertToBoxedFloat(value);
    }

    /**
     * This method converts an Object value to a Float value, if the Object is really a Float.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * <p>
     * This method tries to cast the value to a Float and then unbox the result.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Float convertToBoxedFloat(final Object value)
    {
        return AsConversions.convertToBoxedFloat((Float) value);
    }

    /**
     * This method converts a boolean value to a Double value.
     *
     * @param value is the value to convert.
     */
    public static Double convertToBoxedDouble(final boolean value)
    {
        check(value, Double.MIN_VALUE, Double.MAX_VALUE);
        return AsConversions.convertToBoxedDouble(value);
    }

    /**
     * This method converts a char value to a Double value.
     *
     * @param value is the value to convert.
     */
    public static Double convertToBoxedDouble(final char value)
    {
        check(value, Double.MIN_VALUE, Double.MAX_VALUE);
        return AsConversions.convertToBoxedDouble(value);
    }

    /**
     * This method converts a byte value to a Double value.
     *
     * @param value is the value to convert.
     */
    public static Double convertToBoxedDouble(final byte value)
    {
        check(value, Double.MIN_VALUE, Double.MAX_VALUE);
        return AsConversions.convertToBoxedDouble(value);
    }

    /**
     * This method converts a short value to a Double value.
     *
     * @param value is the value to convert.
     */
    public static Double convertToBoxedDouble(final short value)
    {
        check(value, Double.MIN_VALUE, Double.MAX_VALUE);
        return AsConversions.convertToBoxedDouble(value);
    }

    /**
     * This method converts an int value to a Double value.
     *
     * @param value is the value to convert.
     */
    public static Double convertToBoxedDouble(final int value)
    {
        check(value, Double.MIN_VALUE, Double.MAX_VALUE);
        return AsConversions.convertToBoxedDouble(value);
    }

    /**
     * This method converts a long value to a Double value.
     *
     * @param value is the value to convert.
     */
    public static Double convertToBoxedDouble(final long value)
    {
        check(value, Double.MIN_VALUE, Double.MAX_VALUE);
        return AsConversions.convertToBoxedDouble(value);
    }

    /**
     * This method converts a float value to a Double value.
     *
     * @param value is the value to convert.
     */
    public static Double convertToBoxedDouble(final float value)
    {
        check(value, Double.MIN_VALUE, Double.MAX_VALUE);
        return AsConversions.convertToBoxedDouble(value);
    }

    /**
     * This method converts a double value to a Double value.
     *
     * @param value is the value to convert.
     */
    public static Double convertToBoxedDouble(final double value)
    {
        check(value, Double.MIN_VALUE, Double.MAX_VALUE);
        return AsConversions.convertToBoxedDouble(value);
    }

    /**
     * This method converts a Boolean value to a Double value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Double convertToBoxedDouble(final Boolean value)
    {
        check(value, Double.MIN_VALUE, Double.MAX_VALUE);
        return AsConversions.convertToBoxedDouble(value);
    }

    /**
     * This method converts a Character value to a Double value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Double convertToBoxedDouble(final Character value)
    {
        check(value, Double.MIN_VALUE, Double.MAX_VALUE);
        return AsConversions.convertToBoxedDouble(value);
    }

    /**
     * This method converts a Byte value to a Double value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Double convertToBoxedDouble(final Byte value)
    {
        check(value, Double.MIN_VALUE, Double.MAX_VALUE);
        return AsConversions.convertToBoxedDouble(value);
    }

    /**
     * This method converts a Short value to a Double value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Double convertToBoxedDouble(final Short value)
    {
        check(value, Double.MIN_VALUE, Double.MAX_VALUE);
        return AsConversions.convertToBoxedDouble(value);
    }

    /**
     * This method converts an Integer value to a Double value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Double convertToBoxedDouble(final Integer value)
    {
        check(value, Double.MIN_VALUE, Double.MAX_VALUE);
        return AsConversions.convertToBoxedDouble(value);
    }

    /**
     * This method converts a Long value to a Double value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Double convertToBoxedDouble(final Long value)
    {
        check(value, Double.MIN_VALUE, Double.MAX_VALUE);
        return AsConversions.convertToBoxedDouble(value);
    }

    /**
     * This method converts a Float value to a Double value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Double convertToBoxedDouble(final Float value)
    {
        check(value, Double.MIN_VALUE, Double.MAX_VALUE);
        return AsConversions.convertToBoxedDouble(value);
    }

    /**
     * This method converts a Double value to a Double value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Double convertToBoxedDouble(final Double value)
    {
        check(value, Double.MIN_VALUE, Double.MAX_VALUE);
        return AsConversions.convertToBoxedDouble(value);
    }

    /**
     * This method converts a BigInteger value to a Double value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Double convertToBoxedDouble(final BigInteger value)
    {
        check(value, Double.MIN_VALUE, Double.MAX_VALUE);
        return AsConversions.convertToBoxedDouble(value);
    }

    /**
     * This method converts a BigDecimal value to a Double value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Double convertToBoxedDouble(final BigDecimal value)
    {
        check(value, Double.MIN_VALUE, Double.MAX_VALUE);
        return AsConversions.convertToBoxedDouble(value);
    }

    /**
     * This method converts a String value to a Double value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Double convertToBoxedDouble(final String value)
    {
        check(value, Double.MIN_VALUE, Double.MAX_VALUE);
        return AsConversions.convertToBoxedDouble(value);
    }

    /**
     * This method converts an Object value to a Double value, if the Object is really a Double.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * <p>
     * This method tries to cast the value to a Double and then unbox the result.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Double convertToBoxedDouble(final Object value)
    {
        return AsConversions.convertToBoxedDouble((Double) value);
    }

    /**
     * This method converts a boolean value to a BigInteger value.
     *
     * @param value is the value to convert.
     */
    public static BigInteger convertToBigInteger(final boolean value)
    {
        return AsConversions.convertToBigInteger(value);
    }

    /**
     * This method converts a char value to a BigInteger value.
     *
     * @param value is the value to convert.
     */
    public static BigInteger convertToBigInteger(final char value)
    {
        return AsConversions.convertToBigInteger(value);
    }

    /**
     * This method converts a byte value to a BigInteger value.
     *
     * @param value is the value to convert.
     */
    public static BigInteger convertToBigInteger(final byte value)
    {
        return AsConversions.convertToBigInteger(value);
    }

    /**
     * This method converts a short value to a BigInteger value.
     *
     * @param value is the value to convert.
     */
    public static BigInteger convertToBigInteger(final short value)
    {
        return AsConversions.convertToBigInteger(value);
    }

    /**
     * This method converts an int value to a BigInteger value.
     *
     * @param value is the value to convert.
     */
    public static BigInteger convertToBigInteger(final int value)
    {
        return AsConversions.convertToBigInteger(value);
    }

    /**
     * This method converts a long value to a BigInteger value.
     *
     * @param value is the value to convert.
     */
    public static BigInteger convertToBigInteger(final long value)
    {
        return AsConversions.convertToBigInteger(value);
    }

    /**
     * This method converts a float value to a BigInteger value.
     *
     * @param value is the value to convert.
     */
    public static BigInteger convertToBigInteger(final float value)
    {
        return AsConversions.convertToBigInteger(value);
    }

    /**
     * This method converts a double value to a BigInteger value.
     *
     * @param value is the value to convert.
     */
    public static BigInteger convertToBigInteger(final double value)
    {
        return AsConversions.convertToBigInteger(value);
    }

    /**
     * This method converts a Boolean value to a BigInteger value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static BigInteger convertToBigInteger(final Boolean value)
    {
        check(value);
        return AsConversions.convertToBigInteger(value);
    }

    /**
     * This method converts a Character value to a BigInteger value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static BigInteger convertToBigInteger(final Character value)
    {
        check(value);
        return AsConversions.convertToBigInteger(value);
    }

    /**
     * This method converts a Byte value to a BigInteger value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static BigInteger convertToBigInteger(final Byte value)
    {
        check(value);
        return AsConversions.convertToBigInteger(value);
    }

    /**
     * This method converts a Short value to a BigInteger value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static BigInteger convertToBigInteger(final Short value)
    {
        check(value);
        return AsConversions.convertToBigInteger(value);
    }

    /**
     * This method converts an Integer value to a BigInteger value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static BigInteger convertToBigInteger(final Integer value)
    {
        check(value);
        return AsConversions.convertToBigInteger(value);
    }

    /**
     * This method converts a Long value to a BigInteger value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static BigInteger convertToBigInteger(final Long value)
    {
        check(value);
        return AsConversions.convertToBigInteger(value);
    }

    /**
     * This method converts a Float value to a BigInteger value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static BigInteger convertToBigInteger(final Float value)
    {
        check(value);
        return AsConversions.convertToBigInteger(value);
    }

    /**
     * This method converts a Double value to a BigInteger value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static BigInteger convertToBigInteger(final Double value)
    {
        check(value);
        return AsConversions.convertToBigInteger(value);
    }

    /**
     * This method converts a BigInteger value to a BigInteger value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static BigInteger convertToBigInteger(final BigInteger value)
    {
        check(value);
        return AsConversions.convertToBigInteger(value);
    }

    /**
     * This method converts a BigDecimal value to a BigInteger value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static BigInteger convertToBigInteger(final BigDecimal value)
    {
        check(value);
        return AsConversions.convertToBigInteger(value);
    }

    /**
     * This method converts a String value to a BigInteger value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static BigInteger convertToBigInteger(final String value)
    {
        check(value);
        return AsConversions.convertToBigInteger(value);
    }

    /**
     * This method converts an Object value to a BigInteger value, if the Object is really a
     * BigInteger.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * <p>
     * This method tries to cast the value to a BigInteger and then unbox the result.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static BigInteger convertToBigInteger(final Object value)
    {
        return AsConversions.convertToBigInteger((BigInteger) value);
    }

    /**
     * This method converts a boolean value to a BigDecimal value.
     *
     * @param value is the value to convert.
     */
    public static BigDecimal convertToBigDecimal(final boolean value)
    {
        return AsConversions.convertToBigDecimal(value);
    }

    /**
     * This method converts a char value to a BigDecimal value.
     *
     * @param value is the value to convert.
     */
    public static BigDecimal convertToBigDecimal(final char value)
    {
        return AsConversions.convertToBigDecimal(value);
    }

    /**
     * This method converts a byte value to a BigDecimal value.
     *
     * @param value is the value to convert.
     */
    public static BigDecimal convertToBigDecimal(final byte value)
    {

        return AsConversions.convertToBigDecimal(value);
    }

    /**
     * This method converts a short value to a BigDecimal value.
     *
     * @param value is the value to convert.
     */
    public static BigDecimal convertToBigDecimal(final short value)
    {

        return AsConversions.convertToBigDecimal(value);
    }

    /**
     * This method converts an int value to a BigDecimal value.
     *
     * @param value is the value to convert.
     */
    public static BigDecimal convertToBigDecimal(final int value)
    {

        return AsConversions.convertToBigDecimal(value);
    }

    /**
     * This method converts a long value to a BigDecimal value.
     *
     * @param value is the value to convert.
     */
    public static BigDecimal convertToBigDecimal(final long value)
    {

        return AsConversions.convertToBigDecimal(value);
    }

    /**
     * This method converts a float value to a BigDecimal value.
     *
     * @param value is the value to convert.
     */
    public static BigDecimal convertToBigDecimal(final float value)
    {

        return AsConversions.convertToBigDecimal(value);
    }

    /**
     * This method converts a double value to a BigDecimal value.
     *
     * @param value is the value to convert.
     */
    public static BigDecimal convertToBigDecimal(final double value)
    {

        return AsConversions.convertToBigDecimal(value);
    }

    /**
     * This method converts a Boolean value to a BigDecimal value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static BigDecimal convertToBigDecimal(final Boolean value)
    {
        check(value);
        return AsConversions.convertToBigDecimal(value);
    }

    /**
     * This method converts a Character value to a BigDecimal value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static BigDecimal convertToBigDecimal(final Character value)
    {
        check(value);
        return AsConversions.convertToBigDecimal(value);
    }

    /**
     * This method converts a Byte value to a BigDecimal value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static BigDecimal convertToBigDecimal(final Byte value)
    {
        check(value);
        return AsConversions.convertToBigDecimal(value);
    }

    /**
     * This method converts a Short value to a BigDecimal value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static BigDecimal convertToBigDecimal(final Short value)
    {
        check(value);
        return AsConversions.convertToBigDecimal(value);
    }

    /**
     * This method converts an Integer value to a BigDecimal value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static BigDecimal convertToBigDecimal(final Integer value)
    {
        check(value);
        return AsConversions.convertToBigDecimal(value);
    }

    /**
     * This method converts a Long value to a BigDecimal value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static BigDecimal convertToBigDecimal(final Long value)
    {
        check(value);
        return AsConversions.convertToBigDecimal(value);
    }

    /**
     * This method converts a Float value to a BigDecimal value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static BigDecimal convertToBigDecimal(final Float value)
    {
        check(value);
        return AsConversions.convertToBigDecimal(value);
    }

    /**
     * This method converts a Double value to a BigDecimal value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static BigDecimal convertToBigDecimal(final Double value)
    {
        check(value);
        return AsConversions.convertToBigDecimal(value);
    }

    /**
     * This method converts a BigInteger value to a BigDecimal value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static BigDecimal convertToBigDecimal(final BigInteger value)
    {
        check(value);
        return AsConversions.convertToBigDecimal(value);
    }

    /**
     * This method converts a BigDecimal value to a BigDecimal value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static BigDecimal convertToBigDecimal(final BigDecimal value)
    {
        check(value);
        return AsConversions.convertToBigDecimal(value);
    }

    /**
     * This method converts a String value to a BigDecimal value.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static BigDecimal convertToBigDecimal(final String value)
    {
        check(value);
        return AsConversions.convertToBigDecimal(value);
    }

    /**
     * This method converts an Object value to a BigDecimal value, if the Object is really a
     * BigDecimal.
     *
     * <p>
     * If the value is null, then zero is returned.
     * </p>
     *
     * <p>
     * This method tries to cast the value to a BigDecimal and then unbox the result.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static BigDecimal convertToBigDecimal(final Object value)
    {
        return AsConversions.convertToBigDecimal((BigDecimal) value);
    }

    /**
     * This method converts a boolean value to a String value.
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static String convertToString(final boolean value)
    {
        check(value);
        return AsConversions.convertToString(value);
    }

    /**
     * This method converts a char value to a String value.
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static String convertToString(final char value)
    {
        check(value);
        return AsConversions.convertToString(value);
    }

    /**
     * This method converts a byte value to a String value.
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static String convertToString(final byte value)
    {
        check(value);
        return AsConversions.convertToString(value);
    }

    /**
     * This method converts a short value to a String value.
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static String convertToString(final short value)
    {
        check(value);
        return AsConversions.convertToString(value);
    }

    /**
     * This method converts a int value to a String value.
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static String convertToString(final int value)
    {
        check(value);
        return AsConversions.convertToString(value);
    }

    /**
     * This method converts a long value to a String value.
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static String convertToString(final long value)
    {
        check(value);
        return AsConversions.convertToString(value);
    }

    /**
     * This method converts a float value to a String value.
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static String convertToString(final float value)
    {
        check(value);
        return AsConversions.convertToString(value);
    }

    /**
     * This method converts a double value to a String value.
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static String convertToString(final double value)
    {
        check(value);
        return AsConversions.convertToString(value);
    }

    /**
     * This method converts a Boolean value to a String value.
     *
     * <p>
     * If the value is null, then "null" is returned.
     * </p>
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static String convertToString(final Boolean value)
    {
        check(value);
        return AsConversions.convertToString(value);
    }

    /**
     * This method converts a Character value to a String value.
     *
     * <p>
     * If the value is null, then "null" is returned.
     * </p>
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static String convertToString(final Character value)
    {
        check(value);
        return AsConversions.convertToString(value);
    }

    /**
     * This method converts a Byte value to a String value.
     *
     * <p>
     * If the value is null, then "null" is returned.
     * </p>
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static String convertToString(final Byte value)
    {
        check(value);
        return AsConversions.convertToString(value);
    }

    /**
     * This method converts a Short value to a String value.
     *
     * <p>
     * If the value is null, then "null" is returned.
     * </p>
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static String convertToString(final Short value)
    {
        check(value);
        return AsConversions.convertToString(value);
    }

    /**
     * This method converts an Integer value to a String value.
     *
     * <p>
     * If the value is null, then "null" is returned.
     * </p>
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static String convertToString(final Integer value)
    {
        check(value);
        return AsConversions.convertToString(value);
    }

    /**
     * This method converts a Long value to a String value.
     *
     * <p>
     * If the value is null, then "null" is returned.
     * </p>
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static String convertToString(final Long value)
    {
        check(value);
        return AsConversions.convertToString(value);
    }

    /**
     * This method converts a Float value to a String value.
     *
     * <p>
     * If the value is null, then "null" is returned.
     * </p>
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static String convertToString(final Float value)
    {
        check(value);
        return AsConversions.convertToString(value);
    }

    /**
     * This method converts a Double value to a String value.
     *
     * <p>
     * If the value is null, then "null" is returned.
     * </p>
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static String convertToString(final Double value)
    {
        check(value);
        return AsConversions.convertToString(value);
    }

    /**
     * This method converts a BigInteger value to a String value.
     *
     * <p>
     * If the value is null, then "null" is returned.
     * </p>
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static String convertToString(final BigInteger value)
    {
        check(value);
        return AsConversions.convertToString(value);
    }

    /**
     * This method converts a BigDecimal value to a String value.
     *
     * <p>
     * If the value is null, then "null" is returned.
     * </p>
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static String convertToString(final BigDecimal value)
    {
        check(value);
        return AsConversions.convertToString(value);
    }

    /**
     * This method converts a String value to a String value.
     *
     * <p>
     * If the value is null, then "null" is returned.
     * </p>
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static String convertToString(final String value)
    {
        return value;
    }

    /**
     * This method converts an Object value to a String value.
     *
     * <p>
     * If the value is null, then "null" is returned.
     * </p>
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static String convertToString(final Object value)
    {
        return AsConversions.convertToString(value);
    }

    /**
     * This method converts a boolean value to an Object value.
     *
     * <p>
     * This method simply performs a boxing conversion.
     * </p>
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static Object convertToObject(final boolean value)
    {
        return AsConversions.convertToObject(value);
    }

    /**
     * This method converts a char value to an Object value.
     *
     * <p>
     * This method simply performs a boxing conversion.
     * </p>
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static Object convertToObject(final char value)
    {
        return AsConversions.convertToObject(value);
    }

    /**
     * This method converts a byte value to an Object value.
     *
     * <p>
     * This method simply performs a boxing conversion.
     * </p>
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static Object convertToObject(final byte value)
    {
        return AsConversions.convertToObject(value);
    }

    /**
     * This method converts a short value to an Object value.
     *
     * <p>
     * This method simply performs a boxing conversion.
     * </p>
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static Object convertToObject(final short value)
    {
        return AsConversions.convertToObject(value);
    }

    /**
     * This method converts an int value to an Object value.
     *
     * <p>
     * This method simply performs a boxing conversion.
     * </p>
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static Object convertToObject(final int value)
    {
        return AsConversions.convertToObject(value);
    }

    /**
     * This method converts a long value to an Object value.
     *
     * <p>
     * This method simply performs a boxing conversion.
     * </p>
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static Object convertToObject(final long value)
    {
        return AsConversions.convertToObject(value);
    }

    /**
     * This method converts a float value to an Object value.
     *
     * <p>
     * This method simply performs a boxing conversion.
     * </p>
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static Object convertToObject(final float value)
    {
        return AsConversions.convertToObject(value);
    }

    /**
     * This method converts a double value to an Object value.
     *
     * <p>
     * This method simply performs a boxing conversion.
     * </p>
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static Object convertToObject(final double value)
    {
        return AsConversions.convertToObject(value);
    }

    /**
     * This method converts a Boolean value to an Object value.
     *
     * <p>
     * This method simply returns the value itself.
     * </p>
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static Object convertToObject(final Boolean value)
    {
        check(value);
        return AsConversions.convertToObject(value);
    }

    /**
     * This method converts a Character value to an Object value.
     *
     * <p>
     * This method simply returns the value itself.
     * </p>
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static Object convertToObject(final Character value)
    {
        check(value);
        return AsConversions.convertToObject(value);
    }

    /**
     * This method converts a Byte value to an Object value.
     *
     * <p>
     * This method simply returns the value itself.
     * </p>
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static Object convertToObject(final Byte value)
    {
        check(value);
        return AsConversions.convertToObject(value);
    }

    /**
     * This method converts a Short value to an Object value.
     *
     * <p>
     * This method simply returns the value itself.
     * </p>
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static Object convertToObject(final Short value)
    {
        check(value);
        return AsConversions.convertToObject(value);
    }

    /**
     * This method converts an Integer value to an Object value.
     *
     * <p>
     * This method simply returns the value itself.
     * </p>
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static Object convertToObject(final Integer value)
    {
        check(value);
        return AsConversions.convertToObject(value);
    }

    /**
     * This method converts a Long value to an Object value.
     *
     * <p>
     * This method simply returns the value itself.
     * </p>
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static Object convertToObject(final Long value)
    {
        check(value);
        return AsConversions.convertToObject(value);
    }

    /**
     * This method converts a Float value to an Object value.
     *
     * <p>
     * This method simply returns the value itself.
     * </p>
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static Object convertToObject(final Float value)
    {
        check(value);
        return AsConversions.convertToObject(value);
    }

    /**
     * This method converts a Double value to an Object value.
     *
     * <p>
     * This method simply returns the value itself.
     * </p>
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static Object convertToObject(final Double value)
    {
        check(value);
        return AsConversions.convertToObject(value);
    }

    /**
     * This method converts a BigInteger value to an Object value.
     *
     * <p>
     * This method simply returns the value itself.
     * </p>
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static Object convertToObject(final BigInteger value)
    {
        check(value);
        return AsConversions.convertToObject(value);
    }

    /**
     * This method converts a BigDecimal value to an Object value.
     *
     * <p>
     * This method simply returns the value itself.
     * </p>
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static Object convertToObject(final BigDecimal value)
    {
        check(value);
        return AsConversions.convertToObject(value);
    }

    /**
     * This method converts a String value to an Object value.
     *
     * <p>
     * This method simply returns the value itself.
     * </p>
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static Object convertToObject(final String value)
    {
        return AsConversions.convertToObject(value);
    }

    /**
     * This method converts a Object value to an Object value.
     *
     * <p>
     * This method simply returns the value itself.
     * </p>
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static Object convertToObject(final Object value)
    {
        return value;
    }
}

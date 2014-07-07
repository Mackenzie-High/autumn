package autumn.lang.internals;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * This class provides the predefined as-conversions.
 *
 * @author Mackenzie High
 */
public final class AsConversions
{
    private static final String NULL = "null";

    /**
     * This method converts a boolean value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToPrimitiveBoolean(final boolean value)
    {
        return value;
    }

    /**
     * This method converts a char value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToPrimitiveBoolean(final char value)
    {
        return value != 0;
    }

    /**
     * This method converts a byte value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToPrimitiveBoolean(final byte value)
    {
        return value != 0;
    }

    /**
     * This method converts a short value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToPrimitiveBoolean(final short value)
    {
        return value != 0;
    }

    /**
     * This method converts an int value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToPrimitiveBoolean(final int value)
    {
        return value != 0;
    }

    /**
     * This method converts a long value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToPrimitiveBoolean(final long value)
    {
        return value != 0;
    }

    /**
     * This method converts a float value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToPrimitiveBoolean(final float value)
    {
        return value != 0;
    }

    /**
     * This method converts a double value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToPrimitiveBoolean(final double value)
    {
        return value != 0;
    }

    /**
     * This method converts a Boolean value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToPrimitiveBoolean(final Boolean value)
    {
        return (value != null) && convertToPrimitiveBoolean((boolean) value);
    }

    /**
     * This method converts a Character value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToPrimitiveBoolean(final Character value)
    {
        return (value != null) && convertToPrimitiveBoolean((char) value);
    }

    /**
     * This method converts a Byte value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToPrimitiveBoolean(final Byte value)
    {
        return (value != null) && convertToPrimitiveBoolean((byte) value);
    }

    /**
     * This method converts a Short value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToPrimitiveBoolean(final Short value)
    {
        return (value != null) && convertToPrimitiveBoolean((short) value);
    }

    /**
     * This method converts an Integer value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToPrimitiveBoolean(final Integer value)
    {
        return (value != null) && convertToPrimitiveBoolean((int) value);
    }

    /**
     * This method converts a Long value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToPrimitiveBoolean(final Long value)
    {
        return (value != null) && convertToPrimitiveBoolean((long) value);
    }

    /**
     * This method converts a Float value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToPrimitiveBoolean(final Float value)
    {
        return (value != null) && convertToPrimitiveBoolean((float) value);
    }

    /**
     * This method converts a Double value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToPrimitiveBoolean(final Double value)
    {
        return (value != null) && convertToPrimitiveBoolean((double) value);
    }

    /**
     * This method converts a BigInteger value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToPrimitiveBoolean(final BigInteger value)
    {
        return (value != null) && (value.equals(BigInteger.ZERO) == false);
    }

    /**
     * This method converts a BigDecimal value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToPrimitiveBoolean(final BigDecimal value)
    {
        return (value != null) && (value.equals(BigDecimal.ZERO) == false);
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
        return (value != null) && Boolean.valueOf(value);
    }

    /**
     * This method converts a boolean value to a char value.
     *
     * @param value is the value to convert.
     */
    public static char convertToPrimitiveChar(final boolean value)
    {
        return (char) (value ? 1 : 0);
    }

    /**
     * This method converts a char value to a char value.
     *
     * @param value is the value to convert.
     */
    public static char convertToPrimitiveChar(final char value)
    {
        return (char) value;
    }

    /**
     * This method converts a byte value to a char value.
     *
     * @param value is the value to convert.
     */
    public static char convertToPrimitiveChar(final byte value)
    {
        return (char) value;
    }

    /**
     * This method converts a short value to a char value.
     *
     * @param value is the value to convert.
     */
    public static char convertToPrimitiveChar(final short value)
    {
        return (char) value;
    }

    /**
     * This method converts an int value to a char value.
     *
     * @param value is the value to convert.
     */
    public static char convertToPrimitiveChar(final int value)
    {
        return (char) value;
    }

    /**
     * This method converts a long value to a char value.
     *
     * @param value is the value to convert.
     */
    public static char convertToPrimitiveChar(final long value)
    {
        return (char) value;
    }

    /**
     * This method converts a float value to a char value.
     *
     * @param value is the value to convert.
     */
    public static char convertToPrimitiveChar(final float value)
    {
        return (char) value;
    }

    /**
     * This method converts a double value to a char value.
     *
     * @param value is the value to convert.
     */
    public static char convertToPrimitiveChar(final double value)
    {
        return (char) value;
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
        return (value == null) ? 0 : convertToPrimitiveChar((boolean) value);
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
        return (value == null) ? 0 : convertToPrimitiveChar((char) value);
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
        return (value == null) ? 0 : convertToPrimitiveChar((byte) value);
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
        return (value == null) ? 0 : convertToPrimitiveChar((short) value);
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
        return (value == null) ? 0 : convertToPrimitiveChar((int) value);
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
        return (value == null) ? 0 : convertToPrimitiveChar((long) value);
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
        return (value == null) ? 0 : convertToPrimitiveChar((float) value);
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
        return (value == null) ? 0 : convertToPrimitiveChar((double) value);
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
        return (value == null) ? 0 : (char) value.longValue();
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
        return (value == null) ? 0 : (char) value.doubleValue();
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
        return (value == null) || value.isEmpty() ? 0 : value.charAt(0);
    }

    /**
     * This method converts an Object value to a char value, if the Object is really a Character.
     *
     * <p>
     * If the value is null, then zero is returned. <br>
     * If the value is not a Character, then zero is returned.
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
        return (value == null) || (value instanceof Character == false) ? 0 : (Character) value;
    }

    /**
     * This method converts a boolean value to a byte value.
     *
     * @param value is the value to convert.
     */
    public static byte convertToPrimitiveByte(final boolean value)
    {
        return (byte) (value ? 1 : 0);
    }

    /**
     * This method converts a char value to a byte value.
     *
     * @param value is the value to convert.
     */
    public static byte convertToPrimitiveByte(final char value)
    {
        return (byte) value;
    }

    /**
     * This method converts a byte value to a byte value.
     *
     * @param value is the value to convert.
     */
    public static byte convertToPrimitiveByte(final byte value)
    {
        return (byte) value;
    }

    /**
     * This method converts a short value to a byte value.
     *
     * @param value is the value to convert.
     */
    public static byte convertToPrimitiveByte(final short value)
    {
        return (byte) value;
    }

    /**
     * This method converts an int value to a byte value.
     *
     * @param value is the value to convert.
     */
    public static byte convertToPrimitiveByte(final int value)
    {
        return (byte) value;
    }

    /**
     * This method converts a long value to a byte value.
     *
     * @param value is the value to convert.
     */
    public static byte convertToPrimitiveByte(final long value)
    {
        return (byte) value;
    }

    /**
     * This method converts a float value to a byte value.
     *
     * @param value is the value to convert.
     */
    public static byte convertToPrimitiveByte(final float value)
    {
        return (byte) value;
    }

    /**
     * This method converts a double value to a byte value.
     *
     * @param value is the value to convert.
     */
    public static byte convertToPrimitiveByte(final double value)
    {
        return (byte) value;
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
        return (value == null) ? 0 : convertToPrimitiveByte((boolean) value);
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
        return (value == null) ? 0 : convertToPrimitiveByte((char) value);
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
        return (value == null) ? 0 : convertToPrimitiveByte((byte) value);
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
        return (value == null) ? 0 : convertToPrimitiveByte((short) value);
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
        return (value == null) ? 0 : convertToPrimitiveByte((int) value);
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
        return (value == null) ? 0 : convertToPrimitiveByte((long) value);
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
        return (value == null) ? 0 : convertToPrimitiveByte((float) value);
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
        return (value == null) ? 0 : convertToPrimitiveByte((double) value);
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
        return (value == null) ? 0 : value.byteValue();
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
        return (value == null) ? 0 : value.byteValue();
    }

    /**
     * This method converts a String value to a byte value.
     *
     * <p>
     * If the value is null, then zero is returned. <br>
     * If the value is not parsable, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static byte convertToPrimitiveByte(final String value)
    {
        try
        {
            return value == null ? 0 : Byte.parseByte(value);
        }
        catch (NumberFormatException ex)
        {
            return 0;
        }
    }

    /**
     * This method converts an Object value to a byte value, if the Object is really a Byte.
     *
     * <p>
     * If the value is null, then zero is returned. <br>
     * If the value is not a Byte, then zero is returned.
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
        return (value == null) || (value instanceof Byte == false) ? 0 : (Byte) value;
    }

    /**
     * This method converts a boolean value to a short value.
     *
     * @param value is the value to convert.
     */
    public static short convertToPrimitiveShort(final boolean value)
    {
        return (short) (value ? 1 : 0);
    }

    /**
     * This method converts a char value to a short value.
     *
     * @param value is the value to convert.
     */
    public static short convertToPrimitiveShort(final char value)
    {
        return (short) value;
    }

    /**
     * This method converts a byte value to a short value.
     *
     * @param value is the value to convert.
     */
    public static short convertToPrimitiveShort(final byte value)
    {
        return (short) value;
    }

    /**
     * This method converts a short value to a short value.
     *
     * @param value is the value to convert.
     */
    public static short convertToPrimitiveShort(final short value)
    {
        return (short) value;
    }

    /**
     * This method converts an int value to a short value.
     *
     * @param value is the value to convert.
     */
    public static short convertToPrimitiveShort(final int value)
    {
        return (short) value;
    }

    /**
     * This method converts a long value to a short value.
     *
     * @param value is the value to convert.
     */
    public static short convertToPrimitiveShort(final long value)
    {
        return (short) value;
    }

    /**
     * This method converts a float value to a short value.
     *
     * @param value is the value to convert.
     */
    public static short convertToPrimitiveShort(final float value)
    {
        return (short) value;
    }

    /**
     * This method converts a double value to a short value.
     *
     * @param value is the value to convert.
     */
    public static short convertToPrimitiveShort(final double value)
    {
        return (short) value;
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
        return (value == null) ? 0 : convertToPrimitiveShort((boolean) value);
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
        return (value == null) ? 0 : convertToPrimitiveShort((char) value);
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
        return (value == null) ? 0 : convertToPrimitiveShort((byte) value);
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
        return (value == null) ? 0 : convertToPrimitiveShort((short) value);
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
        return (value == null) ? 0 : convertToPrimitiveShort((int) value);
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
        return (value == null) ? 0 : convertToPrimitiveShort((long) value);
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
        return (value == null) ? 0 : convertToPrimitiveShort((float) value);
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
        return (value == null) ? 0 : convertToPrimitiveShort((double) value);
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
        return (value == null) ? 0 : value.shortValue();
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
        return (value == null) ? 0 : value.shortValue();
    }

    /**
     * This method converts a String value to a short value.
     *
     * <p>
     * If the value is null, then zero is returned. <br>
     * If the value is not parsable, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static short convertToPrimitiveShort(final String value)
    {
        try
        {
            return value == null ? 0 : Short.parseShort(value);
        }
        catch (NumberFormatException ex)
        {
            return 0;
        }
    }

    /**
     * This method converts an Object value to a short value, if the Object is really a Short.
     *
     * <p>
     * If the value is null, then zero is returned. <br>
     * If the value is not a Short, then zero is returned.
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
        return (value == null) || (value instanceof Short == false) ? 0 : (Short) value;
    }

    /**
     * This method converts a boolean value to a int value.
     *
     * @param value is the value to convert.
     */
    public static int convertToPrimitiveInt(final boolean value)
    {
        return (int) (value ? 1 : 0);
    }

    /**
     * This method converts a char value to a int value.
     *
     * @param value is the value to convert.
     */
    public static int convertToPrimitiveInt(final char value)
    {
        return (int) value;
    }

    /**
     * This method converts a byte value to a int value.
     *
     * @param value is the value to convert.
     */
    public static int convertToPrimitiveInt(final byte value)
    {
        return (int) value;
    }

    /**
     * This method converts a short value to a int value.
     *
     * @param value is the value to convert.
     */
    public static int convertToPrimitiveInt(final short value)
    {
        return (int) value;
    }

    /**
     * This method converts an int value to a int value.
     *
     * @param value is the value to convert.
     */
    public static int convertToPrimitiveInt(final int value)
    {
        return (int) value;
    }

    /**
     * This method converts a long value to a int value.
     *
     * @param value is the value to convert.
     */
    public static int convertToPrimitiveInt(final long value)
    {
        return (int) value;
    }

    /**
     * This method converts a float value to a int value.
     *
     * @param value is the value to convert.
     */
    public static int convertToPrimitiveInt(final float value)
    {
        return (int) value;
    }

    /**
     * This method converts a double value to a int value.
     *
     * @param value is the value to convert.
     */
    public static int convertToPrimitiveInt(final double value)
    {
        return (int) value;
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
        return (value == null) ? 0 : convertToPrimitiveInt((boolean) value);
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
        return (value == null) ? 0 : convertToPrimitiveInt((char) value);
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
        return (value == null) ? 0 : convertToPrimitiveInt((byte) value);
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
        return (value == null) ? 0 : convertToPrimitiveInt((short) value);
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
        return (value == null) ? 0 : convertToPrimitiveInt((int) value);
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
        return (value == null) ? 0 : convertToPrimitiveInt((long) value);
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
        return (value == null) ? 0 : convertToPrimitiveInt((float) value);
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
        return (value == null) ? 0 : convertToPrimitiveInt((double) value);
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
        return (value == null) ? 0 : value.intValue();
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
        return (value == null) ? 0 : value.intValue();
    }

    /**
     * This method converts a String value to a int value.
     *
     * <p>
     * If the value is null, then zero is returned. <br>
     * If the value is not parsable, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static int convertToPrimitiveInt(final String value)
    {
        try
        {
            return value == null ? 0 : Integer.parseInt(value);
        }
        catch (NumberFormatException ex)
        {
            return 0;
        }
    }

    /**
     * This method converts an Object value to a int value, if the Object is really a Integer.
     *
     * <p>
     * If the value is null, then zero is returned. <br>
     * If the value is not a Integer, then zero is returned.
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
        return (value == null) || (value instanceof Integer == false) ? 0 : (Integer) value;
    }

    /**
     * This method converts a boolean value to a long value.
     *
     * @param value is the value to convert.
     */
    public static long convertToPrimitiveLong(final boolean value)
    {
        return (long) (value ? 1 : 0);
    }

    /**
     * This method converts a char value to a long value.
     *
     * @param value is the value to convert.
     */
    public static long convertToPrimitiveLong(final char value)
    {
        return (long) value;
    }

    /**
     * This method converts a byte value to a long value.
     *
     * @param value is the value to convert.
     */
    public static long convertToPrimitiveLong(final byte value)
    {
        return (long) value;
    }

    /**
     * This method converts a short value to a long value.
     *
     * @param value is the value to convert.
     */
    public static long convertToPrimitiveLong(final short value)
    {
        return (long) value;
    }

    /**
     * This method converts an int value to a long value.
     *
     * @param value is the value to convert.
     */
    public static long convertToPrimitiveLong(final int value)
    {
        return (long) value;
    }

    /**
     * This method converts a long value to a long value.
     *
     * @param value is the value to convert.
     */
    public static long convertToPrimitiveLong(final long value)
    {
        return (long) value;
    }

    /**
     * This method converts a float value to a long value.
     *
     * @param value is the value to convert.
     */
    public static long convertToPrimitiveLong(final float value)
    {
        return (long) value;
    }

    /**
     * This method converts a double value to a long value.
     *
     * @param value is the value to convert.
     */
    public static long convertToPrimitiveLong(final double value)
    {
        return (long) value;
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
        return (value == null) ? 0 : convertToPrimitiveLong((boolean) value);
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
        return (value == null) ? 0 : convertToPrimitiveLong((char) value);
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
        return (value == null) ? 0 : convertToPrimitiveLong((byte) value);
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
        return (value == null) ? 0 : convertToPrimitiveLong((short) value);
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
        return (value == null) ? 0 : convertToPrimitiveLong((int) value);
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
        return (value == null) ? 0 : convertToPrimitiveLong((long) value);
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
        return (value == null) ? 0 : convertToPrimitiveLong((float) value);
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
        return (value == null) ? 0 : convertToPrimitiveLong((double) value);
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
        return (value == null) ? 0 : value.longValue();
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
        return (value == null) ? 0 : value.longValue();
    }

    /**
     * This method converts a String value to a long value.
     *
     * <p>
     * If the value is null, then zero is returned. <br>
     * If the value is not parsable, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static long convertToPrimitiveLong(final String value)
    {
        try
        {
            return value == null ? 0 : Long.parseLong(value);
        }
        catch (NumberFormatException ex)
        {
            return 0;
        }
    }

    /**
     * This method converts an Object value to a long value, if the Object is really a Long.
     *
     * <p>
     * If the value is null, then zero is returned. <br>
     * If the value is not a Long, then zero is returned.
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
        return (value == null) || (value instanceof Long == false) ? 0 : (Long) value;
    }

    /**
     * This method converts a boolean value to a float value.
     *
     * @param value is the value to convert.
     */
    public static float convertToPrimitiveFloat(final boolean value)
    {
        return (float) (value ? 1 : 0);
    }

    /**
     * This method converts a char value to a float value.
     *
     * @param value is the value to convert.
     */
    public static float convertToPrimitiveFloat(final char value)
    {
        return (float) value;
    }

    /**
     * This method converts a byte value to a float value.
     *
     * @param value is the value to convert.
     */
    public static float convertToPrimitiveFloat(final byte value)
    {
        return (float) value;
    }

    /**
     * This method converts a short value to a float value.
     *
     * @param value is the value to convert.
     */
    public static float convertToPrimitiveFloat(final short value)
    {
        return (float) value;
    }

    /**
     * This method converts an int value to a float value.
     *
     * @param value is the value to convert.
     */
    public static float convertToPrimitiveFloat(final int value)
    {
        return (float) value;
    }

    /**
     * This method converts a long value to a float value.
     *
     * @param value is the value to convert.
     */
    public static float convertToPrimitiveFloat(final long value)
    {
        return (float) value;
    }

    /**
     * This method converts a float value to a float value.
     *
     * @param value is the value to convert.
     */
    public static float convertToPrimitiveFloat(final float value)
    {
        return (float) value;
    }

    /**
     * This method converts a double value to a float value.
     *
     * @param value is the value to convert.
     */
    public static float convertToPrimitiveFloat(final double value)
    {
        return (float) value;
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
        return (value == null) ? 0 : convertToPrimitiveFloat((boolean) value);
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
        return (value == null) ? 0 : convertToPrimitiveFloat((char) value);
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
        return (value == null) ? 0 : convertToPrimitiveFloat((byte) value);
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
        return (value == null) ? 0 : convertToPrimitiveFloat((short) value);
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
        return (value == null) ? 0 : convertToPrimitiveFloat((int) value);
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
        return (value == null) ? 0 : convertToPrimitiveFloat((long) value);
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
        return (value == null) ? 0 : convertToPrimitiveFloat((float) value);
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
        return (value == null) ? 0 : convertToPrimitiveFloat((double) value);
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
        return (value == null) ? 0 : value.floatValue();
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
        return (value == null) ? 0 : value.floatValue();
    }

    /**
     * This method converts a String value to a float value.
     *
     * <p>
     * If the value is null, then zero is returned. <br>
     * If the value is not parsable, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static float convertToPrimitiveFloat(final String value)
    {
        try
        {
            return value == null ? 0 : Float.parseFloat(value);
        }
        catch (NumberFormatException ex)
        {
            return 0;
        }
    }

    /**
     * This method converts an Object value to a float value, if the Object is really a Float.
     *
     * <p>
     * If the value is null, then zero is returned. <br>
     * If the value is not a Float, then zero is returned.
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
        return (value == null) || (value instanceof Float == false) ? 0 : (Float) value;
    }

    /**
     * This method converts a boolean value to a double value.
     *
     * @param value is the value to convert.
     */
    public static double convertToPrimitiveDouble(final boolean value)
    {
        return (double) (value ? 1 : 0);
    }

    /**
     * This method converts a char value to a double value.
     *
     * @param value is the value to convert.
     */
    public static double convertToPrimitiveDouble(final char value)
    {
        return (double) value;
    }

    /**
     * This method converts a byte value to a double value.
     *
     * @param value is the value to convert.
     */
    public static double convertToPrimitiveDouble(final byte value)
    {
        return (double) value;
    }

    /**
     * This method converts a short value to a double value.
     *
     * @param value is the value to convert.
     */
    public static double convertToPrimitiveDouble(final short value)
    {
        return (double) value;
    }

    /**
     * This method converts an int value to a double value.
     *
     * @param value is the value to convert.
     */
    public static double convertToPrimitiveDouble(final int value)
    {
        return (double) value;
    }

    /**
     * This method converts a long value to a double value.
     *
     * @param value is the value to convert.
     */
    public static double convertToPrimitiveDouble(final long value)
    {
        return (double) value;
    }

    /**
     * This method converts a float value to a double value.
     *
     * @param value is the value to convert.
     */
    public static double convertToPrimitiveDouble(final float value)
    {
        return (double) value;
    }

    /**
     * This method converts a double value to a double value.
     *
     * @param value is the value to convert.
     */
    public static double convertToPrimitiveDouble(final double value)
    {
        return (double) value;
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
        return (value == null) ? 0 : convertToPrimitiveDouble((boolean) value);
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
        return (value == null) ? 0 : convertToPrimitiveDouble((char) value);
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
        return (value == null) ? 0 : convertToPrimitiveDouble((byte) value);
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
        return (value == null) ? 0 : convertToPrimitiveDouble((short) value);
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
        return (value == null) ? 0 : convertToPrimitiveDouble((int) value);
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
        return (value == null) ? 0 : convertToPrimitiveDouble((long) value);
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
        return (value == null) ? 0 : convertToPrimitiveDouble((float) value);
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
        return (value == null) ? 0 : convertToPrimitiveDouble((double) value);
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
        return (value == null) ? 0 : value.doubleValue();
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
        return (value == null) ? 0 : value.doubleValue();
    }

    /**
     * This method converts a String value to a double value.
     *
     * <p>
     * If the value is null, then zero is returned. <br>
     * If the value is not parsable, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static double convertToPrimitiveDouble(final String value)
    {
        try
        {
            return value == null ? 0 : Double.parseDouble(value);
        }
        catch (NumberFormatException ex)
        {
            return 0;
        }
    }

    /**
     * This method converts an Object value to a double value, if the Object is really a Double.
     *
     * <p>
     * If the value is null, then zero is returned. <br>
     * If the value is not a Double, then zero is returned.
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
        return (value == null) || (value instanceof Double == false) ? 0 : (Double) value;
    }

    /**
     * This method converts a boolean value to a Boolean value.
     *
     * @param value is the value to convert.
     */
    public static Boolean convertToBoxedBoolean(final boolean value)
    {
        return (Boolean) value;
    }

    /**
     * This method converts a char value to a Boolean value.
     *
     * @param value is the value to convert.
     */
    public static Boolean convertToBoxedBoolean(final char value)
    {
        return value != 0;
    }

    /**
     * This method converts a byte value to a Boolean value.
     *
     * @param value is the value to convert.
     */
    public static Boolean convertToBoxedBoolean(final byte value)
    {
        return value != 0;
    }

    /**
     * This method converts a short value to a Boolean value.
     *
     * @param value is the value to convert.
     */
    public static Boolean convertToBoxedBoolean(final short value)
    {
        return value != 0;
    }

    /**
     * This method converts an int value to a Boolean value.
     *
     * @param value is the value to convert.
     */
    public static Boolean convertToBoxedBoolean(final int value)
    {
        return value != 0;
    }

    /**
     * This method converts a long value to a Boolean value.
     *
     * @param value is the value to convert.
     */
    public static Boolean convertToBoxedBoolean(final long value)
    {
        return value != 0;
    }

    /**
     * This method converts a float value to a Boolean value.
     *
     * @param value is the value to convert.
     */
    public static Boolean convertToBoxedBoolean(final float value)
    {
        return value != 0;
    }

    /**
     * This method converts a double value to a Boolean value.
     *
     * @param value is the value to convert.
     */
    public static Boolean convertToBoxedBoolean(final double value)
    {
        return value != 0;
    }

    /**
     * This method converts a boolean value to a Character value.
     *
     * @param value is the value to convert.
     */
    public static Character convertToBoxedChar(final boolean value)
    {
        return (char) (value ? 1 : 0);
    }

    /**
     * This method converts a char value to a Character value.
     *
     * @param value is the value to convert.
     */
    public static Character convertToBoxedChar(final char value)
    {
        return (Character) value;
    }

    /**
     * This method converts a byte value to a Character value.
     *
     * @param value is the value to convert.
     */
    public static Character convertToBoxedChar(final byte value)
    {
        return (char) value;
    }

    /**
     * This method converts a short value to a Character value.
     *
     * @param value is the value to convert.
     */
    public static Character convertToBoxedChar(final short value)
    {
        return (char) value;
    }

    /**
     * This method converts an int value to a Character value.
     *
     * @param value is the value to convert.
     */
    public static Character convertToBoxedChar(final int value)
    {
        return (char) value;
    }

    /**
     * This method converts a long value to a Character value.
     *
     * @param value is the value to convert.
     */
    public static Character convertToBoxedChar(final long value)
    {
        return (char) value;
    }

    /**
     * This method converts a float value to a Character value.
     *
     * @param value is the value to convert.
     */
    public static Character convertToBoxedChar(final float value)
    {
        return (char) value;
    }

    /**
     * This method converts a double value to a Character value.
     *
     * @param value is the value to convert.
     */
    public static Character convertToBoxedChar(final double value)
    {
        return (char) value;
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
        return (value == null) ? 0 : convertToBoxedChar((boolean) value);
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
        return (value == null) ? 0 : convertToBoxedChar((char) value);
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
        return (value == null) ? 0 : convertToBoxedChar((byte) value);
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
        return (value == null) ? 0 : convertToBoxedChar((short) value);
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
        return (value == null) ? 0 : convertToBoxedChar((int) value);
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
        return (value == null) ? 0 : convertToBoxedChar((long) value);
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
        return (value == null) ? 0 : convertToBoxedChar((float) value);
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
        return (value == null) ? 0 : convertToBoxedChar((double) value);
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
        return (value == null) ? 0 : (char) value.longValue();
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
        return (value == null) ? 0 : (char) value.longValue();
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
        if (value == null)
        {
            return null;
        }
        else if (value.isEmpty())
        {
            return null;
        }
        else
        {
            return value.charAt(0);
        }
    }

    /**
     * This method converts an Object value to a Character value, if the Object is really a
     * Character.
     *
     * <p>
     * If the value is null, then zero is returned. <br>
     * If the value is not a Character, then zero is returned.
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
        return (value == null) || (value instanceof Character == false) ? 0 : (Character) value;
    }

    /**
     * This method converts a boolean value to a Byte value.
     *
     * @param value is the value to convert.
     */
    public static Byte convertToBoxedByte(final boolean value)
    {
        return (Byte) (byte) (value ? 1 : 0);
    }

    /**
     * This method converts a char value to a Byte value.
     *
     * @param value is the value to convert.
     */
    public static Byte convertToBoxedByte(final char value)
    {
        return (Byte) (byte) value;
    }

    /**
     * This method converts a byte value to a Byte value.
     *
     * @param value is the value to convert.
     */
    public static Byte convertToBoxedByte(final byte value)
    {
        return (Byte) value;
    }

    /**
     * This method converts a short value to a Byte value.
     *
     * @param value is the value to convert.
     */
    public static Byte convertToBoxedByte(final short value)
    {
        return (Byte) (byte) value;
    }

    /**
     * This method converts an int value to a Byte value.
     *
     * @param value is the value to convert.
     */
    public static Byte convertToBoxedByte(final int value)
    {
        return (Byte) (byte) value;
    }

    /**
     * This method converts a long value to a Byte value.
     *
     * @param value is the value to convert.
     */
    public static Byte convertToBoxedByte(final long value)
    {
        return (Byte) (byte) value;
    }

    /**
     * This method converts a float value to a Byte value.
     *
     * @param value is the value to convert.
     */
    public static Byte convertToBoxedByte(final float value)
    {
        return (Byte) (byte) value;
    }

    /**
     * This method converts a double value to a Byte value.
     *
     * @param value is the value to convert.
     */
    public static Byte convertToBoxedByte(final double value)
    {
        return (Byte) (byte) value;
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
        return (value == null) ? 0 : convertToBoxedByte((boolean) value);
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
        return (value == null) ? 0 : convertToBoxedByte((char) value);
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
        return (value == null) ? 0 : convertToBoxedByte((byte) value);
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
        return (value == null) ? 0 : convertToBoxedByte((short) value);
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
        return (value == null) ? 0 : convertToBoxedByte((int) value);
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
        return (value == null) ? 0 : convertToBoxedByte((long) value);
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
        return (value == null) ? 0 : convertToBoxedByte((float) value);
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
        return (value == null) ? 0 : convertToBoxedByte((double) value);
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
        return (value == null) ? 0 : value.byteValue();
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
        return (value == null) ? 0 : value.byteValue();
    }

    /**
     * This method converts a String value to a Byte value.
     *
     * <p>
     * If the value is null, then zero is returned. <br>
     * If the value is not parsable, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Byte convertToBoxedByte(final String value)
    {
        try
        {
            return value == null ? 0 : Byte.parseByte(value);
        }
        catch (NumberFormatException ex)
        {
            return 0;
        }
    }

    /**
     * This method converts an Object value to a Byte value, if the Object is really a Byte.
     *
     * <p>
     * If the value is null, then zero is returned. <br>
     * If the value is not a Byte, then zero is returned.
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
        return (value == null) || (value instanceof Byte == false) ? 0 : (Byte) value;
    }

    /**
     * This method converts a boolean value to a Short value.
     *
     * @param value is the value to convert.
     */
    public static Short convertToBoxedShort(final boolean value)
    {
        return (short) (value ? 1 : 0);
    }

    /**
     * This method converts a char value to a Short value.
     *
     * @param value is the value to convert.
     */
    public static Short convertToBoxedShort(final char value)
    {
        return (short) value;
    }

    /**
     * This method converts a byte value to a Short value.
     *
     * @param value is the value to convert.
     */
    public static Short convertToBoxedShort(final byte value)
    {
        return (short) value;
    }

    /**
     * This method converts a short value to a Short value.
     *
     * @param value is the value to convert.
     */
    public static Short convertToBoxedShort(final short value)
    {
        return (Short) value;
    }

    /**
     * This method converts an int value to a Short value.
     *
     * @param value is the value to convert.
     */
    public static Short convertToBoxedShort(final int value)
    {
        return (short) value;
    }

    /**
     * This method converts a long value to a Short value.
     *
     * @param value is the value to convert.
     */
    public static Short convertToBoxedShort(final long value)
    {
        return (short) value;
    }

    /**
     * This method converts a float value to a Short value.
     *
     * @param value is the value to convert.
     */
    public static Short convertToBoxedShort(final float value)
    {
        return (short) value;
    }

    /**
     * This method converts a double value to a Short value.
     *
     * @param value is the value to convert.
     */
    public static Short convertToBoxedShort(final double value)
    {
        return (short) value;
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
        return (value == null) ? 0 : convertToBoxedShort((boolean) value);
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
        return (value == null) ? 0 : convertToBoxedShort((char) value);
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
        return (value == null) ? 0 : convertToBoxedShort((byte) value);
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
        return (value == null) ? 0 : convertToBoxedShort((short) value);
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
        return (value == null) ? 0 : convertToBoxedShort((int) value);
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
        return (value == null) ? 0 : convertToBoxedShort((long) value);
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
        return (value == null) ? 0 : convertToBoxedShort((float) value);
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
        return (value == null) ? 0 : convertToBoxedShort((double) value);
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
        return (value == null) ? 0 : value.shortValue();
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
        return (value == null) ? 0 : value.shortValue();
    }

    /**
     * This method converts a String value to a Short value.
     *
     * <p>
     * If the value is null, then zero is returned. <br>
     * If the value is not parsable, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Short convertToBoxedShort(final String value)
    {
        try
        {
            return value == null ? 0 : Short.parseShort(value);
        }
        catch (NumberFormatException ex)
        {
            return 0;
        }
    }

    /**
     * This method converts an Object value to a Short value, if the Object is really a Short.
     *
     * <p>
     * If the value is null, then zero is returned. <br>
     * If the value is not a Short, then zero is returned.
     * </p>
     *
     * <p>
     * This method tries to cast the value to a Short and then unbox the result.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Short convertToBoxedShort(final Object value)
    {
        return (value == null) || (value instanceof Short == false) ? 0 : (Short) value;
    }

    /**
     * This method converts a boolean value to a Integer value.
     *
     * @param value is the value to convert.
     */
    public static Integer convertToBoxedInt(final boolean value)
    {
        return (Integer) (value ? 1 : 0);
    }

    /**
     * This method converts a char value to a Integer value.
     *
     * @param value is the value to convert.
     */
    public static Integer convertToBoxedInt(final char value)
    {
        return (int) value;
    }

    /**
     * This method converts a byte value to a Integer value.
     *
     * @param value is the value to convert.
     */
    public static Integer convertToBoxedInt(final byte value)
    {
        return (int) value;
    }

    /**
     * This method converts a short value to a Integer value.
     *
     * @param value is the value to convert.
     */
    public static Integer convertToBoxedInt(final short value)
    {
        return (int) value;
    }

    /**
     * This method converts an int value to a Integer value.
     *
     * @param value is the value to convert.
     */
    public static Integer convertToBoxedInt(final int value)
    {
        return (Integer) value;
    }

    /**
     * This method converts a long value to a Integer value.
     *
     * @param value is the value to convert.
     */
    public static Integer convertToBoxedInt(final long value)
    {
        return (int) value;
    }

    /**
     * This method converts a float value to a Integer value.
     *
     * @param value is the value to convert.
     */
    public static Integer convertToBoxedInt(final float value)
    {
        return (int) value;
    }

    /**
     * This method converts a double value to a Integer value.
     *
     * @param value is the value to convert.
     */
    public static Integer convertToBoxedInt(final double value)
    {
        return (int) value;
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
        return (value == null) ? 0 : convertToBoxedInt((boolean) value);
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
        return (value == null) ? 0 : convertToBoxedInt((char) value);
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
        return (value == null) ? 0 : convertToBoxedInt((byte) value);
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
        return (value == null) ? 0 : convertToBoxedInt((short) value);
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
        return (value == null) ? 0 : convertToBoxedInt((int) value);
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
        return (value == null) ? 0 : convertToBoxedInt((long) value);
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
        return (value == null) ? 0 : convertToBoxedInt((float) value);
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
        return (value == null) ? 0 : convertToBoxedInt((double) value);
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
        return (value == null) ? 0 : value.intValue();
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
        return (value == null) ? 0 : value.intValue();
    }

    /**
     * This method converts a String value to a Integer value.
     *
     * <p>
     * If the value is null, then zero is returned. <br>
     * If the value is not parsable, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Integer convertToBoxedInt(final String value)
    {
        try
        {
            return value == null ? 0 : Integer.parseInt(value);
        }
        catch (NumberFormatException ex)
        {
            return 0;
        }
    }

    /**
     * This method converts an Object value to a Integer value, if the Object is really a Integer.
     *
     * <p>
     * If the value is null, then zero is returned. <br>
     * If the value is not a Integer, then zero is returned.
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
        return (value == null) || (value instanceof Integer == false) ? 0 : (Integer) value;
    }

    /**
     * This method converts a boolean value to a Long value.
     *
     * @param value is the value to convert.
     */
    public static Long convertToBoxedLong(final boolean value)
    {
        return (long) (value ? 1 : 0);
    }

    /**
     * This method converts a char value to a Long value.
     *
     * @param value is the value to convert.
     */
    public static Long convertToBoxedLong(final char value)
    {
        return (long) value;
    }

    /**
     * This method converts a byte value to a Long value.
     *
     * @param value is the value to convert.
     */
    public static Long convertToBoxedLong(final byte value)
    {
        return (long) value;
    }

    /**
     * This method converts a short value to a Long value.
     *
     * @param value is the value to convert.
     */
    public static Long convertToBoxedLong(final short value)
    {
        return (long) value;
    }

    /**
     * This method converts an int value to a Long value.
     *
     * @param value is the value to convert.
     */
    public static Long convertToBoxedLong(final int value)
    {
        return (long) value;
    }

    /**
     * This method converts a long value to a Long value.
     *
     * @param value is the value to convert.
     */
    public static Long convertToBoxedLong(final long value)
    {
        return (long) value;
    }

    /**
     * This method converts a float value to a Long value.
     *
     * @param value is the value to convert.
     */
    public static Long convertToBoxedLong(final float value)
    {
        return (long) value;
    }

    /**
     * This method converts a double value to a Long value.
     *
     * @param value is the value to convert.
     */
    public static Long convertToBoxedLong(final double value)
    {
        return (long) value;
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
        return (value == null) ? 0 : convertToBoxedLong((boolean) value);
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
        return (value == null) ? 0 : convertToBoxedLong((char) value);
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
        return (value == null) ? 0 : convertToBoxedLong((byte) value);
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
        return (value == null) ? 0 : convertToBoxedLong((short) value);
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
        return (value == null) ? 0 : convertToBoxedLong((int) value);
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
        return (value == null) ? 0 : convertToBoxedLong((long) value);
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
        return (value == null) ? 0 : convertToBoxedLong((float) value);
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
        return (value == null) ? 0 : convertToBoxedLong((double) value);
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
        return (value == null) ? 0 : value.longValue();
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
        return (value == null) ? 0 : value.longValue();
    }

    /**
     * This method converts a String value to a Long value.
     *
     * <p>
     * If the value is null, then zero is returned. <br>
     * If the value is not parsable, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Long convertToBoxedLong(final String value)
    {
        try
        {
            return value == null ? 0 : Long.parseLong(value);
        }
        catch (NumberFormatException ex)
        {
            return (long) 0;
        }
    }

    /**
     * This method converts an Object value to a Long value, if the Object is really a Long.
     *
     * <p>
     * If the value is null, then zero is returned. <br>
     * If the value is not a Long, then zero is returned.
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
        return (value == null) || (value instanceof Long == false) ? 0 : (Long) value;
    }

    /**
     * This method converts a boolean value to a Float value.
     *
     * @param value is the value to convert.
     */
    public static Float convertToBoxedFloat(final boolean value)
    {
        return (float) (value ? 1 : 0);
    }

    /**
     * This method converts a char value to a Float value.
     *
     * @param value is the value to convert.
     */
    public static Float convertToBoxedFloat(final char value)
    {
        return (float) value;
    }

    /**
     * This method converts a byte value to a Float value.
     *
     * @param value is the value to convert.
     */
    public static Float convertToBoxedFloat(final byte value)
    {
        return (float) value;
    }

    /**
     * This method converts a short value to a Float value.
     *
     * @param value is the value to convert.
     */
    public static Float convertToBoxedFloat(final short value)
    {
        return (float) value;
    }

    /**
     * This method converts an int value to a Float value.
     *
     * @param value is the value to convert.
     */
    public static Float convertToBoxedFloat(final int value)
    {
        return (float) value;
    }

    /**
     * This method converts a long value to a Float value.
     *
     * @param value is the value to convert.
     */
    public static Float convertToBoxedFloat(final long value)
    {
        return (float) value;
    }

    /**
     * This method converts a float value to a Float value.
     *
     * @param value is the value to convert.
     */
    public static Float convertToBoxedFloat(final float value)
    {
        return (float) value;
    }

    /**
     * This method converts a double value to a Float value.
     *
     * @param value is the value to convert.
     */
    public static Float convertToBoxedFloat(final double value)
    {
        return (float) value;
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
        return (value == null) ? 0 : convertToBoxedFloat((boolean) value);
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
        return (value == null) ? 0 : convertToBoxedFloat((char) value);
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
        return (value == null) ? 0 : convertToBoxedFloat((byte) value);
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
        return (value == null) ? 0 : convertToBoxedFloat((short) value);
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
        return (value == null) ? 0 : convertToBoxedFloat((int) value);
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
        return (value == null) ? 0 : convertToBoxedFloat((long) value);
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
        return (value == null) ? 0 : convertToBoxedFloat((float) value);
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
        return (value == null) ? 0 : convertToBoxedFloat((double) value);
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
        return (value == null) ? 0 : value.floatValue();
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
        return (value == null) ? 0 : value.floatValue();
    }

    /**
     * This method converts a String value to a Float value.
     *
     * <p>
     * If the value is null, then zero is returned. <br>
     * If the value is not parsable, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Float convertToBoxedFloat(final String value)
    {
        try
        {
            return value == null ? 0 : Float.parseFloat(value);
        }
        catch (NumberFormatException ex)
        {
            return (float) 0;
        }
    }

    /**
     * This method converts an Object value to a Float value, if the Object is really a Float.
     *
     * <p>
     * If the value is null, then zero is returned. <br>
     * If the value is not a Float, then zero is returned.
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
        return (value == null) || (value instanceof Float == false) ? 0 : (Float) value;
    }

    /**
     * This method converts a boolean value to a Double value.
     *
     * @param value is the value to convert.
     */
    public static Double convertToBoxedDouble(final boolean value)
    {
        return (double) (value ? 1 : 0);
    }

    /**
     * This method converts a char value to a Double value.
     *
     * @param value is the value to convert.
     */
    public static Double convertToBoxedDouble(final char value)
    {
        return (double) value;
    }

    /**
     * This method converts a byte value to a Double value.
     *
     * @param value is the value to convert.
     */
    public static Double convertToBoxedDouble(final byte value)
    {
        return (double) value;
    }

    /**
     * This method converts a short value to a Double value.
     *
     * @param value is the value to convert.
     */
    public static Double convertToBoxedDouble(final short value)
    {
        return (double) value;
    }

    /**
     * This method converts an int value to a Double value.
     *
     * @param value is the value to convert.
     */
    public static Double convertToBoxedDouble(final int value)
    {
        return (double) value;
    }

    /**
     * This method converts a long value to a Double value.
     *
     * @param value is the value to convert.
     */
    public static Double convertToBoxedDouble(final long value)
    {
        return (double) value;
    }

    /**
     * This method converts a float value to a Double value.
     *
     * @param value is the value to convert.
     */
    public static Double convertToBoxedDouble(final float value)
    {
        return (double) value;
    }

    /**
     * This method converts a double value to a Double value.
     *
     * @param value is the value to convert.
     */
    public static Double convertToBoxedDouble(final double value)
    {
        return (double) value;
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
        return (value == null) ? 0 : convertToBoxedDouble((boolean) value);
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
        return (value == null) ? 0 : convertToBoxedDouble((char) value);
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
        return (value == null) ? 0 : convertToBoxedDouble((byte) value);
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
        return (value == null) ? 0 : convertToBoxedDouble((short) value);
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
        return (value == null) ? 0 : convertToBoxedDouble((int) value);
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
        return (value == null) ? 0 : convertToBoxedDouble((long) value);
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
        return (value == null) ? 0 : convertToBoxedDouble((float) value);
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
        return (value == null) ? 0 : convertToBoxedDouble((double) value);
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
        return (value == null) ? 0 : value.doubleValue();
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
        return (value == null) ? 0 : value.doubleValue();
    }

    /**
     * This method converts a String value to a Double value.
     *
     * <p>
     * If the value is null, then zero is returned. <br>
     * If the value is not parsable, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static Double convertToBoxedDouble(final String value)
    {
        try
        {
            return value == null ? 0 : Double.parseDouble(value);
        }
        catch (NumberFormatException ex)
        {
            return (double) 0;
        }
    }

    /**
     * This method converts a boolean value to a BigInteger value.
     *
     * @param value is the value to convert.
     */
    public static BigInteger convertToBigInteger(final boolean value)
    {
        return (BigInteger) (value ? BigInteger.ONE : BigInteger.ZERO);
    }

    /**
     * This method converts a char value to a BigInteger value.
     *
     * @param value is the value to convert.
     */
    public static BigInteger convertToBigInteger(final char value)
    {
        return BigInteger.valueOf(value);
    }

    /**
     * This method converts a byte value to a BigInteger value.
     *
     * @param value is the value to convert.
     */
    public static BigInteger convertToBigInteger(final byte value)
    {
        return BigInteger.valueOf(value);
    }

    /**
     * This method converts a short value to a BigInteger value.
     *
     * @param value is the value to convert.
     */
    public static BigInteger convertToBigInteger(final short value)
    {
        return BigInteger.valueOf(value);
    }

    /**
     * This method converts an int value to a BigInteger value.
     *
     * @param value is the value to convert.
     */
    public static BigInteger convertToBigInteger(final int value)
    {
        return BigInteger.valueOf(value);
    }

    /**
     * This method converts a long value to a BigInteger value.
     *
     * @param value is the value to convert.
     */
    public static BigInteger convertToBigInteger(final long value)
    {
        return BigInteger.valueOf(value);
    }

    /**
     * This method converts a float value to a BigInteger value.
     *
     * @param value is the value to convert.
     */
    public static BigInteger convertToBigInteger(final float value)
    {
        return BigInteger.valueOf((long) value);
    }

    /**
     * This method converts a double value to a BigInteger value.
     *
     * @param value is the value to convert.
     */
    public static BigInteger convertToBigInteger(final double value)
    {
        return BigInteger.valueOf((long) value);
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
        return (value == null) ? BigInteger.ZERO : convertToBigInteger((boolean) value);
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
        return (value == null) ? BigInteger.ZERO : convertToBigInteger((char) value);
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
        return (value == null) ? BigInteger.ZERO : convertToBigInteger((byte) value);
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
        return (value == null) ? BigInteger.ZERO : convertToBigInteger((short) value);
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
        return (value == null) ? BigInteger.ZERO : convertToBigInteger((int) value);
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
        return (value == null) ? BigInteger.ZERO : convertToBigInteger((long) value);
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
        return (value == null) ? BigInteger.ZERO : convertToBigInteger((float) value);
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
        return (value == null) ? BigInteger.ZERO : convertToBigInteger((double) value);
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
        return (value == null) ? BigInteger.ZERO : value;
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
        return (value == null) ? BigInteger.ZERO : value.toBigInteger();
    }

    /**
     * This method converts a String value to a BigInteger value.
     *
     * <p>
     * If the value is null, then zero is returned. <br>
     * If the value is not parsable, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static BigInteger convertToBigInteger(final String value)
    {
        try
        {
            return value == null ? BigInteger.ZERO : new BigInteger(value);
        }
        catch (NumberFormatException ex)
        {
            return BigInteger.ZERO;
        }
    }

    /**
     * This method converts an Object value to a BigInteger value, if the Object is really a
     * BigInteger.
     *
     * <p>
     * If the value is null, then zero is returned. <br>
     * If the value is not a BigInteger, then zero is returned.
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
        return (value == null) || (value instanceof BigInteger == false)
                ? BigInteger.ZERO : (BigInteger) value;
    }

    /**
     * This method converts a boolean value to a BigDecimal value.
     *
     * @param value is the value to convert.
     */
    public static BigDecimal convertToBigDecimal(final boolean value)
    {
        return (BigDecimal) (value ? BigDecimal.ONE : BigDecimal.ZERO);
    }

    /**
     * This method converts a char value to a BigDecimal value.
     *
     * @param value is the value to convert.
     */
    public static BigDecimal convertToBigDecimal(final char value)
    {
        return BigDecimal.valueOf(value);
    }

    /**
     * This method converts a byte value to a BigDecimal value.
     *
     * @param value is the value to convert.
     */
    public static BigDecimal convertToBigDecimal(final byte value)
    {
        return BigDecimal.valueOf(value);
    }

    /**
     * This method converts a short value to a BigDecimal value.
     *
     * @param value is the value to convert.
     */
    public static BigDecimal convertToBigDecimal(final short value)
    {
        return BigDecimal.valueOf(value);
    }

    /**
     * This method converts an int value to a BigDecimal value.
     *
     * @param value is the value to convert.
     */
    public static BigDecimal convertToBigDecimal(final int value)
    {
        return BigDecimal.valueOf(value);
    }

    /**
     * This method converts a long value to a BigDecimal value.
     *
     * @param value is the value to convert.
     */
    public static BigDecimal convertToBigDecimal(final long value)
    {
        return BigDecimal.valueOf(value);
    }

    /**
     * This method converts a float value to a BigDecimal value.
     *
     * @param value is the value to convert.
     */
    public static BigDecimal convertToBigDecimal(final float value)
    {
        return BigDecimal.valueOf(value);
    }

    /**
     * This method converts a double value to a BigDecimal value.
     *
     * @param value is the value to convert.
     */
    public static BigDecimal convertToBigDecimal(final double value)
    {
        return BigDecimal.valueOf(value);
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
        return (value == null) ? BigDecimal.ZERO : convertToBigDecimal((boolean) value);
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
        return (value == null) ? BigDecimal.ZERO : convertToBigDecimal((char) value);
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
        return (value == null) ? BigDecimal.ZERO : convertToBigDecimal((byte) value);
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
        return (value == null) ? BigDecimal.ZERO : convertToBigDecimal((short) value);
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
        return (value == null) ? BigDecimal.ZERO : convertToBigDecimal((int) value);
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
        return (value == null) ? BigDecimal.ZERO : convertToBigDecimal((long) value);
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
        return (value == null) ? BigDecimal.ZERO : convertToBigDecimal((float) value);
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
        return (value == null) ? BigDecimal.ZERO : convertToBigDecimal((double) value);
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
        return (value == null) ? BigDecimal.ZERO : new BigDecimal(value);
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
        return (value == null) ? BigDecimal.ZERO : value;
    }

    /**
     * This method converts a String value to a BigDecimal value.
     *
     * <p>
     * If the value is null, then zero is returned. <br>
     * If the value is not parsable, then zero is returned.
     * </p>
     *
     * @param value is the value to convert.
     */
    public static BigDecimal convertToBigDecimal(final String value)
    {
        try
        {
            return value == null ? BigDecimal.ZERO : new BigDecimal(value);
        }
        catch (NumberFormatException ex)
        {
            return BigDecimal.ZERO;
        }
    }

    /**
     * This method converts an Object value to a BigDecimal value, if the Object is really a
     * BigDecimal.
     *
     * <p>
     * If the value is null, then zero is returned. <br>
     * If the value is not a BigDecimal, then zero is returned.
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
        return (value == null) || (value instanceof BigDecimal == false)
                ? BigDecimal.ZERO : (BigDecimal) value;
    }

    /**
     * This method converts an Object value to a Double value, if the Object is really a Double.
     *
     * <p>
     * If the value is null, then zero is returned. <br>
     * If the value is not a Double, then zero is returned.
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
        return (value == null) || (value instanceof Double == false) ? 0 : (Double) value;
    }

    /**
     * This method converts a boolean value to a String value.
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static String convertToString(final boolean value)
    {
        return Boolean.toString(value);
    }

    /**
     * This method converts a char value to a String value.
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static String convertToString(final char value)
    {
        return Character.toString(value);
    }

    /**
     * This method converts a byte value to a String value.
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static String convertToString(final byte value)
    {
        return Byte.toString(value);
    }

    /**
     * This method converts a short value to a String value.
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static String convertToString(final short value)
    {
        return Short.toString(value);
    }

    /**
     * This method converts a int value to a String value.
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static String convertToString(final int value)
    {
        return Integer.toString(value);
    }

    /**
     * This method converts a long value to a String value.
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static String convertToString(final long value)
    {
        return Long.toString(value);
    }

    /**
     * This method converts a float value to a String value.
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static String convertToString(final float value)
    {
        return Float.toString(value);
    }

    /**
     * This method converts a double value to a String value.
     *
     * @param value is the value to convert.
     * @return the converted value.
     */
    public static String convertToString(final double value)
    {
        return Double.toString(value);
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
        return value == null ? NULL : value.toString();
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
        return value == null ? NULL : value.toString();
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
        return value == null ? NULL : value.toString();
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
        return value == null ? NULL : value.toString();
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
        return value == null ? NULL : value.toString();
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
        return value == null ? NULL : value.toString();
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
        return value == null ? NULL : value.toString();
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
        return value == null ? NULL : value.toString();
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
        return value == null ? NULL : value.toString();
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
        return value == null ? NULL : value.toString();
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
        return value == null ? NULL : value;
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
        return String.valueOf(value);
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
        return value;
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
        return value;
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
        return value;
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
        return value;
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
        return value;
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
        return value;
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
        return value;
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
        return value;
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
        return value;
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
        return value;
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
        return value;
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
        return value;
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
        return value;
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
        return value;
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
        return value;
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
        return value;
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
        return value;
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
        return value;
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
        return value;
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

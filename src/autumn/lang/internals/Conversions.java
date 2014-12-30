package autumn.lang.internals;

import high.mackenzie.autumn.resources.Finished;

/**
 * This class provides the static utility methods that perform the predefined conversions.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public final class Conversions
{
    /**
     * This method boxes a given input value.
     *
     * @param input is the value to box.
     * @return the boxed value.
     */
    public static Boolean box(final boolean input)
    {
        return (boolean) input;
    }

    /**
     * This method boxes a given input value.
     *
     * @param input is the value to box.
     * @return the boxed value.
     */
    public static Character box(final char input)
    {
        return (char) input;
    }

    /**
     * This method boxes a given input value.
     *
     * @param input is the value to box.
     * @return the boxed value.
     */
    public static Byte box(final byte input)
    {
        return (byte) input;
    }

    /**
     * This method boxes a given input value.
     *
     * @param input is the value to box.
     * @return the boxed value.
     */
    public static Short box(final short input)
    {
        return (short) input;
    }

    /**
     * This method boxes a given input value.
     *
     * @param input is the value to box.
     * @return the boxed value.
     */
    public static Integer box(final int input)
    {
        return (int) input;
    }

    /**
     * This method boxes a given input value.
     *
     * @param input is the value to box.
     * @return the boxed value.
     */
    public static Long box(final long input)
    {
        return (long) input;
    }

    /**
     * This method boxes a given input value.
     *
     * @param input is the value to box.
     * @return the boxed value.
     */
    public static Float box(final float input)
    {
        return (float) input;
    }

    /**
     * This method boxes a given input value.
     *
     * @param input is the value to box.
     * @return the boxed value.
     */
    public static Double box(final double input)
    {
        return (double) input;
    }

    /**
     * This method boxes a given input value.
     *
     * @param input is the value to box.
     * @return the boxed value.
     */
    public static Object boxToObject(final boolean input)
    {
        return (boolean) input;
    }

    /**
     * This method boxes a given input value.
     *
     * @param input is the value to box.
     * @return the boxed value.
     */
    public static Object boxToObject(final char input)
    {
        return (char) input;
    }

    /**
     * This method boxes a given input value.
     *
     * @param input is the value to box.
     * @return the boxed value.
     */
    public static Object boxToObject(final byte input)
    {
        return (byte) input;
    }

    /**
     * This method boxes a given input value.
     *
     * @param input is the value to box.
     * @return the boxed value.
     */
    public static Object boxToObject(final short input)
    {
        return (short) input;
    }

    /**
     * This method boxes a given input value.
     *
     * @param input is the value to box.
     * @return the boxed value.
     */
    public static Object boxToObject(final int input)
    {
        return (int) input;
    }

    /**
     * This method boxes a given input value.
     *
     * @param input is the value to box.
     * @return the boxed value.
     */
    public static Object boxToObject(final long input)
    {
        return (long) input;
    }

    /**
     * This method boxes a given input value.
     *
     * @param input is the value to box.
     * @return the boxed value.
     */
    public static Object boxToObject(final float input)
    {
        return (float) input;
    }

    /**
     * This method boxes a given input value.
     *
     * @param input is the value to box.
     * @return the boxed value.
     */
    public static Object boxToObject(final double input)
    {
        return (double) input;
    }

    /**
     * This method unboxes a given input value.
     *
     * @param input is the value to unbox.
     * @return the unboxed value.
     */
    public static boolean unbox(final Boolean input)
    {
        return input == null ? false : (boolean) input;
    }

    /**
     * This method unboxes a given input value.
     *
     * @param input is the value to unbox.
     * @return the unboxed value.
     */
    public static char unbox(final Character input)
    {
        return input == null ? 0 : (char) input;
    }

    /**
     * This method unboxes a given input value.
     *
     * @param input is the value to unbox.
     * @return the unboxed value.
     */
    public static byte unbox(final Byte input)
    {
        return input == null ? 0 : (byte) input;
    }

    /**
     * This method unboxes a given input value.
     *
     * @param input is the value to unbox.
     * @return the unboxed value.
     */
    public static short unbox(final Short input)
    {
        return input == null ? 0 : (short) input;
    }

    /**
     * This method unboxes a given input value.
     *
     * @param input is the value to unbox.
     * @return the unboxed value.
     */
    public static int unbox(final Integer input)
    {
        return input == null ? 0 : (int) input;
    }

    /**
     * This method unboxes a given input value.
     *
     * @param input is the value to unbox.
     * @return the unboxed value.
     */
    public static long unbox(final Long input)
    {
        return input == null ? 0 : (long) input;
    }

    /**
     * This method unboxes a given input value.
     *
     * @param input is the value to unbox.
     * @return the unboxed value.
     */
    public static float unbox(final Float input)
    {
        return input == null ? 0 : (float) input;
    }

    /**
     * This method unboxes a given input value.
     *
     * @param input is the value to unbox.
     * @return the unboxed value.
     */
    public static double unbox(final Double input)
    {
        return input == null ? 0 : (double) input;
    }

    /**
     * This method converts a value to its string representation.
     *
     * @param input is the value to stringify.
     * @return the string representation of the input value.
     */
    public static String convertToString(final boolean input)
    {
        return "" + input;
    }

    /**
     * This method converts a value to its string representation.
     *
     * @param input is the value to stringify.
     * @return the string representation of the input value.
     */
    public static String convertToString(final char input)
    {
        return "" + input;
    }

    /**
     * This method converts a value to its string representation.
     *
     * @param input is the value to stringify.
     * @return the string representation of the input value.
     */
    public static String convertToString(final byte input)
    {
        return "" + input;
    }

    /**
     * This method converts a value to its string representation.
     *
     * @param input is the value to stringify.
     * @return the string representation of the input value.
     */
    public static String convertToString(final short input)
    {
        return "" + input;
    }

    /**
     * This method converts a value to its string representation.
     *
     * @param input is the value to stringify.
     * @return the string representation of the input value.
     */
    public static String convertToString(final int input)
    {
        return "" + input;
    }

    /**
     * This method converts a value to its string representation.
     *
     * @param input is the value to stringify.
     * @return the string representation of the input value.
     */
    public static String convertToString(final long input)
    {
        return "" + input;
    }

    /**
     * This method converts a value to its string representation.
     *
     * @param input is the value to stringify.
     * @return the string representation of the input value.
     */
    public static String convertToString(final float input)
    {
        return "" + input;
    }

    /**
     * This method converts a value to its string representation.
     *
     * @param input is the value to stringify.
     * @return the string representation of the input value.
     */
    public static String convertToString(final double input)
    {
        return "" + input;
    }

    /**
     * This method converts a value to its string representation.
     *
     * @param input is the value to stringify.
     * @return the string representation of the input value.
     */
    public static String convertToString(final Object input)
    {
        return "" + input;
    }

    /**
     * This method converts a boolean value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToBoolean(boolean value)
    {
        return (boolean) value;
    }

    /**
     * This method converts a char value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToBoolean(char value)
    {
        return value != 0;
    }

    /**
     * This method converts a byte value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToBoolean(byte value)
    {
        return value != 0;
    }

    /**
     * This method converts a short value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToBoolean(short value)
    {
        return value != 0;
    }

    /**
     * This method converts a int value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToBoolean(int value)
    {
        return value != 0;
    }

    /**
     * This method converts a long value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToBoolean(long value)
    {
        return value != 0;
    }

    /**
     * This method converts a float value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToBoolean(float value)
    {
        return value != 0;
    }

    /**
     * This method converts a double value to a boolean value.
     *
     * @param value is the value to convert.
     */
    public static boolean convertToBoolean(double value)
    {
        return value != 0;
    }

    /**
     * This method converts a boolean value to a char value.
     *
     * @param value is the value to convert.
     */
    public static char convertToChar(boolean value)
    {
        return (char) (value ? 1 : 0);
    }

    /**
     * This method converts a char value to a char value.
     *
     * @param value is the value to convert.
     */
    public static char convertToChar(char value)
    {
        return (char) value;
    }

    /**
     * This method converts a byte value to a char value.
     *
     * @param value is the value to convert.
     */
    public static char convertToChar(byte value)
    {
        return (char) value;
    }

    /**
     * This method converts a short value to a char value.
     *
     * @param value is the value to convert.
     */
    public static char convertToChar(short value)
    {
        return (char) value;
    }

    /**
     * This method converts a int value to a char value.
     *
     * @param value is the value to convert.
     */
    public static char convertToChar(int value)
    {
        return (char) value;
    }

    /**
     * This method converts a long value to a char value.
     *
     * @param value is the value to convert.
     */
    public static char convertToChar(long value)
    {
        return (char) value;
    }

    /**
     * This method converts a float value to a char value.
     *
     * @param value is the value to convert.
     */
    public static char convertToChar(float value)
    {
        return (char) value;
    }

    /**
     * This method converts a double value to a char value.
     *
     * @param value is the value to convert.
     */
    public static char convertToChar(double value)
    {
        return (char) value;
    }

    /**
     * This method converts a boolean value to a byte value.
     *
     * @param value is the value to convert.
     */
    public static byte convertToByte(boolean value)
    {
        return (byte) (value ? 1 : 0);
    }

    /**
     * This method converts a char value to a byte value.
     *
     * @param value is the value to convert.
     */
    public static byte convertToByte(char value)
    {
        return (byte) value;
    }

    /**
     * This method converts a byte value to a byte value.
     *
     * @param value is the value to convert.
     */
    public static byte convertToByte(byte value)
    {
        return (byte) value;
    }

    /**
     * This method converts a short value to a byte value.
     *
     * @param value is the value to convert.
     */
    public static byte convertToByte(short value)
    {
        return (byte) value;
    }

    /**
     * This method converts a int value to a byte value.
     *
     * @param value is the value to convert.
     */
    public static byte convertToByte(int value)
    {
        return (byte) value;
    }

    /**
     * This method converts a long value to a byte value.
     *
     * @param value is the value to convert.
     */
    public static byte convertToByte(long value)
    {
        return (byte) value;
    }

    /**
     * This method converts a float value to a byte value.
     *
     * @param value is the value to convert.
     */
    public static byte convertToByte(float value)
    {
        return (byte) value;
    }

    /**
     * This method converts a double value to a byte value.
     *
     * @param value is the value to convert.
     */
    public static byte convertToByte(double value)
    {
        return (byte) value;
    }

    /**
     * This method converts a boolean value to a short value.
     *
     * @param value is the value to convert.
     */
    public static short convertToShort(boolean value)
    {
        return (short) (value ? 1 : 0);
    }

    /**
     * This method converts a char value to a short value.
     *
     * @param value is the value to convert.
     */
    public static short convertToShort(char value)
    {
        return (short) value;
    }

    /**
     * This method converts a byte value to a short value.
     *
     * @param value is the value to convert.
     */
    public static short convertToShort(byte value)
    {
        return (short) value;
    }

    /**
     * This method converts a short value to a short value.
     *
     * @param value is the value to convert.
     */
    public static short convertToShort(short value)
    {
        return (short) value;
    }

    /**
     * This method converts a int value to a short value.
     *
     * @param value is the value to convert.
     */
    public static short convertToShort(int value)
    {
        return (short) value;
    }

    /**
     * This method converts a long value to a short value.
     *
     * @param value is the value to convert.
     */
    public static short convertToShort(long value)
    {
        return (short) value;
    }

    /**
     * This method converts a float value to a short value.
     *
     * @param value is the value to convert.
     */
    public static short convertToShort(float value)
    {
        return (short) value;
    }

    /**
     * This method converts a double value to a short value.
     *
     * @param value is the value to convert.
     */
    public static short convertToShort(double value)
    {
        return (short) value;
    }

    /**
     * This method converts a boolean value to a int value.
     *
     * @param value is the value to convert.
     */
    public static int convertToInt(boolean value)
    {
        return (int) (value ? 1 : 0);
    }

    /**
     * This method converts a char value to a int value.
     *
     * @param value is the value to convert.
     */
    public static int convertToInt(char value)
    {
        return (int) value;
    }

    /**
     * This method converts a byte value to a int value.
     *
     * @param value is the value to convert.
     */
    public static int convertToInt(byte value)
    {
        return (int) value;
    }

    /**
     * This method converts a short value to a int value.
     *
     * @param value is the value to convert.
     */
    public static int convertToInt(short value)
    {
        return (int) value;
    }

    /**
     * This method converts a int value to a int value.
     *
     * @param value is the value to convert.
     */
    public static int convertToInt(int value)
    {
        return (int) value;
    }

    /**
     * This method converts a long value to a int value.
     *
     * @param value is the value to convert.
     */
    public static int convertToInt(long value)
    {
        return (int) value;
    }

    /**
     * This method converts a float value to a int value.
     *
     * @param value is the value to convert.
     */
    public static int convertToInt(float value)
    {
        return (int) value;
    }

    /**
     * This method converts a double value to a int value.
     *
     * @param value is the value to convert.
     */
    public static int convertToInt(double value)
    {
        return (int) value;
    }

    /**
     * This method converts a boolean value to a long value.
     *
     * @param value is the value to convert.
     */
    public static long convertToLong(boolean value)
    {
        return (long) (value ? 1 : 0);
    }

    /**
     * This method converts a char value to a long value.
     *
     * @param value is the value to convert.
     */
    public static long convertToLong(char value)
    {
        return (long) value;
    }

    /**
     * This method converts a byte value to a long value.
     *
     * @param value is the value to convert.
     */
    public static long convertToLong(byte value)
    {
        return (long) value;
    }

    /**
     * This method converts a short value to a long value.
     *
     * @param value is the value to convert.
     */
    public static long convertToLong(short value)
    {
        return (long) value;
    }

    /**
     * This method converts a int value to a long value.
     *
     * @param value is the value to convert.
     */
    public static long convertToLong(int value)
    {
        return (long) value;
    }

    /**
     * This method converts a long value to a long value.
     *
     * @param value is the value to convert.
     */
    public static long convertToLong(long value)
    {
        return (long) value;
    }

    /**
     * This method converts a float value to a long value.
     *
     * @param value is the value to convert.
     */
    public static long convertToLong(float value)
    {
        return (long) value;
    }

    /**
     * This method converts a double value to a long value.
     *
     * @param value is the value to convert.
     */
    public static long convertToLong(double value)
    {
        return (long) value;
    }

    /**
     * This method converts a boolean value to a float value.
     *
     * @param value is the value to convert.
     */
    public static float convertToFloat(boolean value)
    {
        return (float) (value ? 1 : 0);
    }

    /**
     * This method converts a char value to a float value.
     *
     * @param value is the value to convert.
     */
    public static float convertToFloat(char value)
    {
        return (float) value;
    }

    /**
     * This method converts a byte value to a float value.
     *
     * @param value is the value to convert.
     */
    public static float convertToFloat(byte value)
    {
        return (float) value;
    }

    /**
     * This method converts a short value to a float value.
     *
     * @param value is the value to convert.
     */
    public static float convertToFloat(short value)
    {
        return (float) value;
    }

    /**
     * This method converts a int value to a float value.
     *
     * @param value is the value to convert.
     */
    public static float convertToFloat(int value)
    {
        return (float) value;
    }

    /**
     * This method converts a long value to a float value.
     *
     * @param value is the value to convert.
     */
    public static float convertToFloat(long value)
    {
        return (float) value;
    }

    /**
     * This method converts a float value to a float value.
     *
     * @param value is the value to convert.
     */
    public static float convertToFloat(float value)
    {
        return (float) value;
    }

    /**
     * This method converts a double value to a float value.
     *
     * @param value is the value to convert.
     */
    public static float convertToFloat(double value)
    {
        return (float) value;
    }

    /**
     * This method converts a boolean value to a double value.
     *
     * @param value is the value to convert.
     */
    public static double convertToDouble(boolean value)
    {
        return (double) (value ? 1 : 0);
    }

    /**
     * This method converts a char value to a double value.
     *
     * @param value is the value to convert.
     */
    public static double convertToDouble(char value)
    {
        return (double) value;
    }

    /**
     * This method converts a byte value to a double value.
     *
     * @param value is the value to convert.
     */
    public static double convertToDouble(byte value)
    {
        return (double) value;
    }

    /**
     * This method converts a short value to a double value.
     *
     * @param value is the value to convert.
     */
    public static double convertToDouble(short value)
    {
        return (double) value;
    }

    /**
     * This method converts a int value to a double value.
     *
     * @param value is the value to convert.
     */
    public static double convertToDouble(int value)
    {
        return (double) value;
    }

    /**
     * This method converts a long value to a double value.
     *
     * @param value is the value to convert.
     */
    public static double convertToDouble(long value)
    {
        return (double) value;
    }

    /**
     * This method converts a float value to a double value.
     *
     * @param value is the value to convert.
     */
    public static double convertToDouble(float value)
    {
        return (double) value;
    }

    /**
     * This method converts a double value to a double value.
     *
     * @param value is the value to convert.
     */
    public static double convertToDouble(double value)
    {
        return (double) value;
    }
}

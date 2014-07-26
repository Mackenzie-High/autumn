package autumn.util;

import autumn.lang.Functor;
import autumn.lang.internals.ArgumentStack;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * This class provides commonly used static utility methods.
 *
 * <p>
 * This class has a single letter name, because it will be used very frequently.
 * As a result, the shorter name saves Autumn programmers some typing.
 * </p>
 *
 * @author Mackenzie High
 */
public final class F
{
    /**
     * Sole Constructor.
     */
    private F()
    {
        // Pass, because this is merely a static utility class.
    }

    public static Object apply(final Functor functor,
                               final Iterable<?> arguments)
            throws Throwable
    {
        final ArgumentStack stack = ArgumentStack.getThreadStack();

        // Clear the stack in order to prevent unexpected bugs.
        stack.clear();

        // Push the arguments onto the stack.
        for (Object argument : arguments)
        {
            stack.push(argument);
        }

        // Invoke the functor.
        functor.apply(stack);

        // Retrieve the return-value of the functor.
        final Object result = stack.popO();

        // Clear the stack in order to prevent unexpected bugs.
        // The stack should already be clear.
        stack.clear();

        return result;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                 Standard I/O
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static void printAll(final PrintStream out,
                                final Iterable value)
    {
        for (Object element : value)
        {
            out.println(element);
        }
    }

    public static void printAll(final Iterable value)
    {
        printAll(System.out, value);
    }

    public static void printIter(final Iterable value)
    {
    }

    public static void printIter(final Object[] value)
    {
        for (Object o : value)
        {
            System.out.println(o);
        }
    }

    public static void printIter(final Iterable value,
                                 final String separator)
    {
    }

    public static void printIter(final Iterable value,
                                 final String separator,
                                 final String prefix,
                                 final String postfix)
    {
    }

    public static void printIter(final StringBuilder out,
                                 final Iterable value)
    {
    }

    public static void printIter(final StringBuilder out,
                                 final Iterable value,
                                 final String separator)
    {
    }

    public static void printIter(final StringBuilder out,
                                 final Iterable value,
                                 final String separator,
                                 final String prefix,
                                 final String postfix)
    {
    }

    public static void printIter(final PrintStream out,
                                 final Iterable value)
    {
    }

    public static void printIter(final PrintStream out,
                                 final Iterable value,
                                 final String separator)
    {
    }

    public static void printIter(final PrintStream out,
                                 final Iterable value,
                                 final String separator,
                                 final String prefix,
                                 final String postfix)
    {
    }

    public static void print(final Object value)
    {
        System.out.print(value);
    }

    public static void println(final Object value)
    {
        System.out.println(value);
    }

    public static void println()
    {
        System.out.println();
    }

    public static void printerr(final Object value)
    {
        System.err.print(value);
    }

    public static void printerrln(final Object value)
    {
        System.err.println(value);
    }

    public static void printf(final String format,
                              final Iterable<Object> args)
    {
        Preconditions.checkNotNull(format);
        Preconditions.checkNotNull(args);

        System.out.printf(format, Lists.newLinkedList(args).toArray());
    }

    public static String readln()
    {
        return readln(System.in);
    }

    public static int readInt()
    {
        return Integer.parseInt(readln());
    }

    public static double readDouble()
    {
        return Double.parseDouble(readln());
    }

    public static String readln(final InputStream stream)
    {
        final Scanner scanner = new Scanner(stream);

        final String input = scanner.nextLine();

        return input;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                 Range
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static Iterator<Integer> range(final int start)
    {
        return range(start, Integer.MAX_VALUE, 1);
    }

    public static Iterator<Integer> range(final int start,
                                          final int end)
    {
        return range(start, end, 1);
    }

    public static Iterator<Integer> range(final int start,
                                          final int end,
                                          final int step)
    {
        // If start <= end, then the iterator must iterate upwards.
        // If start >= end, then the iterator must iterate downwards.
        // Otherwise, the iterator would potentially iterate infinitely, which is forbidden.
        Preconditions.checkArgument((start <= end) && (step > 0) || (step >= end) && (step < 0));

        return new Iterator<Integer>()
        {
            private int index = start;

            @Override
            public boolean hasNext()
            {
                return index < end;
            }

            @Override
            public Integer next()
            {
                return index++;
            }

            @Override
            public void remove()
            {
                throw new UnsupportedOperationException("Not Supported");
            }
        };
    }

    public static List<Integer> listRange(final int start,
                                          final int end)
    {
        return listRange(start, end, 1);
    }

    public static List<Integer> listRange(final int start,
                                          final int end,
                                          final int step)
    {
        final int size = ((end - start) / 2) + 10;

        final List<Integer> list = Lists.newArrayListWithExpectedSize(size);

        for (int i = start; i < end; i = i + step)
        {
            list.add(i);
        }

        return Collections.unmodifiableList(list);
    }

    /**
     * This method creates an iterator over a given array of booleans.
     *
     * <p>
     * The returned iterator will not support the remove() method.
     * </p>
     *
     * @param array is the array itself.
     * @return an iterator that can iterator over the given array.
     */
    public static Iterator iter(final boolean[] array)
    {
        return Iterators.forArray(array);
    }

    /**
     * This method creates an iterator over a given array of chars.
     *
     * <p>
     * The returned iterator will not support the remove() method.
     * </p>
     *
     * @param array is the array itself.
     * @return an iterator that can iterator over the given array.
     */
    public static Iterator iter(final char[] array)
    {
        return Iterators.forArray(array);
    }

    /**
     * This method creates an iterator over a given array of bytes.
     *
     * <p>
     * The returned iterator will not support the remove() method.
     * </p>
     *
     * @param array is the array itself.
     * @return an iterator that can iterator over the given array.
     */
    public static Iterator iter(final byte[] array)
    {
        return Iterators.forArray(array);
    }

    /**
     * This method creates an iterator over a given array of shorts.
     *
     * <p>
     * The returned iterator will not support the remove() method.
     * </p>
     *
     * @param array is the array itself.
     * @return an iterator that can iterator over the given array.
     */
    public static Iterator iter(final short[] array)
    {
        return Iterators.forArray(array);
    }

    /**
     * This method creates an iterator over a given array of ints.
     *
     * <p>
     * The returned iterator will not support the remove() method.
     * </p>
     *
     * @param array is the array itself.
     * @return an iterator that can iterator over the given array.
     */
    public static Iterator iter(final int[] array)
    {
        return Iterators.forArray(array);
    }

    /**
     * This method creates an iterator over a given array of longs.
     *
     * <p>
     * The returned iterator will not support the remove() method.
     * </p>
     *
     * @param array is the array itself.
     * @return an iterator that can iterator over the given array.
     */
    public static Iterator iter(final long[] array)
    {
        return Iterators.forArray(array);
    }

    /**
     * This method creates an iterator over a given array of floats.
     *
     * <p>
     * The returned iterator will not support the remove() method.
     * </p>
     *
     * @param array is the array itself.
     * @return an iterator that can iterator over the given array.
     */
    public static Iterator iter(final float[] array)
    {
        return Iterators.forArray(array);
    }

    /**
     * This method creates an iterator over a given array of doubles.
     *
     * <p>
     * The returned iterator will not support the remove() method.
     * </p>
     *
     * @param array is the array itself.
     * @return an iterator that can iterator over the given array.
     */
    public static Iterator iter(final double[] array)
    {
        return Iterators.forArray(array);
    }

    /**
     * This method creates an iterator over a given array of objects.
     *
     * <p>
     * The returned iterator will not support the remove() method.
     * </p>
     *
     * @param array is the array itself.
     * @return an iterator that can iterator over the given array.
     */
    public static Iterator iter(final Object[] array)
    {
        return Iterators.forArray(array);
    }

    /**
     * This method creates an iterator over an iterable.
     *
     * <p>
     * This method is equivalent to calling the iterable's iterator() method.
     * </p>
     *
     * @param iterable is the iterable itself.
     * @return an iterator that can iterate over the given iterable.
     */
    public static Iterator iter(final Iterable iterable)
    {
        return iterable.iterator();
    }

    /**
     * This method creates an iterator over a map.
     *
     * <p>
     * This method is equivalent to calling the iterator() method on the map's entry-set.
     * </p>
     *
     * @param map is the map itself.
     * @return an iterator that can iterate over the given map's entries.
     */
    public static Iterator iter(final Map map)
    {
        return map.entrySet().iterator();
    }

    /**
     * This method creates an iterator over a given string.
     *
     * <p>
     * The returned iterator will not support the remove() method.
     * </p>
     *
     * @param string is the string itself.
     * @return an iterator that can iterator over the given string.
     */
    public static Iterator iter(final String string)
    {
        return new Iterator<Character>()
        {
            private int position = -1;

            @Override
            public boolean hasNext()
            {
                return position != string.length() - 1;
            }

            @Override
            public Character next()
            {
                return string.charAt(++position);
            }

            @Override
            public void remove()
            {
                throw new UnsupportedOperationException("Not Supported");
            }
        };
    }

    /**
     * This method creates an iterator over a given enumeration.
     *
     * <p>
     * The returned iterator will not support the remove() method.
     * </p>
     *
     * @param enumeration is the enumeration itself.
     * @return an iterator that can iterator over the given enumeration.
     */
    public static Iterator iter(final Enumeration enumeration)
    {
        return Iterators.forEnumeration(enumeration);
    }

    /**
     * This method creates an iterator over a given enum.
     *
     * <p>
     * The returned iterator will not support the remove() method.
     * </p>
     *
     * @param enumeration is the enum itself.
     * @return an iterator that can iterator over the given enum.
     */
    public static Iterator<Enum> iter(final Enum enumeration)
    {
        final Enum[] constants = (Enum[]) enumeration.getDeclaringClass().getEnumConstants();

        return (Iterator<Enum>) Iterators.forArray(constants);
    }

    /**
     * This method creates an iterator over a given function object.
     *
     * <p>
     * Equivalence: iter(next, null, null).
     * </p>
     *
     * <p>
     * The returned iterator will not support the remove() method.
     * </p>
     *
     * @param next is the function object that is invoked by the iterator's next() method.
     * @return an iterator that simply invokes the given function object.
     */
    public static Iterator iter(final Functor next)
    {
        return iter(next, null, null);
    }

    /**
     * This method creates an iterator over a pair of function objects.
     *
     * <p>
     * Equivalence: iter(next, has, null).
     * </p>
     *
     * <p>
     * The returned iterator will not support the remove() method.
     * </p>
     *
     * @param next is the function object that is invoked by the iterator's next() method.
     * @param has is the function object that is invoked by the iterator's hasNext() method.
     * @return an iterator based on the given function objects.
     */
    public static Iterator iter(final Functor next,
                                final Functor has)
    {
        return iter(next, has);
    }

    /**
     * This method creates an iterator over a pair of function objects.
     *
     * @param next is the function object that is invoked by the iterator's next() method.
     * @param has is the function object that is invoked by the iterator's hasNext() method.
     * @param remove is the function object that is invoked by the iterator's remove() method.
     * @return an iterator based on the given function objects.
     */
    public static Iterator iter(final Functor next,
                                final Functor has,
                                final Functor remove)
    {
        return null; // TODO
    }

    /**
     * This method returns the length of an array.
     *
     * @param array is the array itself.
     * @return the length of the array.
     */
    public static int len(final boolean[] array)
    {
        return array.length;
    }

    /**
     * This method returns the length of an array.
     *
     * @param array is the array itself.
     * @return the length of the array.
     */
    public static int len(final char[] array)
    {
        return array.length;
    }

    /**
     * This method returns the length of an array.
     *
     * @param array is the array itself.
     * @return the length of the array.
     */
    public static int len(final byte[] array)
    {
        return array.length;
    }

    /**
     * This method returns the length of an array.
     *
     * @param array is the array itself.
     * @return the length of the array.
     */
    public static int len(final short[] array)
    {
        return array.length;
    }

    /**
     * This method returns the length of an array.
     *
     * @param array is the array itself.
     * @return the length of the array.
     */
    public static int len(final int[] array)
    {
        return array.length;
    }

    /**
     * This method returns the length of an array.
     *
     * @param array is the array itself.
     * @return the length of the array.
     */
    public static int len(final long[] array)
    {
        return array.length;
    }

    /**
     * This method returns the length of an array.
     *
     * @param array is the array itself.
     * @return the length of the array.
     */
    public static int len(final float[] array)
    {
        return array.length;
    }

    /**
     * This method returns the length of an array.
     *
     * @param array is the array itself.
     * @return the length of the array.
     */
    public static int len(final double[] array)
    {
        return array.length;
    }

    /**
     * This method returns the length of an array.
     *
     * @param array is the array itself.
     * @return the length of the array.
     */
    public static int len(final Object[] array)
    {
        return array.length;
    }

    /**
     * This method returns the length of a collection.
     *
     * @param collection is the collection itself.
     * @return the length of the collection.
     */
    public static int len(final Collection collection)
    {
        return collection.size();
    }

    /**
     * This method returns the size of a map.
     *
     * @param map is the map itself.
     * @return the size of the map.
     */
    public static int len(final Map map)
    {
        return map.size();
    }

    /**
     * This method returns the length of an string.
     *
     * @param string is the string itself.
     * @return the length of the string.
     */
    public static int len(final String string)
    {
        return string.length();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                 Get
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static boolean get(final boolean[] array,
                              final int index)
    {
        return array[index];
    }

    public static char get(final char[] array,
                           final int index)
    {
        return array[index];
    }

    public static byte get(final byte[] array,
                           final int index)
    {
        return array[index];
    }

    public static short get(final short[] array,
                            final int index)
    {
        return array[index];
    }

    public static int get(final int[] array,
                          final int index)
    {
        return array[index];
    }

    public static long get(final long[] array,
                           final int index)
    {
        return array[index];
    }

    public static float get(final float[] array,
                            final int index)
    {
        return array[index];
    }

    public static double get(final double[] array,
                             final int index)
    {
        return array[index];
    }

    public static String get(final String[] array,
                             final int index)
    {
        return array[index];
    }

    public static Object get(final Object[] array,
                             final int index)
    {
        return array[index];
    }

    public static char get(final String string,
                           final int index)
    {
        return string.charAt(index);
    }

    public static Object get(final List list,
                             final int index)
    {
        return list.get(index);
    }

    public static Object get(final Map map,
                             final Object key)
    {
        return map.get(key);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                 Set
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static boolean[] set(final boolean[] array,
                                final int index,
                                final boolean value)
    {
        array[index] = value;
        return array;
    }

    public static char[] set(final char[] array,
                             final int index,
                             final char value)
    {
        array[index] = value;
        return array;
    }

    public static byte[] set(final byte[] array,
                             final int index,
                             final byte value)
    {
        array[index] = value;
        return array;
    }

    public static short[] set(final short[] array,
                              final int index,
                              final short value)
    {
        array[index] = value;
        return array;
    }

    public static int[] set(final int[] array,
                            final int index,
                            final int value)
    {
        array[index] = value;
        return array;
    }

    public static long[] set(final long[] array,
                             final int index,
                             final long value)
    {
        array[index] = value;
        return array;
    }

    public static float[] set(final float[] array,
                              final int index,
                              final float value)
    {
        array[index] = value;
        return array;
    }

    public static double[] set(final double[] array,
                               final int index,
                               final double value)
    {
        array[index] = value;
        return array;
    }

    public static String[] set(final String[] array,
                               final int index,
                               final String value)
    {
        array[index] = value;
        return array;
    }

    public static Object[] set(final Object[] array,
                               final int index,
                               final Object value)
    {
        array[index] = value;
        return array;
    }

    public static String set(final String string,
                             final int index,
                             final char value)
    {
        final char[] chars = string.toCharArray();

        chars[index] = value;

        return new String(chars);
    }

    public static List set(final List list,
                           final int index,
                           final Object value)
    {
        list.set(index, value);
        return list;
    }

    public static Map set(final Map map,
                          final Object key,
                          final Object value)
    {
        map.put(key, value);
        return map;
    }

    /**
     * This method retrieves the first element of a list.
     *
     * @param list is the list that contains the element to retrieve.
     * @return the first element in the list.
     */
    public static <T> T first(final List<T> list)
    {
        return list.get(0);
    }

    /**
     * This method retrieves the last element of a list.
     *
     * @param list is the list that contains the element to retrieve.
     * @return the last element in the list.
     */
    public static <T> T last(final List<T> list)
    {
        return list.get(list.size() - 1);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                 Hash
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static int hash(final byte value)
    {
        return 0;
    }

    public static int hash(final short value)
    {
        return 0;
    }

    /**
     * This method creates a new BigInteger from a long value.
     *
     * @param value is the value of the new BigInteger.
     * @return the aforedescribed BigInteger.
     */
    public static BigInteger big(final long value)
    {
        return BigInteger.valueOf(value);
    }

    /**
     * This method creates a new BigDecimal from a double value.
     *
     * @param value is the value of the new BigDecimal.
     * @return the aforedescribed BigDecimal.
     */
    public static BigDecimal big(final double value)
    {
        return BigDecimal.valueOf(value);
    }
}

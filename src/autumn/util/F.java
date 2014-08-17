package autumn.util;

import autumn.lang.Functor;
import autumn.lang.internals.ArgumentStack;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.AbstractList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
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
    private static BigInteger unique = BigInteger.ZERO;

    /**
     * Sole Constructor.
     */
    private F()
    {
        // Pass, because this is merely a static utility class.
    }

    /**
     * This method applies a functor to a list of arguments.
     *
     * @param functor is the functor itself.
     * @param arguments are the arguments to apply the functor to.
     * @return the result of applying the functor to the arguments.
     * @throws NullPointerException if functor is null.
     * @throws NullPointerException if arguments is null.
     * @throws ClassCastException if any of the arguments cannot be accepted due to their type.
     * @throws Throwable in order to propagate any exceptions thrown by the functor.
     */
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
        final Object result = stack.isEmpty() ? null : stack.popO();

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

    /**
     * This method prints a value to standard-output.
     *
     * @param is the value to print.
     */
    public static void print(final Object value)
    {
        System.out.print(value);
    }

    /**
     * This method prints a value followed by a newline to standard-output.
     *
     * @param is the value to print.
     */
    public static void println(final Object value)
    {
        System.out.println(value);
    }

    /**
     * This method prints a newline to standard-output.
     */
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

    /**
     * This method reads a line of text from standard-input.
     *
     * @return the line of text.
     */
    public static String readln()
    {
        final Scanner scanner = new Scanner(System.in);

        final String input = scanner.nextLine();

        return input;
    }

    /**
     * This method reads an integer from standard-input.
     *
     * <p>
     * An entire line of text is read and then converted to an integer.
     * </p>
     *
     * @return the integer that was read.
     * @throws NumberFormatException if the line of text cannot be converted to an integer.
     */
    public static int readInt()
    {
        return Integer.parseInt(readln());
    }

    /**
     * This method reads a double from standard-input.
     *
     * <p>
     * An entire line of text is read and then converted to a double.
     * </p>
     *
     * @return the double that was read.
     * @throws NumberFormatException if the line of text cannot be converted to a double.
     */
    public static double readDouble()
    {
        return Double.parseDouble(readln());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                 Range
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static List<Integer> range(final int start)
    {
        return range(start, Integer.MAX_VALUE, 1);
    }

    public static List<Integer> range(final int start,
                                      final int end)
    {
        return range(start, end, 1);
    }

    /**
     * This method creates a list of the integers in a discrete range.
     *
     * @param start is the inclusive lowest value in the range.
     * @param end is the inclusive highest value in the range.
     * @param step is the distance that elements in the range are separated for one another.
     * @return a lazy list of integers.
     * @throws IllegalArgumentException if end is less-than start.
     */
    public static List<Integer> range(final int start,
                                      final int end,
                                      final int step)
    {
        // TODO: test

        Preconditions.checkArgument(start >= end);

        final int size = (start - end) / step;

        return new AbstractList<Integer>()
        {
            @Override
            public Integer get(int index)
            {
                return start + index * step;
            }

            @Override
            public int size()
            {
                return size;
            }
        };
    }

    /**
     * This method creates an iterator over a given array of booleans.
     *
     * <p>
     * The returned iterator will not support the remove() method.
     * </p>
     *
     * @param array is the array itself.
     * @return an iterable whose iterator can iterate over the given array.
     */
    public static Iterable<Boolean> iter(final boolean[] array)
    {
        return new Iterable()
        {
            @Override
            public Iterator iterator()
            {
                return Iterators.forArray(array);
            }
        };
    }

    /**
     * This method creates an iterator over a given array of chars.
     *
     * <p>
     * The returned iterator will not support the remove() method.
     * </p>
     *
     * @param array is the array itself.
     * @return an iterable whose iterator can iterate over the given array.
     */
    public static Iterable<Character> iter(final char[] array)
    {
        return new Iterable()
        {
            @Override
            public Iterator iterator()
            {
                return Iterators.forArray(array);
            }
        };
    }

    /**
     * This method creates an iterator over a given array of bytes.
     *
     * <p>
     * The returned iterator will not support the remove() method.
     * </p>
     *
     * @param array is the array itself.
     * @return an iterable whose iterator can iterate over the given array.
     */
    public static Iterable<Byte> iter(final byte[] array)
    {
        return new Iterable()
        {
            @Override
            public Iterator iterator()
            {
                return Iterators.forArray(array);
            }
        };
    }

    /**
     * This method creates an iterator over a given array of shorts.
     *
     * <p>
     * The returned iterator will not support the remove() method.
     * </p>
     *
     * @param array is the array itself.
     * @return an iterable whose iterator can iterate over the given array.
     */
    public static Iterable<Short> iter(final short[] array)
    {
        return new Iterable()
        {
            @Override
            public Iterator iterator()
            {
                return Iterators.forArray(array);
            }
        };
    }

    /**
     * This method creates an iterator over a given array of ints.
     *
     * <p>
     * The returned iterator will not support the remove() method.
     * </p>
     *
     * @param array is the array itself.
     * @return an iterable whose iterator can iterate over the given array.
     */
    public static Iterable<Integer> iter(final int[] array)
    {
        return new Iterable()
        {
            @Override
            public Iterator iterator()
            {
                return Iterators.forArray(array);
            }
        };
    }

    /**
     * This method creates an iterator over a given array of longs.
     *
     * <p>
     * The returned iterator will not support the remove() method.
     * </p>
     *
     * @param array is the array itself.
     * @return an iterable whose iterator can iterate over the given array.
     */
    public static Iterable<Long> iter(final long[] array)
    {
        return new Iterable()
        {
            @Override
            public Iterator iterator()
            {
                return Iterators.forArray(array);
            }
        };
    }

    /**
     * This method creates an iterator over a given array of floats.
     *
     * <p>
     * The returned iterator will not support the remove() method.
     * </p>
     *
     * @param array is the array itself.
     * @return an iterable whose iterator can iterate over the given array.
     */
    public static Iterable<Float> iter(final float[] array)
    {
        return new Iterable()
        {
            @Override
            public Iterator iterator()
            {
                return Iterators.forArray(array);
            }
        };
    }

    /**
     * This method creates an iterator over a given array of doubles.
     *
     * <p>
     * The returned iterator will not support the remove() method.
     * </p>
     *
     * @param array is the array itself.
     * @return an iterable whose iterator can iterate over the given array.
     */
    public static Iterable<Double> iter(final double[] array)
    {
        return new Iterable()
        {
            @Override
            public Iterator iterator()
            {
                return Iterators.forArray(array);
            }
        };
    }

    /**
     * This method creates an iterator over a given array of objects.
     *
     * <p>
     * The returned iterator will not support the remove() method.
     * </p>
     *
     * @param array is the array itself.
     * @return an iterable whose iterator can iterate over the given array.
     */
    public static <T> Iterable<T> iter(final T[] array)
    {
        return new Iterable()
        {
            @Override
            public Iterator iterator()
            {
                return Iterators.forArray(array);
            }
        };
    }

    /**
     * This method creates an iterator over an iterable.
     *
     * @param iterable is the iterable itself.
     * @return iterable.
     */
    public static <T> Iterable<T> iter(final Iterable<T> iterable)
    {
        return iterable;
    }

    /**
     * This method creates an iterator over a map.
     *
     * <p>
     * This method is equivalent to map.entrySet().
     * </p>
     *
     * @param map is the map itself.
     * @return an iterable whose iterator can iterate over the given map's entries.
     */
    public static <K, V> Iterable<Entry<K, V>> iter(final Map<K, V> map)
    {
        return map.entrySet();
    }

    /**
     * This method creates an iterator over a given string.
     *
     * <p>
     * The returned iterator will not support the remove() method.
     * </p>
     *
     * @param string is the string itself.
     * @return an iterable whose iterator can iterate over the given string.
     */
    public static Iterable<Character> iter(final CharSequence string)
    {
        return new Iterable()
        {
            @Override
            public Iterator iterator()
            {
                return new Iterator()
                {
                    int index = -1;

                    @Override
                    public boolean hasNext()
                    {
                        return index + 1 < string.length();
                    }

                    @Override
                    public Object next()
                    {
                        if (hasNext())
                        {
                            return string.charAt(++index);
                        }
                        else
                        {
                            throw new NoSuchElementException();
                        }
                    }

                    @Override
                    public void remove()
                    {
                        throw new UnsupportedOperationException("Not Supported");
                    }
                };
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
     * @return an iterable whose iterator can iterate over the given enumeration.
     */
    public static <T> Iterable<T> iter(final Enumeration<T> enumeration)
    {
        return new Iterable()
        {
            @Override
            public Iterator iterator()
            {
                return Iterators.forEnumeration(enumeration);
            }
        };
    }

    /**
     * This method creates an iterator over a given enum.
     *
     * <p>
     * The returned iterator will not support the remove() method.
     * </p>
     *
     * @param enumeration is the enum itself.
     * @return an iterable whose iterator can iterate over the given enum.
     */
    public static Iterable<Enum> iter(final Enum enumeration)
    {
        final Enum[] constants = (Enum[]) enumeration.getDeclaringClass().getEnumConstants();

        return new Iterable()
        {
            @Override
            public Iterator iterator()
            {
                return Iterators.forArray(constants);
            }
        };
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
     * @return an iterable whose iterator simply invokes the given function object.
     */
    public static Iterable iter(final Functor next)
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
     * @return an iterable whose iterator is based on the given function objects.
     */
    public static Iterable iter(final Functor next,
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
     * @return an iterable whose iterator is based on the given function objects.
     */
    public static Iterable iter(final Functor next,
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

    /**
     * This method gets an element from an array.
     *
     * @param array is the array that contains the element.
     * @param index is the index of the element.
     * @return the element at the specified index in the array.
     * @throws NullPointerException if the array is null.
     * @throws IndexOutOfBoundsException if the index is out-of-bounds.
     */
    public static boolean get(final boolean[] array,
                              final int index)
    {
        return array[index];
    }

    /**
     * This method gets an element from an array.
     *
     * @param array is the array that contains the element.
     * @param index is the index of the element.
     * @return the element at the specified index in the array.
     * @throws NullPointerException if the array is null.
     * @throws IndexOutOfBoundsException if the index is out-of-bounds.
     */
    public static char get(final char[] array,
                           final int index)
    {
        return array[index];
    }

    /**
     * This method gets an element from an array.
     *
     * @param array is the array that contains the element.
     * @param index is the index of the element.
     * @return the element at the specified index in the array.
     * @throws NullPointerException if the array is null.
     * @throws IndexOutOfBoundsException if the index is out-of-bounds.
     */
    public static byte get(final byte[] array,
                           final int index)
    {
        return array[index];
    }

    /**
     * This method gets an element from an array.
     *
     * @param array is the array that contains the element.
     * @param index is the index of the element.
     * @return the element at the specified index in the array.
     * @throws NullPointerException if the array is null.
     * @throws IndexOutOfBoundsException if the index is out-of-bounds.
     */
    public static short get(final short[] array,
                            final int index)
    {
        return array[index];
    }

    /**
     * This method gets an element from an array.
     *
     * @param array is the array that contains the element.
     * @param index is the index of the element.
     * @return the element at the specified index in the array.
     * @throws NullPointerException if the array is null.
     * @throws IndexOutOfBoundsException if the index is out-of-bounds.
     */
    public static int get(final int[] array,
                          final int index)
    {
        return array[index];
    }

    /**
     * This method gets an element from an array.
     *
     * @param array is the array that contains the element.
     * @param index is the index of the element.
     * @return the element at the specified index in the array.
     * @throws NullPointerException if the array is null.
     * @throws IndexOutOfBoundsException if the index is out-of-bounds.
     */
    public static long get(final long[] array,
                           final int index)
    {
        return array[index];
    }

    /**
     * This method gets an element from an array.
     *
     * @param array is the array that contains the element.
     * @param index is the index of the element.
     * @return the element at the specified index in the array.
     * @throws NullPointerException if the array is null.
     * @throws IndexOutOfBoundsException if the index is out-of-bounds.
     */
    public static float get(final float[] array,
                            final int index)
    {
        return array[index];
    }

    /**
     * This method gets an element from an array.
     *
     * @param array is the array that contains the element.
     * @param index is the index of the element.
     * @return the element at the specified index in the array.
     * @throws NullPointerException if the array is null.
     * @throws IndexOutOfBoundsException if the index is out-of-bounds.
     */
    public static double get(final double[] array,
                             final int index)
    {
        return array[index];
    }

    /**
     * This method gets an element from an array.
     *
     * @param array is the array that contains the element.
     * @param index is the index of the element.
     * @return the element at the specified index in the array.
     * @throws NullPointerException if the array is null.
     * @throws IndexOutOfBoundsException if the index is out-of-bounds.
     */
    public static String get(final String[] array,
                             final int index)
    {
        return array[index];
    }

    /**
     * This method gets an element from an array.
     *
     * @param array is the array that contains the element.
     * @param index is the index of the element.
     * @return the element at the specified index in the array.
     * @throws NullPointerException if the array is null.
     * @throws IndexOutOfBoundsException if the index is out-of-bounds.
     */
    public static Object get(final Object[] array,
                             final int index)
    {
        return array[index];
    }

    /**
     * This method gets a character from a string.
     *
     * @param string is the sequence of characters that contains the character.
     * @param index is the index of the character.
     * @return the character at the specified index in the string.
     * @throws NullPointerException if the string is null.
     * @throws IndexOutOfBoundsException if the index is out-of-bounds.
     */
    public static char get(final CharSequence string,
                           final int index)
    {
        return string.charAt(index);
    }

    /**
     * This method gets an element from a list.
     *
     * @param list is the list that contains the element.
     * @param index is the index of the element.
     * @return the element at the specified index in the list.
     * @throws NullPointerException if the list is null.
     * @throws IndexOutOfBoundsException if the index is out-of-bounds.
     */
    public static Object get(final List list,
                             final int index)
    {
        return list.get(index);
    }

    /**
     * This method gets an element from an iterable.
     *
     * @param iterable is the iterable that contains the element.
     * @param index is the index of the element.
     * @return the element at the specified index in the iterable.
     * @throws NullPointerException if the iterable is null.
     * @throws IndexOutOfBoundsException if the index is out-of-bounds.
     */
    public static Object get(final Iterable iterable,
                             final int index)
    {
        // If the iterable is really a list, use the potentially faster algorithm.

        if (iterable instanceof List)
        {
            return ((List) iterable).get(index);
        }
        else
        {
            int i = 0;

            for (Object element : iterable)
            {
                if (i++ == index)
                {
                    return element;
                }
            }

            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + i);
        }
    }

    /**
     * This method gets the value of an entry in a map.
     *
     * @param map is the map that contains the entry.
     * @param key is the key that identifies the entry.
     * @return the value in the map entry; or null, if no such entry exists.
     * @throws NullPointerException if the map is null.
     */
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

    /**
     * This method returns the default value of a given type.
     *
     * <p>
     * The boolean type returns false.
     * Numeric types return zero.
     * Reference types return null.
     * </p>
     *
     * @param type is the type that indicates the value to return.
     * @return
     * @throws NullPointerException if the type is null.
     * @throws IllegalArgumentException if the type is the void-type.
     */
    public static Object defaultValue(final Class type)
    {
        Preconditions.checkNotNull(type);
        Preconditions.checkArgument(type != void.class,
                                    "The void-type does not have a default value.");

        if (type == boolean.class)
        {
            return false;
        }
        else if (type == char.class)
        {
            return (char) 0;
        }
        else if (type == byte.class)
        {
            return (byte) 0;
        }
        else if (type == short.class)
        {
            return (short) 0;
        }
        else if (type == int.class)
        {
            return (int) 0;
        }
        else if (type == long.class)
        {
            return (long) 0;
        }
        else if (type == float.class)
        {
            return (float) 0;
        }
        else if (type == double.class)
        {
            return (double) 0;
        }
        else // reference types
        {
            return null;
        }
    }

    /**
     * This method never returns the same BigInteger twice.
     *
     * @return a new unique positive integer.
     */
    public static synchronized BigInteger unique()
    {
        final BigInteger result = unique;

        unique = unique.add(BigInteger.ONE);

        return result;
    }
}

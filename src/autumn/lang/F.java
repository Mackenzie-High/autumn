package autumn.lang;

import autumn.lang.Functor;
import autumn.lang.LocalsMap;
import autumn.lang.exceptions.CheckedException;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import high.mackenzie.autumn.lang.Slice;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * This class provides commonly used static utility methods.
 *
 * @author Mackenzie High
 */
public final class F
{
    /**
     * This method creates an immutable copy of a given mutable set data-structure.
     *
     * @param <T> is the type of the elements in the data-structure.
     * @param mutable is the mutable data-structure.
     * @return an immutable copy of the mutable data-structure.
     */
    public static <T> Set<T> immutable(final Set<? extends T> mutable)
    {
        Preconditions.checkNotNull(mutable);

        return Collections.unmodifiableSet(new HashSet<T>(mutable));
    }

    /**
     * This method creates an immutable copy of a given mutable set data-structure.
     *
     * @param <T> is the type of the elements in the data-structure.
     * @param mutable is the mutable data-structure.
     * @return an immutable copy of the mutable data-structure.
     */
    public static <T> Set<T> immutable(final NavigableSet<? extends T> mutable)
    {
        Preconditions.checkNotNull(mutable);

        return Collections.unmodifiableSet(new TreeSet<T>(mutable));
    }

    /**
     * This method creates an immutable copy of a given mutable list data-structure.
     *
     * @param <T> is the type of the elements in the data-structure.
     * @param mutable is the mutable data-structure.
     * @return an immutable copy of the mutable data-structure.
     */
    public static <T> List<T> immutable(final List<? extends T> mutable)
    {
        Preconditions.checkNotNull(mutable);

        return Collections.unmodifiableList(new ArrayList<T>(mutable));
    }

    /**
     * This method creates an immutable copy of a given mutable collection data-structure.
     *
     * @param <T> is the type of the elements in the data-structure.
     * @param mutable is the mutable data-structure.
     * @return an immutable copy of the mutable data-structure.
     */
    public static <T> Collection<T> immutable(final Collection<? extends T> mutable)
    {
        Preconditions.checkNotNull(mutable);

        return Collections.unmodifiableCollection(new ArrayList<T>(mutable));
    }

    /**
     * This method creates an immutable copy of a given mutable map data-structure.
     *
     * @param <K> is the type of the keys in the map.
     * @param <V> is the type of the values in the map.
     * @param mutable is the mutable data-structure.
     * @return an immutable copy of the mutable data-structure.
     */
    public static <K, V> Map<K, V> immutable(final Map<? extends K, ? extends V> mutable)
    {
        Preconditions.checkNotNull(mutable);

        return Collections.unmodifiableMap(new HashMap<K, V>(mutable));
    }

    /**
     * This method creates an immutable copy of a given mutable map data-structure.
     *
     * @param <K> is the type of the keys in the map.
     * @param <V> is the type of the values in the map.
     * @param mutable is the mutable data-structure.
     * @return an immutable copy of the mutable data-structure.
     */
    public static <K, V> Map<K, V> immutable(final NavigableMap<? extends K, ? extends V> mutable)
    {
        Preconditions.checkNotNull(mutable);

        return Collections.unmodifiableMap(new TreeMap<K, V>(mutable));
    }

    public static List asList(final boolean[] array)
    {
        return Arrays.asList(array);
    }

    public static List asList(final char[] array)
    {
        return Arrays.asList(array);
    }

    public static List asList(final byte[] array)
    {
        return Arrays.asList(array);
    }

    public static List asList(final short[] array)
    {
        return Arrays.asList(array);
    }

    public static List asList(final int[] array)
    {
        return Arrays.asList(array);
    }

    public static List asList(final long[] array)
    {
        return Arrays.asList(array);
    }

    public static List asList(final float[] array)
    {
        return Arrays.asList(array);
    }

    public static List asList(final double[] array)
    {
        return Arrays.asList(array);
    }

    public static List asList(final Object[] array)
    {
        return Arrays.asList(array);
    }

    public static Object safeInvoke(final Functor function,
                                    Object... arguments)
    {
        Preconditions.checkNotNull(function);

        try
        {
            return function.apply(arguments);
        }
        catch (Throwable t)
        {
            throw new CheckedException(t);
        }
    }

    public static Object safeInvoke(final Functor function,
                                    List arguments)
    {
        Preconditions.checkNotNull(function);

        try
        {
            return function.apply(arguments);
        }
        catch (Throwable t)
        {
            throw new CheckedException(t);
        }
    }

    public static Object newArray(final Class element,
                                  final int size)
    {
        return Array.newInstance(element, size);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                 Assert
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static void require(final boolean condition)
    {
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
    //                                 Comprehension
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static Collection<Object> comprehend(final Collection<Object> data_structure,
                                                final Functor expression,
                                                final Iterable<Object> iterable)
    {
//        return comprehend(data_structure,
//                          expression,
//                          iterable,
//                          CallableUtils.constant(true),
//                          CallableUtils.constant(true));
        return null;
    }

    public static Collection<Object> comprehend(final Collection<Object> data_structure,
                                                final Functor expression,
                                                final Iterable<Object> iterable,
                                                final Functor selection,
                                                final Functor termination)
    {
//        for (Object element : iterable)
//        {
//            if ((boolean) (Boolean) selection.funcall(element))
//            {
//                data_structure.add(expression.funcall(element));
//            }
//
//            if (!(boolean) (Boolean) termination.funcall(element))
//            {
//                break;
//            }
//        }

        return data_structure;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                 Generator
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
//    public static Generator<Object, Object> generate(final Functor expression,
//                                                     final Iterable<Object> iterable)
//    {
//        return null;
//    }
//
//    public static Generator<Object, Object> generate(final Functor expression,
//                                                     final Iterable<Object> iterable,
//                                                     final Functor termination)
//    {
//        return null;
//    }
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
        return new Iterator()
        {
            @Override
            public boolean hasNext()
            {
                if (has == null)
                {
                    return true;
                }
                else
                {
                    return (Boolean) safeInvoke(has);
                }
            }

            @Override
            public Object next()
            {
                return safeInvoke(next);
            }

            @Override
            public void remove()
            {
                if (remove == null)
                {
                    throw new UnsupportedOperationException("Not Supported");
                }
                else
                {
                    safeInvoke(remove);
                }
            }
        };
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                 Len
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static int len(final boolean[] array)
    {
        return array.length;
    }

    public static int len(final char[] array)
    {
        return array.length;
    }

    public static int len(final byte[] array)
    {
        return array.length;
    }

    public static int len(final short[] array)
    {
        return array.length;
    }

    public static int len(final int[] array)
    {
        return array.length;
    }

    public static int len(final long[] array)
    {
        return array.length;
    }

    public static int len(final float[] array)
    {
        return array.length;
    }

    public static int len(final double[] array)
    {
        return array.length;
    }

    public static int len(final Object[] array)
    {
        return array.length;
    }

    public static int len(final Collection collection)
    {
        return collection.size();
    }

    public static int len(final Map map)
    {
        return map.size();
    }

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

    public static Object get(final List<Object> list,
                             final int index)
    {
        return list.get(index);
    }

    public static Object get(final Map<Object, Object> map,
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

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                 Slice
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public List slice(final List list,
                      final int start,
                      final int end,
                      final int step)
    {
        return new Slice(list, start, end, step);
    }

    public List slice(final List list,
                      final int start,
                      final int end)
    {
        return slice(list, start, end, 1);
    }

    public String slice(final String string,
                        final int start,
                        final int end,
                        final int step)
    {
        return string.substring(start, end);
    }

    public NavigableMap slice(final NavigableMap map,
                              final Object start,
                              final Object end)
    {
        return map.subMap(start, true, end, false);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                 Left
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static Object left(final List list)
    {
        return null;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                 Right
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static Object right(final List list)
    {
        return null;
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

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                 Map
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static List map(final Iterable iterable,
                           final Functor function)
    {
        return null;
    }

    public static List map(final Iterable iterable,
                           final Functor function,
                           final Object argument)
    {
        return null;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                 Filter
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                 Reduce
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                 Max
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static Comparable max(final Iterable<? extends Comparable> iterable)
    {
        return null;
    }

    public static Comparable max(final Iterator<? extends Comparable> iterable)
    {
        return null;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                 Min
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static Comparable min(final Iterable<? extends Comparable> iterable)
    {
        return null;
    }

    public static Comparable min(final Iterator<? extends Comparable> iterable)
    {
        return null;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                 Sort
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static List sort(final Iterable<? extends Comparable> collection)
    {
        return null;
    }

    public static List sort(final Iterable collection,
                            final Comparator comparator)
    {
        return null;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                 File I/O
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static BufferedInputStream open(final File path)
    {
        return null;
    }

    public static BufferedInputStream open(final String path)
    {
        return null;
    }

    public static void writeLines(final File file,
                                  final List<String> lines)
    {
        writeLines(file, Charset.defaultCharset(), lines);
    }

    public static void writeLines(final File file,
                                  final Charset charset,
                                  final List<String> lines)
    {
    }

    public static List<String> readLines(final File file)
    {
        return readLines(file, Charset.defaultCharset());
    }

    public static List<String> readLines(final File file,
                                         final Charset charset)
    {
        return null;
    }

    public static void debug(final LocalsMap locals)
    {
        // TODO: This is a temporary implementation.

        locals.print();

        System.out.println("Press enter to continue...");
        final Scanner stdin = new Scanner(System.in);
        stdin.nextLine();
    }

    public static void debug(final LocalsMap locals,
                             final boolean condition)
    {
        if (condition)
        {
            debug(locals);
        }
    }

    public static Functor compose(final List functors)
    {
        return null;
    }

    public static void sync(final Object locked,
                            final Functor body)
            throws Throwable
    {
        synchronized (locked)
        {
            body.apply();
        }
    }

    public static Thread newThread(final Functor function,
                                   final List arguments)
    {
        final Runnable runnable = new Runnable()
        {
            @Override
            public void run()
            {
                safeInvoke(function, arguments);
            }
        };

        final Thread thread = new Thread(runnable);

        return thread;
    }
}

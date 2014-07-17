package autumn.lang;

import autumn.lang.exceptions.CheckedException;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
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

    /**
     * This method creates a new linked-list.
     *
     * @param <T> is the type the elements in the new list.
     * @return a new linked-list data-structure.
     */
    public static <T> LinkedList<T> newLinkedList()
    {
        return Lists.newLinkedList();
    }

    /**
     * This method creates a new linked-list that is a copy of an iterable.
     *
     * @param <T> is the type the elements in the new list.
     * @param iterable is the iterable to copy.
     * @return a new linked-list data-structure.
     */
    public static <T> LinkedList<T> newLinkedList(final Iterable<T> iterable)
    {
        Preconditions.checkNotNull(iterable);

        return Lists.newLinkedList(iterable);
    }

    /**
     * This method creates a new array-list.
     *
     * @param <T> is the type the elements in the new list.
     * @return a new array-list data-structure.
     */
    public static <T> ArrayList<T> newArrayList()
    {
        return Lists.newArrayList();
    }

    /**
     * This method creates a new array-list that is a copy of an iterable.
     *
     * @param <T> is the type the elements in the new list.
     * @param iterable is the iterable to copy.
     * @return a new array-list data-structure.
     */
    public static <T> ArrayList<T> newArrayList(final Iterable<T> iterable)
    {
        return Lists.newArrayList(iterable);
    }

    /**
     * This method creates a new hash-set.
     *
     * @param <T> is the type the elements in the new set.
     * @return a new hash-set data-structure.
     */
    public static <T> HashSet<T> newHashSet()
    {
        return Sets.newHashSet();
    }

    /**
     * This method creates a new hash-set that is a copy of an iterable.
     *
     * @param <T> is the type the elements in the new set.
     * @param iterable is the iterable to copy.
     * @return a new hash-set data-structure.
     */
    public static <T> HashSet<T> newHashSet(final Iterable<T> iterable)
    {
        Preconditions.checkNotNull(iterable);

        return Sets.newHashSet(iterable);
    }

    /**
     * This method creates a new tree-set.
     *
     * @param <T> is the type the elements in the new set.
     * @return a new tree-set data-structure.
     */
    public static <T extends Comparable> TreeSet<T> newTreeSet()
    {
        return new TreeSet<T>();
    }

    /**
     * This method creates a new tree-set that is a copy of an iterable.
     *
     * @param <T> is the type the elements in the new set.
     * @param iterable is the iterable to copy.
     * @return a new tree-set data-structure.
     */
    public static <T extends Comparable> TreeSet<T> newTreeSet(final Iterable<T> iterable)
    {
        Preconditions.checkNotNull(iterable);

        return Sets.newTreeSet(iterable);
    }

    /**
     * This method creates a new filter.
     *
     * @param <T> is the type of the elements in the filter.
     * @return a new bloom-filter data-structure.
     */
    public static <T> Set<T> newFilter()
    {
        return null; // TODO
    }

    /**
     * This method creates a new filter.
     *
     * @param <T> is the type of the elements in the filter.
     * @param size is the number of buckets in the new bloom-filter.
     * @return a new filter data-structure.
     */
    public static <T> Set<T> newFilter(final int size)
    {
        return null; // TODO
    }

    /**
     * This method creates a new hash-map.
     *
     * @param <K> is the type the keys in the new map.
     * @param <V> is the type of the values in the new map.
     * @return a new hash-map data-structure.
     */
    public static <K, V> HashMap<K, V> newHashMap()
    {
        return Maps.newHashMap();
    }

    /**
     * This method creates a new tree-map.
     *
     * @param <K> is the type the keys in the new map.
     * @param <V> is the type of the values in the new map.
     * @return a new tree-map data-structure.
     */
    public static <K extends Comparable, V> TreeMap<K, V> newTreeMap()
    {
        return Maps.newTreeMap();
    }

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

    /**
     * This method creates creates a list view of an array.
     *
     * <p>
     * Elements in the underlying array can be set via the set(int, Boolean)
     * method of the new view.
     * </p>
     *
     * @param array is the array that will back the list.
     * @return the new array backed list.
     */
    public static List<Boolean> asList(final boolean[] array)
    {
        Preconditions.checkNotNull(array);

        return new AbstractList<Boolean>()
        {
            @Override
            public Boolean get(int index)
            {
                return array[index];
            }

            @Override
            public Boolean set(final int index,
                               final Boolean value)
            {
                final Boolean last = get(index);

                array[index] = value;

                return last;
            }

            @Override
            public int size()
            {
                return array.length;
            }
        };
    }

    /**
     * This method creates creates a list view of an array.
     *
     * <p>
     * Elements in the underlying array can be set via the set(int, Character)
     * method of the new view.
     * </p>
     *
     * @param array is the array that will back the list.
     * @return the new array backed list.
     */
    public static List<Character> asList(final char[] array)
    {
        Preconditions.checkNotNull(array);

        return new AbstractList<Character>()
        {
            @Override
            public Character get(int index)
            {
                return array[index];
            }

            @Override
            public Character set(final int index,
                                 final Character value)
            {
                final Character last = get(index);

                array[index] = value;

                return last;
            }

            @Override
            public int size()
            {
                return array.length;
            }
        };
    }

    /**
     * This method creates creates a list view of an array.
     *
     * <p>
     * Elements in the underlying array can be set via the set(int, Byte)
     * method of the new view.
     * </p>
     *
     * @param array is the array that will back the list.
     * @return the new array backed list.
     */
    public static List<Byte> asList(final byte[] array)
    {
        Preconditions.checkNotNull(array);

        return new AbstractList<Byte>()
        {
            @Override
            public Byte get(int index)
            {
                return array[index];
            }

            @Override
            public Byte set(final int index,
                            final Byte value)
            {
                final Byte last = get(index);

                array[index] = value;

                return last;
            }

            @Override
            public int size()
            {
                return array.length;
            }
        };
    }

    /**
     * This method creates creates a list view of an array.
     *
     * <p>
     * Elements in the underlying array can be set via the set(int, Short)
     * method of the new view.
     * </p>
     *
     * @param array is the array that will back the list.
     * @return the new array backed list.
     */
    public static List<Short> asList(final short[] array)
    {
        Preconditions.checkNotNull(array);

        return new AbstractList<Short>()
        {
            @Override
            public Short get(int index)
            {
                return array[index];
            }

            @Override
            public Short set(final int index,
                             final Short value)
            {
                final Short last = get(index);

                array[index] = value;

                return last;
            }

            @Override
            public int size()
            {
                return array.length;
            }
        };
    }

    /**
     * This method creates creates a list view of an array.
     *
     * <p>
     * Elements in the underlying array can be set via the set(int, Integer)
     * method of the new view.
     * </p>
     *
     * @param array is the array that will back the list.
     * @return the new array backed list.
     */
    public static List<Integer> asList(final int[] array)
    {
        Preconditions.checkNotNull(array);

        return new AbstractList<Integer>()
        {
            @Override
            public Integer get(int index)
            {
                return array[index];
            }

            @Override
            public Integer set(final int index,
                               final Integer value)
            {
                final Integer last = get(index);

                array[index] = value;

                return last;
            }

            @Override
            public int size()
            {
                return array.length;
            }
        };
    }

    /**
     * This method creates creates a list view of an array.
     *
     * <p>
     * Elements in the underlying array can be set via the set(int, Long)
     * method of the new view.
     * </p>
     *
     * @param array is the array that will back the list.
     * @return the new array backed list.
     */
    public static List<Long> asList(final long[] array)
    {
        Preconditions.checkNotNull(array);

        return new AbstractList<Long>()
        {
            @Override
            public Long get(int index)
            {
                return array[index];
            }

            @Override
            public Long set(final int index,
                            final Long value)
            {
                final Long last = get(index);

                array[index] = value;

                return last;
            }

            @Override
            public int size()
            {
                return array.length;
            }
        };
    }

    /**
     * This method creates creates a list view of an array.
     *
     * <p>
     * Elements in the underlying array can be set via the set(int, Float)
     * method of the new view.
     * </p>
     *
     * @param array is the array that will back the list.
     * @return the new array backed list.
     */
    public static List<Float> asList(final float[] array)
    {
        Preconditions.checkNotNull(array);

        return new AbstractList<Float>()
        {
            @Override
            public Float get(int index)
            {
                return array[index];
            }

            @Override
            public Float set(final int index,
                             final Float value)
            {
                final Float last = get(index);

                array[index] = value;

                return last;
            }

            @Override
            public int size()
            {
                return array.length;
            }
        };
    }

    /**
     * This method creates creates a list view of an array.
     *
     * <p>
     * Elements in the underlying array can be set via the set(int, Double)
     * method of the new view.
     * </p>
     *
     * @param array is the array that will back the list.
     * @return the new array backed list.
     */
    public static List<Double> asList(final double[] array)
    {
        Preconditions.checkNotNull(array);

        return new AbstractList<Double>()
        {
            @Override
            public Double get(int index)
            {
                return array[index];
            }

            @Override
            public Double set(final int index,
                              final Double value)
            {
                final Double last = get(index);

                array[index] = value;

                return last;
            }

            @Override
            public int size()
            {
                return array.length;
            }
        };
    }

    /**
     * This method creates creates a list view of an array.
     *
     * <p>
     * Elements in the underlying array can be set via the set(int, T)
     * method of the new view.
     * </p>
     *
     * @param array is the array that will back the list.
     * @return the new array backed list.
     */
    public static <T> List<T> asList(final T[] array)
    {
        Preconditions.checkNotNull(array);

        return new AbstractList<T>()
        {
            @Override
            public T get(int index)
            {
                return array[index];
            }

            @Override
            public T set(final int index,
                         final T value)
            {
                final T last = get(index);

                array[index] = value;

                return last;
            }

            @Override
            public int size()
            {
                return array.length;
            }
        };
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

    /**
     * This method create a new single-dimensional array.
     *
     * @param element is the type of elements in the new array.
     * @param size is the size of the new array.
     * @return the new array.
     */
    public static Object newArray(final Class element,
                                  final int size)
    {
        return Array.newInstance(element, size);
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
     * This method applies a functor to each element of an iterable and collects the results.
     *
     * @param iterable is the iterable that provides input for the functor.
     * @param function is the functor(Object) : Object that processes the input.
     * @return a list containing the output of the functor.
     * @throws Throwable in order to allow exceptions to propagate from the functor.
     */
    public static List<Object> map(final Iterable iterable,
                                   final Functor function)
            throws Throwable
    {
        Preconditions.checkNotNull(iterable);
        Preconditions.checkNotNull(function);

        final List<Object> output = Lists.newLinkedList();

        for (Object element : iterable)
        {
            output.add(function.apply(element));
        }

        return output;
    }

    /**
     * This method selects the elements from an iterable for which a given functor returns true.
     *
     * @param iterable is the iterable that provides input for the functor.
     * @param function is the functor(Object) : boolean that selects the elements.
     * @return a list containing the selected elements.
     * @throws Throwable in order to allow exceptions to propagate from the functor.
     */
    public static List<Object> filter(final Iterable iterable,
                                      final Functor function)
            throws Throwable
    {
        Preconditions.checkNotNull(iterable);
        Preconditions.checkNotNull(function);

        final List<Object> output = Lists.newLinkedList();

        for (Object element : iterable)
        {
            if (Boolean.TRUE.equals(function.apply(element)))
            {
                output.add(element);
            }
        }

        return output;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                 Reduce
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * This method creates a sorted list from an unsorted iterable.
     *
     * <p>
     * If an in-place sort is preferred, consider using method
     * <code>sort(List)</code> in class
     * <code>java.util.Collections</code>.
     * </p>
     *
     * @param iterable provides the elements for the new sorted list.
     * @return a sorted list that contains the elements of the given input iterable.
     */
    public static <T extends Comparable> List<T> sorted(final Iterable<T> iterable)
    {
        Preconditions.checkNotNull(iterable);

        final ArrayList<T> result = Lists.newArrayList(iterable);

        Collections.sort(result);

        return result;
    }

    /**
     * This method creates a sorted list from an unsorted iterable.
     *
     * <p>
     * If an in-place sort is preferred, consider using method
     * <code>sort(List)</code> in class
     * <code>java.util.Collections</code>.
     * </p>
     *
     * @param iterable provides the elements for the new sorted list.
     * @param comparator is a function object that can compare elements of the iterable.
     * @return a sorted list that contains the elements of the given input iterable.
     */
    public static <T> List<T> sorted(final Iterable<T> iterable,
                                     final Comparator<T> comparator)
    {
        Preconditions.checkNotNull(iterable);

        final ArrayList<T> result = Lists.newArrayList(iterable);

        Collections.sort(result, comparator);

        return result;
    }

    /**
     * This method creates a list that contains the elements from an iterable in reverse order.
     *
     * <p>
     * If an reverse operation is preferred, consider using method
     * <code>reverse(List)</code> in class
     * <code>java.util.Collections</code>.
     * </p>
     *
     * @param iterable provides the elements for the new list.
     * @return a list that contains the elements of the given input iterable in reverse.
     */
    public <T> List<T> reversed(final Iterable<T> iterable)
    {
        Preconditions.checkNotNull(iterable);

        final LinkedList<T> result = Lists.newLinkedList();

        for (T element : iterable)
        {
            result.addFirst(element);
        }

        return result;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                 File I/O
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static BufferedInputStream open(final URL path)
    {
        return null;
    }

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

    public static List<String> readLines(final InputStream input)
    {
        return null;
    }

    public static List<String> readLines(final InputStream input,
                                         final Charset charset)
    {
        return null;
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

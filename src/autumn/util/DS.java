package autumn.util;

import autumn.lang.Functor;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * This class provides static utility methods for working with data-structures.
 *
 * @author Mackenzie High
 */
public final class DS
{
    /**
     * Sole Constructor.
     */
    private DS()
    {
        // Pass, because this is merely a static utility class.
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

    /**
     * This method creates a new linked-list.
     *
     * @param <T> is the type the elements in the new list.
     * @return a new linked-list data-structure.
     */
    public static <T> List<T> newLinkedList()
    {
        return Lists.newLinkedList();
    }

    /**
     * This method creates a new linked-list.
     *
     * @param <T> is the type the elements in the new list.
     * @param T is a supertype of the elements in the new list.
     * @return a new linked-list data-structure.
     */
    public static <T> List<T> newLinkedList(final Class T)
    {
        Preconditions.checkNotNull(T);

        return Collections.checkedList(Lists.newLinkedList(), T);
    }

    /**
     * This method creates a new linked-list that is a copy of an iterable.
     *
     * @param <T> is the type the elements in the new list.
     * @param iterable is the iterable to copy.
     * @return a new linked-list data-structure.
     */
    public static <T> List<T> newLinkedList(final Iterable<T> iterable)
    {
        Preconditions.checkNotNull(iterable);

        return Lists.newLinkedList(iterable);
    }

    /**
     * This method creates a new linked-list that is a copy of an iterable.
     *
     * @param <T> is the type the elements in the new list.
     * @param T is a supertype of the elements in the new list.
     * @param iterable is the iterable to copy.
     * @return a new linked-list data-structure.
     */
    public static <T> List<T> newLinkedList(final Class T,
                                            final Iterable<T> iterable)
    {
        Preconditions.checkNotNull(T);
        Preconditions.checkNotNull(iterable);

        return Collections.checkedList(Lists.newLinkedList(iterable), T);
    }

    /**
     * This method creates a new array-list.
     *
     * @param <T> is the type the elements in the new list.
     * @return a new array-list data-structure.
     */
    public static <T> List<T> newArrayList()
    {
        return Lists.newArrayList();
    }

    /**
     * This method creates a new array-list.
     *
     * @param <T> is the type the elements in the new list.
     * @param T is a supertype of the elements in the new list.
     * @return a new array-list data-structure.
     */
    public static <T> List<T> newArrayList(final Class T)
    {
        Preconditions.checkNotNull(T);

        return Collections.checkedList(Lists.newArrayList(), T);
    }

    /**
     * This method creates a new array-list that is a copy of an iterable.
     *
     * @param <T> is the type the elements in the new list.
     * @param iterable is the iterable to copy.
     * @return a new array-list data-structure.
     */
    public static <T> List<T> newArrayList(final Iterable<T> iterable)
    {
        return Lists.newArrayList(iterable);
    }

    /**
     * This method creates a new array-list that is a copy of an iterable.
     *
     * @param <T> is the type the elements in the new list.
     * @param T is a supertype of the elements in the new list.
     * @param iterable is the iterable to copy.
     * @return a new array-list data-structure.
     */
    public static <T> List<T> newArrayList(final Class T,
                                           final Iterable<T> iterable)
    {
        Preconditions.checkNotNull(T);

        return Collections.checkedList(Lists.newArrayList(iterable), T);
    }

    /**
     * This method creates a new hash-set.
     *
     * @param <T> is the type the elements in the new set.
     * @return a new hash-set data-structure.
     */
    public static <T> Set<T> newHashSet()
    {
        return Sets.newHashSet();
    }

    /**
     * This method creates a new hash-set.
     *
     * @param <T> is the type the elements in the new set.
     * @param T is a supertype of the elements in the new set.
     * @return a new hash-set data-structure.
     */
    public static <T> Set<T> newHashSet(final Class T)
    {
        Preconditions.checkNotNull(T);

        return Collections.checkedSet(Sets.newHashSet(), T);
    }

    /**
     * This method creates a new hash-set that is a copy of an iterable.
     *
     * @param <T> is the type the elements in the new set.
     * @param iterable is the iterable to copy.
     * @return a new hash-set data-structure.
     */
    public static <T> Set<T> newHashSet(final Iterable<T> iterable)
    {
        Preconditions.checkNotNull(iterable);

        return Sets.newHashSet(iterable);
    }

    /**
     * This method creates a new hash-set that is a copy of an iterable.
     *
     * @param <T> is the type the elements in the new set.
     * @param T is a supertype of the elements in the new set.
     * @param iterable is the iterable to copy.
     * @return a new hash-set data-structure.
     */
    public static <T> Set<T> newHashSet(final Class T,
                                        final Iterable<T> iterable)
    {
        Preconditions.checkNotNull(T);
        Preconditions.checkNotNull(iterable);

        return Collections.checkedSet(Sets.newHashSet(iterable), T);
    }

    /**
     * This method creates a new tree-set.
     *
     * @param <T> is the type the elements in the new set.
     * @return a new tree-set data-structure.
     */
    public static <T extends Comparable> SortedSet<T> newTreeSet()
    {
        return new TreeSet<T>();
    }

    /**
     * This method creates a new tree-set.
     *
     * @param <T> is the type the elements in the new set.
     * @param T is a supertype of the elements in the new set.
     * @return a new tree-set data-structure.
     */
    public static <T extends Comparable> SortedSet<T> newTreeSet(final Class T)
    {
        Preconditions.checkNotNull(T);

        return Collections.checkedSortedSet(new TreeSet<T>(), T);
    }

    /**
     * This method creates a new tree-set that is a copy of an iterable.
     *
     * @param <T> is the type the elements in the new set.
     * @param iterable is the iterable to copy.
     * @return a new tree-set data-structure.
     */
    public static <T extends Comparable> SortedSet<T> newTreeSet(final Iterable<T> iterable)
    {
        Preconditions.checkNotNull(iterable);

        return Sets.newTreeSet(iterable);
    }

    /**
     * This method creates a new tree-set that is a copy of an iterable.
     *
     * @param <T> is the type the elements in the new set.
     * @param T is a supertype of the elements in the new set.
     * @param iterable is the iterable to copy.
     * @return a new tree-set data-structure.
     */
    public static <T extends Comparable> SortedSet<T> newTreeSet(final Class T,
                                                                 final Iterable<T> iterable)
    {
        Preconditions.checkNotNull(T);
        Preconditions.checkNotNull(iterable);

        return Collections.checkedSortedSet(Sets.newTreeSet(iterable), T);
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
    public static <K, V> Map<K, V> newHashMap()
    {
        return Maps.newHashMap();
    }

    /**
     * This method creates a new hash-map.
     *
     * @param <K> is the type the keys in the new map.
     * @param <V> is the type of the values in the new map.
     * @param K is a supertype of the keys in the new map.
     * @param V is a supertype of the values in the new map.
     * @return a new hash-map data-structure.
     */
    public static <K, V> Map<K, V> newHashMap(final Class K,
                                              final Class V)
    {
        Preconditions.checkNotNull(K);
        Preconditions.checkNotNull(V);

        return Collections.checkedMap(Maps.newHashMap(), K, V);
    }

    /**
     * This method creates a new tree-map.
     *
     * @param <K> is the type the keys in the new map.
     * @param <V> is the type of the values in the new map.
     * @return a new tree-map data-structure.
     */
    public static <K extends Comparable, V> SortedMap<K, V> newTreeMap()
    {
        return Maps.newTreeMap();
    }

    /**
     * This method creates a new tree-map.
     *
     * @param <K> is the type the keys in the new map.
     * @param <V> is the type of the values in the new map.
     * @param K is a supertype of the keys in the new map.
     * @param V is a supertype of the values in the new map.
     * @return a new tree-map data-structure.
     */
    public static <K extends Comparable, V> SortedMap<K, V> newTreeMap(final Class K,
                                                                       final Class V)
    {
        Preconditions.checkNotNull(K);
        Preconditions.checkNotNull(V);

        return Collections.checkedSortedMap(Maps.newTreeMap(), K, V);
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
    public static <T> SortedSet<T> immutable(final NavigableSet<? extends T> mutable)
    {
        Preconditions.checkNotNull(mutable);

        return Collections.unmodifiableSortedSet(new TreeSet<T>(mutable));
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
    public static <K, V> SortedMap<K, V> immutable(
            final NavigableMap<? extends K, ? extends V> mutable)
    {
        Preconditions.checkNotNull(mutable);

        return Collections.unmodifiableSortedMap(new TreeMap<K, V>(mutable));
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
            output.add(F.apply(function, Collections.singletonList(element)));
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
            if (Boolean.TRUE.equals(F.apply(function, Collections.singletonList(element))))
            {
                output.add(element);
            }
        }

        return output;
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
    public static <T> List<T> reversed(final Iterable<T> iterable)
    {
        Preconditions.checkNotNull(iterable);

        final LinkedList<T> result = Lists.newLinkedList();

        for (T element : iterable)
        {
            result.addFirst(element);
        }

        return result;
    }

    /**
     * This method searches for a value in an iterable.
     *
     * <p>
     * The search is conducted in moving away from index zero.
     * </p>
     *
     * @param iterable is the iterable to search.
     * @param value is the value to search for,
     * @return the index of the found value, or negative one, if the value was not found.
     */
    public static <T> int indexOf(final Iterable<T> iterable,
                                  final T value)
    {
        return indexOf(iterable, value, 0);
    }

    /**
     * This method searches for a value in an iterable, but skips given number of occurrences.
     *
     * <p>
     * The search is conducted in moving away from index zero.
     * </p>
     *
     * @param iterable is the iterable to search.
     * @param value is the value to search for,
     * @param skip is the number of occurrences to skip.
     * @return the index of the found value, or negative one, if the value was not found.
     */
    public static <T> int indexOf(final Iterable<T> iterable,
                                  final T value,
                                  final int skip)
    {
        Preconditions.checkNotNull(iterable, "iterable is null");
        Preconditions.checkArgument(skip >= 0, "skip is less than zero");

        int skipped = 0;

        int index = 0;

        for (T element : iterable)
        {
            final boolean equals = Objects.equals(value, element);

            if (equals)
            {
                if (skipped == skip)
                {
                    return index;
                }
                else
                {
                    ++skipped;
                }
            }

            ++index;
        }

        return -1; // Not Found
    }

    /**
     * This method determines whether an iterable contains a given value.
     *
     * @param iterable is the iterable to search.
     * @param value is the value to search for.
     * @return true, iff the iterable contains the value.
     */
    public static <T> boolean contains(final Iterable<T> iterable,
                                       final T value)
    {
        Preconditions.checkNotNull(iterable, "iterable is null");

        if (iterable instanceof Collection)
        {
            return ((Collection) iterable).contains(value);
        }
        else
        {
            return indexOf(iterable, value) >= 0;
        }
    }

    /**
     * This method determines whether an iterable contains every element of another iterable.
     *
     * @param iterable is the iterable that must contain every element of set.
     * @param set is the iterable whose elements must be in the other iterable.
     * @return true, iff at least one element X in iterable equals an element Y in set,
     * for all elements in set.
     */
    public static <T> boolean containsAll(final Iterable<T> iterable,
                                          final Iterable<T> set)
    {
        Preconditions.checkNotNull(iterable, "iterable is null");
        Preconditions.checkNotNull(iterable, "set is null");

        final Collection<T> required = set instanceof Collection
                ? (Collection) set
                : Sets.newHashSet(set);

        final Collection<T> actual = iterable instanceof Collection
                ? (Collection) iterable
                : Sets.newHashSet(iterable);

        final boolean result = actual.containsAll(required);

        return result;
    }

    /**
     * This method determines whether an iterable contains an element of another iterable.
     *
     * @param iterable is the iterable that must contain an element from set.
     * @param set is the iterable that shares at least one element with the other iterable.
     * @return true, iff at least one element in set equals an element in iterable.
     */
    public static <T> boolean containsAny(final Iterable<T> iterable,
                                          final Iterable<T> set)
    {
        Preconditions.checkNotNull(iterable, "iterable is null");
        Preconditions.checkNotNull(iterable, "set is null");

        final Set<T> required = set instanceof Set ? (Set) set : Sets.newHashSet(set);

        for (T element : iterable)
        {
            if (required.contains(element))
            {
                return true;
            }
        }

        return false;
    }

    /**
     * This method finds the longest-common-subsequence between two sequences.
     *
     * @param sequence1 is the first sequence.
     * @param seqeunce2 is the second sequence.
     * @return the longest-common-subsequence between the two sequences.
     */
    public static <T> List<T> longestCommonSubsequence(final Iterable<T> sequence1,
                                                       final Iterable<T> seqeunce2)
    {
        return null;
    }

    /**
     * This method finds the common prefix of two sequences.
     *
     * @param sequence1 is the first sequence.
     * @param sequence2 is the second sequence.
     * @return the prefix that the two sequences share.
     */
    public static <T> List<T> commonPrefix(final Iterable<T> sequence1,
                                           final Iterable<T> sequence2)
    {
        return null;
    }

    /**
     * This method finds the common suffix of two sequences.
     *
     * @param sequence1 is the first sequence.
     * @param sequence2 is the second sequence.
     * @return the suffix that the two sequences share.
     */
    public static <T> List<T> commonSuffix(final List<T> sequence1,
                                           final List<T> sequence2)
    {
        return null;
    }
}

package high.mackenzie.autumn.util;

import autumn.lang.Module;
import autumn.lang.Record;
import autumn.lang.TypedFunctor;
import autumn.lang.annotations.Infer;
import autumn.lang.exceptions.CheckedException;
import autumn.lang.internals.ArgumentStack;
import autumn.lang.internals.Helpers;
import autumn.lang.internals.Operators;
import high.mackenzie.autumn.resources.dev.AsyncTask;
import autumn.util.F;
import autumn.util.functors.Action;
import autumn.util.functors.Function1;
import autumn.util.functors.Function2;
import autumn.util.functors.Predicate;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import high.mackenzie.autumn.util.json.JsonDecoder;
import high.mackenzie.autumn.util.json.JsonEncoder;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
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
public final class T
{
    private static abstract class ArrayBackedList<E>
            extends AbstractList<E>
    {
    }

    private static BigInteger unique = BigInteger.ZERO;

    /**
     * Sole Constructor.
     */
    private T()
    {
        // Pass, because this is merely a static utility class.
    }

    /**
     * This method rethrows an exception quietly.
     *
     * <p>
     * If the exception is checked, then it will be wrapped in a CheckedException object
     * and then rethrown; otherwise, the exception will simply be rethrown immediately.
     * </p>
     *
     * @param problem is the exception to rethrow.
     */
    public static void rethrow(final Throwable problem)
    {
        if (problem instanceof RuntimeException)
        {
            throw (RuntimeException) problem;
        }
        else if (problem instanceof Error)
        {
            throw (Error) problem;
        }
        else
        {
            throw new CheckedException(problem);
        }
    }

    /**
     * This method applies a functor to a list of arguments.
     *
     * @param functor is the functor itself.
     * @param arguments are the arguments to apply the functor to.
     * @return the result of applying the functor to the arguments.
     * @throws NullPointerException if functor is null.
     * @throws NullPointerException if arguments is null.
     * @throws Throwable in order to propagate any exceptions thrown by the functor.
     */
    public static Object apply(final TypedFunctor functor,
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

        // The number of arguments must match the number of parameters.
        if (functor.parameterTypes().size() != stack.size())
        {
            stack.clear();
            throw new IllegalArgumentException("arguments.length != parameters.length");
        }

        // Invoke the functor.
        functor.apply(stack);

        // Retrieve the return-value of the functor.
        final Object result = stack.popResult();

        return result;
    }

    /**
     * This method prints a formated string to standard-output.
     *
     * <p>
     * This is simply a convenience method on top of PrintStream's printf(String, Object[]).
     * </p>
     *
     * @param format is the format specifier.
     * @param args are the arguments to substitute into the format string.
     */
    public static void printf(final String format,
                              final Iterable<Object> args)
    {
        Preconditions.checkNotNull(format);
        Preconditions.checkNotNull(args);

        System.out.printf(format, Lists.newLinkedList(args).toArray());
    }

    /**
     * This method prints a formated string to standard-error.
     *
     * <p>
     * This is simply a convenience method on top of PrintStream's printf(String, Object[]).
     * </p>
     *
     * @param format is the format specifier.
     * @param args are the arguments to substitute into the format string.
     */
    public static void printerrf(final String format,
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
     * This method creates an iterable that iterates over the integers in a discrete range.
     *
     * @param start is the inclusive lowest value in the range.
     * @param end is the inclusive highest value in the range.
     * @param step is the distance that elements in the range are separated for one another.
     * @return a lazy list of integers.
     * @throws IllegalArgumentException if end is less-than start.
     */
    public static Iterable<Integer> range(final int start,
                                          final int end,
                                          final int step)
    {
        // TODO: test

        Preconditions.checkArgument(start <= end);

        return new Iterable<Integer>()
        {
            @Override
            public Iterator<Integer> iterator()
            {
                return new Iterator<Integer>()
                {
                    private int index = start;

                    @Override
                    public boolean hasNext()
                    {
                        return index <= end;
                    }

                    @Override
                    public Integer next()
                    {
                        final int result = index;

                        index = index + step;

                        return result;
                    }

                    @Override
                    public void remove()
                    {
                        throw new UnsupportedOperationException("Sorry, you cannot remove a number from the number line.");
                    }
                };
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
    public static List<Boolean> iter(final boolean[] array)
    {
        /**
         * Create a list that wraps the array.
         */
        final ArrayBackedList<Boolean> list = new ArrayBackedList<Boolean>()
        {
            @Override
            public Boolean get(int index)
            {
                return array[index];
            }

            @Override
            public int size()
            {
                return array.length;
            }
        };

        /**
         * Make sure the list is unmodifiable.
         */
        return Collections.unmodifiableList(list);
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
    public static List<Character> iter(final char[] array)
    {
        /**
         * Create a list that wraps the array.
         */
        final ArrayBackedList<Character> list = new ArrayBackedList<Character>()
        {
            @Override
            public Character get(int index)
            {
                return array[index];
            }

            @Override
            public int size()
            {
                return array.length;
            }
        };

        /**
         * Make sure the list is unmodifiable.
         */
        return Collections.unmodifiableList(list);
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
    public static List<Byte> iter(final byte[] array)
    {
        /**
         * Create a list that wraps the array.
         */
        final ArrayBackedList<Byte> list = new ArrayBackedList<Byte>()
        {
            @Override
            public Byte get(int index)
            {
                return array[index];
            }

            @Override
            public int size()
            {
                return array.length;
            }
        };

        /**
         * Make sure the list is unmodifiable.
         */
        return Collections.unmodifiableList(list);
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
    public static List<Short> iter(final short[] array)
    {
        /**
         * Create a list that wraps the array.
         */
        final ArrayBackedList<Short> list = new ArrayBackedList<Short>()
        {
            @Override
            public Short get(int index)
            {
                return array[index];
            }

            @Override
            public int size()
            {
                return array.length;
            }
        };

        /**
         * Make sure the list is unmodifiable.
         */
        return Collections.unmodifiableList(list);
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
    public static List<Integer> iter(final int[] array)
    {
        /**
         * Create a list that wraps the array.
         */
        final ArrayBackedList<Integer> list = new ArrayBackedList<Integer>()
        {
            @Override
            public Integer get(int index)
            {
                return array[index];
            }

            @Override
            public int size()
            {
                return array.length;
            }
        };

        /**
         * Make sure the list is unmodifiable.
         */
        return Collections.unmodifiableList(list);
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
    public static List<Long> iter(final long[] array)
    {
        /**
         * Create a list that wraps the array.
         */
        final ArrayBackedList<Long> list = new ArrayBackedList<Long>()
        {
            @Override
            public Long get(int index)
            {
                return array[index];
            }

            @Override
            public int size()
            {
                return array.length;
            }
        };

        /**
         * Make sure the list is unmodifiable.
         */
        return Collections.unmodifiableList(list);
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
    public static List<Float> iter(final float[] array)
    {
        /**
         * Create a list that wraps the array.
         */
        final ArrayBackedList<Float> list = new ArrayBackedList<Float>()
        {
            @Override
            public Float get(int index)
            {
                return array[index];
            }

            @Override
            public int size()
            {
                return array.length;
            }
        };

        /**
         * Make sure the list is unmodifiable.
         */
        return Collections.unmodifiableList(list);
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
    public static List<Double> iter(final double[] array)
    {
        /**
         * Create a list that wraps the array.
         */
        final ArrayBackedList<Double> list = new ArrayBackedList<Double>()
        {
            @Override
            public Double get(int index)
            {
                return array[index];
            }

            @Override
            public int size()
            {
                return array.length;
            }
        };

        /**
         * Make sure the list is unmodifiable.
         */
        return Collections.unmodifiableList(list);
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
    public static <T> List<T> iter(final T[] array)
    {
        /**
         * Create a list that wraps the array.
         */
        final ArrayBackedList<T> list = new ArrayBackedList<T>()
        {
            @Override
            public T get(int index)
            {
                return array[index];
            }

            @Override
            public int size()
            {
                return array.length;
            }
        };

        /**
         * Make sure the list is unmodifiable.
         */
        return Collections.unmodifiableList(list);
    }

    /**
     * This method creates an iterator over an iterable.
     *
     * @param iterable is the iterable itself.
     * @return iterable.
     */
    public static <T> List<T> iter(final Iterable<T> iterable)
    {
        /**
         * Special Case: The iterable is a list.
         */
        if (iterable instanceof List)
        {
            return Collections.unmodifiableList((List) iterable);
        }

        /**
         * Create a list that wraps the array.
         */
        final ArrayBackedList<T> list = new ArrayBackedList<T>()
        {
            @Override
            public T get(int index)
            {
                return Iterables.get(iterable, index);
            }

            @Override
            public int size()
            {
                return Iterables.size(iterable);
            }

            @Override
            public Iterator<T> iterator()
            {
                return Iterators.unmodifiableIterator(iterable.iterator());
            }
        };

        /**
         * Make sure the list is unmodifiable.
         */
        return Collections.unmodifiableList(list);
    }

    /**
     * This method creates an iterator over an iterator.
     *
     * @param iterable is the iterator itself.
     * @return an iterable that wraps the iterator.
     */
    public static <T> Iterable<T> iter(final Iterator<T> iterable)
    {
        return new Iterable<T>()
        {
            @Override
            public Iterator<T> iterator()
            {
                return iterable;
            }
        };
    }

    public static List<String> iter(final Annotation anno)
    {
        try
        {
            Preconditions.checkNotNull(anno);

            /**
             * The values stored in the annotation will be added to this list.
             */
            final List<String> result = new LinkedList();

            /**
             * If the annotation is an Autumn-style annotation,
             * then it must have a value() : String[] method.
             */
            final Method method = F.findMethod(anno.annotationType(), "value", Collections.EMPTY_LIST);

            /**
             * If the annotation is not an Autumn-style annotation,
             * then we cannot extract any values from it.
             */
            if (method == null || method.getReturnType() != String[].class)
            {
                return Collections.EMPTY_LIST;
            }

            /**
             * Extract the values from the annotation.
             */
            final String[] values = (String[]) method.invoke(anno);
            result.addAll(Arrays.asList(values));

            /**
             * Return the result as an immutable list.
             */
            return Collections.unmodifiableList(result);
        }
        catch (IllegalAccessException ex)
        {
            return Collections.EMPTY_LIST;
        }
        catch (InvocationTargetException ex)
        {
            return Collections.EMPTY_LIST;
        }
    }

    /**
     * This method returns the default value of a given type.
     *
     * <p>
     * The boolean type returns false. <br>
     * Numeric types return zero. <br>
     * Reference types return null. <br>
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

    /**
     * This method compares to comparable values.
     *
     * <p>
     * A result of negative one indicates that the left operand is less than the right operand.
     * A result of positive one indicates that the left operand is greater than the right operand.
     * A zero result indicates that the operands are equal.
     * </p>
     *
     * </p>
     * This method considers null to be less than an object.
     * </p>
     *
     * @param left is the left operand.
     * @param right is the right operand.
     * @return an integer that represents the relationship between the operands.
     */
    public static int compare(final Comparable left,
                              final Comparable right)
    {
        if (left == null && right == null)
        {
            return 0;
        }
        else if (left == null && right != null)
        {
            return -1;
        }
        else if (left != null && right == null)
        {
            return 1;
        }
        else
        {
            final int relationship = ((Comparable) left).compareTo(right);

            if (relationship == 0)
            {
                return 0;
            }
            else if (relationship > 0)
            {
                return 1;
            }
            else // relationship < 0
            {
                return -1;
            }
        }
    }

    /**
     * This method calculates the sum of a set of values.
     *
     * @param values are the values to sum.
     * @return the sum of the values.
     */
    public static BigDecimal sum(final Iterable<?> values)
    {
        BigDecimal total = F.big(BigDecimal.ZERO);

        for (Object value : values)
        {
            Preconditions.checkNotNull(value);

            total = total.add(F.big(value));
        }

        return total;
    }

    /**
     * This method calculates the average of a set of values.
     *
     * @param values are the values to average.
     * @return the average of the values.
     */
    public static BigDecimal average(final Iterable<?> values)
    {
        assert values != null;

        BigDecimal total = BigDecimal.ZERO;

        int count = 0;

        for (Object value : values)
        {
            final BigDecimal big = F.big(value);

            total = total.add(big);
            ++count;
        }

        final BigDecimal result = total.divide(F.big(count));

        return F.big(result);
    }

    /**
     * This method searches for the minimum value in a set of values.
     *
     * @param values are the values to search through.
     * @return the minimum value in the group.
     * @throws NullPointerException if values is null.
     */
    public static Comparable minimum(final Iterable<Comparable> values)
    {
        Preconditions.checkNotNull(values);

        final Iterator<Comparable> iter = values.iterator();

        /**
         * If there are no values, then return null;
         * otherwise, return the minimum value.
         */
        if (iter.hasNext() == false)
        {
            return null;
        }
        else
        {
            Comparable least = iter.next();

            while (iter.hasNext())
            {
                final Comparable value = iter.next();

                least = compare(value, least) < 0 ? value : least;
            }

            return least;
        }
    }

    /**
     * This method searches for the maximum value in a set of values.
     *
     * @param values are the values to search through.
     * @return the maximum value in the group.
     * @throws NullPointerException if values is null.
     */
    public static Comparable maximum(final Iterable<Comparable> values)
    {
        Preconditions.checkNotNull(values);

        final Iterator<Comparable> iter = values.iterator();

        /**
         * If there are no values, then return null;
         * otherwise, return the maximum value.
         */
        if (iter.hasNext() == false)
        {
            return null;
        }
        else
        {
            Comparable greatest = iter.next();

            while (iter.hasNext())
            {
                final Comparable value = iter.next();

                greatest = compare(value, greatest) > 0 ? value : greatest;
            }

            return greatest;
        }
    }

    /**
     * This method determines whether any of the values in an iterable match a predicate.
     *
     * @param values are the values to check.
     * @param condition is the condition that may match one of the values.
     * @return true, iff at least one of the values matches the predicate.
     */
    public static boolean any(final Iterable<?> values,
                              final Predicate condition)
            throws Throwable
    {
        for (Object value : values)
        {
            if (condition.invoke(value))
            {
                return true;
            }
        }

        return false;
    }

    /**
     * This method determines whether all of the values in an iterable match a predicate.
     *
     * @param values are the values to check.
     * @param condition is the condition that may match the values.
     * @return true, iff all of the values match the predicate.
     */
    public static boolean all(final Iterable<?> values,
                              final Predicate condition)
            throws Throwable
    {
        for (Object value : values)
        {
            if (condition.invoke(value) == false)
            {
                return false;
            }
        }

        return true;
    }

    /**
     * This method counts the the values that match a predicate
     *
     * @param values are the values to check.
     * @param condition is the condition that may match some of the values.
     * @return the number of values (may be zero) that match the predicate.
     */
    public static int count(final Iterable<?> values,
                            final Predicate condition)
            throws Throwable
    {
        int count = 0;

        for (Object value : values)
        {
            if (condition.invoke(value))
            {
                ++count;
            }
        }

        return count;
    }

    /**
     * This method applies a functor to each element of an iterable and collects the results.
     *
     * @param iterable is the iterable that provides input for the functor.
     * @param function is the functor(Object) : Object that processes the input.
     * @return an immutable list containing the output of the functor.
     * @throws Throwable in order to allow exceptions to propagate from the functor.
     */
    public static List<Object> map(final Iterable iterable,
                                   final Function1 function)
            throws Throwable
    {
        Preconditions.checkNotNull(iterable);
        Preconditions.checkNotNull(function);

        final List<Object> output = Lists.newLinkedList();

        for (Object element : iterable)
        {
            output.add(T.apply(function, Collections.singletonList(element)));
        }

        return Collections.unmodifiableList(output);
    }

    public static List<Object> filter(final Iterable<?> iterable,
                                      final Predicate function)
            throws Throwable
    {
        Preconditions.checkNotNull(iterable);
        Preconditions.checkNotNull(function);

        final List<Object> result = new LinkedList();

        for (Object value : iterable)
        {
            if (function.invoke(value))
            {
                result.add(value);
            }
        }

        return Collections.unmodifiableList(result);
    }

    public static Object reduce(final Iterable iterable,
                                final Function2 functor)
            throws Throwable
    {
        return null;
    }

    public static Object find(final Iterable<?> iterable,
                              final Predicate function,
                              final int skip)
            throws Throwable
    {
        Preconditions.checkNotNull(iterable);
        Preconditions.checkNotNull(function);
        Preconditions.checkArgument(skip >= 0);

        int count = 0;

        for (Object value : iterable)
        {
            final boolean match = function.invoke(value);

            if (match && count == skip)
            {
                return value;
            }
            else if (match && count != skip)
            {
                ++count;
            }
        }

        return null;
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
     * This method replaces escape-sequences in a string with the characters they represent.
     *
     * @param string the string that may contain escape-sequences.
     * @return the modified string.
     * @throws NullPointerException if string is null.
     * @throws IllegalArgumentException if an escape-sequence is malformed.
     */
    public static String escape(final CharSequence string)
    {
        Preconditions.checkNotNull(string);

        // This is the string being built.
        final StringBuilder result = new StringBuilder();

        // This is true, when an escape-sequence is being processed.
        boolean in_escape = false;

        // This is the number of characters to advance forward.
        int increment;

        for (int i = 0; i < string.length(); i = i + increment)
        {
            increment = 1;

            final char chr = string.charAt(i);

            if (in_escape == false && chr == '\\')
            {
                in_escape = true;
            }
            else if (in_escape && chr == 't')
            {
                result.append('\t');
                in_escape = false;
            }
            else if (in_escape && chr == 'b')
            {
                result.append('\b');
                in_escape = false;
            }
            else if (in_escape && chr == 'n')
            {
                result.append('\n');
                in_escape = false;
            }
            else if (in_escape && chr == 'r')
            {
                result.append('\r');
                in_escape = false;
            }
            else if (in_escape && chr == 'f')
            {
                result.append('\f');
                in_escape = false;
            }
            else if (in_escape && chr == '\'')
            {
                result.append('\'');
                in_escape = false;
            }
            else if (in_escape && chr == '"')
            {
                result.append('\"');
                in_escape = false;
            }
            else if (in_escape && chr == '\\')
            {
                result.append('\\');
                in_escape = false;
            }
            else if (in_escape && string.length() >= i + 5)
            {
                /**
                 * Get the character-code, which consist of five digits.
                 */
                final String escape_string = string.subSequence(i, i + 5).toString();

                /**
                 * The string must contain exactly five digits.
                 */
                if (!escape_string.matches("[0-9][0-9][0-9][0-9][0-9]"))
                {
                    Preconditions.checkArgument(false, "Invalid Escape Sequence: \\" + escape_string);
                }

                /**
                 * The character-code must be an integer in range [0, Character.MAX_VALUE].
                 */
                final int value = Integer.parseInt(escape_string);

                if (value < 0 || value > Character.MAX_VALUE)
                {
                    Preconditions.checkArgument(false, "Invalid Escape Sequence: \\" + escape_string);
                }

                /**
                 * Add the specified character to the string.
                 */
                final char character = (char) value;
                result.append(character);

                /**
                 * Skip over the escape-string and exit the escape-sequence.
                 */
                increment = increment + 4;
                in_escape = false;
            }
            else if (in_escape)
            {
                Preconditions.checkArgument(false, "Invalid Escape Sequence: \\" + chr);
            }
            else
            {
                result.append(chr);
            }
        }

        return result.toString();
    }

    /**
     * This method replaces escapable characters with escape-sequences.
     *
     * @param string is the string to un-escape.
     * @return the string with escapable characters replaced with escape-sequences.
     */
    public static String unescape(final CharSequence string)
    {
        Preconditions.checkNotNull(string);

        // These are the characters that can be replaced with an escape-sequence.
        final String escapable = "\t\b\n\r\f\'\"\\";

        final String escaped = "tbnrf'\"\\";

        final StringBuilder result = new StringBuilder();

        for (int i = 0; i < string.length(); i++)
        {
            final char chr = string.charAt(i);

            if (escapable.indexOf(chr) >= 0)
            {
                result.append('\\');
                result.append(escaped.charAt(escapable.indexOf(chr)));
            }
            else if (chr == 0)
            {
                result.append("\\00000");
            }
            else
            {
                result.append(chr);
            }
        }

        return result.toString();
    }

    /**
     * This method pads the start of a string.
     *
     * @param string is the string to pad.
     * @param character is the padding character.
     * @param length is the required minimum length of the string.
     * @return the padded string.
     */
    public static String padStart(final CharSequence string,
                                  final char character,
                                  final int length)
    {
        Preconditions.checkNotNull(string);
        Preconditions.checkArgument(length >= 0);

        final int size = string.length();

        final StringBuilder result = new StringBuilder();

        // Add the padding characters.
        while ((result.length() + size) < length)
        {
            result.append(character);
        }

        result.append(string);

        return result.toString();
    }

    /**
     * This method pads the end of a string.
     *
     * @param string is the string to pad.
     * @param character is the padding character.
     * @param length is the required minimum length of the string.
     * @return the padded string.
     */
    public static String padEnd(final CharSequence string,
                                final char character,
                                final int length)
    {
        Preconditions.checkNotNull(string);
        Preconditions.checkArgument(length >= 0);

        final int size = string.length();

        final StringBuilder result = new StringBuilder(string);

        // Add the padding characters.
        while ((result.length() + size) < length)
        {
            result.append(character);
        }

        return result.toString();
    }

    /**
     * This method pads the beginning of a string with zeros, if needed.
     *
     * @param string is the string to possibly pad.
     * @param length is the desired minimum length of the result.
     * @return the padded string.
     */
    public static String zfill(final CharSequence string,
                               final int length)
    {
        return padStart(string, '0', length);
    }

    /**
     * This method creates a string representation for an iterable.
     *
     * @param iterable is the iterable itself.
     * @param prefix is a string to prepend onto the result.
     * @param separator is the substring used to separate elements in the result.
     * @param suffix is a string to append onto the result.
     * @return the aforedescribed result.
     */
    public static String str(final Iterable<?> iterable,
                             final String prefix,
                             final String separator,
                             final String suffix)
    {
        Preconditions.checkNotNull(iterable);
        Preconditions.checkNotNull(prefix);
        Preconditions.checkNotNull(separator);
        Preconditions.checkNotNull(suffix);

        final List<?> elements = Lists.newArrayList(iterable);

        final StringBuilder result = new StringBuilder();

        result.append(prefix);
        {
            int count = 0;

            for (Object arg : elements)
            {
                ++count;

                result.append(arg);

                if (count < elements.size())
                {
                    result.append(separator);
                }
            }
        }
        result.append(suffix);

        return result.toString();
    }

    /**
     * This method creates a string representation for a map.
     *
     * @param map is the map itself.
     * @param prefix is a string to prepend onto the result.
     * @param assign is the substring to place between keys and values in the result.
     * @param seperator is the substring used to separate key-value-pairs from each other in the result.
     * @param suffix is a string to append onto the result.
     * @return the aforedescribed result.
     */
    public static String str(final Map<?, ?> map,
                             final String prefix,
                             final String assign,
                             final String separator,
                             final String suffix)
    {
        Preconditions.checkNotNull(map);
        Preconditions.checkNotNull(prefix);
        Preconditions.checkNotNull(assign);
        Preconditions.checkNotNull(separator);
        Preconditions.checkNotNull(suffix);

        final StringBuilder result = new StringBuilder();

        result.append(prefix);
        {
            int count = 0;

            for (Entry<?, ?> entry : map.entrySet())
            {
                ++count;

                result.append(entry.getKey());
                result.append(assign);
                result.append(entry.getValue());

                if (count < map.size())
                {
                    result.append(separator);
                }
            }
        }
        result.append(suffix);

        return result.toString();
    }

    /**
     * This method synchronizes the invocation of a functor.
     *
     * @param locked is the object to obtain a lock on.
     * @param body is the functor to invoke.
     * @throws Throwable if an exception is thrown by the functor.
     * @throws IllegalArgumentException if the functor is non-nullary.
     */
    public static void sync(final Object locked,
                            final Action body)
            throws Throwable
    {
        Preconditions.checkNotNull(body);
        Preconditions.checkArgument(body.parameterTypes().isEmpty(),
                                    "The functor cannot take any parameters.");

        synchronized (locked)
        {
            T.apply(body, Collections.EMPTY_LIST);
        }
    }

    /**
     * This method sets the value of each element to its default value.
     *
     * <p>
     * This method does not affect the special-method bindings.
     * </p>
     *
     * @return this object, if this record is mutable; otherwise, return a modified copy thereof.
     */
    @Infer
    public static <T extends Record> T clear(final T self)
    {
        T p = self;

        final List<Class> types = self.types();

        for (int i = 0; i < self.size(); i++)
        {
            p = (T) p.set(i, F.defaultValueOf(types.get(i)));
        }

        return p;
    }

    /**
     * This method assigns a new value to a specific element.
     *
     * <p>
     * Auto-unboxing will be performed, if necessary.
     * However, the unboxed value will not be auto-widened.
     * </p>
     *
     * <p>
     * This is a linear-time operation in the worst case.
     * </p>
     *
     * @param key is the name of the element to assign the value to.
     * @param value is the new value.
     * @return this object, if this record is mutable; otherwise, return a modified copy thereof.
     * @throws NullPointerException if the key is null.
     * @throws NoSuchElementException if the key does not refer to an actual element.
     * @throws ClassCastException if the object is not of an acceptable type.
     */
    @Infer
    public static <T extends Record> T set(final T self,
                                           final String key,
                                           final Object value)
    {
        Preconditions.checkNotNull(key);

        final int index = self.keys().indexOf(key);

        if (index < 0)
        {
            throw new NoSuchElementException("Key: " + key);
        }

        final Record result = self.set(index, value);

        return (T) result;
    }

    /**
     * This method retrieves the value of a specific element.
     *
     * <p>
     * This is a linear-time operation in the worst case.
     * </p>
     *
     * @param key is the name of the element to retrieve.
     * @return the value of the element.
     * @throws NullPointerException if the key is null.
     * @throws NoSuchElementException if no element is identified by the given key.
     */
    public static Object get(final Record self,
                             final String key)
    {
        Preconditions.checkNotNull(key);

        final int index = self.keys().indexOf(key);

        if (index < 0)
        {
            throw new NoSuchElementException("Key: " + key);
        }

        final Object result = self.get(index);

        return result;
    }

    /**
     * This method determines whether the set of entries in one record
     * is a subset of the records in another record.
     *
     * @param subtype is the possible subset record.
     * @param supertype is the possible superset record.
     * @return true, if subtype.keys() is a subset of supertype.keys().
     */
    public static boolean isSubset(final Record subtype,
                                   final Record supertype)
    {
        Preconditions.checkNotNull(subtype);
        Preconditions.checkNotNull(supertype);

        final boolean answer = subtype.keys().containsAll(supertype.keys());

        return answer;
    }

    @Infer
    public static <T extends Record> T set(final T assignee,
                                           final Map<Object, Object> value)
    {
        Preconditions.checkNotNull(assignee, "The assignee cannot be null.");
        Preconditions.checkNotNull(value, "The value cannot be null.");

        T p = assignee;

        for (Entry<Object, Object> entry : value.entrySet())
        {
            if (entry.getKey() instanceof Integer)
            {
                p = (T) F.set(p, (Integer) entry.getKey(), entry.getValue());
            }
            else
            {
                p = (T) F.set(p, (String) entry.getKey(), entry.getValue());
            }
        }

        return p;
    }

    @Infer
    public static <T extends Record> T set(final T assignee,
                                           final Record value)
    {
        Preconditions.checkNotNull(assignee, "The assignee cannot be null.");
        Preconditions.checkNotNull(value, "The value cannot be null.");

        T p = assignee;

        for (String key : value.keys())
        {
            p = (T) F.set(p, key, value);
        }

        return p;
    }

    public static String json(final Object input)
    {
        final JsonEncoder encoder = new JsonEncoder();

        final String output = encoder.encode(input);

        return output;
    }

    public static Object json(final String input,
                              final Module module)
    {
        final JsonDecoder decoder = new JsonDecoder();

        final Object result = decoder.decode(module, input);

        return result;
    }

    public static void remember(final Object target,
                                final Object key,
                                final Object value)
    {
    }

    public static Object recall(final Object target,
                                final Object key)
    {
        return null;
    }

    public static Field findField(final Class owner,
                                  final String name)
    {
        Preconditions.checkNotNull(owner);
        Preconditions.checkNotNull(name);

        try
        {
            return owner.getField(name);
        }
        catch (Exception ex)
        {
            return null;
        }
    }

    public static Constructor findConstructor(final Class owner,
                                              final Iterable<Class> formals)
    {
        Preconditions.checkNotNull(owner);
        Preconditions.checkNotNull(formals);

        final Class[] params = (Class[]) F.immutable(formals).toArray(new Class[0]);

        try
        {
            return owner.getConstructor(params);
        }
        catch (Exception ex)
        {
            return null;
        }
    }

    public static Method findMethod(final Class owner,
                                    final String name,
                                    final Iterable<Class> formals)
    {
        Preconditions.checkNotNull(owner);
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(formals);

        final Class[] params = (Class[]) F.immutable(formals).toArray(new Class[0]);

        try
        {
            return owner.getMethod(name, params);
        }
        catch (Exception ex)
        {
            return null;
        }
    }

    /**
     * TODO: This must be a weak map.
     */
    private static final Map<Object, Map<Object, Object>> details = Maps.newIdentityHashMap();

    /**
     * This method associates a detail with a given exception.
     *
     * @param problem is the exception that the detail is in reference to.
     * @param key identifies the detail.
     * @param value is the content of the detail.
     * @throws NullPointerException if problem is null.
     * @throws NullPointerException if key is null.
     */
    public static void set(final Throwable problem,
                           final String key,
                           final Object value)
    {
        Preconditions.checkNotNull(problem);
        Preconditions.checkNotNull(key);

        if (details.containsKey(problem) == false)
        {
            details.put(problem, Maps.newHashMap());
        }

        final Map<Object, Object> map = details.get(problem);

        map.put(key, value);
    }

    /**
     * This method retrieves a detail that is associated with a given exception.
     *
     * @param problem is the exception that the detail is in reference to.
     * @param key identifies the detail.
     * @return the value of the detail, or null, if the detail does not exist.
     * @throws NullPointerException if problem is null.
     * @throws NullPointerException if key is null.
     */
    public static Object get(final Throwable problem,
                             final String key)
    {
        Preconditions.checkNotNull(problem);
        Preconditions.checkNotNull(key);

        if (details.containsKey(problem) == false)
        {
            return null;
        }

        final Map<Object, Object> map = details.get(problem);

        final Object result = map.get(key);

        return result;
    }

    /**
     * This method converts a value to a BigDecimal.
     *
     * @param value is the value to convert.
     * @return the value as a BigDecimal.
     * @throws NullPointerException if value is null.
     */
    public static BigDecimal big(final Object value)
    {
        Preconditions.checkNotNull(value);

        /**
         * Convert the value to a BigDecimal.
         */
        final BigDecimal result;

        if (value instanceof Character)
        {
            result = BigDecimal.valueOf((long) (Character) value);
        }
        else if (value instanceof Byte)
        {
            result = BigDecimal.valueOf((long) (Byte) value);
        }
        else if (value instanceof Short)
        {
            result = BigDecimal.valueOf((long) (Short) value);
        }
        else if (value instanceof Integer)
        {
            result = BigDecimal.valueOf((long) (Integer) value);
        }
        else if (value instanceof Long)
        {
            result = BigDecimal.valueOf((long) (Long) value);
        }
        else if (value instanceof Float)
        {
            result = BigDecimal.valueOf((double) (Float) value);
        }
        else if (value instanceof Double)
        {
            result = BigDecimal.valueOf((double) (Double) value);
        }
        else if (value instanceof BigInteger)
        {
            result = new BigDecimal((BigInteger) value);
        }
        else if (value instanceof BigDecimal)
        {
            result = (BigDecimal) value;
        }
        else
        {
            throw new IllegalArgumentException("The value cannot be converted to a BigDecimal.");
        }

        /**
         * Ensure that the scale of the result is correct.
         */
        final BigDecimal datum = Helpers.createBigDecimal(result);

        /**
         * Return the value as a BigDecimal.
         */
        return datum;
    }

    /**
     * This method creates a list of [index, element] pairs.
     *
     * @param iterable provides the elements.
     * @return the list of [index, element] pairs.
     * @throws NullPointerException if iterable is null.
     */
    public static List<List<Object>> enumerate(final Iterable<?> iterable)
    {
        Preconditions.checkNotNull(iterable);

        final List<List<Object>> pairs = Lists.newLinkedList();

        int index = 0;

        for (Object value : iterable)
        {
            final List<Object> pair = Lists.newArrayList(index++, value);

            pairs.add(Collections.unmodifiableList(pair));
        }

        return Collections.unmodifiableList(pairs);
    }

    public static AsyncTask async(final Action action)
    {
        return null;
    }

    public static boolean isAssignableTo(final Class T,
                                         final Class X)
    {
        /**
         * Case: X is T
         */
        if (Operators.equals(T, X))
        {
            return true;
        }

        /**
         * Case: X is null
         */
        if (X == null && Object.class.isAssignableFrom(T))
        {
            return true;
        }

        /**
         * From now on, T cannot be the null-type or the void-type.
         * Likewise, X cannot be the void-type.
         */
        if (T == null || T == void.class || X == void.class)
        {
            return false;
        }

        /**
         * Case: Boxing
         */
        final boolean boxing = (X == boolean.class && T == Boolean.class)
                               || (X == char.class && T == Character.class)
                               || (X == byte.class && T == Byte.class)
                               || (X == short.class && T == Short.class)
                               || (X == int.class && T == Integer.class)
                               || (X == long.class && T == Long.class)
                               || (X == float.class && T == Float.class)
                               || (X == double.class && T == Double.class)
                               || (X != null && X.isPrimitive() && T == Object.class)
                               || (X != null && X.isPrimitive() && T == Comparable.class)
                               || (X != null && X.isPrimitive() && X != boolean.class && X != char.class && T == Number.class);

        if (boxing)
        {
            return true;
        }


        /**
         * Case: Unboxing
         */
        final boolean unboxing = (X == Boolean.class && T == boolean.class)
                                 || (X == Character.class && T == char.class)
                                 || (X == Byte.class && T == byte.class)
                                 || (X == Short.class && T == short.class)
                                 || (X == Integer.class && T == int.class)
                                 || (X == Long.class && T == long.class)
                                 || (X == Float.class && T == float.class)
                                 || (X == Double.class && T == double.class);

        if (unboxing)
        {
            return true;
        }

        /**
         * Case: Coercion
         */
        final boolean coercion = (X == char.class && T == int.class)
                                 || (X == char.class && T == long.class)
                                 || (X == byte.class && T == short.class)
                                 || (X == byte.class && T == int.class)
                                 || (X == byte.class && T == long.class)
                                 || (X == short.class && T == int.class)
                                 || (X == short.class && T == long.class)
                                 || (X == int.class && T == long.class)
                                 || (X == float.class && T == double.class);

        if (coercion)
        {
            return true;
        }

        /**
         * Case: Subtyping
         */
        if (isSubtypeOf(X, T))
        {
            return true;
        }

        /**
         * Case: Not Assignable
         */
        return false;
    }

    public static boolean isSubtypeOf(final Class subtype,
                                      final Class supertype)
    {
        if (Operators.equals(subtype, supertype))
        {
            return true;
        }
        else if (subtype == null && Object.class.isAssignableFrom(supertype))
        {
            return true;
        }
        else if (subtype != null && supertype != null && supertype.isAssignableFrom(subtype))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}

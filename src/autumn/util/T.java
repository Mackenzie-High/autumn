package autumn.util;

import autumn.lang.Delegate;
import autumn.lang.Functor;
import autumn.lang.Module;
import autumn.lang.Record;
import autumn.lang.RecordEntry;
import autumn.lang.TypedFunctor;
import autumn.lang.annotations.Infer;
import autumn.lang.exceptions.CheckedException;
import autumn.lang.internals.ArgumentStack;
import autumn.util.functors.Action;
import autumn.util.functors.Function1;
import autumn.util.functors.Function2;
import autumn.util.functors.Predicate;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import high.mackenzie.autumn.util.json.JsonEncoder;
import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

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

    public static String set(final String string,
                             final int index,
                             final char value)
    {
        final char[] chars = string.toCharArray();

        chars[index] = value;

        return new String(chars);
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
     * <p>
     * The new object will have a scale of thirty-two.
     * </p>
     *
     * @param value is the value of the new BigDecimal.
     * @return the aforedescribed BigDecimal.
     */
    public static BigDecimal big(final double value)
    {
        final BigDecimal unscaled = BigDecimal.valueOf(value);

        final BigDecimal scaled = unscaled.setScale(32, RoundingMode.HALF_EVEN);

        return scaled;
    }

    /**
     * This method converts a number to a BigDecimal.
     *
     * <p>
     * The new object will have a scale of thirty-two.
     * </p>
     *
     * @param value is the value to convert.
     * @return the value as a big-decimal.
     * @throws NullPointerException if the value is null.
     * @throws IllegalArgumentException if the number could not be converted.
     */
    public static BigDecimal big(final Number value)
    {
        Preconditions.checkNotNull(value);

        final BigDecimal unscaled;

        if (value instanceof Byte)
        {
            unscaled = new BigDecimal(value.byteValue());
        }
        else if (value instanceof Short)
        {
            unscaled = new BigDecimal(value.shortValue());
        }
        else if (value instanceof Integer)
        {
            unscaled = new BigDecimal(value.intValue());
        }
        else if (value instanceof Long)
        {
            unscaled = new BigDecimal(value.longValue());
        }
        else if (value instanceof Float)
        {
            unscaled = new BigDecimal(value.floatValue());
        }
        else if (value instanceof Double)
        {
            unscaled = new BigDecimal(value.doubleValue());
        }
        else if (value instanceof BigInteger)
        {
            unscaled = new BigDecimal((BigInteger) value);
        }
        else if (value instanceof BigDecimal)
        {
            unscaled = (BigDecimal) value;
        }
        else
        {
            throw new IllegalArgumentException("Unrecognized Number: " + value);
        }

        final BigDecimal scaled = unscaled.setScale(32, RoundingMode.HALF_EVEN);

        return scaled;
    }

    /**
     * This method creates a new BigDecimal from a string value.
     *
     * <p>
     * The new object will have a scale of thirty-two.
     * </p>
     *
     * @param value is the value of the new BigDecimal.
     * @return the aforedescribed BigDecimal.
     */
    public static BigDecimal big(final String value)
    {
        final BigDecimal unscaled = new BigDecimal(value);

        final BigDecimal scaled = unscaled.setScale(32, RoundingMode.HALF_EVEN);

        return scaled;
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
     * A negative result indicates that the left operand is less than the right operand.
     * A positive result indicates that the left operand is greater than the right operand.
     * A zero result indicates that the operands are equals.
     * </p>
     *
     * </p>
     * This method considers null to be less than an object.
     * </p>
     *
     * @param left
     * @param right
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
            return ((Comparable) left).compareTo(right);
        }
    }

    /**
     * This method calculates the sum of a set of values.
     *
     * @param values are the values to sum.
     * @return the sum of the values.
     */
    public static BigDecimal sum(final Iterable<Number> values)
    {
        BigDecimal total = BigDecimal.ZERO;

        for (Number value : values)
        {
            total = total.add(T.big(value));
        }

        return total;
    }

    /**
     * This method calculates the average of a set of values.
     *
     * @param values are the values to average.
     * @return the average of the values.
     */
    public static BigDecimal average(final Iterable<Number> values)
    {
        BigDecimal total = BigDecimal.ZERO;

        long count = 0;

        for (Number value : values)
        {
            total = total.add(T.big(value));
            ++count;
        }

        final BigDecimal result = total.divide(new BigDecimal(count));

        return result;
    }

    /**
     * This method searches for the minimum value in a set of values.
     *
     * @param values are the values to search through.
     * @return the minimum value in the group.
     */
    public static Comparable minimum(final Iterable<Comparable> values)
    {
        Comparable least = null;

        for (Comparable value : values)
        {
            value = compare(value, least) < 0 ? value : least;
        }

        return least;
    }

    /**
     * This method searches for the maximum value in a set of values.
     *
     * @param values are the values to search through.
     * @return the maximum value in the group.
     */
    public static Comparable maximum(final Iterable<Comparable> values)
    {
        Comparable greatest = null;

        for (Comparable value : values)
        {
            value = compare(value, greatest) > 0 ? value : greatest;
        }

        return greatest;
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
     * This method creates an iterable whose iterator can iterate over all the files in a directory.
     *
     * <p>
     * If recur is true, sub-directories will be returned before their contents.
     * </p>
     *
     * @param root is the directory to iterate over.
     * @param recur is true, if the sub-directories transversed.
     * @return the aforedescribed iterator.
     * @throws IllegalArgumentException if root is not a directory.
     */
    public static Iterable<File> filesOf(final File root,
                                         final boolean recur)
    {
        // TODO: this is very buggy

        Preconditions.checkNotNull(root);
        Preconditions.checkArgument(root.isDirectory(), "Expected a Directory: " + root);

        // This stack stores fiels and directories that have not yet been returned.
        final Stack<File> stack = new Stack();

        // Push the files in the root directory, including the directory itself.
        for (File file : root.listFiles())
        {
            stack.push(file);
        }

        /**
         * Create the iterable, which basically performs a preorder depth-first transversal.
         */
        return new Iterable<File>()
        {
            @Override
            public Iterator<File> iterator()
            {
                return new Iterator<File>()
                {
                    @Override
                    public boolean hasNext()
                    {
                        return !stack.isEmpty();
                    }

                    @Override
                    public File next()
                    {
                        // This is required by the iterator interface.
                        if (stack.isEmpty())
                        {
                            throw new NoSuchElementException("Out of Files");
                        }

                        // Get the next file to return.
                        final File next = stack.pop();

                        if (recur && next.isDirectory())
                        {
                            // Push all the files that are directly contained in the sub-directory.
                            for (File child : next.listFiles())
                            {
                                stack.push(child);
                            }
                        }

                        return next;
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
     * This method applies a functor to each element of an iterable and collects the results.
     *
     * @param iterable is the iterable that provides input for the functor.
     * @param function is the functor(Object) : Object that processes the input.
     * @return a list containing the output of the functor.
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
//            output.add(T.apply(function, Collections.singletonList(element)));
        }

        return output;
    }

    public static List<Object> filter(final Iterable iterable,
                                      final Predicate function)
            throws Throwable
    {
        return null;
    }

    public static Object reduce(final Iterable iterable,
                                final Function2 functor)
            throws Throwable
    {
        return null;
    }

    public static List<Object> find(final Iterable iterable,
                                    final Predicate function,
                                    final int skip)
            throws Throwable
    {
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

    public static <T> List<T> scramble(final Iterable<T> iterable)
    {
        Preconditions.checkNotNull(iterable);

        return null;
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
     * This method creates a string that is another string repeated zero or more times.
     *
     * @param string is the string to repeat.
     * @param times is the number of times to repeat the string.
     * @return the aforedescribed string.
     */
    public static String repeat(final CharSequence string,
                                final int times)
    {
        Preconditions.checkNotNull(string, "string");
        Preconditions.checkArgument(times >= 0, "times");

        final StringBuilder result = new StringBuilder();

        for (int i = 0; i < times; i++)
        {
            result.append(string);
        }

        return string.toString();
    }

    /**
     * This method creates the string representation of a value.
     *
     * @param value is the value itself.
     * @return value.toString(), if the value is not null; otherwise return "null".
     */
    public static String str(final Object value)
    {
        return "" + value;
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
     * This method determines whether a string value is either null or the empty string.
     *
     * @param string is the value to check.
     * @return true, iff the argument is null or the empty string.
     */
    public static boolean isNullOrEmpty(final CharSequence string)
    {
        return string == null || string.length() == 0;
    }

    /**
     * This method finds the common prefix of two strings.
     *
     * @param string1 is the first string.
     * @param string2 is the second string.
     * @return the prefix that string1 and string2 share.
     */
    public static String commonPrefix(final CharSequence string1,
                                      final CharSequence string2)
    {
        Preconditions.checkNotNull(string1, "string1");
        Preconditions.checkNotNull(string1, "string2");

        final StringBuilder common = new StringBuilder();

        for (int i = 0; i < string1.length() && i < string2.length(); i++)
        {
            if (string1.charAt(i) == string2.charAt(i))
            {
                common.append(string1.charAt(i));
            }
            else
            {
                break;
            }
        }

        return common.toString();
    }

    /**
     * This method finds the common suffix of two strings.
     *
     * @param string1 is the first string.
     * @param string2 is the second string.
     * @return the suffix that string1 and string2 share.
     */
    public static String commonSuffix(final CharSequence string1,
                                      final CharSequence string2)
    {
        Preconditions.checkNotNull(string1, "string1");
        Preconditions.checkNotNull(string1, "string2");

        final StringBuilder common = new StringBuilder();

        for (int i = 1; string1.length() - i >= 0 && string2.length() - i >= 0; i++)
        {
            if (string1.charAt(string1.length() - i) == string2.charAt(string2.length() - i))
            {
                common.append(string1.charAt(i));
            }
            else
            {
                break;
            }
        }

        return common.reverse().toString();
    }

    /**
     * This method formats a string given an iterable containing the format arguments.
     *
     * <p>
     * The method is exactly like format(String, Object...) in java.lang.String,
     * except this method takes an iterable containing the arguments rather than an array.
     * </p>
     *
     * @param format is the format specifier.
     * @param arguments are the arguments to substitute into the format specifier.
     * @return the formatted string.
     * @throws NullPointerException if format is null.
     * @throws NullPointerException if arguments is null.
     * @see java.lang.String
     */
    public static String format(final String format,
                                final Iterable arguments)
    {
        Preconditions.checkNotNull(format);
        Preconditions.checkNotNull(arguments);

        return String.format(format, (Object[]) Lists.newArrayList(arguments).toArray());
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
     * This method creates a new thread and uses it to immediately run a given action.
     *
     * <p>
     * Equivalent:
     * <code>
     * Threads.newThread(action).start()
     * </code>
     * </p>
     *
     * @param action is the action that will run on the newly created thread.
     */
    public static void run(final Action action)
    {
//        Threads.newThread(action).start();
    }

    /**
     * This method invokes a given action based on a clock.
     *
     * <p>
     * You may want to use a Timer object instead, which is what this method uses internally.
     * </p>
     *
     * @param action is the action to invoke.
     * @param interval is the approximate number of milliseconds between each invocation.
     * @param count is the maximum number of times to invoke the action.
     */
    public static void ticker(final Action action,
                              final long interval,
                              final int count)
    {
        Preconditions.checkNotNull(action);
        Preconditions.checkNotNull(interval >= 0);
        Preconditions.checkNotNull(count >= 0);

        /**
         * This object will be used to count the number of times that the action has executed.
         */
        final AtomicInteger counter = new AtomicInteger();

        /**
         * This is essentially the clock that will fire the event.
         */
        final Timer timer = new Timer();

        /**
         * This is the event that will be fired by the clock.
         */
        final TimerTask task = new TimerTask()
        {
            @Override
            public void run()
            {
                if (counter.get() >= count)
                {
                    timer.cancel();
                    return;
                }
                else
                {
                    counter.incrementAndGet();
                    T.quietlyApply(action, Collections.EMPTY_LIST);
                }
            }
        };

        /**
         * Start the clock.
         */
        timer.scheduleAtFixedRate(task, 0, interval);
    }

    /**
     * This method applies a functor to a list of arguments without risking a checked exception.
     *
     * @param functor is the function object.
     * @param arguments are the arguments to pass to the functor.
     * @return the value returned by the functor.
     */
    public static Object quietlyApply(final Functor functor,
                                      final Iterable<?> arguments)
    {
        try
        {
            return T.apply(functor, arguments);
        }
        catch (Throwable ex)
        {
            T.rethrow(ex);
        }

        throw new RuntimeException("This should never happen.");
    }

    /**
     * This method creates a list describing the signature of a functor.
     *
     * @param functor is the functor itself.
     * @return a new immutable list: [return-type, parameter-type-1, ... , parameter-type-N]
     */
    public static List<Class> signatureOf(final TypedFunctor functor)
    {
        final List<Class> sig = Lists.newLinkedList();

        sig.add(functor.returnType());

        sig.addAll(functor.parameterTypes());

        return ImmutableList.copyOf(sig);
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
            p = (T) p.set(i, F.defaultValue(types.get(i)));
        }

        return p;
    }

    /**
     * This method creates an object that represents an entry in this record.
     *
     * @param index is the index of the element to assign the value to.
     * @return an object that describes the specified entry.
     * @throws IndexOutOfBoundsException if the index is out-of-bounds.
     */
    public static RecordEntry find(final Record self,
                                   final int index)
    {
        return new RecordEntry()
        {
            private String key = null;

            @Override
            public Record record()
            {
                return self;
            }

            @Override
            public String key()
            {
                if (key == null)
                {
                    key = self.keys().get(index);
                }

                return key;
            }

            @Override
            public Class type()
            {
                return self.types().get(index);
            }

            @Override
            public Object value()
            {
                return self.get(index);
            }

            @Override
            public Record clear()
            {
                return self.set(index, F.defaultValue(type()));
            }

            @Override
            public Record set(Object value)
            {
                return self.set(index, value);
            }

            @Override
            public String toString()
            {
                return "" + value();
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    public static RecordEntry find(final Record self,
                                   final String key)
    {
        Preconditions.checkNotNull(key);

        final int index = self.keys().indexOf(key);

        final RecordEntry result = find(self, index);

        return result;
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

    /**
     * This method determines whether the type of the entries in a record are all the same.
     *
     * @param record is the record itself.
     * @return true, if the static type of each entry is the same.
     */
    public static boolean isHomogeneous(final Record record)
    {
        Preconditions.checkNotNull(record);

        // This algorithm could be replaced in the future.
        final boolean answer = Sets.newHashSet(record.types()).size() <= 1;

        return answer;
    }

    /**
     * This method creates a map that maps the entry-names to entry-values.
     *
     * @param record is the record that will be converted to a map.
     * @return a map representation of the record.
     */
    public static Map<String, Object> entryMap(final Record record)
    {
        Preconditions.checkNotNull(record);

        final SortedMap<String, Object> map = Maps.newTreeMap();

        final Collection<String> keys = record.keys();

        final List<Object> values = record.values();

        int i = 0;

        for (String key : keys)
        {
            map.put(key, values.get(i++));
        }

        return Collections.unmodifiableSortedMap(map);
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
        return null;
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

    public static Annotation findAnnotation(final AnnotatedElement owner,
                                            final Class type)
    {
        return null;
    }

    public static Field findField(final Class owner,
                                  final String name)
    {
        return null;
    }

    public static Constructor findConstructor(final Class owner,
                                              final Iterable<Class> formals)
    {
        return null;
    }

    public static Method findMethod(final Class owner,
                                    final String name,
                                    final Iterable<Class> formals)
    {
        return null;
    }

    public static String extractAnnotationValue(final Annotation anno)
    {
        return null;
    }

    public static List extractAnnotationValues(final Annotation anno)
    {
        return null;
    }

    public static Delegate delegateTo(final Constructor ctor)
    {
        return null;
    }

    public static Delegate delegateTo(final Method method)
    {
        return null;
    }
}

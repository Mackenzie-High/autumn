package autumn.util;

import autumn.lang.TypedFunctor;
import autumn.lang.internals.ArgumentStack;
import autumn.util.functors.Predicate;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import java.util.List;

/**
 * This class provides static utility methods for creating commonly used predicates.
 *
 * @author Mackenzie High
 */
public final class Predicates
{
    private static abstract class PredicateImp
            implements TypedFunctor
    {
        public abstract boolean invoke(final Object argument);

        @Override
        public void apply(ArgumentStack stack)
                throws Throwable
        {
            final Object argument = stack.popO();

            stack.push(invoke(argument));
        }

        @Override
        public List<Class> parameterTypes()
        {
            return ImmutableList.<Class>of(Object.class);
        }

        @Override
        public Class returnType()
        {
            return boolean.class;
        }
    }

    /**
     * Sole Constructor.
     */
    private Predicates()
    {
        // Pass, because this is merely a static utility class.
    }

    /**
     * This method creates a predicate that negates another predicate.
     *
     * @param operand is the predicate to negate.
     * @return the newly created predicate.
     */
    public static Predicate not(final Predicate operand)
    {
        return null;
    }

    /**
     * TODO
     *
     * @param operands is the predicate to negate.
     * @return the newly created predicate.
     * @throws IllegalArgumentException operands must have at least two elements.
     */
    public static Predicate and(final Iterable<Predicate> operands)
    {
        return null;
    }

    /**
     * TODO
     *
     * @param operands is the predicate to negate.
     * @return the newly created predicate.
     * @throws IllegalArgumentException operands must have at least two elements.
     */
    public static Predicate or(final Iterable<Predicate> operands)
    {
        return null;
    }

    /**
     * This method creates a predicate that performs a logical-xor on two other predicates.
     *
     * @param left is the left-operand predicate.
     * @param right is the right-operand predicate.
     * @return the newly created predicate.
     */
    public static Predicate xor(final Predicate left,
                                final Predicate right)
    {
        return null;
    }

    /**
     * This method creates a new predicate that requires that all the elements in an iterable
     * satisfy another predicate.
     *
     * @param predicate is the predicate that each element in the iterable must satisfy.
     * @return
     */
    public static Predicate forall(final Predicate predicate)
    {
        return null;
    }

    /**
     * This method creates a new predicate that requires that at least one of the elements
     * in an iterable satisfies another predicate.
     *
     * @param predicate is the predicate that an element in the iterable must satisfy.
     * @return the newly created predicate.
     */
    public static Predicate forany(final Predicate predicate)
    {
        return null;
    }

    /**
     * This method creates a predicate that determines whether its argument equals a specific value.
     *
     * @param value is the specific value.
     * @return the newly created predicate.
     */
    public static Predicate eq(final Object value)
    {
        final PredicateImp inner = new PredicateImp()
        {
            @Override
            public boolean invoke(Object argument)
            {
                return Objects.equal(value, argument);
            }
        };

        return new Predicate(inner);
    }

    /**
     * This method creates a predicate that determines whether its argument does not equal
     * a specific value.
     *
     * @param value is the specific value.
     * @return the newly created predicate.
     */
    public static Predicate ne(final Object value)
    {
        return null;
    }

    /**
     * This method creates a predicate that determines whether its argument is greater-than
     * a specific value.
     *
     * @param value is the specific value.
     * @return the newly created predicate.
     */
    public static Predicate gt(final Comparable value)
    {
        return null;
    }

    /**
     * This method creates a predicate that determines whether its argument is greater-or-equal
     * to a specific value.
     *
     * @param value is the specific value.
     * @return the newly created predicate.
     */
    public static Predicate ge(final Comparable value)
    {
        return null;
    }

    /**
     * This method creates a predicate that determines whether its argument is less-than
     * a specific value.
     *
     * @param value is the specific value.
     * @return the newly created predicate.
     */
    public static Predicate lt(final Comparable value)
    {
        return null;
    }

    /**
     * This method creates a predicate that determines whether its argument is less-or-equal
     * a specific value.
     *
     * @param value is the specific value.
     * @return the newly created predicate.
     */
    public static Predicate le(final Comparable value)
    {
        return null;
    }

    /**
     * This method creates a predicate that determines whether its argument is between
     * two specific values.
     *
     * @param minimum is the lower bound of the argument.
     * @param maximum is the upper bound of the argument.
     * @param inclusive_minimum is true, iff the minimum is inclusive.
     * @param inclusive_maximum is true, iff the maximum is inclusive.
     * @return the newly created predicate.
     */
    public static Predicate between(final Comparable minimum,
                                    final Comparable maximum,
                                    final boolean inclusive_minimum,
                                    final boolean inclusive_maximum)
    {
        return null;
    }

    public static Predicate notBetween(final Comparable minimum,
                                       final Comparable maximum,
                                       final boolean inclusive_minimum,
                                       final boolean inclusive_maximum)
    {
        return null;
    }

    /**
     * This method creates a predicate that determines whether its argument's string
     * representation matches a regular expression.
     *
     * @param pattern is the regular expression pattern.
     * @return the newly created predicate.
     */
    public static Predicate stringMatches(final String pattern)
    {
        return null;
    }

    /**
     * This method creates a predicate that determines whether its argument's string
     * representation starts with a specific prefix.
     *
     * @param prefix is the possible prefix of the argument's string representation.
     * @return the newly created predicate.
     */
    public static Predicate stringStartsWith(final String prefix)
    {
        return null;
    }

    /**
     * This method creates a predicate that determines whether its argument's string
     * representation ends with a specific suffix.
     *
     * @param suffix is the possible suffix of the argument's string representation.
     * @return the newly created predicate.
     */
    public static Predicate stringEndsWith(final String suffix)
    {
        return null;
    }

    /**
     * This method creates a predicate that determines whether its argument's string
     * representation contains a specific substring.
     *
     * @param substring is the possible substring in the argument's string representation.
     * @return the newly created predicate.
     */
    public static Predicate stringContains(final String substring)
    {
        return null;
    }

    public static Predicate oneOf(final Iterable<Object> allowed)
    {
        return null;
    }

    public static Predicate noneOf(final Iterable<Object> forbidden)
    {
        return null;
    }

    public static Predicate instanceOf(final Class type)
    {
        return null;
    }

    public static Predicate assignableTo(final Class type)
    {
        return null;
    }
}

package execution;

import autumn.lang.Functor;
import autumn.lang.internals.AbstractFunctor;
import autumn.lang.internals.ArgumentStack;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Set;

/**
 * This class provides methods and fields for use during testing.
 *
 * @author mackenzie
 */
public final class Helpers
{
    /**
     * This class is used to test that Autumn exceptions only inherit public constructors.
     */
    public static class TestException
            extends Exception
    {
        public TestException(final String x)
        {
            // Pass
        }

        protected TestException(final int x)
        {
            // Pass
        }

        TestException(final short x)
        {
            // Pass
        }

        private TestException(final byte x)
        {
            // Pass
        }
    }

    /**
     * This method creates functor(n : int) that returns the n-th Fibonacci number (n less-than 12).
     *
     * @return a new Fibonacci functor.
     */
    public static Functor newFibonacciFunctor()
    {
        return new AbstractFunctor()
        {
            @Override
            public void apply(ArgumentStack stack)
                    throws Throwable
            {
                Preconditions.checkState(stack.size() == 1);

                final int n = stack.peekI();

                stack.pop();

                final int v = ImmutableList.of(1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144).get(n);

                stack.push(v);
            }
        };
    }

    /**
     * This method determines whether all the elements in an iterable are unique.
     *
     * @param elements are the elements which may be unique.
     * @return true, iff none of the elements occurs twice in the list.
     */
    public static boolean unique(final Collection<?> elements)
    {
        final Set<Object> set = Sets.newIdentityHashSet();
        set.addAll(elements);

        final boolean result = elements.size() == set.size();

        return result;
    }
}

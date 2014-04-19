/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package execution;

import autumn.lang.Functor;
import autumn.lang.internals.AbstractFunctor;
import autumn.lang.internals.ArgumentStack;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

/**
 * This class provides methods and fields for use during testing.
 *
 * @author mackenzie
 */
public final class Helpers
{
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
            public void invoke(ArgumentStack stack)
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
}

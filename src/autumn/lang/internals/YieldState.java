package autumn.lang.internals;

import com.google.common.base.Preconditions;
import high.mackenzie.autumn.resources.Finished;

/**
 * An instance of this class stores the state of a generator function between invocations.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public final class YieldState
{
    /**
     * This is the index of the yield-statement that last executed.
     * This index determines where the method will appear to reenter at.
     * A negate index indicates that no yield statement has executed yet.
     */
    private int index = -1;

    /**
     * This stack temporarily stores the internal state of the generator function.
     * This stack will be emptied, when the generator function is reentered.
     */
    private final ArgumentStack stack = new ArgumentStack();

    /**
     * This method retrieves the stack that is used to store the internal state of the function.
     *
     * @return the aforesaid stack.
     */
    public ArgumentStack stack()
    {
        return stack;
    }

    /**
     * This method gets the index of the yield-statement that last executed.
     *
     * @return the index of the last yield-statement, or a negative number,
     * if the generator function has not yielded at least once.
     */
    public int getIndex()
    {
        return index;
    }

    /**
     * This method sets the index of the yield-statement that last executed.
     *
     * <p>
     * Setting the index allows the generator function to know where to reenter.
     * </p>
     *
     * @param index is the index of the yield-statement that is yielding.
     */
    public void setIndex(final int index)
    {
        Preconditions.checkArgument(index >= 0);

        this.index = index;
    }

    /**
     * This method determines whether the generator function has yielded yet.
     *
     * @return true, if and only if, the generator function has yielded at least once.
     */
    public boolean isFirstCall()
    {
        return index < 0;
    }
}

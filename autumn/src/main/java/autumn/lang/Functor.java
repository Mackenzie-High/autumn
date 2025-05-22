package autumn.lang;

import autumn.lang.internals.ArgumentStack;
import com.mackenziehigh.autumn.resources.Finished;

/**
 * An instance of this interface is a function object.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public interface Functor
{
    /**
     * This method performs an invocation of this function object.
     *
     * <p>
     * Programmers should not call this method directly.
     * Instead, for convenience, call this method indirectly via (F::apply List).
     * </p>
     *
     * <p>
     * Calling Convention from top to bottom (n &gt;= 0):
     * <ol>
     * <li>argument[n]</li>
     * <li>argument[.]</li>
     * <li>argument[2]</li>
     * <li>argument[1]</li>
     * <li>argument[0]</li>
     * </ol>
     * </p>
     *
     * <p>
     * The argument stack will be cleared and then the result will be pushed onto it.
     * If the functor does not return a value, then the stack will remain empty.
     * </p>
     *
     * @param stack is a stack containing the arguments being passed to this functor.
     * @throws Throwable in order to propagate exceptions thrown by the functor.
     */
    public void apply(final ArgumentStack stack)
            throws Throwable;
}

package autumn.util.test;

import autumn.lang.Delegate;
import high.mackenzie.autumn.resources.Finished;

/**
 * An instance of this interface stores vital information regarding a single unit-test.
 *
 * <p>
 * Caution: This interface may have additional methods added in the future.
 * </p>
 *
 * @author Mackenzie High
 */
@Finished("2014/08/21")
public interface TestCase
{
    /**
     * This method retrieves the delegate that refers to the test function.
     *
     * @return the test function in functor form.
     */
    public Delegate function();

    /**
     * This method sets the name of this test-case.
     *
     * @param name is the new name of the test-case.
     * @throws NullPointerException if name is null.
     */
    public void name(final String name);

    /**
     * This method sets the description of this test-case.
     *
     * @param name is the new description of the test-case.
     */
    public void describe(final String text);

    /**
     * This method specifies the type of exception that the test-case will hopefully throw.
     * In other words, the test-case fails, if it does not throw this type of exception.
     *
     * @param expected is the type of exception that should be thrown by the test-case.
     */
    public void expect(final Class expected);
}

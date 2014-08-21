package autumn.util.test;

import autumn.lang.Module;
import high.mackenzie.autumn.resources.Finished;
import java.util.Set;

/**
 * An instance of this interface runs unit-tests on a group of modules.
 *
 * <p>
 * Caution: This interface may have additional methods added in the future.
 * </p>
 *
 * @author Mackenzie High
 */
@Finished("2014/08/21")
public interface Tester
{
    /**
     * This method adds a module to the set of modules that will be unit-tested.
     *
     * <p>
     * An instance of a module can be obtained via its special instance() method.
     * </p>
     *
     * @param module is singleton instance of the module.
     * @throws NullPointerException if module is null.
     */
    public void add(final Module module);

    /**
     * This method creates a view of the modules that will be unit-tested.
     *
     * @return an unmodifiable view of the set of modules that will be unit-tested.
     */
    public Set<Module> modules();

    /**
     * This method causes the previously specified modules to be unit-tested.
     *
     * @return the results of the unit-tests.
     */
    public TestResults run();
}

package autumn.lang;

import autumn.lang.internals.ArgumentStack;

/**
 * This is a supertype of all modules.
 *
 * <p>
 * Each module should provide an instance() method that returns a singleton instance of the module.
 * </p>
 *
 * @author Mackenzie High
 */
public interface Module
{
    /**
     * This method retrieves the object that describes this module.
     *
     * <p>
     * This is a constant-time operation.
     * </p>
     *
     * @return the singleton object that stores information regarding this module.
     */
    public ModuleInfo info();

    /**
     * This method invokes a function within this module.
     *
     * <p>
     * This is a low-level function needed to implement delegates.
     * </p>
     *
     * <p>
     * The key is the index of the function in the list returned by moduleFunctions().
     * </p>
     *
     * <p>
     * This method does nothing, iff the index does not refer to a real function.
     * </p>
     *
     * @param index identifies the function to invoke.
     * @param arguments contains the arguments to pass to the function.
     * @throws Throwable Any unhandled exception thrown by the function will be rethrown.
     */
    public void invoke(final int index,
                                     final ArgumentStack arguments)
            throws Throwable;
}

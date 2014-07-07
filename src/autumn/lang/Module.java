package autumn.lang;

import autumn.lang.internals.ArgumentStack;
import java.util.List;

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
     * This method returns the class objects that represent the annotations defined in this module.
     *
     * @return the aforedescribed immutable list.
     */
    public List<Class> moduleAnnotationDefinitions();

    /**
     * This method returns the class objects that represent the exceptions defined in this module.
     *
     * @return the aforedescribed immutable list.
     */
    public List<Class> moduleExceptionDefinitions();

    /**
     * This method returns the class objects that represent the enumerations defined in this module.
     *
     * @return the aforedescribed immutable list.
     */
    public List<Class> moduleEnumDefinitions();

    /**
     * This method returns the class objects that represent the designs defined in this module.
     *
     * @return the aforedescribed immutable list.
     */
    public List<Class> moduleDesignDefinitions();

    /**
     * This method returns a list containing a delegate for each function defined in this module.
     *
     * <p>
     * The delegates in the list are in the same order as the functions in the source code.
     * </p>
     *
     * @return the aforedescribed immutable list.
     */
    public List<Delegate> moduleFunctions();

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
    public void moduleInvokeFunction(final int index,
                                     final ArgumentStack arguments)
            throws Throwable;
}

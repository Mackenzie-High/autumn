package autumn.util.test;

import autumn.lang.Module;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Set;

/**
 * An instance of this class runs unit-tests on a group of modules.
 *
 * @author Mackenzie High
 */
public final class Tester
{
    private Set<Module> modules = Sets.newHashSet();

    /**
     * This method adds a module to the set of modules that will be unit-tested.
     *
     * <p>
     * The singleton instance of the module will be retrieved via reflection.
     * </p>
     *
     * @param module is the class-object that represents the type of the module.
     * @throws NullPointerException if module is null.
     * @throws IllegalArgumentException if an instance of the module could not be obtained.
     */
    public void add(final Class module)
    {
        Preconditions.checkNotNull(module);

        try
        {
            final Method method = module.getDeclaredMethod("instance");

            final Module instance = (Module) method.invoke(null);

            add(instance);
        }
        catch (NoSuchMethodException ex)
        {
            throw new IllegalArgumentException("No instance() method was found.", ex);
        }
        catch (IllegalAccessException ex)
        {
            throw new IllegalArgumentException("The instance() method is inaccessible.", ex);
        }
        catch (InvocationTargetException ex)
        {
            // This should never happen in reality.
            throw new IllegalArgumentException("Something went wrong in the instance() method.", ex);
        }
    }

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
    public void add(final Module module)
    {
        Preconditions.checkNotNull(module);

        modules.add(module);
    }

    /**
     * This method creates a view of the modules that will be unit-tested.
     *
     * @return an unmodifiable view of the set of modules that will be unit-tested.
     */
    public Set<Module> modules()
    {
        return Collections.unmodifiableSet(modules);
    }

    /**
     * This method causes the previously specified modules to be unit-tested.
     */
    public void run()
    {
    }
}

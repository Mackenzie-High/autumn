package autumn.util;

import autumn.lang.Delegate;
import autumn.lang.Module;
import autumn.lang.ModuleInfo;
import autumn.lang.annotations.Start;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.Set;

/**
 * This class provides static utility methods relating to modules.
 *
 * @author Mackenzie High
 */
public final class Modules
{
    /**
     * This method determines whether a given module contains the entry point of the program.
     *
     * @param module is the given module itself.
     * @return true, iff the module contains the entry point of the program.
     */
    public static boolean isStart(final Module module)
    {
        for (Delegate x : module.moduleInfo().functions())
        {
            final boolean test1 = x.method().isAnnotationPresent(Start.class);

            final boolean test2 = test1 && x.name().equals("main");

            final boolean test3 = test1 && x.parameterTypes().equals(Lists.newArrayList(String[].class));

            final boolean test4 = test1 && x.returnType().equals(void.class);

            if (test1 && test2 && test3 && test4)
            {
                return true;
            }
        }

        return false;
    }

    /**
     * This method creates a set containing all the types defined in a particular module.
     *
     * @param info describes the module.
     * @return the newly created immutable set.
     */
    public static Set<Class> types(final ModuleInfo info)
    {
        final Set<Class> result = Sets.newIdentityHashSet();

        result.addAll(info.annotations());
        result.addAll(info.designs());
        result.addAll(info.enums());
        result.addAll(info.exceptions());
        result.addAll(info.functors());
        result.addAll(info.structs());
        result.addAll(info.tuples());

        return Collections.unmodifiableSet(result);
    }
}

package high.mackenzie.autumn.lang.compiler.utils;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IReturnType;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * An instance of this interface is a list of setters, getters, or methods.
 *
 * @author Mackenzie High
 */
public abstract class MemberList
{
    /**
     * This method creates and returns a sorted list of method overrides.
     *
     * @return the aforesaid immutable list.
     */
    public abstract List<IMethod> list();

    /**
     * This map computes the bridge getters that are present in this list.
     *
     * @return a map that maps a bridge getter to the appropriate real getter.
     */
    public final Map<IMethod, IMethod> bridges()
    {
        final Map<IMethod, IMethod> map = Maps.newIdentityHashMap();

        final Queue<IMethod> queue = Lists.newLinkedList(list());

        final IMethod most_specific = queue.remove();

        final IReturnType most_specific_return = most_specific.getReturnType();

        for (IMethod next : queue)
        {
            if (next.getReturnType().equals(most_specific_return) == false)
            {
                map.put(next, most_specific);
            }
        }

        return ImmutableMap.copyOf(map);
    }

    /**
     * This method retrieves the most-specific method.
     *
     * @return the most-specific method.
     */
    public final IMethod mostSpecific()
    {
        final Queue<IMethod> queue = Lists.newLinkedList(list());

        final IMethod most_specific = queue.remove();

        return most_specific;
    }
}

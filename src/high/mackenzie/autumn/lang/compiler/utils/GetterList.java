package high.mackenzie.autumn.lang.compiler.utils;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IInterfaceType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IReturnType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IType;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * An instance of this class sorts the overrides of a getter.
 *
 * <p>
 * Autumn allows covariant properties. <br>
 * The most specific getter is the getter with the most specific return-type. <br>
 * </p>
 *
 * @author Mackenzie High
 */
public final class GetterList
{
    private final IInterfaceType type;

    private final String property;

    private final ImmutableList<IMethod> getters;

    /**
     * Sole Constructor.
     *
     * @param type is the type of the design.
     * @param property is the name of the property that the getter gets.
     * @param getters are the potential overrides of the getter.
     */
    public GetterList(final IInterfaceType type,
                      final String property,
                      final List<IMethod> getters)
    {
        Preconditions.checkNotNull(type);
        Preconditions.checkNotNull(property);
        Preconditions.checkNotNull(getters);

        this.type = type;
        this.property = property;
        this.getters = ImmutableList.copyOf(getters);
    }

    /**
     * This method creates and returns a sorted list of getter overrides.
     *
     * @return the aforesaid immutable list.
     */
    public List<IMethod> list()
    {
        final TopoSorter<IMethod> sorter = new TopoSorter<IMethod>()
        {
            @Override
            public boolean isLess(final IMethod left,
                                  final IMethod right)
            {
                assert type.isSubtypeOf(left.getOwner());
                assert type.isSubtypeOf(right.getOwner());
                assert property.equals(left.getName());
                assert property.equals(right.getName());
                assert left.getFormalParameters().isEmpty();
                assert right.getFormalParameters().isEmpty();

                final IType left_type = left.getReturnType();
                final IType right_type = right.getReturnType();

                return left_type.isSubtypeOf(right_type);
            }
        };

        sorter.addAll(getters);

        return ImmutableList.copyOf(sorter.elements());
    }

    /**
     * This map computes the bridge getters that are present in this list.
     *
     * @return a map that maps a bridge getter to the appropriate real getter.
     */
    public Map<IMethod, IMethod> bridgeGetters()
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
}

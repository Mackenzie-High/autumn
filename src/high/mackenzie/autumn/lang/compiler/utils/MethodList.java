/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * An instance of this class provides a sorted list of method overrides.
 *
 * <p>
 * The elements are sorted from most specific to least specific. <br>
 * Herein, a method is more specific, iff its return-type is more specific. <br>
 * </p>
 *
 * @author Mackenzie High
 */
public final class MethodList
{
    private final IInterfaceType type;

    private final String description;

    private final ImmutableList<IMethod> methods;

    /**
     * Sole Constructor.
     *
     * @param type is the type of the design.
     * @param description is the name plus parameter-list descriptor of the method.
     * @param methods are the overrides of the method.
     */
    public MethodList(final IInterfaceType type,
                      final String description,
                      final List<IMethod> methods)
    {
        Preconditions.checkNotNull(type);
        Preconditions.checkNotNull(description);
        Preconditions.checkNotNull(methods);

        this.type = type;
        this.description = description;
        this.methods = ImmutableList.copyOf(methods);
    }

    /**
     * This method creates and returns a sorted list of method overrides.
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
                assert description.equals(left.getNamePlusParameterListDescriptor());
                assert description.equals(right.getNamePlusParameterListDescriptor());
                assert type.isSubtypeOf(left.getOwner());
                assert type.isSubtypeOf(right.getOwner());

                final IType left_type = left.getReturnType();
                final IType right_type = right.getReturnType();

                return left_type.isSubtypeOf(right_type);
            }
        };

        sorter.addAll(methods);

        return ImmutableList.copyOf(sorter.elements());
    }

    /**
     * This map computes the bridge methods that are present in this list.
     *
     * @return a map that maps a bridge method to the appropriate real method.
     */
    public Map<IMethod, IMethod> bridgeMethods()
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

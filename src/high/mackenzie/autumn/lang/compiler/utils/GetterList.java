package high.mackenzie.autumn.lang.compiler.utils;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IInterfaceType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IType;
import java.util.List;

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
        extends MemberList
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
    @Override
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
}

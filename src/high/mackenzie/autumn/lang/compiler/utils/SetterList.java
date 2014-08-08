package high.mackenzie.autumn.lang.compiler.utils;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IInterfaceType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IType;
import java.util.List;

/**
 * An instance of this class sorts the overrides of a setter.
 *
 * <p>
 * Autumn allows covariant properties. <br>
 * The most specific setter is the setter with the most specific formal parameter. <br>
 * </p>
 *
 * @author Mackenzie High
 */
public final class SetterList
        extends MemberList
{
    private final IInterfaceType type;

    private final String property;

    private final ImmutableList<IMethod> setters;

    /**
     * Sole Constructor.
     *
     * @param type is the type that will be returned by the setter.
     * @param property is the name of the property that the setter sets.
     * @param setters are the potential overrides of the setter.
     */
    public SetterList(final IInterfaceType type,
                      final String property,
                      final List<IMethod> setters)
    {
        Preconditions.checkNotNull(type);
        Preconditions.checkNotNull(property);
        Preconditions.checkNotNull(setters);

        this.type = type;
        this.property = property;
        this.setters = ImmutableList.copyOf(setters);
    }

    /**
     * This method creates and returns a sorted list of setter overrides.
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
                assert left.getReturnType().equals(left.getOwner());
                assert right.getReturnType().equals(right.getOwner());
                assert property.equals(left.getName());
                assert property.equals(right.getName());
                assert left.getFormalParameters().size() == 1;
                assert right.getFormalParameters().size() == 1;
                assert type.isSubtypeOf(left.getReturnType());
                assert type.isSubtypeOf(right.getReturnType());

                final IType left_type = left.getFormalParameters().get(0).getType();
                final IType right_type = right.getFormalParameters().get(0).getType();

                return left_type.isSubtypeOf(right_type);
            }
        };

        sorter.addAll(setters);

        final List<IMethod> result = ImmutableList.copyOf(sorter.elements());

        return result;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.utils;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IInterfaceType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IType;
import java.util.List;

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
        extends MemberList
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
    @Override
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
}

package high.mackenzie.autumn.lang.compiler.utils;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.List;

/**
 * An instance of this class sorts overrides of a struct element.
 *
 * <p>
 * Autumn allows covariant elements in structs via subtyping. <br>
 * </p>
 *
 * @author Mackenzie High
 */
public final class StructElementList
{
    public final String name;

    public final List<StructElement> sorted;

    /**
     * Sole Constructor.
     *
     * @param name is the name of the element.
     */
    public StructElementList(final String name,
                             final Iterable<StructElement> unsorted)
    {
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(unsorted);

        this.name = name;
        this.sorted = sort(unsorted);
    }

    /**
     * This method creates and returns a sorted list of element overrides.
     *
     * <p>
     * The list is sorted based on the owners of the declarations.
     * </p>
     *
     * @return the aforesaid immutable list.
     */
    private List<StructElement> sort(final Iterable<StructElement> unsorted)
    {
        final TopoSorter<StructElement> sorter = new TopoSorter<StructElement>()
        {
            @Override
            public boolean isLess(final StructElement left,
                                  final StructElement right)
            {
                return left.owner.isSubtypeOf(right.owner);
            }
        };

        sorter.addAll(unsorted);

        final List<StructElement> result = ImmutableList.copyOf(sorter.elements());

        return result;
    }
}

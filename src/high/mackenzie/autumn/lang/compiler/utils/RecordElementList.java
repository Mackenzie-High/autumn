package high.mackenzie.autumn.lang.compiler.utils;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * An instance of this class sorts overrides of a record element.
 *
 * <p>
 * Autumn allows covariant elements in records via subtyping. <br>
 * </p>
 *
 * @author Mackenzie High
 */
public final class RecordElementList
{
    public final String name;

    public final List<RecordElement> sorted;

    /**
     * Sole Constructor.
     *
     * @param name is the name of the element.
     */
    RecordElementList(final String name,
                      final Iterable<RecordElement> unsorted)
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
    private List<RecordElement> sort(final Iterable<RecordElement> unsorted)
    {
        final TopoSorter<RecordElement> sorter = new TopoSorter<RecordElement>()
        {
            @Override
            public boolean isLess(final RecordElement left,
                                  final RecordElement right)
            {
                return left.owner.isSubtypeOf(right.owner);
            }
        };

        sorter.addAll(unsorted);

        final List<RecordElement> result = ImmutableList.copyOf(sorter.elements());

        return result;
    }

    /**
     * This method detects covariance violations.
     *
     * <p>
     * Imagine two records:
     * <ul>
     * <li>design Parent (name : String, children : List)</li>
     * <li>struct Person (name : String, children : Person[]) is Parent</li>
     * <ul>
     * <br>
     * In this situation, the type of element <i>children</i> is problematic.
     * A <i>Person[]</i> is not a subtype of <i>List</i>; therefore, covariance is violated.
     * </p>
     *
     * @return an immutable list of any discovered violations (hopefully empty).
     */
    public List<CovarianceViolation> detectCovarianceViolations()
    {
        final List<CovarianceViolation> violations = Lists.newLinkedList();

        final LinkedList<RecordElement> stack = Lists.newLinkedList(sorted);

        while (stack.isEmpty() == false)
        {
            final RecordElement overridder = stack.pop();

            for (RecordElement overridden : stack)
            {
                final boolean check1 = overridder.owner.isSubtypeOf(overridden.owner);

                final boolean check2 = check1 && overridder.value.isSubtypeOf(overridden.value);

                if (check2 == false)
                {
                    violations.add(new CovarianceViolation(overridden, overridder));
                }
            }
        }

        return Collections.unmodifiableList(violations);
    }
}

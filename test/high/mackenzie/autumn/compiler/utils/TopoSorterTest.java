/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.compiler.utils;

import high.mackenzie.autumn.lang.compiler.utils.TopoSorter;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author mackenzie
 */
public final class TopoSorterTest
{
    /**
     * Test: 20130821062502721163
     *
     * <p>
     * Case: Test Everything
     * </p>
     */
    @Test
    public void test20130821062502721163()
    {
        System.out.println("Test: 20130821062502721163");

        final List<Integer> numbers = Lists.newArrayList(1, 1, 2, 3, 3, 3, 4, 5, 5);

        for (List<Integer> permutation : Collections2.permutations(numbers))
        {
            tsort(permutation);
        }
    }

    private void tsort(final List<Integer> elements)
    {
        final TopoSorter<Integer> sorter = new TopoSorter<Integer>()
        {
            @Override
            public boolean isLess(final Integer left,
                                  final Integer right)
            {
                return left < right;
            }
        };

        sorter.addAll(elements);

        final List<Integer> sorted = Lists.newArrayList(elements);

        Collections.sort(sorted);

        assertEquals(sorted, sorter.elements());
    }
}

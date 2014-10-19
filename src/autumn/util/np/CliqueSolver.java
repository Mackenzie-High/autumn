package autumn.util.np;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * An instance of this class can be used to solve <b>small</b> clique problems regarding objects.
 *
 * <p>
 * This class is <b>not</b> intended to replace dedicated sat-solver frameworks.
 * </p>
 *
 * @author Mackenzie High
 */
public final class CliqueSolver
{
    private final Set<Object> community = Sets.newHashSet();

    private final Map<Object, Integer> allocator = Maps.newHashMap();

    private final ThreeSat expression = new ThreeSat();

    private int find(final Object member)
    {
        if (allocator.containsKey(member) == false)
        {
            allocator.put(member, expression.addVariable(member));
        }

        return allocator.get(member);
    }

    /**
     * This method prevents two objects from being selected simultaneously.
     *
     * <p>
     * The members may both belong to the same clique.
     * </p>
     *
     * @param member1 is a member of a clique.
     * @param member2 is a member of a clique.
     */
    public void addConflict(final Object member1,
                            final Object member2)
    {
        community.add(member1);
        community.add(member2);

        final int var1 = find(member1);
        final int var2 = find(member2);

        expression.nand(var1, var2);
    }

    /**
     * This method creates a new clique.
     *
     * <p>
     * The new clique allows multiple of its members to be selected at the same time.
     * </p>
     *
     * @param members are the members of the new clique.
     */
    public void addClique(final Iterable<?> members)
    {
        final LinkedList<Object> stack = Lists.newLinkedList(Sets.newHashSet(members));

        community.addAll(stack);

        if (stack.isEmpty())
        {
            return;
        }

        while (stack.size() > 1)
        {
            final Object operand1 = stack.pop();

            final Object operand2 = stack.pop();

            final Object result = new Object();

            expression.or(find(result), find(operand1), find(operand2));

            stack.push(result);
        }

        final Object operand = stack.pop();

        expression.on(find(operand));
    }

    /**
     * This method creates a new clique.
     *
     * @param nodes are the members of the clique.
     * @param limit is true, iff only one member of this clique can be selected at a time.
     */
    public void addClique(final Iterable<?> members,
                          final boolean limit)
    {
    }

    /**
     * This method attempts to solve the clique problem.
     *
     * @param timeout is the maximum numbers of milliseconds to spend looking for a solution.
     * @return a set of members that can be simultaneously selected from the cliques.
     */
    public Set<Object> solve(long timeout)
    {
        final Set<Object> result = Sets.newHashSet();

        final SatResult proof = expression.solve(timeout);

        if (proof.isSatisfiable())
        {
            for (Entry<Object, Boolean> entry : proof.proof().entrySet())
            {
                if (community.contains(entry.getKey()) && entry.getValue() == true)
                {
                    result.add(entry.getKey());
                }
            }
        }

        return result;
    }

    public static void main(final String[] args)
    {
        final CliqueSolver solver = new CliqueSolver();

        solver.addClique(Lists.newArrayList("Avril", "Emma"));
        solver.addClique(Lists.newArrayList("Kate", "Erin", "Autumn"));

        solver.addConflict("Avril", "Erin");
        solver.addConflict("Emma", "Kate");

        final Set<Object> result = solver.solve(Integer.MAX_VALUE);

        System.out.println(result);
    }
}

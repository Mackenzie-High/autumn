package autumn.util.np;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * An instance of this class is the output of a 2-SAT or 3-SAT solver.
 *
 * @author Mackenzie High
 */
public final class SatResult
{
    private final Map<String, Boolean> assignments;

    /**
     * Sole Constructor.
     *
     * @param assignments are the assignments to the variables of the expression,
     * which will produce a satisfiable result; or null,
     * if the expression is unsatisfiable.
     */
    public SatResult(final Map<String, Boolean> assignments)
    {
        this.assignments = assignments == null
                ? null
                : Collections.unmodifiableSortedMap(new TreeMap(assignments));
    }

    /**
     * This method determines whether this result indicates satisfiability.
     *
     * @return true, if the sat-solver determined that the expression is satisfiable.
     */
    public boolean isSatisfiable()
    {
        return assignments != null;
    }

    /**
     * This method retrieves the proof of satisfiability.
     *
     * @return the proof, if the expression is satisfiable;
     * or null, if the expression is unsatisfiable.
     */
    public Map<String, Boolean> proof()
    {
        return assignments;
    }

    @Override
    public String toString()
    {
        if (!isSatisfiable())
        {
            return "Unsatisfiable";
        }

        final String NEWLINE = System.lineSeparator();

        final StringBuilder result = new StringBuilder();

        for (Entry<String, Boolean> entry : assignments.entrySet())
        {
            final String prefix = entry.getValue() ? "T" : "F";

            result.append(prefix).append(" = ").append(entry.getKey()).append(NEWLINE);
        }

        return result.toString().trim();
    }
}

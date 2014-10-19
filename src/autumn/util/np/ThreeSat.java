package autumn.util.np;

import autumn.util.exceptions.TimeOutException;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * An instance of this class can be used to solve <b>small</b> 3-SAT expressions.
 *
 * <p>
 * This class is <b>not</b> intended to replace dedicated sat-solver frameworks.
 * </p>
 *
 * @author Mackenzie High
 */
public final class ThreeSat
{
    /**
     * An instance of this class represents a single 3-CNF clause.
     */
    private final class Clause
    {
        /**
         * This is the signed index of the first variable.
         */
        public int X;

        /**
         * This is the signed index of the second variable.
         */
        public int Y;

        /**
         * This is the signed index of the third variable.
         */
        public int Z;

        /**
         * Sole Constructor.
         *
         * @param x is the first literal.
         * @param y is the second literal.
         * @param z is the third literal.
         */
        public Clause(final int x,
                      final int y,
                      final int z)
        {
            this.X = x;
            this.Y = y;
            this.Z = z;
        }

        /**
         * This method evaluates this clause.
         *
         * @param variables are assignments to the variables in the overall expression.
         * @return true, iff this clause evaluates to true.
         */
        public boolean evaluate(final boolean[] variables)
        {
            final boolean x = (X < 0) ? !variables[-X - 1] : variables[X - 1];
            final boolean y = (Y < 0) ? !variables[-Y - 1] : variables[Y - 1];
            final boolean z = (Z < 0) ? !variables[-Z - 1] : variables[Z - 1];

            return x || y || z;
        }
    }

    /**
     * An instance of this class is used to store information during a solving attempt.
     */
    private final class Attempt
    {
        /**
         * These are the clauses in the expression.
         */
        public final Clause[] clauses;

        /**
         * This is the system-time when the solving attempt must come to an end.
         */
        public final long apocalypse;

        /**
         * This array is used by the random solver.
         */
        public final boolean[] random_values;

        /**
         * Sole Constructor.
         *
         * @param timeout is the timeout of the solving attempt.
         */
        public Attempt(final long timeout)
        {
            final BigInteger end_time = BigInteger.valueOf(System.currentTimeMillis())
                    .add(BigInteger.valueOf(timeout));

            this.apocalypse = end_time.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) > 0
                    ? Long.MAX_VALUE
                    : end_time.longValue();

            this.random_values = new boolean[variables.size()];

            /**
             * Convert the array-list to an array for added speed.
             */
            this.clauses = new Clause[expression.size()];

            for (int i = 0; i < clauses.length; i++)
            {
                this.clauses[i] = expression.get(i);
            }
        }

        /**
         * This method is invoked by solver functions in order to enforce the timeout.
         */
        public void enforceTimeOut()
        {
            if (System.currentTimeMillis() >= apocalypse)
            {
                throw new TimeOutException();
            }
        }
    }

    private final Map<Object, Integer> variables = Maps.newHashMap();

    private final Map<Integer, Object> indexes = Maps.newTreeMap();

    private final List<Clause> expression = Lists.newArrayList();

    private final Random random = new Random(System.currentTimeMillis());

    /**
     * This method adds a variable to this 3-SAT expression.
     *
     * @param name is an object that represents the variable (usually its name).
     * @return the index of the new variable.
     */
    public int addVariable(final Object name)
    {
        if (variables.containsKey(name) == false)
        {
            final int index = variables.size() + 1;

            variables.put(name, index);
            indexes.put(index, name);
        }

        return variables.get(name);
    }

    private void addClause(final int x,
                           final int y)
    {
        expression.add(new Clause(x, x, y));
    }

    private void addClause(final int x,
                           final int y,
                           final int z)
    {
        expression.add(new Clause(x, y, z));
    }

    /**
     * Adds Constraint: R = TRUE
     *
     * @param R is the index of the output-variable.
     */
    public void on(final int R)
    {
        // Clause: (R | R | R)
        expression.add(new Clause(R, R, R));
    }

    /**
     * Adds Constraint: R = FALSE
     *
     * @param R is the index of the output-variable.
     */
    public void off(final int R)
    {
        // Clause: (!R | !R | !R)
        expression.add(new Clause(-R, -R, -R));
    }

    /**
     * Adds Constraint: R = X and Y
     *
     * @param R is the index of the output-variable.
     * @param X is the index of the left-operand variable.
     * @param Y is the index of the right-operand variable.
     */
    public void and(final int R,
                    final int X,
                    final int Y)
    {
        addClause(-R, X);
        addClause(-R, Y);
        addClause(R, -X, -Y);
    }

    /**
     * Adds Constraint: R = X or Y
     *
     * @param R is the index of the output-variable.
     * @param X is the index of the left-operand variable.
     * @param Y is the index of the right-operand variable.
     */
    public void or(final int R,
                   final int X,
                   final int Y)
    {
        addClause(-R, X, Y);
        addClause(R, -X);
        addClause(R, -Y);
    }

    /**
     * Adds Constraint: R = X xor Y
     *
     * @param R is the index of the output-variable.
     * @param X is the index of the left-operand variable.
     * @param Y is the index of the right-operand variable.
     */
    public void xor(final int R,
                    final int X,
                    final int Y)
    {
    }

    /**
     * Adds Constraint: R = X nor Y
     *
     * @param R is the index of the output-variable.
     * @param X is the index of the left-operand variable.
     * @param Y is the index of the right-operand variable.
     */
    public void nor(final int R,
                    final int X,
                    final int Y)
    {
    }

    /**
     * Adds Constraint: X nand Y
     *
     * @param X is the index of the left-operand variable.
     * @param Y is the index of the right-operand variable.
     */
    public void nand(final int X,
                     final int Y)
    {
        addClause(-X, -Y);
    }

    /**
     * Adds Constraint: R = X nand Y
     *
     * @param R is the index of the output-variable.
     * @param X is the index of the left-operand variable.
     * @param Y is the index of the right-operand variable.
     */
    public void nand(final int R,
                     final int X,
                     final int Y)
    {
    }

    /**
     * Adds Constraint: R = X implies Y
     *
     * @param R is the index of the output-variable.
     * @param X is the index of the left-operand variable.
     * @param Y is the index of the right-operand variable.
     */
    public void implies(final int R,
                        final int X,
                        final int Y)
    {
    }

    /**
     * Adds Constraint: R = X iff Y
     *
     * @param R is the index of the output-variable.
     * @param X is the index of the left-operand variable.
     * @param Y is the index of the right-operand variable.
     */
    public void iff(final int R,
                    final int X,
                    final int Y)
    {
    }

    /**
     * Adds Constraint: R = X iff Y
     *
     * @param R is the index of the output-variable.
     * @param X is the index of the left-operand variable.
     * @param Y is the index of the right-operand variable.
     */
    public void equals(final int R,
                       final int X,
                       final int Y)
    {
    }

    /**
     * This method attempts to solve the 3-SAT expression.
     *
     * <p>
     * This algorithm is <b>not</b> guaranteed to stop at the timeout.
     * </p>
     *
     * @param timeout is the maximum number of milliseconds the algorithm should run.
     * @return a proof of satisfiability, an indicator of unsatisfiability.
     * @throws autumn.util.exceptions.TimeOutException if the timeout is reached.
     */
    public SatResult solve(final long timeout)
    {
        Preconditions.checkArgument(timeout > 0, "timeout <= 0");

        final Attempt attempt = new Attempt(timeout);

        SatResult result = null;

        while (true)
        {
            // Attempt to solve, by random guessing.
            result = result != null ? result : random(attempt);

            // TODO: Attempt to solve, by depth-first search.

            if (result != null)
            {
                return result;
            }
        }
    }

    /**
     * This method evaluates a set of clauses given assignments to the variables therein.
     *
     * @param clauses are the clauses to evaluate.
     * @param values are the assignments to the variables.
     * @return true, iff all the clauses evaluate to true.
     */
    private boolean evaluate(final Clause[] clauses,
                             final boolean[] values)
    {
        for (int i = 0; i < clauses.length; i++)
        {
            if (!clauses[i].evaluate(values))
            {
                return false;
            }
        }

        return true;
    }

    /**
     * This method creates a map, so that a SatResult can be created.
     *
     * @param values are assignments to the variables in the expression.
     * @return a map that maps the name of a variable to its assigned value.
     */
    private Map<Object, Boolean> proof(final boolean[] values)
    {
        final Map<Object, Boolean> proof = Maps.newHashMap();

        for (Integer index : indexes.keySet())
        {
            final Object variable = indexes.get(index);

            proof.put(variable, values[index - 1]);
        }

        return proof;
    }

    private SatResult check(final Attempt attempt,
                            final boolean[] values)
    {
        if (evaluate(attempt.clauses, values))
        {
            return new SatResult(proof(values));
        }
        else
        {
            return null;
        }
    }

    /**
     * This method determines whether a single random assignment to the variables
     * will satisfy the expression.
     *
     * @param attempt is the solver attempt.
     * @return a solution, if one is found; otherwise, return null.
     */
    private SatResult random(final Attempt attempt)
    {
        attempt.enforceTimeOut();

        final boolean[] assignments = attempt.random_values;

        for (int i = 0; i < attempt.random_values.length; i++)
        {
            assignments[i] = random.nextBoolean();
        }

        return check(attempt, assignments);
    }

    public static void main(String[] args)
    {
        final ThreeSat sat = new ThreeSat();

        final int A = sat.addVariable("A");
        final int B = sat.addVariable("B");
        final int C = sat.addVariable("C");
        final int D = sat.addVariable("D");
        final int E = sat.addVariable("E");
        final int F = sat.addVariable("F");

        sat.on(A);
        sat.on(B);
        sat.on(C);
        sat.on(D);
        sat.on(E);
        sat.off(F);

        final SatResult result = sat.solve(Long.MAX_VALUE);

        System.out.println(result);
    }
}

package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ast.nodes.Variable;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IExpressionType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;
import high.mackenzie.autumn.lang.compiler.utils.Utils;
import java.util.Map;
import java.util.Set;

/**
 * An instance of this class provides the logic necessary to allocate local variables.
 *
 * <p>
 * <b>Note:</b> Parameters must be declared *before* declaring any other local-variables.
 * </p>
 *
 * @author Mackenzie High
 */
public final class VariableAllocator
{
    /**
     * These are the different types of local variables.
     */
    private enum VarTypes
    {
        /**
         * This constant indicates that a local-variable is a formal-parameter.
         */
        PARAM,
        /**
         * This constant indicates that a local-variable is a mutable stack-allocated variable.
         */
        VAR,
        /**
         * This constant indicates that a local-variable is a readonly stack-allocated variable.
         */
        VAL,
        /**
         * This constant indicates that a local-variable is a mutable stack-allocated temporary.
         */
        TEMP,
    }

    /**
     * An instance of this class describes a single local variable.
     */
    private static final class VarInfo
    {
        /**
         * This is the number of scopes that have a lock on this variable.
         * This is used to implement nested scoping.
         * When a nested scope is entered, it obtains a lock on each variable in the outer scope.
         * When a variable is initially allocated, it has one lock.
         * This is because the declaring scope has a lock on the variable.
         * When a scope is exited, it releases its locks.
         * As a a result, the scope of a variable will eventually hit zero.
         * When that happens, attempts to grab a lock are simply ignored.
         * Otherwise, strange things would happen.
         * Overall, this is a very simply way to implement nested scoping.
         */
        public int locks;

        /**
         * This field indicates whether this local variable is a parameter, etc.
         */
        public final VarTypes form;

        /**
         * This field indicates the name of this local variable.
         */
        public final String name;

        /**
         * This field indicates the static-type of this local variable.
         */
        public final IVariableType type;

        /**
         * This field indicates where this local variable will be stored in the activation-record.
         */
        public final int address;

        /**
         * Sole Constructor.
         */
        public VarInfo(final VarTypes form,
                       final String name,
                       final IVariableType type,
                       final int address)
        {
            this.form = form;
            this.name = name;
            this.type = type;
            this.address = address;
            this.locks = 1; // One lock, because of the declaration scope.
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString()
        {
            return "address "
                   + Strings.padStart("" + address, 5, '0')
                   + " "
                   + form.name().toLowerCase()
                   + " "
                   + name
                   + " : "
                   + type.getDescriptor();
        }
    }

    /**
     * This map maps the names of variables to their types.
     * This map is used to assist in testing of the language.
     */
    public static final Map<String, IType> lang_testing_info = Maps.newTreeMap();

    /**
     * This is the current level of scope nesting.
     * Each time a scope is entered, this is incremented.
     * Each time a scope is exited, this is decremented.
     */
    private int level = 0;

    /**
     * This is a counter used to allocate local-variable addresses within an activation-record.
     */
    private int addresses = 0;

    /**
     * This flag indicates whether any non-parameter local variables were declared yet.
     */
    private boolean non_parameters_declared = false;

    /**
     * These are the allocated variables.
     */
    private final Map<String, VarInfo> variables = Maps.newTreeMap();

    /**
     * This flag will become true, when variable declaration is complete.
     */
    private boolean all_declared = false;

    /**
     * Sole Constructor.
     *
     * @param initial_address indicates the address of the first allocated variable.
     * @throws IllegalArgumentException if <code>initial_address &lt; 0</code>.
     */
    public VariableAllocator(final int initial_address)
    {
        Preconditions.checkArgument(initial_address >= 0);

        this.addresses = initial_address;
    }

    /**
     * This method signals that a scope is being entered.
     */
    public void enterScope()
    {
        Preconditions.checkState(all_declared == false);

        // The nesting distance is increasing.
        ++level;

        /**
         * Grab a lock on every variable that was already declared.
         */
        for (VarInfo variable : variables.values())
        {
            variable.locks = variable.locks == 0 ? 0 : variable.locks + 1;
        }
    }

    /**
     * This method signals that a scope is being exited.
     */
    public void exitScope()
    {
        Preconditions.checkState(all_declared == false);

        // The nesting distance is decreasing.
        --level;

        /**
         * Release the locks that were grabbed, when the scope was entered.
         */
        for (VarInfo variable : variables.values())
        {
            variable.locks = variable.locks == 0 ? 0 : variable.locks - 1;
        }

        // If the outermost scope has been exited, then all variables are declared.
        all_declared = all_declared || level == 0;
    }

    /**
     * This method determines whether a particular variable is in-scope.
     *
     * @param name is the name of the variable.
     * @return true, iff the variable is usable in the current scope.
     * @throws IllegalStateException if variable declaration already completed.
     */
    public boolean isUsable(final String name)
    {
        Preconditions.checkState(all_declared == false);

        final VarInfo variable = findVar(name);

        return variable.locks >= 1;
    }

    /**
     * This method declares and allocates a new local-variable that is a parameter.
     *
     * @param variable is an AST-node that represents the variable to declare.
     * @param type is the proposed static-type of the variable.
     * @return true, if declaration was successful.
     * @throws IllegalStateException if any non-parameter locals were already declared.
     */
    public boolean declareParameter(final Variable variable,
                                    final IExpressionType type)
    {
        Preconditions.checkState(!non_parameters_declared);

        return declare(VarTypes.PARAM, variable.getName(), type);
    }

    /**
     * This method declares and allocates a new local-variable.
     *
     * @param variable is an AST-node that represents the variable to declare.
     * @param type is the proposed static-type of the variable.
     * @return true, if declaration was successful.
     */
    public boolean declareVar(final Variable variable,
                              final IExpressionType type)
    {
        return declare(VarTypes.VAR, variable.getName(), type);
    }

    /**
     * This method declares and allocates a new local-variable that is readonly.
     *
     * @param variable is an AST-node that represents the variable to declare.
     * @param type is the proposed static-type of the variable.
     * @return true, if declaration was successful.
     */
    public boolean declareVal(final Variable variable,
                              final IExpressionType type)
    {
        return declare(VarTypes.VAL, variable.getName(), type);
    }

    /**
     * This method declares and allocates a new temporary local-variable.
     *
     * @param variable is the name of the temporary to declare.
     * @param type is the proposed static-type of the variable.
     * @return true, if declaration was successful.
     */
    public boolean declareTemp(final String variable,
                               final IExpressionType type)
    {
        return declare(VarTypes.TEMP, variable, type);
    }

    /**
     * This method determines whether a named variable was already declared.
     *
     * @param name is the name of the variable to search for.
     * @return true, if and only if, the named variable was already allocated.
     */
    public boolean isDeclared(final String name)
    {
        Preconditions.checkNotNull(name);

        return findVar(name) != null;
    }

    /**
     * This method determines whether a named variable is a formal-parameter.
     *
     * @param name is the name of the variable to search for.
     * @return true, if and only if, the named variable is a formal-parameter.
     */
    public boolean isParameter(final String name)
    {
        return findVar(name).form == VarTypes.PARAM;
    }

    /**
     * This method determines whether a named variable allows non-initializer assignments.
     *
     * @param name is the name of the variable.
     * @return true, if and only if, the variable can be repeatedly assigned to.
     */
    public boolean isReadOnly(final String name)
    {
        Preconditions.checkNotNull(name);

        final VarTypes form = findVar(name).form;

        return form == VarTypes.VAL || form == VarTypes.PARAM;
    }

    /**
     * This method determines whether a named variable is a temporary variable.
     *
     * @param name is the name of the variable.
     * @return true, if and only if, the variable is a temporary.
     */
    public boolean isTemporary(final String name)
    {
        Preconditions.checkNotNull(name);

        final VarTypes form = findVar(name).form;

        return form == VarTypes.TEMP;
    }

    /**
     * This method retrieves the address of a previously declared variable.
     *
     * @param name is the name of the variable.
     * @return the address of the variable.
     * @throws IllegalArgumentException if the variable was not previously declared.
     */
    public int addressOf(final String name)
    {
        Preconditions.checkNotNull(name);
        Preconditions.checkArgument(variables.containsKey(name));

        final VarInfo variable = findVar(name);

        final int address = variable.address;

        return address;
    }

    /**
     * This method retrieves the static-type type of a named variable.
     *
     * @param name is the name of the variable.
     * @return the static-type of the variable.
     * @throws IllegalArgumentException if the variable was not previously declared.
     */
    public IVariableType typeOf(final String name)
    {
        Preconditions.checkNotNull(name);
        Preconditions.checkArgument(isDeclared(name));

        final VarInfo variable = findVar(name);

        final IVariableType type = variable.type;

        return type;
    }

    /**
     * This method creates a set containing the names of every declared variable.
     *
     * @return the names of the declared variables.
     */
    public Set<String> getVariables()
    {
        final Set<String> names = Sets.newTreeSet();

        names.addAll(variables.keySet());

        return names;
    }

    /**
     * This method performs the actual declaration and allocation of a local variable.
     *
     * @param form indicates whether the variable is a parameter, etc.
     * @param variable is an AST-node that represents the variable.
     * @param type is the proposed static-type of the variable.
     * @return true, if declaration was successful.
     */
    private boolean declare(final VarTypes form,
                            final String variable,
                            final IExpressionType type)
    {
        /**
         * A variable cannot be declared twice.
         */
        if (isDeclared(variable))
        {
            return false;
        }

        /**
         * This is very important, because of the way the JVM handles parameters.
         * Parameters must be assigned to the lowest addresses.
         * As a result, parameters must be declared first.
         * Otherwise, the non-parameter locals would interfere with the parameters.
         */
        this.non_parameters_declared = form != VarTypes.PARAM;

        /**
         * Create a base address for the new variable.
         */
        final int address = addresses;

        /**
         * Increment the address counter in order to allocate enough space to hold the variable.
         * In particular, longs and doubles require two slots.
         */
        final int increment = Utils.sizeof(type);
        addresses = addresses + increment;

        /**
         * Record the variable for later use.
         */
        final VarInfo info = new VarInfo(form, variable, (IVariableType) type, address);
        variables.put(variable, info);
        lang_testing_info.put(variable, type);

        /**
         * The variable was successfully allocated.
         */
        return true;
    }

    /**
     * This method searches for a variable that was already declared.
     *
     * @param name is the name of the variable to search for.
     * @return the information regarding the sought after variable,
     * or null, if the variable cannot be found.
     */
    private VarInfo findVar(final String name)
    {
        return variables.containsKey(name) ? variables.get(name) : null;
    }

    /**
     * This method should be invoked after all local variables are allocated by this object.
     * This method ensures the the ending state of this object is correct.
     *
     * @throws IllegalStateException if this object still thinks it is in a scope.
     */
    public void checkExitStatus()
    {
        Preconditions.checkState(all_declared);

        for (VarInfo info : variables.values())
        {
            Preconditions.checkState(info.locks == 0, "A variable is still in-scope.");
        }
    }
}

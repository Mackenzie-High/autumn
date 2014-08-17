package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ast.nodes.Variable;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import high.mackenzie.autumn.lang.compiler.typesystem.TypeFactory;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IExpressionType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
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
public final class VariableScope
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
         * This constant indicates that a local-variable is a immutable stack-allocated variable.
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
         * This field indicates the scope that declared this local variable.
         */
        public final VariableScope owner;

        /**
         * This field indicates whether this local variable is a parameter, etc.
         */
        public final VarTypes form;

        /**
         * This field indicates the name of this local variable .
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
        public VarInfo(final VariableScope owner,
                       final VarTypes form,
                       final String name,
                       final IVariableType type,
                       final int address)
        {
            this.owner = owner;
            this.form = form;
            this.name = name;
            this.type = type;
            this.address = address;
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
     * This is a counter used to allocate local-variable addresses within an activation-record.
     */
    private int addresses = 0;

    /**
     * This flag indicates whether any non-parameter local variables were declared yet.
     */
    private boolean non_parameters_declared = false;

    /**
     * These are the variables allocated directly within this scope.
     */
    private final Map<String, VarInfo> variables = Maps.newTreeMap();

    /**
     * If this scope closes over another scope, then this field stores the outer scope.
     */
    private final VariableScope closes_over;

    /**
     * Sole Constructor.
     *
     * @param closes_over is the scope that the new scope closes-over,
     * or null, if the new scope does not close over another scope.
     * @param initial_address indicates the address of the first allocated variable.
     * @throws IllegalArgumentException if <code>initial_address &lt; 0</code>.
     */
    public VariableScope(final VariableScope closes_over,
                         final int initial_address)
    {
        Preconditions.checkArgument(initial_address >= 0);

        this.closes_over = closes_over;
        this.addresses = initial_address;
    }

    /**
     * This method declares and allocates a new local-variable that is a parameter.
     *
     * @param variable is an AST-node that represents the variable to declare.
     * @param type is the proposed static-type of the variable.
     * @throws IllegalStateException if any non-parameter locals were already declared.
     */
    public void declareParameter(final Variable variable,
                                 final IExpressionType type)
    {
        Preconditions.checkState(!non_parameters_declared);

        declare(VarTypes.PARAM, variable.getName(), type);
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
     * This method determines whether a named variable was inherited from an outer scope.
     *
     * @param name is the name of the variable to search for.
     * @return true, if the variable was obtained via a closure.
     */
    public boolean isClosureVariable(final String name)
    {
        Preconditions.checkNotNull(name);

        final VarInfo variable = findVar(name);

        return variable != null && variable.owner != this;
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
     * @throws IllegalArgumentException if the variable was not previously declared in this scope.
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
     * This method retrieves the scope that this scope closes over.
     *
     * @return the scope that this scope forms a closure over,
     * or null, if this scope does not close over any other scope.
     */
    public VariableScope outerScope()
    {
        return closes_over;
    }

    /**
     * This method creates a set containing the names of every variable in this exact scope.
     *
     * @return the names of every variable declared directly in this scope.
     */
    public Set<String> getVariables()
    {
        final Set<String> names = Sets.newTreeSet();

        names.addAll(variables.keySet());

        return names;
    }

    /**
     * This method creates a set containing the names of every variable in this scope.
     *
     * @return the names of every variable declared in this scope.
     */
    public Set<String> getAllVariables()
    {
        final Set<String> names = Sets.newTreeSet();

        names.addAll(variables.keySet());

        if (closes_over != null)
        {
            names.addAll(closes_over.getAllVariables());
        }

        return names;
    }

    /**
     * This method creates a set containing the names of every non-temporary variable in this scope.
     *
     * @return the names of every non-temporary variable declared directly in this scope.
     */
    public Set<String> getAllVisibleVariables()
    {
        final Set<String> names = Sets.newTreeSet();

        for (String name : getAllVariables())
        {
            if (isTemporary(name) == false)
            {
                names.add(name);
            }
        }

        return names;
    }

    /**
     * This method performs all necessary static type-checking of a variable declaration.
     *
     * @param variable is the variable being declared.
     * @param type is the static-type of the variable.
     * @return true, if the declaration should fail.
     */
    private boolean checkDeclare(final String variable,
                                 final IExpressionType type)
    {
        Preconditions.checkNotNull(variable);
        Preconditions.checkNotNull(type);

        if (isDeclared(variable))
        {
        }

        if (!type.isPrimitiveType() && !type.isReferenceType())
        {
        }

        return false;
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
        if (checkDeclare(variable, type))
        {
            return false;
        }

        this.non_parameters_declared = form != VarTypes.PARAM;

        final String name = variable;

        final int address = addresses;

        final int increment;

        // doubles and longs require two slots.
        if (type.getDescriptor().equals("J") || type.getDescriptor().equals("D"))
        {
            increment = 2;
        }
        else
        {
            increment = 1;
        }

        addresses = addresses + increment;

        final VarInfo info = new VarInfo(this, form, name, (IVariableType) type, address);

        variables.put(name, info);

        lang_testing_info.put(name, type);

        return true;
    }

    /**
     * This method searches for a variable that is visible from within this scope.
     *
     * @param name is the name of the variable to search for.
     * @return the information regarding the sought after variable,
     * or null, if the variable cannot be found.
     */
    private VarInfo findVar(String name)
    {
        final VarInfo result;

        if (variables.containsKey(name))
        {
            result = variables.get(name);
        }
        else if (closes_over == null)
        {
            result = null;
        }
        else
        {
            result = closes_over.findVar(name);
        }

        return result;
    }

    /**
     * This method returns strings which describe the variables accessible within this scope.
     *
     * <p>
     * The strings are sorted lexicographically.
     * </p>
     *
     * <p>
     * <b>Format:</b>
     * <b>depth</b> <i>00000</i>
     * <b>address</b> <i>00000</i>
     * <i>form</i>
     * <i>name</i> <b>:</b> <i>type</i>
     * </p>
     *
     * @return the aforedescribed list of strings.
     */
    public List<String> description()
    {
        final List<String> result = description(0);

        Collections.sort(result);

        return result;
    }

    private List<String> description(final int depth)
    {
        final List<String> result = outerScope() == null
                ? new LinkedList<String>()
                : outerScope().description(depth + 1);

        for (VarInfo info : variables.values())
        {
            result.add("depth " + Strings.padStart("" + depth, 5, '0') + " " + info.toString());
        }

        return result;
    }

    public static void main(String[] args)
    {
        VariableScope s = new VariableScope(null, 0);

        // Bug: Auto-boxing list.remove(int) vs list.remove(Object);

        final TypeFactory f = new TypeFactory();

        Variable v;

        v = new Variable();
        v = v.setName("m");
        s.declareVar(v, f.getLong());

        v = new Variable();
        v = v.setName("b");
        s.declareVar(v, f.getInt());

        v = new Variable();
        v = v.setName("c");
        s.declareVar(v, f.getInt());

        v = new Variable();
        v = v.setName("x");
        s.declareVar(v, f.getInt());

        v = new Variable();
        v = v.setName("y");
        s.declareVar(v, f.getInt());

        v = new Variable();
        v = v.setName("z");
        s.declareVar(v, (IExpressionType) f.fromClass(String.class));

        for (String info : s.description())
        {
            System.out.println(info);
        }
    }
}

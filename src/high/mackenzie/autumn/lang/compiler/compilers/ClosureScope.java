/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ast.nodes.FormalParameterList;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;
import java.util.Set;

/**
 * An instance of this class is the scope of a lambda.
 *
 * @author Mackenzie High
 */
public final class ClosureScope
{
    private final VariableScope base;

    private final ClosureScope outer;

    private final FormalParameterList formals;

    private final Set<String> captured = Sets.newTreeSet();

    /**
     * Constructor.
     *
     * @param scope is the scope of the function that this scope closes over.
     * @param formals are the formal-parameters of the lambda or delegate.
     */
    public ClosureScope(final VariableScope scope,
                        final FormalParameterList formals)
    {
        Preconditions.checkNotNull(scope);
        Preconditions.checkNotNull(formals);

        this.base = scope;
        this.outer = null;
        this.formals = formals;
    }

    /**
     * Constructor.
     *
     * @param scope is the scope that this scope closes over.
     * @param formals are the formal-parameters of the lambda or delegate.
     */
    public ClosureScope(final ClosureScope scope,
                        final FormalParameterList formals)
    {
        Preconditions.checkNotNull(scope);
        Preconditions.checkNotNull(formals);

        this.base = null;
        this.outer = scope;
        this.formals = formals;
    }

    /**
     * This method causes this closure to capture a variable.
     *
     * @param name is the name of the variable.
     */
    private void capture(final String name)
    {
        captured.add(name);

        if (outer != null)
        {
            outer.capture(name);
        }
    }

    public IVariableType typeOf(final String name)
    {
        return null;
    }

    /**
     * This method retrieves the names of the variables that this closure must capture.
     *
     * @return the names of the variables that this closure captures from the outer scope.
     */
    public Set<String> captured()
    {
        return ImmutableSet.copyOf(captured);
    }

    /**
     * This method retrieves the scope of the function that is closed over.
     *
     * @return the scope of the base function.
     */
    public VariableScope base()
    {
        if (outer != null)
        {
            return outer.base();
        }
        else
        {
            return base;
        }
    }
}

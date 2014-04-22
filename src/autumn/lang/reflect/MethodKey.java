/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autumn.lang.reflect;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.List;

/**
 * An instance of this class is used to identify a method.
 *
 * @author Mackenzie High
 */
public final class MethodKey
{
    private final String name;

    private final List<Class> formals;

    private final Class returns;

    public MethodKey(final String name,
                     final List<Class> formals,
                     final Class returns)
    {
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(formals);
        Preconditions.checkNotNull(returns);

        this.name = name;
        this.formals = ImmutableList.copyOf(formals);
        this.returns = returns;
    }

    public String name()
    {
        return name;
    }

    public List<Class> parameterTypes()
    {
        return ImmutableList.copyOf(formals);
    }

    public Class returnType()
    {
        return returns;
    }

    public String descriptor()
    {
        return null;
    }
}

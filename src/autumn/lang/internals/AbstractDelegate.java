/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autumn.lang.internals;

import autumn.lang.Delegate;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * TODO: Delegates may call methods that return void!!!
 *
 *
 * @author mackenzie
 */
public abstract class AbstractDelegate
        extends AbstractFunctor
        implements Delegate
{
    @Override
    public Method method()
    {
        final Method method = null;

        for (Method m : owner().getDeclaredMethods())
        {
            if (isMatchingMethod(m))
            {
                return m;
            }
        }

        for (Method m : owner().getMethods())
        {
            if (isMatchingMethod(m))
            {
                return m;
            }
        }

        Preconditions.checkState(method != null);

        return method;
    }

    private boolean isMatchingMethod(final Method method)
    {
        if (!Objects.equal(method.getName(), this.name()))
        {
            return false;
        }

        if (!Objects.equal(this.parameterTypes(), Arrays.asList(method.getParameterTypes())))
        {
            return false;
        }

        return true;
    }

    @Override
    public String toString()
    {
        final Method m = this.method(); // TODO: should this be needed?

        final StringBuilder result = new StringBuilder();

        result.append("delegate ");

        result.append(owner().getSimpleName());

        result.append(Modifier.isStatic(m.getModifiers()) ? "::" : "."); // TODO: does this need changed?

        result.append(name());

        result.append('(');

        int i = 0;

        for (Class param : parameterTypes())
        {
            result.append(param.getSimpleName());

            result.append(i++ < parameterTypes().size() - 1 ? ", " : "");
        }

        result.append(')');

        return result.toString();
    }
}

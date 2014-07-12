package autumn.lang.internals;

import autumn.lang.Delegate;
import com.google.common.base.Objects;
import high.mackenzie.autumn.resources.Finished;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * This class provides a partial implementation of the Delegate interface.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public abstract class AbstractDelegate
        extends AbstractFunctor
        implements Delegate
{
    private Method cache;

    /**
     * {@inheritDoc}
     */
    @Override
    public Method method()
    {
        // This result of this method is memoized in order in order to improve performance.
        if (cache != null)
        {
            return cache;
        }

        /**
         * Delegates always refer to public static methods.
         * Simply find a static method with a matching name and parameter list.
         */
        for (Method m : owner().getMethods())
        {
            if (isMatchingMethod(m))
            {
                return cache = m;
            }
        }

        throw new RuntimeException("This should never happen.");
    }

    /**
     * This method determines whether this delegate's name and parameters match those of a method.
     *
     * @param method is the possibly matching method.
     * @return true, iff this delegate matches the method.
     */
    private boolean isMatchingMethod(final Method method)
    {
        // Does the name match?
        if (!Objects.equal(method.getName(), this.name()))
        {
            return false;
        }

        // Does the parameter-list match?
        if (!Objects.equal(this.parameterTypes(), Arrays.asList(method.getParameterTypes())))
        {
            return false;
        }

        // The name and the parameter-list both match.
        return true;
    }

    /**
     * {@inheritDoc}
     */
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

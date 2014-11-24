package high.mackenzie.autumn.compiler.documentor;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

/**
 *
 * @author mackenzie
 */
final class MetaMethod
{
    public boolean shared;

    public final String name;

    public final List<Class> parameters;

    public final Class returns;

    public final String summary;

    /**
     * Sole Constructor.
     *
     * @param shared is true, if the method is static.
     * @param name is the name of the method.
     * @param parameters are the types of the method's formal parameters.
     * @param returns is the method's return-type.
     * @param summary is a single-line summary of the method's functionality.
     */
    public MetaMethod(final boolean shared,
                      final String name,
                      final Iterable<Class> parameters,
                      final Class returns,
                      final String summary)
    {
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(parameters);
        Preconditions.checkNotNull(returns);

        this.shared = shared;
        this.name = name;
        this.parameters = ImmutableList.copyOf(parameters);
        this.returns = returns;
        this.summary = summary;
    }

    public String signature()
    {
        final StringBuilder signature = new StringBuilder();

        signature.append(name);

        signature.append(" ");

        signature.append("(");

        for (int i = 0; i < parameters.size(); i++)
        {
            signature.append(Utils.linkTo(parameters.get(i), true));

            if (i < parameters.size() - 1)
            {
                signature.append(", ");
            }
        }

        signature.append(")");

        signature.append(" : ");

        signature.append(Utils.linkTo(returns, true));

        return signature.toString();
    }

    public static MetaMethod create(final Method method)
    {
        final MetaMethod result = new MetaMethod(Modifier.isStatic(method.getModifiers()),
                                                 method.getName(),
                                                 Lists.<Class>newArrayList(method.getParameterTypes()),
                                                 method.getReturnType(),
                                                 "");

        return result;
    }
}

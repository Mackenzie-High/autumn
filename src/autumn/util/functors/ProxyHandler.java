package autumn.util.functors;

import autumn.lang.TypedFunctor;
import autumn.lang.internals.AbstractDefinedFunctor;
import com.google.common.collect.ImmutableList;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 *
 * @author mackenzie
 */
public class ProxyHandler
        extends AbstractDefinedFunctor
        implements InvocationHandler
{
    /**
     * Sole Constructor.
     *
     * @param inner is the functor that provides the actual functionality.
     */
    public ProxyHandler(final TypedFunctor inner)
    {
        super(inner);
    }

    /**
     * This method invokes the inner functor.
     *
     * @param arg1 is the 1st argument.
     * @param arg2 is the 2nd argument.
     * @param arg3 is the 3rd argument.
     */
    @Override
    public Object invoke(Object proxy,
                         Method method,
                         Object[] args)
            throws Throwable
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Class> parameterTypes()
    {
        return ImmutableList.<Class>of(Object.class, Object.class, Object.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class returnType()
    {
        return Object.class;
    }
}

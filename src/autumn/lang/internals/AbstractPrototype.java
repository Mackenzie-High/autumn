package autumn.lang.internals;

import autumn.lang.Functor;
import autumn.lang.Prototype;
import autumn.lang.reflect.DesignMethod;
import autumn.lang.reflect.DesignObject;
import autumn.lang.reflect.DesignProperty;
import autumn.lang.reflect.MethodKey;
import autumn.lang.reflect.PropertyKey;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author mackenzie
 */
public abstract class AbstractPrototype
        implements Prototype
{
    protected abstract SharedState autumn$shared();

    protected abstract Prototype autumn$property$set(final int key,
                                                     final Object value);

    protected abstract Prototype autumn$property$get(final int key);

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract Prototype objectCopy();

    /**
     * {@inheritDoc}
     */
    @Override
    public DesignObject objectState()
    {
        final Prototype self = this;

        final List<DesignProperty> properties = null;

        final List<DesignMethod> methods = Lists.newLinkedList();

        final DesignObject result = new DesignObject()
        {
            @Override
            public Prototype object()
            {
                return self;
            }

            @Override
            public Class design()
            {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public DesignProperty getProperty(PropertyKey key)
            {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public DesignMethod getMethod(MethodKey key)
            {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Collection<DesignProperty> properties()
            {
                return ImmutableList.copyOf(properties);
            }

            @Override
            public Collection<DesignMethod> methods()
            {
                return ImmutableList.copyOf(methods);
            }

            @Override
            public Object innerObject()
            {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Prototype objectSetSetter(final PropertyKey key,
                                     final Functor handler)
    {


        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Prototype objectSetGetter(final PropertyKey key,
                                     final Functor handler)
    {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Prototype objectSetMethod(final MethodKey key,
                                     final Functor handler)
    {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Prototype objectSetValue(final PropertyKey key,
                                    final Object value)
    {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object objectGetValue(final PropertyKey key)
    {
        return null;
    }
}

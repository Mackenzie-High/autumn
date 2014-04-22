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
import java.util.List;

/**
 *
 *
 * @author mackenzie
 */
public final class ObjectLayout
{
    /**
     * An instance of this class describes the static representation of a property.
     */
    private static final class StaticPropertyInfo
    {
        public boolean mutable;

        public boolean sync;

        public boolean nullable;

        public PropertyKey key;

        public Class type;
    }

    /**
     * An instance of this class describes the static representation of a method.
     */
    private static final class StaticMethodInfo
    {
        public boolean start;

        public boolean sync;

        public boolean composite;

        public MethodKey key;
    }

    private final List<StaticPropertyInfo> properties = Lists.newLinkedList();

    private final List<StaticMethodInfo> methods = Lists.newLinkedList();

    public void declareProperty(final String name,
                                final Class type,
                                final boolean mutable,
                                final boolean sync,
                                final boolean nullable)
    {
        final StaticPropertyInfo property = new StaticPropertyInfo();
    }

    public int declareMethod(final String key)
    {
        return 0;
    }

    public int declareMethod(final String alias,
                             final String key)
    {
        return 0;
    }

    public int resolveProperty(final String key)
    {
        return -1;
    }

    public List<DesignProperty> properties(final DesignObject owner,
                                           final Prototype instance)
    {
        final List<DesignProperty> list = Lists.newLinkedList();

        final int index = resolveProperty(null);

        final StaticPropertyInfo description = properties.get(index);

        final DesignProperty property = new DesignProperty()
        {
            @Override
            public DesignObject owner()
            {
                return owner;
            }

            @Override
            public PropertyKey key()
            {
                return description.key;
            }

            @Override
            public Class type()
            {
                return description.type;
            }

            @Override
            public boolean isMutable()
            {
                return description.mutable;
            }

            @Override
            public boolean isSync()
            {
                return description.sync;
            }

            @Override
            public boolean isNullable()
            {
                return description.nullable;
            }

            @Override
            public Object get()
            {
                return instance.objectGetValue(key());
            }

            @Override
            public Prototype set(Object value)
            {
                return instance.objectSetValue(key(), value);
            }

            @Override
            public Functor setter()
            {
                return null;
            }

            @Override
            public Functor getter()
            {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public boolean isSet()
            {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };

        return ImmutableList.copyOf(list);
    }

    public List<DesignMethod> methods(final Prototype instance)
    {
        final List<DesignMethod> list = Lists.newLinkedList();

        final DesignMethod method = new DesignMethod()
        {
            @Override
            public DesignObject owner()
            {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public MethodKey key()
            {
                return null;
            }

            @Override
            public boolean isSync()
            {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public boolean isStart()
            {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public boolean isComposite()
            {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Functor handler()
            {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };

        return ImmutableList.copyOf(list);
    }
}

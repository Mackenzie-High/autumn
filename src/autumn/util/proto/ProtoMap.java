package autumn.util.proto;

import autumn.lang.Prototype;
import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 *
 * @author Mackenzie High
 */
public class ProtoMap
        implements Map<String, Object>
{
    private final Prototype p;

    public ProtoMap(final Prototype p)
    {
        Preconditions.checkNotNull(p);

        this.p = p;
    }

    @Override
    public int size()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isEmpty()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean containsKey(Object key)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean containsValue(Object value)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object get(Object key)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object put(String key,
                      Object value)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object remove(Object key)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void putAll(Map<? extends String, ? extends Object> m)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clear()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<String> keySet()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<Object> values()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<Entry<String, Object>> entrySet()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object call()
    {
        return null;
    }

    public Object call(final Object arg0)
    {
        return null;
    }

    public Object call(final Object arg0,
                       final Object arg1)
    {
        return null;
    }

    public Object call(final Object arg0,
                       final Object arg1,
                       final Object arg2)
    {
        return null;
    }

    public Object call(final Object arg0,
                       final Object arg1,
                       final Object arg2,
                       final Object arg3)
    {
        return null;
    }

    public Object call(final Object arg0,
                       final Object arg1,
                       final Object arg2,
                       final Object arg3,
                       final Object arg4)
    {
        return null;
    }

    public Object call(final Object arg0,
                       final Object arg1,
                       final Object arg2,
                       final Object arg3,
                       final Object arg4,
                       final Object... args)
    {
        return null;
    }

    public Object invoke(final List<?> arg0)
    {
        return null;
    }
}

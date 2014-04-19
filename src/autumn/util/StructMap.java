package autumn.util;

import autumn.lang.Struct;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * An instance of this class is a map that wraps a struct.
 *
 * @author Mackenzie High
 */
public class StructMap
        implements Map<String, Object>
{
    // TODO: equals, hash, and tostring
    private Struct s;

    public StructMap(final Struct struct)
    {
        this.s = struct;
    }

    @Override
    public int size()
    {
        return s.size();
    }

    @Override
    public boolean isEmpty()
    {
        return s.isEmpty();
    }

    @Override
    public boolean containsKey(Object key)
    {
        return s.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value)
    {
        return s.containsValue(value);
    }

    @Override
    public Object get(Object key)
    {
        if (key == null)
        {
            return null;
        }
        else
        {
            return null; // TODO: what happens if the key is not present
        }
    }

    @Override
    public Object put(final String key,
                      final Object value)
    {
        final Object old = s.get(key);

        s = s.set(key, value);

        return old;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Object> m)
    {
        for (Entry entry : m.entrySet())
        {
            final Object key = entry.getKey();

            put((key == null ? null : key.toString()), entry.getValue());
        }
    }

    @Override
    public Object remove(Object key)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clear()
    {
        s = s.clear();
    }

    @Override
    public Set<String> keySet()
    {
        return s.keySet();
    }

    @Override
    public Collection<Object> values()
    {
        return s.values();
    }

    @Override
    public Set<Entry<String, Object>> entrySet()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

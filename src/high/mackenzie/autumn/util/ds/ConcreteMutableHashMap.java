package high.mackenzie.autumn.util.ds;

import autumn.util.ds.ImmutableMap;
import autumn.util.ds.MutableMap;
import com.google.common.base.Preconditions;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * This class provides a concrete hashing-based implementation of the MutableMap interface.
 *
 * @author Mackenzie High
 */
public final class ConcreteMutableHashMap<K, V>
        extends AbstractMap<K, V>
        implements MutableMap<K, V>
{
    private FunctionalTreeMap<Integer, FunctionalList<Entry<K, V>>> map;

    public ConcreteMutableHashMap()
    {
        this.map = new FunctionalTreeMap();

    }

    public ConcreteMutableHashMap(final FunctionalTreeMap<Integer, FunctionalList<Entry<K, V>>> map)
    {
        this.map = map;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<K, V> immutable()
    {
        return new ConcreteImmutableHashMap<K, V>(map);
    }

    @Override
    public V put(final K key,
                 final V value)
    {
        final int hash = key.hashCode();

        FunctionalList<Entry<K, V>> list = map.get(hash);

        if (list == null)
        {
            list = new FunctionalList<Entry<K, V>>();
        }

        V previous = null;

        int index = 0;

        for (Entry<K, V> entry : list)
        {
            if (entry.getKey().equals(key))
            {
                previous = entry.getValue();
                list = list.remove(index);
                break;
            }
            else
            {
                ++index;
            }
        }

        list = list.insert(0, new SimpleEntry<K, V>(key, value));

        map = map.put(hash, list);

        return previous;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Map.Entry<K, V>> entrySet()
    {
        return new AbstractSet<Entry<K, V>>()
        {
            @Override
            public Iterator<Entry<K, V>> iterator()
            {
                final Iterator<Entry<K, V>> entries = immutable().entrySet().iterator();

                return new Iterator<Entry<K, V>>()
                {
                    private Entry<K, V> last;

                    @Override
                    public boolean hasNext()
                    {
                        return entries.hasNext();
                    }

                    @Override
                    public Entry<K, V> next()
                    {
                        last = entries.next();

                        return last;
                    }

                    @Override
                    public void remove()
                    {
                        Preconditions.checkState(last != null);

                        final int hash = last.getKey().hashCode();

                        FunctionalList<Entry<K, V>> list = map.get(hash);

                        int index = 0;

                        for (Entry<K, V> entry : list)
                        {
                            if (entry.getKey().equals(last.getKey()))
                            {
                                list = list.remove(index);
                                break;
                            }
                            else
                            {
                                ++index;
                            }
                        }

                        if (list.isEmpty())
                        {
                            map = map.remove(hash);
                        }
                        else
                        {
                            map = map.put(hash, list);
                        }
                    }
                };
            }

            @Override
            public int size()
            {
                return map.size();
            }
        };
    }

    public static void main(String[] args)
    {
        final ConcreteMutableHashMap<String, String> map = new ConcreteMutableHashMap<String, String>();

        map.put("A", "1");
        map.put("B", "2");

        System.out.println(map);
    }
}

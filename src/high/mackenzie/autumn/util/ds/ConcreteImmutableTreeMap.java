package high.mackenzie.autumn.util.ds;

import autumn.util.ds.ImmutableSortedMap;
import autumn.util.ds.MutableSortedMap;
import com.google.common.base.Preconditions;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedMap;

/**
 * This class provides a concrete tree-based implementation of the ImmutableSortedMap interface.
 *
 * @author Mackenzie High
 */
public final class ConcreteImmutableTreeMap<K, V>
        extends AbstractMap<K, V>
        implements ImmutableSortedMap<K, V>
{
    private final FunctionalTreeMap<K, V> map;

    ConcreteImmutableTreeMap(final FunctionalTreeMap<K, V> map)
    {
        Preconditions.checkNotNull(map);

        this.map = map;
    }

    @Override
    public MutableSortedMap<K, V> mutable()
    {
        return map.mutable();
    }

    @Override
    public Set<Entry<K, V>> entrySet()
    {
        final Iterator<K> keys = map.keys();

        return new AbstractSet<Entry<K, V>>()
        {
            @Override
            public Iterator<Entry<K, V>> iterator()
            {
                return new Iterator<Entry<K, V>>()
                {
                    @Override
                    public boolean hasNext()
                    {
                        return keys.hasNext();
                    }

                    @Override
                    public Entry<K, V> next()
                    {
                        final K key = keys.next();

                        final V value = map.get(key);

                        return new SimpleImmutableEntry<K, V>(key, value);
                    }

                    @Override
                    public void remove()
                    {
                        throw new UnsupportedOperationException("Not Supported");
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

    @Override
    public Entry<K, V> lowerEntry(K key)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public K lowerKey(K key)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Entry<K, V> floorEntry(K key)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public K floorKey(K key)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Entry<K, V> ceilingEntry(K key)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public K ceilingKey(K key)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Entry<K, V> higherEntry(K key)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public K higherKey(K key)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Entry<K, V> firstEntry()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Entry<K, V> lastEntry()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Entry<K, V> pollFirstEntry()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Entry<K, V> pollLastEntry()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public NavigableMap<K, V> descendingMap()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public NavigableSet<K> navigableKeySet()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public NavigableSet<K> descendingKeySet()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public NavigableMap<K, V> subMap(K fromKey,
                                     boolean fromInclusive,
                                     K toKey,
                                     boolean toInclusive)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public NavigableMap<K, V> headMap(K toKey,
                                      boolean inclusive)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public NavigableMap<K, V> tailMap(K fromKey,
                                      boolean inclusive)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public SortedMap<K, V> subMap(K fromKey,
                                  K toKey)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public SortedMap<K, V> headMap(K toKey)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public SortedMap<K, V> tailMap(K fromKey)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Comparator<? super K> comparator()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public K firstKey()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public K lastKey()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

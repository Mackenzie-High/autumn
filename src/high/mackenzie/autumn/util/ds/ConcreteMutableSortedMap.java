package high.mackenzie.autumn.util.ds;

import autumn.util.ds.FunctionalSortedMap;
import autumn.util.ds.ImmutableSortedMap;
import autumn.util.ds.MutableSortedMap;
import java.util.AbstractMap;
import java.util.Comparator;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedMap;

/**
 * An instance of this class is a mutable sorted map that is based on a persistent sorted map.
 *
 * @author Mackenzie High
 */
public final class ConcreteMutableSortedMap<K, V>
        extends AbstractMap<K, V>
        implements MutableSortedMap<K, V>
{
    private FunctionalSortedMap<K, V> map;

    /**
     * Sole Constructor.
     *
     * @param map is the wrapped map.
     */
    public ConcreteMutableSortedMap(final FunctionalSortedMap<K, V> map)
    {
        assert map != null;

        this.map = map;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableSortedMap<K, V> immutable()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FunctionalSortedMap<K, V> functional()
    {
        return map;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Entry<K, V>> entrySet()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entry<K, V> lowerEntry(K key)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public K lowerKey(K key)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entry<K, V> floorEntry(K key)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public K floorKey(K key)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entry<K, V> ceilingEntry(K key)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public K ceilingKey(K key)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entry<K, V> higherEntry(K key)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public K higherKey(K key)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entry<K, V> firstEntry()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entry<K, V> lastEntry()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entry<K, V> pollFirstEntry()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entry<K, V> pollLastEntry()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NavigableMap<K, V> descendingMap()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NavigableSet<K> navigableKeySet()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NavigableSet<K> descendingKeySet()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NavigableMap<K, V> subMap(K fromKey,
                                     boolean fromInclusive,
                                     K toKey,
                                     boolean toInclusive)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NavigableMap<K, V> headMap(K toKey,
                                      boolean inclusive)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NavigableMap<K, V> tailMap(K fromKey,
                                      boolean inclusive)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SortedMap<K, V> subMap(K fromKey,
                                  K toKey)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SortedMap<K, V> headMap(K toKey)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SortedMap<K, V> tailMap(K fromKey)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Comparator<? super K> comparator()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public K firstKey()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public K lastKey()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

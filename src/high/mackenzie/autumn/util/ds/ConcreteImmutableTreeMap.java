package high.mackenzie.autumn.util.ds;

import autumn.util.ds.ImmutableTreeMap;
import autumn.util.ds.MutableTreeMap;
import com.google.common.base.Preconditions;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;

/**
 * This class provides a concrete tree-based implementation of the ImmutableTreeMap interface.
 *
 * @author Mackenzie High
 */
public final class ConcreteImmutableTreeMap<K, V>
        extends AbstractMap<K, V>
        implements ImmutableTreeMap<K, V>
{
    /**
     * This object is simply a facade that wraps this map
     * in order to provide a different API.
     */
    private final FunctionalTreeMap<K, V> map;

    /**
     * Sole Constructor.
     *
     * @param map is the internal state of this object.
     * @throws NullPointerException if map is null.
     */
    ConcreteImmutableTreeMap(final FunctionalTreeMap<K, V> map)
    {
        Preconditions.checkNotNull(map);

        this.map = map;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MutableTreeMap<K, V> mutable()
    {
        return map.mutable();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Entry<K, V>> entrySet()
    {
        final Iterator<K> keys = map.keys();

        /**
         * In order to implement an unmodifiable AbstractSet, implement:
         * 1. iterator()
         * 2. size()
         */
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
                        /**
                         * This method is not supported, because the Set is immutable.
                         */
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
}

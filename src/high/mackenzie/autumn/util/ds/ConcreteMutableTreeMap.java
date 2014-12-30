package high.mackenzie.autumn.util.ds;

import autumn.util.ds.ImmutableTreeMap;
import autumn.util.ds.MutableTreeMap;
import com.google.common.base.Preconditions;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;

/**
 * This class provides a concrete tree-based implementation of the MutableMap interface.
 *
 * @author Mackenzie High
 */
public final class ConcreteMutableTreeMap<K, V>
        extends AbstractMap<K, V>
        implements MutableTreeMap<K, V>
{
    /**
     * This is the persistent tree that this object wraps.
     */
    private FunctionalTreeMap<K, V> map;

    /**
     * Sole Constructor.
     *
     * @param map is the wrapped map.
     * @throws NullPointerException if map is null.
     */
    ConcreteMutableTreeMap(final FunctionalTreeMap<K, V> map)
    {
        Preconditions.checkNotNull(map);

        this.map = map;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableTreeMap<K, V> immutable()
    {
        return map.immutable();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear()
    {
        map = new FunctionalTreeMap<K, V>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V put(final K key,
                 final V value)
    {
        /**
         * Retrieve the value that is currently associated with the given key.
         * This will be null, if the given key is not currently in the map.
         */
        final V previous = map.get(key);

        /**
         * Insert the new entry into the map.
         * This will overwrite the previous entry with the given key, if any.
         */
        map = map.put(key, value);

        /**
         * Return the value that was previously associated with the given key.
         */
        return previous;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V get(final Object key)
    {
        return map.get(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V remove(final Object key)
    {
        /**
         * Retrieve the value that is currently associated with the given key.
         * This will be null, if the given key is not currently in the map.
         */
        final V previous = map.get(key);

        /**
         * Remove the entry from the map, if such an entry exists.
         */
        map = map.remove(key);

        /**
         * Return the value that was previously associated with the given key.
         */
        return previous;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsKey(final Object key)
    {
        return map.contains(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size()
    {
        return map.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Entry<K, V>> entrySet()
    {
        final ConcreteMutableTreeMap<K, V> SELF = this;

        return new AbstractSet<Entry<K, V>>()
        {
            /**
             * This is a more efficient implementation
             * of this method than AbstractSet provides.
             */
            @Override
            public void clear()
            {
                SELF.clear();
            }

            /**
             * This is a more efficient implementation
             * of this method than AbstractSet provides.
             */
            @Override
            public boolean contains(final Object key)
            {
                return SELF.containsKey(key);
            }

            /**
             * This method is required, since the map is mutable.
             */
            @Override
            public boolean remove(Object key)
            {
                SELF.remove(key);
                return true;
            }

            /**
             * This method is required by AbstractSet.
             */
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
                        SELF.remove(last.getKey());
                    }
                };
            }

            /**
             * This method is required by AbstractSet.
             */
            @Override
            public int size()
            {
                return SELF.size();
            }
        };
    }
}

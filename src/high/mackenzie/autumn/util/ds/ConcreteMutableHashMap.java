package high.mackenzie.autumn.util.ds;

import autumn.util.ds.ImmutableHashMap;
import autumn.util.ds.MutableHashMap;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * This class provides a concrete hashing-based implementation of the MutableMap interface.
 *
 * @author Mackenzie High
 */
public final class ConcreteMutableHashMap<K, V>
        extends AbstractMap<K, V>
        implements MutableHashMap<K, V>
{
    /**
     * This is the map that maps hash-values to lists of entries,
     * where the aforesaid hash-value is the same as that of each entry's key.
     */
    private FunctionalTreeMap<Integer, FunctionalList<Entry<K, V>>> map;

    /**
     * This is the total number of entries currently in this map.
     */
    private int size = 0;

    /**
     * Constructor.
     */
    public ConcreteMutableHashMap()
    {
        this.map = new FunctionalTreeMap();
    }

    /**
     * Sole Constructor.
     *
     * @param map is the object that this object will initially wrap.
     * @throws NullPointerException if map is null.
     */
    ConcreteMutableHashMap(final FunctionalTreeMap<Integer, FunctionalList<Entry<K, V>>> map)
    {
        this.map = map;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableHashMap<K, V> immutable()
    {
        return new ConcreteImmutableHashMap<K, V>(map, size);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear()
    {
        map = new FunctionalTreeMap<Integer, FunctionalList<Entry<K, V>>>();
        size = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V put(final K key,
                 final V value)
    {
        /**
         * Compute the hash-value of the key.
         */
        final int hash = key.hashCode();

        /**
         * Entry's with the same hash-values are stored in the same list.
         * So, find the list that contains the entry for the given key.
         */
        FunctionalList<Entry<K, V>> list = map.get(hash);

        /**
         * If the list is null, then no entry has the same hash-value as the key.
         * Ideally, this is what we want to happen as much as possible.
         */
        if (list == null)
        {
            /**
             * Create the list that will store our new entry.
             */
            list = new FunctionalList<Entry<K, V>>();
        }

        /**
         * This variable will be assigned the previous entry
         * that was associated with the given key.
         */
        Entry<K, V> previous = null;

        /**
         * Remove the entry from the list whose key equals the given key.
         * Also, find the value previously associated with the given key.
         */
        int index = 0;
        for (Entry<K, V> entry : list)
        {
            if (entry.getKey().equals(key))
            {
                previous = entry;
                list = list.remove(index);
                break;
            }
            else
            {
                ++index;
            }
        }

        /**
         * Create an entry that maps the given key to the given value.
         */
        final Entry<K, V> entry = new SimpleEntry<K, V>(key, value);

        /**
         * Insert the entry into the list of entries that share the same hash-value.
         * Since the list is persistent, a new list will be created.
         */
        list = list.insert(0, entry);

        /**
         * Insert the newly created list object into the map.
         * Since the map is persistent, a new map will be created.
         */
        map = map.put(hash, list);

        /**
         * Return the value that was previously associated with the given key.
         * If the key was not previously in the map, then return null.
         */
        if (previous == null)
        {
            /**
             * Since the key was previously not in the map,
             * increment the size of the map.
             */
            ++size;
            return null;
        }
        else
        {
            return previous.getValue();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V get(final Object key)
    {
        /**
         * Find the entry that contains the key, if any.
         */
        final Entry<K, V> entry = lookup(key);

        /**
         * Return the appropriate value.
         */
        if (entry == null)
        {
            return null;
        }
        else
        {
            return entry.getValue();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V remove(final Object key)
    {
        /**
         * Compute the hash-value of the given key.
         */
        final int hash = Objects.hashCode(key);

        /**
         * Retrieve the list that stores the entry
         * that is associated with the given key.
         */
        FunctionalList<Entry<K, V>> list = map.get(hash);

        /**
         * If no such list exists, then no entry needs removed.
         */
        if (list == null)
        {
            return null;
        }

        /**
         * This variable will be assigned the previous entry
         * that was associated with the given key.
         */
        Entry<K, V> previous = null;

        /**
         * Transverse the list of entries looking the entry associated wit the given key.
         * If such an entry is found, then remove it from the list.
         * This will result in a new list being created, since the list is persistent.
         * If no such entry is found, then do nothing.
         */
        int index = 0;
        for (Entry<K, V> entry : list)
        {
            if (entry.getKey().equals(key))
            {
                // This will be needed later.
                previous = entry;

                list = list.remove(index);

                break;
            }
            else
            {
                ++index;
            }
        }

        /**
         * If no previous entry was found, then the key was not in the map.
         * In other words, no entry was removed from the list of entries.
         */
        if (previous == null)
        {
            return null;
        }
        /**
         * If the list is now empty, then there is no need to keep it in the map.
         * In fact, leaving the list in the map would eventually cause problems.
         */
        else if (list.isEmpty())
        {
            map = map.remove(hash);
        }
        /**
         * The entry was removed from the list of entries.
         * Now we need to update the map.
         */
        else
        {
            map = map.put(hash, list);
        }

        /**
         * Since an entry was removed from the map,
         * the size of the map must be decremented.
         */
        --size;

        /**
         * Return the value that was previously associated with the given key.
         */
        return previous.getValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsKey(Object key)
    {
        return lookup(key) != null;
    }

    /**
     * {@inheritDoc}
     */
    private Entry<K, V> lookup(final Object key)
    {
        /**
         * Compute the hash-value of the given key.
         */
        final int hash = Objects.hashCode(key);

        /**
         * Retrieve the list that stores the entry
         * that is associated with the given key.
         */
        FunctionalList<Entry<K, V>> list = map.get(hash);

        /**
         * If the list is null, then the sought after entry does not exist.
         */
        if (list == null)
        {
            return null;
        }

        /**
         * Search for the entry.
         */
        for (Entry<K, V> entry : list)
        {
            /**
             * If entry's key is the given key,
             * then we have found the sought after entry.
             */
            if (entry.getKey().equals(key))
            {
                return entry;
            }
        }

        /**
         * No entry was found with the given key.
         */
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size()
    {
        return size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Map.Entry<K, V>> entrySet()
    {
        final ConcreteMutableHashMap<K, V> SELF = this;

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
                return size;
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

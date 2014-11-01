package autumn.util.ds;

/**
 * An instance of this interface is a persistent map data-structure.
 *
 * @author Mackenzie High
 */
public interface FunctionalMap<K, V>
{
    /**
     * This method creates a mutable copy of this object.
     *
     * @return an mutable copy of this object.
     */
    public MutableMap<K, V> mutable();

    /**
     * This method creates an immutable copy of this object.
     *
     * @return an immutable copy of this object.
     */
    public ImmutableMap<K, V> immutable();

    /**
     * This method non-destructively inserts an entry into this map.
     *
     * <p>
     * If an entry already exists with the given key, the old entry will be replaced.
     * </p>
     *
     * @param key is the key of the new entry.
     * @param value is the value stored in the new entry.
     * @return a modified copy of this map.
     */
    public FunctionalMap<K, V> put(final K key,
                                   final V value);

    /**
     * This method non-destructively removes an entry from this map.
     *
     * @param key is the key that identifies the entry to remove.
     * @return a modified copy of this map.
     */
    public FunctionalMap<K, V> remove(K key);

    /**
     * This method retrieves the value of an entry in this map.
     *
     * @param key is the key that identifies the entry.
     * @return the value stored in the entry, or null, if the entry does not exist.
     */
    public V get(final K key);

    /**
     * This method determines whether this map contains a given key.
     *
     * @param key is the key that this map may contain.
     * @return true, iff this map contains the given key.
     */
    public boolean containsKey(K key);

    /**
     * This method determines whether this map contains a given value.
     *
     * @param value is the value that this map may contain.
     * @return true, iff this map contains the given value.
     */
    public boolean containsValue(V value);

    /**
     * This method calculates the number of entries in this map.
     *
     * @return the number of entries in this map.
     */
    public int size();

    /**
     * This method determines whether this map is empty.
     *
     * @return true, iff size() == 0.
     */
    public boolean isEmpty();

    @Override
    public boolean equals(Object other);

    @Override
    public int hashCode();

    @Override
    public String toString();
}

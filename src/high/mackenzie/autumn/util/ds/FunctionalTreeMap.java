package high.mackenzie.autumn.util.ds;

import autumn.util.ds.ImmutableMap;
import autumn.util.ds.MutableSortedMap;
import high.mackenzie.autumn.util.ds.AvlTree.Node;

/**
 * This class provides a concrete implementation of the FunctionalSortedMap interface.
 *
 * @author Mackenzie High
 */
public final class FunctionalTreeMap<K, V>
{
    private final AvlTree tree;

    /**
     * Constructor.
     *
     * @param tree is the tree that backs this map.
     */
    private FunctionalTreeMap(final AvlTree tree)
    {
        this.tree = tree;
    }

    /**
     * {@inheritDoc}
     */
    public MutableSortedMap<K, V> mutable()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    public ImmutableMap<K, V> immutable()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    public FunctionalTreeMap<K, V> put(final K key,
                                       final V value)
    {
        return new FunctionalTreeMap<K, V>(tree.insert(key, value));
    }

    /**
     * {@inheritDoc}
     */
    public V get(final K key)
    {
        final Node node = tree.find(key);

        return (V) (node == null ? null : node.value);
    }

    /**
     * {@inheritDoc}
     */
    public boolean containsKey(K key)
    {
        return tree.find(key) != null;
    }

    /**
     * {@inheritDoc}
     */
    public boolean containsValue(final V value)
    {
        return mutable().containsValue(value);
    }

    /**
     * {@inheritDoc}
     */
    public int size()
    {
        return tree.size();
    }

    /**
     * {@inheritDoc}
     */
    public FunctionalTreeMap<K, V> remove(K key)
    {
        return new FunctionalTreeMap<K, V>(tree.delete(key));
    }
}

package high.mackenzie.autumn.util.ds;

import autumn.util.ds.ImmutableTreeMap;
import autumn.util.ds.MutableTreeMap;
import high.mackenzie.autumn.util.ds.AvlTree.Node;
import java.util.Iterator;

/**
 * This class provides a concrete implementation of the FunctionalSortedMap interface.
 *
 * @author Mackenzie High
 */
final class FunctionalTreeMap<K, V>
{
    public final ITree tree;

    /**
     * Constructor.
     */
    public FunctionalTreeMap()
    {
        this.tree = new SlowTree();
    }

    /**
     * Constructor.
     *
     * @param tree is the tree that backs this map.
     */
    private FunctionalTreeMap(final ITree tree)
    {
        this.tree = tree;
    }

    /**
     * {@inheritDoc}
     */
    public MutableTreeMap<K, V> mutable()
    {
        return new ConcreteMutableTreeMap(this);
    }

    /**
     * {@inheritDoc}
     */
    public ImmutableTreeMap<K, V> immutable()
    {
        return new ConcreteImmutableTreeMap(this);
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
    public V get(final Object key)
    {
        final Node node = tree.find(key);

        return (V) (node == null ? null : node.value);
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
    public boolean isEmpty()
    {
        return tree.size() == 0;
    }

    /**
     * {@inheritDoc}
     */
    public FunctionalTreeMap<K, V> remove(final Object key)
    {
        return new FunctionalTreeMap<K, V>(tree.delete(key));
    }

    public Iterator<K> keys()
    {
        return tree.keys();
    }

    public boolean contains(final Object key)
    {
        final Node node = tree.find(key);

        return node != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        return tree.toString();
    }
}

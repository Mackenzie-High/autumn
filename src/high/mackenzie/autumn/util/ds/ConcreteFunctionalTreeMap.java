package high.mackenzie.autumn.util.ds;

import autumn.util.ds.FunctionalMap;
import autumn.util.ds.FunctionalSortedMap;
import autumn.util.ds.ImmutableMap;
import autumn.util.ds.MutableSortedMap;
import high.mackenzie.autumn.util.ds.AvlTree.Node;

/**
 * This class provides a concrete implementation of the FunctionalSortedMap interface.
 *
 * @author Mackenzie High
 */
public final class ConcreteFunctionalTreeMap<K, V>
        extends AbstractFunctionalMap<K, V>
        implements FunctionalSortedMap<K, V>
{
    private final AvlTree tree;

    /**
     * Constructor.
     *
     * @param tree is the tree that backs this map.
     */
    private ConcreteFunctionalTreeMap(final AvlTree tree)
    {
        this.tree = tree;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MutableSortedMap<K, V> mutable()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<K, V> immutable()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FunctionalMap<K, V> put(final K key,
                                   final V value)
    {
        return new ConcreteFunctionalTreeMap<K, V>(tree.insert(key, value));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V get(final K key)
    {
        final Node node = tree.find(key);

        return (V) (node == null ? null : node.value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsKey(K key)
    {
        return tree.find(key) != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsValue(final V value)
    {
        return mutable().containsValue(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size()
    {
        return tree.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FunctionalMap<K, V> remove(K key)
    {
        return new ConcreteFunctionalTreeMap<K, V>(tree.delete(key));
    }
}

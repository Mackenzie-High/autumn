package high.mackenzie.autumn.util.ds;

import autumn.util.ds.FunctionalMap;
import autumn.util.ds.ImmutableMap;
import autumn.util.ds.MutableMap;

/**
 * An instance of this class is a concrete implementation of the FunctionalMap interface.
 *
 * @author Mackenzie High
 */
public final class ConcreteFunctionalHashMap<K, V>
        extends AbstractFunctionalMap<K, V>
{
    private final AvlTree tree;

    public ConcreteFunctionalHashMap()
    {
        this(new AvlTree());
    }

    private ConcreteFunctionalHashMap(final AvlTree tree)
    {
        this.tree = tree;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MutableMap<K, V> mutable()
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FunctionalMap<K, V> remove(K key)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V get(K key)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsKey(K key)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsValue(V value)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

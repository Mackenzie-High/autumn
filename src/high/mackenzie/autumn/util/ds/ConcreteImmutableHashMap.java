package high.mackenzie.autumn.util.ds;

import autumn.util.ds.ImmutableHashMap;
import autumn.util.ds.MutableHashMap;
import com.google.common.base.Preconditions;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Stack;

/**
 * This class provides a concrete hashing-based implementation of the ImmutableHashMap interface.
 *
 * @author Mackenzie High
 */
public final class ConcreteImmutableHashMap<K, V>
        extends AbstractMap<K, V>
        implements ImmutableHashMap<K, V>
{
    /**
     * This is the map that maps hash-values to lists of entries,
     * where the aforesaid hash-value is the same as that of each entry's key.
     */
    private final FunctionalTreeMap<Integer, FunctionalList<Entry<K, V>>> map;

    /**
     * This is the total number of entries in this map.
     */
    private final int size;

    /**
     * Sole Constructor.
     *
     * @param map is the object that this object will wrap.
     * @param size is the number of entries in this new hash-map.
     * @throws NullPointerException if map is null.
     */
    ConcreteImmutableHashMap(final FunctionalTreeMap<Integer, FunctionalList<Entry<K, V>>> map,
                             final int size)
    {
        Preconditions.checkNotNull(map);

        this.map = map;
        this.size = size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MutableHashMap<K, V> mutable()
    {
        return new ConcreteMutableHashMap<K, V>(map);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Entry<K, V>> entrySet()
    {
        return new AbstractSet<Entry<K, V>>()
        {
            final Iterator<Integer> keys = map.keys();

            final Stack<Entry<K, V>> stack = new Stack<Entry<K, V>>();

            /**
             * This method is required by AbstractSet.
             */
            @Override
            public Iterator<Entry<K, V>> iterator()
            {
                return new Iterator<Entry<K, V>>()
                {
                    @Override
                    public boolean hasNext()
                    {
                        if (stack.isEmpty() && keys.hasNext())
                        {
                            stack.addAll(map.get(keys.next()).immutable());
                        }

                        return stack.isEmpty() == false;
                    }

                    @Override
                    public Entry<K, V> next()
                    {
                        if (stack.isEmpty())
                        {
                            throw new NoSuchElementException();
                        }
                        else
                        {
                            return stack.pop();
                        }
                    }

                    @Override
                    public void remove()
                    {
                        throw new UnsupportedOperationException("Not Supported");
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
}

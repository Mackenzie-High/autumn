package high.mackenzie.autumn.util.ds;

import autumn.util.ds.ImmutableMap;
import autumn.util.ds.MutableMap;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Stack;

/**
 * This class provides a concrete hashing-based implementation of the ImmutableMap interface.
 *
 * @author Mackenzie High
 */
public final class ConcreteImmutableHashMap<K, V>
        extends AbstractMap<K, V>
        implements ImmutableMap<K, V>
{
    private final FunctionalTreeMap<Integer, FunctionalList<Entry<K, V>>> map;

    public ConcreteImmutableHashMap(final FunctionalTreeMap<Integer, FunctionalList<Entry<K, V>>> map)
    {
        this.map = map;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MutableMap<K, V> mutable()
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

            @Override
            public int size()
            {
                int size = 0;

                final Iterator<Integer> iter = map.keys();

                while (iter.hasNext())
                {
                    size = size + map.get(iter.next()).size();
                }

                return size;
            }
        };
    }
}

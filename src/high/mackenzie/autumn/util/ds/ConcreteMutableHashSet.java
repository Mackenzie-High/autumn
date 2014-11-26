package high.mackenzie.autumn.util.ds;

import autumn.util.ds.ImmutableSet;
import autumn.util.ds.MutableMap;
import autumn.util.ds.MutableSet;
import java.util.AbstractSet;
import java.util.Iterator;

/**
 * This class provides a concrete hashing-based implementation of the MutableSet interface.
 *
 * @author Mackenzie High
 */
public class ConcreteMutableHashSet<E>
        extends AbstractSet<E>
        implements MutableSet<E>
{
    private MutableMap<E, ?> map;

    public ConcreteMutableHashSet()
    {
        this.map = new ConcreteMutableHashMap<E, Object>();
    }

    public ConcreteMutableHashSet(MutableMap<E, ?> map)
    {
        this.map = map.immutable().mutable();
    }

    @Override
    public ImmutableSet<E> immutable()
    {
        return new ConcreteImmutableHashSet<E>(map);
    }

    @Override
    public boolean add(final E value)
    {
        map.put(value, null);

        return true;
    }

    @Override
    public Iterator<E> iterator()
    {
        return map.keySet().iterator();
    }

    @Override
    public int size()
    {
        return map.size();
    }

    public static void main(String[] args)
    {
        final ConcreteMutableHashSet<String> set = new ConcreteMutableHashSet<String>();

        set.add("A");
        set.add("B");
        set.add("C");
        set.add("A");

        set.remove("B");

        System.out.println(set);
    }
}

package high.mackenzie.autumn.util.ds;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

/**
 *
 * @author mackenzie
 */
public class SlowTree
        implements ITree
{
    private final TreeMap<Object, AvlTree.Node> map;

    public SlowTree()
    {
        this.map = new TreeMap();
    }

    public SlowTree(final TreeMap map)
    {
        this.map = new TreeMap(map);
    }

    @Override
    public ITree clear()
    {
        return new SlowTree(new TreeMap());
    }

    @Override
    public ITree delete(Object key)
    {
        final SlowTree result = new SlowTree(map);

        result.map.remove(key);

        return result;
    }

    @Override
    public AvlTree.Node find(Object key)
    {
        return map.get(key);
    }

    @Override
    public AvlTree.Node find(AvlTree.Node node,
                             Object key)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ITree insert(Object key,
                        Object value)
    {
        final SlowTree result = new SlowTree(map);

        result.map.put(key, new AvlTree.Node(key, value, null, null));

        return result;
    }

    @Override
    public Iterator keys()
    {
        return map.keySet().iterator();
    }

    @Override
    public List<AvlTree.Node> nodes()
    {
        return Lists.newArrayList(map.values());
    }

    @Override
    public int size()
    {
        return map.size();
    }

    @Override
    public Iterator values()
    {
        final List list = Lists.newArrayList();

        for (AvlTree.Node node : nodes())
        {
            list.add(node.value);
        }

        return list.iterator();
    }

    @Override
    public String toString()
    {
        return map.toString();
    }
}

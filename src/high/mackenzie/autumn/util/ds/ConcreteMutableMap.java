package high.mackenzie.autumn.util.ds;

import autumn.util.ds.ImmutableMap;
import autumn.util.ds.MutableMap;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author mackenzie
 */
public final class ConcreteMutableMap<K, V>
        extends AbstractMap<K, V>
        implements MutableMap<K, V>
{
    private final FunctionalTreeMap<K, V> map;

    public ConcreteMutableMap(final FunctionalTreeMap<K, V> map)
    {
        this.map = map;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<K, V> immutable()
    {
        return map.immutable();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Map.Entry<K, V>> entrySet()
    {
        return null; // TODO
    }
}

package high.mackenzie.autumn.util.ds;

import autumn.util.ds.FunctionalMap;

/**
 * TODO equals, hash-code, to-string
 *
 * @author mackenzie
 */
public abstract class AbstractFunctionalMap<K, V>
        implements FunctionalMap<K, V>
{
    @Override
    public final boolean isEmpty()
    {
        return size() == 0;
    }
}

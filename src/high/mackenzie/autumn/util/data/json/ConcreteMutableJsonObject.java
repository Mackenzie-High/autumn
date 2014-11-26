package high.mackenzie.autumn.util.data.json;

import autumn.util.data.json.ImmutableJsonObject;
import autumn.util.data.json.JsonValue;
import autumn.util.data.json.MutableJsonObject;
import autumn.util.ds.ImmutableMap;
import autumn.util.ds.MutableMap;
import high.mackenzie.autumn.util.ds.ConcreteMutableHashMap;
import java.util.Map;

/**
 *
 * @author mackenzie
 */
public final class ConcreteMutableJsonObject
        extends AbstractJsonObject
        implements MutableJsonObject
{
    private final MutableMap<String, JsonValue> inner;

    public ConcreteMutableJsonObject()
    {
        this.inner = new ConcreteMutableHashMap();
    }

    public ConcreteMutableJsonObject(final ImmutableMap<String, JsonValue> inner)
    {
        this.inner = inner.mutable();
    }

    @Override
    protected Map<String, JsonValue> delegate()
    {
        return inner;
    }

    @Override
    public ImmutableJsonObject immutable()
    {
        return new ConcreteImmutableJsonObject(inner.immutable());
    }
}

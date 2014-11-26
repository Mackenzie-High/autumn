package high.mackenzie.autumn.util.data.json;

import autumn.util.data.json.ImmutableJsonObject;
import autumn.util.data.json.JsonValue;
import autumn.util.data.json.MutableJsonObject;
import autumn.util.ds.ImmutableMap;

/**
 *
 * @author mackenzie
 */
public final class ConcreteImmutableJsonObject
        extends AbstractJsonObject
        implements ImmutableJsonObject
{
    private final ImmutableMap<String, JsonValue> inner;

    public ConcreteImmutableJsonObject(final ImmutableMap<String, JsonValue> inner)
    {
        this.inner = inner;
    }

    @Override
    public ImmutableMap<String, JsonValue> delegate()
    {
        return inner;
    }

    @Override
    public MutableJsonObject mutable()
    {
        return new ConcreteMutableJsonObject(this);
    }
}

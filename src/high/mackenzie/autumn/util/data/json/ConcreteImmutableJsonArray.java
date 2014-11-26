package high.mackenzie.autumn.util.data.json;

import autumn.util.data.json.ImmutableJsonArray;
import autumn.util.data.json.JsonValue;
import autumn.util.data.json.MutableJsonArray;
import autumn.util.ds.ImmutableList;
import java.util.List;

/**
 *
 * @author mackenzie
 */
public final class ConcreteImmutableJsonArray
        extends AbstractJsonArray
        implements ImmutableJsonArray
{
    private final ImmutableList<JsonValue> inner;

    public ConcreteImmutableJsonArray(final ImmutableList<JsonValue> inner)
    {
        this.inner = inner;
    }

    @Override
    protected List<JsonValue> delegate()
    {
        return inner;
    }

    @Override
    public MutableJsonArray mutable()
    {
        return new ConcreteMutableJsonArray(this);
    }
}

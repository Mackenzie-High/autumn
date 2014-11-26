package high.mackenzie.autumn.util.data.json;

import autumn.util.data.json.ImmutableJsonArray;
import autumn.util.data.json.JsonValue;
import autumn.util.data.json.MutableJsonArray;
import autumn.util.ds.ImmutableList;
import autumn.util.ds.MutableList;
import high.mackenzie.autumn.util.ds.ConcreteMutableList;
import java.util.List;

/**
 *
 * @author mackenzie
 */
public class ConcreteMutableJsonArray
        extends AbstractJsonArray
        implements MutableJsonArray
{
    private final MutableList<JsonValue> inner;

    public ConcreteMutableJsonArray()
    {
        this.inner = new ConcreteMutableList();
    }

    public ConcreteMutableJsonArray(final ImmutableList<JsonValue> inner)
    {
        this.inner = inner.mutable();
    }

    @Override
    protected List<JsonValue> delegate()
    {
        return inner;
    }

    @Override
    public ImmutableJsonArray immutable()
    {
        return new ConcreteImmutableJsonArray(inner.immutable());
    }
}

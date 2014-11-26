package autumn.util.data.json;

import autumn.util.ds.MutableList;

/**
 *
 * @author mackenzie
 */
public interface MutableJsonArray
        extends JsonArray,
                MutableList<JsonValue>
{
    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableJsonArray immutable();
}

package autumn.util.data.json;

import autumn.util.ds.ImmutableList;

/**
 *
 * @author mackenzie
 */
public interface ImmutableJsonArray
        extends JsonArray,
                ImmutableList<JsonValue>
{
    /**
     * {@inheritDoc}
     */
    @Override
    public MutableJsonArray mutable();
}

package autumn.util.data.json;

import autumn.util.ds.MutableMap;

/**
 *
 * @author mackenzie
 */
public interface MutableJsonObject
        extends JsonObject,
                MutableMap<String, JsonValue>
{
    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableJsonObject immutable();
}

package autumn.util.data.json;

import autumn.util.ds.ImmutableMap;

/**
 *
 * @author mackenzie
 */
public interface ImmutableJsonObject
        extends JsonObject,
                ImmutableMap<String, JsonValue>
{
    /**
     * {@inheritDoc}
     */
    @Override
    public MutableJsonObject mutable();
}

package autumn.util.data.json;

import autumn.util.ds.MutableMap;

/**
 * An instance of this interface is a mutable representation of a Json object.
 *
 * @author Mackenzie High
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

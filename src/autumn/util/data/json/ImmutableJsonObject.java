package autumn.util.data.json;

import autumn.util.ds.ImmutableMap;

/**
 * An instance of this interface is an immutable representation of a Json object.
 *
 * @author Mackenzie High
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

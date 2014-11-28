package autumn.util.data.json;

import autumn.util.ds.MutableList;

/**
 * An instance of this interface is a mutable representation of a Json array.
 *
 * @author Mackenzie High
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

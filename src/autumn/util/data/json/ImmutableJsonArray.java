package autumn.util.data.json;

import autumn.util.ds.ImmutableList;

/**
 * An instance of this interface is an immutable representation of a Json array.
 *
 * @author Mackenzie High
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

package autumn.util.data.json;

import java.io.Serializable;
import java.util.Iterator;

/**
 * An instance of this class is an immutable JSON array/list.
 *
 * @author Mackenzie High
 */
public final class JsonArray
        implements Iterable<JsonValue>,
                   Serializable
// TODO: should this implement the FunctionalList interface?
{
    public JsonArray()
    {
    }

    public JsonArray(final JsonTypeSystem design)
    {
    }

    public JsonArray set(final Iterable<? extends JsonValue> elements)
    {
        return null;
    }

    public JsonValue get(int index)
    {
        return null;
    }

    @Override
    public Iterator<JsonValue> iterator()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

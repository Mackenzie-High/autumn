package autumn.util.data.json;

import java.util.Map;

/**
 * An instance of this class is a JSON object.
 *
 * <p>
 * An JSON object may represent a map or a record.
 * </p>
 *
 * @author Mackenzie High
 */
public interface JsonObject
        extends Map<String, JsonValue>,
                JsonValue
{
    /**
     * {@inheritDoc}
     */
    @Override
    public JsonValue get(Object key);
}

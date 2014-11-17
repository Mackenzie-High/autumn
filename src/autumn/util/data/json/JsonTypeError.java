package autumn.util.data.json;

/**
 * This type of exception indicates that a value does not obey its structural invariants.
 *
 * @author Mackenzie High
 */
public final class JsonTypeError
{
    public JsonTypeError(final JsonTypeSystem typesystem,
                         final String type,
                         final JsonValue value)
    {
    }
}

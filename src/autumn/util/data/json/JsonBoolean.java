package autumn.util.data.json;

/**
 * An instance of this class is a JSON boolean value.
 *
 * @author Mackenzie High
 */
public final class JsonBoolean
{
    public static final JsonBoolean TRUE = new JsonBoolean();

    public static final JsonBoolean FALSE = new JsonBoolean();

    /**
     * Sole Constructor.
     */
    private JsonBoolean()
    {
        // Pass
    }

    public JsonBoolean set(final boolean value)
    {
        return value ? TRUE : FALSE;
    }

    public boolean value()
    {
        return this == TRUE;
    }

    @Override
    public String toString()
    {
        return value() ? "true" : "false";
    }
}

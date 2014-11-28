package autumn.util.data.json;

/**
 * An instance of this class is a JSON boolean value.
 *
 * @author Mackenzie High
 */
public interface JsonBoolean
        extends JsonValue
{
    public boolean value();

    @Override
    public String toString();
}

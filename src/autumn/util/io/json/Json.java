package autumn.util.io.json;

/**
 *
 * @author mackenzie
 */
public interface Json
{
    public boolean isArray();

    public boolean isBoolean();

    public boolean isNull();

    public boolean isNumber();

    public boolean isObject();

    public boolean isString();

    @Override
    public String toString();
}

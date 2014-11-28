package high.mackenzie.autumn.util.data.json;

import autumn.util.data.json.JsonObject;
import autumn.util.data.json.JsonValue;
import com.google.common.collect.ForwardingMap;
import java.io.PrintStream;

/**
 *
 * @author mackenzie
 */
public abstract class AbstractJsonObject
        extends ForwardingMap<String, JsonValue>
        implements JsonObject
{
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isArray()
    {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isBoolean()
    {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNumber()
    {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNull()
    {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isObject()
    {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isString()
    {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String toString()
    {
        return super.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void print(PrintStream out)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

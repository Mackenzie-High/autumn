package high.mackenzie.autumn.util.data.json;

import autumn.util.data.json.JsonArray;
import autumn.util.data.json.JsonValue;
import com.google.common.collect.ForwardingList;
import java.io.PrintStream;

/**
 *
 * @author mackenzie
 */
public abstract class AbstractJsonArray
        extends ForwardingList<JsonValue>
        implements JsonArray
{
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isArray()
    {
        return true;
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
        return false;
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

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonValue first()
    {
        return this.get(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonValue last()
    {
        return this.get(this.size() - 1);
    }
}

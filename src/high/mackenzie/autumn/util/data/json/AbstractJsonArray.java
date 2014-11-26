package high.mackenzie.autumn.util.data.json;

import autumn.util.data.json.JsonArray;
import autumn.util.data.json.JsonTypeSystem;
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
    private JsonTypeSystem typesystem;

    /**
     * {@inheritDoc}
     */
    @Override
    public final void enforce(JsonTypeSystem structure)
    {
        this.typesystem = structure;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonTypeSystem typesystem()
    {
        return typesystem;
    }

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
    public boolean isMap()
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

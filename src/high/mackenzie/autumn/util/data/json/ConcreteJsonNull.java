package high.mackenzie.autumn.util.data.json;

import autumn.util.data.json.JsonNull;
import autumn.util.data.json.JsonTypeSystem;
import java.io.PrintStream;

/**
 *
 * @author mackenzie
 */
public class ConcreteJsonNull
        implements JsonNull
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
        return true;
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
        return "null";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void print(final PrintStream out)
    {
        out.print(this);
    }
}

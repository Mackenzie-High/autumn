package high.mackenzie.autumn.util.data.json;

import autumn.util.data.json.JsonNull;
import java.io.PrintStream;

/**
 *
 * @author mackenzie
 */
public class ConcreteJsonNull
        implements JsonNull
{
    public static JsonNull NULL = new ConcreteJsonNull();

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

package high.mackenzie.autumn.util.data.json;

import autumn.util.data.json.JsonBoolean;
import java.io.PrintStream;

/**
 *
 * @author mackenzie
 */
public class ConcreteJsonBoolean
        implements JsonBoolean
{
    public static final JsonBoolean TRUE = new ConcreteJsonBoolean();

    public static final JsonBoolean FALSE = new ConcreteJsonBoolean();

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
        return true;
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
        return value() ? "true" : "false";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void print(final PrintStream out)
    {
        out.print(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean value()
    {
        return this == TRUE;
    }
}

package high.mackenzie.autumn.util.data.json;

import autumn.util.data.json.JsonNumber;
import autumn.util.data.json.JsonTypeSystem;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author mackenzie
 */
public class ConcreteJsonNumber
        implements JsonNumber
{
    private JsonTypeSystem typesystem;

    public BigDecimal value;

    public ConcreteJsonNumber(final BigDecimal value)
    {
        this.value = value;
    }

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
        return true;
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
        return value.toPlainString();
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
    public int asInt()
    {
        return value.intValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long asLong()
    {
        return value.longValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double asDouble()
    {
        return value.doubleValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigInteger asBigInteger()
    {
        return value.toBigInteger();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal asBigDecimal()
    {
        return value;
    }
}

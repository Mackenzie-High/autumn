package high.mackenzie.autumn.util.data.json;

import autumn.util.data.json.JsonNumber;
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
    public BigDecimal value;

    public ConcreteJsonNumber(final BigDecimal value)
    {
        this.value = value;
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
    public void print(final PrintStream out)
    {
        out.print(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte asByte()
    {
        return value.byteValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public short asShort()
    {
        return value.shortValue();
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
    public float asFloat()
    {
        return value.floatValue();
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

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 79 * hash + (this.value != null ? this.value.hashCode() : 0);
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final ConcreteJsonNumber other = (ConcreteJsonNumber) obj;
        if (this.value != other.value && (this.value == null || !this.value.equals(other.value)))
        {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        return value.toString().toLowerCase();
    }
}

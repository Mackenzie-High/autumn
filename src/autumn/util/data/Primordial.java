package autumn.util.data;

import autumn.util.F;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * An instance of this class stores a primordial atomic value.
 *
 * @author Mackenzie High
 */
public final class Primordial
        implements Comparable<Primordial>
{
    private String value;

    public Primordial(final String value)
    {
        this.value = value;
    }

    public boolean isNull()
    {
        return false;
    }

    public boolean isBoolean()
    {
        return false;
    }

    public boolean isByte()
    {
        return false;
    }

    public boolean isShort()
    {
        return false;
    }

    public boolean isInt()
    {
        return false;
    }

    public boolean isLong()
    {
        return false;
    }

    public boolean isFloat()
    {
        return false;
    }

    public boolean isDouble()
    {
        return false;
    }

    public boolean isBigInteger()
    {
        return false;
    }

    public boolean isBigDecimal()
    {
        return false;
    }

    public boolean isString()
    {
        return false;
    }

    public boolean asBoolean()
    {
        return false;
    }

    public byte asByte()
    {
        return 0;
    }

    public short asShort()
    {
        return 0;
    }

    public int asInt()
    {
        return 0;
    }

    public long asLong()
    {
        return 0;
    }

    public float asFloat()
    {
        return 0;
    }

    public double asDouble()
    {
        return 0;
    }

    public BigInteger asBigInteger()
    {
        return null;
    }

    public BigDecimal asBigDecimal()
    {
        return null;
    }

    public String asString()
    {
        return value;
    }

    @Override
    public int compareTo(Primordial o)
    {
        return F.compare(value, o.value);
    }

    @Override
    public boolean equals(final Object other)
    {
        return false;
    }

    @Override
    public int hashCode()
    {
        return 0;
    }

    @Override
    public String toString()
    {
        return asString();
    }
}

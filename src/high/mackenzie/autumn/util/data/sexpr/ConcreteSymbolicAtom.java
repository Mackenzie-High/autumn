package high.mackenzie.autumn.util.data.sexpr;

import autumn.util.data.sexpr.SymbolicAtom;
import com.google.common.base.Preconditions;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * This class provides a concrete implementation of the SymbolicAtom interface.
 *
 * // TODO: should symbolic-atom be renamed symbolic-word, since a string is also atomic?
 *
 * @author Mackenzie High
 */
public class ConcreteSymbolicAtom
        implements SymbolicAtom
{
    /**
     * This is the textual representation of this object.
     */
    private final String value;

    /**
     * Sole Constructor.
     *
     * @param value is the textual representation of the new object.
     */
    public ConcreteSymbolicAtom(final String value)
    {
        Preconditions.checkNotNull(value);

        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean matches(final String regex)
    {
        return value.matches(regex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNumber()
    {
        try
        {
            asBigDecimal();

            return true;
        }
        catch (NumberFormatException ex)
        {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int asInt()
    {
        return Integer.parseInt(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long asLong()
    {
        return Long.parseLong(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float asFloat()
    {
        return Float.parseFloat(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double asDouble()
    {
        return Double.parseDouble(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigInteger asBigInteger()
    {
        return new BigInteger(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal asBigDecimal()
    {
        return new BigDecimal(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAtom()
    {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isList()
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
    public boolean equals(final Object argument)
    {
        if (argument == null)
        {
            return false;
        }

        if (getClass() != argument.getClass())
        {
            return false;
        }

        final ConcreteSymbolicAtom other = (ConcreteSymbolicAtom) argument;

        if ((this.value == null) ? (other.value != null) : !this.value.equals(other.value))
        {
            return false;
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 53 * hash + (this.value != null ? this.value.hashCode() : 0);
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        return value;
    }
}

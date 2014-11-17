package high.mackenzie.autumn.util.data.sexpr;

import autumn.util.data.sexpr.SymbolicString;

/**
 * This class provides a concrete implementation of the SymbolicString interface.
 *
 * @author Mackenzie High
 */
public final class ConcreteSymbolicString
        implements SymbolicString
{
    private final String value;

    public ConcreteSymbolicString(final String value)
    {
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean matches(String regex)
    {
        return value.matches(regex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAtom()
    {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isList()
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
    public int length()
    {
        return value.length();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public char charAt(int index)
    {
        return value.charAt(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CharSequence subSequence(int start,
                                    int end)
    {
        return value.subSequence(start, end);
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

        final ConcreteSymbolicString other = (ConcreteSymbolicString) argument;

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
}

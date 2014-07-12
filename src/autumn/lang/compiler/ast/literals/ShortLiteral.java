package autumn.lang.compiler.ast.literals;

import high.mackenzie.autumn.resources.Finished;

/**
 * An instance of this class represents a
 * <code>short</code> literal.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public final class ShortLiteral
        extends AbstractNumericLiteral<Short>
{
    private Short value = null;

    /**
     * Constructor.
     *
     * @param source is the source-code representation of this literal.
     */
    public ShortLiteral(final String source)
    {
        super(source);
    }

    /**
     * Constructor.
     *
     * @param value is the value represented by this literal.
     */
    public ShortLiteral(final short value)
    {
        super(Short.toString(value) + "S");

        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Short value()
    {
        try
        {
            if (value == null)
            {
                final String source = source().trim().replaceFirst("S", "");

                final Short result = Short.valueOf(source);

                return result;
            }
            else
            {
                return value;
            }
        }
        catch (Exception ex)
        {
            // The literal is malformed.
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isParsable()
    {
        return source().matches("-?[0-9]+S");
    }
}

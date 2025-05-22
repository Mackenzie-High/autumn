package autumn.lang.compiler.ast.literals;

import high.mackenzie.autumn.resources.Finished;

/**
 * An instance of this class represents a
 * <code>byte</code> literal.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public final class ByteLiteral
        extends AbstractNumericLiteral<Byte>
{
    private Byte value = null;

    /**
     * Constructor.
     *
     * @param source is the source-code representation of this literal.
     */
    public ByteLiteral(final String source)
    {
        super(source);
    }

    /**
     * Constructor.
     *
     * @param value is the value represented by this literal.
     */
    public ByteLiteral(final byte value)
    {
        super(Byte.toString(value) + "B");

        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Byte value()
    {
        try
        {
            if (value == null)
            {
                final String source = sourceWithoutUnderscores()
                        .trim()
                        .replaceFirst("B", "")
                        .replaceFirst("b", "");

                final Byte result = Byte.valueOf(source);

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
        return sourceWithoutUnderscores().matches("-?[0-9]+[Bb]");
    }
}

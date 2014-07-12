package autumn.lang.compiler.ast.literals;

import high.mackenzie.autumn.resources.Finished;

/**
 * An instance of this class represents a
 * <code>int</code> literal.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public final class IntLiteral
        extends AbstractNumericLiteral<Integer>
{
    private Integer value = null;

    /**
     * Constructor.
     *
     * @param source is the source-code representation of this literal.
     */
    public IntLiteral(final String source)
    {
        super(source);
    }

    /**
     * Constructor.
     *
     * @param value is the value represented by this literal.
     */
    public IntLiteral(final int value)
    {
        super(Integer.toString(value));

        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value()
    {
        try
        {
            if (value == null)
            {
                final String source = source().trim();

                final Integer result = Integer.valueOf(source);

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
        return source().matches("-?[0-9]+");
    }
}

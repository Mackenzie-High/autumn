package autumn.lang.compiler.ast.literals;

import com.mackenziehigh.autumn.resources.Finished;

/**
 * An instance of this class represents a
 * <code>double</code> literal.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public final class DoubleLiteral
        extends AbstractNumericLiteral<Double>
{
    private Double value = null;

    /**
     * Constructor.
     *
     * @param source is the source-code representation of this literal.
     */
    public DoubleLiteral(final String source)
    {
        super(source);
    }

    /**
     * Constructor.
     *
     * @param value is the value represented by this literal.
     */
    public DoubleLiteral(final double value)
    {
        super(Double.toString(value));

        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double value()
    {
        final Double result;

        try
        {
            if (value == null)
            {
                final String source = sourceWithoutUnderscores().trim();

                result = Double.valueOf(source);
            }
            else
            {
                result = value;
            }
        }
        catch (Exception ex)
        {
            // The literal is malformed.
            return null;
        }

        return (result == null || result.isInfinite() || result.isNaN()) ? null : result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isParsable()
    {
        return sourceWithoutUnderscores().matches("-?[0-9]+([.][0-9]*)?([eE][\\-\\+]?[0-9]+)?");
    }
}

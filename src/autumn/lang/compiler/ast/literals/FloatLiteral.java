package autumn.lang.compiler.ast.literals;

import high.mackenzie.autumn.resources.Finished;

/**
 * An instance of this class represents a
 * <code>float</code> literal.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public final class FloatLiteral
        extends AbstractNumericLiteral<Float>
{
    private Float value = null;

    /**
     * Constructor.
     *
     * @param source is the source-code representation of this literal.
     */
    public FloatLiteral(final String source)
    {
        super(source);
    }

    /**
     * Constructor.
     *
     * @param value is the value represented by this literal.
     */
    public FloatLiteral(final float value)
    {
        super(Float.toString(value) + "F");

        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Float value()
    {
        final Float result;

        try
        {
            if (value == null)
            {
                final String source = source().trim();

                result = Float.valueOf(source);
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
        return source().matches("-?[0-9]+([.][0-9]*)?([eE]-?[0-9]+)?F");
    }
}

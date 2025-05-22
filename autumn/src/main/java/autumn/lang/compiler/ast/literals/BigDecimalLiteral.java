package autumn.lang.compiler.ast.literals;

import autumn.lang.internals.Helpers;
import com.mackenziehigh.autumn.resources.Finished;
import java.math.BigDecimal;

/**
 * An instance of this class represents a
 * <code>double</code> literal.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public final class BigDecimalLiteral
        extends AbstractNumericLiteral<BigDecimal>
{
    private BigDecimal value = null;

    /**
     * Constructor.
     *
     * @param source is the source-code representation of this literal.
     */
    public BigDecimalLiteral(final String source)
    {
        super(source);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal value()
    {
        final BigDecimal result;

        try
        {
            if (value == null)
            {
                final String source = sourceWithoutUnderscores()
                        .trim()
                        .replaceAll("[Bb][Dd]", "");

                result = Helpers.createBigDecimal(source);
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

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isParsable()
    {
        return sourceWithoutUnderscores().matches("-?[0-9]+([.][0-9]*)?([eE][\\-\\+]?[0-9]+)?[Bb][Dd]");
    }
}

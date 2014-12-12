package autumn.lang.compiler.ast.literals;

import high.mackenzie.autumn.resources.Finished;
import java.math.BigInteger;

/**
 * An instance of this class represents a
 * <code>long</code> literal.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public final class BigIntegerLiteral
        extends AbstractNumericLiteral<BigInteger>
{
    private BigInteger value = null;

    /**
     * Constructor.
     *
     * @param source is the source-code representation of this literal.
     */
    public BigIntegerLiteral(final String source)
    {
        super(source);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigInteger value()
    {
        try
        {
            if (value == null)
            {
                final String source = sourceWithoutUnderscores().trim().replaceFirst("BI", "");

                final BigInteger result = new BigInteger(source);

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
        return sourceWithoutUnderscores().matches("-?[0-9]+BI");
    }
}

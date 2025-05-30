package autumn.lang.compiler.ast.literals;

import com.mackenziehigh.autumn.resources.Finished;

/**
 * An instance of this class represents a
 * <code>long</code> literal.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public final class LongLiteral
        extends AbstractNumericLiteral<Long>
{
    private Long value = null;

    /**
     * Constructor.
     *
     * @param source is the source-code representation of this literal.
     */
    public LongLiteral(final String source)
    {
        super(source);
    }

    /**
     * Constructor.
     *
     * @param value is the value represented by this literal.
     */
    public LongLiteral(final long value)
    {
        super(Long.toString(value) + "L");

        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value()
    {
        try
        {
            if (value == null)
            {
                final String source = sourceWithoutUnderscores()
                        .trim()
                        .replaceFirst("L", "")
                        .replaceFirst("l", "");

                final Long result = Long.valueOf(source);

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
        return sourceWithoutUnderscores().matches("-?[0-9]+[Ll]");
    }
}

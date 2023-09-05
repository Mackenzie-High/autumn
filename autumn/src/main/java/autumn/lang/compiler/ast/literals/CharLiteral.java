package autumn.lang.compiler.ast.literals;

import com.mackenziehigh.autumn.resources.Finished;

/**
 * An instance of this class represents a
 * <code>char</code> literal.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public final class CharLiteral
        extends AbstractNumericLiteral<Character>
{
    private static final int MAXIMUM = Character.MAX_VALUE;

    private Character value = null;

    /**
     * Constructor.
     *
     * @param source is the source-code representation of this literal.
     */
    public CharLiteral(final String source)
    {
        super(source);
    }

    /**
     * Constructor.
     *
     * @param value is the value represented by this literal.
     */
    public CharLiteral(final char value)
    {
        super(Long.toString(value) + "C");

        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Character value()
    {
        final String source = source();

        if (source.length() == 3 && source.startsWith("'") && source.endsWith("'"))
        {
            return source.charAt(1);
        }
        else if (source.matches("[0-9]+[Cc]"))
        {
            final int digits = Integer.valueOf(sourceWithoutUnderscores().trim().replace("C", "").replaceFirst("c", ""));

            final boolean conversion_is_safe = digits <= MAXIMUM;

            if (conversion_is_safe)
            {
                return (char) digits;
            }
        }

        // The literal is malformed.
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isParsable()
    {
        return sourceWithoutUnderscores().matches("[0-9]+[Cc]") || source().matches("'(.|\n)'");
    }
}

package autumn.lang.compiler.ast.literals;

import java.math.BigInteger;

/**
 * An instance of this class represents a
 * <code>char</code> literal.
 *
 * @author Mackenzie High
 */
public final class CharLiteral
        extends AbstractNumericLiteral<Character>
{
    private static final BigInteger MAXIMUM = BigInteger.valueOf(Character.MAX_VALUE);

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
        else if (source.matches("[0-9]+C"))
        {
            final BigInteger digits = new BigInteger(source.replace("C", ""));

            final boolean conversion_is_safe = digits.compareTo(MAXIMUM) <= 0;

            if (conversion_is_safe)
            {
                return (char) digits.longValue();
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
        // Note: The \r character is not allowed in 'X' style literals.

        return source().matches("[0-9]+C") || source().matches("'(.|\n)'");
    }
}

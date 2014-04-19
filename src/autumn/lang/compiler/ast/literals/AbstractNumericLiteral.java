package autumn.lang.compiler.ast.literals;

import com.google.common.base.Preconditions;

/**
 * Instances of this class represent numeric literals in Autumn source-code.
 *
 * @author Mackenzie High
 */
public abstract class AbstractNumericLiteral<T>
{
    /**
     * This method converts this literal to its numeric equivalent.
     *
     * @return the numeric representation of this literal,
     *         or null, if a lossless conversion is not possible.
     */
    public abstract T value();

    /**
     * This method determines whether this literal would be recognized by the parser.
     *
     * <p>
     * Note: Under some circumstances, a literal may be malformed and parse-able.
     * </p>
     *
     * @return true, iff the parser would recognize this literal.
     */
    public abstract boolean isParsable();

    private final String source;

    /**
     * Sole Constructor.
     *
     * @param source is this literal as it appeared in the source-code.
     * @throws NullPointerException     if source is null.
     * @throws IllegalArgumentException if source.length() equals zero.
     */
    public AbstractNumericLiteral(final String source)
    {
        Preconditions.checkNotNull(source);
        Preconditions.checkArgument(source.length() > 0);

        this.source = source;
    }

    /**
     * This method returns the source-code representation of this literal.
     *
     * @return this literal, as it appeared in the source code.
     */
    public final String source()
    {
        return source;
    }

    /**
     * This method determines whether this literal can be converted to a number accurately.
     *
     * @return true, if a conversion would lose information.
     */
    public final boolean isMalformed()
    {
        return value() == null;
    }

    /**
     * This method returns a string representation of this literal.
     *
     * @return <code>source()</code>
     */
    @Override
    public final String toString()
    {
        return source();
    }
}

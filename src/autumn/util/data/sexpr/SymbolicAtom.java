package autumn.util.data.sexpr;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * An instance of this interface is a symbol that represents a word or a number,
 *
 * @author Mackenzie High
 */
public interface SymbolicAtom
        extends Symbol
{
    /**
     * This method determines whether this atom matches a given regular expression.
     *
     * @param regex is the regular expression.
     * @return true, iff this atom matches the regular expression.
     */
    public boolean matches(final String regex);

    /**
     * This method determines whether this atom can be converted to a number.
     *
     * @return true, iff this atom can be converted to a number.
     */
    public boolean isNumber();

    /**
     * This method attempts to convert this atom to a number.
     *
     * @return the value of this atom's textual representation as a number.
     * @throws NumberFormatException if the conversion cannot be performed.
     */
    public int asInt();

    /**
     * This method attempts to convert this atom to a number.
     *
     * @return the value of this atom's textual representation as a number.
     * @throws NumberFormatException if the conversion cannot be performed.
     */
    public long asLong();

    /**
     * This method attempts to convert this atom to a number.
     *
     * @return the value of this atom's textual representation as a number.
     * @throws NumberFormatException if the conversion cannot be performed.
     */
    public float asFloat();

    /**
     * This method attempts to convert this atom to a number.
     *
     * @return the value of this atom's textual representation as a number.
     * @throws NumberFormatException if the conversion cannot be performed.
     */
    public double asDouble();

    /**
     * This method attempts to convert this atom to a number.
     *
     * @return the value of this atom's textual representation as a number.
     * @throws NumberFormatException if the conversion cannot be performed.
     */
    public BigInteger asBigInteger();

    /**
     * This method attempts to convert this atom to a number.
     *
     * @return the value of this atom's textual representation as a number.
     * @throws NumberFormatException if the conversion cannot be performed.
     */
    public BigDecimal asBigDecimal();
}

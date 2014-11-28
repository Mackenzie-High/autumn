package autumn.util.data.json;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * An instance of this class represents a number in the JSON format.
 *
 * @author Mackenzie High
 */
public interface JsonNumber
        extends JsonValue
{
    /**
     * This method gets the value of this number.
     *
     * @return the value of this number.
     */
    public byte asByte();

    /**
     * This method gets the value of this number.
     *
     * @return the value of this number.
     */
    public short asShort();

    /**
     * This method gets the value of this number.
     *
     * @return the value of this number.
     */
    public int asInt();

    /**
     * This method gets the value of this number.
     *
     * @return the value of this number.
     */
    public long asLong();

    /**
     * This method gets the value of this number.
     *
     * @return the value of this number.
     */
    public float asFloat();

    /**
     * This method gets the value of this number.
     *
     * @return the value of this number.
     */
    public double asDouble();

    /**
     * This method gets the value of this number.
     *
     * @return the value of this number.
     */
    public BigInteger asBigInteger();

    /**
     * This method gets the value of this number.
     *
     * @return the value of this number.
     */
    public BigDecimal asBigDecimal();
}

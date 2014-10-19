package autumn.util.tables;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author mackenzie
 */
public interface Cell
{
    /**
     * This method retrieves the row that contains this cell.
     *
     * @return the enclosing row.
     */
    public Row row();

    public byte asByte();

    public short asShort();

    public int asInt();

    public long asLong();

    public float asFloat();

    public double asDouble();

    public BigInteger asBigInteger();

    public BigDecimal asBigDecimal();

    public String asString();
}

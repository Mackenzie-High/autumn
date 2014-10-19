package autumn.util.tables;

import autumn.lang.Record;
import java.util.Map;

/**
 *
 * @author mackenzie
 */
public interface Row
{
    /**
     * This method retrieves the table that contains this row.
     *
     * @return the enclosing table.
     */
    public Table table();

    /**
     * This method creates a record based representation of this row.
     *
     * @param prototype is an instance of the record type.
     * @return a copy of the prototype with the values of the cells in this row in the record.
     */
    public Record assign(final Record prototype);

    public void update(final Record input);

    public void update(final Map<String, Object> input);

    public Cell find(final String name);
}

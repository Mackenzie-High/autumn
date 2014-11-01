package autumn.util.ds.tables;

import java.util.List;

/**
 * An instance of this interface is a row in a table.
 *
 * @author Mackenzie High
 */
public interface TableRow
{
    /**
     * This method retrieves the table that contains this row.
     *
     * @return the enclosing table.
     */
    public Table table();

    /**
     * This method creates a list of the cells in the key-part of this row.
     *
     * @return an immutable list of all the cells in this key.
     */
    public List<TableCell> key();

    /**
     * This method creates a list of the cells in this row.
     *
     * @return an immutable list of all the cells in this row.
     */
    public List<TableCell> cells();
}

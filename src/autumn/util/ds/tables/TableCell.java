package autumn.util.ds.tables;

import autumn.util.data.Primordial;

/**
 * An instance of this interface is a cell in a row in a table.
 *
 * @author Mackenzie High
 */
public interface TableCell
{
    /**
     * This method retrieves the row that contains this cell.
     *
     * @return the enclosing row.
     */
    public TableRow row();

    /**
     * This method sets the data that is stored in this cell.
     *
     * @param value is the new value stored in this cell.
     */
    public void set(final Primordial value);

    /**
     * This method gets the data that is stored in this cell.
     *
     * @return the data that is currently stored in this cell, or null, if no such data exists.
     */
    public Primordial get();
}

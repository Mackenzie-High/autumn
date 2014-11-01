package autumn.util.ds.tables;

import java.util.Set;

/**
 * An instance of this interface is a tabular data-structure for primordial data.
 *
 * @author Mackenzie High
 */
public interface Table
{
    /**
     * This method creates a set containing the names of the columns in this table.
     *
     * <p>
     * Changes to the returned set will be reflected in the table.
     * </p>
     *
     * @return the names of the columns herein.
     */
    public Set<String> headers();

    /**
     * This method creates a set containing the names of the columns that are part of the key.
     *
     * <p>
     * Changes to the returned set will be reflected in the table.
     * </p>
     *
     * @return the name of the columns in the key.
     */
    public Set<String> key();

    /**
     * This method creates an a set-view of the rows in this table.
     *
     * <p>
     * Changes to the returned set will be reflected in the table.
     * </p>
     *
     * @return a set-view, backed by this object, that exposes the rows in this table.
     */
    public Set<TableRow> rows();

    /**
     * This method counts the number of rows in this table.
     *
     * @return the number of rows in this table.
     */
    public int size();

    /**
     * This method determines whether this table is empty.
     *
     * @return true, if this table has zero rows in it.
     */
    public boolean isEmpty();
}

package autumn.util.ds.tables;

import autumn.util.functors.Predicate;
import autumn.util.data.csv.CsvObject;
import com.sun.rowset.internal.Row;

/**
 * This class provides static utility methods for creating, manipulating, and exporting tables.
 *
 * @author Mackenzie High
 */
public final class Tables
{
    public static Table create(final CsvObject object)
    {
        return null;
    }

    public static Table union(final Table left,
                              final Table right)
    {
        return null;
    }

    public static Table intersect(final Table left,
                                  final Table right)
    {
        return null;
    }

    public static Table subtract(final Table left,
                                 final Table right)
    {
        return null;
    }

    /**
     * This method creates an iterable whose iterator produces the rows in a table that
     * satisfy a given condition predicate.
     *
     * @param table is the table that contains the rows.
     * @param condition is the condition that the rows must satisfy.
     * @return the aforedescribed iterable.
     */
    public static Iterable<Row> find(final Table table,
                                     final Predicate condition)
    {
        return null;
    }
}

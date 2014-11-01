package autumn.util.data.json;

import autumn.lang.Record;
import autumn.util.data.sexpr.Symbol;

/**
 * This class provides static utility methods for working with JSON data.
 *
 * @author Mackenzie High
 */
public class Json
{
    /**
     * This method converts a JSON data object to a record.
     *
     * @param prototype is a prototype of the output record.
     * @param data is the data to convert.
     * @return a new record that represents the
     */
    public static Record convertToRecord(final Record prototype,
                                         final JsonValue data)
    {
        return null;
    }

    public static Symbol convertToSymbolicExpression(final JsonValue value)
    {
        return null;
    }

    public static JsonValue convertToJson(final Record data)
    {
        return null;
    }

    public static JsonValue convertToJson(final Symbol sexpr)
    {
        return null;
    }

    /**
     * This method sets a node in a JSON data tree.
     *
     * @param root is the root of the data tree.
     * @param path is the path to the node to set.
     * @param value is the new node.
     * @return the new root.
     */
    public static JsonValue set(final JsonValue root,
                                final Iterable<?> path,
                                final JsonValue value)
    {
        return null;
    }

    /**
     * This method gets a node in a JSON data tree.
     *
     * @param root is the root of the data tree.
     * @param path is the path to the node to set.
     * @return the node at the path; or null, if the no such node exists.
     */
    public static JsonValue get(final JsonValue root,
                                final Iterable<?> path)
    {
        return null;
    }
}

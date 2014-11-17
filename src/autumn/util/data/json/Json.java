package autumn.util.data.json;

import autumn.lang.Record;
import autumn.util.data.sexpr.Symbol;
import com.google.common.base.Preconditions;
import high.mackenzie.autumn.util.data.json.ConcreteJsonArray;
import high.mackenzie.autumn.util.data.json.ConcreteJsonBoolean;
import high.mackenzie.autumn.util.data.json.ConcreteJsonMap;
import high.mackenzie.autumn.util.data.json.ConcreteJsonNull;
import high.mackenzie.autumn.util.data.json.ConcreteJsonNumber;
import high.mackenzie.autumn.util.data.json.JsonParser;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

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

    public static JsonValue parse(final String input)
    {
        return JsonParser.parse(input);
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

    /**
     * This method creates a new and empty array.
     *
     * @return the new JSON array object.
     */
    public static JsonArray array()
    {
        return new ConcreteJsonArray();
    }

    /**
     * This method creates a new array that contains a given number of JSON null values.
     *
     * @param size is the number of elements in the new array.
     * @return the new JSON array object.
     */
    public static JsonArray array(int size)
    {
        return array(size, nil());
    }

    /**
     * This method creates a new array that contains a given number of JSON values.
     *
     * @param size is the number of elements in the new array.
     * @param value is the default value of the initial elements in the new array.
     * @return the new JSON array object.
     */
    public static JsonArray array(int size,
                                  JsonValue value)
    {
        final ConcreteJsonArray array = new ConcreteJsonArray();

        for (int i = 0; i < size; i++)
        {
            array.add(value);
        }

        return array;
    }

    /**
     * This method creates a new boolean value.
     *
     * @param value is the boolean value.
     * @return the new JSON boolean value.
     */
    public static JsonBoolean bool(boolean value)
    {
        return new ConcreteJsonBoolean(value);
    }

    /**
     * This method creates the null value.
     *
     * @return the JSON null value.
     */
    public static JsonNull nil()
    {
        return new ConcreteJsonNull();
    }

    /**
     * This method creates a new numeric value.
     *
     * @param value is the number value.
     * @return the JSON numeric value.
     */
    public static JsonNumber number(long value)
    {
        Preconditions.checkNotNull(value, "value");

        return new ConcreteJsonNumber(new BigDecimal(value));
    }

    /**
     * This method creates a new numeric value.
     *
     * @param value is the number value.
     * @return the JSON numeric value.
     */
    public static JsonNumber number(double value)
    {
        Preconditions.checkNotNull(value, "value");

        return new ConcreteJsonNumber(new BigDecimal(value));
    }

    /**
     * This method creates a new numeric value.
     *
     * @param value is the number value.
     * @return the JSON numeric value.
     */
    public static JsonNumber number(BigInteger value)
    {
        Preconditions.checkNotNull(value, "value");

        return new ConcreteJsonNumber(new BigDecimal(value));
    }

    /**
     * This method creates a new numeric value.
     *
     * @param value is the number value.
     * @return the JSON numeric value.
     */
    public static JsonNumber number(BigDecimal value)
    {
        Preconditions.checkNotNull(value, "value");

        return new ConcreteJsonNumber(value);
    }

    /**
     * This method creates a new numeric value.
     *
     * <p>
     * The value must be one of:
     * <ul>
     * <li>byte</li>
     * <li>short</li>
     * <li>int</li>
     * <li>long</li>
     * <li>float</li>
     * <li>double</li>
     * <li>BigInteger</li>
     * <li>BigDecimal</li>
     * </ul>
     * </p>
     *
     * @param value is the number value in textual format.
     * @return the JSON numeric value.
     * @throws NumberFormatException if the value cannot be parsed.
     */
    public static JsonNumber number(final String value)
    {
        return number(new BigDecimal(value));
    }

    /**
     * This method creates a new map.
     *
     * @return the JSON map.
     */
    public static JsonMap map()
    {
        return new ConcreteJsonMap();
    }

    /**
     * This method creates a new map.
     *
     * @param map contains the initial entries of the new map.
     * @return the JSON map.
     */
    public static JsonMap map(Map<JsonValue, JsonValue> map)
    {
        final ConcreteJsonMap result = new ConcreteJsonMap();

        result.putAll(map);

        return result;
    }
}

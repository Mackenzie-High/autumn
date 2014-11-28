package autumn.util.data.json;

import autumn.lang.Record;
import autumn.util.F;
import autumn.util.data.sexpr.Symbol;
import com.google.common.base.Preconditions;
import high.mackenzie.autumn.util.data.json.ConcreteJsonBoolean;
import high.mackenzie.autumn.util.data.json.ConcreteJsonNull;
import high.mackenzie.autumn.util.data.json.ConcreteJsonNumber;
import high.mackenzie.autumn.util.data.json.ConcreteMutableJsonArray;
import high.mackenzie.autumn.util.data.json.ConcreteMutableJsonObject;
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
        final ConcreteMutableJsonArray array = new ConcreteMutableJsonArray();

        for (int i = 0; i < size; i++)
        {
            array.add(value);
        }

        return array.immutable();
    }

    /**
     * This method creates a new array.
     *
     * @param values are the elements of the array.
     * @return the new JSON array object.
     */
    public static JsonArray array(final Iterable values)
    {
        final ConcreteMutableJsonArray array = new ConcreteMutableJsonArray();

        for (Object value : values)
        {
            array.add((JsonValue) value); // TODO: should an automatic conversion take place, if necessary, rather than cast?
        }

        return array.immutable();
    }

    /**
     * This method creates a new boolean value.
     *
     * @param value is the boolean value.
     * @return the new JSON boolean value.
     */
    public static JsonBoolean bool(boolean value)
    {
        return value ? ConcreteJsonBoolean.TRUE : ConcreteJsonBoolean.FALSE;
    }

    /**
     * This method creates the null value.
     *
     * @return the JSON null value.
     */
    public static JsonNull nil()
    {
        return ConcreteJsonNull.NULL;
    }

    /**
     * This method creates a new numeric value.
     *
     * @param value is the number value.
     * @return the JSON numeric value.
     */
    public static JsonNumber number(final long value)
    {
        Preconditions.checkNotNull(value, "value");

        return new ConcreteJsonNumber(F.big(new BigDecimal(value)));
    }

    /**
     * This method creates a new numeric value.
     *
     * @param value is the number value.
     * @return the JSON numeric value.
     */
    public static JsonNumber number(final double value)
    {
        Preconditions.checkNotNull(value, "value");

        return new ConcreteJsonNumber(F.big(new BigDecimal(value)));
    }

    /**
     * This method creates a new numeric value.
     *
     * @param value is the number value.
     * @return the JSON numeric value.
     */
    public static JsonNumber number(final BigInteger value)
    {
        Preconditions.checkNotNull(value, "value");

        return new ConcreteJsonNumber(F.big(new BigDecimal(value)));
    }

    /**
     * This method creates a new numeric value.
     *
     * @param value is the number value.
     * @return the JSON numeric value.
     */
    public static JsonNumber number(final BigDecimal value)
    {
        Preconditions.checkNotNull(value, "value");

        return new ConcreteJsonNumber(F.big(value));
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
        return new ConcreteJsonNumber(F.big(new BigDecimal(value)));
    }

    /**
     * This method creates a new object.
     *
     * @param map contains the initial entries of the new object.
     * @return the JSON object.
     */
    public static JsonObject map(final Map<String, JsonValue> map)
    {
        final ConcreteMutableJsonObject builder = new ConcreteMutableJsonObject();

        builder.putAll(map);

        return builder.immutable();
    }
}

package autumn.util.data.json;

import java.io.PrintStream;
import java.io.Serializable;

/**
 * An instance of this interface is a part of a JSON data tree.
 *
 * @author Mackenzie High
 */
public interface JsonValue
        extends Serializable
{
    /**
     * This method applies a type-system to this value; thereby, causing this value to be typed.
     *
     * @param structure is the type-system that this object must subsequently obey (may be null).
     */
    public void enforce(final JsonTypeSystem structure);

    /**
     * This method retrieves the type-system that this object obeys.
     *
     * @return the obeyed type-system, or null, if this object is untyped.
     */
    public JsonTypeSystem typesystem();

    /**
     * This method determines whether this object represents a JSON array.
     *
     * @return true, iff this object represents a JSON array.
     */
    public boolean isArray();

    /**
     * This method determines whether this object represents a JSON boolean value.
     *
     * @return true, iff this object represents a JSON boolean value.
     */
    public boolean isBoolean();

    /**
     * This method determines whether this object represents a JSON numeric value.
     *
     * @return true, iff this object represents a JSON numeric value.
     */
    public boolean isNumber();

    /**
     * This method determines whether this object represents a JSON null value.
     *
     * @return true, iff this object represents a JSON null value.
     */
    public boolean isNull();

    /**
     * This method determines whether this object represents a JSON map.
     *
     * @return true, iff this object represents a JSON map.
     */
    public boolean isMap();

    /**
     * This method determines whether this object represents a JSON string.
     *
     * @return true, iff this object represents a JSON string.
     */
    public boolean isString();

    /**
     * This method prints the string representation of this value to an output-stream.
     *
     * @param out is the output-stream.
     */
    public void print(PrintStream out);

    /**
     * This method creates the string representation of this JSON value.
     *
     * @return the parsable string representation of this value.
     */
    @Override
    public String toString();
}

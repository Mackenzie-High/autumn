package autumn.util.data.json;

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
     * This method determines whether this object represents a JSON array.
     *
     * @return true, iff this object represents a JSON array.
     */
    public boolean isArray();

    /**
     * This method determines whether this object represents a JSON atom.
     *
     * @return true, iff this object represents a JSON atom.
     */
    public boolean isAtom();

    /**
     * This method determines whether this object represents a JSON object.
     *
     * @return true, iff this object represents a JSON object.
     */
    public boolean isObject();

    /**
     * This method determines whether this object represents a JSON string.
     *
     * @return true, iff this object represents a JSON string.
     */
    public boolean isString();

    @Override
    public String toString();
}

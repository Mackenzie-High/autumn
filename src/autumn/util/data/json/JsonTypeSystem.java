package autumn.util.data.json;

/**
 * An instance of this interface can be used to impose a mandatory structure on JSON data.
 *
 * <p>
 * <b>Predefined Types:</b>
 * <ul>
 * <li>boolean</li>
 * <li>char</li>
 * <li>byte</li>
 * <li>short</li>
 * <li>int</li>
 * <li>long</li>
 * <li>float</li>
 * <li>double</li>
 * <li>BigInteger</li>
 * <li>BigInteger</li>
 * <li>String</li>
 * </ul>
 * </p>
 *
 * @author Mackenzie High
 */
public final class JsonTypeSystem
{
    /**
     * This method creates a new list-type.
     *
     * @param name is the name of the new type.
     * @param element is the type of elements that the list can contain.
     * @return the modified copy of this object.
     */
    public JsonTypeSystem list(final String name,
                               final String element)
    {
        return null;
    }

    /**
     * This method creates a new map-type.
     *
     * @param name is the name of the new type.
     * @param key is the type of keys that the list can contain.
     * @param value is the type of values that the list can contain.
     * @return the modified copy of this object.
     */
    public JsonTypeSystem map(final String name,
                              final String key,
                              final String value)
    {
        return null;
    }

    /**
     * This method creates a new record-type.
     *
     * @param name is the name of the new type.
     * @return the modified copy of this object.
     */
    public JsonTypeSystem record(final String name)
    {
        return null;
    }

    /**
     * This method creates a new record-type that inherits from other types.
     *
     * @param name is the name of the new type.
     * @param supers are the names of the parent record-types.
     * @return the modified copy of this object.
     */
    public JsonTypeSystem record(final String name,
                                 final Iterable<String> supers)
    {
        return null;
    }

    /**
     * This method adds a field to an record-type.
     *
     * @param owner is the name of the record-type.
     * @param field is the name of the new field.
     * @param type is the type of value that the field can contain.
     * @return the modified copy of this object.
     */
    public JsonTypeSystem element(final String owner,
                                  final String field,
                                  final String type)
    {
        return null;
    }

    /**
     * This method verifies that all this object represents a well-defined type-system.
     *
     * @param raise indicates whether to throw an exception, iff the type-system contains an error.
     * @return true, iff the type-system is error-free.
     * @throws IllegalStateException if <i>raise</i> is true and an error is detected.
     */
    public boolean validate(final boolean raise)
    {
        return false;
    }

    public void check(final JsonArray value)
    {
    }

    public void check(final JsonMap value)
    {
    }
}

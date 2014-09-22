package autumn.lang;

/**
 * An instance of this interface is a struct.
 *
 * @author Mackenzie High
 */
public interface Struct
        extends Record
{
    /**
     * {@inheritDoc}
     */
    @Override
    public Struct clear();

    /**
     * {@inheritDoc}
     */
    @Override
    public Struct set(String key,
                      Object value);

    /**
     * {@inheritDoc}
     */
    @Override
    public Struct copy();

    /**
     * {@inheritDoc}
     */
    @Override
    public Struct immutable();

    /**
     * {@inheritDoc}
     */
    @Override
    public Struct mutable();

    /**
     * This method creates a string representation of this struct.
     *
     * <p>
     * The string representation of a record is a brace enclosed list of the elements therein.
     * <br>
     * Example:
     * <code>
     * { name=Mackenzie, age=24 }
     * </code>
     * </p>
     *
     * <p>
     * This method's default behavior can be replaced by binding a method handler to the object.
     * </p>
     *
     * @return the string representation of this struct.
     */
    @Override
    public String toString();
}

package autumn.lang;

import java.util.Map;

/**
 * An instance of this interface is a lambda function.
 *
 * @author Mackenzie
 */
public interface Lambda
        extends TypedFunctor
{
    /**
     * This method is invoked to capture the state of a variable.
     *
     * @param name is the name of the variable.
     * @param value is the value to capture.
     * @throws NullPointerException if the name is null.
     * @throws IllegalArgumentException if the variable was already captured.
     */
    public void capture(final String name,
                        final Object value);

    /**
     * This method retrieves the value of a previously captured variable as a boolean.
     *
     * @param name is the name of the captured variable.
     * @return the value that was in the variable at the time it was captured.
     * @throws NullPointerException if the name is null.
     * @throws IllegalStateException if the name does not refer to a captured variable.
     */
    public boolean valueAsBoolean(final String name);

    /**
     * This method retrieves the value of a previously captured variable as a char.
     *
     * @param name is the name of the captured variable.
     * @return the value that was in the variable at the time it was captured.
     * @throws NullPointerException if the name is null.
     * @throws IllegalStateException if the name does not refer to a captured variable.
     */
    public char valueAsChar(final String name);

    /**
     * This method retrieves the value of a previously captured variable as a byte.
     *
     * @param name is the name of the captured variable.
     * @return the value that was in the variable at the time it was captured.
     * @throws NullPointerException if the name is null.
     * @throws IllegalStateException if the name does not refer to a captured variable.
     */
    public byte valueAsByte(final String name);

    /**
     * This method retrieves the value of a previously captured variable as a short.
     *
     * @param name is the name of the captured variable.
     * @return the value that was in the variable at the time it was captured.
     * @throws NullPointerException if the name is null.
     * @throws IllegalStateException if the name does not refer to a captured variable.
     */
    public short valueAsShort(final String name);

    /**
     * This method retrieves the value of a previously captured variable as a int.
     *
     * @param name is the name of the captured variable.
     * @return the value that was in the variable at the time it was captured.
     * @throws NullPointerException if the name is null.
     * @throws IllegalStateException if the name does not refer to a captured variable.
     */
    public int valueAsInt(final String name);

    /**
     * This method retrieves the value of a previously captured variable as a long.
     *
     * @param name is the name of the captured variable.
     * @return the value that was in the variable at the time it was captured.
     * @throws NullPointerException if the name is null.
     * @throws IllegalStateException if the name does not refer to a captured variable.
     */
    public long valueAsLong(final String name);

    /**
     * This method retrieves the value of a previously captured variable as a float.
     *
     * @param name is the name of the captured variable.
     * @return the value that was in the variable at the time it was captured.
     * @throws NullPointerException if the name is null.
     * @throws IllegalStateException if the name does not refer to a captured variable.
     */
    public float valueAsFloat(final String name);

    /**
     * This method retrieves the value of a previously captured variable as a double.
     *
     * @param name is the name of the captured variable.
     * @return the value that was in the variable at the time it was captured.
     * @throws NullPointerException if the name is null.
     * @throws IllegalStateException if the name does not refer to a captured variable.
     */
    public double valueAsDouble(final String name);

    /**
     * This method retrieves the value of a previously captured variable as a Object.
     *
     * @param name is the name of the captured variable.
     * @return the value that was in the variable at the time it was captured.
     * @throws NullPointerException if the name is null.
     * @throws IllegalStateException if the name does not refer to a captured variable.
     */
    public Object valueAsObject(final String name);

    /**
     * This method creates a map representation of the closure.
     *
     * @return an immutable map containing the values of the captured variables.
     */
    public Map<String, Object> closure();
}

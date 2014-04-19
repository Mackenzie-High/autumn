package autumn.lang;

import com.google.common.base.Preconditions;
import java.util.Objects;

/**
 * An instance of this class represents a single local-variable in a locals-map.
 *
 * @author Mackenzie High
 */
public final class Local
{
    /**
     * This is the name of the variable.
     */
    private final String name;

    /**
     * This is the compile-time type of the variable.
     */
    private final Class type;

    /**
     * This is the value stored in the variable at the time of this object's creation.
     */
    private final Object value;

    /**
     * Constructor.
     *
     * @param name is the name of the variable.
     * @param type is the static-type of the variable.
     * @param value is the value of the variable.
     */
    public Local(final String name,
                 final Class type,
                 final Object value)
    {
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(type);

        this.name = name;
        this.type = type;
        this.value = value;
    }

    /**
     * This method retrieves the name of the variable.
     *
     * @return the name of the variable.
     */
    public String name()
    {
        return name;
    }

    /**
     * This method retrieves the type of the variable.
     *
     * @return the type of the variable.
     */
    public Class type()
    {
        return type;
    }

    /**
     * This method retrieves the value of the variable.
     *
     * <p>
     * The value returned is the value of the variable, as it was when this object was created.
     * </p>
     *
     * @return the value of the variable.
     */
    public Object value()
    {
        return value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        final String string = Objects.toString(value);

        final String modified = string.replace("\r", "\\r").replace("\n", "\\n");

        return name + " : " + type.getSimpleName() + " = " + modified;
    }
}

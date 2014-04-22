package autumn.lang.reflect;

import com.google.common.base.Preconditions;

/**
 * An instance of this class can be used to identify a property in a prototype object.
 *
 * @author Mackenzie High
 */
public final class PropertyKey
{
    private final String name;

    public PropertyKey(final String name)
    {
        Preconditions.checkNotNull(name);

        this.name = name;
    }

    public String name()
    {
        return name;
    }
}

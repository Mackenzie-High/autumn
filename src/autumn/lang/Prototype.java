package autumn.lang;

import java.util.List;

/**
 * This interface is a supertype of all objects that are instantiated Autumn designs.
 *
 * <p>
 * The methods defined in this interface start with "object" in order to avoid name collisions
 * with user defined methods in implementations of this interface.
 * </p>
 *
 * @author Mackenzie High
 */
public interface Prototype
{
    /**
     * This method creates a shallow copy of this object and then returns the copy.
     *
     * @return a shallow copy of this object.
     */
    public Prototype objectCopy();

    /**
     * This method retrieves a list of all the properties and methods in this object.
     *
     * <p>
     * This is a constant-time operation.
     * </p>
     *
     * @return an immutable list containing the properties and methods in the object.
     */
    public List<Member> objectMembers();

    @Override
    public boolean equals(Object o);

    @Override
    public int hashCode();

    @Override
    public String toString();
}

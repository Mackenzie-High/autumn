package autumn.lang.internals.proto;

/**
 * An instance of this class is a low-level implementation of a member of a prototype object.
 *
 * <p>
 * Low level members lack a reference to the prototype that contains them.
 * As a result, different prototype objects can contain the same low-level member objects.
 * In short, this facilitates the usage of the Flyweight design-pattern behind the scenes.
 * </p>
 *
 * @author Mackenzie High
 */
public abstract class AbstractLowLevelMember
{
    /**
     * This method determines whether this object represents a property.
     *
     * @return true, iff this object represents a property in a prototype object.
     */
    public abstract boolean isProperty();

    /**
     * This method determines whether this object represents a method.
     *
     * @return true, iff this object represents a method in a prototype object.
     */
    public abstract boolean isMethod();
}

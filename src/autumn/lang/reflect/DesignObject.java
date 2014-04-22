package autumn.lang.reflect;

import autumn.lang.Prototype;
import java.util.Collection;

/**
 * An instance of this class exposes the state of an object that is the instantiation of a design.
 *
 * @author Mackenzie High
 */
public interface DesignObject
{
    /**
     * This method retrieves the prototype object itself.
     *
     * @return the viewed object.
     */
    public Prototype object();

    /**
     * This method retrieves the class representation of the design of the prototype.
     *
     * @return the prototype's design.
     */
    public Class design();

    /**
     * This method retrieves a description of the property that that is identified by a given key.
     *
     * @param key is the key that identifies the property to retrieve.
     * @return the identified property, or null, if the property does not exist.
     */
    public DesignProperty getProperty(final PropertyKey key);

    /**
     * This method retrieves a description of the method that that is identified by a given key.
     *
     * @param key is the key that identifies the method to retrieve.
     * @return the identified method, or null, if the method does not exist.
     */
    public DesignMethod getMethod(final MethodKey key);

    /**
     * This method creates a collection of descriptions that describe the properties in the object.
     *
     * @return the aforesaid immutable collection.
     */
    public Collection<DesignProperty> properties();

    /**
     * This method creates a collection of descriptions that describe the methods in the object.
     *
     * @return the aforesaid immutable collection.
     */
    public Collection<DesignMethod> methods();

    /**
     * This method retrieves the object that the prototype object is composed of.
     *
     * @return the prototype object's inner object.
     */
    public Object innerObject();
}

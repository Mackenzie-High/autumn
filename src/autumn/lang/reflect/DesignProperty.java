package autumn.lang.reflect;

import autumn.lang.Functor;
import autumn.lang.Prototype;

/**
 * An instance of this interface describes a property within a prototype object.
 *
 * @author Mackenzie High
 */
public interface DesignProperty
{
    public DesignObject owner();

    /**
     * This method retrieves the object that identifies the property.
     *
     * @return the key that identifies the property.
     */
    public PropertyKey key();

    /**
     * This method retrieves the class representation of the property's type.
     *
     * @return type that indicates the type of value that can be placed into the property.
     */
    public Class type();

    /**
     * This method determines whether the property is mutable.
     *
     * @return true, iff the property is mutable.
     */
    public boolean isMutable();

    /**
     * This method determines whether the property is synchronized.
     *
     * @return true, iff the property is synchronized.
     */
    public boolean isSync();

    /**
     * This method determines whether the property is nullable.
     *
     * @return true, if the property is nullable.
     */
    public boolean isNullable();

    /**
     * This method gets the value that is currently stored in the property.
     *
     * @return the property's current value.
     * @throws TODO: something, iff the field is unset
     */
    public Object get();

    /**
     * This method sets the value that is stored in the property.
     *
     * @param value is the new value of the property.
     * @return the prototype that contains the property, if the property is mutable.
     * If the property is immutable, return a modified copy of the prototype.
     */
    public Prototype set(final Object value);

    /**
     * This method retrieves the setter associated with the property, if any.
     *
     * @return the property's setter, or null, if the property does not have a setter.
     */
    public Functor setter();

    /**
     * This method retrieves the getter associated with the property, if any.
     *
     * @return the property's getter, or null, if the property does not have a getter.
     */
    public Functor getter();

    /**
     * This method determines whether the property has been set at least once.
     *
     * @return true, if the property has been assigned a value.
     */
    public boolean isSet();
}

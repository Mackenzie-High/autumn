package autumn.lang;

import autumn.lang.reflect.DesignObject;
import autumn.lang.reflect.MethodKey;
import autumn.lang.reflect.PropertyKey;

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
     * This method returns an object that exposes the internal state of this object.
     *
     * @return an object that provides a view of this object's state.
     */
    public DesignObject objectState();

    /**
     * This method non-destructively sets the implementation of a setter method.
     *
     * @param key is the name + descriptor of the setter to bind a handler to.
     * @param handler is the new implementation of the setter (may be null).
     * @return a copy of this object, with the new setter implementation in it.
     * @throws NoSuchMethodException if the setter to define cannot be determined.
     */
    public Prototype objectSetSetter(PropertyKey key,
                                     Functor handler);

    /**
     * This method non-destructively sets the implementation of a getter method.
     *
     * @param key is the name + descriptor of the getter to bind a handler to.
     * @param handler is the new implementation of the getter (may be null).
     * @return a copy of this object, with the new getter implementation in it.
     * @throws NoSuchMethodException if the getter to define cannot be determined.
     */
    public Prototype objectSetGetter(PropertyKey key,
                                     Functor handler);

    /**
     * This method non-destructively sets the implementation of a setter method.
     *
     * <p>
     * The formal-parameter-types of the method are inferred from those of the lambda.
     * </p>
     *
     * @param key is the name + descriptor of the method to bind a handler to.
     * @param handler is the new implementation of the method (may be null).
     * @return a copy of this object, with the new setter implementation in it.
     * @throws NoSuchMethodException if the method to define cannot be determined.
     */
    public Prototype objectSetMethod(MethodKey key,
                                     Functor handler);

    /**
     * This method retrieves the value of a property in this object.
     *
     * <p>
     * If a getter is associated with the property,
     * then the property's value will be passed into the getter.
     * Finally, the value returned by the getter will be returned by this method.
     * </p>
     *
     * @param name is the name of the property to get.
     * @return the aforedescribed value.
     */
    public Object objectGetValue(final PropertyKey name);

    /**
     * This method assigns the value of a property in this object.
     *
     * <p>
     * If a setter is associated with the property,
     * then the given value will be passed into the setter.
     * Finally, the value returned by the setter will be assigned to the property.
     * </p>
     *
     * @param name is the name of the property to set.
     * @return this, if the property is mutable;
     * otherwise, return the copy of this object that was modified.
     */
    public Prototype objectSetValue(final PropertyKey name,
                                    final Object value);
}

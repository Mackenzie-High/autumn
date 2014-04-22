package autumn.lang;

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
     * This method retrieves the object that this object is composed of, if any.
     *
     * @return the inner object, or null, if there is no inner object.
     */
    public Object objectInner();

    /**
     * This method non-destructively sets the implementation of a setter method.
     *
     * @param key is the name + descriptor of the setter to bind a handler to.
     * @param handler is the new implementation of the setter (may be null).
     * @return a copy of this object, with the new setter implementation in it.
     * @throws NoSuchMethodException if the setter to define cannot be determined.
     */
    public Prototype objectSetSetter(String key,
                                     Functor handler);

    /**
     * This method non-destructively sets the implementation of a getter method.
     *
     * @param key is the name + descriptor of the getter to bind a handler to.
     * @param handler is the new implementation of the getter (may be null).
     * @return a copy of this object, with the new getter implementation in it.
     * @throws NoSuchMethodException if the getter to define cannot be determined.
     */
    public Prototype objectSetGetter(String key,
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
    public Prototype objectSetMethod(String key,
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
    public Object objectPropertyGetValue(final String name);

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
    public Prototype objectPropertySetValue(final String name,
                                            final Object value);

    /**
     * This method determines whether a particular property has been assigned to.
     *
     * @param name is the name of the property that may be unset.
     * @return true, iff the property has been assigned a value.
     */
    public boolean objectPropertyIsSet(final String name);

    /**
     * This method resets a property to its unset state.
     *
     * @param name is the name of the property to clear.
     * @return this, if the property is mutable;
     * otherwise, return the copy of this object that was modified.
     */
    public Prototype objectPropertyClear(final String name);
}

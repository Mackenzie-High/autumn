package autumn.lang;

import java.util.Set;

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
     * This method returns the class-object that represents the design that this object implements.
     *
     * @return the aforesaid class.
     */
    public Class objectDesign();

    /**
     * This method creates a shallow copy of this object and then returns the copy.
     *
     * @return a shallow copy of this object.
     */
    public Prototype objectCopy();

    /**
     * This method returns a list containing the names of the properties in this object.
     *
     * @return the aforedescribed immutable list.
     */
    public Set<String> objectProperties();

    /**
     * This method returns a list containing the descriptions of the methods in this object.
     *
     * <p>
     * Each description consists of the method's name + descriptor.
     * </p>
     *
     * <p>
     * The returned list only incudes methods that are bind-able.
     * </p>
     *
     * @return the aforedescribed immutable list.
     */
    public Set<String> objectMethods();

    /**
     * This method non-destructively sets the implementation of a setter method.
     *
     * @param key is the name + descriptor of the setter to bind a handler to.
     * @param implementation is the new implementation of the setter (may be null).
     * @return a copy of this object, with the new setter implementation in it.
     * @throws NoSuchMethodException if the setter to define cannot be determined.
     */
    public Prototype objectSetSetter(String key,
                                     Functor implementation);

    /**
     * This method non-destructively sets the implementation of a getter method.
     *
     * @param key is the name + descriptor of the getter to bind a handler to.
     * @param implementation is the new implementation of the getter (may be null).
     * @return a copy of this object, with the new getter implementation in it.
     * @throws NoSuchMethodException if the getter to define cannot be determined.
     */
    public Prototype objectSetGetter(String key,
                                     Functor implementation);

    /**
     * This method non-destructively sets the implementation of a setter method.
     *
     * <p>
     * The formal-parameter-types of the method are inferred from those of the lambda.
     * </p>
     *
     * @param key is the name + descriptor of the method to bind a handler to.
     * @param implementation is the new implementation of the method (may be null).
     * @return a copy of this object, with the new setter implementation in it.
     * @throws NoSuchMethodException if the method to define cannot be determined.
     */
    public Prototype objectSetMethod(String key,
                                     Functor implementation);

    /**
     * This method retrieves the functor that implements a named property's setter.
     *
     * @param key is the name of the property.
     * @return the functor that is the property's setter, or null, if no such functor exists.
     */
    public Functor objectGetSetter(final String key);

    /**
     * This method retrieves the functor that implements a named property's getter.
     *
     * @param key is the name of the property.
     * @return the functor that is the property's getter, or null, if no such functor exists.
     */
    public Functor objectGetGetter(final String key);

    /**
     * This method retrieves the functor that implements a specified method.
     *
     * @param key is the name + descriptor of the method.
     * @return the functor that implements the method, or null, if no such functor exists.
     */
    public Functor objectGetMethod(final String key);

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
    public Object objectGetValue(final String name);

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
    public Prototype objectSetValue(final String name,
                                    final Object value);

    /**
     * This method retrieves the object that this object wraps.
     *
     * @return the object that this object is composed of, or null, if no such object exists.
     */
    public Object objectInner();
}

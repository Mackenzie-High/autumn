package autumn.lang;

/**
 * An instance of this interface is a property in a prototype based object.
 *
 * @author Mackenzie High
 */
public interface Property<T>
{
    /**
     * This method creates a copy of this property.
     *
     * @return a copy of this object.
     */
    public Property<T> copy();

    /**
     * This method retrieves the prototype object that this property is apart of.
     *
     * @return the enclosing object.
     */
    public Prototype object();

    /**
     * This method retrieves the name of the property.
     *
     * @return the name of the property.
     */
    public String name();

    /**
     * This method retrieves the type of the value that is stored in the property.
     *
     * @return the static-type of the property.
     */
    public Class type();

    /**
     * This method sets the implementation of a setter method.
     *
     * @param handler is the new implementation of the setter (may be null).
     */
    public void setSetter(final Functor handler);

    /**
     * This method sets the implementation of a getter method.
     *
     * @param handler is the new implementation of the getter (may be null).
     */
    public void setGetter(final Functor handler);

    /**
     * This method gets the functor that is the setter handler for this property.
     *
     * @return the setter's underlying functor; or null, if no such object exists.
     */
    public Functor getSetter();

    /**
     * This method gets the functor that is the getter handler for this property.
     *
     * @return the getter's underlying functor; or null, if no such object exists.
     */
    public Functor getGetter();

    /**
     * This method removes the value stored within this property.
     *
     * @return this.
     */
    public Property<T> clearValue();

    /**
     * This method determines whether this property has a value stored in it.
     *
     * @return true, iff this property currently stores a value.
     */
    public boolean hasValue();

    /**
     * This method gets the value stored within this object.
     *
     * <p>
     * This method provides type-independent access to the value in this property.
     * </p>
     *
     * @return the value stored herein.
     */
    public T getValue();

    /**
     * This method sets the value stored within this object.
     *
     * <p>
     * This method provides type-independent access to the value in this property.
     * </p>
     *
     * @param value is the new value stored herein.
     * @return this.
     */
    public Property<T> setValue(T value);

    /**
     * This method gets the value stored in this property without calling the getter.
     *
     * @return the value stored herein.
     */
    public T getFieldValue();

    /**
     * This method sets the value stored in this property without calling the setter.
     *
     * @param value is the new value stored herein.
     * @return this.
     */
    public Property<T> setFieldValue(T value);

    /**
     * After this method is invoked, this property's value cannot be changed.
     */
    public void makeReadonly();

    /**
     * After this method is invoked, access to this property will be synchronized.
     */
    public void makeSynchronized();

    /**
     * This method determines whether this property is flagged as readonly.
     *
     * @return true, iff this property is readonly.
     */
    public boolean isReadonly();

    /**
     * This method determines whether this property is flagged as synchronized.
     *
     * @return true, iff this property is synchronized.
     */
    public boolean isSynchronized();

    /**
     * This method sets the event handler that is fired whenever the property's value is changed.
     *
     * @param handler is the event handler (may be null).
     */
    public void setEvent(final Functor handler);

    /**
     * This method gets the event handler that is fired whenever the property's value is changed.
     *
     * @return the event handler; or null, if no such handler exists.
     */
    public Functor getEvent();

    /**
     * This method determines whether this object equals another given object.
     *
     * <p>
     * Equality Requirement: The other object is an instance of the IProperty interface. <br>
     * Equality Requirement: Both objects are enclosed by the same object. <br>
     * Equality Requirement: Both objects have the same name. <br>
     * </p>
     *
     * @param o is the other object.
     * @return true, iff this object equals the other object.
     */
    @Override
    public boolean equals(final Object o);

    /**
     * This method computes a hash-code for this object.
     *
     * <p>
     * The hash is five times the (hash of the name() plus the hash of the object()).
     * </p>
     *
     * @return the hash-code of this object.
     */
    @Override
    public int hashCode();

    /**
     * This method creates a string representation of this property.
     *
     * @return the string representation of the stored value.
     */
    @Override
    public String toString();
}

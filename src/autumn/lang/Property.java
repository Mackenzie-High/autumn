package autumn.lang;

/**
 * An instance of this interface is a property in a prototype based object.
 *
 * @author Mackenzie High
 */
public interface Property<T>
        extends Member
{
    /**
     * {@inheritDoc}
     */
    @Override
    public int index();

    /**
     * {@inheritDoc}
     */
    @Override
    public Prototype owner();

    /**
     * {@inheritDoc}
     */
    @Override
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
     * @return a modified copy of this property object.
     */
    public Property<T> setSetter(final Functor handler);

    /**
     * This method sets the implementation of a getter method.
     *
     * @param handler is the new implementation of the getter (may be null).
     * @return a modified copy of this property object.
     */
    public Property<T> setGetter(final Functor handler);

    /**
     * This method gets the functor that is the setter handler for this property.
     *
     * @return the setter's underlying functor; or null, if no such object exists.
     * @return a modified copy of this property object.
     */
    public Functor getSetter();

    /**
     * This method gets the functor that is the getter handler for this property.
     *
     * @return the getter's underlying functor; or null, if no such object exists.
     * @return a modified copy of this property object.
     */
    public Functor getGetter();

    /**
     * This method removes the value stored within this property.
     *
     * @return a modified copy of this property object.
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
     * <p>
     * Calling this method is equivalent to invoking the property's getter method.
     * </p>
     *
     * @return the value stored herein.
     * @throws IllegalStateException if the property does not have a value.
     * @throws Throwable in order to propagate exceptions thrown by the setter.
     */
    public T getValue()
            throws Throwable;

    /**
     * This method sets the value stored within this object.
     *
     * <p>
     * This method provides type-independent access to the value in this property.
     * </p>
     *
     * <p>
     * Calling this method is equivalent to invoking the property's setter method.
     * </p>
     *
     * @param value is the new value stored herein.
     * @return a modified copy of this property object.
     * @throws Throwable in order to propagate exceptions thrown by the setter.
     */
    public Property<T> setValue(T value)
            throws Throwable;

    /**
     * This method gets the value stored in this property without calling the getter.
     *
     * @return the value stored herein.
     * @throws IllegalStateException if the property does not have a value.
     */
    public T getFieldValue();

    /**
     * This method sets the value stored in this property without calling the setter.
     *
     * @param value is the new value stored herein.
     * @return a modified copy of this property object.
     */
    public Property<T> setFieldValue(T value);

    /**
     * This method creates a string representation of this property.
     *
     * @return the string representation of the stored value.
     */
    @Override
    public String toString();
}

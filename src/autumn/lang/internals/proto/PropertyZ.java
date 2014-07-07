package autumn.lang.internals.proto;

import autumn.lang.Property;
import autumn.lang.Prototype;

/**
 * An instance of this class is a boolean property.
 *
 * @author mackenzie
 */
public final class PropertyZ
        extends AbstractProperty<Boolean>
{
    private Boolean value;

    /**
     * Sole Constructor.
     *
     * @param owner is the enclosing object.
     * @param name is the name of the property.
     * @param type is the type of the property.
     */
    public PropertyZ(final Prototype owner,
                     final String name,
                     final Class type)
    {
        super(owner, name, type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Property<Boolean> copy()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Property<Boolean> clearValue()
    {
        value = null;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasValue()
    {
        return value != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean getValue()
    {
        return value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Property<Boolean> setValue(final Boolean value)
    {
        this.value = !((value == null) || (!value));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean getFieldValue()
    {
        return value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Property<Boolean> setFieldValue(final Boolean value)
    {
        this.value = !((value == null) || (!value));
        return this;
    }

    /**
     * This method sets the value stored in this property.
     *
     * @param value is the new value.
     * @return this.
     */
    public Property<Boolean> set(final boolean value)
    {
        this.value = value;
        return this;
    }

    /**
     * This method gets the value stored in this property.
     *
     * @return the value stored herein.
     */
    public boolean get()
    {
        return value;
    }
}

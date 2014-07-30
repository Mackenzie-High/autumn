package autumn.lang.internals.proto;

import autumn.lang.Functor;
import autumn.lang.Property;
import autumn.lang.Prototype;
import autumn.lang.internals.proto.AbstractPrototype.Actions;
import com.google.common.base.Preconditions;

/**
 * This class provides a concrete implementation of the Property interface.
 *
 * @author Mackenzie High
 */
final class ConcreteProperty
        implements Property
{
    private final AbstractPrototype owner;

    private final AbstractLowLevelProperty low;

    /**
     * Sole Constructor.
     *
     * @param owner is the prototype that contains the property represented by this object.
     * @param index is the index of the property within the prototype.
     * @param low is the low-level representation of the property.
     */
    public ConcreteProperty(final AbstractPrototype owner,
                            final int index,
                            final AbstractLowLevelProperty low)
    {
        Preconditions.checkNotNull(owner);
        Preconditions.checkNotNull(index);
        Preconditions.checkNotNull(low);
        Preconditions.checkArgument(index == low.meta.index); // Sanity Check

        this.owner = owner;
        this.low = low;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int index()
    {
        return low.meta.index;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Prototype owner()
    {
        return owner;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name()
    {
        return low.meta.name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class type()
    {
        return low.meta.type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Property setSetter(Functor handler)
    {
        // Create a modified version of the low-level property.
        final AbstractLowLevelProperty property = low.setSetter(handler);

        // Create a copy of the owner that contains the modified low-level property.
        final AbstractPrototype prototype = Actions.replaceSlot(owner, low.meta.index, property);

        // Create a copy of this object that reflects the changes.
        final ConcreteProperty result = new ConcreteProperty(prototype, low.meta.index, property);

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Property setGetter(Functor handler)
    {
        // Create a modified version of the low-level property.
        final AbstractLowLevelProperty property = low.setGetter(handler);

        // Create a copy of the owner that contains the modified low-level property.
        final AbstractPrototype prototype = Actions.replaceSlot(owner, low.meta.index, property);

        // Create a copy of this object that reflects the changes.
        final ConcreteProperty result = new ConcreteProperty(prototype, low.meta.index, property);

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Functor getSetter()
    {
        return low.setter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Functor getGetter()
    {
        return low.getter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Property clearValue()
    {
        // Create a modified version of the low-level property.
        final AbstractLowLevelProperty property = low.clear();

        // Create a copy of the owner that contains the modified low-level property.
        final AbstractPrototype prototype = Actions.replaceSlot(owner, low.meta.index, property);

        // Create a copy of this object that reflects the changes.
        final ConcreteProperty result = new ConcreteProperty(prototype, low.meta.index, property);

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasValue()
    {
        return low.has_value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getValue()
            throws Throwable
    {
        return AbstractPrototype.Actions.getO(owner, low.meta.index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Property setValue(Object value)
    {
//        // Create a modified version of the low-level property.
//        final AbstractLowLevelProperty property;
//
//        if(object)
//
//        // Create a copy of the owner that contains the modified low-level property.
//        final AbstractPrototype prototype = Actions.replaceSlot(owner, low.meta.index, property);
//
//        // Create a copy of this object that reflects the changes.
//        final ConcreteProperty result = new ConcreteProperty(prototype, low.meta.index, property);
//
//        return result;

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getFieldValue()
    {
        return null; // TODO
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Property setFieldValue(Object value)
    {
//        // Create a modified version of the low-level property.
//        final AbstractLowLevelProperty property = low.setFieldValue(value);
//
//        // Create a copy of the owner that contains the modified low-level property.
//        final AbstractPrototype prototype = Actions.replaceSlot(owner, low.meta.index, property);
//
//        // Create a copy of this object that reflects the changes.
//        final ConcreteProperty result = new ConcreteProperty(prototype, low.meta.index, property);
//
//        return result;

        return null; // TODO
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isProperty()
    {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMethod()
    {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        return "data " + name() + " : " + type().getSimpleName();
    }
}

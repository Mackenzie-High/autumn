package autumn.lang.internals.proto;

import autumn.lang.Property;
import autumn.lang.Functor;
import autumn.lang.Prototype;
import com.google.common.base.Preconditions;

/**
 * This class provides a partial implementation of the IProperty interface.
 *
 * @author Mackenzie High
 */
public abstract class AbstractProperty<T>
        implements Property<T>
{
    private final Prototype object;

    private final String name;

    private final Class type;

    private Functor setter = null;

    private Functor getter = null;

    private Functor event = null;

    private boolean readonly = false;

    private boolean sync = false;

    /**
     * Sole Constructor.
     *
     * @param owner is the enclosing object.
     * @param name is the name of the property.
     * @param type is the type of the property.
     */
    public AbstractProperty(final Prototype owner,
                            final String name,
                            final Class type)
    {
        Preconditions.checkNotNull(owner);
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(type);

        this.object = owner;
        this.name = name;
        this.type = type;
    }

    /**
     * This method throws an exception, if this property is readonly.
     *
     * @throws IllegalStateException if this property is readonly.
     */
    protected void enforceReadonly()
    {
        Preconditions.checkState(readonly == false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Prototype object()
    {
        return object;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String name()
    {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Class type()
    {
        return type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setSetter(final Functor handler)
    {
        this.setter = handler;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setGetter(Functor handler)
    {
        this.getter = handler;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Functor getSetter()
    {
        return this.setter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Functor getGetter()
    {
        return this.getter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void makeReadonly()
    {
        this.readonly = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void makeSynchronized()
    {
        this.sync = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean isReadonly()
    {
        return this.readonly;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean isSynchronized()
    {
        return this.sync;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setEvent(Functor handler)
    {
        this.event = handler;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Functor getEvent()
    {
        return event;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean equals(final Object o)
    {
        if (o instanceof Property)
        {
            final Property p = (Property) o;

            final boolean result = object.equals(p.object()) || name.equals(p.name());

            return result;
        }
        else
        {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int hashCode()
    {
        return 7 * (object().hashCode() + name().hashCode());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String toString()
    {
        final Object value = this.getValue();

        return value == null ? "" : value.toString();
    }
}

package autumn.lang.internals.proto;

import autumn.lang.Functor;
import autumn.lang.internals.ArgumentStack;

/**
 * An instance of this class is a short property.
 *
 * @author Mackenzie High
 */
final class LowLevelPropertyS
        extends AbstractLowLevelProperty<Short>
{
    /**
     * This is the value stored in this property.
     */
    private final short value;

    /**
     * Sole Constructor.
     *
     * @param meta describes the new property object.
     * @param setter is the setter of the new property (may be null).
     * @param getter is the getter of the new property (may be null).
     * @param has_value is true, iff the new property really will contain a value.
     * @param value is the value the property will contain (meaningless, if has_value is false).
     */
    public LowLevelPropertyS(final MetaProperty meta,
                             final Functor setter,
                             final Functor getter,
                             final boolean has_value,
                             final short value)
    {
        super(meta, setter, getter, has_value);

        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LowLevelPropertyS setSetter(final Functor setter)
    {
        return new LowLevelPropertyS(meta, setter, getter, has_value, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LowLevelPropertyS setGetter(final Functor getter)
    {
        return new LowLevelPropertyS(meta, setter, getter, has_value, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LowLevelPropertyS clear()
    {
        return new LowLevelPropertyS(meta, setter, getter, false, (short) 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LowLevelPropertyS store(final ArgumentStack stack)
    {
        final short new_value = stack.peekS();

        stack.pop();

        return new LowLevelPropertyS(meta, setter, getter, true, new_value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void load(final ArgumentStack stack)
    {
        assert has_value;

        stack.push(value);
    }
}

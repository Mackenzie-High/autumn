package autumn.lang.internals.proto;

import autumn.lang.Functor;
import autumn.lang.internals.ArgumentStack;

/**
 * An instance of this class is a long property.
 *
 * @author Mackenzie High
 */
final class LowLevelPropertyJ
        extends AbstractLowLevelProperty<Long>
{
    /**
     * This is the value stored in this property.
     */
    private final long value;

    /**
     * Sole Constructor.
     *
     * @param meta describes the new property object.
     * @param setter is the setter of the new property (may be null).
     * @param getter is the getter of the new property (may be null).
     * @param has_value is true, iff the new property really will contain a value.
     * @param value is the value the property will contain (meaningless, if has_value is false).
     */
    public LowLevelPropertyJ(final MetaProperty meta,
                             final Functor setter,
                             final Functor getter,
                             final boolean has_value,
                             final long value)
    {
        super(meta, setter, getter, has_value);

        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LowLevelPropertyJ setSetter(final Functor setter)
    {
        return new LowLevelPropertyJ(meta, setter, getter, has_value, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LowLevelPropertyJ setGetter(final Functor getter)
    {
        return new LowLevelPropertyJ(meta, setter, getter, has_value, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LowLevelPropertyJ clear()
    {
        return new LowLevelPropertyJ(meta, setter, getter, false, (long) 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LowLevelPropertyJ store(final ArgumentStack stack)
    {
        final long new_value = stack.peekJ();

        stack.pop();

        return new LowLevelPropertyJ(meta, setter, getter, true, new_value);
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

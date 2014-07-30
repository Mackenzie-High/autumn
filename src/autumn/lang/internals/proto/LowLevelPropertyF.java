package autumn.lang.internals.proto;

import autumn.lang.Functor;
import autumn.lang.internals.ArgumentStack;

/**
 * An instance of this class is a float property.
 *
 * @author Mackenzie High
 */
final class LowLevelPropertyF
        extends AbstractLowLevelProperty<Float>
{
    /**
     * This is the value stored in this property.
     */
    private final float value;

    /**
     * Sole Constructor.
     *
     * @param meta describes the new property object.
     * @param setter is the setter of the new property (may be null).
     * @param getter is the getter of the new property (may be null).
     * @param has_value is true, iff the new property really will contain a value.
     * @param value is the value the property will contain (meaningless, if has_value is false).
     */
    public LowLevelPropertyF(final MetaProperty meta,
                             final Functor setter,
                             final Functor getter,
                             final boolean has_value,
                             final float value)
    {
        super(meta, setter, getter, has_value);

        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LowLevelPropertyF setSetter(final Functor setter)
    {
        return new LowLevelPropertyF(meta, setter, getter, has_value, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LowLevelPropertyF setGetter(final Functor getter)
    {
        return new LowLevelPropertyF(meta, setter, getter, has_value, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LowLevelPropertyF clear()
    {
        return new LowLevelPropertyF(meta, setter, getter, false, (float) 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LowLevelPropertyF store(final ArgumentStack stack)
    {
        final float new_value = stack.peekF();

        stack.pop();

        return new LowLevelPropertyF(meta, setter, getter, true, new_value);
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

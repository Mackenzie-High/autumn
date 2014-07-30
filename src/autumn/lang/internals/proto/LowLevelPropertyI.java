package autumn.lang.internals.proto;

import autumn.lang.Functor;
import autumn.lang.internals.ArgumentStack;

/**
 * An instance of this class is an int property.
 *
 * @author Mackenzie High
 */
final class LowLevelPropertyI
        extends AbstractLowLevelProperty<Integer>
{
    /**
     * This is the value stored in this property.
     */
    private final int value;

    /**
     * Sole Constructor.
     *
     * @param meta describes the new property object.
     * @param setter is the setter of the new property (may be null).
     * @param getter is the getter of the new property (may be null).
     * @param has_value is true, iff the new property really will contain a value.
     * @param value is the value the property will contain (meaningless, if has_value is false).
     */
    public LowLevelPropertyI(final MetaProperty meta,
                             final Functor setter,
                             final Functor getter,
                             final boolean has_value,
                             final int value)
    {
        super(meta, setter, getter, has_value);

        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LowLevelPropertyI setSetter(final Functor setter)
    {
        return new LowLevelPropertyI(meta, setter, getter, has_value, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LowLevelPropertyI setGetter(final Functor getter)
    {
        return new LowLevelPropertyI(meta, setter, getter, has_value, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LowLevelPropertyI clear()
    {
        return new LowLevelPropertyI(meta, setter, getter, false, (int) 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LowLevelPropertyI store(final ArgumentStack stack)
    {
        final int new_value = stack.peekI();

        stack.pop();

        return new LowLevelPropertyI(meta, setter, getter, true, new_value);
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

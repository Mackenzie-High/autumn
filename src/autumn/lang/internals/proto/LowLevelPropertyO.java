package autumn.lang.internals.proto;

import autumn.lang.Functor;
import autumn.lang.internals.ArgumentStack;

/**
 * An instance of this class is an object property.
 *
 * @author Mackenzie High
 */
final class LowLevelPropertyO
        extends AbstractLowLevelProperty<Object>
{
    /**
     * This is the value stored in this property.
     */
    private final Object value;

    /**
     * Sole Constructor.
     *
     * @param meta describes the new property object.
     * @param setter is the setter of the new property (may be null).
     * @param getter is the getter of the new property (may be null).
     * @param has_value is true, iff the new property really will contain a value.
     * @param value is the value the property will contain (meaningless, if has_value is false).
     */
    public LowLevelPropertyO(final MetaProperty meta,
                             final Functor setter,
                             final Functor getter,
                             final boolean has_value,
                             final Object value)
    {
        super(meta, setter, getter, has_value);

        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LowLevelPropertyO setSetter(final Functor setter)
    {
        return new LowLevelPropertyO(meta, setter, getter, has_value, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LowLevelPropertyO setGetter(final Functor getter)
    {
        return new LowLevelPropertyO(meta, setter, getter, has_value, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LowLevelPropertyO clear()
    {
        return new LowLevelPropertyO(meta, setter, getter, false, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LowLevelPropertyO store(final ArgumentStack stack)
    {
        final Object new_value = stack.peekO();

        stack.pop();

        return new LowLevelPropertyO(meta, setter, getter, true, new_value);
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

package autumn.lang.internals.proto;

import autumn.lang.Functor;
import autumn.lang.internals.ArgumentStack;

/**
 * An instance of this class is a boolean property.
 *
 * @author Mackenzie High
 */
final class LowLevelPropertyZ
        extends AbstractLowLevelProperty<Character>
{
    /**
     * This is the value stored in this property.
     */
    private final boolean value;

    /**
     * Sole Constructor.
     *
     * @param meta describes the new property object.
     * @param setter is the setter of the new property (may be null).
     * @param getter is the getter of the new property (may be null).
     * @param has_value is true, iff the new property really will contain a value.
     * @param value is the value the property will contain (meaningless, if has_value is false).
     */
    public LowLevelPropertyZ(final MetaProperty meta,
                             final Functor setter,
                             final Functor getter,
                             final boolean has_value,
                             final boolean value)
    {
        super(meta, setter, getter, has_value);

        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LowLevelPropertyZ setSetter(final Functor setter)
    {
        return new LowLevelPropertyZ(meta, setter, getter, has_value, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LowLevelPropertyZ setGetter(final Functor getter)
    {
        return new LowLevelPropertyZ(meta, setter, getter, has_value, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LowLevelPropertyZ clear()
    {
        return new LowLevelPropertyZ(meta, setter, getter, false, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LowLevelPropertyZ store(final ArgumentStack stack)
    {
        final boolean new_value = stack.peekZ();

        stack.pop();

        return new LowLevelPropertyZ(meta, setter, getter, true, new_value);
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

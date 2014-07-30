package autumn.lang.internals.proto;

import autumn.lang.Functor;
import autumn.lang.internals.ArgumentStack;

/**
 * An instance of this class is a byte property.
 *
 * @author Mackenzie High
 */
final class LowLevelPropertyB
        extends AbstractLowLevelProperty<Byte>
{
    /**
     * This is the value stored in this property.
     */
    private final byte value;

    /**
     * Sole Constructor.
     *
     * @param meta describes the new property object.
     * @param setter is the setter of the new property (may be null).
     * @param getter is the getter of the new property (may be null).
     * @param has_value is true, iff the new property really will contain a value.
     * @param value is the value the property will contain (meaningless, if has_value is false).
     */
    public LowLevelPropertyB(final MetaProperty meta,
                             final Functor setter,
                             final Functor getter,
                             final boolean has_value,
                             final byte value)
    {
        super(meta, setter, getter, has_value);

        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LowLevelPropertyB setSetter(final Functor setter)
    {
        return new LowLevelPropertyB(meta, setter, getter, has_value, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LowLevelPropertyB setGetter(final Functor getter)
    {
        return new LowLevelPropertyB(meta, setter, getter, has_value, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LowLevelPropertyB clear()
    {
        return new LowLevelPropertyB(meta, setter, getter, false, (byte) 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LowLevelPropertyB store(final ArgumentStack stack)
    {
        final byte new_value = stack.peekB();

        stack.pop();

        return new LowLevelPropertyB(meta, setter, getter, true, new_value);
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

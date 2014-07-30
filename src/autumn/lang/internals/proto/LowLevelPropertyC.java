package autumn.lang.internals.proto;

import autumn.lang.Functor;
import autumn.lang.internals.ArgumentStack;

/**
 * An instance of this class is a char property.
 *
 * @author Mackenzie High
 */
final class LowLevelPropertyC
        extends AbstractLowLevelProperty<Character>
{
    /**
     * This is the value stored in this property.
     */
    private final char value;

    /**
     * Sole Constructor.
     *
     * @param meta describes the new property object.
     * @param setter is the setter of the new property (may be null).
     * @param getter is the getter of the new property (may be null).
     * @param has_value is true, iff the new property really will contain a value.
     * @param value is the value the property will contain (meaningless, if has_value is false).
     */
    public LowLevelPropertyC(final MetaProperty meta,
                             final Functor setter,
                             final Functor getter,
                             final boolean has_value,
                             final char value)
    {
        super(meta, setter, getter, has_value);

        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LowLevelPropertyC setSetter(final Functor setter)
    {
        return new LowLevelPropertyC(meta, setter, getter, has_value, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LowLevelPropertyC setGetter(final Functor getter)
    {
        return new LowLevelPropertyC(meta, setter, getter, has_value, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LowLevelPropertyC clear()
    {
        return new LowLevelPropertyC(meta, setter, getter, false, (char) 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LowLevelPropertyC store(final ArgumentStack stack)
    {
        final char new_value = stack.peekC();

        stack.pop();

        return new LowLevelPropertyC(meta, setter, getter, true, new_value);
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

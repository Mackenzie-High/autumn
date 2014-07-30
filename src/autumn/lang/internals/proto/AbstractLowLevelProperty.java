package autumn.lang.internals.proto;

import autumn.lang.Functor;
import autumn.lang.internals.ArgumentStack;

/**
 * This class provides a partial implementation of the IProperty interface.
 *
 * @author Mackenzie High
 */
abstract class AbstractLowLevelProperty<T>
        extends AbstractLowLevelMember
{
    public abstract AbstractLowLevelProperty<T> setSetter(final Functor setter);

    public abstract AbstractLowLevelProperty<T> setGetter(final Functor getter);

    public abstract AbstractLowLevelProperty<T> clear();

    /**
     * This method sets the value in a property, while obeying the immutability thereof.
     *
     * <p>
     * This method merely sets the field, it does not invoke the setter, if any.
     * </p>
     *
     * <p>
     * Precondition of stack:
     * <ul>
     * <li>value</li>
     * <li>.....</li>
     * </ul>
     * </p>
     *
     * <p>
     * Postcondition of stack:
     * <ul>
     * <li>.....</li>
     * </ul>
     * </p>
     *
     * @param stack contains the value to place into the field.
     * @return a modified copy of this property, since this object is immutable.
     */
    public abstract AbstractLowLevelProperty<T> store(final ArgumentStack stack);

    /**
     * This method gets the value in a property.
     *
     * <p>
     * This method merely gets the field, it does not invoke the getter, if any.
     * </p>
     *
     * <p>
     * Precondition of stack:
     * <ul>
     * <li>.....</li>
     * </ul>
     * </p>
     *
     * <p>
     * Postcondition of stack:
     * <ul>
     * <li>value</li>
     * <li>.....</li>
     * </ul>
     * </p>
     *
     * @param stack contains the value to place into the field.
     * @return a modified copy of this property, since this object is immutable.
     */
    public abstract void load(final ArgumentStack stack);

    final MetaProperty meta;

    final Functor setter;

    final Functor getter;

    final boolean has_value;

    public AbstractLowLevelProperty(final MetaProperty meta,
                                    final Functor setter,
                                    final Functor getter,
                                    final boolean has_value)
    {
        this.meta = meta;
        this.setter = setter;
        this.getter = getter;
        this.has_value = has_value;
    }

    @Override
    public final boolean isProperty()
    {
        return true;
    }

    @Override
    public final boolean isMethod()
    {
        return false;
    }

    static void throwUnsetProperty()
    {
        throw new RuntimeException(); // TODO
    }
}

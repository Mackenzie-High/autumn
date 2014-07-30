package autumn.lang.internals.proto;

import autumn.lang.Functor;

/**
 * An instance of this class is the low-level representation of a method in a prototype.
 *
 * @author Mackenzie High
 */
final class LowLevelMethod
        extends AbstractLowLevelMember
{
    final MetaMethod meta;

    final Functor handler;

    public LowLevelMethod(final MetaMethod meta,
                          final Functor handler)
    {
        this.meta = meta;
        this.handler = handler;
    }

    @Override
    public boolean isProperty()
    {
        return false;
    }

    @Override
    public boolean isMethod()
    {
        return true;
    }
}

package autumn.lang.internals.proto;

import com.google.common.collect.ImmutableList;
import java.util.List;

/**
 * An instance of this class represents a method declared in a design.
 */
final class MetaMethod
        extends AbstractMetaMember
{
    public final int index;

    public final String name;

    public final List<Class> formals;

    public final Class returns;

    public MetaMethod(final int index,
                      final String name,
                      final Iterable<Class> formals,
                      final Class returns)
    {
        this.index = index;
        this.name = name;
        this.formals = ImmutableList.copyOf(formals);
        this.returns = returns;
    }

    @Override
    public AbstractLowLevelMember instantiate()
    {
        return new LowLevelMethod(this, null);
    }
}

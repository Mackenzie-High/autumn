package autumn.lang.internals.proto;

import com.google.common.base.Preconditions;
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

    /**
     * Sole Constructor.
     *
     * @param index is the index of the method in the prototype's list of members.
     * @param name is the name of the method.
     * @param formals are the types of the method's formal-parameters.
     * @param returns is the most-specific return-type of the method.
     */
    public MetaMethod(final int index,
                      final String name,
                      final Iterable<Class> formals,
                      final Class returns)
    {
        Preconditions.checkArgument(index >= 0);
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(formals);
        Preconditions.checkNotNull(returns);

        this.index = index;
        this.name = name;
        this.formals = ImmutableList.copyOf(formals);
        this.returns = returns;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AbstractLowLevelMember instantiate()
    {
        return new LowLevelMethod(this, null);
    }
}

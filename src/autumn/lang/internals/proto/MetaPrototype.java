package autumn.lang.internals.proto;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.List;

/**
 * An instance of this class describes the internal state of a design-based object.
 *
 * @author Mackenzie High
 */
public final class MetaPrototype
{
    int index = 0;

    private final List<AbstractMetaMember> members = Lists.newLinkedList();

    /**
     * This method declares a new property.
     *
     * @param name is the name of the property.
     * @param type is the type of value stored in the property.
     */
    public void newProperty(final String name,
                            final Class type)
    {
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(type);

        members.add(new MetaProperty(index, name, type));

        index++;
    }

    /**
     * This method declares a new method.
     *
     * @param name is the name of the method.
     * @param params ares the types of the method's parameters.
     * @param returns is the return-type of the method.
     */
    public void newMethod(final String name,
                          final Iterable<Class> params,
                          final Class returns)
    {
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(params);
        Preconditions.checkNotNull(returns);

        members.add(new MetaMethod(index, name, params, returns));

        index++;
    }

    /**
     * This method is invoked in order to create the low-level representations
     * of a prototype's properties and methods.
     *
     * @return the low-level representations of a prototype's members.
     */
    public AbstractLowLevelMember[] instantiate()
    {
        final AbstractLowLevelMember[] array = new AbstractLowLevelMember[members.size()];

        int i = 0;

        for (AbstractMetaMember member : members)
        {
            array[i++] = member.instantiate();
        }

        return array;
    }
}

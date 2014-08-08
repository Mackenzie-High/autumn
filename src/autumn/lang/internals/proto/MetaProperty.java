package autumn.lang.internals.proto;

import com.google.common.base.Preconditions;

/**
 * An instance of this class represents a property declared in a design.
 */
final class MetaProperty
        extends AbstractMetaMember
{
    public final int index;

    public final String name;

    public final Class type;

    /**
     * Sole Constructor.
     *
     * @param index is the index of the property in the prototype's list of members.
     * @param name is the name of the property.
     * @param type is the most-specific static-type of the value stored in the property.
     */
    public MetaProperty(final int index,
                        final String name,
                        final Class type)
    {
        Preconditions.checkArgument(index >= 0);
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(type);

        this.index = index;
        this.name = name;
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AbstractLowLevelMember instantiate()
    {
        if (type == boolean.class)
        {
            return new LowLevelPropertyZ(this, null, null, false, false);
        }
        else if (type == char.class)
        {
            return new LowLevelPropertyC(this, null, null, false, (char) 0);
        }
        else if (type == byte.class)
        {
            return new LowLevelPropertyB(this, null, null, false, (byte) 0);
        }
        else if (type == short.class)
        {
            return new LowLevelPropertyS(this, null, null, false, (short) 0);
        }
        else if (type == int.class)
        {
            return new LowLevelPropertyI(this, null, null, false, (int) 0);
        }
        else if (type == long.class)
        {
            return new LowLevelPropertyJ(this, null, null, false, (long) 0);
        }
        else if (type == float.class)
        {
            return new LowLevelPropertyF(this, null, null, false, (float) 0);
        }
        else if (type == double.class)
        {
            return new LowLevelPropertyD(this, null, null, false, (double) 0);
        }
        else // reference types
        {
            return new LowLevelPropertyO(this, null, null, false, null);
        }
    }
}

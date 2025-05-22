package com.mackenziehigh.autumn.lang.compiler.typesystem;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IPrimitiveType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.ITypeFactory;
import com.mackenziehigh.autumn.resources.Finished;
import java.util.Map;

/**
 * An instance of this class is a primitive-type.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public final class PrimitiveType
        extends AbstractType
        implements IPrimitiveType
{
    private static final Map<Class, String> descriptors;

    static
    {
        final Map<Class, String> map = Maps.newHashMap();

        map.put(boolean.class, "Z");
        map.put(char.class, "C");
        map.put(byte.class, "B");
        map.put(short.class, "S");
        map.put(int.class, "I");
        map.put(long.class, "J");
        map.put(float.class, "F");
        map.put(double.class, "D");

        descriptors = ImmutableMap.copyOf(map);
    }

    private final Class<?> clazz;

    /**
     * Sole Constructor.
     *
     * @param factory is the type-factory that is used to access types.
     * @param clazz is the class object representation of the primitive-type.
     */
    public PrimitiveType(final ITypeFactory factory,
                         final Class<?> clazz)
    {
        super(factory, descriptors.get(clazz));

        Preconditions.checkNotNull(clazz);
        Preconditions.checkArgument(clazz.isPrimitive());

        this.clazz = clazz;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isBoolean()
    {
        return clazz.equals(boolean.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isChar()
    {
        return clazz.equals(char.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isByte()
    {
        return clazz.equals(byte.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isShort()
    {
        return clazz.equals(short.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isInt()
    {
        return clazz.equals(int.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isLong()
    {
        return clazz.equals(long.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isFloat()
    {
        return clazz.equals(float.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isDouble()
    {
        return clazz.equals(double.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?> toClass()
    {
        return clazz;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSubtypeOf(IType target)
    {
        Preconditions.checkNotNull(target);

        return this.equals(target);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPrimitiveType()
    {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isReferenceType()
    {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNullType()
    {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isVoidType()
    {
        return false;
    }
}

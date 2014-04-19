/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.typesystem;

import high.mackenzie.autumn.lang.compiler.typesystem.design.IPrimitiveType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.ITypeFactory;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.Map;

/**
 *
 * @author mackenzie
 */
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

    public PrimitiveType(final ITypeFactory factory,
                         final Class<?> clazz)
    {
        super(factory, descriptors.get(clazz));

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
        return this.equals(target);
    }

    @Override
    public boolean isPrimitiveType()
    {
        return true;
    }

    @Override
    public boolean isReferenceType()
    {
        return false;
    }

    @Override
    public boolean isNullType()
    {
        return false;
    }

    @Override
    public boolean isVoidType()
    {
        return false;
    }
}

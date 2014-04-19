/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.typesystem;

import high.mackenzie.autumn.lang.compiler.typesystem.design.IArrayType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IClassType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IElementType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IInterfaceType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.INullType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IPrimitiveType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.ITypeFactory;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVoidType;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mackenzie
 */
public final class TypeFactory
        implements ITypeFactory
{
    private final IPrimitiveType BOOLEAN = new PrimitiveType(this, boolean.class);

    private final IPrimitiveType CHAR = new PrimitiveType(this, char.class);

    private final IPrimitiveType BYTE = new PrimitiveType(this, byte.class);

    private final IPrimitiveType SHORT = new PrimitiveType(this, short.class);

    private final IPrimitiveType INT = new PrimitiveType(this, int.class);

    private final IPrimitiveType LONG = new PrimitiveType(this, long.class);

    private final IPrimitiveType FLOAT = new PrimitiveType(this, float.class);

    private final IPrimitiveType DOUBLE = new PrimitiveType(this, double.class);

    private final IVoidType VOID = new VoidType(this);

    private final INullType NULL = new NullType(this);

    private final Map<String, IType> names_to_types = Maps.newTreeMap();

    private final Map<Class, IType> classes_to_types = Maps.newHashMap();

    private final ClassLoader class_loader;

    public TypeFactory()
    {
        this(String.class.getClassLoader());
    }

    public TypeFactory(final ClassLoader class_loader)
    {
        this.class_loader = class_loader == null
                ? ClassLoader.getSystemClassLoader()
                : class_loader;

        names_to_types.put("Z", getBoolean());
        names_to_types.put("C", getChar());
        names_to_types.put("B", getByte());
        names_to_types.put("S", getShort());
        names_to_types.put("I", getInt());
        names_to_types.put("J", getLong());
        names_to_types.put("F", getFloat());
        names_to_types.put("D", getDouble());
        names_to_types.put("V", getVoid());
        names_to_types.put("Lnull;", getNull());

        classes_to_types.put(boolean.class, getBoolean());
        classes_to_types.put(char.class, getChar());
        classes_to_types.put(byte.class, getByte());
        classes_to_types.put(short.class, getShort());
        classes_to_types.put(int.class, getInt());
        classes_to_types.put(long.class, getLong());
        classes_to_types.put(float.class, getFloat());
        classes_to_types.put(double.class, getDouble());
        classes_to_types.put(void.class, getVoid());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IPrimitiveType getBoolean()
    {
        return BOOLEAN;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IPrimitiveType getChar()
    {
        return CHAR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IPrimitiveType getByte()
    {
        return BYTE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IPrimitiveType getShort()
    {
        return SHORT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IPrimitiveType getInt()
    {
        return INT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IPrimitiveType getLong()
    {
        return LONG;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IPrimitiveType getFloat()
    {
        return FLOAT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IPrimitiveType getDouble()
    {
        return DOUBLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public INullType getNull()
    {
        return NULL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IVoidType getVoid()
    {
        return VOID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IArrayType getArrayType(final IElementType element,
                                   final int dimensions)
    {
        return new ArrayType(this, element, dimensions, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IType fromClass(final Class<?> clazz)
    {
        if (classes_to_types.containsKey(clazz))
        {
            return classes_to_types.get(clazz);
        }

        final IType result;

        if (clazz.isArray())
        {
            final Class element_class = getElementType(clazz);

            final IElementType element = (IElementType) fromClass(element_class);

            final int dimensions = getDimensions(clazz);

            result = new ArrayType(this, element, dimensions, clazz);
        }
        else if (clazz.isAnnotation())
        {
            result = new ReflectiveDeclaredType(this, clazz);
        }
        else if (clazz.isInterface())
        {
            result = new ReflectiveDeclaredType(this, clazz);
        }
        else if (clazz.isEnum())
        {
            result = new ReflectiveDeclaredType(this, clazz);
        }
        else // clazz.isClass()
        {
            result = new ReflectiveDeclaredType(this, clazz);
        }

        classes_to_types.put(clazz, result);

        names_to_types.put(result.getDescriptor(), result);

        return result;
    }

    private Class getElementType(final Class array)
    {
        Class p = array;

        while (p.getComponentType() != null)
        {
            p = p.getComponentType();
        }

        return p;
    }

    private int getDimensions(final Class array)
    {
        int dimensions = 0;

        Class p = array;

        while (p.getComponentType() != null)
        {
            ++dimensions;

            p = p.getComponentType();
        }

        return dimensions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IType findType(String descriptor)
    {
        if (names_to_types.containsKey(descriptor))
        {
            return names_to_types.get(descriptor);
        }

        try
        {
            final String name = descriptor.substring(1).replace(";", "").replace("/", ".");

            final Class clazz = Class.forName(name, true, class_loader);

            return fromClass(clazz);
        }
        catch (ClassNotFoundException ex)
        {
            /* Do Nothing */
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<IType> getTypes()
    {
        return ImmutableSet.copyOf(names_to_types.values());
    }

    public CustomDeclaredType newAnnotationType(final String descriptor)
    {
        final CustomDeclaredType result = CustomDeclaredType.newAnnotationType(this,
                                                                               descriptor);

        names_to_types.put(descriptor, result);

        return result;
    }

    public CustomDeclaredType newClassType(final String descriptor)
    {
        final CustomDeclaredType result = CustomDeclaredType.newClassType(this, descriptor);

        names_to_types.put(descriptor, result);

        return result;
    }

    public CustomDeclaredType newEnumType(final String descriptor)
    {
        final CustomDeclaredType result = CustomDeclaredType.newEnumType(this, descriptor);

        names_to_types.put(descriptor, result);

        return result;
    }

    public CustomDeclaredType newInterfaceType(final String descriptor)
    {
        final CustomDeclaredType result = CustomDeclaredType.newInterfaceType(this,
                                                                              descriptor);

        names_to_types.put(descriptor, result);

        return result;
    }

    public static void main(String[] args)
    {
        final TypeFactory f = new TypeFactory(null);

        final IClassType string = (IClassType) f.fromClass(String.class);
        final IClassType object = (IClassType) f.fromClass(Object.class);
        final IInterfaceType list = (IInterfaceType) f.fromClass(List.class);
        final IInterfaceType collection = (IInterfaceType) f.fromClass(Collection.class);
        final IArrayType string_array = (IArrayType) f.fromClass(String[].class);

        System.out.println(string == f.fromClass(String.class));
        System.out.println(string.getDescriptor());
        System.out.println(string.getNamespace());
        System.out.println(string.getSuperclass().getDescriptor());
        System.out.println(string.isSubtypeOf(object));
        System.out.println(object.isSubtypeOf(string));
        System.out.println(object.isSubtypeOf(list));
        System.out.println(list.isSubtypeOf(object));
        System.out.println(collection.isSubtypeOf(list));
        System.out.println(list.isSubtypeOf(collection));
        System.out.println(collection.isSubtypeOf(collection));

        System.out.println("Types:");
        for (IType t : f.getTypes())
        {
            System.out.println("    " + t.getDescriptor());
        }
    }
}

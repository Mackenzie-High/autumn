package high.mackenzie.autumn.lang.compiler.typesystem;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IArrayType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IElementType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.INullType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IPrimitiveType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.ITypeFactory;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVoidType;
import high.mackenzie.autumn.resources.Finished;
import java.util.Collection;
import java.util.Map;

/**
 * An instance of this class is a concrete implementation of the ITypeFactory interface.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
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

    /**
     * Constructor.
     *
     * <p>
     * The new factory will use the bootstrap class-loader to resolve previously loaded classes.
     * </p>
     */
    public TypeFactory()
    {
        this(String.class.getClassLoader());
    }

    /**
     * Constructor.
     *
     * @param class_loader is the class-loader that will be used to resolve loaded classes.
     */
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
        Preconditions.checkNotNull(element);
        Preconditions.checkArgument(dimensions >= 1);

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

    /**
     * This method extracts the element-type from an array-type.
     *
     * <p>
     * Example: int[][][] returns int
     * </p>
     *
     * @param array is the array-type.
     * @return the type of the elements in an array of the array-type.
     */
    private Class getElementType(final Class array)
    {
        assert array.isArray();

        Class p = array;

        while (p.getComponentType() != null)
        {
            p = p.getComponentType();
        }

        return p;
    }

    /**
     * This method counts the number of dimensions in an array-type.
     *
     * @param array is the array-type.
     * @return the number of dimensions in the array-type.
     */
    private int getDimensions(final Class array)
    {
        assert array.isArray();

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
    public IType findType(final String descriptor)
    {
        Preconditions.checkNotNull(descriptor);

        /**
         * If object that represents the type was already created, then simply return it.
         */
        if (names_to_types.containsKey(descriptor))
        {
            return names_to_types.get(descriptor);
        }

        /**
         * Since the object that represents the type was not already created,
         * there are only two possibilities left. First, entity that the type represents
         * may already have been loaded into the class-loader, but its types has not been
         * encountered here before. Second, the the type simply does not exist.
         */
        try
        {
            // Get the name of the type as it appears in source-code.
            final String name = descriptor.substring(1).replace(";", "").replace("/", ".");

            // If there is a class object for the aforesaid name,
            // then the type exists but has not been encountered here before.
            final Class clazz = Class.forName(name, true, class_loader);

            // Create an object that represents the type that is described by the class object.
            return fromClass(clazz);
        }
        catch (ClassNotFoundException ex)
        {
            /* Do Nothing */
        }

        /**
         * This type-factory does not know of any type associated with the given descriptor.
         */
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

    /**
     * This method declares a new annotation-type.
     *
     * @param descriptor is the type-descriptor of the new type.
     * @return the object that represents the new type.
     */
    public CustomDeclaredType newAnnotationType(final String descriptor)
    {
        Preconditions.checkNotNull(descriptor);
        Preconditions.checkState(!names_to_types.containsKey(descriptor));

        final CustomDeclaredType result = CustomDeclaredType.newAnnotationType(this,
                                                                               descriptor);

        names_to_types.put(descriptor, result);

        return result;
    }

    /**
     * This method declares a new class-type.
     *
     * @param descriptor is the type-descriptor of the new type.
     * @return the object that represents the new type.
     */
    public CustomDeclaredType newClassType(final String descriptor)
    {
        Preconditions.checkNotNull(descriptor);
        Preconditions.checkState(!names_to_types.containsKey(descriptor));

        final CustomDeclaredType result = CustomDeclaredType.newClassType(this, descriptor);

        names_to_types.put(descriptor, result);

        return result;
    }

    /**
     * This method declares a new enum-type.
     *
     * @param descriptor is the type-descriptor of the new type.
     * @return the object that represents the new type.
     */
    public CustomDeclaredType newEnumType(final String descriptor)
    {
        Preconditions.checkNotNull(descriptor);
        Preconditions.checkState(!names_to_types.containsKey(descriptor));

        final CustomDeclaredType result = CustomDeclaredType.newEnumType(this, descriptor);

        names_to_types.put(descriptor, result);

        return result;
    }

    /**
     * This method declares a new interface-type.
     *
     * @param descriptor is the type-descriptor of the new type.
     * @return the object that represents the new type.
     */
    public CustomDeclaredType newInterfaceType(final String descriptor)
    {
        Preconditions.checkNotNull(descriptor);
        Preconditions.checkState(!names_to_types.containsKey(descriptor));

        final CustomDeclaredType result = CustomDeclaredType.newInterfaceType(this,
                                                                              descriptor);

        names_to_types.put(descriptor, result);

        return result;
    }
}

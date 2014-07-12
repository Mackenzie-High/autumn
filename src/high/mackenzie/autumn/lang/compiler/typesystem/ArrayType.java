package high.mackenzie.autumn.lang.compiler.typesystem;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IArrayType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IElementType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.ITypeFactory;
import high.mackenzie.autumn.resources.Finished;

/**
 * This class provides a concrete implementation of the IArrayType interface.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public final class ArrayType
        extends AbstractType
        implements IArrayType
{
    private final IElementType element;

    private final int dimensions;

    private final Class clazz;

    /**
     * Sole Constructor.
     *
     * @param factory is the type factory that is used to create types.
     * @param element are the type of the array-type's elements.
     * @param dimensions are the number of dimensions in the array-type.
     * @param clazz is the class representation of the array-type, if one exists.
     */
    public ArrayType(final ITypeFactory factory,
                     final IElementType element,
                     final int dimensions,
                     final Class clazz)
    {
        super(factory, Strings.repeat("[", dimensions) + element.getDescriptor());

        Preconditions.checkNotNull(factory);
        Preconditions.checkNotNull(element);
        Preconditions.checkArgument(dimensions >= 1);

        this.element = element;
        this.dimensions = dimensions;
        this.clazz = clazz;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSubtypeOf(final IType target)
    {
        final boolean case1 = target.equals(getTypeFactory().fromClass(Object.class));

        final boolean case2 = target instanceof IArrayType
                              && dimensions == ((IArrayType) target).getDimensions()
                              && element.isSubtypeOf(((IArrayType) target).getElement());

        return case1 || case2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IElementType getElement()
    {
        return element;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDimensions()
    {
        return dimensions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class toClass()
    {
        return clazz;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isArrayType()
    {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDeclaredType()
    {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPrimitiveType()
    {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isReferenceType()
    {
        return true;
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

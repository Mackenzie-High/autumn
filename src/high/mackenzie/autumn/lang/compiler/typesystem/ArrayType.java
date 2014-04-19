/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.typesystem;

import com.google.common.base.Strings;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IArrayType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IElementType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.ITypeFactory;

/**
 *
 * @author mackenzie
 */
public final class ArrayType
        extends AbstractType
        implements IArrayType
{
    private final IElementType element;

    private final int dimensions;

    private final Class clazz;

    public ArrayType(final ITypeFactory factory,
                     final IElementType element,
                     final int dimensions,
                     final Class clazz)
    {
        super(factory, Strings.repeat("[", dimensions) + element.getDescriptor());

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

    @Override
    public boolean isArrayType()
    {
        return true;
    }

    @Override
    public boolean isDeclaredType()
    {
        return false;
    }

    @Override
    public boolean isPrimitiveType()
    {
        return false;
    }

    @Override
    public boolean isReferenceType()
    {
        return true;
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

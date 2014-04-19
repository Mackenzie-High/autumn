/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.typesystem;

import high.mackenzie.autumn.lang.compiler.typesystem.design.IType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.ITypeFactory;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVoidType;

/**
 *
 * @author mackenzie
 */
public final class VoidType
        extends AbstractType
        implements IVoidType
{
    public VoidType(final ITypeFactory factory)
    {
        super(factory, "V");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSubtypeOf(IType target)
    {
        return this.equals(target);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class toClass()
    {
        return void.class;
    }

    @Override
    public boolean isPrimitiveType()
    {
        return false;
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
        return true;
    }
}

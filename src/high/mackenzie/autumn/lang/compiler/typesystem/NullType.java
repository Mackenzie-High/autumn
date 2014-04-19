/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.typesystem;

import high.mackenzie.autumn.lang.compiler.typesystem.design.INullType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.ITypeFactory;

/**
 *
 * @author mackenzie
 */
public final class NullType
        extends AbstractType
        implements INullType
{
    public NullType(final ITypeFactory factory)
    {
        super(factory, "Lnull;");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSubtypeOf(final IType target)
    {
        return target.getDescriptor().startsWith("L");
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
        return true;
    }

    @Override
    public boolean isVoidType()
    {
        return false;
    }
}

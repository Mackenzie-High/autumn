package high.mackenzie.autumn.lang.compiler.typesystem;

import high.mackenzie.autumn.lang.compiler.typesystem.design.INullType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.ITypeFactory;
import high.mackenzie.autumn.resources.Finished;

/**
 * An instance of this class is the null-type.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public final class NullType
        extends AbstractType
        implements INullType
{
    /**
     * Sole Constructor.
     *
     * @param factory is the type-factory that is used to access types.
     */
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
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isVoidType()
    {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isArrayType()
    {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDeclaredType()
    {
        return false;
    }
}

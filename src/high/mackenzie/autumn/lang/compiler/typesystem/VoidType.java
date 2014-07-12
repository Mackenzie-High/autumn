package high.mackenzie.autumn.lang.compiler.typesystem;

import high.mackenzie.autumn.lang.compiler.typesystem.design.IType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.ITypeFactory;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVoidType;
import high.mackenzie.autumn.resources.Finished;

/**
 * An instance of this class is the void-type.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public final class VoidType
        extends AbstractType
        implements IVoidType
{
    /**
     * Sole Constructor.
     *
     * @param factory is the type-factory that is used to access types.
     */
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
        return true;
    }
}

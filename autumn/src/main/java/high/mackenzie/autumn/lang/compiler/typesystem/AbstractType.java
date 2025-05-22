package high.mackenzie.autumn.lang.compiler.typesystem;

import com.google.common.base.Preconditions;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.ITypeFactory;
import high.mackenzie.autumn.resources.Finished;

/**
 * This class provides a partial implementation of the IType interface.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public abstract class AbstractType
        implements IType
{
    private final ITypeFactory factory;

    private final String descriptor;

    /**
     * Sole Constructor.
     *
     * @param factory type-factory that is used to access types.
     * @param descriptor is the type-descriptor of the new type.
     */
    public AbstractType(final ITypeFactory factory,
                        final String descriptor)
    {
        Preconditions.checkNotNull(factory);
        Preconditions.checkNotNull(descriptor);

        this.factory = factory;
        this.descriptor = descriptor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final ITypeFactory getTypeFactory()
    {
        return factory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String getDescriptor()
    {
        return descriptor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        return this.getDescriptor();
    }
}

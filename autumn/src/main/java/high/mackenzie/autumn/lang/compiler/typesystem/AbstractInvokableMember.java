package high.mackenzie.autumn.lang.compiler.typesystem;

import com.google.common.base.Preconditions;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IFormalParameter;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IInvokableMember;
import high.mackenzie.autumn.lang.compiler.typesystem.design.ITypeFactory;
import high.mackenzie.autumn.resources.Finished;

/**
 * This class provides a partial implementation of the IInvokableMember interface.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public abstract class AbstractInvokableMember
        extends AbstractMember
        implements IInvokableMember
{
    /**
     * Sole Constructor.
     *
     * @param factory type-factory that is used to access types. 
     */
    public AbstractInvokableMember(final ITypeFactory factory)
    {
        super(factory);

        Preconditions.checkNotNull(factory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescriptor()
    {
        return getParameterListDescriptor() + getReturnType().getDescriptor();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getParameterListDescriptor()
    {
        final StringBuilder result = new StringBuilder();

        result.append('(');

        for (IFormalParameter param : this.getParameters())
        {
            result.append(param.getType().getDescriptor());
        }

        result.append(')');

        return result.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNamePlusDescriptor()
    {
        return getName() + getDescriptor();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNamePlusParameterListDescriptor()
    {
        return getName() + getParameterListDescriptor();
    }
}

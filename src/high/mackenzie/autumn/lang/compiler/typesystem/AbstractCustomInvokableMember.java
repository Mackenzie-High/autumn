package high.mackenzie.autumn.lang.compiler.typesystem;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IClassType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IFormalParameter;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IInvokableMember;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IReturnType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.ITypeFactory;
import high.mackenzie.autumn.resources.Finished;
import java.util.Collection;
import java.util.List;

/**
 * This class provides a partial implementation of the IInvokableMember interface.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public abstract class AbstractCustomInvokableMember
        extends AbstractCustomMember
        implements IInvokableMember
{
    private ImmutableList<IFormalParameter> parameters = ImmutableList.of();

    private IReturnType return_type;

    private Collection<IClassType> throws_clause = ImmutableList.of();

    /**
     * Sole Constructor.
     *
     * @param factory type-factory that is used to access types.
     */
    public AbstractCustomInvokableMember(final ITypeFactory factory)
    {
        super(factory);

        Preconditions.checkNotNull(factory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IFormalParameter> getParameters()
    {
        return ImmutableList.copyOf(parameters);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IReturnType getReturnType()
    {
        return return_type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<IClassType> getThrowsClause()
    {
        return ImmutableList.copyOf(throws_clause);
    }

    /**
     * Setter.
     *
     * @param parameters are the formal-parameters of this invokable-member.
     */
    public void setParameters(final List<IFormalParameter> parameters)
    {
        this.parameters = ImmutableList.copyOf(parameters);
    }

    /**
     * Setter.
     *
     * @param return_type is the return-type of this invokable-member.
     */
    public void setReturnType(final IReturnType return_type)
    {
        this.return_type = return_type;
    }

    /**
     * Setter.
     *
     * @param throws_clause is the throws-clause of this invokable-member.
     */
    public void setThrowsClause(final Collection<IClassType> throws_clause)
    {
        this.throws_clause = ImmutableList.copyOf(throws_clause);
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

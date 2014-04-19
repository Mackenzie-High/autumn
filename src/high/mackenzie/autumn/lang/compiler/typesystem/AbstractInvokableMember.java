/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.typesystem;

import high.mackenzie.autumn.lang.compiler.typesystem.design.IAnnotation;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IClassType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IDeclaredType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IFormalParameter;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IInvokableMember;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IReturnType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.ITypeFactory;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author mackenzie
 */
public abstract class AbstractInvokableMember
        extends AbstractMember
        implements IInvokableMember
{
    public AbstractInvokableMember(final ITypeFactory factory)
    {
        super(factory);
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

        for (IFormalParameter param : this.getFormalParameters())
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

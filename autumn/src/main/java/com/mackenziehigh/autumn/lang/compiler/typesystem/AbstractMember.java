package com.mackenziehigh.autumn.lang.compiler.typesystem;

import com.google.common.base.Preconditions;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IMember;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.ITypeFactory;
import com.mackenziehigh.autumn.resources.Finished;

/**
 * This method provides a partial implementation of the IMember interface.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public abstract class AbstractMember
        implements IMember
{
    private final ITypeFactory factory;

    /**
     * Sole Constructor.
     *
     * @param factory type-factory that is used to access types. 
     */
    public AbstractMember(final ITypeFactory factory)
    {
        this.factory = factory;

        Preconditions.checkNotNull(factory);
    }

    /**
     * {@inheritDoc}
     */
    public ITypeFactory getTypeFactory()
    {
        return factory;
    }
}

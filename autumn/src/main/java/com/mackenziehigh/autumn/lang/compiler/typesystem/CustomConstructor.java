package com.mackenziehigh.autumn.lang.compiler.typesystem;

import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IConstructor;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.ITypeFactory;
import com.mackenziehigh.autumn.resources.Finished;
import java.lang.reflect.Constructor;

/**
 * An instance of this class represents a constructor.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public final class CustomConstructor
        extends AbstractCustomInvokableMember
        implements IConstructor
{
    /**
     * Sole Constructor.
     *
     * @param factory type-factory that is used to access types.
     */
    public CustomConstructor(final ITypeFactory factory)
    {
        super(factory);

        super.setName("<init>");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Constructor getConstructor()
    {
        return null;
    }
}

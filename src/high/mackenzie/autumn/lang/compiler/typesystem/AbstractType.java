/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.typesystem;

import high.mackenzie.autumn.lang.compiler.typesystem.design.IType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.ITypeFactory;

/**
 *
 * @author mackenzie
 */
public abstract class AbstractType
        implements IType
{
    private final ITypeFactory factory;

    private final String descriptor;

    public AbstractType(final ITypeFactory factory,
                        final String descriptor)
    {
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

    @Override
    public String toString()
    {
        return this.getDescriptor();
    }
}

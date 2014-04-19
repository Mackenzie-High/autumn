/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.typesystem;

import high.mackenzie.autumn.lang.compiler.typesystem.design.IAnnotation;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IDeclaredType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMember;
import high.mackenzie.autumn.lang.compiler.typesystem.design.ITypeFactory;
import java.util.Collection;

/**
 *
 * @author mackenzie
 */
public abstract class AbstractMember implements IMember
{

    private final ITypeFactory factory;
    
    public AbstractMember(final ITypeFactory factory)
    {
        this.factory = factory;
    }
    
    /**
     * {@inheritDoc}
     */
    public ITypeFactory getTypeFactory()
    {
        return factory;
    }
    
}

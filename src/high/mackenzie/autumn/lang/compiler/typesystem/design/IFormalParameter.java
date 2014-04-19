/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.typesystem.design;

import java.util.Collection;

/**
 * An instance of this interface is the type of a formal parameter. 
 * 
 * @author Mackenzie High
 */
public interface IFormalParameter extends IAnnotatable
{
 
    /**
     * {@inheritDoc} 
     */
    @Override
    public Collection<IAnnotation> getAnnotations();
    
    /**
     * This method returns the type of value that this formal-parameter can accept.
     * 
     * @return the formal type of this formal-parameter.
     */
    public IVariableType getType();
    
}

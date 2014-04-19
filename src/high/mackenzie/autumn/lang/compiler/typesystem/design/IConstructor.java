/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.typesystem.design;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.List;

/**
 * An instance of this interface is the type of a constructor. 
 * 
 * @author Mackenzie High
 */
public interface IConstructor extends IInvokableMember                          
{
    /**
     * {@inheritDoc}
     * 
     * @return the void-type, because the return-type of a constructor is always void. 
     */
    @Override
    public IReturnType getReturnType();
    
    /**
     * {@inheritDoc}
     */
    @Override
    public IDeclaredType getOwner();
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<IAnnotation> getAnnotations();
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int getModifiers();
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<IFormalParameter> getFormalParameters();
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<IClassType> getThrowsClause();
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescriptor();   
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getParameterListDescriptor();   
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getNamePlusDescriptor();
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getNamePlusParameterListDescriptor();
    
    /**
     * This method tries to return the <code>java.lang.reflect.Constructor</code> object 
     * that represents the same constructor that this object represents.
     * 
     * @return the constructor-object that represents the same constructor as this object, 
     *         if such a constructor-object exists; otherwise, return null. 
     */
    public Constructor getConstructor();
}

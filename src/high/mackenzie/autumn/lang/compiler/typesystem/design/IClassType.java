/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.typesystem.design;

import java.util.Collection;

/**
 * An instance of this interface is the type of a class. 
 * 
 * @author Mackenzie High
 */
public interface IClassType extends IDeclaredType
{
    /**
     * {@inheritDoc}
     */
    @Override
    public String getNamespace();
    
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
    public IClassType getSuperclass();    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<IInterfaceType> getSuperinterfaces();    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<IField> getFields();
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<IConstructor> getConstructors();
    
    /**
     * {@inheritDoc}
     */
    @Override  
    public Collection<IMethod> getMethods();
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isClassType();

    /**
     * {@inheritDoc}
     */
    @Override
    public Class toClass();    
}

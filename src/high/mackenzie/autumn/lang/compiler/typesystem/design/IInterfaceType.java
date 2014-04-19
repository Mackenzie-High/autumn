/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.typesystem.design;

import java.util.Collection;

/**
 * An instance of this interface is the type of an interface. 
 * 
 * @author Mackenzie High
 */
public interface IInterfaceType extends IDeclaredType
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
     * 
     * @return an empty immutable collection. 
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
    public boolean isInterfaceType();
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Class toClass();    
}

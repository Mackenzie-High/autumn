/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.typesystem.design;

import java.util.Collection;

/**
 * An instance of this interface is the type of an annotation-definition. 
 * 
 * @author Mackenzie High
 */
public interface IAnnotationType extends IDeclaredType
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
     * 
     * @return a single-element immutable-collection containing the type representation 
     *         of <code>java.lang.Annotation</code>.
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
     * This method returns a collection containing the methods that represent annotation elements.
     * 
     * @return <code>getMethods()</code>
     */
    public Collection<IAnnotationMethod> getAnnotationMethods();
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAnnotationType();
    
}

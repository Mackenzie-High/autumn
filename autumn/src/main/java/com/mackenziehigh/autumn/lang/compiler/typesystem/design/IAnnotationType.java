package com.mackenziehigh.autumn.lang.compiler.typesystem.design;

import com.mackenziehigh.autumn.resources.Finished;
import java.util.Collection;
import java.util.List;

/**
 * An instance of this interface is the type of an annotation-definition.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public interface IAnnotationType
        extends IDeclaredType
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
    public List<IAnnotation> getAnnotations();

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
     * of <code>java.lang.Annotation</code>.
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

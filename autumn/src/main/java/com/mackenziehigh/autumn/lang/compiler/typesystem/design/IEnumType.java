package com.mackenziehigh.autumn.lang.compiler.typesystem.design;

import com.mackenziehigh.autumn.resources.Finished;
import java.util.Collection;
import java.util.List;

/**
 * An instance of this interface is type of an enumeration.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public interface IEnumType
        extends IClassType
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
     *
     * @return the type representation of <code>java.lang.Enum</code>.
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
     * This method returns the enum-constant fields that this type directly declares.
     *
     * <p>
     * The collection returned by this method is a subset of
     * <code>this.getFields()</code>.
     * </p>
     *
     * @return a possibly empty immutable collection containing the enum-constants
     * that are declared within this type.
     */
    public Collection<IEnumConstant> getEnumConstants();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEnumType();

    /**
     * {@inheritDoc}
     */
    @Override
    public Class toClass();
}

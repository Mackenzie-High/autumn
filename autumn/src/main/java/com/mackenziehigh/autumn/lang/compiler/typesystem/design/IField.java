package com.mackenziehigh.autumn.lang.compiler.typesystem.design;

import com.mackenziehigh.autumn.resources.Finished;
import java.lang.reflect.Field;
import java.util.List;

/**
 * An instance of this interface is the type of a field.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public interface IField
        extends IMember
{
    /**
     * {@inheritDoc}
     */
    @Override
    public IDeclaredType getOwner();

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
    public String getName();

    /**
     * This method returns the formal type of the field.
     *
     * @return the type of value that the field can store at runtime.
     */
    public IVariableType getType();

    /**
     * This method determines whether this object is really the type of an enum-constant.
     *
     * @return true, if and only if, this object is the type of an enum-constant.
     */
    public boolean isEnumConstant();

    /**
     * This method tries to return the
     * <code>java.lang.reflect.Field</code> object
     * that represents the same field that this object represents.
     *
     * @return the field-object that represents the same field as this object,
     * if such a field-object exists; otherwise, return null.
     */
    public Field getField();
}

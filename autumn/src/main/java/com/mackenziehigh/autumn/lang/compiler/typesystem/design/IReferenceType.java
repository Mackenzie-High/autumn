package com.mackenziehigh.autumn.lang.compiler.typesystem.design;

import com.mackenziehigh.autumn.resources.Finished;

/**
 * An instance of this interface is a type that represents a runtime object.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public interface IReferenceType
        extends IExpressionType
{
    /**
     * This method determines whether this type is an array-type.
     *
     * @return true, iff this type is an array-type.
     */
    public boolean isArrayType();

    /**
     * This method determines whether this type is an annotation-type.
     *
     * @return true, iff this type is an annotation-type.
     */
    public boolean isAnnotationType();

    /**
     * This method determines whether this type is a class-type.
     *
     * @return true, iff this type is a class-type.
     */
    public boolean isClassType();

    /**
     * This method determines whether this type is an enum-type.
     *
     * @return true, iff this type is an enum-type.
     */
    public boolean isEnumType();

    /**
     * This method determines whether this type is an interface-type.
     *
     * @return true, iff this type is an interface-type.
     */
    public boolean isInterfaceType();

    /**
     * This method determines whether this type is a declared-type.
     *
     * @return true, iff this type is a declared-type.
     */
    public boolean isDeclaredType();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNullType();
}

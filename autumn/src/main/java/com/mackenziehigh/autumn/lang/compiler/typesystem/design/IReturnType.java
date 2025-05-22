package com.mackenziehigh.autumn.lang.compiler.typesystem.design;

import com.mackenziehigh.autumn.resources.Finished;

/**
 * An instance of this interface is a type that can be the return-type of a method.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public interface IReturnType
        extends IExpressionType
{
    /**
     * This method tries to return the
     * <code>java.lang.Class</code> related to this type.
     *
     * @return the class-object representation of this type,
     * if a class-object exists for this type; otherwise, return null.
     */
    public Class toClass();
}

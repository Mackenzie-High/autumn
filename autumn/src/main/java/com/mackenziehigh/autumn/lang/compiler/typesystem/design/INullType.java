package com.mackenziehigh.autumn.lang.compiler.typesystem.design;

import com.mackenziehigh.autumn.resources.Finished;

/**
 * An instance of this interface is the type of the "null" constant.
 *
 * <p>
 * Technically, the null-constant does not have a descriptor.
 * However, it is sometimes useful to pretend that the null-constant has a descriptor.
 * As a result, instances of this interface will use "Lnull;" as the null-constant's descriptor.
 * </p>
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public interface INullType
        extends IReferenceType
{
    /* This interface does not directly specify any methods. */
}

package com.mackenziehigh.autumn.lang.compiler.typesystem;

import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IAnnotatable;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IAnnotation;
import com.mackenziehigh.autumn.resources.Finished;
import java.util.Collection;

/**
 * An instance of this interface represents a customized annotatable entity.
 *
 * @author Mackenzie High
 */
@Finished("2015/01/17")
public interface ICustomAnnotatable
        extends IAnnotatable
{
    /**
     * Setter.
     *
     * @param annotations are the annotations applied directly to this entity.
     */
    public void setAnnotations(Collection<IAnnotation> annotations);
}

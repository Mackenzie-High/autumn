/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.typesystem.design;

import java.util.Collection;

/**
 * An instance of this interface is an entity that can have annotations applied thereto. 
 * 
 * @author Mackenzie High
 */
public interface IAnnotatable 
{
    /**
     * This method returns the annotations that are directly applied to this entity. 
     * 
     * @return an immutable collection containing the annotations that are applied to this entity,
     *         or an empty immutable collection, if no annotations are applied to this entity.
     */
    public Collection<IAnnotation> getAnnotations();
}

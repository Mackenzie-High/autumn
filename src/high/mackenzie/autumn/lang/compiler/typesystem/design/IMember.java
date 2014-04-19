/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.typesystem.design;

import java.util.Collection;

/**
 * An instance of this interface is a member (ie sub-part) of a <code>ClassLikeType</code>
 * 
 * @author Mackenzie High
 */
public interface IMember extends IAnnotatable
{
    /**
     * This method returns the type that the member is declared within. 
     * 
     * @return the owner of the member. 
     */
    public IDeclaredType getOwner();
    
    /**
     * This method returns the annotations that are directly applied to the member. 
     * 
     * @return an immutable collection containing the annotations that are applied to the member. 
     */
    @Override
    public Collection<IAnnotation> getAnnotations();
    
    /**
     * This method returns the modifiers that are applied to the member.
     * 
     * @return the modifiers that modify the member.
     * @see java.lang.reflect.Modifier
     */
    public int getModifiers();
    
    /**
     * This method returns the name of this member. 
     * 
     * @return the name of the member.  
     */
    public String getName();    
}

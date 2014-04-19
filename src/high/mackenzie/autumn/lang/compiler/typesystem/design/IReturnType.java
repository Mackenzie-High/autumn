/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.typesystem.design;

/**
 * An instance of this interface is a type that can be the return-type of a method.
 *
 * @author Mackenzie High
 */
public interface IReturnType
        extends IType,
                IExpressionType
{
    /**
     * This method tries to return the
     * <code>java.lang.Class</code> related to this type.
     *
     * @return the class-object representation of this type,
     *         if a class-object exists for this type; otherwise, return null.
     */
    public Class toClass();
}

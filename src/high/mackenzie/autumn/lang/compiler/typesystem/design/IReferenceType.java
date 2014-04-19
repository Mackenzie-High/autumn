/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.typesystem.design;

/**
 * An instance of this interface is a type that represents a runtime object.
 *
 * @author Mackenzie High
 */
public interface IReferenceType
        extends IVariableType
{
    public boolean isArrayType();

    public boolean isDeclaredType();
}

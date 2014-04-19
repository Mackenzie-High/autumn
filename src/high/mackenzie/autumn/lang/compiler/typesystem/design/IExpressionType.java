/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.typesystem.design;

/**
 * An instance of this interface can be the type of a value that an expression produces.
 *
 * TODO: Check that this interface is properly used in the design of the type-system.
 *
 * @author Nackenzie High
 */
public interface IExpressionType
        extends IType
{
    public boolean isPrimitiveType();

    public boolean isReferenceType();

    public boolean isNullType();

    public boolean isVoidType();
}

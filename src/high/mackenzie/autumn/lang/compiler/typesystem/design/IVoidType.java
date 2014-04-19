/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.typesystem.design;

/**
 * An instance of this interface is the type of the "void" return-type.
 *
 * @author Mackenzie High
 */
public interface IVoidType
        extends IReturnType,
                IExpressionType
{
    /**
     * {@inheritDoc}
     */
    @Override
    public Class toClass();
}

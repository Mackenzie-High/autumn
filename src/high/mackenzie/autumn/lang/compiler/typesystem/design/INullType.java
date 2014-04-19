/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.typesystem.design;

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
public interface INullType
        extends IType,
                IExpressionType
{
    /* This interface does not directly specify any methods. */
}

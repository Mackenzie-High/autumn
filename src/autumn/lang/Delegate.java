/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autumn.lang;

import java.lang.reflect.Method;
import java.util.List;

/**
 * An instance of this interface is a delegate.
 *
 * <p>
 * A delegate is a functor that simply invokes a static method.
 * </p>
 *
 * @author Mackenzie High
 */
public interface Delegate
        extends TypedFunctor
{
    /**
     * This method retrieves the type that owns the method that is invoked by this delegate.
     *
     * @return the owner of the underlying method.
     */
    public Class owner();

    /**
     * This method retrieves the name of the method that is invoked by this delegate.
     *
     * @return the name of the underlying method.
     */
    public String name();

    /**
     * This method retrieves the return-type of the the method that is invoked by this delegate.
     *
     * @return the return-type of the underlying method.
     */
    @Override
    public Class returnType();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Class> parameterTypes();

    /**
     * This method retrieves reflection of the method that is invoked by this delegate.
     *
     * @return the reflective view of the underlying method.
     */
    public Method method();
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.typesystem.design;

/**
 * An instance of this interface is the type of a method in an annotation-definition.
 *
 * @author Mackenzie High
 */
public interface IAnnotationMethod
        extends IMethod
{
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAnnotationMethod();
}

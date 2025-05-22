package high.mackenzie.autumn.lang.compiler.typesystem.design;

import high.mackenzie.autumn.resources.Finished;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

/**
 * An instance of this interface is the type of a method.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public interface IMethod
        extends IInvokableMember
{
    /**
     * {@inheritDoc}
     */
    @Override
    public IDeclaredType getOwner();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IAnnotation> getAnnotations();

    /**
     * {@inheritDoc}
     */
    @Override
    public int getModifiers();

    /**
     * {@inheritDoc}
     */
    @Override
    public IReturnType getReturnType();

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IFormalParameter> getParameters();

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<IClassType> getThrowsClause();

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescriptor();

    /**
     * {@inheritDoc}
     */
    @Override
    public String getParameterListDescriptor();

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNamePlusDescriptor();

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNamePlusParameterListDescriptor();

    /**
     * This method determines whether this object really represents an annotation-method.
     *
     * @return true, if and only if, this object represents an annotation-method.
     */
    public boolean isAnnotationMethod();

    /**
     * This method tries to return the
     * <code>java.lang.reflect.Method</code> object
     * that represents the same method that this object represents.
     *
     * @return the method-object that represents the same method as this object,
     * if such a method-object exists; otherwise, return null.
     */
    public Method getMethod();
}

package high.mackenzie.autumn.lang.compiler.typesystem.design;

import high.mackenzie.autumn.resources.Finished;
import java.util.Collection;
import java.util.List;

/**
 * An instance of this interface is the type of an invokable member.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public interface IInvokableMember
        extends IMember
{
    /**
     * This method returns the formal return-type of the method.
     *
     * @return the formal type of value that the method returns at runtime.
     */
    public IReturnType getReturnType();

    /**
     * This method returns the method's list of formal-parameters.
     *
     * @return an immutable list containing the method's formal-parameters.
     */
    public List<IFormalParameter> getParameters();

    /**
     * This method returns the method's throws-clause.
     *
     * @return an immutable collection containing the types in the method's throws-clause.
     */
    public Collection<IClassType> getThrowsClause();

    /**
     * This method computes and returns the method's descriptor.
     *
     * @return <code>getParameterListDescriptor() + getReturnType().getDescriptor()</code>
     */
    public String getDescriptor();

    /**
     * This method computes and returns the descriptor of the method's formal parameter-list.
     *
     * <p>
     * The returned string is simply an opening-parenthesis followed by the type-descriptor of each
     * of the method's formal-parameters followed by a closing-parenthesis. The descriptor of
     * a formal parameter is the type-descriptor of the formal-parameter's type.
     * </p>
     *
     * @return the descriptor of the method's formal parameter-list.
     */
    public String getParameterListDescriptor();

    /**
     * This method returns the method's name plus the method's descriptor.
     *
     * @return <code>getName() + getDescriptor()</code>
     */
    public String getNamePlusDescriptor();

    /**
     * This method returns the method's name plus the descriptor of the method's parameter-list.
     *
     * @return <code>getName() + getParameterListDescriptor()</code>
     */
    public String getNamePlusParameterListDescriptor();
}

package high.mackenzie.autumn.lang.compiler.typesystem.design;

import high.mackenzie.autumn.resources.Finished;

/**
 * An instance of this interface is the type of a value.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public interface IType
{
    /**
     * This method retrieves the type-factory that created this object.
     *
     * <p>
     * This method must always return normally and return the same value per instance.
     * </p>
     *
     * @return the type-factory associated with this type.
     */
    public ITypeFactory getTypeFactory();

    /**
     * This method determines whether this type is a subtype of another type.
     *
     * @param target is the other type itself.
     * @return true, if and only if, this type is a subtype of the <code>target</code> type.
     * @throws NullPointerException if <code>target</code> is null.
     */
    public boolean isSubtypeOf(IType target);

    /**
     * This method returns the descriptor of this type.
     *
     * <p>
     * This method must always return normally. <br>
     * This helps enforce the contracts of
     * <code>equals(Object)</code> and
     * <code>hashcode()</code>.
     * </p>
     *
     * @return the descriptor of this type.
     */
    public String getDescriptor();

    /**
     * This method determines whether this object equals another object.
     *
     * <p>
     * Equality Requirement:
     * <code>other instanceof ClassObject</code> <br>
     * Equality Requirement:
     * <code>getTypeLoader().equals(other.getTypeLoader())</code> <br>
     * Equality Requirement:
     * <code>getDescriptor().equals(other.getDescriptor())</code> <br>
     * </p>
     *
     * @param other is an object that is possibly equal to this object.
     * @return true, if and only if, this object equals <code>other</code>.
     */
    @Override
    public boolean equals(Object other);

    /**
     * This method computes and returns the hash-code of this object.
     *
     * @return <code>getName().hashCode() + getTypeLoader().hashCode()</code>
     */
    @Override
    public int hashCode();

    /**
     * This method returns the fully-qualified name of this type.
     *
     * @return <code>this.getDescriptor();</code>
     */
    @Override
    public String toString();
}

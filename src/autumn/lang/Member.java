package autumn.lang;

/**
 * An instance of this interface is either a property or a method in a prototype based object.
 *
 * <p>
 * Prototype objects are immutable and member objects are immutable.
 * Thus, in order to modify a member object, both the member object
 * and the underlying prototype object must be copied.
 * </p>
 *
 * @author Mackenzie High
 */
public interface Member
{
    /**
     * This method retrieves the index of this member within the enclosing prototype.
     *
     * @return the index of this member.
     */
    public int index();

    /**
     * This method retrieves the prototype object that this member is apart of.
     *
     * @return the enclosing object.
     */
    public Prototype owner();

    /**
     * This method retrieves the name of this member.
     *
     * @return the name of this member.
     */
    public String name();

    /**
     * This method determines whether this member is a property.
     *
     * @return true, iff this member is a property.
     */
    public boolean isProperty();

    /**
     * This method determines whether this member is a method.
     *
     * @return true, iff this member is a method.
     */
    public boolean isMethod();

    /**
     * This method determines whether this object equals another given object.
     *
     * <p>
     * Equality Requirement: The other object is an instance of the Member interface. <br>
     * Equality Requirement: Both objects are enclosed by the same object. <br>
     * Equality Requirement: Both objects have the same index. <br>
     * </p>
     *
     * @param o is the other object.
     * @return true, iff this object equals the other object.
     */
    @Override
    public boolean equals(final Object o);

    /**
     * This method computes a hash-code for this object.
     *
     * <p>
     * The hash is (3 * index() + 7 * object().hashCode()).
     * </p>
     *
     * @return the hash-code of this object.
     */
    @Override
    public int hashCode();
}

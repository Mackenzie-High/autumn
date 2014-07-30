package autumn.lang.internals.proto;

/**
 * An instance of this class describes a member of a prototype.
 *
 * @author Mackenzie High
 */
abstract class AbstractMetaMember
{
    /**
     * This method creates a low-level representation of the member described by this object.
     *
     * @return the low-level representation of the described member.
     */
    public abstract AbstractLowLevelMember instantiate();
}

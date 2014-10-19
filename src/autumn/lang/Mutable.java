package autumn.lang;

import high.mackenzie.autumn.resources.Finished;

/**
 * An instance of this interface is a potentially mutable object that can be made immutable.
 *
 * <p>
 * An object may implement both the Mutable interface and Immutable interface.
 * </p>
 *
 * @author Mackenzie High
 */
@Finished("2014/10/05")
public interface Mutable
{
    /**
     * This method determines whether this object is really mutable.
     *
     * @return true, iff this object is mutable.
     */
    public boolean isMutable();

    /**
     * This method creates an immutable copy of this mutable object.
     *
     * @return an immutable copy of this object.
     */
    public Immutable immutable();
}

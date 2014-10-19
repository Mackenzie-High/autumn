package autumn.lang;

import high.mackenzie.autumn.resources.Finished;

/**
 * An instance of this interface is a potentially immutable object that can be made mutable.
 *
 * <p>
 * An object may implement both the Mutable interface and Immutable interface.
 * </p>
 *
 * @author Mackenzie High
 */
@Finished("2014/10/05")
public interface Immutable
{
    /**
     * This method determines whether this object is really immutable.
     *
     * @return true, iff this object is immutable.
     */
    public boolean isImmutable();

    /**
     * This method creates a mutable copy of this immutable object.
     *
     * @return a mutable copy of this object.
     */
    public Mutable mutable();
}

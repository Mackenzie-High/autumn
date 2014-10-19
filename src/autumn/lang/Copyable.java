package autumn.lang;

import high.mackenzie.autumn.resources.Finished;

/**
 * An instance of this interface is an object that can be copied.
 *
 * @author Mackenzie High
 */
@Finished("2014/10/05")
public interface Copyable
{
    /**
     * This method creates a shallow copy of this object.
     *
     * @return a copy of this object.
     */
    public Copyable copy();
}

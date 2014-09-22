package autumn.lang;

/**
 * An instance of this interface is an object that can be copied.
 *
 * @author Mackenzie High
 */
public interface Copyable<T extends Copyable>
{
    public T copy();
}

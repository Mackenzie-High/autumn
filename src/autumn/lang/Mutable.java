package autumn.lang;

/**
 * An instance of this interface is a potentially mutable object that can be made immutable.
 *
 * @author Mackenzie High
 */
public interface Mutable<T extends Immutable>
{
    public Immutable immutable();
}

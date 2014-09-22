package autumn.lang;

/**
 * An instance of this interface is a potentially immutable object that can be made mutable.
 *
 * @author mackenzie
 */
public interface Immutable<T extends Mutable>
{
    public T mutable();
}

package autumn.lang;

/**
 * An instance of this interface is either a property or a method in a prototype based object.
 *
 * @author Mackenzie High
 */
public interface Member
{
    public int index();

    public boolean isProperty();

    public boolean isMethod();
}

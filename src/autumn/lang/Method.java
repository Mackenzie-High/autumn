package autumn.lang;

import java.util.List;

/**
 * An instance of this method is a method in a prototype based object.
 *
 * @author Mackenzie High
 */
public interface Method
        extends Member
{
    public String name();

    public List<Class> parameterTypes();

    public Class returnType();
}

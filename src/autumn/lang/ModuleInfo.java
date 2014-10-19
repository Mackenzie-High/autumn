package autumn.lang;

import java.util.List;

/**
 * An instance of this interfaces provides information regarding a Module.
 *
 * @author Mackenzie High
 */
public interface ModuleInfo
{
    public Module instance();

    public String name();

    public Class type();

    public List<Class> annotations();

    public List<Class> enums();

    public List<Class> exceptions();

    public List<Class> designs();

    public List<Class> tuples();

    public List<Class> structs();

    public List<Class> functors();

    public List<Delegate> functions();
}

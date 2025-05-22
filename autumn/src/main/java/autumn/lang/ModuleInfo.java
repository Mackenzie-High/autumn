package autumn.lang;

import java.util.List;
import java.util.Set;

/**
 * An instance of this interfaces provides information regarding a Module.
 *
 * @author Mackenzie High
 */
public interface ModuleInfo
{
    /**
     * This method retrieves the module that this object describes.
     *
     * @return the underlying module.
     */
    public Module instance();

    /**
     * This method retrieves the fully qualified name of the underlying module.
     *
     * @return the module's full-name.
     */
    public String name();

    /**
     * This method retrieves the reflective type-system representation of the underlying module.
     *
     * @return the module's reflective type.
     */
    public Class type();

    /**
     * This method creates a list containing the types of the annotations defined in the module.
     *
     * <p>
     * This is a constant-time operation.
     * </p>
     *
     * @return an immutable list of the types of the annotation-definitions in the module.
     */
    public List<Class> annotations();

    /**
     * This method creates a list containing the types of the enumerations defined in the module.
     *
     * <p>
     * This is a constant-time operation.
     * </p>
     *
     * @return an immutable list of the types of the enums-definitions in the module.
     */
    public List<Class> enums();

    /**
     * This method creates a list containing the types of the exceptions defined in the module.
     *
     * <p>
     * This is a constant-time operation.
     * </p>
     *
     * @return an immutable list of the types of the exception-definitions in the module.
     */
    public List<Class> exceptions();

    /**
     * This method creates a list containing the types of the designs defined in the module.
     *
     * <p>
     * This is a constant-time operation.
     * </p>
     *
     * @return an immutable list of the types of the design-definitions in the module.
     */
    public List<Class> designs();

    /**
     * This method creates a list containing the types of the tuples defined in the module.
     *
     * <p>
     * This is a constant-time operation.
     * </p>
     *
     * @return an immutable list of the types of the tuple-definitions in the module.
     */
    public List<Class> tuples();

    /**
     * This method creates a list containing the types of the structs defined in the module.
     *
     * <p>
     * This is a constant-time operation.
     * </p>
     *
     * @return an immutable list of the types of the struct-definitions in the module.
     */
    public List<Class> structs();

    /**
     * This method creates a list containing the types of the functors defined in the module.
     *
     * <p>
     * This is a constant-time operation.
     * </p>
     *
     * @return an immutable list of the types of the functor-definitions in the module.
     */
    public List<Class> functors();

    /**
     * This method creates a list containing references to the functions defined in the module.
     *
     * <p>
     * The delegates in the list are in the same order as the functions in the source code.
     * </p>
     *
     * <p>
     * This is a constant-time operation.
     * </p>
     *
     * @return an immutable list of delegates that refer to the functions in the module.
     */
    public List<Delegate> functions();

    /**
     * This method creates a set containing all of the types that are declared inside of the module.
     *
     * <p>
     * The new set contains all of the following:
     * <ul>
     * <li>annotations()</li>
     * <li>designs()</li>
     * <li>enums()</li>
     * <li>exceptions()</li>
     * <li>functors()</li>
     * <li>structs()</li>
     * <li>tuples()</li>
     * </ul>
     * </p>
     *
     * @return the new immutable set.
     */
    public Set<Class> types();

    /**
     * This method determines whether the module is likely the entry-point of the program.
     *
     * @return true, iff the module contains the main(String[]) function.
     */
    public boolean isStart();

    /**
     * This method creates a string representation of the module.
     *
     * @return <code>name()</code>
     */
    @Override
    public String toString();
}

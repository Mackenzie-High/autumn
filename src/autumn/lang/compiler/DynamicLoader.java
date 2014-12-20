package autumn.lang.compiler;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import high.mackenzie.autumn.resources.Finished;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * An instance of this class is a custom class-loader that can load a compiled Autumn program.
 *
 * @author Mackenzie High
 */
@Finished("2014/08/18")
public final class DynamicLoader
        extends ClassLoader
{
    /**
     * This map maps a name to its related class-object.
     */
    private final Map<String, Class> defined = Maps.newHashMap();

    /**
     * This is the program that this loader loads.
     */
    private final CompiledProgram program;

    /**
     * Sole Constructor.
     *
     * @param parent is the parent of the new class-loader.
     * @param program is the program that is being dynamically loaded.
     */
    public DynamicLoader(final ClassLoader parent,
                         final CompiledProgram program)
    {
        super(parent);

        this.program = program;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class findClass(final String name)
            throws ClassNotFoundException
    {
        Preconditions.checkNotNull(name);

        if (defined.containsKey(name))
        {
            return defined.get(name);
        }

        for (ClassFile clazz : program.classes())
        {
            if (clazz.name().equals(name))
            {
                final byte[] bytecode = clazz.bytecode();

                final Class klass = defineClass(clazz.name(), bytecode, 0, bytecode.length);

                return klass;
            }
        }

        return Class.forName(name, false, getParent());
    }

    /**
     * This method invokes the program's entry-point thereby causing the program to execute.
     *
     * <p>
     * This method does fails quietly, if the program's entry-point was not specified.
     * </p>
     *
     * @param args are the arguments to pass to the main method.
     * @throws ClassNotFoundException if the main class does not exist.
     * @throws NoSuchMethodException if the main method does not exist.
     * @throws InvocationTargetException if main method throws an exception.
     * @throws IllegalAccessException if the main method cannot be accessed reflectively.
     */
    public void invokeMain(final String[] args)
            throws ClassNotFoundException,
                   NoSuchMethodException,
                   InvocationTargetException,
                   IllegalAccessException
    {
        Preconditions.checkNotNull(args);

        /**
         * Get the name of the module that is the entry-point of the program.
         */
        final String name = program.mainClass();

        /**
         * Fail quietly, if no entry point was specified.
         */
        if (name == null)
        {
            return;
        }

        /**
         * Reflectively find the main class.
         */
        final Class main_class = Class.forName(name, false, this);

        /**
         * Reflectively find the main function.
         */
        final Method main = main_class.getMethod("main", String[].class);

        /**
         * Invoke the main function.
         */
        main.invoke(null, (Object) args);
    }
}

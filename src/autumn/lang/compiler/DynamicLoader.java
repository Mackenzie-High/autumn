package autumn.lang.compiler;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * An instance of this class is a custom class-loader that can load a compiled Autumn program.
 *
 * @author Mackenzie High
 */
public final class DynamicLoader
        extends ClassLoader
{
    private static final ClassLoader system = System.class.getClassLoader();

    private final Map<String, Class> defined = Maps.newHashMap();

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

        final String name = program.mainClass();

        Preconditions.checkNotNull(name, "The program's entry point was not specified.");

        final Class main_class = Class.forName(name, false, this);

        final Method main = main_class.getMethod("main", String[].class);

        main.invoke(null, (Object) args);
    }
}

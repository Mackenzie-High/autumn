package autumn.lang.compiler;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import high.mackenzie.autumn.resources.Finished;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

/**
 * An instance of this class is a compiled Autumn program.
 *
 * @author Mackenzie High
 */
@Finished("2014/08/19")
public final class CompiledProgram
{
    private final String main_class;

    private final List<ClassFile> classes = Lists.newLinkedList();

    private final List<URL> libraries = Lists.newLinkedList();

    /**
     * Constructor.
     *
     * <p>
     * The main-class parameter must be null, if no entry-point is specified.
     * For example, this would occur when the compiled program is a library only.
     * </p>
     *
     * <p>
     * The dependency files will be added to the generated generated Jar file's manifest.
     * </p>
     *
     * @param main_class is the name of the class that contains the program's entry-point.
     * @param classes are the classes that the compiled program is composed of.
     */
    public CompiledProgram(final String main_class,
                           final List<ClassFile> classes)
    {
        Preconditions.checkNotNull(classes);

        this.main_class = main_class;
        this.classes.addAll(classes);
    }

    /**
     * Constructor.
     *
     * @param program is the original compiled program.
     * @param libraries are the paths to libraries that the program relies upon.
     * @throws NullPointerException if program is null.
     * @throws NullPointerException if libraries is null.
     */
    public CompiledProgram(final CompiledProgram program,
                           final List<URL> libraries)
    {
        this(program.main_class, program.classes);

        Preconditions.checkNotNull(libraries);

        this.libraries.addAll(libraries);
    }

    /**
     * This method retrieves the name of the module that contains the entry-point.
     *
     * <p>
     * This is the fully-qualified name as it would appear in source code.
     * </p>
     *
     * @return the aforedescribed name (may be null).
     */
    public String mainClass()
    {
        return main_class;
    }

    /**
     * This method retrieves the class-files that are part of the compiled program.
     *
     * @return the program's class-files.
     */
    public List<ClassFile> classes()
    {
        return ImmutableList.copyOf(classes);
    }

    /**
     * This method retrieves the list of libraries that this program relies upon.
     *
     * @return an immutable list containing the URLs of the aforesaid libraries.
     */
    public List<URL> libraries()
    {
        return ImmutableList.copyOf(libraries);
    }

    /**
     * This method writes this compiled program to a specified JAR file.
     *
     * <p>
     * If the JAR already exists, then it will be overwritten.
     * If the JAR does not exist, then it will be created.
     * </p>
     *
     * @param path is the path to the new jar file.
     */
    public void jar(final File path)
            throws IOException
    {
        final Manifest manifest = createManifest();

        final FileOutputStream fos = new FileOutputStream(path);

        final JarOutputStream jos = new JarOutputStream(fos, manifest);

        for (ClassFile file : classes)
        {
            writeClassFile(jos, file);
        }

        jos.close();
    }

    /**
     * This method creates the MANIFEST.MF file to put into the JAR file.
     *
     * @return the manifest as an object.
     */
    private Manifest createManifest()
    {
        final Manifest manifest = new Manifest();

        /**
         * Set the manifest version.
         */
        manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");

        /**
         * Set the main-class attribute.
         */
        if (mainClass() != null)
        {
            manifest.getMainAttributes().put(Attributes.Name.MAIN_CLASS, mainClass());
        }

        return manifest;
    }

    /**
     * This method writes a class-file to a JAR file.
     *
     * @param jos is the the JAR file being written
     * @param file is the class-file to write to the JAR file.
     */
    private void writeClassFile(final JarOutputStream jos,
                                final ClassFile file)
            throws IOException
    {
        /**
         * Compute the name of the class-file as a regular file.
         * In other words, the package part of the name specifies a folder hierarchy.
         */
        final String name = file.name().replace('.', '/') + ".class";

        /**
         * Add the class-file to the jar-file.
         */
        final JarEntry entry = new JarEntry(name);
        entry.setTime(System.currentTimeMillis());
        jos.putNextEntry(entry);
        jos.write(file.bytecode());
        jos.closeEntry();
    }

    /**
     * This method creates a new class-loader and uses it to load this program.
     *
     * @param parent is the parent of the new class-loader.
     * @return the newly created class-loader with this program loaded into it.
     */
    public DynamicLoader load(final ClassLoader parent)
    {
        final DynamicLoader loader = new DynamicLoader(parent, this);

        return loader;
    }

    /**
     * This method creates a new class-loader and uses it to load this program.
     *
     * <p>
     * Note: The parent of the new class-loader is the system's class-loader.
     * </p>
     *
     * @return the newly created class-loader with this program loaded into it.
     */
    public DynamicLoader load()
    {
        return load(ClassLoader.getSystemClassLoader());
    }
}

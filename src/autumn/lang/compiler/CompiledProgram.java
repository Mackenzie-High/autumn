/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autumn.lang.compiler;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
public final class CompiledProgram
{
    private final String main_class;

    private final List<File> dependencies = Lists.newLinkedList();

    private final List<ClassFile> classes = Lists.newLinkedList();

    public CompiledProgram(final String main_class,
                           final List<File> dependencies,
                           final List<ClassFile> classes)
    {
        Preconditions.checkNotNull(classes);

        this.main_class = main_class;
        this.classes.addAll(classes);

        if (dependencies != null)
        {
            this.dependencies.addAll(dependencies);
        }
    }

    /**
     * This method retrieves the name of the module that contains the entry-point.
     *
     * @return the aforedescribed name.
     */
    public String mainClass()
    {
        return main_class;
    }

    /**
     * This method retrieves the paths to the dependency JAR files, if any.
     *
     * @return the paths to the program's dependencies.
     */
    public List<File> dependencies()
    {
        return ImmutableList.copyOf(dependencies);
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

        manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");

        if (mainClass() != null)
        {
            manifest.getMainAttributes().put(Attributes.Name.MAIN_CLASS, mainClass());
        }

        final StringBuilder entries = new StringBuilder();

        for (File file : dependencies)
        {
            Preconditions.checkState(file.toString().contains(" ") == false);

            entries.append(file.toString()).append(" ");
        }

        final String paths = entries.toString().trim();

        manifest.getMainAttributes().put(Attributes.Name.CLASS_PATH, paths);

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
        final String name = file.name().replace('.', '/') + ".class";

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

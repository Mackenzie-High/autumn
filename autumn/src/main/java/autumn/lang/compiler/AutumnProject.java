package autumn.lang.compiler;

import autumn.lang.compiler.errors.IErrorReporter;
import autumn.util.FileIO;
import autumn.util.test.TestResults;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.JarFile;

/**
 * <b>(Under Active Development)</b> - An instance of this class represents an Autumn project.
 *
 * @author Mackenzie High
 */
public final class AutumnProject
{
    /**
     * This is the path to the directory containing the Autumn project.
     *
     * <p>
     * The directory has several important subdirectories, including "src", "test", and "lib".
     * </p>
     */
    private final File project;

    /**
     * This is the path to the JAR-file representation of the project, if any.
     */
    private final File program;

    /**
     * This object is used to report compilation errors.
     */
    private final IErrorReporter reporter;

    /**
     * Sole Constructor.
     *
     * @param project is the directory that contains the Autumn-based project.
     * @param reporter is used to report compilation errors.
     * @throws NullPointerException if project is null.
     * @throws NullPointerException if reporter is null.
     * @throws IllegalArgumentException if project does not refer to a directory.
     */
    public AutumnProject(final File project,
                         final IErrorReporter reporter)
    {
        Preconditions.checkNotNull(project);
        Preconditions.checkNotNull(reporter);
        Preconditions.checkArgument(project.isDirectory());

        this.project = project;
        this.program = new File(project, "program.jar");
        this.reporter = reporter;
    }

    /**
     * This method enumerates all the source-code files in the project.
     *
     * @return the aforedescribed immutable list.
     */
    public List<File> srcFiles()
    {
        return filesOf(new File(project, "src/"), ".leaf");
    }

    /**
     * This method enumerates all the test-code files in the project.
     *
     * @return the aforedescribed immutable list.
     */
    public List<File> testFiles()
    {
        return filesOf(new File(project, "test/"), ".leaf");
    }

    /**
     * This method enumerates all the library jar-files in the project.
     *
     * @return the aforedescribed immutable list.
     */
    public List<File> libFiles()
    {
        return filesOf(new File(project, "lib/"), ".jar");
    }

    /**
     * This method creates a list of all the files with a certain extension in a directory.
     *
     * <p>
     * The list will include files in subdirectories.
     * </p>
     *
     * <p>
     * Hidden files are simply ignored.
     * </p>
     *
     * @param folder is the root directory.
     * @param extension is the file extension of each file.
     * @return the aforedescribed immutable list.
     */
    private List<File> filesOf(final File folder,
                               final String extension)
    {
        final List<File> files = Lists.newLinkedList();

        for (File file : FileIO.filesOf(folder, true))
        {
            if (!file.isHidden() && file.isFile() && file.getPath().endsWith(extension))
            {
                files.add(file);
            }
        }

        return Collections.unmodifiableList(files);
    }

    /**
     * This method compiles the project and outputs a jar-file.
     *
     * <p>
     * The name of the jar-file is always "program.jar".
     * The jar-file will be placed in the root directory of the project.
     * </p>
     *
     * @throws IOException if an IO error occurs.
     */
    public void compile()
            throws IOException
    {
        /**
         * Load the project.
         */
        final Autumn autumn = load();

        /**
         * Compile the project and then create the resulting JAR file.
         */
        autumn.compile(program);
    }

    /**
     * This method dynamically compiles and then runs a project.
     *
     * @param args are the command-line arguments to pass to the program.
     * @throws IOException if an IO error occurs.
     * @throws ClassNotFoundException if the main module of the project cannot be found.
     * @throws NoSuchMethodException if the project does not contain a valid entry-point.
     * @throws InvocationTargetException if the entry-point function throws an exception.
     * @throws IllegalAccessException
     */
    public void run(final String[] args)
            throws IOException,
                   ClassNotFoundException,
                   NoSuchMethodException,
                   InvocationTargetException,
                   IllegalAccessException
    {
        /**
         * Load the project.
         */
        final Autumn autumn = load();

        /**
         * Execute the program.
         */
        autumn.run(args);
    }

    /**
     * This method dynamically compiles and then runs the unit-tests of a project.
     *
     * @return the results of running the unit-tests.
     * @throws IOException if an IO error occurs.
     */
    public TestResults test()
            throws IOException
    {
        /**
         * Load the project.
         */
        final Autumn autumn = load();

        /**
         * Test the program.
         */
        final TestResults results = autumn.test();

        /**
         * Return the results of the unit-testing.
         */
        return results;
    }

    public void execute(final String[] args)
            throws MalformedURLException,
                   IOException,
                   ClassNotFoundException,
                   IllegalAccessException,
                   IllegalArgumentException,
                   InvocationTargetException,
                   NoSuchMethodException
    {
        final List<File> jars = Lists.newArrayList();

        jars.add(program);

        jars.addAll(libFiles());

        execute(jars, args);
    }

    public static void execute(final List<File> program,
                               final String[] args)
            throws MalformedURLException,
                   IOException,
                   ClassNotFoundException,
                   IllegalAccessException,
                   IllegalArgumentException,
                   InvocationTargetException,
                   NoSuchMethodException
    {
        /**
         * Step 1. Load all the jar files.
         */
        final URL[] jars = new URL[program.size()];

        int i = 0;

        for (File jar : program)
        {
            jars[i++] = jar.toURI().toURL();
        }

        final URLClassLoader loader = new URLClassLoader(jars);

        /**
         * Step 2. Find the main class.
         */
        String name = null;

        for (File jar : program)
        {
            name = new JarFile(jar).getManifest().getMainAttributes().getValue(Attributes.Name.MAIN_CLASS);
        }

        final Class klass = Class.forName(name, true, loader);


        /**
         * Reflectively find the main function.
         */
        final Method main = klass.getMethod("main", String[].class);

        /**
         * Invoke the main function.
         */
        main.invoke(null, (Object) args);
    }

    public Autumn load()
            throws IOException
    {
        /**
         * Create the object that will actually perform the execution.
         */
        final Autumn autumn = new Autumn();
        autumn.setErrorReporter(reporter);

        /**
         * Load all of the library files.
         */
        for (File library : libFiles())
        {
            autumn.loadFile(library);
        }

        /**
         * Read all of the source-code files.
         */
        for (File src : srcFiles())
        {
            autumn.srcFile(src);
        }

        /**
         * Read all of the test-code files.
         */
        for (File test : testFiles())
        {
            autumn.srcFile(test);
        }

        return autumn;
    }

    public static void create(final File project)
            throws IOException
    {
        Preconditions.checkNotNull(project);

        URL url;
        String code;

        /**
         * Create the project folder itself.
         */
        project.mkdirs();

        /**
         * Create the project/src directory.
         */
        final File src = new File(project, "src");
        src.mkdirs();

        /**
         * Create the project/test directory.
         */
        final File test = new File(project, "test");
        test.mkdirs();

        /**
         * Create the project/lib directory.
         */
        final File lib = new File(project, "lib");
        lib.mkdirs();

        /**
         * Create the project/src/Main.leaf file.
         */
        url = Resources.getResource(Autumn.class, "/com/mackenziehigh/autumn/resources/default-src-main.leaf");
        code = Resources.toString(url, Charset.defaultCharset());

        final File src_main = new File(src, "Main.leaf");
        Files.write(code, src_main, Charset.defaultCharset());

        /**
         * Create the project/test/MainTest.leaf file.
         */
        url = Resources.getResource(Autumn.class, "/com/mackenziehigh/autumn/resources/default-test-main.leaf");
        code = Resources.toString(url, Charset.defaultCharset());

        final File test_main = new File(test, "MainTest.leaf");
        Files.write(code, test_main, Charset.defaultCharset());
    }
}

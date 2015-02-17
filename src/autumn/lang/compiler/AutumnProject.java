package autumn.lang.compiler;

import autumn.lang.compiler.errors.BasicErrorReporter;
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
    private final File project;

    private final File program;

    private final IErrorReporter reporter;

    public AutumnProject(final File project,
                         final IErrorReporter reporter)
    {
        this.project = project;
        this.program = new File(project, "program.jar");
        this.reporter = reporter;
    }

    public List<File> srcFiles()
    {
        return filesOf(new File(project, "src/"), ".leaf");
    }

    public List<File> testFiles()
    {
        return filesOf(new File(project, "test/"), ".leaf");
    }

    public List<File> libFiles()
    {
        return filesOf(new File(project, "lib/"), ".jar");
    }

    private List<File> filesOf(final File folder,
                               final String extension)
    {
        final List<File> files = Lists.newLinkedList();

        for (File file : FileIO.filesOf(folder, true))
        {
            if (file.getPath().endsWith(extension))
            {
                files.add(file);
            }
        }

        return files;
    }

    public void compile()
            throws MalformedURLException,
                   IOException
    {
        /**
         * Create the object that will control the actual compilation.
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
         * Compile the project and then create the resulting JAR file.
         */
        autumn.compile(program);
    }

    public void run(final String[] args)
            throws IOException,
                   ClassNotFoundException,
                   NoSuchMethodException,
                   InvocationTargetException,
                   IllegalAccessException
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
         * Execute the program.
         */
        autumn.run(args);
    }

    public TestResults test()
            throws MalformedURLException,
                   IOException
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
        url = Resources.getResource(Autumn.class, "/high/mackenzie/autumn/resources/default-src-main.leaf");
        code = Resources.toString(url, Charset.defaultCharset());

        final File src_main = new File(src, "Main.leaf");
        Files.write(code, src_main, Charset.defaultCharset());

        /**
         * Create the project/test/MainTest.leaf file.
         */
        url = Resources.getResource(Autumn.class, "/high/mackenzie/autumn/resources/default-test-main.leaf");
        code = Resources.toString(url, Charset.defaultCharset());

        final File test_main = new File(test, "MainTest.leaf");
        Files.write(code, test_main, Charset.defaultCharset());
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

    public static void main(final String[] args)
            throws Exception
    {
        final File P = new File("/home/mackenzie/apps/project2/");

//        AutumnProject.create(P);

        final AutumnProject p = new AutumnProject(P, new BasicErrorReporter(System.out));

//        p.run(args);

        p.execute(args);

//        p.test().print(System.out);

//        p.compile();

//        AutumnProject.execute(new File(P, "program.jar"), Lists.<File>newArrayList(), args);
    }
}

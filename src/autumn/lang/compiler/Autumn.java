package autumn.lang.compiler;

import autumn.lang.compiler.ast.nodes.Module;
import autumn.lang.compiler.ast.nodes.ModuleDirective;
import autumn.lang.compiler.ast.nodes.Name;
import autumn.lang.compiler.errors.BasicErrorReporter;
import autumn.lang.compiler.errors.IErrorReporter;
import autumn.util.F;
import autumn.util.FileIO;
import autumn.util.test.TestResults;
import autumn.util.test.UnitTester;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;

/**
 * An instance of this class simplifies the use of Autumn.
 *
 * @author Mackenzie High
 */
public final class Autumn
{
    private URLClassLoader loader;

    /**
     * These are the paths that where passed to the class-loader's constructor.
     */
    private final List<URL> libraries = Lists.newLinkedList();

    /**
     * This object is used to issue error-message,
     * such as syntax errors and type-checking errors.
     */
    private IErrorReporter reporter = new BasicErrorReporter();

    /**
     * These are the modules that will be compiled.
     */
    private final List<Module> modules = Lists.newLinkedList();

    /**
     * This flag is true, if the debug-statements are turned on.
     */
    private static boolean debug = false;

    /**
     * This flag is true, if assume-statements are turned on.
     */
    private static boolean assume = true;

    /**
     * Sole Constructor.
     */
    public Autumn()
    {
        loader = new URLClassLoader(new URL[0]);
    }

    /**
     * This method turns the debug-statements on.
     */
    public static void enableDebugger()
    {
        debug = true;
    }

    /**
     * This method turns the debug-statements off.
     */
    public static void disableDebugger()
    {
        debug = false;
    }

    /**
     * This method determines whether the debugger is turned on.
     *
     * @return true, iff the debugger is turned on.
     */
    public static boolean isDebugOn()
    {
        return debug;
    }

    /**
     * This method turns the assume-statements on.
     */
    public static void enableAssume()
    {
        assume = true;
    }

    /**
     * This method turns the assume-statements off.
     */
    public static void disableAssume()
    {
        assume = false;
    }

    /**
     * This method determines whether assumptions are turned on.
     *
     * <p>
     * By default, assume-statements and assert-statements are both on.
     * Only assume-statements (aka assumptions) can be turned off.
     * </p>
     *
     * @return true, iff assumptions are turned on.
     */
    public static boolean isAssumeOn()
    {
        return assume;
    }

    /**
     * This method sets the error-reporter that is used to report compilation-errors.
     *
     * <p>
     * The error-reporter reports parsing-errors, type-checking errors, etc.
     * </p>
     *
     * @param reporter is the new error-reporter.
     * @throws NullPointerException if reporter is null.
     */
    public void setErrorReporter(final IErrorReporter reporter)
    {
        Preconditions.checkNotNull(reporter);

        this.reporter = reporter;
    }

    /**
     * This method causes source-files to be read, parsed, and added to the list of modules.
     *
     * <p>
     * This method skips hidden files and files with extensions
     * other than ".leaf" (ignoring case).
     * </p>
     *
     * @param root is the path to the directory containing the source-files.
     * @param recur is true, if this method should recurse into sub-directories.
     * @return the modules that were successfully parsed.
     * @throws IOException if a source-file cannot be read.
     */
    public List<Module> srcDir(final File root,
                               final boolean recur)
            throws IOException
    {
        Preconditions.checkNotNull(root);

        final List<Module> result = Lists.newLinkedList();

        for (Object path : F.iter(FileIO.filesOf(root, recur)))
        {
            final File file = (File) path;

            final String name = file.getName();

            final boolean is_file = file.isFile();

            final boolean is_hidden = file.isHidden();

            final boolean is_leaf = name.length() > 5
                                    && name.substring(name.length() - 5, name.length()).equalsIgnoreCase(".leaf");

            if (is_file && is_leaf && !is_hidden)
            {
                srcFile(file);
            }
        }

        return Collections.unmodifiableList(result);
    }

    /**
     * This method causes source-files to be read, parsed, and added to the list of modules.
     *
     * <p>
     * Equivalence:
     * <code> srcFile(new File(file)) </code>
     * </p>
     *
     * @param root is the path to the directory containing the source-files.
     * @param recur is true, if this method should recurse into sub-directories.
     * @return the modules that were successfully parsed.
     * @throws IOException if the source-file cannot be read.
     */
    public List<Module> srcDir(final String root,
                               final boolean recur)
            throws IOException
    {
        return srcDir(new File(root), recur);
    }

    /**
     * This method causes a source-file to be read, parsed, and added to the list of modules.
     *
     * @param file is the path to the source-file.
     * @throws IOException if the source-file cannot be read.
     */
    public Module srcFile(final File file)
            throws IOException
    {
        Preconditions.checkNotNull(file);

        final String code = Files.toString(file, Charset.defaultCharset());

        final AutumnParser parser = new AutumnParser(reporter);

        final Module module = parser.parse(code, file);

        return src(module);
    }

    /**
     * This method causes a source-file to be read, parsed, and added to the list of modules.
     *
     * <p>
     * Equivalence:
     * <code> srcFile(new File(file)) </code>
     * </p>
     *
     * @param file is the path to the source-file.
     * @throws IOException if the source-file cannot be read.
     */
    public Module srcFile(final String file)
            throws IOException
    {
        return srcFile(new File(file));
    }

    /**
     * This method causes a source-file to be read, parsed, and added to the list of modules.
     *
     * @param file is the path to the source-file.
     * @throws IOException if the source-file cannot be read.
     */
    public Module srcURL(final URL file)
            throws IOException
    {
        Preconditions.checkNotNull(file);

        final String code = Resources.toString(file, Charset.defaultCharset());

        final AutumnParser parser = new AutumnParser(reporter);

        final Module module = parser.parse(code, file);

        return src(module);
    }

    /**
     * This method causes a source-file to be read, parsed, and added to the list of modules.
     *
     * <p>
     * Equivalence:
     * <code> srcURL(new URL(file)) </code>
     * </p>
     *
     * @param file is the path to the source-file.
     * @return the Abstract-Syntax-Tree representation of the module.
     * @throws IOException if the source-file cannot be read.
     */
    public Module srcURL(final String file)
            throws MalformedURLException,
                   IOException
    {
        return srcURL(new URL(file));
    }

    /**
     * This method adds the Abstract-Syntax-Tree representation of a module to the list of modules.
     *
     * @param node is the AST representation of the module.
     * @return node.
     */
    public Module src(final Module node)
    {
        if (reporter.errorCount() > 0)
        {
            return null;
        }

        Preconditions.checkNotNull(node);

        modules.add(node);

        return node;
    }

    /**
     * This method compiles the list of modules to bytecode.
     *
     * @return the bytecode representation the program.
     */
    public CompiledProgram compile()
    {
        if (reporter.errorCount() > 0)
        {
            return null;
        }

        final AutumnCompiler cmp = new AutumnCompiler(reporter, loader);

        final CompiledProgram program = cmp.compile(modules);

        return program;
    }

    /**
     * This method compiles the list of modules to bytecode.
     *
     * @param out is the path to write the jar-file to.
     * @return the bytecode representation the program.
     */
    public CompiledProgram compile(final File out)
            throws IOException
    {
        if (reporter.errorCount() > 0)
        {
            return null;
        }

        Preconditions.checkNotNull(out);

        final CompiledProgram program = compile();

        program.jar(out);

        return program;
    }

    /**
     * This method compiles the list of modules to bytecode.
     *
     * <p>
     * Equivalence:
     * <code> compile(new File(file)) </code>
     * </p>
     *
     * @param out is the path to write the jar-file to.
     * @return the bytecode representation the program.
     */
    public CompiledProgram compile(final String out)
            throws IOException
    {
        return compile(new File(out));
    }

    /**
     * This method compiles the program, dynamically loads it, and then runs it.
     *
     * @param args are the command-line arguments to pass to the main(String[]) method.
     */
    public void run(final String[] args)
            throws ClassNotFoundException,
                   NoSuchMethodException,
                   InvocationTargetException,
                   IllegalAccessException
    {
        Preconditions.checkNotNull(args);

        final CompiledProgram program = compile();

        if (reporter.errorCount() > 0)
        {
            return;
        }

        final DynamicLoader dyn_loader = program.load();

        dyn_loader.invokeMain(args);
    }

    /**
     * This method compiles the program, dynamically loads it, and then runs it.
     *
     * @param args are the command-line arguments to pass to the main(String[]) method.
     */
    public void run(final Iterable<String> args)
            throws ClassNotFoundException,
                   NoSuchMethodException,
                   InvocationTargetException,
                   IllegalAccessException
    {
        Preconditions.checkNotNull(args);

        run(Lists.newArrayList(args).toArray(new String[0]));
    }

    /**
     * This method compiles the program, dynamically loads it,
     * and then executes the unit-tests contained therein.
     *
     * <p>
     * For more information on Autumn unit-tests, see the (autumn.util.test) package.
     * </p>
     *
     * @return the results of running the unit-tests.
     */
    public TestResults test()
    {
        if (reporter.errorCount() > 0)
        {
            return null;
        }

        final UnitTester tester = new UnitTester();

        final CompiledProgram program = compile();

        final DynamicLoader loader = program.load();

        for (Module module : modules)
        {
            final String name = nameOf(module);

            if (name == null)
            {
                continue;
            }

            try
            {
                final Class clazz = Class.forName(name, false, loader);

                tester.add(clazz);
            }
            catch (ClassNotFoundException ex)
            {
                /**
                 * Technically, this should never happen.
                 * Compilation was successfully and the modules were successfully loaded.
                 * As a result, the module's class must exist in the loader.
                 */
                throw new RuntimeException(ex);
            }
        }

        final TestResults results = tester.run();

        return results;
    }

    /**
     * This method computes the fully-qualified name of a module.
     *
     * @param module is the Abstract-Syntax-Tree representation of the module.
     * @return the name of the module; or null, if the module is anonymous.
     */
    private String nameOf(final Module module)
    {
        // The name of the module is specified by its only module-directive.
        final ModuleDirective directive = module.getModuleDirectives().asMutableList().get(0);

        // The name will consist of a package-part and s simple-name.
        final StringBuilder name = new StringBuilder();

        // For each part of the package's name:
        for (Name part : directive.getNamespace().getNames())
        {
            name.append(part.getName());
            name.append('.');
        }

        // Append the simple-name of the module.
        // This will be a '*' character, if the module is anonymous.
        name.append(directive.getName().getName());

        // Return null, if the module is anonymous.
        return name.toString().contains("*") ? null : name.toString();
    }

    /**
     * This method loads a library jar-file or class-file.
     *
     * @param path is the path to where the jar-file is located.
     */
    public void loadURL(final URL path)
    {
        Preconditions.checkNotNull(path);

        libraries.add(path);

        final URL[] array = libraries.toArray(new URL[0]);

        loader = new URLClassLoader(array);
    }

    /**
     * This method loads a library jar-file or class-file.
     *
     * @param path is the path to where the jar-file is located.
     */
    public void loadURL(final String path)
            throws MalformedURLException
    {
        Preconditions.checkNotNull(path);

        loadURL(new URL(path));
    }

    /**
     * This method loads a library jar-file or class-file.
     *
     * @param path is the path to where the jar-file is located.
     */
    public void loadFile(final File path)
            throws MalformedURLException
    {
        Preconditions.checkNotNull(path);

        loadURL(path.toURI().toURL());
    }

    /**
     * This method loads a library jar-file or class-file.
     *
     * @param path is the path to where the jar-file is located.
     */
    public void loadFile(final String path)
            throws MalformedURLException
    {
        Preconditions.checkNotNull(path);

        loadFile(path);
    }

    /**
     * This method creates a new project folder in a specified directory.
     *
     * @param folder is the directory where the new project folder will be created.
     * @param name is the name of the new project.
     * @throws IllegalArgumentException if the name is not a valid name in Autumn.
     * @throws IllegalArgumentException if a folder with the given name already exists.
     * @throws IOException if name cannot be the name of a folder.
     * @throws IOException if the project folder cannot be created.
     */
    public static void createProject(final File folder,
                                     final String name)
            throws IOException
    {
        Preconditions.checkNotNull(folder);
        Preconditions.checkNotNull(name);
        Preconditions.checkArgument(name.matches("[A-Za-z_$][A-Za-z_$0-9]*"),
                                    "The name of a project folder must be a valid Autumn name: " + name);

        URL url;
        String code;

        /**
         * Create the project folder itself.
         */
        final File project = new File(folder, name);
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
         * Create the project/data directory.
         */
        final File data = new File(project, "data");
        data.mkdirs();

        /**
         * Create the project/src/Main.leaf file.
         */
        url = Resources.getResource(Autumn.class, "/high/mackenzie/autumn/resources/default-src-main.leaf");
        code = Resources.toString(url, Charset.defaultCharset());

        final File src_main = new File(src, "Main.leaf");
        Files.write(code, src_main, Charset.defaultCharset());

        /**
         * Create the project/test/Main.leaf file.
         */
        url = Resources.getResource(Autumn.class, "/high/mackenzie/autumn/resources/default-test-main.leaf");
        code = Resources.toString(url, Charset.defaultCharset());

        final File test_main = new File(test, "MainTest.leaf");
        Files.write(code, test_main, Charset.defaultCharset());

    }

    /**
     * This method loads all the source-files and library-files associated with a project.
     *
     * @param folder is the path to the project-directory.
     * @throws IOException if the folder does not exist.
     * @throws IOException if the files cannot be read.
     */
    public void loadProject(final File folder)
            throws IOException
    {
        /**
         * Load all the source code files.
         */
        final File src = new File(folder, "src");

        for (Object path : F.iter(FileIO.filesOf(src, true)))
        {
            final File file = (File) path;

            if (file.getPath().endsWith(".leaf") && !file.isHidden())
            {
                srcFile(file);
            }
        }

        /**
         * Load all the test files.
         */
        final File test = new File(folder, "test");

        for (Object path : F.iter(FileIO.filesOf(test, true)))
        {
            final File file = (File) path;

            if (file.getPath().endsWith(".leaf") && !file.isHidden())
            {
                srcFile(file);
            }
        }

        // TODO: load resources, libs, etc
    }
}

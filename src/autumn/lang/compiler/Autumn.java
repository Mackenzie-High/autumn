package autumn.lang.compiler;

import autumn.lang.compiler.ast.nodes.Module;
import autumn.lang.compiler.errors.BasicErrorReporter;
import autumn.lang.compiler.errors.IErrorReporter;
import autumn.util.test.TestResults;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

/**
 * An instance of this class simplifies the use of Autumn.
 *
 * @author Mackenzie High
 */
public final class Autumn
{
    private IErrorReporter reporter = new BasicErrorReporter();

    private final List<Module> modules = Lists.newLinkedList();

    /**
     * This method turns the debug-statements on.
     */
    public static void enableDebugger()
    {
    }

    /**
     * This method turns the debug-statements off.
     */
    public static void disableDebugger()
    {
    }

    /**
     * This method turns the assume-statements on.
     */
    public static void enableAssume()
    {
    }

    /**
     * This method turns the assume-statements off.
     */
    public static void disableAssume()
    {
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

    public void help()
    {
    }

    /**
     * This method loads a Jar library.
     *
     * @param jar is the path to the jar-file.
     */
    public void load(final File jar)
    {
    }

    /**
     * This method causes a source-file to be read, parsed, and added to the list of modules.
     *
     * @param file is the path to the source-file.
     * @throws IOException if the source-file cannot be read.
     */
    public void srcFile(final File file)
            throws IOException
    {
        Preconditions.checkNotNull(file);

        final String code = Files.toString(file, Charset.defaultCharset());

        final AutumnParser parser = new AutumnParser(reporter);

        final Module module = parser.parse(code, file);

        src(module);
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
    public void srcFile(final String file)
            throws IOException
    {
        srcFile(new File(file));
    }

    /**
     * This method causes a source-file to be read, parsed, and added to the list of modules.
     *
     * @param file is the path to the source-file.
     * @throws IOException if the source-file cannot be read.
     */
    public void srcURL(final URL file)
            throws IOException
    {
        Preconditions.checkNotNull(file);

        final String code = Resources.toString(file, Charset.defaultCharset());

        final AutumnParser parser = new AutumnParser(reporter);

        final Module module = parser.parse(code, file);

        src(module);
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
     * @throws IOException if the source-file cannot be read.
     */
    public void srcURL(final String file)
            throws MalformedURLException,
                   IOException
    {
        srcURL(new URL(file));
    }

    /**
     * This method adds the Abstract-Syntax-Tree representation of a module to the list of modules.
     *
     * @param node is the AST representation of the module.
     */
    public void src(final Module node)
    {
        Preconditions.checkNotNull(node);

        modules.add(node);
    }

    /**
     * This method compiles the list of modules to bytecode.
     *
     * @return the bytecode representation the program.
     */
    public CompiledProgram compile()
    {
        final AutumnCompiler cmp = new AutumnCompiler(reporter);

        final CompiledProgram program = cmp.compile(modules);

        return program;
    }

    /**
     * This method compiles the list of modules to bytecode.
     *
     * @param out is the to write the jar-file to.
     * @return the bytecode representation the program.
     */
    public CompiledProgram compile(final File out)
            throws IOException
    {
        Preconditions.checkNotNull(out);

        final CompiledProgram program = compile();

        program.jar(out);

        return program;
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

        final DynamicLoader loader = program.load();

        loader.invokeMain(args);
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
        return null;
    }
}

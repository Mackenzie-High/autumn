package high.mackenzie.autumn.lang.compiler.args;

import autumn.lang.compiler.AutumnCompiler;
import autumn.lang.compiler.AutumnParser;
import autumn.lang.compiler.AutumnPrettyPrinter;
import autumn.lang.compiler.CompiledProgram;
import autumn.lang.compiler.DynamicLoader;
import autumn.lang.compiler.ast.nodes.Module;
import autumn.lang.compiler.errors.BasicErrorReporter;
import autumn.lang.compiler.errors.IErrorReporter;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import high.mackenzie.autumn.Main;
import high.mackenzie.snowflake.ITreeNode;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

/**
 * An instance of this class is used to process the command-line arguments given to the compiler.
 *
 * @author Mackenzie High
 */
public final class Visitor
        extends AbstractVisitor
{
    private static final String HELP = "/high/mackenzie/autumn/resources/help.txt";

    /**
     * This flag is true, if the pretty option was given.
     */
    private boolean pretty = false;

    /**
     * This flag is true, if the compile option was given.
     */
    private boolean compile = false;

    /**
     * This flag is true, if the interpret option was given.
     */
    private boolean interpret = false;

    /**
     * This is the path to write the output file to.
     */
    private File output = new File("a.jar");

    /**
     * These are the paths to the source-code files.
     */
    private final List<File> sources = Lists.newLinkedList();

    /**
     * These are the paths to the JAR file libraries the program being compiled references.
     */
    private final List<File> libraries = Lists.newLinkedList();

    /**
     * These are the command-line arguments to pass to the interpreted program.
     */
    private final List<String> args = Lists.newLinkedList();

    /**
     * This flag is true, if the help message was already printed.
     */
    private boolean help_printed = false;

    /**
     * This list aids in the construction of the more specific lists.
     */
    private final List<String> arguments = Lists.newLinkedList();

    /**
     * This field is used to temporarily store a single argument.
     */
    private String argument;

    private void visitChildren(final ITreeNode node)
    {
        for (ITreeNode kid : node.children())
        {
            visit(kid);
        }
    }

    public void printHelp()
    {
        if (help_printed)
        {
            return;
        }

        final URL url = Resources.getResource(Main.class, HELP);

        String help;

        try
        {
            help = Resources.toString(url, Charset.defaultCharset());
        }
        catch (Exception ex)
        {
            ex.printStackTrace(System.out);
            return;
        }

        System.out.println(help);

        help_printed = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visitUnknown(final ITreeNode node)
    {
        visitChildren(node);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_accept(final ITreeNode node)
    {
        visitChildren(node);

        // TODO: Finish

        if (interpret && compile)
        {
            System.out.println("Error: Options -compile and -interpret were used together.");
            return;
        }

        if (compile && !args.isEmpty())
        {
            System.out.println("Error: Options -args and -compile were used together.");
            return;
        }

        if (pretty && !args.isEmpty())
        {
            System.out.println("Error: Options -args and -pretty were used together.");
            return;
        }

        if (pretty && compile)
        {
            System.out.println("Error: Options -pretty and -compile were used together.");
            return;
        }

        if (pretty && interpret)
        {
            System.out.println("Error: Options -pretty and -interpret were used together.");
            return;
        }

        if (pretty && (output != null))
        {
            System.out.println("Error: Options -pretty and -out were used together.");
            return;
        }

        if (pretty && args.size() != 1)
        {
            System.out.println("Error: Option -pretty requires exactly one argument.");
            return;
        }

        final IErrorReporter reporter = new BasicErrorReporter(System.out);

        for (File lib : libraries)
        {
            System.load(lib.toString());
        }

        final AutumnCompiler compiler = new AutumnCompiler(reporter);

        final List<Module> modules = Lists.newLinkedList();

        for (File file : sources)
        {
            final AutumnParser parser = new AutumnParser(reporter);

            final String source;

            try
            {
                source = Files.toString(file, Charset.defaultCharset());
            }
            catch (IOException ex)
            {
                System.out.println("Error - No Such File: " + file);
                return;
            }

            final Module module = parser.parse(source, file);

            if (module == null)
            {
                return;
            }

            modules.add(module);
        }

        if (pretty)
        {
            final Module module = modules.get(0);

            final AutumnPrettyPrinter printer = new AutumnPrettyPrinter(System.out);

            printer.print(module);

            return;
        }

        final CompiledProgram program = compiler.compile(modules);

        if (program == null)
        {
            return;
        }

        if (interpret)
        {
            final DynamicLoader loader = new DynamicLoader(ClassLoader.getSystemClassLoader(),
                                                           program);

            try
            {
                loader.invokeMain(args.toArray(new String[0]));
            }
            catch (Throwable ex)
            {
                ex.printStackTrace(System.out);
            }
        }
        else if (compile)
        {
            try
            {
                program.jar(output);
            }
            catch (Throwable ex)
            {
                ex.printStackTrace(System.out);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_option_help(final ITreeNode node)
    {
        printHelp();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_option_compile(final ITreeNode node)
    {
        compile = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_option_interpret(final ITreeNode node)
    {
        interpret = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_option_output(final ITreeNode node)
    {
        visitChildren(node);

        output = new File(arguments.get(0));

        arguments.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_option_source(final ITreeNode node)
    {
        visitChildren(node);

        for (String file : arguments)
        {
            sources.add(new File(file));
        }

        arguments.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_option_library(final ITreeNode node)
    {
        visitChildren(node);

        for (String file : arguments)
        {
            libraries.add(new File(file));
        }

        arguments.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_arg(final ITreeNode node)
    {
        visitChildren(node);

        args.add(argument);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_argument(final ITreeNode node)
    {
        visitChildren(node);

        arguments.add(argument);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_qstring(final ITreeNode node)
    {
        final String text = node.text().substring(0, node.length() - 2);

        argument = text;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_pstring1(final ITreeNode node)
    {
        final String text = node.text();

        argument = text;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_pstring2(final ITreeNode node)
    {
        final String text = node.text();

        argument = text;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_error(final ITreeNode node)
    {
        printHelp();
    }
}

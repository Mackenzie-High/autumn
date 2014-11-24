package high.mackenzie.autumn.lang.compiler.args;

import autumn.lang.compiler.Autumn;
import autumn.lang.exceptions.AssertionFailedException;
import autumn.lang.exceptions.AssumptionFailedException;
import autumn.util.test.TestResults;
import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import high.mackenzie.autumn.Main;
import high.mackenzie.autumn.resources.Finished;
import high.mackenzie.snowflake.ITreeNode;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.LinkedList;

/**
 * An instance of this class is used to process the command-line arguments given to the compiler.
 *
 * @author Mackenzie High
 */
@Finished("2014/08/22")
public final class Visitor
        extends AbstractVisitor
{
    private static interface Invokable
    {
        public void invoke()
                throws ClassNotFoundException,
                       NoSuchMethodException,
                       InvocationTargetException,
                       IllegalAccessException;
    }

    private static final String HELP = "/high/mackenzie/autumn/resources/help.txt";

    private static final String LICENSE = "/high/mackenzie/autumn/resources/license.txt";

    private static final String VERSION = "/high/mackenzie/autumn/resources/version.txt";

    private String name;

    private static final Autumn cmp = new Autumn();

    /**
     * These are the command-line arguments to pass to the interpreted program.
     */
    private static final LinkedList<String> args = Lists.newLinkedList();

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

    /**
     * This method resolves the project folder.
     *
     * <p>
     * Imagine that you try to run an Autumn project from somewhere deep within the project.
     * Autumn must resolve the outer most folder in the project folder.
     * That is precisely what this method does.
     * </p>
     *
     * @return the resolved project folder.
     */
    private static File resolveProject()
    {
        File p = new File(System.getProperty("user.dir"));

        while (isProject(p) == false && p.getParent() != null)
        {
            p = p.getParentFile();
        }

        return p;
    }

    /**
     * This method determines whether a folder is a project folder.
     *
     * @param folder is a possible project folder.
     * @return true, iff the file represents a project folder.
     */
    private static boolean isProject(final File folder)
    {
        return folder.isDirectory()
               && !folder.isHidden()
               && new File(folder, "src").exists()
               && new File(folder, "lib").exists()
               && new File(folder, "test").exists();
    }

    /**
     * This method generalizes the running of a project.
     *
     * <p>
     * This method alleviates the need for separate complex methods
     * for running, testing, and debugging Autumn programs.
     * </p>
     *
     * @param function is used to invoke the appropriate method in the Autumn object.
     */
    private static void run(final Invokable function)
    {
        /**
         * Load the project.
         */
        try
        {
            final File project = resolveProject();

            cmp.loadProject(project);
        }
        catch (IOException ex)
        {
            System.out.println("The project coult not be loaded.");
            ex.printStackTrace(System.out);
        }

        /**
         * Execute the script.
         */
        try
        {
            function.invoke();
        }
        catch (ClassNotFoundException ex)
        {
            System.out.println("The main-class could not be found.");
            ex.printStackTrace(System.out);
        }
        catch (NoSuchMethodException ex)
        {
            System.out.println("The main(String[]) function could not be found.");
            ex.printStackTrace(System.out);
        }
        catch (InvocationTargetException ex)
        {
            if (ex.getCause() instanceof AssertionFailedException)
            {
                System.out.println("Assertion Failed:");
                System.out.println("  File: " + ((AssertionFailedException) ex.getCause()).file());
                System.out.println("  Line: " + ((AssertionFailedException) ex.getCause()).line());
            }

            if (ex.getCause() instanceof AssumptionFailedException)
            {
                System.out.println("Assumption Failed:");
                System.out.println("  File: " + ((AssumptionFailedException) ex.getCause()).file());
                System.out.println("  Line: " + ((AssumptionFailedException) ex.getCause()).line());
            }

            ex.getCause().printStackTrace(System.out);
        }
        catch (IllegalAccessException ex)
        {
            ex.printStackTrace(System.out);
        }
    }

    public void printHelp()
    {
        final URL url = Resources.getResource(Main.class, HELP);

        try
        {
            System.out.println(Resources.toString(url, Charset.defaultCharset()));
        }
        catch (Exception ex)
        {
            ex.printStackTrace(System.out);
            return;
        }
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
    public void visit_case_help(final ITreeNode node)
    {
        printHelp();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_case_version(final ITreeNode node)
    {
        final URL url = Resources.getResource(Main.class, VERSION);

        try
        {
            System.out.println(Resources.toString(url, Charset.defaultCharset()));
        }
        catch (Exception ex)
        {
            ex.printStackTrace(System.out);
            return;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_case_license(final ITreeNode node)
    {
        final URL url = Resources.getResource(Main.class, LICENSE);

        try
        {
            System.out.println(Resources.toString(url, Charset.defaultCharset()));
        }
        catch (Exception ex)
        {
            ex.printStackTrace(System.out);
            return;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_case_run(final ITreeNode node)
    {
        visitChildren(node);

        final Invokable function = new Invokable()
        {
            @Override
            public void invoke()
                    throws ClassNotFoundException,
                           NoSuchMethodException,
                           InvocationTargetException,
                           IllegalAccessException
            {
                /**
                 * Run the program.
                 */
                cmp.run(args);
            }
        };

        run(function);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_case_debug(final ITreeNode node)
    {
        visitChildren(node);

        final Invokable function = new Invokable()
        {
            @Override
            public void invoke()
                    throws ClassNotFoundException,
                           NoSuchMethodException,
                           InvocationTargetException,
                           IllegalAccessException
            {
                /**
                 * Enter debug mode.
                 */
                Autumn.enableDebugger();
                Autumn.enableAssume();

                /**
                 * Run the program.
                 */
                cmp.run(args);
            }
        };

        run(function);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_case_test(final ITreeNode node)
    {
        visitChildren(node);

        final Invokable function = new Invokable()
        {
            @Override
            public void invoke()
                    throws ClassNotFoundException,
                           NoSuchMethodException,
                           InvocationTargetException,
                           IllegalAccessException
            {
                final TestResults results = cmp.test();

                results.print(System.out);
            }
        };

        run(function);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_case_doc(final ITreeNode node)
    {
        visitChildren(node);

        /**
         * Load the project.
         */
        try
        {
            final File project = resolveProject();

            cmp.loadProject(project);
        }
        catch (IOException ex)
        {
            System.out.println("The project coult not be loaded.");
            ex.printStackTrace(System.out);
        }

        /**
         * Generate the documentation.
         */
        cmp.document();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_case_create(final ITreeNode node)
    {
        visitChildren(node);

        try
        {
            final File userdir = new File(System.getProperty("user.dir"));

            System.out.println("Current Directory = " + userdir);
            System.out.println("Project Name = " + name);

            Autumn.createProject(userdir, name);
        }
        catch (IOException ex)
        {
            System.out.println("Error - The project could not be created.");
            ex.printStackTrace(System.out);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_name(final ITreeNode node)
    {
        visitChildren(node);

        name = args.get(0).trim();
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
    public void visit_qstring(final ITreeNode node)
    {
        final String text = node.text().substring(0, node.length() - 2);

        argument = text;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_pstring(final ITreeNode node)
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

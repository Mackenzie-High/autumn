package high.mackenzie.autumn.lang.compiler.args;

import autumn.lang.compiler.AutumnProject;
import autumn.lang.compiler.errors.BasicErrorReporter;
import autumn.lang.exceptions.AssertionFailedException;
import autumn.lang.exceptions.AssumptionFailedException;
import autumn.util.test.TestResults;
import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import high.mackenzie.autumn.Main;
import high.mackenzie.autumn.resources.Finished;
import com.mackenziehigh.snowflake.ITreeNode;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.NoSuchFileException;
import java.util.LinkedList;
import java.util.List;

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
                throws Exception;
    }

    private static final String HELP = "/high/mackenzie/autumn/resources/help.txt";

    private static final String LICENSE = "/high/mackenzie/autumn/resources/license.txt";

    private static final String VERSION = "/high/mackenzie/autumn/resources/version.txt";

    private String name;

    private static AutumnProject project;

    /**
     * These are the paths to the jar files.
     */
    private static final LinkedList<File> paths = Lists.newLinkedList();

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
     * This method resolves the name of a file.
     *
     * <p>
     * The file may already be resolved, or the file may be in the current directory,
     * or the file may be in the home directory.
     * </p>
     *
     * @param file is the file to resolve.
     * @return the actual path to the file.
     * @throws NoSuchFileException if the file cannot be found.
     */
    private static File resolve(final File file)
            throws NoSuchFileException
    {
        final File CURRENT = new File(System.getProperty("user.dir"));

        final File HOME = new File(System.getProperty("user.home"));

        final boolean atom = file.getPath().contains(System.getProperty("file.separator")) == false;

        final File possibility1 = new File(CURRENT, file.getName());

        final File possibility2 = new File(HOME, file.getName());

        if (file.exists())
        {
            return file;
        }
        else if (atom && possibility1.exists())
        {
            return possibility1;
        }
        else if (atom && possibility2.exists())
        {
            return possibility2;
        }
        else
        {
            throw new NoSuchFileException(file.toString());
        }
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
        final File folder = resolveProject();
        final BasicErrorReporter reporter = new BasicErrorReporter(System.out);
        project = new AutumnProject(folder, reporter);

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
        catch (MalformedURLException ex)
        {
            ex.printStackTrace(System.out);
        }
        catch (Exception ex)
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
                           IllegalAccessException,
                           IOException
            {
                /**
                 * Run the program.
                 */
                project.run(args.toArray(new String[0]));
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
                           IllegalAccessException,
                           IOException
            {
                final TestResults results = project.test();

                results.print(System.out);
            }
        };

        run(function);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_case_execute(final ITreeNode node)
    {
        visitChildren(node);

        final Invokable function = new Invokable()
        {
            @Override
            public void invoke()
                    throws Exception
            {
                final List<File> jars = new LinkedList<File>();

                for (File path : paths)
                {
                    jars.add(resolve(path));
                }

                AutumnProject.execute(jars, args.toArray(new String[0]));
            }
        };

        run(function);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_case_compile(final ITreeNode node)
    {
        visitChildren(node);

        try
        {
            final File userdir = new File(System.getProperty("user.dir"));

            final AutumnProject project = new AutumnProject(userdir, new BasicErrorReporter(System.out));

            project.compile();
        }
        catch (IOException ex)
        {
            System.out.println("Error - The project could not be compiled.");
            ex.printStackTrace(System.out);
        }
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

            AutumnProject.create(new File(userdir, name));
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
    public void visit_path(final ITreeNode node)
    {
        visitChildren(node);

        paths.add(new File(args.pollLast()));
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
        final String text = node.childAt(2).text().substring(0, node.childAt(2).text().length());

        argument = text;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_pstring(final ITreeNode node)
    {
        final String text = node.childAt(1).text();

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

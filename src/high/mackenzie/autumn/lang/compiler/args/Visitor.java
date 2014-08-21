package high.mackenzie.autumn.lang.compiler.args;

import autumn.lang.compiler.Autumn;
import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import high.mackenzie.autumn.Main;
import high.mackenzie.snowflake.ITreeNode;
import java.io.FileNotFoundException;
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
public final class Visitor
        extends AbstractVisitor
{
    private static final String HELP = "/high/mackenzie/autumn/resources/help.txt";

    private static final String LICENSE = "/high/mackenzie/autumn/resources/license.txt";

    private static final String VERSION = "/high/mackenzie/autumn/resources/version.txt";

    private String src;

    /**
     * These are the command-line arguments to pass to the interpreted program.
     */
    private final LinkedList<String> args = Lists.newLinkedList();

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

        final Autumn cmp = new Autumn();

        /**
         * Read the source-file.
         */
        try
        {
            cmp.srcFile(src);
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Error - No Such File: " + src);
        }
        catch (IOException ex)
        {
            System.out.println("Error - The source-file could not be read.");
            ex.printStackTrace(System.out);
        }

        /**
         * Execute the script.
         */
        try
        {
            cmp.run(args);
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
            ex.printStackTrace(System.out);
        }
        catch (IllegalAccessException ex)
        {
            ex.printStackTrace(System.out);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_src(final ITreeNode node)
    {
        visitChildren(node);

        src = argument;

        // Remove the src file from the list of arguments.
        args.remove();
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

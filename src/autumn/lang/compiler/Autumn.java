package autumn.lang.compiler;

import autumn.lang.compiler.ast.nodes.Module;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * An instance of this class simplifies the use of Autumn.
 *
 * @author Mackenzie High
 */
public final class Autumn
{
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

    public void srcFile(final File file)
    {
    }

    public void srcFile(final String file)
    {
    }

    public void srcURL(final URL url)
    {
    }

    public void srcURL(final String url)
    {
    }

    public void src(final Module node)
    {
        Preconditions.checkNotNull(node);

        modules.add(node);
    }

    public CompiledProgram compile()
    {
        return null;
    }

    public CompiledProgram compile(final File out)
            throws IOException
    {
        final CompiledProgram program = compile();

        program.jar(out);

        return program;
    }

    public void run(final String[] args)
    {
    }

    public void run(final Iterable<String> args)
    {
        run(Lists.newArrayList(args).toArray(new String[0]));
    }

    public void test()
    {
    }
}

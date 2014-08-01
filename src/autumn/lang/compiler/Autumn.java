package autumn.lang.compiler;

import autumn.lang.compiler.ast.nodes.Module;
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

    public void document(final File outdir)
    {
    }
}

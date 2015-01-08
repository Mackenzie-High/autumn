package high.mackenzie.autumn.compiler.documentor;

import autumn.lang.compiler.ast.nodes.Module;
import autumn.lang.compiler.ast.nodes.Name;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An instance of this class generates browsable documentation based on Autumn doc-comments.
 *
 * @author Mackenzie High
 */
public final class Documentor
{
    private final File folder;

    public final ClassLoader loader;

    public final GlobalPage global;

    public final List<ModulePage> modules = Lists.newLinkedList();

    /**
     * Sole Constructor.
     *
     * @param loader
     * @param program
     * @param folder is the path to the documentation folder.
     */
    public Documentor(final ClassLoader loader,
                      final List<Module> program,
                      final File folder)
    {
        this.loader = loader;
        this.folder = folder;

        this.global = new GlobalPage(this);

        for (Module node : program)
        {
            final ModulePage page = new ModulePage(this, node);

            global.add(page.namespace());

            modules.add(page);
        }
    }

    public void add(final AbstractTypePage type)
    {
        final String namespace = type.name().substring(0, type.name().lastIndexOf("."));

        global.packages.get(namespace).add(type);
    }

    public void generate()
    {
        /**
         * Generate the style.css file.
         */
        generateCSS();

        /**
         * Generate all the files related to modules, functions, tuples, etc.
         */
        for (ModulePage page : modules)
        {
            page.generate();
        }

        /**
         * Generate the index of packages and the indexes thereof.
         */
        global.generate();
    }

    private void generateCSS()
    {
        final File file = new File("style.css");

        final String css = Utils.template("style.css");

        write(file, css);
    }

    public void write(final File file,
                      final String content)
    {
        try
        {
            folder.mkdirs();

            final File path = new File(folder, file.toString());

            path.createNewFile();

            Files.write(content, path, Charset.defaultCharset());
        }
        catch (IOException ex)
        {
            Logger.getLogger(Documentor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String link(final String name,
                              final File file)
    {
        return "<a href=\"" + file.toString() + "\"> " + name + "</a>";
    }

    public static String name(final ModulePage module,
                              final Name name)
    {
        final String module_name = module.name();

        final String namespace = module_name.substring(0, module_name.indexOf('.'));

        final String type = namespace + "." + name.getName();

        return type;
    }
}

package high.mackenzie.autumn.compiler.documentor;

import autumn.lang.compiler.ast.nodes.Name;
import autumn.lang.compiler.ast.nodes.Namespace;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.File;
import java.util.List;
import java.util.SortedMap;

/**
 * An instance of this class represents the list of packages in the generated documentation.
 *
 * @author Mackenzie High
 */
public final class GlobalPage
        extends AbstractPage
{
    private final List<String> list = Lists.newLinkedList();

    public final SortedMap<String, PackagePage> packages = Maps.newTreeMap();

    public GlobalPage(final Documentor documentor)
    {
        super(documentor);
    }

    public void add(final Namespace namespace)
    {
        final StringBuilder key = new StringBuilder();

        for (Name part : namespace.getNames())
        {
            key.append(part.getName());
        }

        final PackagePage value = new PackagePage(documentor, key.toString());

        packages.put(key.toString(), value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void generate()
    {
        /**
         * Generate an index page for each of the packages.
         */
        for (PackagePage page : packages.values())
        {
            page.generate();
        }

        /**
         * Generate the global index itself.
         */
        final String template = Utils.template("GlobalIndex.html");

        final String html = template;

        final String list_of_packages = createListOfPackages();

        final String text = template.replace("#LIST-OF-PACKAGES#", list_of_packages);

        documentor.write(file(), text);
    }

    private String createListOfPackages()
    {
        final StringBuilder html = new StringBuilder();

        html.append("<ul>");

        for (PackagePage page : packages.values())
        {
            html.append("<li>").append(page.link()).append("</li>");
        }

        html.append("</ul>");

        return html.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public File file()
    {
        return new File("index.html");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name()
    {
        return "index";
    }
}

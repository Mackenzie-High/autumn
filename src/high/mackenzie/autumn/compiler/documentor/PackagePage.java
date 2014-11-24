package high.mackenzie.autumn.compiler.documentor;

import com.google.common.collect.Maps;
import java.io.File;
import java.util.SortedMap;

/**
 *
 * @author mackenzie
 */
public final class PackagePage
        extends AbstractPage
{
    private final String name;

    private final SortedMap<String, AbstractTypePage> types = Maps.newTreeMap();

    public PackagePage(final Documentor documentor,
                       final String namespace)
    {
        super(documentor);

        this.name = namespace;
    }

    public void add(final AbstractTypePage page)
    {
        types.put(page.name(), page);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void generate()
    {
        final String template = Utils.template("PackageIndex.html");

        final String table_of_annotations = createTable(AnnotationPage.class);

        final String table_of_enums = createTable(EnumPage.class);

        final String table_of_exceptions = createTable(ExceptionPage.class);

        final String table_of_functors = createTable(FunctorPage.class);

        final String table_of_designs = createTable(DesignPage.class);

        final String table_of_structs = createTable(StructPage.class);

        final String table_of_tuples = createTable(TuplePage.class);

        final String table_of_modules = createTable(ModulePage.class);

        final String text = template.replace("#NAME#", name())
                .replace("#TABLE-OF-ANNOTATIONS#", table_of_annotations)
                .replace("#TABLE-OF-ENUMS#", table_of_enums)
                .replace("#TABLE-OF-EXCEPTIONS#", table_of_exceptions)
                .replace("#TABLE-OF-FUNCTORS#", table_of_functors)
                .replace("#TABLE-OF-DESIGNS#", table_of_designs)
                .replace("#TABLE-OF-STRUCTS#", table_of_structs)
                .replace("#TABLE-OF-TUPLES#", table_of_tuples)
                .replace("#TABLE-OF-MODULES#", table_of_modules);

        documentor.write(file(), text);
    }

    private String createTable(final Class type_of_page)
    {
        final StringBuilder table = new StringBuilder();

        table.append("<table class=\"package-page-table\"");

        table.append("<tr>").append("<td><b>Type Name</b></td>").append("<td><b>Description</b></td>").append("</tr>");

        for (AbstractTypePage page : types.values())
        {
            if (page.getClass() == type_of_page)
            {
                createTableEntry(table, page);
            }
        }

        table.append("</table>");

        return table.toString();
    }

    private void createTableEntry(final StringBuilder table,
                                  final AbstractTypePage page)
    {
        final String about = page.createSummary();

        table.append("<tr>")
                .append("<td class=\"package-page-table-td\">").append(page.link()).append("</td>")
                .append("<td>").append(about).append("</td>")
                .append("</tr>");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public File file()
    {
        return new File("package-" + name().replace('.', '-') + ".html");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name()
    {
        return name;
    }
}

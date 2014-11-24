package high.mackenzie.autumn.compiler.documentor;

import autumn.lang.compiler.ast.nodes.FunctorDefinition;
import com.google.common.collect.Maps;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.SortedMap;

/**
 *
 * @author mackenzie
 */
public final class FunctorPage
        extends AbstractTypePage
{
    private final ModulePage module;

    private final FunctorDefinition node;

    private Method invoke;

    public FunctorPage(final ModulePage module,
                       final Documentor documentor,
                       final FunctorDefinition node)
    {
        super(documentor, node.getComment());

        this.module = module;
        this.node = node;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void generate()
    {
        final Class klass = Utils.classOf(documentor.loader, name());

        documentor.add(this);

        method_table.load(klass);

        findInvoke(klass);

        final String template = Utils.template("FunctorPage.html");

        final String name = name();

        final String title = name;

        final String simple = klass.getSimpleName();

        final String comment = Utils.commentOf(node.getComment());

        final String supers = Utils.listofSupertypes(klass);

        final String parameters = createTableOfParameters();

        final String return_type = Utils.linkTo(invoke.getReturnType(), true);

        final String return_summary = " ";

        final String text = template.replace("#NAME#", name)
                .replace("#SIMPLE-NAME#", simple)
                .replace("#TITLE", title)
                .replace("#COMMENT#", comment)
                .replace("#SUPERTYPES#", supers)
                .replace("#TABLE-OF-METHODS#", method_table.createTable())
                .replace("#TABLE-OF-PARAMETERS#", parameters)
                .replace("#RETURN-TYPE#", return_type)
                .replace("#RETURN-SUMMARY#", return_summary);

        documentor.write(file(), text);
    }

    public String createTableOfParameters()
    {
        final SortedMap<String, String> descriptions = Maps.newTreeMap();

        final SortedMap<String, Class> types = Maps.newTreeMap();

        loadParameters(types);

        final StringBuilder table = new StringBuilder();

        table.append("<table>");

        table.append("<tr>").append("<td><b>Name</b></td>").append("<td><b>Type</b></td>").append("<td><b>Description</b></td>").append("</tr>");

        for (String element : types.keySet())
        {
            final String description = descriptions.get(element);

            createTableOfElementsEntry(table, element, types.get(element), description);
        }

        table.append("</table>");

        return table.toString();
    }

    public void createTableOfElementsEntry(final StringBuilder table,
                                           final String variable,
                                           final Class type,
                                           final String description)
    {
        final String about = description == null ? "" : description;

        final String link = Utils.linkTo(type, true);

        table.append("<tr>")
                .append("<td>").append(variable).append("</td>")
                .append("<td>").append(link).append("</td>")
                .append("<td>").append(about).append("</td>")
                .append("</tr> ");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name()
    {
        return Documentor.name(module, node.getName());
    }

    private void findInvoke(final Class klass)
    {
        for (Method method : klass.getDeclaredMethods())
        {
            if (method.getName().equals("invoke"))
            {
                invoke = method;
                return;
            }
        }

        throw new RuntimeException("Malformed Functor");
    }

    private void loadParameters(final Map<String, Class> types)
    {
        for (int i = 0; i < node.getParameters().getParameters().size(); i++)
        {
            final String name = node.getParameters().getParameters().get(i).getVariable().getName();

            final Class type = invoke.getParameterTypes()[i];

            types.put(name, type);
        }
    }
}

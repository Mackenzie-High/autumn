package high.mackenzie.autumn.compiler.documentor;

import autumn.lang.compiler.ast.nodes.AnnotationDefinition;
import autumn.lang.compiler.ast.nodes.DesignDefinition;
import autumn.lang.compiler.ast.nodes.EnumDefinition;
import autumn.lang.compiler.ast.nodes.ExceptionDefinition;
import autumn.lang.compiler.ast.nodes.FunctionDefinition;
import autumn.lang.compiler.ast.nodes.FunctorDefinition;
import autumn.lang.compiler.ast.nodes.Module;
import autumn.lang.compiler.ast.nodes.ModuleDirective;
import autumn.lang.compiler.ast.nodes.Name;
import autumn.lang.compiler.ast.nodes.Namespace;
import autumn.lang.compiler.ast.nodes.StructDefinition;
import autumn.lang.compiler.ast.nodes.TupleDefinition;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.SortedMap;

/**
 *
 * @author mackenzie
 */
public final class ModulePage
        extends AbstractTypePage
{
    public final Module node;

    private final List<AbstractPage> pages = Lists.newArrayList();

    public ModulePage(final Documentor documentor,
                      final Module node)
    {
        super(documentor, node.getModuleDirectives().get(0).getComment());

        this.node = node;

        for (AnnotationDefinition x : node.getAnnotations())
        {
            pages.add(new AnnotationPage(this, documentor, x));
        }

        for (DesignDefinition x : node.getDesigns())
        {
            pages.add(new DesignPage(this, documentor, x));
        }

        for (EnumDefinition x : node.getEnums())
        {
            pages.add(new EnumPage(this, documentor, x));
        }

        for (ExceptionDefinition x : node.getExceptions())
        {
            pages.add(new ExceptionPage(this, documentor, x));
        }

        for (FunctionDefinition x : node.getFunctions())
        {
            pages.add(new FunctionPage(this, documentor, x));
        }

        for (FunctorDefinition x : node.getFunctors())
        {
            pages.add(new FunctorPage(this, documentor, x));
        }

        for (StructDefinition x : node.getStructs())
        {
            pages.add(new StructPage(this, documentor, x));
        }

        for (TupleDefinition x : node.getTuples())
        {
            pages.add(new TuplePage(this, documentor, x));
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void generate()
    {
        /**
         * Generate the pages for the tuples, structs, etc defined in the module.
         */
        for (AbstractPage page : pages)
        {
            page.generate();
        }

        /**
         * Generate the page for the module itself.
         */
        final Class klass = Utils.classOf(documentor.loader, name());

        documentor.add(this);

        method_table.load(klass);

        final String template = Utils.template("ModulePage.html");

        final String comment = Utils.commentOf(node.getModuleDirectives().get(0).getComment());

        final String list_of_supers = Utils.listofSupertypes(klass);

        final String table_of_functions = createTableOfFunctions();

        final String methods = method_table.createTable();

        final String text = template.replace("#NAME#", name())
                .replace("#SUPERTYPES#", list_of_supers)
                .replace("#COMMENT#", comment)
                .replace("#TABLE-OF-FUNCTIONS#", table_of_functions)
                .replace("#TABLE-OF-METHODS#", methods);

        documentor.write(file(), text);
    }

    private String createTableOfFunctions()
    {
        final StringBuilder table = new StringBuilder();

        table.append("<table>");

        table.append("<tr>").append("<td><b>Function</b></td>").append("<td><b>Summary</b></td>").append("</tr>");

        for (FunctionPage page : sortedFunctions())
        {
            createTableOfFunctionsEntry(table, page);
        }

        table.append("</table>");

        return table.toString();
    }

    private void createTableOfFunctionsEntry(final StringBuilder table,
                                             final FunctionPage page)
    {
        table.append("<tr>")
                .append("<td>").append(page.toString()).append("</td>")
                .append("<td>").append("").append("</td>")
                .append("</tr> ");
    }

    private List<FunctionPage> sortedFunctions()
    {
        final SortedMap<String, FunctionPage> functions = Maps.newTreeMap();

        for (AbstractPage page : pages)
        {
            if (page.getClass() == FunctionPage.class)
            {
                final FunctionPage function = (FunctionPage) page;

                functions.put(function.toString(), function);
            }
        }


        return Lists.newLinkedList(functions.values());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String name()
    {
        final ModuleDirective directive = node.getModuleDirectives().get(0);

        final StringBuilder result = new StringBuilder();

        for (Name part : directive.getNamespace().getNames())
        {
            result.append(part.getName()).append('.');
        }

        result.append(directive.getName().getName());

        return result.toString();
    }

    public Namespace namespace()
    {
        return node.getModuleDirectives().get(0).getNamespace();
    }
}

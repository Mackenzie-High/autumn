package high.mackenzie.autumn.compiler.documentor;

import autumn.lang.compiler.ast.commons.IRecord;
import autumn.lang.compiler.ast.nodes.Element;
import autumn.lang.compiler.ast.nodes.StructDefinition;
import autumn.lang.compiler.ast.nodes.TupleDefinition;

/**
 *
 * @author mackenzie
 */
public abstract class AbstractRecordPage
        extends AbstractTypePage
{
    private final ModulePage module;

    private final IRecord node;

    private final String form;

    public AbstractRecordPage(final ModulePage module,
                              final Documentor documentor,
                              final IRecord node)
    {
        super(documentor, node.getComment());

        this.module = module;
        this.node = node;

        if (node instanceof TupleDefinition)
        {
            form = "Tuple";
        }
        else if (node instanceof StructDefinition)
        {
            form = "Struct";
        }
        else // DesignDefinition
        {
            form = "Design";
        }

        /**
         * Extract the names of the elements declared directly inside the record.
         */
        for (Element element : node.getElements().getElements())
        {
            //elements.add(element.getName().getName());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void generate()
    {
        final Class klass = Utils.classOf(documentor.loader, name());

        documentor.add(this);

        method_table.load(klass);

        final String template = Utils.template("RecordPageTemplate.html");

        final String comment = Utils.commentOf(node.getComment());

        final String supers = Utils.listofSupertypes(klass);

        final String elements = Utils.createTableOfElements(node.getComment(), klass);

        final String methods = method_table.createTable();

        final String html = template
                .replace("#FORM#", form)
                .replace("#TITLE#", name())
                .replace("#NAME#", name())
                .replace("#COMMENT#", comment)
                .replace("#SUPERTYPES#", supers)
                .replace("#TABLE-OF-ELEMENTS#", elements)
                .replace("#TABLE-OF-METHODS#", methods);



        documentor.write(file(), html);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String name()
    {
        return Documentor.name(module, node.getName());
    }
}

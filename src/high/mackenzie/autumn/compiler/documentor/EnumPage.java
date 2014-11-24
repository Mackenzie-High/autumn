package high.mackenzie.autumn.compiler.documentor;

import autumn.lang.compiler.ast.nodes.EnumDefinition;

/**
 *
 * @author mackenzie
 */
public final class EnumPage
        extends AbstractTypePage
{
    private final ModulePage module;

    private final EnumDefinition node;

    public EnumPage(final ModulePage module,
                    final Documentor documentor,
                    final EnumDefinition node)
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

        final String template = Utils.template("EnumPage.html");

        final String name = name();

        final String title = name;

        final String simple = klass.getSimpleName();

        final String comment = Utils.commentOf(node.getComment());

        final String supers = Utils.listofSupertypes(klass);

        final String elements = Utils.createTableOfElements(node.getComment(), klass);

        final String methods = method_table.createTable();

        final String text = template.replace("#NAME#", name)
                .replace("#SIMPLE-NAME#", simple)
                .replace("#TITLE", title)
                .replace("#COMMENT#", comment)
                .replace("#SUPERTYPES#", supers)
                .replace("#TABLE-OF-ELEMENTS#", elements)
                .replace("#TABLE-OF-METHODS#", methods);

        documentor.write(file(), text);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name()
    {
        return Documentor.name(module, node.getName());
    }
}

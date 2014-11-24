package high.mackenzie.autumn.compiler.documentor;

import autumn.lang.compiler.ast.nodes.DocComment;
import java.io.File;

/**
 *
 * @author mackenzie
 */
abstract class AbstractTypePage
        extends AbstractPage
{
    protected DocComment doc;

    protected MethodTable method_table = new MethodTable();

    public AbstractTypePage(final Documentor documentor,
                            final DocComment comment)
    {
        super(documentor);

        this.doc = comment;
    }

    public String createSummary()
    {
        if (doc == null || doc.getLines().isEmpty())
        {
            return "";
        }

        final String summary = doc.getLines().get(0).getText();

        return summary;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final File file()
    {
        final String page = name().replace('.', '-') + ".html";

        return new File(page);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String toString()
    {
        return name();
    }
}

package high.mackenzie.autumn.compiler.documentor;

import java.io.File;

/**
 *
 * @author mackenzie
 */
public abstract class AbstractPage
{
    public abstract void generate();

    public abstract File file();

    public abstract String name();

    protected final Documentor documentor;

    public AbstractPage(final Documentor documentor)
    {
        this.documentor = documentor;
    }

    /**
     * {@inheritDoc}
     */
    public final String link()
    {
        String name = name();

        name = name.substring(name.indexOf('.') + 1, name.length());

        return Documentor.link(name, file());
    }
}

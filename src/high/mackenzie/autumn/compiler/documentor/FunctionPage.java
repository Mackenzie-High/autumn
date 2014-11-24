package high.mackenzie.autumn.compiler.documentor;

import autumn.lang.compiler.ast.nodes.FormalParameter;
import autumn.lang.compiler.ast.nodes.FunctionDefinition;
import java.io.File;

/**
 *
 * @author mackenzie
 */
public final class FunctionPage
        extends AbstractPage
{
    private final ModulePage module;

    private final FunctionDefinition node;

    public FunctionPage(final ModulePage module,
                        final Documentor documentor,
                        final FunctionDefinition node)
    {
        super(documentor);

        this.module = module;
        this.node = node;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void generate()
    {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public File file()
    {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name()
    {
        return null;
    }

    @Override
    public String toString()
    {
        final StringBuilder signature = new StringBuilder();

        signature.append(node.getName().getName());

        signature.append(" ");

        signature.append("(");

        int i = 0;

        for (FormalParameter param : node.getParameters().getParameters())
        {
            signature.append(param.getVariable().getName());
            signature.append(" : ");
            signature.append("type");

            if (i++ < node.getParameters().getParameters().size() - 1)
            {
                signature.append(", ");
            }
        }

        signature.append(")");

        signature.append(" : ");

        signature.append("returns");

        return signature.toString();
    }
}

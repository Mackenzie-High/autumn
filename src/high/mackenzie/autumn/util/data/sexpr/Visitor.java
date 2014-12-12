package high.mackenzie.autumn.util.data.sexpr;

import high.mackenzie.snowflake.ITreeNode;

/**
 *
 * @author mackenzie
 */
public final class Visitor
        extends AbstractVisitor
{
    @Override
    public void visitUnknown(final ITreeNode node)
    {
        for (ITreeNode child : node.children())
        {
            visit(child);
        }
    }
}

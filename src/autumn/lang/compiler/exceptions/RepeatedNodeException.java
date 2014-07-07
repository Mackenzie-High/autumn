package autumn.lang.compiler.exceptions;

import autumn.lang.compiler.ast.commons.IConstruct;
import com.google.common.base.Preconditions;

/**
 * This type of exception indicates that a node occurs more than once in an Abstract Syntax Tree.
 *
 * @author Mackenzie High
 */
public final class RepeatedNodeException
        extends RuntimeException
{
    private final IConstruct node;

    /**
     * Sole Constructor.
     *
     * @param node is the repeated node itself.
     */
    public RepeatedNodeException(final IConstruct node)
    {
        Preconditions.checkNotNull(node);

        this.node = node;
    }

    /**
     * This method retrieves the node that is repeated illegally.
     *
     * @return the aforedescribed node.
     */
    public IConstruct node()
    {
        return node;
    }
}

package autumn.lang.compiler.exceptions;

import autumn.lang.compiler.ast.commons.IConstruct;
import com.google.common.base.Preconditions;
import com.mackenziehigh.autumn.resources.Finished;

/**
 * This type of exception indicates that a in an Abstract Syntax Tree is unprintable.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public final class UnprintableNodeException
        extends RuntimeException
{
    private final IConstruct node;

    /**
     * Sole Constructor.
     *
     * @param node is the node that cannot be printed.
     */
    public UnprintableNodeException(IConstruct node)
    {
        Preconditions.checkNotNull(node);

        this.node = node;
    }

    /**
     * This method retrieves the node that cannot be printed.
     *
     * @return the aforedescribed node.
     */
    public IConstruct node()
    {
        return node;
    }
}

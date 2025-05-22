package autumn.lang.compiler.exceptions;

import autumn.lang.compiler.ast.commons.IConstruct;
import com.google.common.base.Preconditions;
import com.mackenziehigh.autumn.resources.Finished;

/**
 * This type of exception indicates that an Abstract Syntax Tree node contains a part that is null,
 * when it is forbidden to do so. In other words, this type of exception indicates that an AST
 * is missing vitally important information.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public class IncompleteNodeException
        extends RuntimeException
{
    private final IConstruct parent;

    /**
     * Sole Constructor.
     *
     * @param node is the parent of the missing node.
     */
    public IncompleteNodeException(final IConstruct parent)
    {
        Preconditions.checkNotNull(parent);

        this.parent = parent;
    }

    /**
     * This method retrieves the parent of the node that is missing.
     *
     * @return the aforedescribed parent node.
     */
    public IConstruct parent()
    {
        return parent;
    }
}

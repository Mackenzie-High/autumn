/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autumn.lang.compiler.exceptions;

import autumn.lang.compiler.ast.commons.IConstruct;
import com.google.common.base.Preconditions;

/**
 * This type of exception indicates that a in an Abstract Syntax Tree is unprintable.
 *
 * @author Mackenzie High
 */
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

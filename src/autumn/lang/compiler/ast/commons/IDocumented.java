package autumn.lang.compiler.ast.commons;

import autumn.lang.compiler.ast.nodes.DocComment;

/**
 * An instance of this interface is an AST node that has a documentation comment applied to it.
 *
 * @author Mackenzie High
 */
public interface IDocumented
        extends IConstruct
{
    /**
     * This method gets the doc-comment that is applied to this construct.
     *
     * @return the aforedescribed doc-comment.
     */
    public DocComment getComment();

    /**
     * This method sets the doc-comment that is applied to this construct.
     *
     * @param comment is the doc-comment to apply to this construct.
     * @return a modified copy of this object.
     */
    public IDocumented setComment(DocComment comment);
}

package autumn.lang.compiler.ast.commons;

import autumn.lang.compiler.ast.nodes.IAstVisitor;
import autumn.lang.compiler.ast.nodes.SourceLocation;
import com.mackenziehigh.autumn.resources.Finished;

/**
 * An instance of this class is an AST node.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public interface IConstruct
{
    /**
     * This method causes a visitor to visit this AST node.
     *
     * @param visitor is the visitor that will visit.
     */
    public void accept(IAstVisitor visitor);

    /**
     * This method sets the information which indicates where this construct originated from.
     *
     * @param value is the aforedescribed information.
     * @return a modified copy of this object.
     */
    public IConstruct setLocation(SourceLocation value);

    /**
     * This method gets the information which indicates where this construct originated from.
     *
     * @return the aforedescribed information.
     */
    public SourceLocation getLocation();

    /**
     * This method creates a shallow copy of this object.
     *
     * @return a copy of this object.
     */
    public IConstruct copy();
}

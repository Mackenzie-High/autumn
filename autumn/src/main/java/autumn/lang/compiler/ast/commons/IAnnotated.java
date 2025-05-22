package autumn.lang.compiler.ast.commons;

import autumn.lang.compiler.ast.nodes.AnnotationList;
import com.mackenziehigh.autumn.resources.Finished;

/**
 * An instance of this interface is an AST node that represents an annotatable construct.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public interface IAnnotated
        extends IConstruct
{
    /**
     * This method gets the list of annotations applied to the construct.
     *
     * @return the annotation-list.
     */
    public AnnotationList getAnnotations();

    /**
     * This method sets the list of annotations applied to the construct.
     *
     * @return a modified copy of this object.
     */
    public IAnnotated setAnnotations(AnnotationList annotations);
}

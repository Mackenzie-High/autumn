/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autumn.lang.compiler.ast.commons;

import autumn.lang.compiler.ast.nodes.AnnotationList;

/**
 * An instance of this interface is an AST node that represents an annotatable construct.
 *
 * @author Mackenzie High
 */
public interface IAnnotated
        extends IConstruct
{
    public AnnotationList getAnnotations();

    public IConstruct setAnnotations(AnnotationList annotations);
}

package autumn.lang.compiler.ast.commons;

import autumn.lang.compiler.ast.nodes.AnnotationList;
import autumn.lang.compiler.ast.nodes.DocComment;
import autumn.lang.compiler.ast.nodes.ElementList;
import autumn.lang.compiler.ast.nodes.Name;
import autumn.lang.compiler.ast.nodes.TypeSpecifier;
import high.mackenzie.autumn.resources.Finished;

/**
 * An instance of this interface is the definition of a record type.
 *
 * @author Mackenzie High
 */
@Finished("2014/10/12")
public interface IRecord
        extends IDocumented,
                IAnnotated
{
    /**
     * {@inheritDoc}
     */
    @Override
    public DocComment getComment();

    /**
     * {@inheritDoc}
     */
    @Override
    public IDocumented setComment(DocComment comment);

    /**
     * {@inheritDoc}
     */
    @Override
    public AnnotationList getAnnotations();

    /**
     * {@inheritDoc}
     */
    @Override
    public IAnnotated setAnnotations(AnnotationList annotations);

    /**
     * The method gets the simple name of the new record type.
     *
     * @return the simple name of the new type.
     */
    public Name getName();

    /**
     * This method sets the simple name of the new type.
     *
     * @param name is the simple name of the new type.
     * @return a modified copy of this object.
     */
    public IRecord setName(Name name);

    /**
     * This method gets the element declarations.
     *
     * @return the element declarations.
     */
    public ElementList getElements();

    /**
     * This method sets the element declarations.
     *
     * @param elements are the new element declarations.
     * @return a modified copy of this object.
     */
    public IRecord setElements(ElementList elements);

    /**
     * This method gets the direct supertype declarations.
     *
     * @return the direct supertypes.
     */
    public ConstructList<TypeSpecifier> getSupers();

    /**
     * This method sets the direct supertype declarations.
     *
     * @param supers are the new direct supertypes.
     * @return a modified copy of this object.
     */
    public IRecord setSupers(ConstructList<TypeSpecifier> supers);
}

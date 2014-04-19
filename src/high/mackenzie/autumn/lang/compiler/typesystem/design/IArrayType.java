/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.typesystem.design;

/**
 * An instance of this interface is the type of an array.
 *
 * @author Mackenzie High
 */
public interface IArrayType
        extends IReferenceType,
                IExpressionType
{
    /**
     * This method returns the type of the elements that are in an array of this type.
     *
     * <p>
     * For example, this method will return type <i>String</i>, if this type is <i>String[]</i>.
     * </p>
     *
     * @return the type of the elements in an array of this type.
     */
    public IElementType getElement();

    /**
     * This method returns the number of dimensions in an array of this type.
     *
     * <p>
     * For example, this method will return <i>1</i>, if this type is <i>String[]</i>.
     * Likewise, this method will return <i>3</i>, if this type is <i>String[][][]</i>.
     * </p>
     *
     * @return the dimensionality of an array of this type.
     */
    public int getDimensions();

    /**
     * {@inheritDoc}
     */
    @Override
    public Class toClass();
}

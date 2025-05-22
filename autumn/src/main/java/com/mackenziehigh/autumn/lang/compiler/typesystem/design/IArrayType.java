package com.mackenziehigh.autumn.lang.compiler.typesystem.design;

import com.mackenziehigh.autumn.resources.Finished;

/**
 * An instance of this interface is the type of an array.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public interface IArrayType
        extends IReferenceType,
                IVariableType
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.typesystem.design;

/**
 * An instance of this interface is the type of a field that is also an enum-constant.
 *
 * @author Mackenzie High
 */
public interface IEnumConstant
        extends IField
{
    /**
     * {@inheritDoc}
     *
     * @return <code>getOwner()</code>
     */
    @Override
    public IVariableType getType();

    /**
     * This method returns the constant's ordinal value.
     *
     * @return the ordinal value of the enum-constant.
     * @see java.lang.Enum
     */
    public int getOrdinal();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEnumConstant();
}

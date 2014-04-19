/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.typesystem;

import high.mackenzie.autumn.lang.compiler.typesystem.design.IEnumConstant;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IField;
import high.mackenzie.autumn.lang.compiler.typesystem.design.ITypeFactory;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;
import java.lang.reflect.Field;

/**
 *
 * @author mackenzie
 */
public final class CustomField
        extends AbstractCustomMember
        implements IField,
                   IEnumConstant
{
    private Integer ordinal = null;

    private IVariableType type;

    public CustomField(final ITypeFactory factory)
    {
        super(factory);
    }

    /**
     * This method sets the type of value that can be stored in the field.
     *
     * @param type is the aforedescribed type.
     */
    public void setType(final IVariableType type)
    {
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IVariableType getType()
    {
        return type;
    }

    /**
     * This method sets the ordinal that will be used for this enum-constant.
     *
     * <p>
     * Note: Setting the ordinal value will cause this field to become an enum-constant.
     * </p>
     *
     * @param ordinal is the ordinal value of this enum-constant field.
     */
    public void setOrdinal(final int ordinal)
    {
        this.ordinal = ordinal;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getOrdinal()
    {
        return ordinal;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEnumConstant()
    {
        return ordinal != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field getField()
    {
        return null;
    }
}

package com.mackenziehigh.autumn.lang.compiler.typesystem;

import com.google.common.base.Preconditions;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IEnumConstant;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IField;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.ITypeFactory;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IVariableType;
import com.mackenziehigh.autumn.resources.Finished;
import java.lang.reflect.Field;

/**
 * An instance of this class represents a field.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public final class CustomField
        extends AbstractCustomMember
        implements IField,
                   IEnumConstant
{
    private Integer ordinal = null;

    private IVariableType type;

    /**
     * Sole Constructor.
     *
     * @param factory is the type-factory that is used to access types.
     */
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
        Preconditions.checkNotNull(type);

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

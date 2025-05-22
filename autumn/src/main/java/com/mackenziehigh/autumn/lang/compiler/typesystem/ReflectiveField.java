package com.mackenziehigh.autumn.lang.compiler.typesystem;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IAnnotation;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IDeclaredType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IEnumConstant;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IField;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.ITypeFactory;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IVariableType;
import com.mackenziehigh.autumn.resources.Finished;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

/**
 * An instance of this class represents a field.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public final class ReflectiveField
        extends AbstractMember
        implements IField,
                   IEnumConstant
{
    protected final Field field;

    /**
     * Sole Constructor.
     *
     * @param factory is the type-factory that is used to access types.
     * @param field is the field that this new object will represent.
     */
    public ReflectiveField(final ITypeFactory factory,
                           final Field field)
    {
        super(factory);

        Preconditions.checkNotNull(field);

        this.field = field;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IDeclaredType getOwner()
    {
        return (IDeclaredType) getTypeFactory().fromClass(field.getDeclaringClass());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field getField()
    {
        return field;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IAnnotation> getAnnotations()
    {
        final List result = Lists.newLinkedList();

        for (Annotation element : field.getDeclaredAnnotations())
        {
            final IAnnotation annotation = CustomAnnotation.fromAnnotation(getTypeFactory(), element);

            result.add(annotation);
        }

        return ImmutableList.copyOf(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getModifiers()
    {
        return field.getModifiers();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName()
    {
        return field.getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IVariableType getType()
    {
        return (IVariableType) getTypeFactory().fromClass(field.getType());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEnumConstant()
    {
        return field.isEnumConstant();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getOrdinal()
    {
        final Object[] constants = field.getDeclaringClass().getEnumConstants();

        int i = 0;

        for (Object x : constants)
        {
            final Enum constant = (Enum) x;

            if (constant.name().equals(this.getName()))
            {
                break;
            }
            else
            {
                ++i;
            }
        }

        return i;
    }
}

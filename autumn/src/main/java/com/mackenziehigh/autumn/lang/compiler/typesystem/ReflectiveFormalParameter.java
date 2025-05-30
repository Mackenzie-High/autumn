package com.mackenziehigh.autumn.lang.compiler.typesystem;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IAnnotation;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IFormalParameter;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.ITypeFactory;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IVariableType;
import com.mackenziehigh.autumn.resources.Finished;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

/**
 * An instance of this class represents a previously compiled formal-parameter.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public final class ReflectiveFormalParameter
        implements IFormalParameter
{
    private final ITypeFactory factory;

    private final ImmutableList<Annotation> annotations;

    private final Class type;

    /**
     * Sole Constructor.
     *
     * @param factory is the type-factory that is used to access types.
     * @param annotations are the annotations applied to the formal-parameter.
     * @param type is the class object representation of the formal-parameter's type.
     */
    public ReflectiveFormalParameter(final ITypeFactory factory,
                                     final Annotation[] annotations,
                                     final Class type)
    {
        this.factory = factory;

        this.annotations = ImmutableList.copyOf(Arrays.asList(annotations));

        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IAnnotation> getAnnotations()
    {
        final List result = Lists.newLinkedList();

        for (Annotation element : annotations)
        {
            final IAnnotation annotation = CustomAnnotation.fromAnnotation(factory, element);

            result.add(annotation);
        }

        return ImmutableList.copyOf(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IVariableType getType()
    {
        return (IVariableType) factory.fromClass(type);
    }
}

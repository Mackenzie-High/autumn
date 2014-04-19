/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.typesystem;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IAnnotation;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IFormalParameter;
import high.mackenzie.autumn.lang.compiler.typesystem.design.ITypeFactory;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author mackenzie
 */
public final class ReflectiveFormalParameter
        implements IFormalParameter
{
    private final ITypeFactory factory;

    private final List<Annotation> annotations = Lists.newLinkedList();

    private final Class type;

    public ReflectiveFormalParameter(final ITypeFactory factory,
                                     final Annotation[] annotations,
                                     final Class type)
    {
        this.factory = factory;

        this.annotations.addAll(Arrays.asList(annotations));

        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<IAnnotation> getAnnotations()
    {
        final List result = Lists.newLinkedList();

        for (Annotation element : annotations)
        {
            final IAnnotation annotation = CustomAnnotation.fromAnnotation(factory, element);

            result.add(annotation);
        }

        return ImmutableSet.copyOf(result);
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

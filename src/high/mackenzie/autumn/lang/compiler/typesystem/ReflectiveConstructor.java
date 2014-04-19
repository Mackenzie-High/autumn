/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.typesystem;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IAnnotation;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IClassType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IConstructor;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IDeclaredType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IFormalParameter;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IReturnType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.ITypeFactory;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author mackenzie
 */
public class ReflectiveConstructor
        extends AbstractInvokableMember
        implements IConstructor
{
    private final Constructor constructor;

    public ReflectiveConstructor(final ITypeFactory factory,
                                 final Constructor constructor)
    {
        super(factory);

        this.constructor = constructor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Constructor getConstructor()
    {
        return constructor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IDeclaredType getOwner()
    {
        return (IDeclaredType) getTypeFactory().fromClass(constructor.getDeclaringClass());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<IAnnotation> getAnnotations()
    {
        final List result = Lists.newLinkedList();

        for (Annotation element : constructor.getDeclaredAnnotations())
        {
            final IAnnotation annotation = CustomAnnotation.fromAnnotation(getTypeFactory(), element);

            result.add(annotation);
        }

        return ImmutableSet.copyOf(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getModifiers()
    {
        return constructor.getModifiers();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IReturnType getReturnType()
    {
        return this.getTypeFactory().getVoid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName()
    {
        return "<init>";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IFormalParameter> getFormalParameters()
    {
        final List<IFormalParameter> result = Lists.newLinkedList();

        for (int i = 0; i < constructor.getParameterTypes().length; i++)
        {
            final Annotation[] annotations = constructor.getParameterAnnotations()[i];

            final Class type = constructor.getParameterTypes()[i];

            final IFormalParameter parameter = new ReflectiveFormalParameter(
                    getTypeFactory(),
                    annotations,
                    type);

            result.add(parameter);
        }

        return ImmutableList.copyOf(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<IClassType> getThrowsClause()
    {
        final List<IClassType> result = Lists.newLinkedList();

        for (Class exception : constructor.getExceptionTypes())
        {
            result.add((IClassType) getTypeFactory().fromClass(exception));
        }

        return ImmutableSet.copyOf(result);
    }
}

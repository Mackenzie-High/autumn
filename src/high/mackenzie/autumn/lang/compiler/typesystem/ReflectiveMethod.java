package high.mackenzie.autumn.lang.compiler.typesystem;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IAnnotation;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IAnnotationMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IClassType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IDeclaredType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IFormalParameter;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IReturnType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.ITypeFactory;
import high.mackenzie.autumn.resources.Finished;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

/**
 * An instance of this class represents a previously compiled method.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public final class ReflectiveMethod
        extends AbstractInvokableMember
        implements IMethod,
                   IAnnotationMethod
{
    private final Method method;

    /**
     * Sole Constructor.
     *
     * @param factory is the factory that is used to access types.
     * @param method is the method that this object will represent.
     */
    public ReflectiveMethod(final ITypeFactory factory,
                            final Method method)
    {
        super(factory);

        Preconditions.checkNotNull(method);

        this.method = method;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Method getMethod()
    {
        return method;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IDeclaredType getOwner()
    {
        final Class klass = method.getDeclaringClass();

        final IDeclaredType type = (IDeclaredType) getTypeFactory().fromClass(klass);

        return type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<IAnnotation> getAnnotations()
    {
        final List result = Lists.newLinkedList();

        for (Annotation element : method.getDeclaredAnnotations())
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
        return method.getModifiers();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IReturnType getReturnType()
    {
        return (IReturnType) getTypeFactory().fromClass(method.getReturnType());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName()
    {
        return method.getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IFormalParameter> getParameters()
    {
        final List<IFormalParameter> result = Lists.newLinkedList();

        for (int i = 0; i < method.getParameterTypes().length; i++)
        {
            final Annotation[] annotations = method.getParameterAnnotations()[i];

            final Class type = method.getParameterTypes()[i];

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

        for (Class exception : method.getExceptionTypes())
        {
            result.add((IClassType) getTypeFactory().fromClass(exception));
        }

        return ImmutableSet.copyOf(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAnnotationMethod()
    {
        return method.getDeclaringClass().isAnnotation();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        return this.getNamePlusDescriptor();
    }
}

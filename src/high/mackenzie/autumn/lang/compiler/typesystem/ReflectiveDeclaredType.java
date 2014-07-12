package high.mackenzie.autumn.lang.compiler.typesystem;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IAnnotation;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IAnnotationType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IClassType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IConstructor;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IEnumType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IField;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IInterfaceType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.ITypeFactory;
import high.mackenzie.autumn.resources.Finished;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import org.objectweb.asm.Type;

/**
 * An instance of this class is the type of an annotation, class, enum, or interface that
 * was already into a
 * <code>java.lang.ClassLoader</code>.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public class ReflectiveDeclaredType
        extends AbstractDeclaredType
        implements IAnnotationType,
                   IClassType,
                   IEnumType,
                   IInterfaceType
{
    protected final Class clazz;

    /**
     * Sole Constructor.
     *
     * @param factory is the type-factory that is used to access types.
     * @param clazz is the class object representation of the new type.
     */
    public ReflectiveDeclaredType(final ITypeFactory factory,
                                  final Class clazz)
    {
        super(factory, Type.getDescriptor(clazz));

        this.clazz = clazz;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAlreadyCompiled()
    {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNamespace()
    {
        return clazz.getPackage().getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<IAnnotation> getAnnotations()
    {
        final List result = Lists.newLinkedList();

        for (Annotation element : clazz.getDeclaredAnnotations())
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
        return clazz.getModifiers();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IClassType getSuperclass()
    {
        return (clazz.getSuperclass() == null)
                ? (IClassType) getTypeFactory().fromClass(Object.class)
                : (IClassType) getTypeFactory().fromClass(clazz.getSuperclass());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<IInterfaceType> getSuperinterfaces()
    {
        final List<IInterfaceType> result = Lists.newLinkedList();

        for (Class superinterface : clazz.getInterfaces())
        {
            result.add((IInterfaceType) getTypeFactory().fromClass(superinterface));
        }

        return ImmutableSet.copyOf(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<IField> getFields()
    {
        final List<IField> result = Lists.newLinkedList();

        for (Field field : clazz.getDeclaredFields())
        {
            result.add(new ReflectiveField(getTypeFactory(), field));
        }

        return ImmutableSet.copyOf(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<IConstructor> getConstructors()
    {
        final List<IConstructor> result = Lists.newLinkedList();

        for (Constructor ctor : clazz.getDeclaredConstructors())
        {
            result.add(new ReflectiveConstructor(getTypeFactory(), ctor));
        }

        return ImmutableSet.copyOf(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<IMethod> getMethods()
    {
        final List<IMethod> result = Lists.newLinkedList();


        for (Method method : clazz.getDeclaredMethods())
        {
            result.add(new ReflectiveMethod(getTypeFactory(), method));
        }

        return ImmutableSet.copyOf(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAnnotationType()
    {
        return clazz.isAnnotation();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isClassType()
    {
        final boolean not_an_array = clazz.isArray() == false;

        final boolean not_an_annotation = clazz.isAnnotation() == false;

        final boolean not_an_enum = clazz.isEnum() == false;

        final boolean not_an_interface = clazz.isInterface() == false;

        final boolean not_a_primitive = clazz.isPrimitive() == false;

        return not_an_array
               && not_an_annotation
               && not_an_enum
               && not_an_interface
               && not_a_primitive;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEnumType()
    {
        return clazz.isEnum();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isInterfaceType()
    {
        return clazz.isInterface();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class toClass()
    {
        return clazz;
    }
}

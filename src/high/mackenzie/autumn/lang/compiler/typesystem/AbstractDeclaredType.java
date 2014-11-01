package high.mackenzie.autumn.lang.compiler.typesystem;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IAnnotationMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IClassType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IDeclaredType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IEnumConstant;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IField;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IInterfaceType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.ITypeFactory;
import high.mackenzie.autumn.resources.Finished;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * This class provides a partial implementation of the IDeclaredType interface.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public abstract class AbstractDeclaredType
        extends AbstractType
        implements IDeclaredType
{
    /**
     * Sole Constructor.
     *
     * @param factory type-factory that is used to access types.
     * @param descriptor is the type-descriptor of the new type.
     */
    public AbstractDeclaredType(final ITypeFactory factory,
                                final String descriptor)
    {
        super(factory, descriptor);

        Preconditions.checkNotNull(factory);
        Preconditions.checkNotNull(descriptor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNamespace()
    {
        final String full_name = getDescriptor();

        final int last_slash = full_name.lastIndexOf('/');

        final String no_slashes = full_name.replace('/', '.');

        final String result = no_slashes.substring(1, last_slash);

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IClassType> getAllSuperclasses()
    {
        final List<IClassType> result = Lists.newLinkedList();

        IClassType p = this.getSuperclass();

        if (this.getDescriptor().equals("Ljava/lang/Object;"))
        {
            result.add((IClassType) getTypeFactory().fromClass(Object.class));
        }
        else
        {
            result.add(this.getSuperclass());
            result.addAll(this.getSuperclass().getAllSuperclasses());
        }

        return ImmutableList.copyOf(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<IInterfaceType> getAllSuperinterfaces()
    {
        final Set<IInterfaceType> result = Sets.newHashSet();

        final Set<IDeclaredType> visited = Sets.newHashSet();

        findSuperinterfaces(result, visited, this);

        return ImmutableSet.copyOf(result);
    }

    private void findSuperinterfaces(final Set<IInterfaceType> output,
                                     final Set<IDeclaredType> visited,
                                     final IDeclaredType target)
    {
        if (visited.contains(target))
        {
            return;
        }
        else
        {
            visited.add(target);
        }

        output.addAll(target.getSuperinterfaces());

        findSuperinterfaces(output, visited, target.getSuperclass());

        for (IInterfaceType direct_superinterface : target.getSuperinterfaces())
        {
            findSuperinterfaces(output, visited, direct_superinterface);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<IMethod> getAllVisibleMethods()
    {
        final List<IDeclaredType> supertypes = Lists.newLinkedList();
        supertypes.addAll(this.getAllSuperclasses());
        supertypes.addAll(this.getAllSuperinterfaces());

        final Set<IMethod> result = Sets.newHashSet();

        result.addAll(this.getMethods());

        for (IDeclaredType supertype : supertypes)
        {
            for (IMethod method : supertype.getMethods())
            {
                final int modifiers = method.getModifiers();

                if (!Modifier.isStatic(modifiers) && !Modifier.isPrivate(modifiers))
                {
                    result.add(method);
                }
            }
        }

        return Collections.unmodifiableCollection(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSubtypeOf(final IType target)
    {
        Preconditions.checkNotNull(target);

        if (this.equals(target))
        {
            return true; // Because, target is this.
        }
        else if (this.getAllSuperclasses().contains(target))
        {
            return true; // Because, target is a superclass of this.
        }
        else if (this.getAllSuperinterfaces().contains(target))
        {
            return true; // Because, target is a superinterfaces of this.
        }
        else
        {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    public Collection<IAnnotationMethod> getAnnotationMethods()
    {
        final List<IAnnotationMethod> result = Lists.newLinkedList();

        for (IMethod method : this.getMethods())
        {
            if (method.isAnnotationMethod())
            {
                result.add((IAnnotationMethod) method);
            }
        }

        return ImmutableList.copyOf(result);
    }

    /**
     * {@inheritDoc}
     */
    public Collection<IEnumConstant> getEnumConstants()
    {
        final List<IEnumConstant> result = Lists.newLinkedList();

        for (IField field : this.getFields())
        {
            if (field.isEnumConstant())
            {
                result.add((IEnumConstant) field);
            }
        }

        return ImmutableList.copyOf(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        return getDescriptor();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNullType()
    {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isVoidType()
    {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPrimitiveType()
    {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isReferenceType()
    {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isArrayType()
    {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDeclaredType()
    {
        return true;
    }
}

package high.mackenzie.autumn.lang.compiler.utils;

import autumn.lang.internals.annotations.Getter;
import autumn.lang.internals.annotations.Setter;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IDeclaredType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IInterfaceType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * An instance of this class analyzes a the type of a design.
 *
 * @author Mackenzie High
 */
public final class Design
{
    private final IInterfaceType design;

    /**
     * Sole Constructor.
     *
     * @param design is the type of the design.
     */
    public Design(final IInterfaceType design)
    {
        Preconditions.checkNotNull(design);

        this.design = design;
    }

    /**
     * This method retrieves the type being analyzed herein.
     *
     * @return the design.
     */
    public IInterfaceType type()
    {
        return design;
    }

    /**
     * This method detects both circular inheritance.
     *
     * @return true, if and only if the design experiences circular inheritance.
     */
    public boolean detectCircularInheritance()
    {
        return detectCircularInheritance(Sets.<IDeclaredType>newHashSet(), design);
    }

    private boolean detectCircularInheritance(final Set<IDeclaredType> types,
                                              final IDeclaredType type)
    {
        if (types.contains(type))
        {
            return true;
        }
        else
        {
            boolean result = detectCircularInheritance(types, type.getSuperclass());

            for (IDeclaredType superinterface : type.getAllSuperinterfaces())
            {
                result = result || detectCircularInheritance(types, superinterface);
            }

            return result;
        }
    }

    /**
     * This method returns the set of all the types that the design is a subtype of.
     * Remember, this includes the type itself.
     *
     * @return the aforesaid immutable set.
     */
    public Set<IDeclaredType> types()
    {
        final Set<IDeclaredType> result = Sets.newHashSet();

        result.add(design);

        result.addAll(supertypes());

        return ImmutableSet.copyOf(result);
    }

    /**
     * This method returns the set of all the types that the design is a proper subtype of.
     *
     * @return the aforesaid immutable set.
     */
    public Set<IDeclaredType> supertypes()
    {
        final Set<IDeclaredType> result = Sets.newHashSet();

        result.addAll(design.getAllSuperclasses());

        result.addAll(design.getAllSuperinterfaces());

        return ImmutableSet.copyOf(result);
    }

    /**
     * This method finds all the setters within the design.
     *
     * <p>
     * A key in the returned map is the name of the property.
     * A value in the returned map is a sorted list of setter overrides.
     * The sorted list is needed, due to covariant properties.
     * </p>
     *
     * @return the aforedescribed immutable map.
     */
    public Map<String, SetterList> setters()
    {
        final Map<String, List<IMethod>> methods = Maps.newHashMap();

        for (IMethod method : design.getAllVisibleMethods())
        {
            final String key = method.getName();

            final boolean is_setter = TypeSystemUtils.isAnnotationPresent(method, Setter.class);

            if (is_setter && (methods.containsKey(key) == false))
            {
                methods.put(key, Lists.<IMethod>newLinkedList());
            }

            if (is_setter)
            {
                methods.get(key).add(method);
            }
        }

        final Map<String, SetterList> result = Maps.newTreeMap();

        for (Entry<String, List<IMethod>> entry : methods.entrySet())
        {
            result.put(entry.getKey(), new SetterList(design, entry.getKey(), entry.getValue()));
        }

        return ImmutableMap.copyOf(result);
    }

    /**
     * This method finds all the getters within the design.
     *
     * <p>
     * A key in the returned map is the name of the property.
     * A value in the returned map is a sorted list of getter overrides.
     * The sorted list is needed, due to covariant properties.
     * </p>
     *
     * @return the aforedescribed immutable map.
     */
    public Map<String, GetterList> getters()
    {
        final Map<String, List<IMethod>> methods = Maps.newHashMap();

        for (IMethod method : design.getAllVisibleMethods())
        {
            final String key = method.getName();

            final boolean is_getter = TypeSystemUtils.isAnnotationPresent(method, Getter.class);

            if (is_getter && (methods.containsKey(key) == false))
            {
                methods.put(key, Lists.<IMethod>newLinkedList());
            }

            if (is_getter)
            {
                methods.get(key).add(method);
            }
        }

        final Map<String, GetterList> result = Maps.newTreeMap();

        for (Entry<String, List<IMethod>> entry : methods.entrySet())
        {
            result.put(entry.getKey(), new GetterList(design, entry.getKey(), entry.getValue()));
        }

        return ImmutableMap.copyOf(result);
    }

    /**
     * This method finds all the methods in the design, excluding the property setters and getters.
     *
     * <p>
     * A key in the returned map is the name plus parameter list descriptor of the method.
     * A value in the returned map is a sorted list of method overrides.
     * </p>
     *
     * @return the aforedescribed immutable map.
     */
    public Map<String, MethodList> methods()
    {
        final Map<String, List<IMethod>> methods = Maps.newHashMap();

        for (IMethod method : design.getAllVisibleMethods())
        {
            final String key = method.getNamePlusParameterListDescriptor();

            final boolean is_setter = TypeSystemUtils.isAnnotationPresent(method, Setter.class);

            final boolean is_getter = TypeSystemUtils.isAnnotationPresent(method, Getter.class);

            if (!is_setter && !is_getter && !methods.containsKey(key))
            {
                methods.put(key, Lists.<IMethod>newLinkedList());
            }

            if (is_setter || is_getter)
            {
                // Do Nothing, because the method is either a setter or a getter.
            }
            else
            {
                methods.get(key).add(method);
            }
        }

        final Map<String, MethodList> result = Maps.newTreeMap();

        for (Entry<String, List<IMethod>> entry : methods.entrySet())
        {
            result.put(entry.getKey(), new MethodList(design, entry.getKey(), entry.getValue()));
        }

        return ImmutableMap.copyOf(result);
    }

    /**
     * This method determines the type of value that a property accepts.
     *
     * @param name is the name of the property.
     * @return the aforesaid type, or null, if the property does not exist.
     */
    public IVariableType typeof(final String name)
    {
        final Map<String, GetterList> map = getters();

        if (map.containsKey(name))
        {
            return (IVariableType) map.get(name).list().get(0).getReturnType();
        }
        else
        {
            return null;
        }
    }

    /**
     * This method performs all necessary type-checking on a design.
     */
    public void reportErrors()
    {
    }

    private void detectAndReportErrorCircularInheritance()
    {
    }

    private void detectAndReportErrorDuplicateProperty()
    {
    }

    private void detectAndReportErrorDuplicateMethod()
    {
    }

    private void detectAndReportErrorSuperTypesMustBeInterfaces()
    {
    }

    private void detectAndReportErrorBadAnnotationList()
    {
    }
}

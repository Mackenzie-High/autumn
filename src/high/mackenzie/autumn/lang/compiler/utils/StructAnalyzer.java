package high.mackenzie.autumn.lang.compiler.utils;

import autumn.lang.internals.annotations.Getter;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IInterfaceType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * An instance of this class is used to analyze a struct-type.
 *
 * @author Mackenzie High
 */
public final class StructAnalyzer
{
    /**
     * This is the type of the struct.
     */
    public final IInterfaceType type;

    /**
     * This map maps the name of an element to objects that describe
     * the declarations of the element.
     */
    public final Map<String, StructElementList> elements;

    /**
     * Sole Constructor.
     *
     * @param type is the type of the struct.
     */
    public StructAnalyzer(final IInterfaceType type)
    {
        Preconditions.checkNotNull(type);

        this.type = type;

        this.elements = createMap(findElements());
    }

    /**
     * This method finds all the elements in the struct.
     *
     * @return a map that maps the name of an element to objects
     * that represent the declarations of the elements.
     */
    private Map<String, Set<StructElement>> findElements()
    {
        final Map<String, Set<StructElement>> map = Maps.newTreeMap();

        for (IMethod method : type.getAllVisibleMethods())
        {
            add(map, method);
        }

        return map;
    }

    /**
     * This method adds an object representation of an element to a map,
     * if the type-system representation of a method is a getter.
     *
     * @param map is the map to add an entry to.
     * @param method is possibly a getter.
     */
    private void add(final Map<String, Set<StructElement>> map,
                     final IMethod method)
    {
        /**
         * If the method takes parameters, then it is not a getter.
         */
        if (method.getFormalParameters().isEmpty() == false)
        {
            return;
        }

        /**
         * A getter has a special annotation applied to it.
         */
        if (TypeSystemUtils.isAnnotationPresent(method, Getter.class) == false)
        {
            return;
        }

        /**
         * Create an object that represents the declaration of the element.
         */
        final String name = method.getName();
        final IVariableType value = (IVariableType) method.getReturnType();
        final StructElement element = new StructElement(type, name, value);

        /**
         * Create a map entry for elements with the given name.
         */
        if (map.containsKey(name) == false)
        {
            map.put(name, Sets.<StructElement>newHashSet());
        }

        /**
         * Add the declaration to the map.
         */
        map.get(name).add(element);
    }

    /**
     * This method creates a map that maps the name of an element to its overrides.
     *
     * @param map is the basis for the new map.
     * @return an immutable map as described above.
     */
    private Map<String, StructElementList> createMap(final Map<String, Set<StructElement>> map)
    {
        final Map<String, StructElementList> result = Maps.newTreeMap();

        for (Entry<String, Set<StructElement>> entry : map.entrySet())
        {
            final String name = entry.getKey();

            final Set<StructElement> unsorted = entry.getValue();

            final StructElementList sorted = new StructElementList(name, unsorted);

            result.put(name, sorted);
        }

        return Collections.unmodifiableMap(result);
    }
}

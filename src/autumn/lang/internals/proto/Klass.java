package autumn.lang.internals.proto;

import autumn.lang.Member;
import autumn.lang.Prototype;
import com.google.common.collect.Lists;
import java.util.List;

/**
 * An instance of this class describes the internal state of a design-based object.
 *
 * @author Mackenzie High
 */
public final class Klass
{
    private static class Property
    {
        public String name;

        public Class type;
    }

    private final List<Property> properties = Lists.newLinkedList();

    /**
     * This method declares a new property.
     *
     * @param name is the name of the property.
     * @param type is the type of value stored in the property.
     * @return the index of the new property.
     */
    public int newProperty(final String name,
                           final Class type)
    {
        final int index = properties.size();

        return index;
    }

    /**
     * This method declares a new method.
     *
     * @param name is the name of the method.
     * @param params ares the types of the method's parameters.
     * @param returns is the return-type of the method.
     * @return the index of the new method.
     */
    public int newMethod(final String name,
                         final Iterable<Class> params,
                         final Class returns)
    {
        return 0;
    }

    public Member[] instantiate(final Prototype owner)
    {
        return null;
    }
}

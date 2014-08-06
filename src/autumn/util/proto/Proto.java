package autumn.util.proto;

import autumn.lang.Member;
import autumn.lang.Method;
import autumn.lang.Property;
import autumn.lang.Prototype;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMember;
import java.util.List;
import org.objectweb.asm.Type;

/**
 * This class provides static utility methods for working with prototype based objects.
 *
 * @author Mackenzie High
 */
public final class Proto
{
    public static Prototype derive(final Prototype x,
                                   final Prototype y)
    {
        return null;
    }

    public static Prototype derivePreferX(final Prototype x,
                                          final Prototype y)
    {
        return null;
    }

    public static Prototype derivePreferY(final Prototype x,
                                          final Prototype y)
    {
        return null;
    }

    /**
     * This method creates a list of the properties in a prototype.
     *
     * @param p is the prototype that contains the properties.
     * @return an immutable list containing the properties in the given prototype.
     */
    public static List<Property> propertiesOf(final Prototype p)
    {
        final List<Property> list = Lists.newLinkedList();

        for (Member member : p.members())
        {
            if (member.isProperty())
            {
                list.add((Property) member);
            }
        }

        return ImmutableList.copyOf(list);
    }

    /**
     * This method creates a list of the properties in a prototype.
     *
     * @param p is the prototype that contains the properties.
     * @return an immutable list containing the properties in the given prototype.
     */
    public static List<Method> methodsOf(final Prototype p)
    {
        final List<Method> list = Lists.newLinkedList();

        for (Member member : p.members())
        {
            if (member.isMethod())
            {
                list.add((Method) member);
            }
        }

        return ImmutableList.copyOf(list);
    }

    public static Prototype clearAll(final Prototype p)
    {
        Prototype result = p;

        for (Property prop : propertiesOf(p))
        {
            result = prop.clearValue().owner();
        }

        return result;
    }

    public static boolean isComplete(final Prototype p)
    {
        for (Method method : methodsOf(p))
        {
            if (method.getHandler() == null)
            {
                return false;
            }
        }

        return true;
    }

    public static IMember property(final Prototype owner,
                                   final String name)
    {
        return null;
    }

    public static IMember method(final Prototype owner,
                                 final String name,
                                 final Iterable<Class> parameters)
    {
        return null;
    }

    public static String descriptorOf(final Method method)
    {
        final String part1 = parameterListDescriptorOf(method);

        final String part2 = Type.getDescriptor(method.returnType());

        return part1 + part2;
    }

    public static String parameterListDescriptorOf(final Method method)
    {
        final StringBuilder descriptor = new StringBuilder();

        descriptor.append('(');
        {
            for (Class param : method.parameterTypes())
            {
                descriptor.append(Type.getDescriptor(param));
            }
        }
        descriptor.append(')');

        return descriptor.toString();
    }

    public static String namePlusDescriptorOf(final Method method)
    {
        return method.name() + descriptorOf(method);
    }

    public static String namePlusParameterListDescriptorOf(final Method method)
    {
        return method.name() + parameterListDescriptorOf(method);
    }
}

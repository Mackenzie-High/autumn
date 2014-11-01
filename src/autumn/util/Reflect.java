package autumn.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import high.mackenzie.autumn.lang.compiler.typesystem.TypeFactory;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.ITypeFactory;
import high.mackenzie.autumn.lang.compiler.utils.TypeSystemUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import org.objectweb.asm.Type;

/**
 * This class provides static utility methods for working with reflection.
 *
 * @author Mackenzie High
 */
public final class Reflect
{
    /**
     * This method searches for a field in a class object.
     *
     * @param owner is the class object to search.
     * @param name is the name of the field to search for.
     * @return the found field.
     * @throws NoSuchFieldException if the method could not be found.
     */
    public static Field getField(final Class owner,
                                 final String name)
            throws NoSuchFieldException
    {
        Preconditions.checkNotNull(owner);
        Preconditions.checkNotNull(name);

        return owner.getField(name);
    }

    /**
     * This method searches for a constructor in a class object.
     *
     * @param owner is the class object to search.
     * @param parameters are the types of the constructor's formal parameters.
     * @return the found constructor.
     * @throws NoSuchMethodException if the constructor could not be found.
     */
    public static Constructor getConstructor(final Class owner,
                                             final Iterable<Class> parameters)
            throws NoSuchMethodException
    {
        Preconditions.checkNotNull(owner);
        Preconditions.checkNotNull(parameters);

        final List<Class> list = Lists.newArrayList(parameters);

        final Class[] array = list.toArray(new Class[0]);

        final Constructor ctor = owner.getConstructor(array);

        return ctor;
    }

    /**
     * This method searches for a method in a class object.
     *
     * @param owner is the class object to search.
     * @param name is the name of the method to search for.
     * @param parameters are the types of the method's formal parameters.
     * @return the found method.
     * @throws NoSuchMethodException if the method could not be found.
     */
    public static Method getMethod(final Class owner,
                                   final String name,
                                   final Iterable<Class> parameters)
            throws NoSuchMethodException
    {
        Preconditions.checkNotNull(owner);
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(parameters);

        return owner.getMethod(name, Lists.newArrayList(parameters).toArray(new Class[0]));
    }

    /**
     * This method computes the descriptor of a given type.
     *
     * @param klass is the class object that represents the type.
     * @return the descriptor of the type.
     */
    public static String descriptorOf(final Class klass)
    {
        Preconditions.checkNotNull(klass);

        return Type.getDescriptor(klass);
    }

    /**
     * This method computes the descriptor of a constructor.
     *
     * @param ctor is the constructor whose descriptor will be computed.
     * @return the descriptor of the given constructor.
     */
    public static String descriptorOf(final Constructor ctor)
    {
        Preconditions.checkNotNull(ctor);

        return Type.getConstructorDescriptor(ctor);
    }

    /**
     * This method computes the descriptor of a method.
     *
     * @param method is the method whose descriptor will be computed.
     * @return the descriptor of the given method.
     */
    public static String descriptorOf(final Method method)
    {
        Preconditions.checkNotNull(method);

        return Type.getMethodDescriptor(method);
    }

    /**
     * This method determines whether one type is a subtype of another.
     *
     * @param subtype is a possible subtype of the supertype.
     * @param supertype is a possible supertype of the subtype.
     * @return true, iff subtype is really a subtype of the supertype.
     */
    public static boolean isSubtypeOf(final Class subtype,
                                      final Class supertype)
    {
        Preconditions.checkNotNull(subtype);
        Preconditions.checkNotNull(supertype);

        final ITypeFactory factory = new TypeFactory();

        final TypeSystemUtils utils = new TypeSystemUtils(factory);

        final IType input = factory.fromClass(subtype);

        final boolean result = utils.isSubtypeOf(input, supertype);

        return result;
    }

    /**
     * This method determines whether one type is assignable to another type.
     *
     * <p>
     * This method makes its determination based on the rules of the Autumn programming language.
     * Remember, Autumn's rules differ from Java's rules.
     * </p>
     *
     * @param value is the type that may be assignable-to the target type.
     * @param type is the type that the value type may be assignable to.
     * @return true, iff the value type is assignable-to the target type.
     */
    public static boolean isAssignableTo(final Class value,
                                         final Class target)
    {
        final ITypeFactory factory = new TypeFactory();

        final TypeSystemUtils utils = new TypeSystemUtils(factory);

        final IType input = factory.fromClass(value);

        final IType output = factory.fromClass(target);

        final boolean result = utils.assign(input, output) != null;

        return result;
    }
}

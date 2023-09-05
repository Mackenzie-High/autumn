package execution;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.objectweb.asm.Type;

/**
 * An instance of this class verifies that a class declares
 * required fields, constructors, and methods.
 *
 * @author Mackenzie High
 */
public final class ReflectionTester
{
    /**
     * This set stores the keys of the expected members.
     */
    private final Set<String> expected = Sets.newTreeSet();

    /**
     * This set stores the keys of members after they are checked.
     */
    private final Set<String> checked = Sets.newTreeSet();

    /**
     * This map maps keys to sets of annotation types.
     */
    private final Map<String, Set<Class>> expected_annotations = Maps.newTreeMap();

    /**
     * This map maps keys to types.
     */
    private final Map<String, Class> expected_types = Maps.newTreeMap();

    /**
     * This map maps keys to lists of formal parameter types.
     */
    private final Map<String, List<Class>> expected_formals = Maps.newTreeMap();

    /**
     * This method declares that a particular field should be expected.
     *
     * @param annotations are the types of the some of the field's annotations.
     * @param name is the name of the field.
     * @param type is the type of value stored in the field.
     */
    public void expectField(final Collection<Class> annotations,
                            final String name,
                            final Class type)
    {
        expected.add(name);
        expected_annotations.put(name, Sets.newHashSet(annotations));
        expected_types.put(name, type);
    }

    /**
     * This method declares that a particular constructor should be expected.
     *
     * @param annotations are the types of the some of the constructor's annotations.
     * @param formals are the types of the types of the constructor's formal-parameters.
     */
    public void expectCtor(final Collection<Class> annotations,
                           final Collection<Class> formals)
    {
        final String key = "<init>" + descriptorOf(formals, void.class);

        expected.add(key);
        expected_annotations.put(key, Sets.newHashSet(annotations));
        expected_formals.put(key, Lists.newLinkedList(formals));
    }

    /**
     * This method declares that a particular method should be expected.
     *
     * @param annotations are the types of the some of the method's annotations.
     * @param name is the name of the method.
     * @param formals are the types of the types of the method's formal-parameters.
     */
    public void expectMethod(final Collection<Class> annotations,
                             final String name,
                             final Collection<Class> formals,
                             final Class returns)
    {
        final String key = name + descriptorOf(formals, returns);

        expected.add(key);
        expected_annotations.put(key, Sets.newHashSet(annotations));
        expected_formals.put(key, Lists.newLinkedList(formals));
        expected_types.put(key, returns);
    }

    private String descriptorOf(final Collection<Class> formals,
                                final Class returns)
    {
        final StringBuilder descriptor = new StringBuilder();

        descriptor.append('(');

        for (Class formal : formals)
        {
            descriptor.append(Type.getDescriptor(formal));
        }

        descriptor.append(')');

        descriptor.append(Type.getDescriptor(returns));

        return descriptor.toString();
    }

    /**
     * This method ensures that the expected fields, constructors, and methods
     * are actually found in a given class.
     *
     * @param owner is the class that must contain the expected members.
     */
    public void check(final Class owner)
    {
        for (Field x : owner.getDeclaredFields())
        {
            check(x);
        }

        for (Constructor x : owner.getDeclaredConstructors())
        {
            check(x);
        }

        for (Method x : owner.getDeclaredMethods())
        {
            check(x);
        }

        /**
         * Make sure that all the expected members were present.
         */
        for (String key : expected)
        {
            Preconditions.checkState(checked.contains(key), "Missed Member: " + key);
        }
    }

    /**
     * This method verifies that each expected annotation is actually present.
     *
     * @param expected are the types of the expected annotations.
     * @param actual are the actual annotations.
     */
    private void check(final Collection<Class> expected,
                       final Annotation[] actual)
    {
        final Set<Class> applied = Sets.newHashSet();

        for (Annotation anno : actual)
        {
            applied.add(anno.annotationType());
        }

        /**
         * If an annotation of type T is expected,
         * then an annotation of type T should be applied.
         */
        for (Class T : expected)
        {
            Preconditions.checkArgument(applied.contains(T));
        }
    }

    /**
     * This method verifies that the compiled version of a field was expected.
     *
     * @param field is the field to check.
     */
    private void check(final Field field)
    {
        final String key = field.getName();

        if (expected.contains(key))
        {
            // Check the annotations applied to the field.
            check(expected_annotations.get(key), field.getAnnotations());

            // Check the type of value stored in the field.
            Preconditions.checkArgument(expected_types.get(key).equals(field.getType()));

            // Record the fact that the member was checked.
            checked.add(key);
        }
    }

    /**
     * This method verifies that the compiled version of a constructor was expected.
     *
     * @param ctor is the constructor to check.
     */
    private void check(final Constructor ctor)
    {
        final List<Class> params = Lists.newArrayList(ctor.getParameterTypes());

        final String key = "<init>" + descriptorOf(params, void.class);

        if (expected.contains(key))
        {
            // Check the annotations applied to the ctor.
            check(expected_annotations.get(key), ctor.getAnnotations());

            // Check the types of the ctor's parameters.
            Preconditions.checkArgument(expected_formals.get(key).equals(params));

            // Record the fact that the member was checked.
            checked.add(key);
        }
    }

    /**
     * This method verifies that the compiled version of a method was expected.
     *
     * @param method is the method to check.
     */
    private void check(final Method method)
    {
        final List params = Lists.newArrayList(method.getParameterTypes());

        final String key = method.getName() + descriptorOf(params, method.getReturnType());

        if (expected.contains(key))
        {
            // Check the annotations applied to the method.
            check(expected_annotations.get(key), method.getAnnotations());

            // Check the types of the method's parameters.
            Preconditions.checkArgument(expected_formals.get(key).equals(params));

            // Check the method's return-type.
            Preconditions.checkArgument(expected_types.get(key).equals(method.getReturnType()));

            // Record the fact that the member was checked.
            checked.add(key);
        }
    }
}

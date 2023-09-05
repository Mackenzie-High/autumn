package com.mackenziehigh.autumn.compiler.typesystem;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.reflect.Reflection;
import com.mackenziehigh.autumn.lang.compiler.typesystem.ReflectiveConstructor;
import com.mackenziehigh.autumn.lang.compiler.typesystem.ReflectiveField;
import com.mackenziehigh.autumn.lang.compiler.typesystem.ReflectiveMethod;
import com.mackenziehigh.autumn.lang.compiler.typesystem.TypeFactory;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IAnnotatable;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IAnnotation;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IConstructor;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IDeclaredType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IField;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IFormalParameter;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IMethod;
import com.mackenziehigh.snowflake.Utils;
import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.*;
import org.junit.Test;
import org.objectweb.asm.Type;

public class ReflectiveDeclaredTypeTest
{

    private int class_count = 0;

    private int field_count = 0;

    private int ctor_count = 0;

    private int method_count = 0;

    private int annotation_count = 0;

    private int parameter_count = 0;

    /**
     * Test: 20130722093607208742
     *
     * <p>
     * Case: Test Everything
     * </p>
     */
    @Test
    public void test20130722093607208742 ()
            throws Exception
    {
        System.out.println("Test: 20130722093607208742");

        final File resource = new File("src/test/resources/autumn/lang/compiler/List of rt.jar Classes.dat");

        final List<String> lines = Files.readAllLines(resource.toPath());

        for (String line : lines)
        {
            if (line.trim().startsWith("java.") || line.trim().startsWith("javax."))
            {
                try
                {
                    final Class clazz = Class.forName(line.trim());

                    checkDeclaredType(clazz);
                }
                catch (ClassNotFoundException ex)
                {
                    // Skip the class.
                }
            }
        }

        assertTrue(class_count > 1000);
        assertTrue(field_count > 1000);
        assertTrue(ctor_count > 1000);
        assertTrue(method_count > 1000);
        assertTrue(annotation_count > 250);
        assertTrue(parameter_count > 1000);

        System.out.println("------------------------------------------------------------");
        System.out.println("Class Count: " + class_count);
        System.out.println("Field Count: " + field_count);
        System.out.println("Constructor Count: " + ctor_count);
        System.out.println("Method Count: " + method_count);
        System.out.println("Annotation Count: " + annotation_count);
        System.out.println("Parameter Count: " + parameter_count);
        System.out.println("------------------------------------------------------------");

    }

    private void checkDeclaredType (final Class clazz)
    {
        Utils.checkNonNull(clazz);

        ++class_count;

        final TypeFactory factory = new TypeFactory();

        final IDeclaredType type = (IDeclaredType) factory.fromClass(clazz);

        // Check Namespace
        assertEquals(Reflection.getPackageName(clazz), type.getNamespace());

        // Check Annotations
        checkAnnotations(clazz.getDeclaredAnnotations(), type);

        // Check Modifiers
        assertEquals(clazz.getModifiers(), type.getModifiers());

        // Check Descriptor
        assertEquals(Type.getDescriptor(clazz), type.getDescriptor());

        // Check Superclass
        final Class superclass = clazz.getSuperclass() == null
                                 ? Object.class
                                 : clazz.getSuperclass();

        assertEquals(Type.getDescriptor(superclass),
                     type.getSuperclass().getDescriptor());

        // Check Fields
        for (IField field : type.getFields())
        {
            checkField((ReflectiveField) field);
        }

        // Check Constructors
        for (IConstructor ctor : type.getConstructors())
        {
            checkConstructor((ReflectiveConstructor) ctor);
        }

        // Check Methods
        for (IMethod method : type.getMethods())
        {
            checkMethod((ReflectiveMethod) method);
        }

        // Check Type Identification Functions
        assertEquals(clazz.isAnnotation(), type.isAnnotationType());
        assertEquals(clazz.isEnum(), type.isEnumType());
        assertEquals(clazz.isInterface(), type.isInterfaceType());

        // Check Other Methods
        assertTrue(type.isAlreadyCompiled());
    }

    private void checkAnnotations (final Annotation[] declared_annotations,
                                   final IAnnotatable annotated)
    {
        final Collection<Annotation> annotations = Lists.newLinkedList();

        for (IAnnotation anno : annotated.getAnnotations())
        {
            ++annotation_count;

            annotations.add(anno.toAnnotation());
        }

        assertEquals(ImmutableList.copyOf(declared_annotations), annotations);
    }

    private void checkField (final ReflectiveField field)
    {
        ++field_count;

        final Field reflective = field.getField();

        // Check Annotations
        checkAnnotations(reflective.getDeclaredAnnotations(), field);

        // Check Owner
        assertEquals(Type.getDescriptor(reflective.getDeclaringClass()),
                     field.getOwner().getDescriptor());

        // Check Modifiers
        assertEquals(reflective.getModifiers(), field.getModifiers());

        // Check Name
        assertEquals(reflective.getName(), field.getName());

        // Check Type
        assertEquals(Type.getDescriptor(reflective.getType()),
                     field.getType().getDescriptor());

        // Check Special Methods
        assertEquals(reflective.isEnumConstant(), field.isEnumConstant());
    }

    private void checkConstructor (final ReflectiveConstructor ctor)
    {
        ++ctor_count;

        final Constructor reflective = ctor.getConstructor();

        // Check Owner
        assertEquals(Type.getDescriptor(reflective.getDeclaringClass()),
                     ctor.getOwner().getDescriptor());

        // Check Annotations
        checkAnnotations(reflective.getDeclaredAnnotations(), ctor);

        // Check Modifiers
        assertEquals(reflective.getModifiers(), ctor.getModifiers());

        // Check Name
        assertEquals("<init>", ctor.getName());

        // Check Parameters
        for (int i = 0; i < reflective.getParameterTypes().length; i++)
        {
            checkFormalParameter(reflective.getParameterAnnotations()[i],
                                 reflective.getParameterTypes()[i],
                                 ctor.getParameters().get(i));
        }

        // Check Return-Type
        assertEquals("V", ctor.getReturnType().getDescriptor());

        // Check Throws-Clause
        final Set<Class> clause = ImmutableSet.copyOf(reflective.getExceptionTypes());
        for (IDeclaredType exception : ctor.getThrowsClause())
        {
            clause.contains(exception.toClass());
        }

        // Check Descriptor Methods
        final String descriptor = Type.getConstructorDescriptor(reflective);
        assertEquals(descriptor, ctor.getDescriptor());
        assertEquals("<init>" + ctor.getDescriptor(), ctor.getNamePlusDescriptor());
    }

    private void checkMethod (final ReflectiveMethod method)
    {
        ++method_count;

        final Method reflective = method.getMethod();

        // Check Owner
        assertEquals(Type.getDescriptor(reflective.getDeclaringClass()),
                     method.getOwner().getDescriptor());

        // Check Annotations
        checkAnnotations(reflective.getDeclaredAnnotations(), method);

        // Check Modifiers
        assertEquals(reflective.getModifiers(), method.getModifiers());

        // Check Name
        assertEquals(reflective.getName(), method.getName());

        // Check Parameters
        for (int i = 0; i < reflective.getParameterTypes().length; i++)
        {
            checkFormalParameter(reflective.getParameterAnnotations()[i],
                                 reflective.getParameterTypes()[i],
                                 method.getParameters().get(i));
        }

        // Check Return-Type
        assertEquals(Type.getDescriptor(reflective.getReturnType()),
                     method.getReturnType().getDescriptor());

        // Check Throws-Clause
        final Set<Class<?>> clause = ImmutableSet.copyOf(reflective.getExceptionTypes());
        for (IDeclaredType exception : method.getThrowsClause())
        {
            clause.contains(exception.toClass());
        }

        // Check Special Methods
        assertEquals(reflective.getDeclaringClass().isAnnotation(),
                     method.isAnnotationMethod());

        // Check Descriptor Methods
        final String name = reflective.getName();
        final String descriptor = Type.getMethodDescriptor(reflective);
        assertEquals(descriptor, method.getDescriptor());
        assertEquals(name + descriptor, method.getNamePlusDescriptor());
    }

    private void checkFormalParameter (final Annotation[] annotations,
                                       final Class type,
                                       final IFormalParameter parameter)
    {
        ++parameter_count;

        // Check Annotations
        checkAnnotations(annotations, parameter);

        // Check Type
        assertEquals(Type.getDescriptor(type), parameter.getType().getDescriptor());
    }
}

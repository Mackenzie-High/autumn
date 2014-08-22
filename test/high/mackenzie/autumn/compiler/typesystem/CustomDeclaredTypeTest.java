package high.mackenzie.autumn.compiler.typesystem;

import high.mackenzie.autumn.lang.compiler.typesystem.TypeFactory;
import high.mackenzie.autumn.lang.compiler.typesystem.CustomDeclaredType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IClassType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IDeclaredType;
import com.google.common.collect.Lists;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class CustomDeclaredTypeTest
{
    /**
     * Test: 20130724071546548983
     *
     * <p>
     * Case: Setter and Getter Throughput
     * </p>
     */
    @Test
    public void test20130724071546548983()
    {
        // Note: This method avoids the use of generics in order to simplify the tests.

        System.out.println("Test: 20130724071546548983");

        final TypeFactory factory = new TypeFactory();

        final IDeclaredType string = (IDeclaredType) factory.fromClass(String.class);


        final CustomDeclaredType clazz = factory.newClassType("Lhigh/mackenzie/MyClass;");

        final List annotations = Lists.newArrayList("A1", "A2", "A3");
        final int modifiers = 1234567;
        final IClassType superclass = (IClassType) string;
        final List superinterfaces = Lists.newArrayList("A", "B", "C");
        final List fields = Lists.newArrayList("F1", "F2", "F3");
        final List ctors = Lists.newArrayList("C1", "C2", "C3");
        final List methods = Lists.newArrayList("M1", "M2", "M3");

        clazz.setAnnotations(annotations);
        clazz.setModifiers(modifiers);
        clazz.setSuperclass(superclass);
        clazz.setSuperinterfaces(superinterfaces);
        clazz.setFields(fields);
        clazz.setConstructors(ctors);
        clazz.setMethods(methods);

        assertFalse(clazz.isAlreadyCompiled());

        assertEquals(annotations, clazz.getAnnotations());
        assertFalse(annotations == clazz.getAnnotations());

        assertEquals(modifiers, clazz.getModifiers());

        assertEquals(superinterfaces, clazz.getSuperinterfaces());
        assertFalse(superinterfaces == clazz.getSuperinterfaces());

        assertEquals(fields, clazz.getFields());
        assertFalse(fields == clazz.getFields());

        assertEquals(ctors, clazz.getConstructors());
        assertFalse(ctors == clazz.getConstructors());

        assertEquals(methods, clazz.getMethods());
        assertFalse(methods == clazz.getMethods());
    }

    /**
     * Test: 20130724101256532419
     *
     * <p>
     * Case: Construction Methods, etc
     * </p>
     */
    @Test
    public void test20130724101256532419()
    {
        System.out.println("Test: 20130724101256532419");

        final TypeFactory factory = new TypeFactory();

        IDeclaredType t;

        t = factory.newAnnotationType("LAoo;");
        assertTrue(t == factory.findType("LAoo;"));
        assertTrue(t.isAnnotationType());

        t = factory.newClassType("LBoo;");
        assertTrue(t == factory.findType("LBoo;"));
        assertTrue(t.isClassType());

        t = factory.newEnumType("LCoo;");
        assertTrue(t == factory.findType("LCoo;"));
        assertTrue(t.isEnumType());

        t = factory.newInterfaceType("LDoo;");
        assertTrue(t == factory.findType("LDoo;"));
        assertTrue(t.isInterfaceType());
    }
}

package high.mackenzie.autumn.lang.compiler.utils;

import autumn.lang.internals.annotations.Getter;
import autumn.lang.internals.annotations.Setter;
import high.mackenzie.autumn.lang.compiler.typesystem.TypeFactory;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IDeclaredType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IInterfaceType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.junit.Assert.*;
import org.junit.Test;

public final class DesignTest
{
    static interface Interface1
    {
        @Setter
        public Interface1 property1(List value);

        @Getter
        public List property1();

        public Object method1(int arg1);

        public Object method1(float arg1);
    }

    static interface Interface2
    {
    }

    static interface Interface3
    {
    }

    static interface Interface4
    {
    }

    static interface Interface5
            extends Interface1,
                    Interface2
    {
        @Setter
        public Interface5 property1(LinkedList value);

        @Getter
        @Override
        public LinkedList property1();

        @Override
        public String method1(int arg1);

        @Override
        public List method1(float arg1);
    }

    static interface Interface6
            extends Interface3,
                    Interface4
    {
    }

    static interface Interface7
            extends Interface5,
                    Interface6
    {
    }

    private final TypeFactory factory = new TypeFactory();

    private final IInterfaceType INTERFACE_1 = (IInterfaceType) factory.fromClass(Interface1.class);

    private final IInterfaceType INTERFACE_2 = (IInterfaceType) factory.fromClass(Interface2.class);

    private final IInterfaceType INTERFACE_3 = (IInterfaceType) factory.fromClass(Interface3.class);

    private final IInterfaceType INTERFACE_4 = (IInterfaceType) factory.fromClass(Interface4.class);

    private final IInterfaceType INTERFACE_5 = (IInterfaceType) factory.fromClass(Interface5.class);

    private final IInterfaceType INTERFACE_6 = (IInterfaceType) factory.fromClass(Interface6.class);

    private final IInterfaceType INTERFACE_7 = (IInterfaceType) factory.fromClass(Interface7.class);

    /**
     * Test: 20140220024517283727
     *
     * <p>
     * Method:
     * <code>types()</code>
     * </p>
     */
    @Test
    public void test20140220024517283727()
    {
        System.out.println("Test: 20140220024517283727");

        final Design design = new Design(INTERFACE_7);

        final Set<IDeclaredType> types = design.types();

        assertTrue(types.contains(INTERFACE_1));
        assertTrue(types.contains(INTERFACE_2));
        assertTrue(types.contains(INTERFACE_3));
        assertTrue(types.contains(INTERFACE_4));
        assertTrue(types.contains(INTERFACE_5));
        assertTrue(types.contains(INTERFACE_6));
        assertTrue(types.contains(INTERFACE_7));
        assertTrue(types.contains(factory.fromClass(Object.class)));
    }

    /**
     * Test: 20140220024517283846
     *
     * <p>
     * Method:
     * <code>supertypes()</code>
     * </p>
     */
    @Test
    public void test20140220024517283846()
    {
        System.out.println("Test: 20140220024517283846");

        final Design design = new Design(INTERFACE_7);

        final Set<IDeclaredType> types = design.supertypes();

        assertTrue(types.contains(INTERFACE_1));
        assertTrue(types.contains(INTERFACE_2));
        assertTrue(types.contains(INTERFACE_3));
        assertTrue(types.contains(INTERFACE_4));
        assertTrue(types.contains(INTERFACE_5));
        assertTrue(types.contains(INTERFACE_6));
        assertTrue(types.contains(factory.fromClass(Object.class)));

        assertFalse(types.contains(INTERFACE_7));
    }

    /**
     * Test: 20140220024517283896
     *
     * <p>
     * Method:
     * <code>setters</code>
     * </p>
     */
    @Test
    public void test20140220024517283896()
    {
        System.out.println("Test: 20140220024517283896");

        final Design design = new Design(INTERFACE_7);

        final Map<String, SetterList> setters = design.setters();

        assertEquals(1, setters.size());

        SetterList list;
        IMethod setter;

        list = setters.get("property1");
        setter = list.list().get(0);
        assertEquals("property1", setter.getName());
        assertEquals("(Ljava/util/LinkedList;)", setter.getParameterListDescriptor());

        list = setters.get("property1");
        setter = list.list().get(1);
        assertEquals("property1", setter.getName());
        assertEquals("(Ljava/util/List;)", setter.getParameterListDescriptor());

        for (SetterList values : setters.values())
        {
            for (IMethod value : values.list())
            {
                assertFalse(TypeSystemUtils.isAnnotationPresent(value, Getter.class));
                assertTrue(TypeSystemUtils.isAnnotationPresent(value, Setter.class));
            }
        }
    }

    /**
     * Test: 20140220024517283938
     *
     * <p>
     * Method:
     * <code>getters</code>
     * </p>
     */
    @Test
    public void test20140220024517283938()
    {
        System.out.println("Test: 20140220024517283938");

        final Design design = new Design(INTERFACE_7);

        final Map<String, GetterList> getters = design.getters();

        assertEquals(1, getters.size());

        GetterList list;
        IMethod getter;

        list = getters.get("property1");
        getter = list.list().get(0);
        assertEquals("property1", getter.getName());
        assertEquals("()Ljava/util/LinkedList;", getter.getDescriptor());

        list = getters.get("property1");
        getter = list.list().get(1);
        assertEquals("property1", getter.getName());
        assertEquals("()Ljava/util/List;", getter.getDescriptor());

        for (GetterList values : getters.values())
        {
            for (IMethod value : values.list())
            {
                assertTrue(TypeSystemUtils.isAnnotationPresent(value, Getter.class));
                assertFalse(TypeSystemUtils.isAnnotationPresent(value, Setter.class));
            }
        }
    }

    /**
     * Test: 20140220024517283975
     *
     * <p>
     * Method:
     * <code>methods</code>
     * </p>
     */
    @Test
    public void test20140220024517283975()
    {
        System.out.println("Test: 20140220024517283975");

        final Design design = new Design(INTERFACE_7);

        final Map<String, MethodList> methods = design.methods();

        MethodList list;
        IMethod method;

        list = methods.get("method1(I)");
        method = list.list().get(0);
        assertEquals("method1", method.getName());
        assertEquals("(I)Ljava/lang/String;", method.getDescriptor());

        list = methods.get("method1(I)");
        method = list.list().get(1);
        assertEquals("method1", method.getName());
        assertEquals("(I)Ljava/lang/Object;", method.getDescriptor());

        list = methods.get("method1(F)");
        method = list.list().get(0);
        assertEquals("method1", method.getName());
        assertEquals("(F)Ljava/util/List;", method.getDescriptor());

        list = methods.get("method1(F)");
        method = list.list().get(1);
        assertEquals("method1", method.getName());
        assertEquals("(F)Ljava/lang/Object;", method.getDescriptor());

        for (MethodList values : methods.values())
        {
            for (IMethod value : values.list())
            {
                assertFalse(TypeSystemUtils.isAnnotationPresent(value, Getter.class));
                assertFalse(TypeSystemUtils.isAnnotationPresent(value, Setter.class));
            }
        }
    }
}

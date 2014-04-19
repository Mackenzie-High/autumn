/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.compiler.utils;

import high.mackenzie.autumn.lang.compiler.utils.TypeSystemUtils;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IInterfaceType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IInvokableMember;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.ITypeFactory;
import high.mackenzie.autumn.lang.compiler.typesystem.TypeFactory;
import java.util.Collection;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;

/**
 *
 * @author mackenzie
 */
public class TypeSystemUtilsTest
{
    private static interface TestInterface1
    {
        public String method4(int x);

        public void method5();
    }

    private static interface TestInterface2
    {
        public Object method4(int x);
    }

    private static interface TestInterface3
            extends TestInterface1,
                    TestInterface2
    {
        public void method1(int x);

        public void method1(int x,
                            int y);

        public void method2(int x);

        public void method3(int x);

        public void method3(String x);

        public void method3(Integer x);

        public void method3(Number x);

        public void method3(Object x);

        @Override
        public void method5();
    }

    /**
     * Test: 20130821005217107897
     *
     * <p>
     * Method:
     * <code>assign(IType, IType)</code>
     * </p>
     *
     * <p>
     * Case: Test Everything
     * </p>
     */
    @Test
    public void test20130821005217107897()
    {
        System.out.println("Test: 20130821005217107897");

        final ITypeFactory factory = new TypeFactory();

        final TypeSystemUtils utils = new TypeSystemUtils(factory);

        List<AbstractInsnNode> code;

        // Case #1: Identity

        // Pimitive Identity
        code = utils.assign(utils.PRIMITIVE_BOOLEAN, utils.PRIMITIVE_BOOLEAN);
        assertFalse(code == null);
        assertEquals(0, code.size());

        // Reference Identity
        code = utils.assign(utils.OBJECT, utils.OBJECT);
        assertFalse(code == null);
        assertEquals(0, code.size());

        // Case #2: Proper Subtype

        code = utils.assign(factory.fromClass(String.class), utils.OBJECT);
        assertFalse(code == null);
        assertEquals(0, code.size());

        // Case #3: Auto-Boxing

        code = utils.assign(utils.PRIMITIVE_BOOLEAN, utils.BOXED_BOOLEAN);
        assertFalse(code == null);
        assertEquals(1, code.size());
        assertEquals(Type.getInternalName(Boolean.class), ((MethodInsnNode) code.get(0)).owner);
        assertEquals("valueOf", ((MethodInsnNode) code.get(0)).name);
        assertEquals("(Z)Ljava/lang/Boolean;", ((MethodInsnNode) code.get(0)).desc);

        // Case #4: Auto-Unboxing

        code = utils.assign(utils.BOXED_BOOLEAN, utils.PRIMITIVE_BOOLEAN);
        assertFalse(code == null);
        assertEquals(1, code.size());
        assertEquals(Type.getInternalName(Boolean.class), ((MethodInsnNode) code.get(0)).owner);
        assertEquals("booleanValue", ((MethodInsnNode) code.get(0)).name);
        assertEquals("()Z", ((MethodInsnNode) code.get(0)).desc);

        // Case #5: Assignment Failed.

        code = utils.assign(utils.OBJECT, utils.NUMBER);
        assertTrue(code == null);
    }

    /**
     * Test: 20130821005217108018
     *
     * <p>
     * Method:
     * <code>box(IType, IType)</code>
     * </p>
     *
     * <p>
     * Case: Test Everything
     * </p>
     */
    @Test
    public void test20130821005217108018()
    {
        System.out.println("Test: 20130821005217108018");

        final ITypeFactory factory = new TypeFactory();

        final TypeSystemUtils utils = new TypeSystemUtils(factory);

        List<AbstractInsnNode> code;

        // Case #1: Primitive-Type to Boxed-Type

        code = utils.box(utils.PRIMITIVE_BOOLEAN, utils.BOXED_BOOLEAN);
        assertFalse(code == null);
        assertEquals(1, code.size());
        assertEquals(Type.getInternalName(Boolean.class), ((MethodInsnNode) code.get(0)).owner);
        assertEquals("valueOf", ((MethodInsnNode) code.get(0)).name);
        assertEquals("(Z)Ljava/lang/Boolean;", ((MethodInsnNode) code.get(0)).desc);

        code = utils.box(utils.PRIMITIVE_CHAR, utils.BOXED_CHAR);
        assertFalse(code == null);
        assertEquals(1, code.size());
        assertEquals(Type.getInternalName(Character.class), ((MethodInsnNode) code.get(0)).owner);
        assertEquals("valueOf", ((MethodInsnNode) code.get(0)).name);
        assertEquals("(C)Ljava/lang/Character;", ((MethodInsnNode) code.get(0)).desc);

        code = utils.box(utils.PRIMITIVE_BYTE, utils.BOXED_BYTE);
        assertFalse(code == null);
        assertEquals(1, code.size());
        assertEquals(Type.getInternalName(Byte.class), ((MethodInsnNode) code.get(0)).owner);
        assertEquals("valueOf", ((MethodInsnNode) code.get(0)).name);
        assertEquals("(B)Ljava/lang/Byte;", ((MethodInsnNode) code.get(0)).desc);

        code = utils.box(utils.PRIMITIVE_SHORT, utils.BOXED_SHORT);
        assertFalse(code == null);
        assertEquals(1, code.size());
        assertEquals(Type.getInternalName(Short.class), ((MethodInsnNode) code.get(0)).owner);
        assertEquals("valueOf", ((MethodInsnNode) code.get(0)).name);
        assertEquals("(S)Ljava/lang/Short;", ((MethodInsnNode) code.get(0)).desc);

        code = utils.box(utils.PRIMITIVE_INT, utils.BOXED_INT);
        assertFalse(code == null);
        assertEquals(1, code.size());
        assertEquals(Type.getInternalName(Integer.class), ((MethodInsnNode) code.get(0)).owner);
        assertEquals("valueOf", ((MethodInsnNode) code.get(0)).name);
        assertEquals("(I)Ljava/lang/Integer;", ((MethodInsnNode) code.get(0)).desc);

        code = utils.box(utils.PRIMITIVE_LONG, utils.BOXED_LONG);
        assertFalse(code == null);
        assertEquals(1, code.size());
        assertEquals(Type.getInternalName(Long.class), ((MethodInsnNode) code.get(0)).owner);
        assertEquals("valueOf", ((MethodInsnNode) code.get(0)).name);
        assertEquals("(J)Ljava/lang/Long;", ((MethodInsnNode) code.get(0)).desc);

        code = utils.box(utils.PRIMITIVE_FLOAT, utils.BOXED_FLOAT);
        assertFalse(code == null);
        assertEquals(1, code.size());
        assertEquals(Type.getInternalName(Float.class), ((MethodInsnNode) code.get(0)).owner);
        assertEquals("valueOf", ((MethodInsnNode) code.get(0)).name);
        assertEquals("(F)Ljava/lang/Float;", ((MethodInsnNode) code.get(0)).desc);

        code = utils.box(utils.PRIMITIVE_DOUBLE, utils.BOXED_DOUBLE);
        assertFalse(code == null);
        assertEquals(1, code.size());
        assertEquals(Type.getInternalName(Double.class), ((MethodInsnNode) code.get(0)).owner);
        assertEquals("valueOf", ((MethodInsnNode) code.get(0)).name);
        assertEquals("(D)Ljava/lang/Double;", ((MethodInsnNode) code.get(0)).desc);

        // Case #2: Primitive-Type to type <code>java.lang.Number</code>

        code = utils.box(utils.PRIMITIVE_BYTE, utils.NUMBER);
        assertFalse(code == null);
        assertEquals(1, code.size());
        assertEquals(Type.getInternalName(Byte.class), ((MethodInsnNode) code.get(0)).owner);
        assertEquals("valueOf", ((MethodInsnNode) code.get(0)).name);
        assertEquals("(B)Ljava/lang/Byte;", ((MethodInsnNode) code.get(0)).desc);

        code = utils.box(utils.PRIMITIVE_SHORT, utils.NUMBER);
        assertFalse(code == null);
        assertEquals(1, code.size());
        assertEquals(Type.getInternalName(Short.class), ((MethodInsnNode) code.get(0)).owner);
        assertEquals("valueOf", ((MethodInsnNode) code.get(0)).name);
        assertEquals("(S)Ljava/lang/Short;", ((MethodInsnNode) code.get(0)).desc);

        code = utils.box(utils.PRIMITIVE_INT, utils.NUMBER);
        assertFalse(code == null);
        assertEquals(1, code.size());
        assertEquals(Type.getInternalName(Integer.class), ((MethodInsnNode) code.get(0)).owner);
        assertEquals("valueOf", ((MethodInsnNode) code.get(0)).name);
        assertEquals("(I)Ljava/lang/Integer;", ((MethodInsnNode) code.get(0)).desc);

        code = utils.box(utils.PRIMITIVE_LONG, utils.NUMBER);
        assertFalse(code == null);
        assertEquals(1, code.size());
        assertEquals(Type.getInternalName(Long.class), ((MethodInsnNode) code.get(0)).owner);
        assertEquals("valueOf", ((MethodInsnNode) code.get(0)).name);
        assertEquals("(J)Ljava/lang/Long;", ((MethodInsnNode) code.get(0)).desc);

        code = utils.box(utils.PRIMITIVE_FLOAT, utils.NUMBER);
        assertFalse(code == null);
        assertEquals(1, code.size());
        assertEquals(Type.getInternalName(Float.class), ((MethodInsnNode) code.get(0)).owner);
        assertEquals("valueOf", ((MethodInsnNode) code.get(0)).name);
        assertEquals("(F)Ljava/lang/Float;", ((MethodInsnNode) code.get(0)).desc);

        code = utils.box(utils.PRIMITIVE_DOUBLE, utils.NUMBER);
        assertFalse(code == null);
        assertEquals(1, code.size());
        assertEquals(Type.getInternalName(Double.class), ((MethodInsnNode) code.get(0)).owner);
        assertEquals("valueOf", ((MethodInsnNode) code.get(0)).name);
        assertEquals("(D)Ljava/lang/Double;", ((MethodInsnNode) code.get(0)).desc);

        // Case #3: Primitive-Type to type <code>java.lang.Object</code>

        code = utils.box(utils.PRIMITIVE_BOOLEAN, utils.OBJECT);
        assertFalse(code == null);
        assertEquals(1, code.size());
        assertEquals(Type.getInternalName(Boolean.class), ((MethodInsnNode) code.get(0)).owner);
        assertEquals("valueOf", ((MethodInsnNode) code.get(0)).name);
        assertEquals("(Z)Ljava/lang/Boolean;", ((MethodInsnNode) code.get(0)).desc);

        code = utils.box(utils.PRIMITIVE_CHAR, utils.OBJECT);
        assertFalse(code == null);
        assertEquals(1, code.size());
        assertEquals(Type.getInternalName(Character.class), ((MethodInsnNode) code.get(0)).owner);
        assertEquals("valueOf", ((MethodInsnNode) code.get(0)).name);
        assertEquals("(C)Ljava/lang/Character;", ((MethodInsnNode) code.get(0)).desc);

        code = utils.box(utils.PRIMITIVE_BYTE, utils.OBJECT);
        assertFalse(code == null);
        assertEquals(1, code.size());
        assertEquals(Type.getInternalName(Byte.class), ((MethodInsnNode) code.get(0)).owner);
        assertEquals("valueOf", ((MethodInsnNode) code.get(0)).name);
        assertEquals("(B)Ljava/lang/Byte;", ((MethodInsnNode) code.get(0)).desc);

        code = utils.box(utils.PRIMITIVE_SHORT, utils.OBJECT);
        assertFalse(code == null);
        assertEquals(1, code.size());
        assertEquals(Type.getInternalName(Short.class), ((MethodInsnNode) code.get(0)).owner);
        assertEquals("valueOf", ((MethodInsnNode) code.get(0)).name);
        assertEquals("(S)Ljava/lang/Short;", ((MethodInsnNode) code.get(0)).desc);

        code = utils.box(utils.PRIMITIVE_INT, utils.OBJECT);
        assertFalse(code == null);
        assertEquals(1, code.size());
        assertEquals(Type.getInternalName(Integer.class), ((MethodInsnNode) code.get(0)).owner);
        assertEquals("valueOf", ((MethodInsnNode) code.get(0)).name);
        assertEquals("(I)Ljava/lang/Integer;", ((MethodInsnNode) code.get(0)).desc);

        code = utils.box(utils.PRIMITIVE_LONG, utils.OBJECT);
        assertFalse(code == null);
        assertEquals(1, code.size());
        assertEquals(Type.getInternalName(Long.class), ((MethodInsnNode) code.get(0)).owner);
        assertEquals("valueOf", ((MethodInsnNode) code.get(0)).name);
        assertEquals("(J)Ljava/lang/Long;", ((MethodInsnNode) code.get(0)).desc);

        code = utils.box(utils.PRIMITIVE_FLOAT, utils.OBJECT);
        assertFalse(code == null);
        assertEquals(1, code.size());
        assertEquals(Type.getInternalName(Float.class), ((MethodInsnNode) code.get(0)).owner);
        assertEquals("valueOf", ((MethodInsnNode) code.get(0)).name);
        assertEquals("(F)Ljava/lang/Float;", ((MethodInsnNode) code.get(0)).desc);

        code = utils.box(utils.PRIMITIVE_DOUBLE, utils.OBJECT);
        assertFalse(code == null);
        assertEquals(1, code.size());
        assertEquals(Type.getInternalName(Double.class), ((MethodInsnNode) code.get(0)).owner);
        assertEquals("valueOf", ((MethodInsnNode) code.get(0)).name);
        assertEquals("(D)Ljava/lang/Double;", ((MethodInsnNode) code.get(0)).desc);

        // Case #4: Auto-Boxing Not-Applicable

        code = utils.box(utils.NUMBER, utils.OBJECT);
        assertTrue(code == null);
    }

    /**
     * Test: 20130821005217108068
     *
     * <p>
     * Method:
     * <code>unbox(IType, IType)</code>
     * </p>
     *
     * <p>
     * Case: Test Everything
     * </p>
     */
    @Test
    public void test20130821005217108068()
    {
        System.out.println("Test: 20130821005217108068");

        final ITypeFactory factory = new TypeFactory();

        final TypeSystemUtils utils = new TypeSystemUtils(factory);

        List<AbstractInsnNode> code;

        // Case #1: Boxed-Type to Primitive-Type

        code = utils.unbox(utils.BOXED_BOOLEAN, utils.PRIMITIVE_BOOLEAN);
        assertFalse(code == null);
        assertEquals(1, code.size());
        assertEquals(Type.getInternalName(Boolean.class), ((MethodInsnNode) code.get(0)).owner);
        assertEquals("booleanValue", ((MethodInsnNode) code.get(0)).name);
        assertEquals("()Z", ((MethodInsnNode) code.get(0)).desc);

        code = utils.unbox(utils.BOXED_CHAR, utils.PRIMITIVE_CHAR);
        assertFalse(code == null);
        assertEquals(1, code.size());
        assertEquals(Type.getInternalName(Character.class), ((MethodInsnNode) code.get(0)).owner);
        assertEquals("charValue", ((MethodInsnNode) code.get(0)).name);
        assertEquals("()C", ((MethodInsnNode) code.get(0)).desc);

        code = utils.unbox(utils.BOXED_BYTE, utils.PRIMITIVE_BYTE);
        assertFalse(code == null);
        assertEquals(1, code.size());
        assertEquals(Type.getInternalName(Byte.class), ((MethodInsnNode) code.get(0)).owner);
        assertEquals("byteValue", ((MethodInsnNode) code.get(0)).name);
        assertEquals("()B", ((MethodInsnNode) code.get(0)).desc);

        code = utils.unbox(utils.BOXED_SHORT, utils.PRIMITIVE_SHORT);
        assertFalse(code == null);
        assertEquals(1, code.size());
        assertEquals(Type.getInternalName(Short.class), ((MethodInsnNode) code.get(0)).owner);
        assertEquals("shortValue", ((MethodInsnNode) code.get(0)).name);
        assertEquals("()S", ((MethodInsnNode) code.get(0)).desc);

        code = utils.unbox(utils.BOXED_INT, utils.PRIMITIVE_INT);
        assertFalse(code == null);
        assertEquals(1, code.size());
        assertEquals(Type.getInternalName(Integer.class), ((MethodInsnNode) code.get(0)).owner);
        assertEquals("intValue", ((MethodInsnNode) code.get(0)).name);
        assertEquals("()I", ((MethodInsnNode) code.get(0)).desc);

        code = utils.unbox(utils.BOXED_LONG, utils.PRIMITIVE_LONG);
        assertFalse(code == null);
        assertEquals(1, code.size());
        assertEquals(Type.getInternalName(Long.class), ((MethodInsnNode) code.get(0)).owner);
        assertEquals("longValue", ((MethodInsnNode) code.get(0)).name);
        assertEquals("()J", ((MethodInsnNode) code.get(0)).desc);

        code = utils.unbox(utils.BOXED_FLOAT, utils.PRIMITIVE_FLOAT);
        assertFalse(code == null);
        assertEquals(1, code.size());
        assertEquals(Type.getInternalName(Float.class), ((MethodInsnNode) code.get(0)).owner);
        assertEquals("floatValue", ((MethodInsnNode) code.get(0)).name);
        assertEquals("()F", ((MethodInsnNode) code.get(0)).desc);

        code = utils.unbox(utils.BOXED_DOUBLE, utils.PRIMITIVE_DOUBLE);
        assertFalse(code == null);
        assertEquals(1, code.size());
        assertEquals(Type.getInternalName(Double.class), ((MethodInsnNode) code.get(0)).owner);
        assertEquals("doubleValue", ((MethodInsnNode) code.get(0)).name);
        assertEquals("()D", ((MethodInsnNode) code.get(0)).desc);

        // Case #2: Auto-Unboxing Not-Applicable

        code = utils.unbox(utils.OBJECT, utils.NUMBER);
        assertTrue(code == null);
    }

    /**
     * Test: 20130821034423394202
     *
     * <p>
     * Method:
     * <code>sort(Collection)</code>
     * </p>
     *
     * <p>
     * Case: Test EveryThing
     * </p>
     */
    @Test
    public void test20130821034423394202()
    {
        System.out.println("Test: 20130821034423394202");

        // Note: Sorting of methods/constructors is performed using a topological sort.
        //       As a result, the algorithm may produce slightly different results randomly.
        //      Thus, only a weaker invariant can be enforced.
        //      Specifically, a more specific method will "come before" a less specific method
        //        within the results of a sort.
        //      Furthermore, the tests should be repeated multiple times in order to lessen
        //      the likelyhood that discrepancies will randomly appear.

        for (int i = 0; i < 1000; i++)
        {
            testSort();
        }
    }

    /**
     * Body of Test: test20130821034423394202
     */
    private void testSort()
    {
        final TypeFactory factory = new TypeFactory();

        final TypeSystemUtils utils = new TypeSystemUtils(factory);

        final IInterfaceType type = (IInterfaceType) factory.fromClass(TestInterface1.class);

        final Collection<IMethod> methods = type.getAllVisibleMethods();

        final List<IMethod> sorted = utils.sort(methods);

        final String FACE1 = Type.getDescriptor(TestInterface1.class);
        final String FACE2 = Type.getDescriptor(TestInterface2.class);
        final String FACE3 = Type.getDescriptor(TestInterface3.class);

        // Remember, comparisons are based on:
        // . Name
        // . Parameter Count
        // . Parameter List Specificity
        // . . Primitives Types Come First
        // . . Subtyping
        // . Return Type
        // . Owner

        // Case: different name

        comesBefore(sorted,
                    FACE3,
                    "method1(I)V",
                    FACE3,
                    "method2(I)V");

        // Case: different number of parameters

        comesBefore(sorted,
                    FACE3,
                    "method1(I)V",
                    FACE3,
                    "method1(II)V");

        // Case: parameters, primitive-types come first

        comesBefore(sorted,
                    FACE3,
                    "method3(I)V",
                    FACE3,
                    "method3(Ljava/lang/Integer;)V");

        // Case: parameters, more specific

        comesBefore(sorted,
                    FACE3,
                    "method3(Ljava/lang/String;)V",
                    FACE3,
                    "method3(Ljava/lang/Object;)V");

        comesBefore(sorted,
                    FACE3,
                    "method3(Ljava/lang/Integer;)V",
                    FACE3,
                    "method3(Ljava/lang/Number;)V");

        comesBefore(sorted,
                    FACE3,
                    "method3(Ljava/lang/Number;)V",
                    FACE3,
                    "method3(Ljava/lang/Object;)V");

        // Case: more specific return-type

        comesBefore(sorted,
                    FACE1,
                    "method4(I)Ljava/lang/String;",
                    FACE2,
                    "method4(I)Ljava/lang/Object;");

        // Case: more specific owner

        comesBefore(sorted,
                    FACE1,
                    "method5()V",
                    FACE3,
                    "method5()V");
    }

    /**
     * This method ensures that a particular invokable member precedes another specific
     * invokable member in a given list of invokable members.
     *
     * @param members    is the list that contains the invokable members.
     * @param owner1     is the descriptor of the type that declares the first invokable member.
     * @param signature1 is the name and descriptor of the first invokable member.
     * @param owner2     is the descriptor of the type that declares the second invokable member.
     * @param signature2 is the name and descriptor of the second invokable member.
     */
    private void comesBefore(final List<? extends IInvokableMember> members,
                             final String owner1,
                             final String signature1,
                             final String owner2,
                             final String signature2)
    {
        for (IInvokableMember member : members)
        {
            final boolean condition1 = member.getOwner().getDescriptor().equals(owner1)
                                       && member.getNamePlusDescriptor().equals(signature1);

            final boolean condition2 = member.getOwner().getDescriptor().equals(owner2)
                                       && member.getNamePlusDescriptor().equals(signature2);

            if (condition1)
            {
                return;
            }
            else if (condition2)
            {
                fail();
            }
        }
    }

    /**
     * Test: 20130821135121807566
     *
     * <p>
     * Method:
     * <code>checkArgs(List, List)</code>
     * </p>
     *
     * <p>
     * Case: Test Everything
     * </p>
     */
    @Test
    public void test20130821135121807566()
    {
        System.out.println("Test: 20130821135121807566");

        final TypeFactory factory = new TypeFactory();

        final TypeSystemUtils utils = new TypeSystemUtils(factory);

        final IInterfaceType type = (IInterfaceType) factory.fromClass(TestInterface1.class);

        final Collection<IMethod> methods = type.getAllVisibleMethods();

        final String FACE1 = Type.getDescriptor(TestInterface1.class);

        fail();
    }
}

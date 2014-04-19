//package high.mackenzie.autumn.lang.compiler;
//
//import autumn.lang.compiler.errors.DefaultErrorReporter;
//import autumn.lang.compiler.errors.IErrorReporter;
//import autumn.lang.compiler.ast.nodes.Variable;
//import com.google.common.base.Objects;
//import high.mackenzie.autumn.lang.compiler.typesystem.TypeFactory;
//import high.mackenzie.autumn.lang.compiler.typesystem.design.IExpressionType;
//import static org.junit.Assert.*;
//import org.junit.Test;
//
///**
// *
// * @author mackenzie
// */
//public final class VariableScopeTest
//{
//    private final IErrorReporter reporter = new DefaultErrorReporter();
//
//    private final TypeFactory types = new TypeFactory();
//
//    private Variable var(final String name)
//    {
//        Variable var = new Variable();
//
//        var = var.setName(name);
//
//        return var;
//    }
//
//    private VariableScope buildScopeA(final VariableScope outer,
//                                      final int initial_address)
//    {
//        final VariableScope scope = new VariableScope(reporter, outer, initial_address);
//
//        return scope;
//    }
//
//    private void check(final VariableScope scope,
//                       final String description)
//    {
//        for (String element : scope.description())
//        {
//            if (Objects.equal(element, description))
//            {
//                return;
//            }
//        }
//
//        fail("No matching description.");
//    }
//
//    /**
//     * Test: 20130918210442759308
//     *
//     * <p>
//     * Method:
//     * <code>declareParameter</code>
//     * </p>
//     *
//     * <p>
//     * Case: normal
//     * </p>
//     */
//    @Test
//    public void test20130918210442759308()
//    {
//        System.out.println("Test: 20130918210442759308");
//
//        final VariableScope scope = new VariableScope(reporter, null, 0);
//
//        scope.declareParameter(var("a"), types.getInt());
//        scope.declareParameter(var("b"), types.getLong());
//        scope.declareParameter(var("c"), types.getInt());
//        scope.declareParameter(var("d"), types.getDouble());
//        scope.declareParameter(var("e"), types.getInt());
//        scope.declareParameter(var("f"), (IExpressionType) types.fromClass(Object.class));
//        scope.declareParameter(var("g"), types.getInt());
//
//        check(scope, "depth 00000 address 00000 param a : I");
//        check(scope, "depth 00000 address 00001 param b : J");
//        check(scope, "depth 00000 address 00003 param c : I");
//        check(scope, "depth 00000 address 00004 param d : D");
//        check(scope, "depth 00000 address 00006 param e : I");
//        check(scope, "depth 00000 address 00007 param f : Ljava/lang/Object;");
//        check(scope, "depth 00000 address 00008 param g : I");
//    }
//
//    /**
//     * Test: 20130918210442759308
//     *
//     * <p>
//     * Method:
//     * <code>declareVar</code>
//     * </p>
//     *
//     * <p>
//     * Case: normal
//     * </p>
//     */
//    @Test
//    public void test20130918210442759422()
//    {
//        System.out.println("Test: 20130918210442759422");
//
//        final VariableScope scope = new VariableScope(reporter, null, 0);
//
//        scope.declareVar(var("a"), types.getInt());
//        scope.declareVar(var("b"), types.getLong());
//        scope.declareVar(var("c"), types.getInt());
//        scope.declareVar(var("d"), types.getDouble());
//        scope.declareVar(var("e"), types.getInt());
//        scope.declareVar(var("f"), (IExpressionType) types.fromClass(Object.class));
//        scope.declareVar(var("g"), types.getInt());
//
//        check(scope, "depth 00000 address 00000 var a : I");
//        check(scope, "depth 00000 address 00001 var b : J");
//        check(scope, "depth 00000 address 00003 var c : I");
//        check(scope, "depth 00000 address 00004 var d : D");
//        check(scope, "depth 00000 address 00006 var e : I");
//        check(scope, "depth 00000 address 00007 var f : Ljava/lang/Object;");
//        check(scope, "depth 00000 address 00008 var g : I");
//    }
//
//    /**
//     * Test: 20130918210442759308
//     *
//     * <p>
//     * Method:
//     * <code>declareVal</code>
//     * </p>
//     *
//     * <p>
//     * Case: normal
//     * </p>
//     */
//    @Test
//    public void test20130918210442759470()
//    {
//        System.out.println("Test: 20130918210442759470");
//
//        final VariableScope scope = new VariableScope(reporter, null, 0);
//
//        scope.declareVal(var("a"), types.getInt());
//        scope.declareVal(var("b"), types.getLong());
//        scope.declareVal(var("c"), types.getInt());
//        scope.declareVal(var("d"), types.getDouble());
//        scope.declareVal(var("e"), types.getInt());
//        scope.declareVal(var("f"), (IExpressionType) types.fromClass(Object.class));
//        scope.declareVal(var("g"), types.getInt());
//
//        check(scope, "depth 00000 address 00000 val a : I");
//        check(scope, "depth 00000 address 00001 val b : J");
//        check(scope, "depth 00000 address 00003 val c : I");
//        check(scope, "depth 00000 address 00004 val d : D");
//        check(scope, "depth 00000 address 00006 val e : I");
//        check(scope, "depth 00000 address 00007 val f : Ljava/lang/Object;");
//        check(scope, "depth 00000 address 00008 val g : I");
//    }
//
//    /**
//     * Test: 20130918210442759308
//     *
//     * <p>
//     * Method:
//     * <code>declareHeap</code>
//     * </p>
//     *
//     * <p>
//     * Case: normal
//     * </p>
//     */
//    @Test
//    public void test20130918210442759511()
//    {
//        System.out.println("Test: 20130918210442759511");
//
//        final VariableScope scope = new VariableScope(reporter, null, 0);
//
//        scope.declareHeap(var("a"), types.getInt());
//        scope.declareHeap(var("b"), types.getLong());
//        scope.declareHeap(var("c"), types.getInt());
//        scope.declareHeap(var("d"), types.getDouble());
//        scope.declareHeap(var("e"), types.getInt());
//        scope.declareHeap(var("f"), (IExpressionType) types.fromClass(Object.class));
//        scope.declareHeap(var("g"), types.getInt());
//
//        check(scope, "depth 00000 address 00000 heap a : I");
//        check(scope, "depth 00000 address 00001 heap b : J");
//        check(scope, "depth 00000 address 00002 heap c : I");
//        check(scope, "depth 00000 address 00003 heap d : D");
//        check(scope, "depth 00000 address 00004 heap e : I");
//        check(scope, "depth 00000 address 00005 heap f : Ljava/lang/Object;");
//        check(scope, "depth 00000 address 00006 heap g : I");
//    }
//
//    /**
//     * Test: 20130918210442759548
//     *
//     * <p>
//     * Method:
//     * <code>isDeclared</code>
//     * </p>
//     */
//    @Test
//    public void test20130918210442759548()
//    {
//        System.out.println("Test: 20130918210442759548");
//
//        final VariableScope scope = new VariableScope(reporter, null, 0);
//
//        scope.declareParameter(var("a"), types.getInt());
//        scope.declareParameter(var("b"), types.getInt());
//        scope.declareVar(var("c"), types.getInt());
//        scope.declareVar(var("d"), types.getInt());
//        scope.declareVal(var("e"), types.getInt());
//        scope.declareVal(var("f"), types.getInt());
//        scope.declareHeap(var("g"), types.getInt());
//        scope.declareHeap(var("h"), types.getInt());
//
//        assertTrue(scope.isDeclared("a"));
//        assertTrue(scope.isDeclared("b"));
//        assertTrue(scope.isDeclared("c"));
//        assertTrue(scope.isDeclared("d"));
//        assertTrue(scope.isDeclared("e"));
//        assertTrue(scope.isDeclared("f"));
//        assertTrue(scope.isDeclared("g"));
//        assertTrue(scope.isDeclared("h"));
//
//        assertFalse(scope.isDeclared("z"));
//    }
//
//    /**
//     * Test: 20130918210442759584
//     *
//     * <p>
//     * Method:
//     * <code>isHeapAllocated</code>
//     * </p>
//     */
//    @Test
//    public void test20130918210442759584()
//    {
//        System.out.println("Test: 20130918210442759584");
//
//        final VariableScope scope = new VariableScope(reporter, null, 0);
//
//        scope.declareParameter(var("a"), types.getInt());
//        scope.declareParameter(var("b"), types.getInt());
//        scope.declareVar(var("c"), types.getInt());
//        scope.declareVar(var("d"), types.getInt());
//        scope.declareVal(var("e"), types.getInt());
//        scope.declareVal(var("f"), types.getInt());
//        scope.declareHeap(var("g"), types.getInt());
//        scope.declareHeap(var("h"), types.getInt());
//
//        assertFalse(scope.isHeapAllocated("a"));
//        assertFalse(scope.isHeapAllocated("b"));
//        assertFalse(scope.isHeapAllocated("c"));
//        assertFalse(scope.isHeapAllocated("d"));
//        assertFalse(scope.isHeapAllocated("e"));
//        assertFalse(scope.isHeapAllocated("f"));
//
//        assertTrue(scope.isHeapAllocated("g"));
//        assertTrue(scope.isHeapAllocated("h"));
//    }
//
//    /**
//     * Test: 20130918210442759619
//     *
//     * <p>
//     * Method:
//     * <code>isReadOnly</code>
//     * </p>
//     */
//    @Test
//    public void test20130918210442759619()
//    {
//        System.out.println("Test: 20130918210442759619");
//
//        final VariableScope scope = new VariableScope(reporter, null, 0);
//
//        scope.declareParameter(var("a"), types.getInt());
//        scope.declareParameter(var("b"), types.getInt());
//        scope.declareVar(var("c"), types.getInt());
//        scope.declareVar(var("d"), types.getInt());
//        scope.declareVal(var("e"), types.getInt());
//        scope.declareVal(var("f"), types.getInt());
//        scope.declareHeap(var("g"), types.getInt());
//        scope.declareHeap(var("h"), types.getInt());
//
//        assertTrue(scope.isReadOnly("a"));
//        assertTrue(scope.isReadOnly("b"));
//        assertTrue(scope.isReadOnly("e"));
//        assertTrue(scope.isReadOnly("f"));
//
//        assertFalse(scope.isReadOnly("c"));
//        assertFalse(scope.isReadOnly("d"));
//        assertFalse(scope.isReadOnly("g"));
//        assertFalse(scope.isReadOnly("h"));
//    }
//
//    /**
//     * Test: 20130918210442759653
//     *
//     * <p>
//     * Method:
//     * <code>isParameter</code>
//     * </p>
//     */
//    @Test
//    public void test20130918210442759653()
//    {
//        System.out.println("Test: 20130918210442759653");
//
//        final VariableScope scope = new VariableScope(reporter, null, 0);
//
//        scope.declareParameter(var("a"), types.getInt());
//        scope.declareParameter(var("b"), types.getInt());
//        scope.declareVar(var("c"), types.getInt());
//        scope.declareVar(var("d"), types.getInt());
//        scope.declareVal(var("e"), types.getInt());
//        scope.declareVal(var("f"), types.getInt());
//        scope.declareHeap(var("g"), types.getInt());
//        scope.declareHeap(var("h"), types.getInt());
//
//        assertTrue(scope.isParameter("a"));
//        assertTrue(scope.isParameter("b"));
//
//        assertFalse(scope.isParameter("c"));
//        assertFalse(scope.isParameter("d"));
//        assertFalse(scope.isParameter("e"));
//        assertFalse(scope.isParameter("f"));
//        assertFalse(scope.isParameter("g"));
//        assertFalse(scope.isParameter("h"));
//    }
//
//    /**
//     * Test: 20130918210442759689
//     *
//     * <p>
//     * Method:
//     * <code>addressOf</code>
//     * </p>
//     */
//    @Test
//    public void test20130918210442759689()
//    {
//        System.out.println("Test: 20130918210442759689");
//
//        final VariableScope scope = new VariableScope(reporter, null, 0);
//
//        scope.declareParameter(var("a"), types.getInt());
//        scope.declareParameter(var("b"), types.getInt());
//        scope.declareVar(var("c"), types.getInt());
//        scope.declareVar(var("d"), types.getInt());
//        scope.declareVal(var("e"), types.getInt());
//        scope.declareVal(var("f"), types.getInt());
//        scope.declareHeap(var("g"), types.getInt());
//        scope.declareHeap(var("h"), types.getInt());
//
//        assertEquals(0, scope.addressOf("a"));
//        assertEquals(1, scope.addressOf("b"));
//        assertEquals(2, scope.addressOf("c"));
//        assertEquals(3, scope.addressOf("d"));
//        assertEquals(4, scope.addressOf("e"));
//        assertEquals(5, scope.addressOf("f"));
//        assertEquals(6, scope.addressOf("g"));
//        assertEquals(7, scope.addressOf("h"));
//    }
//
//    /**
//     * Test: 20130918210442759725
//     *
//     * <p>
//     * Method:
//     * <code></code>
//     * </p>
//     *
//     * <p>
//     * Case:
//     * </p>
//     */
//    @Test
//    public void test20130918210442759725()
//    {
//        System.out.println("Test: 20130918210442759725");
//        fail();
//    }
//}


package high.mackenzie.autumn.compiler.typesystem;

import high.mackenzie.autumn.lang.compiler.typesystem.TypeFactory;
import high.mackenzie.autumn.lang.compiler.typesystem.VoidType;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mackenzie
 */
public class VoidTypeTest
{
    /**
     * Test: 20130722092918840209
     *
     * <p>
     * Case: Test Everything
     * </p>
     */
    @Test
    public void test20130722092918840209()
    {
        System.out.println("Test: 20130722092918840209");

        final TypeFactory factory = new TypeFactory();

        final VoidType t = (VoidType) factory.getVoid();
        assertTrue(t == ((VoidType) factory.fromClass(void.class)));
        assertTrue(t == ((VoidType) factory.findType("V")));
        assertEquals("V", t.getDescriptor());
        assertTrue(factory == t.getTypeFactory());
        assertTrue(void.class == t.toClass());

        // Test isSubtypeOf(IType)
        assertTrue(factory.getVoid().isSubtypeOf(factory.getVoid()));
        assertFalse(factory.getVoid().isSubtypeOf(factory.getByte()));
    }
}

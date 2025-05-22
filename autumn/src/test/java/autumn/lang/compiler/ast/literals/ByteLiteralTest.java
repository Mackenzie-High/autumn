package autumn.lang.compiler.ast.literals;

import static org.junit.Assert.*;
import org.junit.Test;

public final class ByteLiteralTest
{
    /**
     * Test: 20140111034001972579
     *
     * <p>
     * Case: Normal Literals
     * </p>
     */
    @Test
    public void test20140111034001972579()
    {
        System.out.println("Test: 20140111034001972579");

        final byte MIN = Byte.MIN_VALUE;
        final byte MAX = Byte.MAX_VALUE;
        final String suffix = "B";

        for (byte i = MIN; i < MAX; i++)
        {
            final byte n = i;

            final String source = "" + n + suffix;

            final ByteLiteral literal1 = new ByteLiteral(source);

            assertTrue(literal1.isParsable());
            assertFalse(literal1.isMalformed());
            assertEquals((Object) n, literal1.value());
            assertEquals(source, literal1.source());
            assertEquals(literal1.source(), literal1.toString());

            final ByteLiteral literal2 = new ByteLiteral(n);

            assertTrue(literal2.isParsable());
            assertFalse(literal2.isMalformed());
            assertEquals(literal1.value(), literal2.value());
            assertEquals(literal1.source(), literal2.source());
        }
    }

    /**
     * Test: 20140111034147173952
     *
     * <p>
     * Case: Malformed Literals
     * </p>
     */
    @Test
    public void test20140111034147173952()
    {
        System.out.println("Test: 20140111034147173952");

        final ByteLiteral literal = new ByteLiteral("200B");

        assertTrue(literal.isParsable());
        assertTrue(literal.isMalformed());
        assertTrue(literal.value() == null);
    }

    /**
     * Test: 20140111034147174073
     *
     * <p>
     * Case: Unparsable Literals
     * </p>
     */
    @Test
    public void test20140111034147174073()
    {
        System.out.println("Test: 20140111034147174073");

        final ByteLiteral literal = new ByteLiteral("ABC");

        assertFalse(literal.isParsable());
        assertTrue(literal.isMalformed());
        assertTrue(literal.value() == null);
    }
}

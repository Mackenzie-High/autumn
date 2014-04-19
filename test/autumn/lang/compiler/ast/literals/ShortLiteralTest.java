package autumn.lang.compiler.ast.literals;

import static org.junit.Assert.*;
import org.junit.Test;

public final class ShortLiteralTest
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

        final short MIN = Short.MIN_VALUE;
        final short MAX = Short.MAX_VALUE;
        final String suffix = "S";

        for (short i = MIN; i < MAX; i++)
        {
            final short n = i;

            final String source = "" + n + suffix;

            final ShortLiteral literal1 = new ShortLiteral(source);

            assertTrue(literal1.isParsable());
            assertFalse(literal1.isMalformed());
            assertEquals((Object) n, literal1.value());
            assertEquals(source, literal1.source());

            final ShortLiteral literal2 = new ShortLiteral(n);

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

        final ShortLiteral literal = new ShortLiteral("71234S");

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

        final ShortLiteral literal = new ShortLiteral("ABC");

        assertFalse(literal.isParsable());
        assertTrue(literal.isMalformed());
        assertTrue(literal.value() == null);
    }
}

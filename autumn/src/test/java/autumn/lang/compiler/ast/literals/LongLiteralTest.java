package autumn.lang.compiler.ast.literals;

import java.util.Random;
import static org.junit.Assert.*;
import org.junit.Test;

public final class LongLiteralTest
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

        final String suffix = "L";

        final Random random = new Random();

        for (int i = 0; i < 123456; i++)
        {
            final long n = random.nextBoolean() ? -random.nextLong() : random.nextLong();

            final String source = "" + n + suffix;

            final LongLiteral literal1 = new LongLiteral(source);

            assertTrue(literal1.isParsable());
            assertFalse(literal1.isMalformed());
            assertEquals((Object) n, literal1.value());
            assertEquals(source, literal1.source());

            final LongLiteral literal2 = new LongLiteral(n);

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

        final LongLiteral literal = new LongLiteral("90000000000000000000000000000000000000000L");

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

        final LongLiteral literal = new LongLiteral("ABC");

        assertFalse(literal.isParsable());
        assertTrue(literal.isMalformed());
        assertTrue(literal.value() == null);
    }
}

package autumn.lang.compiler.ast.literals;

import java.util.Random;
import static org.junit.Assert.*;
import org.junit.Test;

public final class DoubleLiteralTest
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

        final String suffix = "";

        final Random random = new Random();

        for (int i = 0; i < 123456; i++)
        {
            final double n = random.nextBoolean()
                    ? (random.nextBoolean() ? -random.nextInt() : random.nextInt())
                    : (random.nextBoolean() ? -random.nextDouble() : random.nextDouble());

            final String source = "" + n + suffix;

            final DoubleLiteral literal1 = new DoubleLiteral(source);

            assertTrue(literal1.isParsable());
            assertFalse(literal1.isMalformed());
            assertEquals((Object) n, literal1.value());
            assertEquals(source, literal1.source());

            final DoubleLiteral literal2 = new DoubleLiteral(n);

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

        final DoubleLiteral literal = new DoubleLiteral(Double.POSITIVE_INFINITY);

        assertFalse(literal.isParsable());
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

        final DoubleLiteral literal = new DoubleLiteral("ABC");

        assertFalse(literal.isParsable());
        assertTrue(literal.isMalformed());
        assertTrue(literal.value() == null);
    }
}

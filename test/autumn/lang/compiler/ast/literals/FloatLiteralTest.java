package autumn.lang.compiler.ast.literals;

import java.util.Random;
import static org.junit.Assert.*;
import org.junit.Test;

public final class FloatLiteralTest
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

        final String suffix = "F";

        final Random random = new Random();

        for (int i = 0; i < 123456; i++)
        {
            final float n = random.nextBoolean()
                    ? (random.nextBoolean() ? -random.nextInt() : random.nextInt())
                    : (random.nextBoolean() ? -random.nextFloat() : random.nextFloat());

            final String source = "" + n + suffix;

            final FloatLiteral literal1 = new FloatLiteral(source);

            assertTrue(literal1.isParsable());
            assertFalse(literal1.isMalformed());
            assertEquals((Object) n, literal1.value());
            assertEquals(source, literal1.source());

            final FloatLiteral literal2 = new FloatLiteral(n);

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

        final FloatLiteral literal = new FloatLiteral(Double.MAX_VALUE + "F");

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

        final FloatLiteral literal = new FloatLiteral("ABC");

        assertFalse(literal.isParsable());
        assertTrue(literal.isMalformed());
        assertTrue(literal.value() == null);
    }
}

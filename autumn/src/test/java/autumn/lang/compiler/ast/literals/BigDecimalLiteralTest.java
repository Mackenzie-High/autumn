package autumn.lang.compiler.ast.literals;

import autumn.lang.internals.Helpers;
import java.util.Random;
import static org.junit.Assert.*;
import org.junit.Test;

public final class BigDecimalLiteralTest
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

        final String suffix = "BD";

        final Random random = new Random();

        for (int i = 0; i < 123456; i++)
        {
            final double n = random.nextBoolean()
                    ? (random.nextBoolean() ? -random.nextInt() : random.nextInt())
                    : (random.nextBoolean() ? -random.nextDouble() : random.nextDouble());

            final String source = "" + n + suffix;

            final BigDecimalLiteral literal1 = new BigDecimalLiteral(source);

            assertTrue(literal1.isParsable());
            assertFalse(literal1.isMalformed());
            assertEquals(Helpers.createBigDecimal(Double.toString(n)), literal1.value());
            assertEquals(source, literal1.source());
        }
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

package autumn.lang.compiler.ast.literals;

import java.math.BigInteger;
import java.util.Random;
import static org.junit.Assert.*;
import org.junit.Test;

public final class BigIntegerLiteralTest
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

        final String suffix = "BI";

        final Random random = new Random();

        for (int i = 0; i < 123456; i++)
        {
            final long n = random.nextBoolean() ? -random.nextLong() : random.nextLong();

            final String source = "" + n + suffix;

            final BigIntegerLiteral literal1 = new BigIntegerLiteral(source);

            assertTrue(literal1.isParsable());
            assertFalse(literal1.isMalformed());
            assertEquals(BigInteger.valueOf(n), literal1.value());
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

        final BigIntegerLiteral literal = new BigIntegerLiteral("ABC");

        assertFalse(literal.isParsable());
        assertTrue(literal.isMalformed());
        assertTrue(literal.value() == null);
    }
}

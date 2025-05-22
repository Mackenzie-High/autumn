package autumn.lang.compiler.ast.literals;

import static org.junit.Assert.*;
import org.junit.Test;

public final class CharLiteralTest
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

        final char MIN = Character.MIN_VALUE;
        final char MAX = Character.MAX_VALUE;
        final String suffix = "C";

        for (char i = MIN; i < MAX; i++)
        {
            final char n = i;

            final String source1 = "" + Long.toString(n) + suffix;

            final CharLiteral literal1 = new CharLiteral(source1);

            assertTrue(literal1.isParsable());
            assertFalse(literal1.isMalformed());
            assertEquals((Object) n, literal1.value());
            assertEquals(source1, literal1.source());

            if (n >= 1 && n <= 127 && n != '\r')
            {
                final String source2 = "'" + n + "'";

                final CharLiteral literal2 = new CharLiteral(source2);
                assertTrue(literal2.isParsable());
                assertFalse(literal2.isMalformed());
                assertEquals((Object) n, literal2.value());
                assertEquals(source2, literal2.source());
            }

            final CharLiteral literal3 = new CharLiteral(n);

            assertTrue(literal3.isParsable());
            assertFalse(literal3.isMalformed());
            assertEquals(literal1.value(), literal3.value());
            assertEquals(literal1.source(), literal3.source());
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

        final CharLiteral literal = new CharLiteral("123456C");

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

        final CharLiteral literal = new CharLiteral("ABC");

        assertFalse(literal.isParsable());
        assertTrue(literal.isMalformed());
        assertTrue(literal.value() == null);
    }
}

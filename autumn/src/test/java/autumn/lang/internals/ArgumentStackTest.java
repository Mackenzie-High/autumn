package autumn.lang.internals;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * This is a test of the ArgumentStack class.
 *
 * @author Mackenzie High
 */
public final class ArgumentStackTest
{
    /**
     * Test: 20140716110718418139
     *
     * <p>
     * Case: Automatic Coercions
     * </p>
     */
    @Test
    public void test20140716110718418139()
    {
        System.out.println("Test: 20140716110718418139");

        final ArgumentStack s = new ArgumentStack();

        s.push((char) 7);
        assertEquals((int) 7, s.peekI());
        assertEquals((long) 7, s.peekJ());

        s.clear();

        s.push((byte) 5);
        assertEquals((short) 5, s.peekS());
        assertEquals((int) 5, s.peekI());
        assertEquals((long) 5, s.peekJ());

        s.clear();

        s.push((short) 3);
        assertEquals((int) 3, s.peekI());
        assertEquals((long) 3, s.peekJ());

        s.clear();

        s.push((int) 7);
        assertEquals((long) 7, s.peekJ());

        s.clear();

        s.push((float) 5);
        assertEquals((double) 5, s.peekD(), 0.01);
    }

    /**
     * Test: 20140716113639505190
     *
     * <p>
     * Case: Boxing
     * </p>
     */
    @Test
    public void test20140716113639505190()
    {
        System.out.println("Test: 20140716113639505190");

        final ArgumentStack s = new ArgumentStack();

        s.push(true);
        assertEquals(true, s.peekO());

        s.push('X');
        assertEquals('X', s.peekO());

        s.push((byte) 7);
        assertEquals((byte) 7, s.peekO());

        s.push((short) 7);
        assertEquals((short) 7, s.peekO());

        s.push((int) 7);
        assertEquals((int) 7, s.peekO());

        s.push((long) 7);
        assertEquals((long) 7, s.peekO());

        s.push((float) 7);
        assertEquals((float) 7, s.peekO());

        s.push((double) 7);
        assertEquals((double) 7, s.peekO());
    }

    /**
     * Test: 20140716113639505283
     *
     * <p>
     * Case: Unboxing
     * </p>
     */
    @Test
    public void test20140716113639505283()
    {
        System.out.println("Test: 20140716113639505283");

        final ArgumentStack s = new ArgumentStack();

        s.push(Boolean.TRUE);
        assertEquals(true, s.peekZ());

        s.push(new Character('X'));
        assertEquals('X', s.peekC());

        s.push(Byte.valueOf((byte) 7));
        assertEquals((byte) 7, s.peekB());

        s.push(Short.valueOf((short) 7));
        assertEquals((short) 7, s.peekS());

        s.push((Integer) 7);
        assertEquals((int) 7, s.peekI());

        s.push(Long.valueOf(7));
        assertEquals((long) 7, s.peekJ());

        s.push(Float.valueOf(2));
        assertEquals((float) 2, s.peekF(), 0.01);

        s.push(Double.valueOf(2));
        assertEquals((double) 2, s.peekD(), 0.01);
    }

    /**
     * Test: 20140716113639505325
     *
     * <p>
     * Case: Identity
     * </p>
     */
    @Test
    public void test20140716113639505325()
    {
        System.out.println("Test: 20140716113639505325");

        final ArgumentStack s = new ArgumentStack();

        s.push(true);
        assertEquals(true, s.peekZ());

        s.push('X');
        assertEquals('X', s.peekC());

        s.push(((byte) 7));
        assertEquals((byte) 7, s.peekB());

        s.push(((short) 7));
        assertEquals((short) 7, s.peekS());

        s.push(7);
        assertEquals((int) 7, s.peekI());

        s.push(((long) 7));
        assertEquals((long) 7, s.peekJ());

        s.push(((float) 7));
        assertEquals((float) 7, s.peekF(), 0.01);

        s.push(((double) 7));
        assertEquals((double) 7, s.peekD(), 0.01);
    }

    /**
     * Test: 20140716113639505362
     *
     * <p>
     * Case: Wrong Type - Expected boolean
     * </p>
     */
    @Test(expected = ClassCastException.class)
    public void test20140716113639505362()
    {
        System.out.println("Test: 20140716113639505362");

        final ArgumentStack s = new ArgumentStack();

        s.push(5);

        s.peekZ();
    }

    /**
     * Test: 20140716113639505397
     *
     * <p>
     * Case: Wrong Type - Expected char
     * </p>
     */
    @Test(expected = ClassCastException.class)
    public void test20140716113639505397()
    {
        System.out.println("Test: 20140716113639505397");

        final ArgumentStack s = new ArgumentStack();

        s.push(5.0);

        s.peekC();
    }

    /**
     * Test: 20140716113811355209
     *
     * <p>
     * Case: Wrong Type - Expected byte
     * </p>
     */
    @Test(expected = ClassCastException.class)
    public void test20140716113811355209()
    {
        System.out.println("Test: 20140716113811355209");

        final ArgumentStack s = new ArgumentStack();

        s.push(5.0);

        s.peekB();
    }

    /**
     * Test: 20140716113811355301
     *
     * <p>
     * Case: Wrong Type - Expected short
     * </p>
     */
    @Test(expected = ClassCastException.class)
    public void test20140716113811355301()
    {
        System.out.println("Test: 20140716113811355301");

        final ArgumentStack s = new ArgumentStack();

        s.push(5.0);

        s.peekS();
    }

    /**
     * Test: 20140716113811355346
     *
     * <p>
     * Case: Wrong Type - Expected int
     * </p>
     */
    @Test(expected = ClassCastException.class)
    public void test20140716113811355346()
    {
        System.out.println("Test: 20140716113811355346");

        final ArgumentStack s = new ArgumentStack();

        s.push(5.0);

        s.peekI();
    }

    /**
     * Test: 20140716113811355383
     *
     * <p>
     * Case: Wrong Type - Expected long
     * </p>
     */
    @Test(expected = ClassCastException.class)
    public void test20140716113811355383()
    {
        System.out.println("Test: 20140716113811355383");

        final ArgumentStack s = new ArgumentStack();

        s.push(5.0);

        s.peekJ();
    }

    /**
     * Test: 20140716113811355417
     *
     * <p>
     * Case: Wrong Type - Expected float
     * </p>
     */
    @Test(expected = ClassCastException.class)
    public void test20140716113811355417()
    {
        System.out.println("Test: 20140716113811355417");

        final ArgumentStack s = new ArgumentStack();

        s.push(5);

        s.peekF();
    }

    /**
     * Test: 20140716113926644475
     *
     * <p>
     * Case: Wrong Type - Expected double
     * </p>
     */
    @Test(expected = ClassCastException.class)
    public void test20140716113926644475()
    {
        System.out.println("Test: 20140716113926644475");

        final ArgumentStack s = new ArgumentStack();

        s.push(5);

        s.peekD();
    }
}

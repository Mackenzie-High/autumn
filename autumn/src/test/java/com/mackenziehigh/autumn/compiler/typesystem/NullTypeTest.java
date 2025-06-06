package com.mackenziehigh.autumn.compiler.typesystem;

import com.mackenziehigh.autumn.lang.compiler.typesystem.NullType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.TypeFactory;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mackenzie
 */
public class NullTypeTest
{
    /**
     * Test: 20130722093205078631
     *
     * <p>
     * Case: Test Everything
     * </p>
     */
    @Test
    public void test20130722093205078631()
    {
        System.out.println("Test: 20130722093205078631");

        final TypeFactory factory = new TypeFactory();

        final NullType t = (NullType) factory.getNull();
        assertTrue(t == ((NullType) factory.findType("Lnull;")));
        assertEquals("Lnull;", t.getDescriptor());
        assertTrue(factory == t.getTypeFactory());

        // Test isSubtypeOf(IType)
        assertTrue(factory.getNull().isSubtypeOf(factory.fromClass(String.class)));
        assertTrue(factory.getNull().isSubtypeOf(factory.getNull()));
        assertFalse(factory.getNull().isSubtypeOf(factory.getByte()));
    }
}

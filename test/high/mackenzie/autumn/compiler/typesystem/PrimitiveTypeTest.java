/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.compiler.typesystem;

import high.mackenzie.autumn.lang.compiler.typesystem.TypeFactory;
import high.mackenzie.autumn.lang.compiler.typesystem.PrimitiveType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mackenzie
 */
public class PrimitiveTypeTest
{
    /**
     * Test: 20130722075132364380
     * 
     * <p>
     * Case: Test Everything
     * </p>
     */
     @Test
     public void test20130722075132364380()
     {
         System.out.println("Test: 20130722075132364380");
         
         final TypeFactory factory = new TypeFactory();
         
         PrimitiveType t;
         
         // boolean
         t = (PrimitiveType) factory.getBoolean();
         assertTrue(t.isBoolean());
         assertTrue(boolean.class == t.toClass());
         assertTrue(t == ((PrimitiveType) factory.fromClass(boolean.class)));
         assertTrue(t == ((PrimitiveType) factory.findType("Z")));
         assertEquals("Z", t.getDescriptor());
         assertTrue(factory == t.getTypeFactory());
         
         // char
         t = (PrimitiveType) factory.getChar();
         assertTrue(t.isChar());
         assertEquals(char.class, t.toClass());
         assertTrue(t == ((PrimitiveType) factory.fromClass(char.class)));
         assertTrue(t == ((PrimitiveType) factory.findType("C")));
         assertEquals("C", t.getDescriptor());
         assertTrue(factory == t.getTypeFactory());
         
         // byte
         t = (PrimitiveType) factory.getByte();
         assertTrue(t.isByte());
         assertEquals(byte.class, t.toClass());
         assertTrue(t == ((PrimitiveType) factory.fromClass(byte.class)));
         assertTrue(t == ((PrimitiveType) factory.findType("B")));
         assertEquals("B", t.getDescriptor());
         assertTrue(factory == t.getTypeFactory());
         
         // short
         t = (PrimitiveType) factory.getShort();
         assertTrue(t.isShort());
         assertEquals(short.class, t.toClass());
         assertTrue(t == ((PrimitiveType) factory.fromClass(short.class)));
         assertTrue(t == ((PrimitiveType) factory.findType("S")));
         assertEquals("S", t.getDescriptor());
         assertTrue(factory == t.getTypeFactory());
         
         // int
         t = (PrimitiveType) factory.getInt();
         assertTrue(t.isInt());
         assertEquals(int.class, t.toClass());
         assertTrue(t == ((PrimitiveType) factory.fromClass(int.class)));
         assertTrue(t == ((PrimitiveType) factory.findType("I")));
         assertEquals("I", t.getDescriptor());
         assertTrue(factory == t.getTypeFactory());
         
         // long
         t = (PrimitiveType) factory.getLong();
         assertTrue(t.isLong());
         assertEquals(long.class, t.toClass());
         assertTrue(t == ((PrimitiveType) factory.fromClass(long.class)));
         assertTrue(t == ((PrimitiveType) factory.findType("J")));
         assertEquals("J", t.getDescriptor());
         assertTrue(factory == t.getTypeFactory());
         
         // float
         t = (PrimitiveType) factory.getFloat();
         assertTrue(t.isFloat());
         assertEquals(float.class, t.toClass());
         assertTrue(t == ((PrimitiveType) factory.fromClass(float.class)));
         assertTrue(t == ((PrimitiveType) factory.findType("F")));
         assertEquals("F", t.getDescriptor());
         assertTrue(factory == t.getTypeFactory());
         
         // double
         t = (PrimitiveType) factory.getDouble();
         assertTrue(t.isDouble());
         assertEquals(double.class, t.toClass());
         assertTrue(t == ((PrimitiveType) factory.fromClass(double.class)));
         assertTrue(t == ((PrimitiveType) factory.findType("D")));
         assertEquals("D", t.getDescriptor());
         assertTrue(factory == t.getTypeFactory());
         
         // Test isSubtypeOf(IType)
         assertTrue(factory.getBoolean().isSubtypeOf(factory.getBoolean()));
         assertFalse(factory.getBoolean().isSubtypeOf(factory.getByte()));
         
     }
}

package com.mackenziehigh.autumn.compiler.typesystem;

import com.mackenziehigh.autumn.lang.compiler.typesystem.TypeFactory;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IArrayType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IElementType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.ITypeFactory;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * This is a test of the ArrayType class.
 *
 * @author Mackenzie High
 */
public final class ArrayTypeTest
{
    @Test
    public void test()
    {
        final ITypeFactory factory = new TypeFactory();

        final IArrayType array1 = factory.getArrayType((IElementType) factory.fromClass(Object.class), 5);

        final IArrayType array2 = factory.getArrayType((IElementType) factory.fromClass(String.class), 5);

        final IArrayType array3 = factory.getArrayType((IElementType) factory.fromClass(String.class), 3);


        assertEquals(factory.fromClass(Object.class), array1.getElement());
        assertEquals(5, array1.getDimensions());

        assertTrue(array1.isArrayType());
        assertFalse(array1.isDeclaredType());
        assertFalse(array1.isNullType());
        assertFalse(array1.isPrimitiveType());
        assertTrue(array1.isReferenceType());
        assertFalse(array1.isVoidType());

        assertEquals("[[[[[Ljava/lang/Object;", array1.getDescriptor());

        assertTrue(array2.isSubtypeOf(array1));
        assertFalse(array1.isSubtypeOf(array2));
        assertFalse(array3.isSubtypeOf(array2));

        assertEquals(null, array1.toClass());

        assertTrue(factory == array1.getTypeFactory());
    }
}

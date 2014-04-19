///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package high.mackenzie.autumn.compiler.typesystem;
//
//import high.mackenzie.autumn.lang.compiler.typesystem.AnnotationFactory;
//import high.mackenzie.autumn.lang.compiler.typesystem.TypeFactory;
//import high.mackenzie.autumn.lang.compiler.typesystem.design.AnnotationValueForms;
//import high.mackenzie.autumn.lang.compiler.typesystem.design.IAnnotation;
//import high.mackenzie.autumn.lang.compiler.typesystem.design.IAnnotationMethod;
//import high.mackenzie.autumn.lang.compiler.typesystem.design.IAnnotationValue;
//import high.mackenzie.autumn.lang.compiler.typesystem.design.IType;
//import high.mackenzie.autumn.lang.compiler.typesystem.design.ITypeFactory;
//import com.google.common.collect.Maps;
//import java.lang.annotation.Annotation;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.reflect.Field;
//import java.util.Arrays;
//import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
///**
// *
// * @author mackenzie
// */
//public class AnnotationFactoryTest
//{
//    @Anno1
//    private static class AnnotationProvider1
//    {
//    }
//
//    private static enum Places
//    {
//        AMSTERDAM,
//        BERLIN,
//        CONSTANTINOPLE,
//        DENMARK,
//        ESTONIA,
//        FRANKFURT,
//        LONDON,
//    }
//
//    @Retention(RetentionPolicy.RUNTIME)
//    private static @interface Anno1
//    {
//        public boolean method1() default true;
//
//        public boolean method2() default false;
//
//        public char method3() default 'A';
//
//        public char method4() default 'Z';
//
//        public byte method5() default 12;
//
//        public byte method6() default 34;
//
//        public short method7() default 56;
//
//        public short method8() default 78;
//
//        public int method9() default 10;
//
//        public int method10() default 11;
//
//        public long method11() default 12;
//
//        public long method12() default 13;
//
//        public float method13() default 14;
//
//        public float method14() default 15;
//
//        public double method15() default 16;
//
//        public double method16() default 17;
//
//        public String method17() default "Neptune";
//
//        public String method18() default "Pluto";
//
//        public Class method19() default LinkedList.class;
//
//        public Class method20() default int.class;
//
//        public Places method21() default Places.BERLIN;
//
//        public Places method22() default Places.LONDON;
//
//        public Anno2 method23() default @Anno2();
//
//        public Anno2 method24() default @Anno2("Mars");
//
//        public boolean[] method25() default
//        {
//            true,
//            false,
//            true,
//            true,
//        };
//
//        public char[] method26() default
//        {
//            'L',
//            'I',
//            'S',
//            'P'
//        };
//
//        public byte[] method27() default
//        {
//            101,
//            102,
//            103,
//            104,
//        };
//
//        public short[] method28() default
//        {
//            201,
//            202,
//            203,
//            204,
//        };
//
//        public int[] method29() default
//        {
//            301,
//            302,
//            303,
//            304,
//        };
//
//        public long[] method30() default
//        {
//            401,
//            402,
//            403,
//            404,
//        };
//
//        public float[] method31() default
//        {
//            501,
//            502,
//            503,
//            504,
//        };
//
//        public double[] method32() default
//        {
//            601,
//            602,
//            603,
//            604,
//        };
//
//        public String[] method33() default
//        {
//            "Antelope",
//            "Bear",
//            "Cat",
//            "Dog",
//        };
//
//        public Class[] method34() default
//        {
//            byte.class,
//            Map.class,
//            Set.class,
//            long.class,
//        };
//
//        public Places[] method35() default
//        {
//            Places.AMSTERDAM,
//            Places.BERLIN,
//            Places.DENMARK,
//            Places.ESTONIA,
//        };
//
//        public Anno2[] method36() default
//        {
//            @Anno2(),
//            @Anno2(),
//            @Anno2("Jupiter"),
//            @Anno2("Uranus"),
//        };
//    }
//
//    private static @interface Anno2
//    {
//        public String value() default "Venus";
//    }
//
//    /**
//     * Test: 20130726072630228641
//     *
//     * <p>
//     * Case: Test Everything <br>
//     * This test also tests the
//     * <code>AnnotationValue</code> class in passing. <br>
//     * </p>
//     */
//    @Test
//    @Anno1
//    public void test20130726072630228641()
//            throws NoSuchFieldException
//    {
//        System.out.println("Test: 20130726072630228641");
//
//        final ITypeFactory type_factory = new TypeFactory();
//
//        final AnnotationFactory factory = new AnnotationFactory(type_factory);
//
//        final Annotation anno1 = AnnotationProvider1.class.getAnnotation(Anno1.class);
//
//        final IAnnotation annotation = factory.newAnnotation(anno1);
//
//        assertTrue(annotation.getAnnotationType().toClass() == Anno1.class);
//
//        String method;
//        IAnnotationValue value;
//        AnnotationValueForms form;
//
//        method = "method1";
//        value = annotation.getMapping(method);
//        form = value.type();
//        assertEquals(AnnotationValueForms.BOOLEAN, form);
//        assertEquals(true, value.asBoolean());
//
//        method = "method2";
//        value = annotation.getMapping(method);
//        form = value.type();
//        assertEquals(AnnotationValueForms.BOOLEAN, form);
//        assertEquals(false, value.asBoolean());
//
//        method = "method3";
//        value = annotation.getMapping(method);
//        form = value.type();
//        assertEquals(AnnotationValueForms.CHAR, form);
//        assertEquals('A', value.asChar());
//
//        method = "method4";
//        value = annotation.getMapping(method);
//        form = value.type();
//        assertEquals(AnnotationValueForms.CHAR, form);
//        assertEquals('Z', value.asChar());
//
//        method = "method5";
//        value = annotation.getMapping(method);
//        form = value.type();
//        assertEquals(AnnotationValueForms.BYTE, form);
//        assertEquals(12, value.asByte());
//
//        method = "method6";
//        value = annotation.getMapping(method);
//        form = value.type();
//        assertEquals(AnnotationValueForms.BYTE, form);
//        assertEquals(34, value.asByte());
//
//        method = "method7";
//        value = annotation.getMapping(method);
//        form = value.type();
//        assertEquals(AnnotationValueForms.SHORT, form);
//        assertEquals(56, value.asShort());
//
//        method = "method8";
//        value = annotation.getMapping(method);
//        form = value.type();
//        assertEquals(AnnotationValueForms.SHORT, form);
//        assertEquals(78, value.asShort());
//
//        method = "method9";
//        value = annotation.getMapping(method);
//        form = value.type();
//        assertEquals(AnnotationValueForms.INT, form);
//        assertEquals(10, value.asInt());
//
//        method = "method10";
//        value = annotation.getMapping(method);
//        form = value.type();
//        assertEquals(AnnotationValueForms.INT, form);
//        assertEquals(11, value.asInt());
//
//        method = "method11";
//        value = annotation.getMapping(method);
//        form = value.type();
//        assertEquals(AnnotationValueForms.LONG, form);
//        assertEquals(12, value.asLong());
//
//        method = "method12";
//        value = annotation.getMapping(method);
//        form = value.type();
//        assertEquals(AnnotationValueForms.LONG, form);
//        assertEquals(13, value.asLong());
//
//
//        method = "method13";
//        value = annotation.getMapping(method);
//        form = value.type();
//        assertEquals(AnnotationValueForms.FLOAT, form);
//        assertEquals(14, value.asFloat(), 0.01);
//
//        method = "method14";
//        value = annotation.getMapping(method);
//        form = value.type();
//        assertEquals(AnnotationValueForms.FLOAT, form);
//        assertEquals(15, value.asFloat(), 0.01);
//
//        method = "method15";
//        value = annotation.getMapping(method);
//        form = value.type();
//        assertEquals(AnnotationValueForms.DOUBLE, form);
//        assertEquals(16, value.asDouble(), 0.01);
//
//        method = "method16";
//        value = annotation.getMapping(method);
//        form = value.type();
//        assertEquals(AnnotationValueForms.DOUBLE, form);
//        assertEquals(17, value.asDouble(), 0.01);
//
//        method = "method17";
//        value = annotation.getMapping(method);
//        form = value.type();
//        assertEquals(AnnotationValueForms.STRING, form);
//        assertEquals("Neptune", value.asString());
//
//        method = "method18";
//        value = annotation.getMapping(method);
//        form = value.type();
//        assertEquals(AnnotationValueForms.STRING, form);
//        assertEquals("Pluto", value.asString());
//
//        IType clazz;
//
//        method = "method19";
//        value = annotation.getMapping(method);
//        form = value.type();
//        clazz = type_factory.fromClass(LinkedList.class);
//        assertEquals(AnnotationValueForms.CLASS, form);
//        assertEquals(clazz, value.asClass());
//
//        method = "method20";
//        value = annotation.getMapping(method);
//        form = value.type();
//        clazz = type_factory.getInt();
//        assertEquals(AnnotationValueForms.CLASS, form);
//        assertEquals(clazz, value.asClass());
//
//        Field constant;
//
//        method = "method21";
//        value = annotation.getMapping(method);
//        form = value.type();
//        constant = Places.class.getField("BERLIN");
//        assertEquals(AnnotationValueForms.ENUM, form);
//        assertEquals(constant, value.asEnum().getField());
//
//        method = "method22";
//        value = annotation.getMapping(method);
//        form = value.type();
//        constant = Places.class.getField("LONDON");
//        assertEquals(AnnotationValueForms.ENUM, form);
//        assertEquals(constant, value.asEnum().getField());
//
//        IAnnotation anno;
//
//        method = "method23";
//        value = annotation.getMapping(method);
//        form = value.type();
//        assertEquals(AnnotationValueForms.ANNOTATION, form);
//        assertEquals(Anno2.class, value.asAnnotation().getAnnotationType().toClass());
//        assertEquals("Venus", value.asAnnotation().getMapping("value").asString());
//
//        method = "method24";
//        value = annotation.getMapping(method);
//        form = value.type();
//        assertEquals(AnnotationValueForms.ANNOTATION, form);
//        assertEquals(Anno2.class, value.asAnnotation().getAnnotationType().toClass());
//        assertEquals("Mars", value.asAnnotation().getMapping("value").asString());
//
//        method = "method25";
//        value = annotation.getMapping(method);
//        form = value.type();
//        assertEquals(AnnotationValueForms.BOOLEAN_ARRAY, form);
//        assertEquals(4, value.asBooleanArray().length);
//        assertEquals(true, value.asBooleanArray()[0]);
//        assertEquals(false, value.asBooleanArray()[1]);
//        assertEquals(true, value.asBooleanArray()[2]);
//        assertEquals(true, value.asBooleanArray()[3]);
//
//        method = "method26";
//        value = annotation.getMapping(method);
//        form = value.type();
//        assertEquals(AnnotationValueForms.CHAR_ARRAY, form);
//        assertEquals(4, value.asCharArray().length);
//        assertEquals('L', value.asCharArray()[0]);
//        assertEquals('I', value.asCharArray()[1]);
//        assertEquals('S', value.asCharArray()[2]);
//        assertEquals('P', value.asCharArray()[3]);
//
//        method = "method27";
//        value = annotation.getMapping(method);
//        form = value.type();
//        assertEquals(AnnotationValueForms.BYTE_ARRAY, form);
//        assertEquals(4, value.asByteArray().length);
//        assertEquals(101, value.asByteArray()[0]);
//        assertEquals(102, value.asByteArray()[1]);
//        assertEquals(103, value.asByteArray()[2]);
//        assertEquals(104, value.asByteArray()[3]);
//
//        method = "method28";
//        value = annotation.getMapping(method);
//        form = value.type();
//        assertEquals(AnnotationValueForms.SHORT_ARRAY, form);
//        assertEquals(4, value.asShortArray().length);
//        assertEquals(201, value.asShortArray()[0]);
//        assertEquals(202, value.asShortArray()[1]);
//        assertEquals(203, value.asShortArray()[2]);
//        assertEquals(204, value.asShortArray()[3]);
//
//        method = "method29";
//        value = annotation.getMapping(method);
//        form = value.type();
//        assertEquals(AnnotationValueForms.INT_ARRAY, form);
//        assertEquals(4, value.asIntArray().length);
//        assertEquals(301, value.asIntArray()[0]);
//        assertEquals(302, value.asIntArray()[1]);
//        assertEquals(303, value.asIntArray()[2]);
//        assertEquals(304, value.asIntArray()[3]);
//
//        method = "method30";
//        value = annotation.getMapping(method);
//        form = value.type();
//        assertEquals(AnnotationValueForms.LONG_ARRAY, form);
//        assertEquals(4, value.asLongArray().length);
//        assertEquals(401, value.asLongArray()[0]);
//        assertEquals(402, value.asLongArray()[1]);
//        assertEquals(403, value.asLongArray()[2]);
//        assertEquals(404, value.asLongArray()[3]);
//
//        method = "method31";
//        value = annotation.getMapping(method);
//        form = value.type();
//        assertEquals(AnnotationValueForms.FLOAT_ARRAY, form);
//        assertEquals(4, value.asFloatArray().length);
//        assertEquals(501, value.asFloatArray()[0], 0.01);
//        assertEquals(502, value.asFloatArray()[1], 0.01);
//        assertEquals(503, value.asFloatArray()[2], 0.01);
//        assertEquals(504, value.asFloatArray()[3], 0.01);
//
//        method = "method32";
//        value = annotation.getMapping(method);
//        form = value.type();
//        assertEquals(AnnotationValueForms.DOUBLE_ARRAY, form);
//        assertEquals(4, value.asDoubleArray().length);
//        assertEquals(601, value.asDoubleArray()[0], 0.01);
//        assertEquals(602, value.asDoubleArray()[1], 0.01);
//        assertEquals(603, value.asDoubleArray()[2], 0.01);
//        assertEquals(604, value.asDoubleArray()[3], 0.01);
//
//        method = "method33";
//        value = annotation.getMapping(method);
//        form = value.type();
//        assertEquals(AnnotationValueForms.STRING_ARRAY, form);
//        assertEquals(4, value.asStringArray().length);
//        assertEquals("Antelope", value.asStringArray()[0]);
//        assertEquals("Bear", value.asStringArray()[1]);
//        assertEquals("Cat", value.asStringArray()[2]);
//        assertEquals("Dog", value.asStringArray()[3]);
//
//        method = "method34";
//        value = annotation.getMapping(method);
//        form = value.type();
//        assertEquals(AnnotationValueForms.CLASS_ARRAY, form);
//        assertEquals(4, value.asClassArray().length);
//        clazz = type_factory.getByte();
//        assertEquals(clazz, value.asClassArray()[0]);
//        clazz = type_factory.fromClass(Map.class);
//        assertEquals(clazz, value.asClassArray()[1]);
//        clazz = type_factory.fromClass(Set.class);
//        assertEquals(clazz, value.asClassArray()[2]);
//        clazz = type_factory.getLong();
//        assertEquals(clazz, value.asClassArray()[3]);
//
//        method = "method35";
//        value = annotation.getMapping(method);
//        form = value.type();
//        assertEquals(AnnotationValueForms.ENUM_ARRAY, form);
//        assertEquals(4, value.asEnumArray().length);
//        assertEquals(Places.AMSTERDAM.name(), value.asEnumArray()[0].getName());
//        assertEquals(Places.AMSTERDAM.ordinal(), value.asEnumArray()[0].getOrdinal());
//        assertEquals(Places.BERLIN.name(), value.asEnumArray()[1].getName());
//        assertEquals(Places.BERLIN.ordinal(), value.asEnumArray()[1].getOrdinal());
//        assertEquals(Places.DENMARK.name(), value.asEnumArray()[2].getName());
//        assertEquals(Places.DENMARK.ordinal(), value.asEnumArray()[2].getOrdinal());
//        assertEquals(Places.ESTONIA.name(), value.asEnumArray()[3].getName());
//        assertEquals(Places.ESTONIA.ordinal(), value.asEnumArray()[3].getOrdinal());
//
//        method = "method36";
//        value = annotation.getMapping(method);
//        form = value.type();
//        assertEquals(AnnotationValueForms.ANNOTATION_ARRAY, form);
//        assertEquals(4, value.asAnnotationArray().length);
//        anno = value.asAnnotationArray()[0];
//        assertEquals("Venus", anno.getMapping("value").asString());
//        anno = value.asAnnotationArray()[1];
//        assertEquals("Venus", anno.getMapping("value").asString());
//        anno = value.asAnnotationArray()[2];
//        assertEquals("Jupiter", anno.getMapping("value").asString());
//        anno = value.asAnnotationArray()[3];
//        assertEquals("Uranus", anno.getMapping("value").asString());
//    }
//}


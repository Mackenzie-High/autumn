/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.resources;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * This class is used during testing of the implementation.
 *
 * @author mackenzie
 */
public class TestHelper1
{
    public static boolean field01;

    public static char field02;

    public static byte field03;

    public static short field04;

    public static int field05;

    public static long field06;

    public static float field07;

    public static double field08;

    public static String field09;

    public static Object field10;

    public static void printAnnotations(final Class clazz)
    {
        try
        {
            final Method m = clazz.getMethod("main", String[].class);

            final Annotation[] array = m.getDeclaredAnnotations();

            for (Annotation a : array)
            {
                System.out.println(a.annotationType().getName());
            }
        }
        catch (Exception ex)
        {
        }
    }
}

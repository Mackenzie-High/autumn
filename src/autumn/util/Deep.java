package autumn.util;

import autumn.lang.Record;
import autumn.lang.annotations.InferReturnType;
import autumn.lang.compiler.ast.commons.IConstruct;
import java.util.List;
import java.util.Map;

/**
 * This class provides static utility methods for manipulating object trees.
 *
 * <p>
 * The purpose of this class
 * </>
 *
 * @author mackenzie
 */
public final class Deep
{
    /**
     * This method sets an element in an object tree.
     *
     * <p>
     * The purpose of this method is to simplify setting elements deep inside immutable objects.
     * </p>
     *
     * @param object is the root object.
     * @param path is the path to the element to set.
     * @param value is the value to place into the object tree.
     * @return a modified version of the object tree, which will be a copy of the object,
     * if the object is immutable.
     */
    @InferReturnType
    public static Record set(final Record object,
                             final List<?> path,
                             final Object value)
    {
        return null;
    }

    @InferReturnType
    public static List set(final List<?> object,
                           final List<?> path,
                           final Object value)
    {
        return null;
    }

    @InferReturnType
    public static Map<?, ?> set(final Map<?, ?> object,
                                final List<?> path,
                                final Object value)
    {
        return null;
    }

    @InferReturnType
    public static IConstruct set(final IConstruct object,
                                 final List<?> path,
                                 final Object value)
    {


        return null;
    }

    public static Object get(final Record object,
                             final List<?> path)
    {
        return null;
    }

    public static Object get(final List<?> object,
                             final List<?> path)
    {
        return null;
    }

    public static Object get(final Map<?, ?> object,
                             final List<?> path)
    {
        return null;
    }

    public static Object get(final IConstruct object,
                             final List<?> path)
    {
        return null;
    }

    public static List<Object> parentsOf(final Record object,
                                         final List<?> path)
    {
        return null;
    }

    public static List<Object> parentsOf(final List<?> object,
                                         final List<?> path)
    {
        return null;
    }

    public static List<Object> parentsOf(final Map<?, ?> object,
                                         final List<?> path)
    {
        return null;
    }

    public static List<Object> parentsOf(final IConstruct object,
                                         final List<?> path)
    {
        return null;
    }
}

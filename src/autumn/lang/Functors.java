/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autumn.lang;

/**
 * This class provides static utility methods that create common functor objects.
 *
 * @author M ackenzie High
 */
public final class Functors
{
    /**
     * This method creates a functor that ensures its argument it within a given range.
     *
     * @param minimum is the minimum value that the functor will accept.
     * @param maximum is the maximum value that the functor will accept.
     * @return the aforedescribed functor.
     */
    public static TypedFunctor checkRange(final int minimum,
                                          final int maximum)
    {
        return null;
    }

    public static TypedFunctor checkRange(final long minimum,
                                          final long maximum)
    {
        return null;
    }

    public static TypedFunctor checkRange(final double minimum,
                                          final double maximum)
    {
        return null;
    }

    public static TypedFunctor checkRange(final Comparable minimum,
                                          final Comparable maximum)
    {
        return null;
    }

    public static TypedFunctor checkNonnull()
    {
        return null;
    }

    public static TypedFunctor checkRegex(final String regex)
    {
        return null;
    }

    public static TypedFunctor checkType(final Class type)
    {
        return null;
    }

    public static TypedFunctor checkSubtype(final Class supertype)
    {
        return null;
    }

    public static TypedFunctor immutable()
    {
        return null;
    }

    public static TypedFunctor readonly()
    {
        return null;
    }

    public static TypedFunctor copy()
    {
        return null;
    }

    public static TypedFunctor cloner()
    {
        return null;
    }
}

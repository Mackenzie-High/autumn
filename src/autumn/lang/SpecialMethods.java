package autumn.lang;

/**
 * An instance of this class provides functors that implement special methods.
 *
 * @author Mackenzie High
 */
public final class SpecialMethods
{
    private final Functor compare;

    private final Functor equals;

    private final Functor hash;

    private final Functor string;

    /**
     * Constructor.
     *
     * @param compare is the compareTo(Object) handler.
     * @param equals is the equals(Object) handler.
     * @param hash is the hashCode() handler.
     * @param string is the toString() handler.
     */
    public SpecialMethods()
    {
        this(null, null, null, null);
    }

    /**
     * Constructor.
     *
     * @param compare is the compareTo(Object) handler.
     * @param equals is the equals(Object) handler.
     * @param hash is the hashCode() handler.
     * @param string is the toString() handler.
     */
    private SpecialMethods(final Functor compare,
                           final Functor equals,
                           final Functor hash,
                           final Functor string)
    {
        super();
        this.compare = compare;
        this.equals = equals;
        this.hash = hash;
        this.string = string;
    }

    public SpecialMethods setCompareTo(final Functor handler)
    {
        return new SpecialMethods(handler, equals, hash, string);
    }

    public SpecialMethods setEquals(final Functor handler)
    {
        return new SpecialMethods(compare, handler, hash, string);
    }

    public SpecialMethods setHashCode(final Functor handler)
    {
        return new SpecialMethods(compare, equals, handler, string);
    }

    public SpecialMethods setToString(final Functor handler)
    {
        return new SpecialMethods(compare, equals, hash, handler);
    }

    public Functor getCompareTo()
    {
        return compare;
    }

    public Functor getEquals()
    {
        return compare;
    }

    public Functor getHashCode()
    {
        return compare;
    }

    public Functor getToString()
    {
        return compare;
    }
}

package autumn.lang;

/**
 * An instance of this class provides functors that implement special methods.
 *
 * @author Mackenzie High
 */
public final class SpecialMethods
{
    public static final SpecialMethods EMPTY = new SpecialMethods(null, null, null, null, null);

    private final TypedFunctor iterator;

    private final TypedFunctor compare;

    private final TypedFunctor equals;

    private final TypedFunctor hash;

    private final TypedFunctor string;

    /**
     * Constructor.
     *
     * @param compare is the compareTo(Object) handler.
     * @param equals is the equals(Object) handler.
     * @param hash is the hashCode() handler.
     * @param string is the toString() handler.
     */
    public static SpecialMethods instance()
    {
        return EMPTY;
    }

    /**
     * Constructor.
     *
     * @param iterator is the iterator() handler.
     * @param compare is the compareTo(Object) handler.
     * @param equals is the equals(Object) handler.
     * @param hash is the hashCode() handler.
     * @param string is the toString() handler.
     */
    private SpecialMethods(final TypedFunctor iterator,
                           final TypedFunctor compare,
                           final TypedFunctor equals,
                           final TypedFunctor hash,
                           final TypedFunctor string)
    {
        super();
        this.iterator = iterator;
        this.compare = compare;
        this.equals = equals;
        this.hash = hash;
        this.string = string;
    }

    public SpecialMethods setIterator(final TypedFunctor handler)
    {
        return new SpecialMethods(handler, compare, equals, hash, string);
    }

    public SpecialMethods setCompareTo(final TypedFunctor handler)
    {
        return new SpecialMethods(iterator, handler, equals, hash, string);
    }

    public SpecialMethods setEquals(final TypedFunctor handler)
    {
        return new SpecialMethods(iterator, compare, handler, hash, string);
    }

    public SpecialMethods setHashCode(final TypedFunctor handler)
    {
        return new SpecialMethods(iterator, compare, equals, handler, string);
    }

    public SpecialMethods setToString(final TypedFunctor handler)
    {
        return new SpecialMethods(iterator, compare, equals, hash, handler);
    }

    public TypedFunctor getIterator()
    {
        return iterator;
    }

    public TypedFunctor getCompareTo()
    {
        return compare;
    }

    public TypedFunctor getEquals()
    {
        return compare;
    }

    public TypedFunctor getHashCode()
    {
        return compare;
    }

    public TypedFunctor getToString()
    {
        return compare;
    }
}

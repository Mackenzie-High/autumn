package autumn.util.ds;

/**
 * An instance of this interface is a persistent set data-structure.
 *
 * @author Mackenzie High
 */
public interface FunctionalSet<E>
        extends FunctionalCollection<E>
{
    /**
     * This method creates a mutable copy of this object.
     *
     * @return a mutable copy of this object.
     */
    @Override
    public MutableSet<E> mutable();

    /**
     * This method creates an immutable copy of this object.
     *
     * @return an immutable copy of this object.
     */
    @Override
    public ImmutableSet<E> immutable();

    public FunctionalSet<E> add(E value);

    public FunctionalSet<E> addAll(Iterable<? extends E> values);

    public FunctionalSet<E> remove(E value);

    public FunctionalSet<E> removeAll(Iterable<? extends E> values);

    public FunctionalSet<E> clear();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(E value);

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsAll(Iterable<? extends E> values);

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsAny(Iterable<? extends E> values);

    /**
     * {@inheritDoc}
     */
    @Override
    public int size();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty();
}

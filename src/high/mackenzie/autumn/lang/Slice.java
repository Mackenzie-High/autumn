package high.mackenzie.autumn.lang;

import com.google.common.base.Preconditions;
import java.util.AbstractList;
import java.util.List;

/**
 * An instance of this class provides a slice view of a linear data structure.
 *
 * @author Mackenzie High
 */
public final class Slice
        extends AbstractList
{
    private final List list;

    private final int start;

    private final int end;

    private final int step;

    public Slice(final List list,
                 final int start,
                 final int end,
                 final int step)
    {
        Preconditions.checkNotNull(list);
        Preconditions.checkArgument(start <= end, "Slice Ctor: start > end");
        Preconditions.checkArgument(step >= 1, "Clice Ctor: step < 1");

        this.list = list;
        this.start = start;
        this.end = end;
        this.step = step;
    }

    private int computeIndex(final int index)
    {
        return start + index * step;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object set(final int index,
                      final Object value)
    {
        return list.set(computeIndex(index), value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object get(final int index)
    {
        return list.get(computeIndex(index));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size()
    {
        return (end - start) / step;
    }
}

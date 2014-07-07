package autumn.lang;

import java.util.List;

/**
 * An instance of this interface is a tuple.
 *
 * @author Mackenzie High
 */
public interface Tuple<T extends Tuple>
{
    public boolean isEmpty();

    public int size();

    public boolean contains(Object value);

    public Tuple<T> set(int index,
                        Object value);

    public Object get(int index);

    public List<?> elements();
}

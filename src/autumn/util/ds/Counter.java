package autumn.util.ds;

import java.math.BigInteger;
import java.util.Map;

/**
 * An instance of this interface is a data-structure that can be used for counting values.
 *
 * @author Mackenzie High
 */
public interface Counter<T>
{
    public void add(T value);

    public void remove(T value);

    public BigInteger count(T value);

    public BigInteger total();

    public Map<T, BigInteger> toMap();
}

package autumn.util.ds;

import java.util.Map;

/**
 * An instance of this interface is a data-structure that can be used for counting values.
 *
 * @author Mackenzie High
 */
public interface Counter<T>
{
    /**
     * This method adds the value to the total.
     *
     * @param value is the value to count.
     */
    public void add(T value);

    /**
     * This method subtracts the value from the total.
     *
     * @param value is the value to subtract.
     */
    public void subtract(T value);

    /**
     * This method counts the number of times that a particular value was encountered.
     *
     * @param value is the value to count.
     * @return the number of times the value was added to this counter.
     */
    public int count(T value);

    /**
     * This method creates map that maps a value to the number of times it was counted.
     *
     * <p>
     * A count may be negative, if more subtractions occurred than additions.
     * </p>
     *
     * @return the aforesaid map that is backed by this counter.
     */
    public Map<T, Integer> toMap();
}

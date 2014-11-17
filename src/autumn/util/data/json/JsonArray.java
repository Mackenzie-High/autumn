package autumn.util.data.json;

import java.util.List;

/**
 * An instance of this class is a mutable JSON array/list.
 *
 * @author Mackenzie High
 */
public interface JsonArray
        extends JsonValue,
                List<JsonValue>
{
    /**
     * This method retrieves the first element in this list.
     *
     * @return the first element herein.
     * @throws NoSuchElementException if this list is empty.
     */
    public JsonValue first();

    /**
     * This method retrieves the last element in this list.
     *
     * @return the last element herein.
     * @throws NoSuchElementException if this list is empty.
     */
    public JsonValue last();
}

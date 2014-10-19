package autumn.util.tables;

import java.util.Set;

/**
 *
 * @author mackenzie
 */
public interface Table
{
    public int columnCount();

    public int rowCount();

    public Set<String> headers();

    public Row rows();

    public Query finder();
}

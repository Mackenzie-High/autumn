package autumn.util.dataflow;

/**
 * An instance of this interface is a node in a dataflow graph.
 *
 * @author Mackenzie High
 */
public interface Worker<T>
{
    public String name();

    public int id();

    public void process(final T data);
}

package autumn.util.ds.graphs;

/**
 * An instance of this interface is an edge in a graph.
 *
 * @author Mackenzie High
 */
public interface GraphEdge
{
    /**
     * This method sets the identifier of this edge.
     *
     * @param tag is the new identifier of this edge.
     */
    public void setTag(Object tag);

    /**
     * This method gets the identifier of this edge.
     *
     * @return the identifier of this edge.
     */
    public Object getTag();

    /**
     * This method sets the data stored in this edge.
     *
     * @param value is the data that will now be stored in this node.
     */
    public void setData(Object value);

    /**
     * This method gets the data stored in this node.
     *
     * @return the data currently stored in this node, or null, if no such data exists.
     */
    public Object getData();

    /**
     * This method causes this edge to become an undirected edge.
     */
    public void redirect();

    /**
     * This method causes this edge to become a directed edge.
     *
     * @param tail is the node that the edge will emanate from.
     * @param head is the node that the edge will point to.
     * @throws IllegalArgumentException if (tail != tail()) && (tail != head()).
     * @throws IllegalArgumentException if (head != tail()) && (head != head()).
     */
    public void redirect(GraphNode tail,
                         GraphNode head);

    /**
     * This method determines whether this is a directed edge.
     *
     * @return true, iff this is a directed edge.
     */
    public boolean isDirected();

    /**
     * This method determines whether this is an undirected edge.
     *
     * @return true, iff this is an undirected edge.
     */
    public boolean isUndirected();

    /**
     * This method retrieves the first node that this edge is connected to.
     *
     * <p>
     * If this is a directed edge, then this is the node that the edge emanates from.
     * </p>
     *
     * <p>
     * The value returned by this method will change, if the direction of this edge changes.
     * </p>
     *
     * @return the first node.
     */
    public GraphNode tail();

    /**
     * This method retrieves the second node that this edge is connected to.
     *
     * <p>
     * If this is a directed edge, then this is the node that the edge points to.
     * </p>
     *
     * <p>
     * The value returned by this method will change, if the direction of this edge changes.
     * </p>
     *
     * @return the second node.
     */
    public GraphNode head();
}

package autumn.util.ds.graphs;

import java.util.Set;

/**
 * An instance of this interface is a node in a graph.
 *
 * @author Mackenzie High
 */
public interface GraphNode
{
    /**
     * This method sets the identifier of this node.
     *
     * @param tag is the new identifier of this node.
     */
    public void setTag(Object tag);

    /**
     * This method gets the identifier of this node.
     *
     * @return the identifier of this node.
     */
    public Object getTag();

    /**
     * This method sets the data stored in this node.
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
     * This method gets a set-view of the edges connected to this node.
     *
     * <p>
     * Changes made to the returned set will be reflected in th graph.
     * </p>
     *
     * @return a new set-view, backed by this object, that lazily produces the aforesaid edges.
     */
    public Set<GraphEdge> edges();
}

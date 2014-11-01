package autumn.util.ds.graphs;

import java.util.Set;

/**
 * An instance of this interface is a data-structure that represents a graph.
 *
 * @author Mackenzie High
 */
public interface Graph<T, N, E>
{
    /**
     * This method determines whether this is a directed graph.
     *
     * @return true, iff this is a directed graph.
     */
    public boolean isDirected();

    /**
     * This method determines whether this is an undirected graph.
     *
     * @return true, iff this is an undirected graph.
     */
    public boolean isUndirected();

    /**
     * This method adds a node to this graph.
     *
     * @return the newly added node.
     */
    public GraphNode addNode();

    /**
     * This method adds an edge to this graph.
     *
     * <p>
     * Note: An edge may be connected to the same node on both ends, which produces a loop.
     * </p>
     *
     * @param tail is one of the nodes that the edge is connected to.
     * @param head is one of the nodes that the edge is connected to.
     * @return the new edge.
     */
    public GraphEdge addEdge(GraphNode tail,
                             GraphNode head);

    /**
     * This method creates a set-view of the nodes in this graph.
     *
     * <p>
     * Changes made to the returned set will be reflected in th graph.
     * </p>
     *
     * @return a new set-view, backed by this object, which lazily provides the nodes herein.
     */
    public Set<GraphNode> nodes();

    /**
     * This method creates a set-view of the edges in this graph.
     *
     * <p>
     * Changes made to the returned set will be reflected in th graph.
     * </p>
     *
     * @return a new set-view, backed by this object, which lazily provides the edges herein.
     */
    public Set<GraphEdge> edges();

    /**
     * This method counts the number of nodes in this graph.
     *
     * @return the number of nodes herein.
     */
    public int nodeCount();

    /**
     * This method counts the number of edges in this graph.
     *
     * @return the number of edges herein.
     */
    public int edgeCount();

    /**
     * This method determines whether this object equals another object.
     *
     * <p>
     * Equality Algorithm:
     * <ol>
     * <li>If other is null, return false.</li>
     * <li>If other is this object, return true.</li>
     * <li>If other is not a Graph, return false.</li>
     * <li>If this.nodes() does not equal other.nodes(), return false.</li>
     * <li>If this.edges() does not equal other.edges(), return false.</li>
     * <li>Return true.</li>
     * </ol>
     * </p>
     *
     * @param other is a value that may equal this object.
     * @return true, iff this object equals other.
     */
    @Override
    public boolean equals(Object other);

    /**
     * This method computes the hash-code of this graph.
     *
     * @return <code> nodes().hashCode() ^ edges.hashCode() </code>
     */
    @Override
    public int hashCode();
}

package autumn.util.ds.graphs;

import autumn.util.functors.Predicate;
import high.mackenzie.autumn.util.ds.SimpleGraph.Edge;
import java.util.List;

/**
 * This class provides static utility functions related to graph data-structures.
 *
 * @author Mackenzie High
 */
public final class Graphs
{
    /**
     * This method creates an iterable whose iterator performs
     * a depth-first-transversal of a graph.
     *
     * @param start is the initial node.
     * @return the aforedescribed iterable.
     */
    public static Iterable<GraphNode> dfs(final GraphNode start)
    {
        return null;
    }

    /**
     * This method creates an iterable whose iterator performs
     * a breadth-first-transversal of a graph.
     *
     * @param start is the initial node.
     * @return the aforedescribed iterable.
     */
    public static Iterable<GraphNode> bfs(final GraphNode start)
    {
        return null;
    }

    /**
     * This method searches a graph for a particular node using depth-first-search.
     *
     * @param start is the initial node in the search.
     * @param tag is the identifier of the node.
     * @return the node that was found, or null, if no node was found.
     */
    public static GraphNode findDFS(final GraphNode start,
                                    final Object tag)
    {
        return null;
    }

    /**
     * This method searches a graph for a particular node using depth-first-search.
     *
     * @param start is the initial node in the search.
     * @param condition is a predicate that will recognize the sought after node.
     * @return the node that was found, or null, if no node was found.
     */
    public static GraphNode findDFS(final GraphNode start,
                                    final Predicate condition)
    {
        return null;
    }

    /**
     * This method searches a graph for a particular node using breadth-first-search.
     *
     * @param start is the initial node in the search.
     * @param tag is the identifier of the node.
     * @return the node that was found, or null, if no node was found.
     */
    public static GraphNode findBFS(final GraphNode start,
                                    final Object tag)
    {
        return null;
    }

    /**
     * This method searches a graph for a particular node using breadth-first-search.
     *
     * @param start is the initial node in the search.
     * @param condition is a predicate that will recognize the sought after node.
     * @return the node that was found, or null, if no node was found.
     */
    public static GraphNode findBFS(final GraphNode start,
                                    final Predicate condition)
    {
        return null;
    }

    /**
     * This method determines whether every other node is reachable from an initial node.
     *
     * @param start is the initial node.
     * @return true, iff every other node in the graph can be reached from the initial node.
     */
    public static boolean isConnected(final GraphNode start)
    {
        return false;
    }

    /**
     * This method finds a path between two nodes using a depth-first-search.
     *
     * @param start is the start point.
     * @param destination is the end point.
     * @return the path from the start to the end.
     */
    public List<Edge> pathDFS(final GraphNode start,
                              final GraphNode destination)
    {
        return null;
    }

    /**
     * This method finds a path between two nodes using a breadth-first-search.
     *
     * @param start is the start point.
     * @param destination is the end point.
     * @return the path from the start to the end.
     */
    public List<Edge> pathBFS(final GraphNode start,
                              final GraphNode destination)
    {
        return null;
    }

    /**
     * This method finds the minimum-spanning-tree of a graph using Kruskal's algorithm.
     *
     * @param start
     * @return
     */
    public List<Edge> minimumSpanningTree(final GraphNode start)
    {
        return null;
    }

    /**
     * This method finds the shortest path between two points using Dijkstra's algorithm.
     *
     * @param start
     * @param destination
     * @return
     */
    public static List<Edge> shortestPath(final GraphNode start,
                                          final GraphNode destination)
    {
        return null;
    }
}

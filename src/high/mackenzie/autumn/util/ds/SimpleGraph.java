package high.mackenzie.autumn.util.ds;

import autumn.lang.Functor;
import com.google.common.collect.Lists;
import java.util.List;

/**
 * An instance of this class is a graph.
 *
 * @param <N> is the type of the value stored in a node.
 * @param <E> is the type of the value stored in an edge.
 * @author Mackenzie High
 */
public final class SimpleGraph<N, E>
{
    public static final class Node<X, Y>
    {
        private X value;

        private final List<Node<X, Y>> edges = Lists.newLinkedList();

        public void remove()
        {
        }
    }

    public static final class Edge<X, Y>
    {
        private Node<X, Y> node1;

        private Node<X, Y> node2;

        // TODO: direction
        private Y value;

        public void remove()
        {
        }
    }

    public Node<N, E> addNode(final N value)
    {
        return null;
    }

    public Edge<N, E> addDirectedEdge(final Node<N, E> start,
                                      final Node<N, E> end,
                                      final E value)
    {
        return null;
    }

    public Edge<N, E> addUndirectedEdge(final Node<N, E> start,
                                        final Node<N, E> end,
                                        final E value)
    {
        return null;
    }

    public boolean isUndirected()
    {
        return false; // TODO
    }

    public void bfs(final Functor visitor)
    {
    }

    public void dfs(final Functor visitor)
    {
    }
}

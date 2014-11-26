package high.mackenzie.autumn.util.ds;

import high.mackenzie.autumn.util.ds.AvlTree.Node;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author mackenzie
 */
public interface ITree
{
    /**
     * This method removes all the nodes from the tree.
     *
     * <p>
     * This is a constant-time operation.
     * </p>
     *
     * @return a modified copy of the tree.
     */
    ITree clear();

    /**
     *
     * @param key
     * @return
     */
    ITree delete(final Object key);

    /**
     *
     * @param key
     * @return
     */
    Node find(final Object key);

    /**
     * Leave this as public.
     *
     * @param key
     * @return
     */
    Node find(final Node node,
              final Object key);

    /**
     * This method non-destructively inserts a node into the tree.
     *
     * @param key is the key of the new node in the tree.
     * @param value is the value of the new node in the tree.
     * @return a modified copy of the tree.
     */
    ITree insert(final Object key,
                 final Object value);

    Iterator keys();

    /**
     *
     * @return
     */
    List<Node> nodes();

    /**
     * This method determines the size of this tree.
     *
     * @return the number of nodes in this tree.
     */
    int size();

    Iterator values();
}

package high.mackenzie.autumn.util.ds;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * An instance of this class is an immutable AVL tree.
 *
 * http://en.wikipedia.org/wiki/AVL_tree
 * http://users.cis.fiu.edu/~weiss/dsaajava/code/DataStructures/AvlTree.java
 *
 * @author mackenzie
 */
public class AvlTree
        implements Serializable
{
    /**
     * An instance of this class is a node in an AVL tree.
     */
    public static final class Node
            implements Serializable
    {
        public static long count;

        public final int height;

        public final int size;

        public final Object key;

        public final Object value;

        public final Node left;

        public final Node right;

        public Node(final Object key,
                    final Object value,
                    Node left,
                    Node right)
        {
            ++count;

            final int height_of_left = left == null ? -1 : left.height;
            final int height_of_right = right == null ? -1 : right.height;
            final int max = height_of_left > height_of_right ? height_of_left : height_of_right;

            this.height = max + 1;
            this.size = (left == null ? 0 : left.size) + (right == null ? 0 : right.size) + 1;
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public boolean isLeaf()
        {
            return left == null && right == null;
        }

        public boolean isSingleParent()
        {
            return (left == null && right != null) || (left != null && right == null);
        }

        @Override
        public String toString()
        {
            return "" + value;
        }
    }

    /**
     * If this field is non-null, then this comparator is used to compare the keys of nodes.
     */
    private final Comparator comparator = null;

    /**
     * This is the root of the tree.
     *
     * If this field is null, then the tree is empty.
     */
    public final Node root;

    /**
     * Constructor.
     */
    public AvlTree()
    {
        this(null, null);
    }

    /**
     * Constructor.
     *
     * @param old is the tree that is being semi-copied.
     * @param root is the root of the new tree, which may be null.
     */
    private AvlTree(final AvlTree old,
                    final Node root)
    {
        this.root = root;
    }

    /**
     * This method verifies that a node is properly ordered.
     *
     * @param node is the node to check.
     * @return true.
     */
    private boolean check(final Node node)
    {
        if (node.left != null)
        {
            assert compare(node.key, node.left.key) > 0;
        }

        if (node.right != null)
        {
            assert compare(node.key, node.right.key) < 0;
        }

        return true;
    }

    /**
     * This method ensures that this tree is actually balanced.
     *
     * @return true.
     * @throws IllegalStateException if this tree is unbalanced.
     */
    public boolean requireBalance()
    {
        if (root != null)
        {
            final int balance_factor = height(root.left) - height(root.right);

            final boolean condition = Math.abs(balance_factor) < 2;

            Preconditions.checkState(condition, "Unbalanced AVL Tree: BF = " + balance_factor);
        }

        return true;
    }

    /**
     * This method compares two keys to each other.
     *
     * @param key1 is the left operand.
     * @param key2 is the right operand.
     * @return (negative = less, zero = equal, positive = greater)
     */
    private int compare(final Object key1,
                        final Object key2)
    {
        // TODO: comparator
        return ((Comparable) key1).compareTo(key2);
    }

    /**
     * This method non-destructively inserts a node into the tree.
     *
     * @param key is the key of the new node in the tree.
     * @param value is the value of the new node in the tree.
     * @return a modified copy of the tree.
     */
    public AvlTree insert(final Object key,
                          final Object value)
    {
        final Node new_root;

        new_root = insert(root, key, value);

        assert requireBalance();

        return new AvlTree(this, new_root);
    }

    /**
     * This method non-destructively inserts a node into the subtree.
     *
     * @param node is the subtree itself.
     * @param key is the key of the new node in the subtree.
     * @param value is the value of the new node in the subtree.
     * @return a modified copy of the subtree.
     */
    private Node insert(final Node node,
                        final Object key,
                        final Object value)
    {
        if (node == null)
        {
            return new Node(key, value, null, null);
        }

        final int cmp = compare(key, node.key);

        final Node new_node;

        if (cmp < 0)
        {
            final Node left = insert(node.left, key, value);

            new_node = new Node(node.key, node.value, left, node.right);
        }
        else if (cmp > 0)
        {
            final Node right = insert(node.right, key, value);

            new_node = new Node(node.key, node.value, node.left, right);
        }
        else // equal
        {
            new_node = new Node(node.key, value, node.left, node.right);
        }

        final Node result = balance(new_node);

        return result;
    }

    /**
     * This method non-destructively balances a subtree.
     *
     * @param node is the subtree itself.
     * @return a modified copy of the subtree.
     */
    private Node balance(final Node node)
    {
        if (node == null)
        {
            return node;
        }

        Node p = node;

        final int balance_factor = height(p.left) - height(p.right);

        if (balance_factor == 2)
        {
            final int left_balance_factor = height(p.left.left) - height(p.left.right);

            if (left_balance_factor == -1)
            {
                p = rotateLeft(node.left);
            }

            p = rotateRight(p);
        }
        else if (balance_factor == -2)
        {
            final int right_balance_factor = height(p.right.left) - height(p.right.right);

            if (right_balance_factor == 1)
            {
                p = rotateRight(node.right);
            }

            p = rotateLeft(p);
        }

        final int new_balance_factor = height(p.left) - height(p.right);

        assert p == node ? Math.abs(new_balance_factor) < 2 : true;

        assert check(p);

        return p;
    }

    /**
     * This method determines the height of a subtree.
     *
     * @param node is the subtree itself.
     * @return -1, if the node is null; otherwise, return the height of the subtree.
     */
    private int height(final Node node)
    {
        return node == null ? -1 : node.height;
    }

    /**
     * This method performs a non-destructive left-rotation.
     *
     * @param node is the root of the subtree.
     * @return a modified copy of the subtree.
     */
    private Node rotateLeft(final Node node)
    {
        final Node child = new Node(node.key, node.value, node.left, node.right.left);

        final Node parent = new Node(node.right.key, node.right.value, child, node.right.right);

        return parent;
    }

    /**
     * This method performs a non-destructive right-rotation.
     *
     * @param node is the root of the subtree.
     * @return a modified copy of the subtree.
     */
    private Node rotateRight(final Node node)
    {
        final Node child = new Node(node.key, node.value, node.left.right, node.right);

        final Node parent = new Node(node.left.key, node.left.value, node.left.left, child);

        return parent;
    }

    /**
     * This method determines the size of this tree.
     *
     * @return the number of nodes in this tree.
     */
    public int size()
    {
        return size(root);
    }

    /**
     * This
     *
     * @param node
     * @return
     */
    private int size(Node node)
    {
        return node == null ? 0 : node.size;
    }

    /**
     *
     * @param key
     * @return
     */
    public Node find(final Object key)
    {
        return find(root, key);
    }

    /**
     * Leave this as public.
     *
     * @param key
     * @return
     */
    public Node find(final Node node,
                     final Object key)
    {
        if (node == null)
        {
            return null;
        }

        final int cmp = compare(key, node.key);

        if (cmp < 0)
        {
            return find(node.left, key);
        }
        else if (cmp > 0)
        {
            return find(node.right, key);
        }
        else // equal
        {
            return node;
        }
    }

    /**
     * This method removes all the nodes from the tree.
     *
     * <p>
     * This is a constant-time operation.
     * </p>
     *
     * @return a modified copy of the tree.
     */
    public AvlTree clear()
    {
        return new AvlTree(this, null);
    }

    /**
     *
     * @param key
     * @return
     */
    public AvlTree delete(final Object key)
    {
        final Node new_root;

        new_root = delete(root, key);

        assert requireBalance();

        return new AvlTree(this, new_root);
    }

    /**
     *
     * @param node
     * @param key
     * @param value
     * @return
     */
    private Node delete(final Node node,
                        final Object key)
    {
        if (node == null)
        {
            return null;
        }

        final int cmp = compare(key, node.key);

        final Node new_node;

        if (cmp < 0)
        {
            final Node left = delete(node.left, key);

            new_node = new Node(node.key, node.value, left, node.right);
        }
        else if (cmp > 0)
        {
            final Node right = delete(node.right, key);

            new_node = new Node(node.key, node.value, node.left, right);
        }
        else // equal
        {
            if (node.isLeaf())
            {
                new_node = null;
            }
            else if (node.left == null && node.right != null)
            {
                new_node = node.right;
            }
            else if (node.left != null && node.right == null)
            {
                new_node = node.left;
            }
            else
            {
                if (node.left.size >= node.right.size)
                {
                    final Node predecessor = findMax(node.left);

                    final Node new_left = delete(node.left, predecessor.key);

                    new_node = new Node(predecessor.key, predecessor.value, new_left, node.right);
                }
                else
                {
                    final Node successor = findMin(node.right);

                    final Node new_right = delete(node.right, successor.key);

                    new_node = new Node(successor.key, successor.value, node.left, new_right);
                }
            }
        }

        final Node result = balance(new_node);

        return result;
    }

    /**
     *
     * @param node
     * @return
     */
    public static Node findMin(final Node node)
    {
        if (node == null)
        {
            return null;
        }

        if (node.left != null)
        {
            return findMin(node.left);
        }
        else
        {
            return node;
        }
    }

    /**
     *
     * @param node
     * @return
     */
    public static Node findMax(final Node node)
    {
        if (node == null)
        {
            return null;
        }

        if (node.right != null)
        {
            return findMax(node.right);
        }
        else
        {
            return node;
        }
    }

    /**
     *
     * @return
     */
    public List<Node> nodes()
    {
        final List<Node> list = Lists.newArrayListWithExpectedSize(size());

        nodes(list, root);

        return list;
    }

    /**
     *
     * @param root
     * @return
     */
    public static List<Node> nodes(final Node root)
    {
        final List<Node> list = Lists.newLinkedList();

        nodes(list, root);

        return list;
    }

    /**
     *
     * @param list
     * @param node
     */
    private static void nodes(final List<Node> list,
                              final Node node)
    {
        if (node != null)
        {
            nodes(list, node.left);
            list.add(node);
            nodes(list, node.right);
        }
    }

    public Iterator values()
    {
        // TODO: make lazy

        final Iterator<Node> nodes = this.nodes().iterator();

        final Iterator values = new Iterator()
        {
            @Override
            public boolean hasNext()
            {
                return nodes.hasNext();
            }

            @Override
            public Object next()
            {
                return nodes.next().value;
            }

            @Override
            public void remove()
            {
                throw new UnsupportedOperationException("Not Supported");
            }
        };

        return values;
    }

    public Iterator keys()
    {
        // TODO: make lazy

        final Iterator<Node> nodes = this.nodes().iterator();

        final Iterator keys = new Iterator()
        {
            @Override
            public boolean hasNext()
            {
                return nodes.hasNext();
            }

            @Override
            public Object next()
            {
                return nodes.next().key;
            }

            @Override
            public void remove()
            {
                throw new UnsupportedOperationException("Not Supported");
            }
        };

        return keys;
    }

    /**
     *
     * @param args
     */
    public static void main(final String[] args)
    {
        AvlTree p = new AvlTree();

        for (int i = 0; i < 26; i++)
        {
            p = p.insert(i, "" + (char) ('A' + i));
        }

        for (int i = 25; i >= 0; i--)
        {
            System.out.println("Size = " + p.root.size);
            System.out.println(nodes(p.root));
            System.out.println();

            p = p.delete(i);
        }

        System.out.println("Size = " + p.size());
        System.out.println(p.nodes());
        System.out.println();
    }
}

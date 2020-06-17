package chapter4.algs4.binary_search_tree.certification;


import java.util.HashSet;
import java.util.NoSuchElementException;

/**
 * 3.2.32 Certification. Write a method isBST() that takes a Node as argument and returns true
 * if the argument node is the root of a binary search tree, false otherwise.
 * Hint : This task is also more difficult than it might seem, because the order in which you call the methods in the previous three exercises is important.
 *
 * Solution :
 *     private boolean isBST()
 *     {
 *        if (!isBinaryTree(root)) return false;
 *        if (!isOrdered(root, min(), max())) return false;
 *        if (!hasNoDuplicates(root)) return false;
 *        return true;
 *     }
 *
 *  tips:
 *      1.判定某个二叉树的节点是否为跟节点，首先
 *          a).判定是否为标准二叉树
 *          b).再次判断是否按顺序排列
 *          c).最后查看是否有重复值
 *
 */
public abstract class AbstractCertification<Key extends Comparable<Key>, Value> {
    protected Node root;
    protected
    class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int size;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.size = 0;
        }
    }

    public abstract boolean isBinaryTree();

    public abstract boolean isOrdered(Key key, Key max, Key min);

    public int size(Node node) { return node.size; }

    public boolean hasNoDuplicates(Node node) {
        HashSet<Integer> set = new HashSet<>();
        return hasNoDuplicates(node, set);
    }

    private boolean hasNoDuplicates(Node root, HashSet set) {
        if (root == null) return true;
        if (set.contains(root.key)) return false;
        set.add(root.key);
        if (!hasNoDuplicates(root.left, set)) return false;
        if (!hasNoDuplicates(root.right,set)) return false;
        return true;
    }

    public abstract boolean isBTS(Node node);

    public Node get(Key key) {
        return get(root, key);
    }

    private Node get(Node root, Key key) {
        if (root == null) return null;
        int cmp = key.compareTo(root.key);
        if (cmp < 0) return get(root.left, key);
        if (cmp > 0) return get(root.right, key);
        return root;
    }
    public Key min() {
        Node result = min(root);
        return result == null ? null : result.key;
    }

    public Key min(Key key) {
        Node target = get(key);
        Node result = min(target);
        return result == null ? null : result.key;
    }

    private Node min(Node root) {
        if (root == null) throw new NoSuchElementException();
        if (root.left != null) return min(root.left);
        return root;
    }

    public Key max(Key key) {
        Node target = get(key);
        Node result = max(target);
        return result == null ? null : result.key;
    }

    public Key max() {
        Node result = max(root);
        return result == null ? null : result.key;
    }


    private Node max(Node root) {
        if (root == null) throw new NoSuchElementException();
        if (root.right != null) return max(root.right);
        return root;
    }

    public void put(Key key, Value value) {
        root = put(root, new Node(key, value));
    }

    public Node put(Node root, Node node) {
        if (root == null) {
            node.size++;
            return node;
        }
        int cmp = node.key.compareTo(root.key);
        if (cmp < 0) { root.left = put(root.left, node); }
        if (cmp > 0) { root.right = put(root.right, node); }
        root.size++;
        return root;
    }


}

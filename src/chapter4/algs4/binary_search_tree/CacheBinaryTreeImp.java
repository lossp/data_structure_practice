package chapter4.algs4.binary_search_tree;


/**
 * 3.2.28 Software caching.
 * Modify BST to keep the most recently accessed Node in an instance variable so that it can be accessed in constant time
 * if the next put() or get() uses the same key
 */
public class CacheBinaryTreeImp<Key extends Comparable<Key>, Value> {
    private Node root;
    private Node recent;

    private class Node {
        Key key;
        Value value;
        int size;
        Node left;
        Node right;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.size = 0;
        }
    }

    public Value get(Key key) {
        if (recent != null && recent.key.equals(key)) return recent.value;
        Node result = get(root, key);
        if (result == null) return null;
        recent = result;
        return result == null ? null : result.value;
    }

    private Node get(Node root, Key key) {
        if (root == null) return null;
        int cmp = key.compareTo(root.key);
        if (cmp > 0) return get(root.right, key);
        if (cmp < 0) return get(root.left, key);
        return root;
    }

    public void put(Key key, Value value) {
        root = put(root, new Node(key, value));
    }

    private Node put(Node root, Node node) {
        if (root == null) {
            node.size++;
            return node;
        }
        int cmp = node.key.compareTo(root.key);
        if (cmp < 0) {
            root.left = put(root.left, node);
        }
        if (cmp > 0) {
            root.right = put(root.right, node);
        }
        node.size++;
        return root;
    }
}

package chapter4.algs4.binary_search_tree.orderCheck;


/**
 * 3.2.30
 * Order check. Write a recursive method isOrdered() that takes a Node and two keys min and max as arguments
 * and returns true if all the keys in the tree are between min and max; min and max are indeed the smallest
 * and largest keys in the tree, respectively; and the BST ordering property holds for all keys in the tree;
 * false otherwise.
 *
 * tips:
 *  获取针对节点的最大节点以及最小节点
 */
public abstract class AbstractOrderCheckBinaryTree<Key extends Comparable<Key>, Value> {
    protected Node root;

    protected class Node {
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

    public abstract boolean isOrdered(Key key, Key max, Key min);

    public Node get(Key key) {
        return get(root, key);
    }

    private Node get(Node root, Key key) {
        if (root == null) return null;
        int cmp = key.compareTo(root.key);
        if (cmp > 0) return get(root.right, key);
        if (cmp < 0) return get(root.left, key);
        return root;
    }

    public Key min(Key key) {
        Node target = get(root, key);
        Node node = min(target);
        return node == null ? null : node.key;
    }

    private Node min(Node root) {
        if (root == null) return null;
        if (root.left != null) {
            return min(root.left);
        }
        return root;
    }

    public Key max(Key key) {
        Node target = get(root, key);
        Node node = max(target);
        return node == null ? null : node.key;
    }

    private Node max(Node root) {
        if (root == null) return null;
        if (root.right != null) {
            return max(root.right);
        }
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
        if (cmp < 0) root.left = put(root.left, node);
        if (cmp > 0) root.right = put(root.right, node);
        if (cmp == 0) {
            root.value = node.value;
            return root;
        }
        root.size++;
        return root;
    }

    public void printTreeMap() {
        StringBuilder stringBuilder = new StringBuilder();
        printTreeMap(stringBuilder,"", "", root);
        System.out.println(stringBuilder.toString());
    }

    private void printTreeMap(StringBuilder sb,String padding, String pointer, Node node) {
        if (node != null) {
            sb.append(padding);
            sb.append(pointer);
            sb.append(node.value);
            sb.append("\n");

            StringBuilder paddingBuilder = new StringBuilder(padding);
            paddingBuilder.append("|  ");

            String paddingForBoth = paddingBuilder.toString();
            String pointerForRight = "└──";
            String pointerForLeft = (node.right != null) ? "├──" : "└──";

            printTreeMap(sb, paddingForBoth, pointerForLeft, node.left);
            printTreeMap(sb, paddingForBoth, pointerForRight, node.right);
        }
    }
}

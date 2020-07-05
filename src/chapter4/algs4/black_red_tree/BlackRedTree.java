package chapter4.algs4.black_red_tree;


/**
 *  此数据结构默认在红色连接处，左侧一个为较小值，右侧一个为较大值，因此红色连接总是向左下倾斜
 *
 *  tips:
 *      如果右子节点是红色而左子节点是黑色，进行左旋转
 *      如果左子节点是红色并且它的左子节点也是红色，进行右旋转
 *      如果左右两个节点均为红色，进行颜色转换
 */
public class BlackRedTree<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    private class Node {
        Key key;
        Value value;
        Node left, right;
        int number;
        boolean color;

        Node (Key key, Value value, int number, boolean color) {
            this.key = key;
            this.value = value;
            this.number = number;
            this.color = color;
        }
    }

    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    public Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.number = h.number;
        h.number = size(h.left) + size(h.right) + 1;
        return x;
    }

    public Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.number = h.number;
        h.number = size(h.left) + size(h.right) + 1;
        return x;
    }

    public int size(Node node) {
        return node.number;
    }

    public void flipColors(Node x) {
        x.color = RED;
        x.left.color = BLACK;
        x.right.color = BLACK;
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    // TODO 这部分及其重要，需要补全各种情况以及注释
    private Node put(Node h, Key key, Value value) {
        if (h == null) return new Node(key, value,1, RED);
        int cmp = key.compareTo(h.key);
        if (cmp < 0) { h.left = put(h.left, key, value); }
        else if (cmp > 0) { h.right = put(h.right, key, value); }
        else h.value = value;

        if (isRed(h.right) && isRed(h.left)) flipColors(h);
        if (isRed(h.left) && isRed(h.left.left))  h = rotateRight(h);
        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        h.number = size(h.left) + size(h.right) + 1;
        return h;
    }
}

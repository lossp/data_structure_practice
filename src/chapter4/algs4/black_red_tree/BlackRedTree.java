package chapter4.algs4.black_red_tree;


/**
 *  此数据结构默认在红色连接处，左侧一个为较小值，右侧一个为较大值，因此红色连接总是向左下倾斜
 *
 *  tips:
 *      如果右子节点是红色而左子节点是黑色，进行左旋转
 *      如果左子节点是红色并且它的左子节点也是红色，进行右旋转
 *      如果左右两个节点均为红色，进行颜色转换
 *
 *      之所以存在rotateRight以及rotateLeft方法，是因为当某个节点的左子树和右子树高度差距大于1的时候，不满足成为AVL-Tree的条件
 *      因此需要左旋转和右旋转，将子树高度差距缩短为1。当然，这也只适用一部分情况，还有一些情况需要两边同时旋转
 *
 *      有关根节点的颜色总是黑色:
 *          如果某个节点的颜色是红色节点，则表明该节点所在位置是一个3-节点，而作为根节点，不存在3-情况，即使当前的根节点为3-节点，通过转换，会生成新的2-根节点，原有根节点会变成子节点。
 *          因此根节点颜色总是黑色。
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
        // 这部分理解为当插入新节点的时候，理解新节点与其父节点形成一个2-节点，平衡起来看，就是通过红色连接将二者连接起来
        // 因此每次默认插入的节点的颜色为红色
        if (h == null) return new Node(key, value,1, RED);
        int cmp = key.compareTo(h.key);
        if (cmp < 0) { h.left = put(h.left, key, value); }
        else if (cmp > 0) { h.right = put(h.right, key, value); }
        else h.value = value;

        /**
         * 需要处理的三种情况
         *  1.节点的左子树以及右子树均为红色，要进行颜色翻转
         *  2.节点的左子树是红色并且它的左子树也为红色，需要进行右旋转，将其变成情况1。
         *  3.节点的右子树为红色，左子树为黑色，需要发生左旋转
         */
        if (isRed(h.right) && isRed(h.left)) flipColors(h);
        if (isRed(h.left) && isRed(h.left.left))  h = rotateRight(h);
        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        h.number = size(h.left) + size(h.right) + 1;
        return h;
    }
}

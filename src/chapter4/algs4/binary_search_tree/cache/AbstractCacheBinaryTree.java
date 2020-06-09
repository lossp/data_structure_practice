package chapter4.algs4.binary_search_tree.cache;


/**
 * 3.2.28 Software caching.
 * Modify BST to keep the most recently accessed Node in an instance variable so that it can be accessed in constant time
 * if the next put() or get() uses the same key
 *
 *  tips:
 *      solution - 1. 可以使用二叉树映射到数组上，这样可以保存相应的index来实现O(1)时间来获取最近访问的节点，缺点在于需要将二叉树所有元素复制到数组上，空间为2N
 *                    简而言之就是利用数组来当作缓冲区
 *
 *                    需要遍历数组，采用中序遍历的方法
 *
 *                    QUESTION1: 如何将Key 降级为int? 才能将泛型Key值转为数组的index
 *                      SOLUTION: 将此类定义为Abstract，在子类中去具体定义相应的数据结构
 */
public abstract class AbstractCacheBinaryTree<Key extends Comparable<Key>, Value> {
    protected Node root;
    protected Key recentIndex;

    protected class Node {
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
        Node result = get(root, key);
        if (result == null) return null;
        recentIndex = key;
        return result.value;
    }

    private Node get(Node root, Key key) {
        if (root == null) return null;
        int cmp = key.compareTo(root.key);
        if (cmp > 0) return get(root.right, key);
        if (cmp < 0) return get(root.left, key);
        return root;
    }

    public void put(Key key, Value value) {
        recentIndex = key;
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

    public int size() {
        return root.size;
    }

    abstract void inOrderTravel();
}

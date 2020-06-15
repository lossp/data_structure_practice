package chapter4.algs4.binary_search_tree;

/**
 * 3.2.31 Equal key check. Write a method hasNoDuplicates()
 * that takes a Node as argument and returns true if there are
 * no equal keys in the binary tree rooted at the argument node, false otherwise.
 * Assume that the test of the previous exercise has passed.
 *
 *
 * Question: 1.已经完美平衡的二叉树，为何会出现两个相同的Key值？
 *           2.相同的Key值，如何分配在子树中？
 */
public class EqualKeyCheckImp<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
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

        public boolean sameKey(Node node) {
            return this.key.equals(node.key);
        }
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
        root.size++;
        return root;
    }

    public Value get(Key key) {
        Node node = get(root, key);
        return node == null ? null : node.value;
    }

    private Node get(Node root, Key key) {
        if (root == null) return null;
        int cmp = key.compareTo(root.key);
        if (cmp > 0) return get(root.right, key);
        if (cmp < 0) return get(root.left, key);
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

    public boolean hasNoDuplicates(Key key) {
        // TODO
//        Node node = hasNoDuplicates(root, new Node(key, null));
        return false;
    }

    private Node hasNoDuplicates(Node root, Node node) {
        // TODO
        return null;
//        if (node.sameKey(root)) return root;
//        int cmp = node.key.compareTo(root.key);

    }

    public static void main(String[] args) {
        EqualKeyCheckImp equalKeyCheckImp = new EqualKeyCheckImp();
        equalKeyCheckImp.put(5, "fifth");
        equalKeyCheckImp.put(4, "forth");
        equalKeyCheckImp.put(8, "eighth");
        equalKeyCheckImp.put(9, "ninth");
        equalKeyCheckImp.put(10, "tenth");
        equalKeyCheckImp.put(6, "sixth");
        equalKeyCheckImp.printTreeMap();

    }

}
